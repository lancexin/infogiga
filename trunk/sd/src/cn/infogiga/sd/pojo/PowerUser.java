package cn.infogiga.sd.pojo;

import java.util.Date;

public class PowerUser {
	
	protected String userName;
	protected String passWord;
	protected String nickName;
	protected Power power;
	protected Integer status;
	protected Date addTime;
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Power getPower() {
		return power;
	}

	public void setPower(Power power) {
		this.power = power;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}
