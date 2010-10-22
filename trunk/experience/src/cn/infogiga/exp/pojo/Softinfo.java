package cn.infogiga.exp.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSoftinfo entity provides the base persistence definition of the
 * Softinfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Softinfo implements java.io.Serializable,cindy.web.pojo.POJO {

	// Fields

	private Integer softId;
	private String softCode;
	private String softName;
	private Date addTime;
	private Integer status;
	private String backup1;
	private String backup2;
	private String backup3;
	private Integer flag;
	private Set downloadlists = new HashSet(0);

	// Constructors

	/** default constructor */
	public Softinfo() {
	}

	/** full constructor */
	public Softinfo(String softCode, String softName, Date addTime,
			Integer status, String backup1, String backup2, String backup3,
			Integer flag, Set downloadlists) {
		this.softCode = softCode;
		this.softName = softName;
		this.addTime = addTime;
		this.status = status;
		this.backup1 = backup1;
		this.backup2 = backup2;
		this.backup3 = backup3;
		this.flag = flag;
		this.downloadlists = downloadlists;
	}

	// Property accessors

	public Integer getSoftId() {
		return this.softId;
	}

	public void setSoftId(Integer softId) {
		this.softId = softId;
	}

	public String getSoftCode() {
		return this.softCode;
	}

	public void setSoftCode(String softCode) {
		this.softCode = softCode;
	}

	public String getSoftName() {
		return this.softName;
	}

	public void setSoftName(String softName) {
		this.softName = softName;
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

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Set getDownloadlists() {
		return this.downloadlists;
	}

	public void setDownloadlists(Set downloadlists) {
		this.downloadlists = downloadlists;
	}

}