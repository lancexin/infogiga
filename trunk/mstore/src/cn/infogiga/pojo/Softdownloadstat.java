package cn.infogiga.pojo;

import java.util.Date;

/**
 * Softdownloadstat entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Softdownloadstat implements java.io.Serializable {

	// Fields

	private Integer id;
	private Downloadtype downloadtype;
	private Users users;
	private Soft soft;
	private Equipment equipment;
	private Phonetype phonetype;
	private String phoneNumber;
	private Date addTime;
	private Integer status;
	private Date completeTime;

	// Constructors

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	/** default constructor */
	public Softdownloadstat() {
	}

	/** full constructor */
	public Softdownloadstat(Downloadtype downloadtype, Users users, Soft soft,
			Equipment equipment, Phonetype phonetype, String phoneNumber,
			Date addTime) {
		this.downloadtype = downloadtype;
		this.users = users;
		this.soft = soft;
		this.equipment = equipment;
		this.phonetype = phonetype;
		this.phoneNumber = phoneNumber;
		this.addTime = addTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Downloadtype getDownloadtype() {
		return this.downloadtype;
	}

	public void setDownloadtype(Downloadtype downloadtype) {
		this.downloadtype = downloadtype;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Soft getSoft() {
		return this.soft;
	}

	public void setSoft(Soft soft) {
		this.soft = soft;
	}

	public Equipment getEquipment() {
		return this.equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Phonetype getPhonetype() {
		return this.phonetype;
	}

	public void setPhonetype(Phonetype phonetype) {
		this.phonetype = phonetype;
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

}