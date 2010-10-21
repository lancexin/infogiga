package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;


public class JsonPhonetype {
	@Sync(value = "phonetypeId")
	private String phonetypeId;
	
	@Sync(value = "phonebrand.phonebrandId")
	private String phonebrandId;
	
	@Sync(value = "phonebrand.phonebrandName")
	private String phonebrandName;
	
	@Sync(value = "phoneplatform.platformId")
	private String platformId;
	
	@Sync(value = "phoneplatform.platformName")
	private String platformName;
	
	@Sync(value = "phonetypeName")
	private String phonetypeName;
	
	@Sync(value = "pic")
	private String pic;
	
	@Sync(value = "status")
	private String status;

	public String getPhonetypeId() {
		return phonetypeId;
	}

	public void setPhonetypeId(String phonetypeId) {
		this.phonetypeId = phonetypeId;
	}

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

	public String getPhonetypeName() {
		return phonetypeName;
	}

	public void setPhonetypeName(String phonetypeName) {
		this.phonetypeName = phonetypeName;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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
