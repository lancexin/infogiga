package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonEmployee {
	@Sync(value = "userId")
	private String employeeId;
	
	@Sync(value = "nickName")
	private String nickName;
	
	@Sync(value = "userName")
	private String userName;
	
	@Sync(value = "passWord")
	private String passWord;
	
	@Sync(value = "bissinusshall.hallId")
	private String hallId;
	
	@Sync(value = "bissinusshall.hallName")
	private String hallName;
	
	@Sync(value = "power.powerId")
	private String powerId;
	
	@Sync(value = "power.powerName")
	private String powerName;
	
	@Sync(value = "status")
	private String status;
	
	@Sync(value = "addTime")
	private String addTime;
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	public String getHallId() {
		return hallId;
	}
	public void setHallId(String hallId) {
		this.hallId = hallId;
	}
	public String getHallName() {
		return hallName;
	}
	public void setHallName(String hallName) {
		this.hallName = hallName;
	}
	public String getPowerId() {
		return powerId;
	}
	public void setPowerId(String powerId) {
		this.powerId = powerId;
	}
	public String getPowerName() {
		return powerName;
	}
	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
}
