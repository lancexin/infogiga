package cn.infogiga.exp.webservice.bean;

import cn.infogiga.util.xml.XMLElement;
import cn.infogiga.util.xml.XMLField;
import cn.infogiga.util.xml.XMLTypes;

@XMLElement(value="msg",type=XMLTypes.XML_ROOT)
public class SendBean {
	
	@XMLField(xmlType=XMLTypes.XML_BEAN,mClass=PCInfo.class)
	private PCInfo pcInfo = null;
	
	@XMLField(value="scene",xmlType=XMLTypes.XML_ELEMENT)
	private String scene = null;
	
	@XMLField(value="code",xmlType=XMLTypes.XML_ELEMENT)
	private String code = null;
	
	@XMLField(value="emp_no",xmlType=XMLTypes.XML_ELEMENT)
	private String emp_no = null;
	
	@XMLField(value="type",xmlType=XMLTypes.XML_ELEMENT)
	private String type = null;
	
	@XMLField(value="emp_pwd",xmlType=XMLTypes.XML_ELEMENT)
	private String emp_pwd = null;
	
	@XMLField(value="system_id",xmlType=XMLTypes.XML_ELEMENT)
	private String system_id = null;
	
	@XMLField(value="team_id",xmlType=XMLTypes.XML_ELEMENT)
	private String team_id = null;
	
	@XMLField(value="menu_id",xmlType=XMLTypes.XML_ELEMENT)
	private String menu_id = null;
	
	@XMLField(value="phone_number",xmlType=XMLTypes.XML_ELEMENT)
	private String phone_number = null;
	
	@XMLField(value="target_name",xmlType=XMLTypes.XML_ELEMENT)
	private String target_name = null;
	
	@XMLField(value="target_code",xmlType=XMLTypes.XML_ELEMENT)
	private String target_code = null;
	
	@XMLField(value="func_id",xmlType=XMLTypes.XML_ELEMENT)
	private String func_id = null;
	
	@XMLField(value="sys_oper",xmlType=XMLTypes.XML_ELEMENT)
	private String sys_oper = null;
	
	@XMLField(value="server_pwd",xmlType=XMLTypes.XML_ELEMENT)
	private String server_pwd = null;
	
	@XMLField(value="version",xmlType=XMLTypes.XML_ELEMENT)
	private String version = null;
	
	@XMLField(value="is_fetion",xmlType=XMLTypes.XML_ELEMENT)
	private String is_fetion = null;

	public PCInfo getPcInfo() {
		return pcInfo;
	}

	public void setPcInfo(PCInfo pcInfo) {
		this.pcInfo = pcInfo;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmp_pwd() {
		return emp_pwd;
	}

	public void setEmp_pwd(String emp_pwd) {
		this.emp_pwd = emp_pwd;
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

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getTarget_name() {
		return target_name;
	}

	public void setTarget_name(String target_name) {
		this.target_name = target_name;
	}

	public String getTarget_code() {
		return target_code;
	}

	public void setTarget_code(String target_code) {
		this.target_code = target_code;
	}

	public String getFunc_id() {
		return func_id;
	}

	public void setFunc_id(String func_id) {
		this.func_id = func_id;
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
}
