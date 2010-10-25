package cn.infogiga.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.infogiga.bean.Customer;
import cn.infogiga.bean.Qrcode;
import cn.infogiga.bean.Visit;
import cn.infogiga.service.SystemService;
import cn.infogiga.service.notification.MMSBean;
import cn.infogiga.util.DateUtil;
import cn.infogiga.util.StringToolkit;

public class QrcodeDAO extends BaseDAO{

	private static final Log log = LogFactory.getLog(QrcodeDAO.class);
	
	private static final String QR_HEAD = "ZMCC_GE_A"; //二维码的头	
	private static String lastDate; //最新的日期码字符串
	private static String lastCodeM; //最新的识别码字符串，预约的
	private static String lastCodeT; //最新的识别码字符串，临时的
	private static final int SYSTEM = 62; //默认的进制数
	private static final char[] digits = { //62进制位
		'0','1','2','3','4','5','6','7','8','9',
		'a','b','c','d','e','f','g','h','i','j',
		'k','l','m','n','o','p','q','r','s','t',
		'u','v','w','x','y','z','A','B','C','D',
		'E','F','G','H','I','J','K','L','M','N',
		'O','P','Q','R','S','T','U','V','W','X',
		'Y','Z'
	};
	
	private cn.infogiga.service.qrcode.Qrcode qrcodeService;
	
	public QrcodeDAO() {
		getLastQrcode();
		qrcodeService = SystemService.getService().getHandler(SystemService.QRCODE);
	}
	
	/**
	 * 根据二维码获取用户手机号码
	 * @param wapcode
	 * @return
	 */
	public String getPhoneNumberForWapcode(String wapcode) {
		List<Qrcode> list = findByProperty(Qrcode.class, "wapCode", wapcode);
		if(list != null && list.size() >0) {
			Qrcode qrcode = list.get(0);
			getSession().update(qrcode);
			return qrcode.getCustomer().getPhoneNumber();
		}
		return null;
	}
	
	public String getPhoneNumberForMmscode(String mmsCode) {
		List<Qrcode> list = findByProperty(Qrcode.class, "mmsCode", mmsCode);
		if(list != null && list.size() >0) {
			Qrcode qrcode = list.get(0);
			getSession().update(qrcode);
			return qrcode.getCustomer().getPhoneNumber();
		}
		return null;
	}
	
	/**
	 * 保存访问记录
	 * @param equipmentCode
	 * @param systemCode
	 * @param qrcode
	 */
	public void save(String equipmentCode, String systemCode, String qrcode) {
		char f = qrcode.charAt(qrcode.length() - 1);		
		List<Qrcode> codeList = findByProperty(Qrcode.class, "wapCode", qrcode);
		if(codeList == null || codeList.size() <= 0) {
			codeList = findByProperty(Qrcode.class, "mmsCode", qrcode);
		}
		
		Qrcode q = codeList.get(0);
		Visit visit = new Visit();
		visit.setEquipmentCode(equipmentCode);
		visit.setSystemCode(systemCode);
		visit.setQrcode(q);
		visit.setCheckMethod(String.valueOf(f));
		visit.setVisitTime(new Date());
		
		save(visit);
	}
	
	/**
	 * 注册临时用户
	 * @param phoneNumber
	 */
	@SuppressWarnings("unchecked")
	public boolean inUser(String phoneNumber, MMSBean mms) {
		log.debug("临时用户注册："+ phoneNumber);
		String[] qrcode = new String[2]; 
		boolean makeSuccess = makeQrcode(qrcode, 'T'); //创建临时二维码
		if(!makeSuccess) {			
			return false;
		}
		Customer customer = findCustomer(phoneNumber);
		if(customer == null) {
			customer = new Customer();
			customer.setUsername("guest");
			customer.setPassword(StringToolkit.createPassword());
			customer.setPhoneNumber(phoneNumber);
		}
		
		Qrcode q = new Qrcode();
		q.setWapCode(qrcode[0]);
		q.setMmsCode(qrcode[1]);
		q.setCustomer(customer);		
				
		//生成彩信
		mms.setId(q.getCodeId()+"");
		mms.setPhoneNumber(phoneNumber);
		mms.setQrcode(qrcode[1]);
		mms.setContent(qrcode[0]);
		
		attachDirty(customer);
		save(q);
		return true;
	}
	
	/**
	 * 判断二维码的末尾字符
	 * @param qrcode
	 * @return
	 */
	public char checkVersionForQrcode(String qrcode) {
		return qrcode.charAt(qrcode.length() - 1);		
	}
	
	/**
	 * 生成两个二维码，一个是wap的，一个是mms的，存放到参数字符串数组中。
	 * @param qrcode	存放二维码的字符串二维数组。0是wap的，1是mms的。
	 * @param flag	标志预约还是临时的。M是预约的，T是临时的。
	 * @return 是否生成成功
	 */
	public synchronized boolean makeQrcode(String[] qrcode, char flag) {
		if(flag != 'M' && flag != 'T') {
			log.error("flag:"+flag+"无效");
			return false;
		}
		if(qrcode == null || qrcode.length < 2) {
			log.error("qrcode可能未初始化");
			return false;
		}
		
		StringBuffer buffer = new StringBuffer(QR_HEAD);		
		if(!createNext(flag)) {
			log.error("二维码生成失败");
			return false; //生成下一个
		}
		buffer.append(flag).append(0).append(lastDate).append(flag == 'M'?lastCodeM:lastCodeT);
		
		qrcode[0] = buffer.append("W").toString();
		qrcode[1] = buffer.deleteCharAt(buffer.length()-1).append("M").toString();
				
		log.debug("二维码生成成功,wap:"+qrcode[0]+",mms:"+qrcode[1]);
		return true;
	}
	
