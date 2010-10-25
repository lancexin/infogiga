package cn.infogiga.ibatis.pojo;

/**
 * User entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class User implements
		java.io.Serializable {

	// Fields

	private Integer userID;
	private Power power;
	private String userName;
	private String passWord;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(Power power, String userName, String passWord) {
		this.power = power;
		this.userName = userName;
		this.passWord = passWord;
	}

	// Property accessors

	public Power getPower() {
		return this.power;
	}

	public void setPower(Power power) {
		this.power = power;
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

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}


}