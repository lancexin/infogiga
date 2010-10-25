package cn.infogiga.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.util.logging.resources.logging;

import cn.infogiga.bean.BaseBean;
import cn.infogiga.controller.WpController;
import cn.infogiga.dao.QrcodeDAO;
import cn.infogiga.service.notification.MMSBean;
import cn.infogiga.service.notification.Notification;
import cn.infogiga.service.qrcode.QrcodeBean;
import cn.infogiga.util.StringToolkit;

/**
 * 二维码导览服务
 * @author ya
 *
 */
public class QrcodeService extends BaseService{
	private static final Log log = LogFactory.getLog(QrcodeService.class);
	
	private static final Map<String, String> qrin = new HashMap<String, String>(); //保存wap页面刷二维码的记录
	private QrcodeDAO qrcodeDAO;
	private static final Notification notification =
		SystemService.getService().getHandler(SystemService.NOTIFICATION); //通知服务
	/*public QrcodeService() {
		notification = SystemService.getService().getHandler(SystemService.NOTIFICATION);
	}*/
	public void setQrcodeDAO(QrcodeDAO qrcodeDAO) {
		this.qrcodeDAO = qrcodeDAO;
	}
	
	/**
	 * 保存访问记录
	 * @param equipmentCode  设备码
	 * @param systemCode	系统码
	 * @param qrcode		二维码
	 */
	public void saveVisit(String equipmentCode, String systemCode, String qrcode) {
		qrcodeDAO.save(equipmentCode, systemCode, qrcode);
	}
	
	/**
	 * 用户在前台临时预约登记
	 * @param phoneNumber
	 */
	public boolean inUser(String phoneNumber) {		
		ArrayList<MMSBean> mmsList = new ArrayList<MMSBean>();
		MMSBean mms = new MMSBean();
		boolean inOK = qrcodeDAO.inUser(phoneNumber, mms);
		boolean sendOK = false;
		if(inOK) {
			mmsList.add(mms);
			sendOK = notification.sendMMS(mmsList);
		}
		return (inOK && sendOK);
	}
	
	/**
	 * 根据wap二维码获取区域代码，
	 * @param wapcode
	 * @return
	 */
	public String getRegionCode(String wapcode) {
		return qrin.get(wapcode);
	}
	/**
	 * 根据wap二维码获取区域代码，只能得到一次，然后就删除了
	 * @param wapcode
	 * @return
	 */
	public String pickRegionCode(String wapcode) {
		return qrin.remove(wapcode);
	}
	
	/**
	 * 刷二维码进行访问
	 * @param qrcode
	 */
	public void visit(String equipmentCode, String systemCode, String qrcode, String regionCode, String template) {
		log.info("visit");
		if(!validate(qrcode)) return;
		saveVisit(equipmentCode, systemCode, qrcode); //保存访问记录
		if(isWapVersion(qrcode)) {
			//wap版的,区域码放入map，控制器来获取值，然后跳转
			qrin.put(qrcode, regionCode);//二维码为key，区域码为value
		} else {
			//mms版的，发彩信
			MMSBean mms = new MMSBean();
			String phoneNumber = qrcodeDAO.getPhoneNumberForMmscode(qrcode);
			mms.setPhoneNumber(phoneNumber);
			mms.setQrcode(qrcode);
			mms.setTemplateName(template);
			mms.setId(Integer.MAX_VALUE+"");
			notification.sendMMS_n(mms);			
		}
	}
	
	/**
	 * 判断二维码是不是wap版
	 * @param qrcode
	 * @return
	 */
	public boolean isWapVersion(String qrcode) {
		return ('W' == qrcode.charAt(qrcode.length() - 1));
	}
	
	/**
	 * 验证二维码的合法性
	 * @param qrcode
	 * @return
	 */
	public boolean validate(String qrcode) {
		return StringToolkit.isNewQrcode(qrcode);
	}
}
