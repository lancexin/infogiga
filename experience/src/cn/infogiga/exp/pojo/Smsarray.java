package cn.infogiga.exp.pojo;

import java.util.Date;

/**
 * Smsarray entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Smsarray implements java.io.Serializable,cindy.web.pojo.POJO {

	// Fields

	private Integer smsId;
	private Date sendTime;
	private Integer status;
	private String phoneNumbers;
	private String context;

	// Constructors

	/** default constructor */
	public Smsarray() {
	}

	/** full constructor */
	public Smsarray(Date sendTime, Integer status, String phoneNumbers,
			String context) {
		this.sendTime = sendTime;
		this.status = status;
		this.phoneNumbers = phoneNumbers;
		this.context = context;
	}

	// Property accessors

	public Integer getSmsId() {
		return this.smsId;
	}

	public void setSmsId(Integer smsId) {
		this.smsId = smsId;
	}


	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPhoneNumbers() {
		return this.phoneNumbers;
	}

	public void setPhoneNumbers(String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

}