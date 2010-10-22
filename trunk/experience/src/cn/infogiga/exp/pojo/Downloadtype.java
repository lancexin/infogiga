package cn.infogiga.exp.pojo;

import java.util.Date;

/**
 * AbstractDownloadtype entity provides the base persistence definition of the
 * Downloadtype entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Downloadtype implements java.io.Serializable,cindy.web.pojo.POJO {

	// Fields

	private Integer downloadtypeId;
	private String typeName;
	private Date addTime;
	private String backup1;
	private String backup2;
	private String backup3;
	private Integer status;

	// Constructors

	/** default constructor */
	public Downloadtype() {
	}

	/** full constructor */
	public Downloadtype(String typeName, Date addTime, String backup1,
			String backup2, String backup3, Integer status) {
		this.typeName = typeName;
		this.addTime = addTime;
		this.backup1 = backup1;
		this.backup2 = backup2;
		this.backup3 = backup3;
		this.status = status;
	}

	// Property accessors

	public Integer getDownloadtypeId() {
		return this.downloadtypeId;
	}

	public void setDownloadtypeId(Integer downloadtypeId) {
		this.downloadtypeId = downloadtypeId;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getBackup1() {
		return this.backup1;
	}

	public void setBackup1(String backup1) {
		this.backup1 = backup1;
	}

	public String getBackup2() {
		return this.backup2;
	}

	public void setBackup2(String backup2) {
		this.backup2 = backup2;
	}

	public String getBackup3() {
		return this.backup3;
	}

	public void setBackup3(String backup3) {
		this.backup3 = backup3;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}