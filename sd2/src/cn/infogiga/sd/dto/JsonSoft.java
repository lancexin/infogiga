package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonSoft {
	@Sync(value = "id")
	private String softId;
		
	@Sync(value = "softName")
	private String softName;
	
	@Sync(value = "description")
	private String description;
	
	@Sync(value = "addTime")
	private String addTime;
	
	@Sync(value = "status")
	private String status;
	
	@Sync(value = "icon")
	private String icon;
	
	@Sync(value = "pic1")
	private String pic1;

	@Sync(value = "pic2")
	private String pic2;
	
	@Sync(value = "pic3")
	private String pic3;

	@Sync(value = "pic4")
	private String pic4;
	
	@Sync(value = "pic5")
	private String pic5;
	
	@Sync(value = "download.downloadCount")
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getPic3() {
		return pic3;
	}
	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}
	public String getPic4() {
		return pic4;
	}
	public void setPic4(String pic4) {
		this.pic4 = pic4;
	}
	public String getPic5() {
		return pic5;
	}
	public void setPic5(String pic5) {
		this.pic5 = pic5;
	}
	
}
