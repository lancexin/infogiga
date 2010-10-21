package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonMusicman {
	@Sync(value = "musicmanId")
	String musicmanId;
	
	@Sync(value = "musicmanName")
	String musicmanName;
	

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
}
