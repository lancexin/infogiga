package cn.infogiga.exp.webservice.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cindy.util.ProperiesReader;
import cn.infogiga.exp.webservice.bean.ErrInfo;
import cn.infogiga.exp.webservice.bean.IntfParams;
import cn.infogiga.exp.webservice.bean.OperInfo;

public class CmbBeanFactory {
	private static final Log log = LogFactory.getLog(CmbBeanFactory.class);
	public static OperInfo getDefaultOperInfo(){
		ProperiesReader reader = ProperiesReader.getInstence("config.properties");
		OperInfo operInfo = new OperInfo();
		operInfo.setOpId(reader.getStringValue("op_id"));
		operInfo.setDomainId(reader.getStringValue("domain_id"));
		operInfo.setPassword(reader.getStringValue("cmb_pwd"));
		return operInfo;
	}
	
	public static IntfParams getBusiIntfParams(){
		IntfParams intfParams = new IntfParams();
		intfParams.setIntfCode("1005");
		intfParams.setMenuId("");
		intfParams.setBillId("");
		intfParams.setExtNotes("");
		intfParams.setExtNotes1("");
		return intfParams;
	}
	
	public static IntfParams getOrderIntfParams(){
		IntfParams orderIntfParams = new IntfParams();
		orderIntfParams.setIntfCode("1006");
		orderIntfParams.setAction("1");
		orderIntfParams.setMenuId("");
		orderIntfParams.setFuncId("");
		orderIntfParams.setBillId("");
		orderIntfParams.setTargetCode("");
		orderIntfParams.setTargetName("");
		orderIntfParams.setEffectType("");
		orderIntfParams.setVerifyCode("");
		orderIntfParams.setAgentId("");
		orderIntfParams.setAgentOpId("");
		orderIntfParams.setPartnerId("");
		orderIntfParams.setSysOper("");
		orderIntfParams.setLeadType("");
		orderIntfParams.setExtNotes("");
		orderIntfParams.setExtNotes1("");
		return orderIntfParams;
	}
	
	public static IntfParams getServerPwdIntfParams(){
		IntfParams serverPwdIntfParams = new IntfParams(); 
		serverPwdIntfParams.setIntfCode("1090");
		serverPwdIntfParams.setBillId("");
		serverPwdIntfParams.setUserPwd("");
		serverPwdIntfParams.setExtNotes("");
		serverPwdIntfParams.setExtNotes1("");
		return serverPwdIntfParams;
	}
	
	public static IntfParams getMenuIntfParams(){
		IntfParams serverPwdIntfParams = new IntfParams(); 
		serverPwdIntfParams.setIntfCode("1094");
		serverPwdIntfParams.setBillId("");
		serverPwdIntfParams.setParentMenuId("");
		serverPwdIntfParams.setExtNotes("");
		serverPwdIntfParams.setExtNotes1("");
		return serverPwdIntfParams;
	}
	
	public static IntfParams getFetionIntfParams(){
		IntfParams serverPwdIntfParams = new IntfParams();
		serverPwdIntfParams.setIntfCode("1048");
		serverPwdIntfParams.setBillId("");
		serverPwdIntfParams.setParentMenuId("");
		serverPwdIntfParams.setExtNotes("");
		serverPwdIntfParams.setExtNotes1("");
		return serverPwdIntfParams;
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
		return setErrInfo("0", "添加成功", "添加成功");
	}
	
	public static ErrInfo getAddFailureErrInfo(){
		return setErrInfo("e6", "添加失败", "远程服务器出现错误,错误代码：e6.");
	}
	
	public static ErrInfo getNoSuchEmployeeErrInfo(){
		return setErrInfo("e7", "没有该员工,或信息填写错误", "查询无此员工,或信息填写错误");
	}
	
	public static ErrInfo getLoginSuccessErrInfo(){
		return setErrInfo("0", "登录成功", "登录成功");
	}
	
	public static ErrInfo getNoSuchEquipmentErrInfo(){
		return setErrInfo("e8", "非法设备", "检查无此设备,请联系管理员");
	}
	
	public static ErrInfo getSendSmsSuccessErrInfo(){
		return setErrInfo("0", "短信发送成功,并成功记录统计信息", "短信发送成功,并成功记录统计信息");
	}
	
	public static ErrInfo getSuccessErrInfo(){
		return setErrInfo("0", "", "");
	}
	
	
	public static ErrInfo getSendSmsFailureErrInfo(){
		return setErrInfo("e10", "短信发送失败", "短信发送失败");
	}
	
	public static ErrInfo getSendSmsSuccessButLogFailureErrInfo(){
		return setErrInfo("e11", "短信发送成功,但统计信息记录失败", "短信发送成功,但统计信息记录失败");
	}
	
	public static ErrInfo getIllegalErrInfo(){
		return setErrInfo("e12", "短信发送成功,但统计信息记录失败", "短信发送成功,但统计信息记录失败");
	}

	public static ErrInfo getTopSoftErrInfo(){
		return setErrInfo("e13", "获取前10软件排行出现错误", "系统出现错误,或连接服务器失败");
	}
	
	public static ErrInfo getItemInfoErrInfo(){
		return setErrInfo("e14", "获取菜单信息失败", "获取菜单信息失败");
	}
	
	
	public static ErrInfo setErrInfo(String errCode,String errMsg,String errHint){
		ErrInfo errInfo = new ErrInfo();
		errInfo.setErrCode(errCode);
		errInfo.setErrMsg(errMsg);
		errInfo.setErrHint(errHint);
		return errInfo;
	}
}
