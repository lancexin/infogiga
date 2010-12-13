package cn.infogiga.pojo;

import java.util.Date;

/**
 * Sms entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Sms implements java.io.Serializable {

	// Fields

	private Integer id;
	private String phoneNumber;
	private String context;
	private Date sendTime;
	private Integer status;
	private Integer smsType;
	private String url;

	// Constructors

	/** default constructor */
	public Sms() {
	}

	/** full constructor */
	public Sms(String phoneNumber, String context, Date sendTime,
			Integer status, Integer smsType, String url) {
		this.phoneNumber = phoneNumber;
		this.context = context;
		this.sendTime = sendTime;
		this.status = status;
		this.smsType = smsType;
		this.url = url;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getSendTime() {
		return this.sendTime;
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

	public Integer getSmsType() {
		return this.smsType;
	}

	public void setSmsType(Integer smsType) {
		this.smsType = smsType;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}