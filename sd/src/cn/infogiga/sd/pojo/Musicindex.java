package cn.infogiga.sd.pojo;

/**
 * Musicindex entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Musicindex implements java.io.Serializable {

	// Fields

	private Integer indexId;
	private Musicmenu musicmenu;
	private Music music;

	// Constructors

	/** default constructor */
	public Musicindex() {
	}

	/** full constructor */
	public Musicindex(Musicmenu musicmenu, Music music) {
		this.musicmenu = musicmenu;
		this.music = music;
	}

	// Property accessors

	public Integer getIndexId() {
		return this.indexId;
	}

	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}

	public Musicmenu getMusicmenu() {
		return this.musicmenu;
	}

	public void setMusicmenu(Musicmenu musicmenu) {
		this.musicmenu = musicmenu;
	}

	public Music getMusic() {
		return this.music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

}