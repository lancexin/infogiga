package cn.infogiga.exp.server.xml;

import cindy.util.xml.XMLBean;
import cindy.util.xml.XMLElement;
import cindy.util.xml.XMLField;
import cindy.util.xml.XMLTypes;

@XMLElement(value="MENU_INFO",type=XMLTypes.XML_UNROOT)
public class MenuInfo implements XMLBean{
	
	@XMLField(value="MENU_ID",xmlType=XMLTypes.XML_ELEMENT)
	private String menuId = null;
	
	@XMLField(value="FUNC_ID",xmlType=XMLTypes.XML_ELEMENT)
	private String funcId = null;
	
	@XMLField(value="PARENT_MENU_ID",xmlType=XMLTypes.XML_ELEMENT)
	private String parentMenuId = null;
	
	@XMLField(value="MENU_NAME",xmlType=XMLTypes.XML_ELEMENT)
	private String menuName = null;
	
	@XMLField(value="MENU_DESC",xmlType=XMLTypes.XML_ELEMENT)
	private String menuDesc = null;
	
	@XMLField(value="DOMAIN_ID",xmlType=XMLTypes.XML_ELEMENT)
	private String domainId = null;
	
	@XMLField(value="MENU_URL",xmlType=XMLTypes.XML_ELEMENT)
	private String menuUrl = null;
	
	@XMLField(value="MENU_PIC_URL",xmlType=XMLTypes.XML_ELEMENT)
	private String menuPicUrl = null;
	
	@XMLField(value="MENU_IDX",xmlType=XMLTypes.XML_ELEMENT)
	private String menuIdx = null;
	
	@XMLField(value="NEED_LOGIN",xmlType=XMLTypes.XML_ELEMENT)
	private String needLogin = null;
	
	@XMLField(value="NEED_ECONTRACT",xmlType=XMLTypes.XML_ELEMENT)
	private String needEContract = null;
	
	@XMLField(value="NEED_SEC_PASSWD",xmlType=XMLTypes.XML_ELEMENT)
	private String needSecPasswd = null;
	
	@XMLField(value="STOP_SHOW",xmlType=XMLTypes.XML_ELEMENT)
	private String stopShow = null;
	
	@XMLField(value="NOTLOGIN_SHOW",xmlType=XMLTypes.XML_ELEMENT)
	private String notLoginShow = null;
	
	@XMLField(value="BUSI_KIND",xmlType=XMLTypes.XML_ELEMENT)
	private String busiKind = null;
	
	@XMLField(value="VALID_DATE",xmlType=XMLTypes.XML_ELEMENT)
	private String validDate = null;
	
	@XMLField(value="EXPIRE_DATE",xmlType=XMLTypes.XML_ELEMENT)
	private String expireDate = null;
	
	@XMLField(value="LASTDAY_CANDO",xmlType=XMLTypes.XML_ELEMENT)
	private String lastDayCando = null;
	
	@XMLField(value="HELP_URL",xmlType=XMLTypes.XML_ELEMENT)
	private String helpUrl = null;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuPicUrl() {
		return menuPicUrl;
	}

	public void setMenuPicUrl(String menuPicUrl) {
		this.menuPicUrl = menuPicUrl;
	}

	public String getMenuIdx() {
		return menuIdx;
	}

	public void setMenuIdx(String menuIdx) {
		this.menuIdx = menuIdx;
	}

	public String getNeedLogin() {
		return needLogin;
	}

	public void setNeedLogin(String needLogin) {
		this.needLogin = needLogin;
	}

	public String getNeedEContract() {
		return needEContract;
	}

	public void setNeedEContract(String needEContract) {
		this.needEContract = needEContract;
	}

	public String getNeedSecPasswd() {
		return needSecPasswd;
	}

	public void setNeedSecPasswd(String needSecPasswd) {
		this.needSecPasswd = needSecPasswd;
	}

	public String getStopShow() {
		return stopShow;
	}

	public void setStopShow(String stopShow) {
		this.stopShow = stopShow;
	}

	public String getNotLoginShow() {
		return notLoginShow;
	}

	public void setNotLoginShow(String notLoginShow) {
		this.notLoginShow = notLoginShow;
	}

	public String getBusiKind() {
		return busiKind;
	}

	public void setBusiKind(String busiKind) {
		this.busiKind = busiKind;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getLastDayCando() {
		return lastDayCando;
	}

	public void setLastDayCando(String lastDayCando) {
		this.lastDayCando = lastDayCando;
	}

	public String getHelpUrl() {
		return helpUrl;
	}

	public void setHelpUrl(String helpUrl) {
		this.helpUrl = helpUrl;
	}
}
