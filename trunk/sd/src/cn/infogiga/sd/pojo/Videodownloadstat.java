package cn.infogiga.sd.pojo;

import java.util.Date;

/**
 * Videodownloadstat entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Videodownloadstat implements java.io.Serializable {

	// Fields

	private Integer statId;
	private Equipment equipment;
	private Video video;
	private Downloadtype downloadtype;
	private String phoneNumber;
	private Integer status;
	private Date addTime;

	// Constructors

	/** default constructor */
	public Videodownloadstat() {
	}

	/** full constructor */
	public Videodownloadstat(Equipment equipment, Video video,
			Downloadtype downloadtype, String phoneNumber, Integer status,
			Date addTime) {
		this.equipment = equipment;
		this.video = video;
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

	public Video getVideo() {
		return this.video;
	}

	public void setVideo(Video video) {
		this.video = video;
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