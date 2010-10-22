package cn.infogiga.exp.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.infogiga.exp.server.xml.Intf;
import cn.infogiga.exp.server.xml.IntfParams;
import cn.infogiga.exp.server.xml.OperInfo;
import cn.infogiga.exp.webservice.CMBServiceClient;
import cn.infogiga.exp.webservice.CmbBeanFactory;
import cn.infogiga.exp.webservice.SMSServiceClient;




public class CmbService {
	private static final Log log = LogFactory.getLog(CmbService.class);
	/**
	 * 获得业务的套餐列表以及说明
	 * @param meauId
	 * @param phoneNunber
	 * @return
	 */
	public String getBusiInfo(String menuId,String phoneNumber){
		Intf intf = new Intf();
		OperInfo operInfo = CmbBeanFactory.getDefaultOperInfo();
		IntfParams intfParams = CmbBeanFactory.getBusiIntfParams();
		
		intfParams.setMenuId(menuId);
		intfParams.setBillId(phoneNumber);
		intf.setOperInfo(operInfo);
		intf.setIntfParams(intfParams);
		
		return CMBServiceClient.send(intf.toString());
	}
	
	/**
	 * 业务订购
	 * @param meauId
	 * @param funcId
	 * @param billId
	 * @param targetCode
	 * @param targetName
	 * @return
	 */
	public String sendOrder(String menuId,String funcId,String billId,String targetCode,String targetName,String sysOper,String action){
		
		Intf intf = new Intf();
		OperInfo operInfo = CmbBeanFactory.getDefaultOperInfo();
		IntfParams intfParams = CmbBeanFactory.getOrderIntfParams();
		
		intfParams.setMenuId(menuId);
		intfParams.setFuncId(funcId);
		intfParams.setBillId(billId);
		intfParams.setTargetCode(targetCode);
		intfParams.setTargetName(targetName);
		intfParams.setSysOper(sysOper);
		intfParams.setAction(action);
		intf.setOperInfo(operInfo);
		intf.setIntfParams(intfParams);

		return CMBServiceClient.send(intf.toString());
	}
	
	/**
	 * 检查该用户服务密码是否正确
	 * @param phoneNumber
	 * @param server_pwd
	 * @return
	 */
	public String checkServerPassword(String phoneNumber,String server_pwd){
		Intf intf = new Intf();
		OperInfo operInfo = CmbBeanFactory.getDefaultOperInfo();
		IntfParams intfParams = CmbBeanFactory.getServerPwdIntfParams();
		intfParams.setBillId(phoneNumber);
		intfParams.setUserPwd(server_pwd);
		intf.setOperInfo(operInfo);
		intf.setIntfParams(intfParams);
		/*intf.OPER_INFO = CmbBeanFactory.getDefaultOperInfo();
		intf.INTF_PARAMS = CmbBeanFactory.getServerPwdIntfParams();
		intf.INTF_PARAMS.BILL_ID = phoneNumber;
		intf.INTF_PARAMS.USER_PWD = server_pwd;*/
		//String s = CMBServiceClient.send(XMLReader.getServerPwdXMLStr(sendInfo));
		//return XMLReader.getServerPwdInfo(s);
		return CMBServiceClient.send(intf.toString());
	}
	
	
/*	*//**
	 * 获得菜单信息
	 * @return
	 *//*
	public String getMenuInfo(){
		Intf intf = new Intf();
		OperInfo operInfo = CmbBeanFactory.getDefaultOperInfo();
		intf.OPER_INFO = CmbBeanFactory.getDefaultOperInfo();
		intf.INTF_PARAMS = CmbBeanFactory.getMenuIntfParams();
		
		
		return CMBServiceClient.send(XMLHelper.IntfToString(intf));
	}*/
	
	
	/**
	 * 发送一条短信信息
	 * @return
	 */
	public boolean sendSMS(String phoneNumber,String msg){
		return SMSServiceClient.sendSms(phoneNumber,msg);
	}
	
	/**
	 * 发送一条wap push信息
	 * @param phoneNumber
	 * @param msg
	 * @param url
	 * @return
	 */
	public boolean sendWapPush(String phoneNumber,String url,String msg){
		return SMSServiceClient.sendWapPush(phoneNumber, url, msg);
	}
}
