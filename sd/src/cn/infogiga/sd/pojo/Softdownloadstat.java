package cn.infogiga.sd.pojo;

import java.util.Date;

/**
 * Softdownloadstat entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Softdownloadstat implements java.io.Serializable {

	// Fields

	private Integer statId;
	private Equipment equipment;
	private Soft soft;
	private Phonetype phonetype;
	private Employee employee;
	private Downloadtype downloadtype;
	private String phoneNumber;
	private Integer status;
	private Date addTime;

	// Constructors

	/** default constructor */
	public Softdownloadstat() {
	}

	/** full constructor */
	public Softdownloadstat(Equipment equipment, Soft soft,
			Phonetype phonetype, Employee employee, Downloadtype downloadtype,
			String phoneNumber, Integer status, Date addTime) {
		this.equipment = equipment;
		this.soft = soft;
		this.phonetype = phonetype;
		this.employee = employee;
		this.downloadtype = downloadtype;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.addTime = addTime;
	}

	// Property accessors

	public Integer getStatId() {
		return this.statId;
	}

	public void setStatId(Integer statId) {
		this.statId = statId;
	}

	public Equipment getEquipment() {
		return this.equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Soft getSoft() {
		return this.soft;
	}

	public void setSoft(Soft soft) {
		this.soft = soft;
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

}