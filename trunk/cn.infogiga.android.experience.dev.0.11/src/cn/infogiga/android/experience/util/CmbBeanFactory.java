package cn.infogiga.android.experience.util;

import cn.infogiga.android.experience.bean.ErrInfo;
import cn.infogiga.android.experience.bean.PCInfo;
import cn.infogiga.android.experience.bean.ReceiveBean;

public class CmbBeanFactory {
	public static ReceiveBean getUpdateReceiveBean(PCInfo pcInfo){
		ReceiveBean recieveBean = getDefaultReceiveBean(pcInfo);
		recieveBean.setType("6");
		recieveBean.setScene("6");
		return recieveBean;
	}
	
	public static ReceiveBean getItemSendBean(PCInfo pcInfo){
		ReceiveBean recieveBean = getDefaultReceiveBean(pcInfo);
		recieveBean.setType("4");
		recieveBean.setScene("4");
		return recieveBean;
	}
	
	public static ReceiveBean getMenuIdSendBean(PCInfo pcInfo){
		ReceiveBean recieveBean = getDefaultReceiveBean(pcInfo);
		recieveBean.setType("12");
		return recieveBean;
	}
	
	public static ReceiveBean getLoginSendBean(PCInfo pcInfo){
		ReceiveBean recieveBean = getDefaultReceiveBean(pcInfo);
		recieveBean.setType("2");
		recieveBean.setScene("2");
		return recieveBean;
	}
	
	public static ReceiveBean getOrderSendBean(PCInfo pcInfo){
		ReceiveBean recieveBean = getDefaultReceiveBean(pcInfo);
		recieveBean.setType("5");
		recieveBean.setScene("5");
		return recieveBean;
	}
	
	public static ReceiveBean getSMSSendBean(PCInfo pcInfo){
		ReceiveBean recieveBean = getDefaultReceiveBean(pcInfo);
		recieveBean.setType("8");
		recieveBean.setScene("8");
		return recieveBean;
	}
	
	public static ReceiveBean getLogSendBean(PCInfo pcInfo,String type,String scene){
		ReceiveBean recieveBean = getDefaultReceiveBean(pcInfo);
		recieveBean.setType(type);
		recieveBean.setScene(scene);
		return recieveBean;
	}
	
	private static ReceiveBean getDefaultReceiveBean(PCInfo pcInfo){
		ReceiveBean receiveBean = new ReceiveBean();
		receiveBean.setMac(pcInfo.getMac());
		receiveBean.setHarddisk(pcInfo.getHarddisk());
		receiveBean.setIp(pcInfo.getIp());
		receiveBean.setCmp_name(pcInfo.getCmp_name());
		return receiveBean;
	}
	
	public static ErrInfo getNullErrorInfo(){
		return setErrInfo("e1","报文为空","系统出现错误,错误代号：e1");
	}
	
	public static ErrInfo getNoUpdateErrInfo(){
		return setErrInfo("e2","服务器没有更新","服务器没有更新");
	}
	
	public static ErrInfo getHasUpdateErrInfo(){
		return setErrInfo("e3","","服务器有更新,是否现在升级?");
	}
	
	public static ErrInfo getNoCallbackErrInfo(){
		return setErrInfo("e4","连接boss平台出现错误","服务器无响应,请稍后再试!");
	}
	
	public static ErrInfo getAddSuccessErrInfo(){
		return setErrInfo("e5", "添加成功", "添加成功");
	}
	
	public static ErrInfo getAddFailureErrInfo(){
		return setErrInfo("e6", "添加失败", "远程服务器出现错误,错误代码：e7.");
	}
	
	public static ErrInfo getNoSuchEmployeeErrInfo(){
		return setErrInfo("e7", "没有该员工", "查询无此员工");
	}
	
	public static ErrInfo getLoginSuccessErrInfo(){
		return setErrInfo("0", "登录成功", "登录成功");
	}
	
	public static ErrInfo getNoSuchEquipmentErrInfo(){
		return setErrInfo("e8", "非法设备", "检查无次设备,请联系管理员");
	}
	

	public static ErrInfo setErrInfo(String errCode,String errMsg,String errHint){
		ErrInfo errInfo = new ErrInfo();
		errInfo.setErrCode(errCode);
		errInfo.setErrMsg(errMsg);
		errInfo.setErrHint(errHint);
		return errInfo;
	}
}
