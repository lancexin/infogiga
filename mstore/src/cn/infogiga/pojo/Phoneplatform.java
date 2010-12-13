package cn.infogiga.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Phoneplatform entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Phoneplatform implements java.io.Serializable {

	// Fields

	private Integer id;
	private String platformName;
	private Set phonetypes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Phoneplatform() {
	}

	/** full constructor */
	public Phoneplatform(String platformName, Set phonetypes) {
		this.platformName = platformName;
		this.phonetypes = phonetypes;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlatformName() {
		return this.platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public Set getPhonetypes() {
		return this.phonetypes;
	}

	public void setPhonetypes(Set phonetypes) {
		this.phonetypes = phonetypes;
	}

}