package cn.infogiga.po;

import java.util.Date;

public class CustomerPO {

	private String name;
	private String gender;
	private String phoneNumber;
	private String mail;
	private String company;
	private String username;
	private String password;
	private Integer copyFlag;
	private Integer sendStatus;
	public CustomerPO(){}
	public CustomerPO(String name, String gender, String phoneNumber,
			String mail, String company, String username, String password,
			Integer copyFlag, Integer sendStatus) {
		super();
		this.name = name;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.mail = mail;
		this.company = company;
		this.username = username;
		this.password = password;
		this.copyFlag = copyFlag;
		this.sendStatus = sendStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getCopyFlag() {
		return copyFlag;
	}
	public void setCopyFlag(Integer copyFlag) {
		this.copyFlag = copyFlag;
	}
	public Integer getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}
}
