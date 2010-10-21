package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonVideo {
	@Sync(value = "videoId")
	String videoId;
	
	@Sync(value = "videoName")
	String videoName;
	
	@Sync(value = "description")
	String description;
	
	@Sync(value = "addTime")
	String addTime;
	
	@Sync(value = "status")
	String status;
	
	@Sync(value = "pic1")
	String pic1;
	
	@Sync(value = "pic2")
	String pic2;
	
	@Sync(value = "downloadCount")
	String downloadCount;
	
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
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
}
