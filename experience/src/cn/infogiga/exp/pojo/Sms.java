package cn.infogiga.exp.pojo;

import java.util.Date;

/**
 * AbstractSms entity provides the base persistence definition of the Sms
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Sms implements java.io.Serializable,cindy.web.pojo.POJO {

	// Fields

	private Integer smsId;
	private Menu menu;
	private String phoneNumber;
	private String context;
	private Date sendTime;
	private Integer status;
	private String backup1;
	private String backup2;
	private String backup3;

	// Constructors

	/** default constructor */
	public Sms() {
	}

	/** full constructor */
	public Sms(Menu menu, String phoneNumber, String context,
			Date sendTime, Integer status, String backup1, String backup2,
			String backup3) {
		this.menu = menu;
		this.phoneNumber = phoneNumber;
		this.context = context;
		this.sendTime = sendTime;
		this.status = status;
		this.backup1 = backup1;
		this.backup2 = backup2;
		this.backup3 = backup3;
	}

	// Property accessors

	public Integer getSmsId() {
		return this.smsId;
	}

	public void setSmsId(Integer smsId) {
		this.smsId = smsId;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
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