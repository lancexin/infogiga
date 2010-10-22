package cn.infogiga.exp.pojo;

import java.util.Date;

/**
 * AbstractUserinfo entity provides the base persistence definition of the
 * Userinfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Userinfo extends PowerUser implements java.io.Serializable,cindy.web.pojo.POJO {

	// Fields

	private Integer userId;
	private Sysinfo sysinfo;
	private String userName;
	private String userPassword;
	private String nickName;
	private Date addTime;
	private Integer status;
	private String backup1;
	private String backup2;
	private String backup3;

	// Constructors

	/** default constructor */
	public Userinfo() {
	}

	/** full constructor */
	public Userinfo(Sysinfo sysinfo, String userName,
			String userPassword, String nickName, Date addTime, String power,
			Integer status, String backup1, String backup2, String backup3) {
		this.sysinfo = sysinfo;
		this.userName = userName;
		this.userPassword = userPassword;
		this.nickName = nickName;
		this.addTime = addTime;
		this.power = power;
		this.status = status;
		this.backup1 = backup1;
		this.backup2 = backup2;
		this.backup3 = backup3;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Sysinfo getSysinfo() {
		return this.sysinfo;
	}

	public void setSysinfo(Sysinfo sysinfo) {
		this.sysinfo = sysinfo;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
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