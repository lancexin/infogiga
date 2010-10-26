package cn.infogiga.sd.dto;

import java.util.Date;

import cindy.page.beanutils.Sync;

public class JsonPowerUser {
	@Sync(value = "userId")
	private String userId;
	
	@Sync(value = "nickName")
	private String nickName;
	
	@Sync(value = "userName")
	private String userName;
	
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


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


}
