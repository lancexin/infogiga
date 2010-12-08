package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonPhoneplatform {
	
	@Sync(value = "id")
	private String platformId;
	
	@Sync(value = "platformName")
	private String platformName;
	
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
}
