package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonDownloadtype {
	@Sync(value = "id")
	String downloadtypeId;
	@Sync(value = "downloadtypeName")
	String downloadtypeName;
	public String getDownloadtypeId() {
		return downloadtypeId;
	}
	public void setDownloadtypeId(String downloadtypeId) {
		this.downloadtypeId = downloadtypeId;
	}
	public String getDownloadtypeName() {
		return downloadtypeName;
	}
	public void setDownloadtypeName(String downloadtypeName) {
		this.downloadtypeName = downloadtypeName;
	}
}
