package cn.infogiga.exp.pojo;

import java.util.Date;

/**
 * AbstractDownloadlist entity provides the base persistence definition of the
 * Downloadlist entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Downloadlist implements java.io.Serializable,cindy.web.pojo.POJO {

	// Fields

	private Integer downloadListId;
	private Softinfo softinfo;
	private Phonetype phonetype;
	private String downloadUrl;
	private Integer status;
	private Date addTime;
	private String backup1;
	private String backup2;
	private String backup3;

	// Constructors

	/** default constructor */
	public Downloadlist() {
	}

	/** full constructor */
	public Downloadlist(Softinfo softinfo, Phonetype phonetype,
			String downloadUrl, Integer status, Date addTime, String backup1,
			String backup2, String backup3) {
		this.softinfo = softinfo;
		this.phonetype = phonetype;
		this.downloadUrl = downloadUrl;
		this.status = status;
		this.addTime = addTime;
		this.backup1 = backup1;
		this.backup2 = backup2;
		this.backup3 = backup3;
	}

	// Property accessors



	public Softinfo getSoftinfo() {
		return this.softinfo;
	}

	public void setSoftinfo(Softinfo softinfo) {
		this.softinfo = softinfo;
	}

	public Phonetype getPhonetype() {
		return this.phonetype;
	}

	public void setPhonetype(Phonetype phonetype) {
		this.phonetype = phonetype;
	}

	public String getDownloadUrl() {
		return this.downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getDownloadListId() {
		return downloadListId;
	}

	public void setDownloadListId(Integer downloadListId) {
		this.downloadListId = downloadListId;
	}

}