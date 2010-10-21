package cn.infogiga.sd.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Musicman entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Musicman implements java.io.Serializable {

	// Fields

	private Integer musicmanId;
	private String musicmanName;
	private Set musics = new HashSet(0);

	// Constructors

	/** default constructor */
	public Musicman() {
	}

	/** full constructor */
	public Musicman(String musicmanName, Set musics) {
		this.musicmanName = musicmanName;
		this.musics = musics;
	}

	// Property accessors

	public Integer getMusicmanId() {
		return this.musicmanId;
	}

	public void setMusicmanId(Integer musicmanId) {
		this.musicmanId = musicmanId;
	}

	public String getMusicmanName() {
		return this.musicmanName;
	}

	public void setMusicmanName(String musicmanName) {
		this.musicmanName = musicmanName;
	}

	public Set getMusics() {
		return this.musics;
	}

	public void setMusics(Set musics) {
		this.musics = musics;
	}

}