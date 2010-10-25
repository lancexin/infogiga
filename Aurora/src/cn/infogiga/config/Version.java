package cn.infogiga.config;

public class Version {

	private int versionCode;
	private String versionName;
	public Version() {
		super();
	}
	public Version(int versionCode, String versionName) {
		super();
		this.versionCode = versionCode;
		this.versionName = versionName;
	}
	public int getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	
}
