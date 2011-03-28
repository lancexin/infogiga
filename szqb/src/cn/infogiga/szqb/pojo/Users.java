package cn.infogiga.szqb.pojo;

import java.util.Date;

/**
 * Users entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable {

	// Fields

	private Integer id;
	private Power power;
	private String nickName;
	private String userName;
	private String passWord;
	private Integer status;
	private Date addTime;

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** full constructor */
	public Users(Power power, String nickName, String userName,
			String passWord, Integer status, Date addTime) {
		this.power = power;
		this.nickName = nickName;
		this.userName = userName;
		this.passWord = passWord;
		this.status = status;
		this.addTime = addTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Power getPower() {
		return this.power;
	}

	public void setPower(Power power) {
		this.power = power;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return this.passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
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