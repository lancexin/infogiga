package cn.infogiga.sd.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Phoneplatform entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Phoneplatform implements java.io.Serializable {

	// Fields

	private Integer platformId;
	private String platformName;
	private Set phonetypes = new HashSet(0);
	private Set softs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Phoneplatform() {
	}
	
	public Phoneplatform(Integer platformId) {
		this.platformId = platformId;
	}

	/** full constructor */
	public Phoneplatform(String platformName, Set phonetypes, Set softs) {
		this.platformName = platformName;
		this.phonetypes = phonetypes;
		this.softs = softs;
	}

	// Property accessors

	public Integer getPlatformId() {
		return this.platformId;
	}

	public void setPlatformId(Integer platformId) {
		this.platformId = platformId;
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

	public Set getSofts() {
		return this.softs;
	}

	public void setSofts(Set softs) {
		this.softs = softs;
	}

}