package cn.infogiga.exp.pojo;

import java.util.Date;

/**
 * Mms entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Mms  implements java.io.Serializable,cindy.web.pojo.POJO {

	// Fields

	private Integer mmsId;
	private String phoneNumber;
	private Date startTime;
	private Date endTime;
	private Integer status;
	private String sequenceId;

	// Constructors

	/** default constructor */
	public Mms() {
	}

	/** full constructor */
	public Mms(String phoneNumber, Date startTime, Date endTime,
			Integer status, String sequenceId) {
		this.phoneNumber = phoneNumber;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.sequenceId = sequenceId;
	}

	// Property accessors

	public Integer getMmsId() {
		return this.mmsId;
	}

	public void setMmsId(Integer mmsId) {
		this.mmsId = mmsId;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSequenceId() {
		return this.sequenceId;
	}

	public void setSequenceId(String sequenceId) {
		this.sequenceId = sequenceId;
	}

}