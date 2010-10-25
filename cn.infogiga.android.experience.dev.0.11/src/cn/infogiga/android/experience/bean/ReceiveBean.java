package cn.infogiga.android.experience.bean;



import cn.infogiga.util.xml.XMLBean;
import cn.infogiga.util.xml.XMLElement;
import cn.infogiga.util.xml.XMLField;
import cn.infogiga.util.xml.XMLTypes;

@XMLElement(value="msg",type=XMLTypes.XML_ROOT)
public class ReceiveBean extends XMLBean{
	
	@XMLField(value="type",xmlType=XMLTypes.XML_ELEMENT)
	private String type = null;
	
	@XMLField(value="scene",xmlType=XMLTypes.XML_ELEMENT)
	private String scene = null;
	
	@XMLField(value="mac",xmlType=XMLTypes.XML_ELEMENT)
	private String mac = null;

	@XMLField(value="code",xmlType=XMLTypes.XML_ELEMENT)
	private String code = null;

	@XMLField(value="emp_no",xmlType=XMLTypes.XML_ELEMENT)
	private String emp_no = null;

	@XMLField(value="emp_pwd",xmlType=XMLTypes.XML_ELEMENT)
	private String emp_pwd = null;

	@XMLField(value="ip",xmlType=XMLTypes.XML_ELEMENT)
	private String ip = null;

	@XMLField(value="harddisk",xmlType=XMLTypes.XML_ELEMENT)
	private String harddisk = null;
	
	@XMLField(value="cmp_name",xmlType=XMLTypes.XML_ELEMENT)
	private String cmp_name = null;

	@XMLField(value="system_id",xmlType=XMLTypes.XML_ELEMENT)
	private String system_id = null;

	@XMLField(value="team_id",xmlType=XMLTypes.XML_ELEMENT)
	private String team_id = null;

	@XMLField(value="menu_id",xmlType=XMLTypes.XML_ELEMENT)
	private String menu_id = null;

	@XMLField(value="func_id",xmlType=XMLTypes.XML_ELEMENT)
	private String func_id = null;

	@XMLField(value="phone_number",xmlType=XMLTypes.XML_ELEMENT)
	private String phone_number = null;

	@XMLField(value="target_code",xmlType=XMLTypes.XML_ELEMENT)
	private String target_code = null;

	@XMLField(value="target_name",xmlType=XMLTypes.XML_ELEMENT)
	private String target_name = null;

	@XMLField(value="sys_oper",xmlType=XMLTypes.XML_ELEMENT)
	private String sys_oper = null;

	@XMLField(value="server_pwd",xmlType=XMLTypes.XML_ELEMENT)
	private String server_pwd = null;

	@XMLField(value="version",xmlType=XMLTypes.XML_ELEMENT)
	private String version = null;

	@XMLField(value="is_fetion",xmlType=XMLTypes.XML_ELEMENT)
	private String is_fetion = null;
	
	@XMLField(value="combo_name",xmlType=XMLTypes.XML_ELEMENT)
	private String combo_name = null;
	
	@XMLField(value="soft_name",xmlType=XMLTypes.XML_ELEMENT)
	private String soft_name = null;
	
	@XMLField(value="soft_download_url",xmlType=XMLTypes.XML_ELEMENT)
	private String soft_download_url = null;
	
	@XMLField(value="sms_context",xmlType=XMLTypes.XML_ELEMENT)
	private String sms_context = null;

	@XMLField(value="action",xmlType=XMLTypes.XML_ELEMENT)
	private String action = null;
	
	@XMLField(value="pkg",xmlType=XMLTypes.XML_ELEMENT)
	private String pkg = null;
	
	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public String getEmp_pwd() {
		return emp_pwd;
	}

	public void setEmp_pwd(String emp_pwd) {
		this.emp_pwd = emp_pwd;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHarddisk() {
		return harddisk;
	}

	public void setHarddisk(String harddisk) {
		this.harddisk = harddisk;
	}

	public String getSystem_id() {
		return system_id;
	}

	public void setSystem_id(String system_id) {
		this.system_id = system_id;
	}

	public String getTeam_id() {
		return team_id;
	}

	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

	public String getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}

	public String getFunc_id() {
		return func_id;
	}

	public void setFunc_id(String func_id) {
		this.func_id = func_id;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getTarget_code() {
		return target_code;
	}

	public void setTarget_code(String target_code) {
		this.target_code = target_code;
	}

	public String getTarget_name() {
		return target_name;
	}

	public void setTarget_name(String target_name) {
		this.target_name = target_name;
	}

	public String getSys_oper() {
		return sys_oper;
	}

	public void setSys_oper(String sys_oper) {
		this.sys_oper = sys_oper;
	}

	public String getServer_pwd() {
		return server_pwd;
	}

	public void setServer_pwd(String server_pwd) {
		this.server_pwd = server_pwd;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getIs_fetion() {
		return is_fetion;
	}

	public void setIs_fetion(String is_fetion) {
		this.is_fetion = is_fetion;
	}

	public String getCmp_name() {
		return cmp_name;
	}

	public void setCmp_name(String cmp_name) {
		this.cmp_name = cmp_name;
	}

	public String getCombo_name() {
		return combo_name;
	}

	public void setCombo_name(String combo_name) {
		this.combo_name = combo_name;
	}

	public String getSoft_name() {
		return soft_name;
	}

	public void setSoft_name(String soft_name) {
		this.soft_name = soft_name;
	}

	public String getSoft_download_url() {
		return soft_download_url;
	}

	public void setSoft_download_url(String soft_download_url) {
		this.soft_download_url = soft_download_url;
	}

	public String getSms_context() {
		return sms_context;
	}

	public void setSms_context(String sms_context) {
		this.sms_context = sms_context;
	}
}
