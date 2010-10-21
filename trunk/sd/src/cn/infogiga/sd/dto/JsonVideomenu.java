package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonVideomenu {
	
	@Sync(value = "videomenuId")
	String videomenuId;
	
	@Sync(value = "videomenuName")
	String videomenuName;
	
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
