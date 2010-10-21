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

	private Integer downloadtypeId;
	private String downloadtypeName;
	private Set softdownloadstats = new HashSet(0);
	private Set videodownloadstats = new HashSet(0);

	// Constructors

	/** default constructor */
	public Downloadtype() {
	}

	/** full constructor */
	public Downloadtype(String downloadtypeName, Set softdownloadstats,
			Set videodownloadstats) {
		this.downloadtypeName = downloadtypeName;
		this.softdownloadstats = softdownloadstats;
		this.videodownloadstats = videodownloadstats;
	}

	// Property accessors

	public Integer getDownloadtypeId() {
		return this.downloadtypeId;
	}

	public void setDownloadtypeId(Integer downloadtypeId) {
		this.downloadtypeId = downloadtypeId;
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

	public Set getVideodownloadstats() {
		return this.videodownloadstats;
	}

	public void setVideodownloadstats(Set videodownloadstats) {
		this.videodownloadstats = videodownloadstats;
	}

}