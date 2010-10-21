package cn.infogiga.sd.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Musicmenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Musicmenu implements java.io.Serializable {

	// Fields

	private Integer musicmenuId;
	private Musicmenu musicmenu;
	private String musicmenuName;
	private Integer isLeaf;
	private Set musicindexes = new HashSet(0);
	private Set musicmenus = new HashSet(0);

	// Constructors

	/** default constructor */
	public Musicmenu() {
	}

	/** full constructor */
	public Musicmenu(Musicmenu musicmenu, String musicmenuName, Integer isLeaf,
			Set musicindexes, Set musicmenus) {
		this.musicmenu = musicmenu;
		this.musicmenuName = musicmenuName;
		this.isLeaf = isLeaf;
		this.musicindexes = musicindexes;
		this.musicmenus = musicmenus;
	}

	// Property accessors

	public Integer getMusicmenuId() {
		return this.musicmenuId;
	}

	public void setMusicmenuId(Integer musicmenuId) {
		this.musicmenuId = musicmenuId;
	}

	public Musicmenu getMusicmenu() {
		return this.musicmenu;
	}

	public void setMusicmenu(Musicmenu musicmenu) {
		this.musicmenu = musicmenu;
	}

	public String getMusicmenuName() {
		return this.musicmenuName;
	}

	public void setMusicmenuName(String musicmenuName) {
		this.musicmenuName = musicmenuName;
	}

	public Integer getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Set getMusicindexes() {
		return this.musicindexes;
	}

	public void setMusicindexes(Set musicindexes) {
		this.musicindexes = musicindexes;
	}

	public Set getMusicmenus() {
		return this.musicmenus;
	}

	public void setMusicmenus(Set musicmenus) {
		this.musicmenus = musicmenus;
	}

}