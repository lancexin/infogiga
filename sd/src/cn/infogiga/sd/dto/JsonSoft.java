package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonSoft {
	@Sync(value = "softId")
	private String softId;
	
	@Sync(value = "softmenu.softmenuId")
	private String softmenuId;
	
	@Sync(value = "softmenu.softmenuName")
	private String softmenuName;
	
	@Sync(value = "softName")
	private String softName;
	
	@Sync(value = "description")
	private String description;
	
	@Sync(value = "addTime")
	private String addTime;
	
	@Sync(value = "status")
	private String status;
	
	@Sync(value = "pic1")
	private String pic1;
	
	@Sync(value = "pic2")
	private String pic2;
	
	@Sync(value = "downloadCount")
	private String downloadCount;
	
	public String getSoftId() {
		return softId;
	}
	public void setSoftId(String softId) {
		this.softId = softId;
	}

	public String getSoftName() {
		return softName;
	}
	public void setSoftName(String softName) {
		this.softName = softName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPic1() {
		return pic1;
	}
	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}
	public String getPic2() {
		return pic2;
	}
	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}
	public String getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(String downloadCount) {
		this.downloadCount = downloadCount;
	}
	public String getSoftmenuId() {
		return softmenuId;
	}
	public void setSoftmenuId(String softmenuId) {
		this.softmenuId = softmenuId;
	}
	public String getSoftmenuName() {
		return softmenuName;
	}
	public void setSoftmenuName(String softmenuName) {
		this.softmenuName = softmenuName;
	}
}
