package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonMusicmenu {
	@Sync(value = "musicmenuId")
	String musicmenuId;
	
	@Sync(value = "musicmenu.musicmenuId")
	String fatherMusicmenuId;
	
	@Sync(value = "musicmenuName")
	String musicmenuName;
	
	@Sync(value = "isLeaf")
	String isLeaf;
	
	public String getMusicmenuId() {
		return musicmenuId;
	}
	public void setMusicmenuId(String musicmenuId) {
		this.musicmenuId = musicmenuId;
	}
	public String getFatherMusicmenuId() {
		return fatherMusicmenuId;
	}
	public void setFatherMusicmenuId(String fatherMusicmenuId) {
		this.fatherMusicmenuId = fatherMusicmenuId;
	}

	public String getMusicmenuName() {
		return musicmenuName;
	}
	public void setMusicmenuName(String musicmenuName) {
		this.musicmenuName = musicmenuName;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
}
