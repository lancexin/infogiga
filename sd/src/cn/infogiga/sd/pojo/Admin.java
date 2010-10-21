package cn.infogiga.sd.pojo;

import java.util.Date;

/**
 * Admin entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Admin  extends PowerUser  implements java.io.Serializable  {

	// Fields

	private Integer adminId;


	// Constructors

	/** default constructor */
	public Admin() {
	}
	
	public Admin(Integer adminId) {
		this.adminId = adminId;
	}

	/** full constructor */
	public Admin(Power power, String nickName, String userName,
			String passWord, Integer status, Date addTime) {
		this.power = power;
		this.nickName = nickName;
		this.userName = userName;
		this.passWord = passWord;
		this.status = status;
		this.addTime = addTime;
	}

	// Property accessors

	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}



}