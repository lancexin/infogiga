package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonPhonearray {
	
	@Sync(value = "id")
	private String phonearrayId;
	
	@Sync(value = "phonearrayName")
	private String phonearrayName;

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
}
