package cn.infogiga.sd.pojo;

import java.util.Date;

/**
 * Admin entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Admin extends PowerUser implements java.io.Serializable {

	// Fields
	

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** full constructor */
	public Admin(Power power, String nickName, String userName,
			String passWord, Integer status, Date addTime) {
		super.power = power;
		super.nickName = nickName;
		super.userName = userName;
		super.passWord = passWord;
		super.status = status;
		super.addTime = addTime;
	}
}