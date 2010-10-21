package cn.infogiga.sd.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Soft entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Soft implements java.io.Serializable {

	// Fields

	private Integer softId;
	private Softmenu softmenu;
	private String softName;
	private String description;
	private Date addTime;
	private Integer status;
	private String pic1;
	private String pic2;
	private Integer downloadCount;
	private Set softdownloadstats = new HashSet(0);
	private Set softindexes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Soft() {
	}

	/** full constructor */
	public Soft(Softmenu softmenu, String softName, String description,
			Date addTime, Integer status, String pic1, String pic2,
			Integer downloadCount, Set softdownloadstats, Set softindexes) {
		this.softmenu = softmenu;
		this.softName = softName;
		this.description = description;
		this.addTime = addTime;
		this.status = status;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.downloadCount = downloadCount;
		this.softdownloadstats = softdownloadstats;
		this.softindexes = softindexes;
	}

	// Property accessors

	public Integer getSoftId() {
		return this.softId;
	}

	public void setSoftId(Integer softId) {
		this.softId = softId;
	}

	public Softmenu getSoftmenu() {
		return this.softmenu;
	}

	public void setSoftmenu(Softmenu softmenu) {
		this.softmenu = softmenu;
	}

	public String getSoftName() {
		return this.softName;
	}

	public void setSoftName(String softName) {
		this.softName = softName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getDownloadCount() {
		return this.downloadCount;
	}

	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

	public Set getSoftdownloadstats() {
		return this.softdownloadstats;
	}

	public void setSoftdownloadstats(Set softdownloadstats) {
		this.softdownloadstats = softdownloadstats;
	}

	public Set getSoftindexes() {
		return this.softindexes;
	}

	public void setSoftindexes(Set softindexes) {
		this.softindexes = softindexes;
	}

}