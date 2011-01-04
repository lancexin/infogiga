package cn.infogiga.exp.pojo;

import java.util.Date;

/**
 * AbstractDownloadstat entity provides the base persistence definition of the
 * Downloadstat entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Downloadstat implements java.io.Serializable {

	// Fields

	private Integer downloadstatId;
	private Equipment equipment;
	private Softinfo softinfo;
	private Phonetype phonetype;
	private Employee employee;
	private Downloadtype downloadtype;
	private String phoneNumber;
	private Date addTime;
	private Integer status;
	private String backup1;
	private String backup2;
	private String backup3;
	private String downloadUrl;
	
	private String softName;
	private Integer downloadCount;

	// Constructors

	/** default constructor */
	public Downloadstat() {
	}

	/** full constructor */
	public Downloadstat(Equipment equipment, Softinfo softinfo,
			Phonetype phonetype, Employee employee, Downloadtype downloadtype,
			String phoneNumber, Date addTime, Integer status, String backup1,
			String backup2, String backup3, String downloadUrl) {
		this.equipment = equipment;
		this.softinfo = softinfo;
		this.phonetype = phonetype;
		this.employee = employee;
		this.downloadtype = downloadtype;
		this.phoneNumber = phoneNumber;
		this.addTime = addTime;
		this.status = status;
		this.backup1 = backup1;
		this.backup2 = backup2;
		this.backup3 = backup3;
		this.downloadUrl = downloadUrl;
	}

	// Property accessors

	public Integer getDownloadstatId() {
		return this.downloadstatId;
	}

	public void setDownloadstatId(Integer downloadstatId) {
		this.downloadstatId = downloadstatId;
	}

	public Equipment getEquipment() {
		return this.equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

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

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Downloadtype getDownloadtype() {
		return this.downloadtype;
	}

	public void setDownloadtype(Downloadtype downloadtype) {
		this.downloadtype = downloadtype;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getDownloadUrl() {
		return this.downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getSoftName() {
		return softName;
	}

	public void setSoftName(String softName) {
		this.softName = softName;
	}

	public Integer getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

}