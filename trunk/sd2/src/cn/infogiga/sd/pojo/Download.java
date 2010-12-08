package cn.infogiga.sd.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Download entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Download implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer downloadCount;
	private Set softs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Download() {
	}

	/** full constructor */
	public Download(Integer downloadCount, Set softs) {
		this.downloadCount = downloadCount;
		this.softs = softs;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDownloadCount() {
		return this.downloadCount;
	}

	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

	public Set getSofts() {
		return this.softs;
	}

	public void setSofts(Set softs) {
		this.softs = softs;
	}

}