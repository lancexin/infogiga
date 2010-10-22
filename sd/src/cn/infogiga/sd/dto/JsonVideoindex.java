package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonVideoindex {
	@Sync(value = "indexId")
	String indexId;
	
	@Sync(value = "video.videoId")
	String videoId;
	
	@Sync(value = "video.videoName")
	String videoName;
	
	@Sync(value = "videomenu.videomenuId")
	String videomenuId;
	
	@Sync(value = "videomenu.videomenuName")
	String videomenuName;
	public String getIndexId() {
		return indexId;
	}
	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}
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
	public String getVideomenuId() {
		return videomenuId;
	}
	public void setVideomenuId(String videomenuId) {
		this.videomenuId = videomenuId;
	}
	public String getVideomenuName() {
		return videomenuName;
	}
	public void setVideomenuName(String videomenuName) {
		this.videomenuName = videomenuName;
	}
}
