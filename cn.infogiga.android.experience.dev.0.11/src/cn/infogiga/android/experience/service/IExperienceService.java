package cn.infogiga.android.experience.service;

import cn.infogiga.android.experience.bean.Intf;
import cn.infogiga.android.experience.bean.PCInfo;
import cn.infogiga.android.experience.bean.ReceiveBean;


public interface IExperienceService {
	/**
	 * 获得电脑信息
	 * @return
	 */
	public PCInfo getPcInfo();
	/**
	 * 用户登录
	 * @param empNo
	 * @return
	 */
	public Intf login(String empNo,String pwd);
	
	/**
	 * 用户订购
	 * @param meauId
	 * @param funcId
	 * @param phoneNumber
	 * @param targetCode
	 * @param targetName
	 * @param sysOper
	 * @return
	 */
	public Intf order(String meauId,String funcId,String phoneNumber,String targetCode,String targetName,String serverPwd,String sysOper,String action);
	/**
	 * 获得菜单信息
	 * @param menuId
	 * @param phoneNumber
	 * @return
	 */
	public String getItemInfo(String menuId,String phoneNumber,String isFetion);
	/**
	 * 检查是否有更新
	 * @return
	 */
	public Intf checkUpdate();
	/**
	 * 是否已经登录
	 * @return
	 */
	public boolean hasLogin();
	/**
	 * 获得雇员编号
	 * @return
	 */
	public String getEmpNo();
	
	public void setEmpNo(String empNo,String empPassword);
	
	public void setLoginState(boolean b);
	
	public Intf sendSMS(String phoneNUmber,String context,String menuId);
	/**
	 * 发送信息
	 * @param xml
	 * @return
	 */
	public String send(String xml);
	
	public String send(ReceiveBean sb);
	
	public Intf sendForErr(ReceiveBean sb);
	
	public Intf sendForErr(String xml);
	
	public Intf log(String menuId,String type,String phoneNumber,String scene,String comboName,String pkg);
	
	public String getMenuId(String pkg);
	
	public String getFuncId(String pkg);
	
	public void sendToPC(String code,String id);
}
