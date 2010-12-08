package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonPhonebrandcategory {
	@Sync(value = "id")
	private String categoryId;
	@Sync(value = "categoryName")
	private String categoryName;
	@Sync(value = "phonebrand.id")
	private String phonebrandId;
	@Sync(value = "phonebrand.phonebrandName")
	private String phonebrandName;
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
