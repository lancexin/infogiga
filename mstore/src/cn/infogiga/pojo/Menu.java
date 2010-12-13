package cn.infogiga.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Menu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Menu implements java.io.Serializable {

	// Fields

	private Integer id;
	private String menuid;
	private String funcId;
	private String parentMenuId;
	private String menuDesc;
	private String domainId;
	private String menuUrl;
	private String menuPicUrl;
	private String menuIdx;
	private String needLogin;
	private String needEcontrct;
	private String needSecondPasswd;
	private String stopShow;
	private String notLoginShow;
	private String busiKind;
	private String isUsed;
	private String validDate;
	private String expireDate;
	private String lastDayCando;
	private String helpUrl;
	private String channelLevel;
	private String menuName;
	private String pkg;
	private Set ophonestats = new HashSet(0);

	// Constructors

	/** default constructor */
	public Menu() {
	}

	/** full constructor */
	public Menu(String menuid, String funcId, String parentMenuId,
			String menuDesc, String domainId, String menuUrl,
			String menuPicUrl, String menuIdx, String needLogin,
			String needEcontrct, String needSecondPasswd, String stopShow,
			String notLoginShow, String busiKind, String isUsed,
			String validDate, String expireDate, String lastDayCando,
			String helpUrl, String channelLevel, String menuName, String pkg,
			Set ophonestats) {
		this.menuid = menuid;
		this.funcId = funcId;
		this.parentMenuId = parentMenuId;
		this.menuDesc = menuDesc;
		this.domainId = domainId;
		this.menuUrl = menuUrl;
		this.menuPicUrl = menuPicUrl;
		this.menuIdx = menuIdx;
		this.needLogin = needLogin;
		this.needEcontrct = needEcontrct;
		this.needSecondPasswd = needSecondPasswd;
		this.stopShow = stopShow;
		this.notLoginShow = notLoginShow;
		this.busiKind = busiKind;
		this.isUsed = isUsed;
		this.validDate = validDate;
		this.expireDate = expireDate;
		this.lastDayCando = lastDayCando;
		this.helpUrl = helpUrl;
		this.channelLevel = channelLevel;
		this.menuName = menuName;
		this.pkg = pkg;
		this.ophonestats = ophonestats;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuid() {
		return this.menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getFuncId() {
		return this.funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	public String getParentMenuId() {
		return this.parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getMenuDesc() {
		return this.menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public String getDomainId() {
		return this.domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuPicUrl() {
		return this.menuPicUrl;
	}

	public void setMenuPicUrl(String menuPicUrl) {
		this.menuPicUrl = menuPicUrl;
	}

	public String getMenuIdx() {
		return this.menuIdx;
	}

	public void setMenuIdx(String menuIdx) {
		this.menuIdx = menuIdx;
	}

	public String getNeedLogin() {
		return this.needLogin;
	}

	public void setNeedLogin(String needLogin) {
		this.needLogin = needLogin;
	}

	public String getNeedEcontrct() {
		return this.needEcontrct;
	}

	public void setNeedEcontrct(String needEcontrct) {
		this.needEcontrct = needEcontrct;
	}

	public String getNeedSecondPasswd() {
		return this.needSecondPasswd;
	}

	public void setNeedSecondPasswd(String needSecondPasswd) {
		this.needSecondPasswd = needSecondPasswd;
	}

	public String getStopShow() {
		return this.stopShow;
	}

	public void setStopShow(String stopShow) {
		this.stopShow = stopShow;
	}

	public String getNotLoginShow() {
		return this.notLoginShow;
	}

	public void setNotLoginShow(String notLoginShow) {
		this.notLoginShow = notLoginShow;
	}

	public String getBusiKind() {
		return this.busiKind;
	}

	public void setBusiKind(String busiKind) {
		this.busiKind = busiKind;
	}

	public String getIsUsed() {
		return this.isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public String getValidDate() {
		return this.validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getLastDayCando() {
		return this.lastDayCando;
	}

	public void setLastDayCando(String lastDayCando) {
		this.lastDayCando = lastDayCando;
	}

	public String getHelpUrl() {
		return this.helpUrl;
	}

	public void setHelpUrl(String helpUrl) {
		this.helpUrl = helpUrl;
	}

	public String getChannelLevel() {
		return this.channelLevel;
	}

	public void setChannelLevel(String channelLevel) {
		this.channelLevel = channelLevel;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getPkg() {
		return this.pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public Set getOphonestats() {
		return this.ophonestats;
	}

	public void setOphonestats(Set ophonestats) {
		this.ophonestats = ophonestats;
	}

}