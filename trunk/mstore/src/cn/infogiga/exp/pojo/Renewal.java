package cn.infogiga.exp.pojo;

import java.util.Date;

/**
 * AbstractRenewal entity provides the base persistence definition of the
 * Renewal entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Renewal
		implements java.io.Serializable {

	// Fields

	private Integer renewalId;
	private Sysinfo sysinfo;
	private String version;
	private String url;
	private Date uploadTime;
	private Integer status;
	private String backup1;
	private String backup2;
	private String backup3;

	// Constructors

	/** default constructor */
	public Renewal() {
	}

	/** full constructor */
	public Renewal(Sysinfo sysinfo, String version, String url,
			Date uploadTime, Integer status, String backup1, String backup2,
			String backup3) {
		this.sysinfo = sysinfo;
		this.version = version;
		this.url = url;
		this.uploadTime = uploadTime;
		this.status = status;
		this.backup1 = backup1;
		this.backup2 = backup2;
		this.backup3 = backup3;
	}

	// Property accessors

	public Integer getRenewalId() {
		return this.renewalId;
	}

	public void setRenewalId(Integer renewalId) {
		this.renewalId = renewalId;
	}

	public Sysinfo getSysinfo() {
		return this.sysinfo;
	}

	public void setSysinfo(Sysinfo sysinfo) {
		this.sysinfo = sysinfo;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

}