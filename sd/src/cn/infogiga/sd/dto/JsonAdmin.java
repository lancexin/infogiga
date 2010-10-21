package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonAdmin {
	@Sync(value = "adminId")
	private String adminId;
	
	@Sync(value = "nickName")
	private String nickName;
	
	@Sync(value = "userName")
	private String userName;
	
	@Sync(value = "passWord")
	private String passWord;
	
	@Sync(value = "power.powerId")
	private String powerId;
	
	@Sync(value = "power.powerName")
	private String powerName;
	
	@Sync(value = "status")
	private String status;
	
	@Sync(value = "addTime")
	private String addTime;
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
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
