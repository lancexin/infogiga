package cn.infogiga.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Customer entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Customer extends cn.infogiga.bean.BaseBean implements
		java.io.Serializable {

	// Fields

	private Integer customerId;
	private Invitation invitation;
	private String name;
	private String gender;
	private String phoneNumber;
	private String mail;
	private String company;
	private String username;
	private String password;
	private Date lastLoginTime;
	private Integer copyFlag;
	private Integer sendStatus;
	private Set qrcodes = new HashSet(0);
	private Set applications = new HashSet(0);
	private Set comments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Customer() {
	}

	/** minimal constructor */
	public Customer(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/** full constructor */
	public Customer(Invitation invitation, String name, String gender,
			String phoneNumber, String mail, String company, String username,
			String password, Date lastLoginTime, Integer copyFlag,
			Integer sendStatus, Set qrcodes, Set applications, Set comments) {
		this.invitation = invitation;
		this.name = name;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.mail = mail;
		this.company = company;
		this.username = username;
		this.password = password;
		this.lastLoginTime = lastLoginTime;
		this.copyFlag = copyFlag;
		this.sendStatus = sendStatus;
		this.qrcodes = qrcodes;
		this.applications = applications;
		this.comments = comments;
	}

	// Property accessors

	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Invitation getInvitation() {
		return this.invitation;
	}

	public void setInvitation(Invitation invitation) {
		this.invitation = invitation;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getCopyFlag() {
		return this.copyFlag;
	}

	public void setCopyFlag(Integer copyFlag) {
		this.copyFlag = copyFlag;
	}

	public Integer getSendStatus() {
		return this.sendStatus;
	}

	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}

	public Set getQrcodes() {
		return this.qrcodes;
	}

	public void setQrcodes(Set qrcodes) {
		this.qrcodes = qrcodes;
	}

	public Set getApplications() {
		return this.applications;
	}

	public void setApplications(Set applications) {
		this.applications = applications;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

}