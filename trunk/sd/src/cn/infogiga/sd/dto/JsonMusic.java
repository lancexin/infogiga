package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonMusic {
	@Sync(value = "musicId")
	String musicId;
	
	@Sync(value = "musicName")
	String musicName;
	
	@Sync(value = "musicman.musicmanId")
	String musicmanId;
	
	@Sync(value = "musicman.musicmanName")
	String musicmanName;
	
	@Sync(value = "description")
	String description;
	
	@Sync(value = "addTime")
	String addTime;
	
	@Sync(value = "status")
	String status;
	
	@Sync(value = "downloadCount")
	String downloadCount;
	
	@Sync(value = "download.downloadId")
	String downloadId;
	
	@Sync(value = "download.downloadCode")
	String downloadCode;
	
	public String getDownloadId() {
		return downloadId;
	}
	public void setDownloadId(String downloadId) {
		this.downloadId = downloadId;
	}
	public String getDownloadCode() {
		return downloadCode;
	}
	public void setDownloadCode(String downloadCode) {
		this.downloadCode = downloadCode;
	}
	public String getMusicId() {
		return musicId;
	}
	public void setMusicId(String musicId) {
		this.musicId = musicId;
	}
	public String getMusicName() {
		return musicName;
	}
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	public String getMusicmanId() {
		return musicmanId;
	}
	public void setMusicmanId(String musicmanId) {
		this.musicmanId = musicmanId;
	}
	public String getMusicmanName() {
		return musicmanName;
	}
	public void setMusicmanName(String musicmanName) {
		this.musicmanName = musicmanName;
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
	public String getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(String downloadCount) {
		this.downloadCount = downloadCount;
	}
}
