package cn.infogiga.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringToolkit {
	
	private static final String URL_FORMAT = "http://pppeeeoni.gicp.net:8888/online/q?p=1&v=%1$s&code=%2$s&pwd=%3$s";
	private static final String GUIDER = "您好，[地点][时间]有[人数]人参观，请做好准备。";
	private static final int LEN = 5;//密码长度
	private static final char[] digits = { //62进制位
		'0','1','2','3','4','5','6','7','8','9',
		'a','b','c','d','e','f','g','h','i','j',
		'k','l','m','n','o','p','q','r','s','t',
		'u','v','w','x','y','z','A','B','C','D',
		'E','F','G','H','I','J','K','L','M','N',
		'O','P','Q','R','S','T','U','V','W','X',
		'Y','Z'
	};

	/**
	 * 是否是新的二维码
	 * @param qrcode	待检查二维码字符串
	 * @return
	 */
	public static boolean isNewQrcode(String qrcode) {
		Pattern pattern = Pattern.compile("ZMCC_GE_A[MT]\\d\\w{4}[WM]");
		Matcher matcher = pattern.matcher(qrcode);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 检查是否是空白字符串
	 * @param cstr  待检查字符串
	 * @return
	 */
	public static boolean isBlank(String cstr) {
		String str = cstr;
		if(str == null || "".equals(str)) {
			return true;
		}
		return false;
	}	
	
	/**
	 * 转化字符串，如果是空白字符串，则转成指定的字符串
	 * @param cstr	待检查的字符串
	 * @param tip	为空则转成此字符串
	 * @return
	 */
	public static String upString(String cstr, String tip) {
		return isBlank(cstr)?tip:cstr;		
	}
	
	/**
	 * 比较字符串是否相等
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean eq(String a,String b) {
		if(a == null || b == null) {
			return false;
		}
		return a.equals(b);
	}
	
	public static String arrayToString(String[] array,String s){
		int length = array.length;
		if(length <=0){
			return null;
		}else{
			StringBuffer buffer = new StringBuffer();
			for(int i = 0;i<length-1;i++){
				buffer.append(array[i]+s);
			}
			buffer.append(array[length-1]);
			return "["+buffer.toString()+"]";
		}
	}
	
	/**
	 * 随机密码
	 * @return
	 */
	public static String createPassword() {
		Random random = new Random();
		char[] chars = new char[LEN];
		for(int index=0; index<LEN; index++) {
			chars[index] = digits[random.nextInt(62)];
		}
		return new String(chars);
	}
	
	/**
	 * 解析彩信模板内容
	 * @param srcString
	 * @return
	 */
	public static String parseTemplateContent(String srcString, TemplateBean temp) {		
		return srcString.replace("[姓名]", temp.getName())
				.replace("[时间]", DateUtil.getDateString(temp.getTime(),
						new SimpleDateFormat("yyyy-MM-dd HH:mm")))
				.replace("[地点]", temp.getLocation()).replace("[url]", temp.getUrl());			
	}
	
	public static String parseTemplateForGuider(TemplateBean temp, String count) {
		return GUIDER.replace("[地点]", temp.getLocation()).replace("[时间]",
				DateUtil.getDateString(temp.getTime(),
						new SimpleDateFormat("yyyy-MM-dd HH:mm"))).replace("[人数]", count);
	}

	/**
	 * 创建访问wap的url
	 * @param args  版本号，二维码，密码
	 * @return
	 */
	public static String createUrl(String...args) {
		return String.format(URL_FORMAT, args);
	}
}
