package cn.infogiga.pojo;

import java.util.Date;

/**
 * Tempdownloadstat entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tempdownloadstat implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer equipmentId;
	private Integer softId;
	private Integer phonetypeId;
	private Integer userId;
	private Integer downloadtypeId;
	private String phoneNumber;
	private Date addTime;
	private String code;

	// Constructors

	/** default constructor */
	public Tempdownloadstat() {
	}

	/** full constructor */
	public Tempdownloadstat(Integer equipmentId, Integer softId,
			Integer phonetypeId, Integer userId, Integer downloadtypeId,
			String phoneNumber, Date addTime, String code) {
		this.equipmentId = equipmentId;
		this.softId = softId;
		this.phonetypeId = phonetypeId;
		this.userId = userId;
		this.downloadtypeId = downloadtypeId;
		this.phoneNumber = phoneNumber;
		this.addTime = addTime;
		this.code = code;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public Integer getSoftId() {
		return this.softId;
	}

	public void setSoftId(Integer softId) {
		this.softId = softId;
	}

	public Integer getPhonetypeId() {
		return this.phonetypeId;
	}

	public void setPhonetypeId(Integer phonetypeId) {
		this.phonetypeId = phonetypeId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDownloadtypeId() {
		return this.downloadtypeId;
	}

	public void setDownloadtypeId(Integer downloadtypeId) {
		this.downloadtypeId = downloadtypeId;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}