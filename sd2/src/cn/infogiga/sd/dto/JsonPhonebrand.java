package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonPhonebrand {
	@Sync(value = "id")
	private String phonebrandId;
	
	@Sync(value = "phonebrandName")
	private String phonebrandName;

	@Sync(value = "url")
	private String url;
	
	public String getPhonebrandId() {
		return phonebrandId;
	}
	public void setPhonebrandId(String phonebrandId) {
		this.phonebrandId = phonebrandId;
	}
	public String getPhonebrandName() {
		return phonebrandName;
	}
	public void setPhonebrandName(String phonebrandName) {
		this.phonebrandName = phonebrandName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
