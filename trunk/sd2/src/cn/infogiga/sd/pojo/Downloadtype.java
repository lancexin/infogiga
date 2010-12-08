package cn.infogiga.sd.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Downloadtype entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Downloadtype implements java.io.Serializable {

	// Fields

	private Integer id;
	private String downloadtypeName;
	private Set softdownloadstats = new HashSet(0);

	// Constructors

	/** default constructor */
	public Downloadtype() {
	}

	/** full constructor */
	public Downloadtype(String downloadtypeName, Set softdownloadstats) {
		this.downloadtypeName = downloadtypeName;
		this.softdownloadstats = softdownloadstats;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDownloadtypeName() {
		return this.downloadtypeName;
	}

	public void setDownloadtypeName(String downloadtypeName) {
		this.downloadtypeName = downloadtypeName;
	}

	public Set getSoftdownloadstats() {
		return this.softdownloadstats;
	}

	public void setSoftdownloadstats(Set softdownloadstats) {
		this.softdownloadstats = softdownloadstats;
	}

}