package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;


public class JsonPhonetype {
	@Sync(value = "id")
	private String phonetypeId;
	
	@Sync(value = "phonebrandcategory.id")
	private String categoryId;
	
	@Sync(value = "phonebrandcategory.categoryName")
	private String categoryName;
	
	@Sync(value = "phonebrandcategory.phonebrand.id")
	private String phonebrandId;
	
	@Sync(value = "phonebrandcategory.phonebrand.phonebrandName")
	private String phonebrandName;
	

	@Sync(value = "phonearray.id")
	private String phonearrayId;
	
	@Sync(value = "phonearray.phonearrayName")
	private String phonearrayName;
	
	@Sync(value = "phoneplatform.id")
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

	public String getPhonearrayId() {
		return phonearrayId;
	}

	public void setPhonearrayId(String phonearrayId) {
		this.phonearrayId = phonearrayId;
	}

	public String getPhonearrayName() {
		return phonearrayName;
	}

	public void setPhonearrayName(String phonearrayName) {
		this.phonearrayName = phonearrayName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