	//更新当前的二维码信息
	private void getLastQrcode() {
		if(!getLastQrcodeFromDatabase() && !getLastQrcodeFromFile()) {
			log.debug("数据库和文件没有二维码信息，赋初始值");
			lastDate = toCustomNumber(DateUtil.computeDaysFrom(2010, 1, 1), SYSTEM);
			lastCodeM = toCustomNumber(0, SYSTEM);
			lastCodeT = toCustomNumber(0, SYSTEM);
		}
	}
		
	//从数据库获取最新的二维码
	private boolean getLastQrcodeFromDatabase() {
		ArrayList<String> qrcodesArrayList = new Database().getLastQrcode();
		ArrayList<Integer> lcCode = new ArrayList<Integer>();
		for(String code: qrcodesArrayList) {
			String lc = code.substring(11, 15);
			log.info("code:"+ lc);
			lcCode.add(fromCustomNumber(lc, SYSTEM));			
		}
		int max = Collections.max(lcCode);
		log.info("max:"+ max);
		int index = lcCode.indexOf(max);
		log.info("index:"+ index);
		String mmsCode = qrcodesArrayList.get(index);
		log.debug("查询到最新二维码:"+ mmsCode);
		return breakUp(mmsCode);
	}	
	
	//从文件获取最新的二维码
	private boolean getLastQrcodeFromFile() {
		return false;
	}
	
	//分解二维码
	private boolean breakUp(String qrcode) {
		String[] result = qrcode.split("_");
		String q; //二维码后面8位
		if(qrcode.length() != 16 && result.length != 3) {
			return false; 
		}
		
		q = result[2];
		Pattern pattern = Pattern.compile("A([MT])\\d(\\w{2})(\\w{2})[WM]");
		Matcher matcher = pattern.matcher(q);
		if(matcher.matches()) {
			log.debug("有效二维码，更新");
			lastDate = matcher.group(2);
			if(matcher.group(1).equals("M")) {
				lastCodeM = matcher.group(3);
			} else {
				lastCodeT = matcher.group(3);
			}
		} else {
			return false;
		}
		return true;
	}
	
	//生成下一个日期码和识别码,根据flag生成预约的或者临时的
	private boolean createNext(char flag) {		
		int date = fromCustomNumber(lastDate, SYSTEM);
		int duration = DateUtil.computeDaysFrom(2010, 1, 1);
		
		if (date == duration) {//同一天 
			lastDate = toCustomNumber(duration, SYSTEM);
		} else if (date < duration ){//另外一天，识别码都归零
			lastDate = toCustomNumber(duration, SYSTEM);
			lastCodeM = toCustomNumber(0, SYSTEM);
			lastCodeT = toCustomNumber(0, SYSTEM);
		} else { //最新的日期码比得到的间隔天数要大
			log.error("系统时间可能有误");
			return false;
		}
		if(flag == 'M') {
			int code = fromCustomNumber(lastCodeM, SYSTEM);
			lastCodeM = toCustomNumber(code+1, SYSTEM);
			log.debug("产生下一个预约识别码");
		} else {
			int code = fromCustomNumber(lastCodeT, SYSTEM);
			lastCodeT = toCustomNumber(code+1, SYSTEM);
			log.debug("产生下一个临时识别码");
		}
		return true;
	}
		
	/**
	 * 从制定进制的字符串得到十进制数
	 * @param numberString 制定进制的字符串
	 * @param system  进制的位数
	 * @return 得到的十进制数
	 */
	private int fromCustomNumber(String numberString, int system) {
		String str = numberString; //制定进制的字符串
		int sys = system;  //进制的位数
		int len = str.length();  //字符串长度
		char[] buf = new char[len]; //字符数组
		int result = 0;
		
		str.getChars(0, len, buf, 0);
		for(int i=0; i<len; i++) {			
			int pos = findInArray(digits, buf[len-i-1]); //字符在digits中的位置			
			result += pos * Math.pow(sys, i);
		}
		return result;
	}
	
	/**
	 * 十进制数转换为制定进制的字符串
	 * @param number 十进制数字
	 * @param system 进制的位数
	 * @return 转换后的字符串
	 */
	private String toCustomNumber(int number, int system) {
		int num = number; //十进制数字
		int sys = system; //进制的位数
		int pos = 32; //游标的位置，初始为最后一位
		char[] nums = new char[32];
		
		while (num/sys > 0) {
			nums[--pos] = digits[num%sys]; //根据余数从digits中找对应的字符
			num = num/sys;
		}
		nums[--pos] = digits[num%sys]; //商为0的时候，还有余数得放进来
		String code = new String(nums, pos, 32-pos);
		log.debug("生成62进制数："+ code);
		return code.length()<=1?"0"+code:code.substring(code.length()-2, code.length());
	}
	
	//字符数组中查找字符，返回索引。找不到则返回-1
	private int findInArray(char[] array, char c) {
		char[] cs = array;
		for(int index=0; index<cs.length; index++) {
			if(cs[index] == c) {
				return index;
			}
		}
		return -1;
	}
}
