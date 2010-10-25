package cn.infogiga.cmpp2.util;

public class Config extends PropertiesLoader{
	
	public String getSms_host() {
		return getStringParameter("sms_host");
	}
	public Integer getSms_post() {
		return getIntParameter("sms_post");
	}
	public String getSp_id() {
		return getStringParameter("sp_id");
	}
	public String getSp_pwd() {
		return getStringParameter("sp_pwd");
	}
	public String getService_id() {
		return getStringParameter("service_id");
	}
	public String getServer_code() {
		return getStringParameter("server_code");
	}
}
