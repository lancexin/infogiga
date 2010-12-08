package cn.infogiga.sd.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Softmenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Softmenu implements java.io.Serializable {

	// Fields

	private Integer id;
	private String menuName;
	private String pic1;
	private String pic2;
	private String link;
	private Set softindexes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Softmenu() {
	}

	/** full constructor */
	public Softmenu(String menuName, String pic1, String pic2, String link,
			Set softindexes) {
		this.menuName = menuName;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.link = link;
		this.softindexes = softindexes;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getPic1() {
		return this.pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return this.pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Set getSoftindexes() {
		return this.softindexes;
	}

	public void setSoftindexes(Set softindexes) {
		this.softindexes = softindexes;
	}

}