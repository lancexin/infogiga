package cn.infogiga.android.experience.bean;



import cn.infogiga.util.xml.XMLBean;
import cn.infogiga.util.xml.XMLElement;
import cn.infogiga.util.xml.XMLField;
import cn.infogiga.util.xml.XMLTypes;

@XMLElement(value="INTF_PARAMS",type=XMLTypes.XML_UNROOT)
public class IntfParams extends XMLBean{
	
	@XMLField(value="INTF_CODE",xmlType=XMLTypes.XML_ELEMENT)
	private String intfCode = null;
	
	@XMLField(value="MENU_ID",xmlType=XMLTypes.XML_ELEMENT)
	private String menuId = null;
	
	@XMLField(value="BILL_ID",xmlType=XMLTypes.XML_ELEMENT)
	private String billId = null;
	
	@XMLField(value="FUNC_ID",xmlType=XMLTypes.XML_ELEMENT)
	private String funcId = null;
	
	@XMLField(value="TARGET_CODE",xmlType=XMLTypes.XML_ELEMENT)
	private String targetCode = null;
	
	@XMLField(value="TARGET_NAME",xmlType=XMLTypes.XML_ELEMENT)
	private String targetName = null;
	
	@XMLField(value="ACTION",xmlType=XMLTypes.XML_ELEMENT)
	private String action = null;
	
	@XMLField(value="EFFECT_TYPE",xmlType=XMLTypes.XML_ELEMENT)
	private String effectType = null;
	
	@XMLField(value="VERIFY_CODE",xmlType=XMLTypes.XML_ELEMENT)
	private String verifyCode = null;
	
	@XMLField(value="AGENT_ID",xmlType=XMLTypes.XML_ELEMENT)
	private String agentId = null;
	
	@XMLField(value="AGENT_OP_ID",xmlType=XMLTypes.XML_ELEMENT)
	private String agentOpId = null;
	
	@XMLField(value="PARTNER_ID",xmlType=XMLTypes.XML_ELEMENT)
	private String partnerId = null;
	
	@XMLField(value="SYS_OPER",xmlType=XMLTypes.XML_ELEMENT)
	private String sysOper = null;
	
	@XMLField(value="LEAD_TYPE",xmlType=XMLTypes.XML_ELEMENT)
	private String leadType = null;
	
	@XMLField(value="USER_PWD",xmlType=XMLTypes.XML_ELEMENT)
	private String userPwd = null;
	
	@XMLField(value="PARENT_MENU_ID",xmlType=XMLTypes.XML_ELEMENT)
	private String parentMenuId = null;
	
	@XMLField(value="SP_ID",xmlType=XMLTypes.XML_ELEMENT)
	private String spId = null;
	
	@XMLField(value="SERVICE_ID",xmlType=XMLTypes.XML_ELEMENT)
	private String serviceId = null;
	
	@XMLField(value="SP_TYPE",xmlType=XMLTypes.XML_ELEMENT)
	private String spType = null;
	
	@XMLField(value="STATUS",xmlType=XMLTypes.XML_ELEMENT)
	private String status = null;
	
	@XMLField(value="SERVICE_NAME",xmlType=XMLTypes.XML_ELEMENT)
	private String serviceName = null;
	
	@XMLField(value="EXT_NOTES",xmlType=XMLTypes.XML_ELEMENT)
	private String extNotes = null;
	
	@XMLField(value="EXT_NOTES_1",xmlType=XMLTypes.XML_ELEMENT)
	private String extNotes1 = null;

	public String getIntfCode() {
		return intfCode;
	}

	public void setIntfCode(String intfCode) {
		this.intfCode = intfCode;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	public String getTargetCode() {
		return targetCode;
	}

	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getEffectType() {
		return effectType;
	}

	public void setEffectType(String effectType) {
		this.effectType = effectType;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgentOpId() {
		return agentOpId;
	}

	public void setAgentOpId(String agentOpId) {
		this.agentOpId = agentOpId;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getSysOper() {
		return sysOper;
	}

	public void setSysOper(String sysOper) {
		this.sysOper = sysOper;
	}

	public String getLeadType() {
		return leadType;
	}

	public void setLeadType(String leadType) {
		this.leadType = leadType;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getSpType() {
		return spType;
	}

	public void setSpType(String spType) {
		this.spType = spType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getExtNotes() {
		return extNotes;
	}

	public void setExtNotes(String extNotes) {
		this.extNotes = extNotes;
	}

	public String getExtNotes1() {
		return extNotes1;
	}

	public void setExtNotes1(String extNotes1) {
		this.extNotes1 = extNotes1;
	}
	
	
}
