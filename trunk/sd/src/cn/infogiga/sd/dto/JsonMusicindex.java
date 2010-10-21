package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonMusicindex {
	
	@Sync(value = "indexId")
	String indexId;
	
	@Sync(value = "musicmenu.musicmenuId")
	String musicmenuId;
	
	@Sync(value = "musicmenu.musicmenuName")
	String musicmenuName;
	
	@Sync(value = "music.musicId")
	String musicId;
	
	@Sync(value = "music.musicName")
	String musicName;
	public String getIndexId() {
		return indexId;
	}
	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}
	public String getMusicmenuId() {
		return musicmenuId;
	}
	public void setMusicmenuId(String musicmenuId) {
		this.musicmenuId = musicmenuId;
	}
	public String getMusicmenuName() {
		return musicmenuName;
	}
	public void setMusicmenuName(String musicmenuName) {
		this.musicmenuName = musicmenuName;
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
}
