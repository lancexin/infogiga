package cn.infogiga.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Users entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable {

	// Fields

	private Integer id;
	private Power power;
	private Bissinusshall bissinusshall;
	private String nickName;
	private String userName;
	private String passWord;
	private Integer status;
	private Date addTime;
	private String phoneNumber;
	private Set softdownloadstats = new HashSet(0);
	private Set ophonestats = new HashSet(0);

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** full constructor */
	public Users(Power power, Bissinusshall bissinusshall, String nickName,
			String userName, String passWord, Integer status, Date addTime,
			String phoneNumber, Set softdownloadstats, Set ophonestats) {
		this.power = power;
		this.bissinusshall = bissinusshall;
		this.nickName = nickName;
		this.userName = userName;
		this.passWord = passWord;
		this.status = status;
		this.addTime = addTime;
		this.phoneNumber = phoneNumber;
		this.softdownloadstats = softdownloadstats;
		this.ophonestats = ophonestats;
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

	public Bissinusshall getBissinusshall() {
		return this.bissinusshall;
	}

	public void setBissinusshall(Bissinusshall bissinusshall) {
		this.bissinusshall = bissinusshall;
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

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set getSoftdownloadstats() {
		return this.softdownloadstats;
	}

	public void setSoftdownloadstats(Set softdownloadstats) {
		this.softdownloadstats = softdownloadstats;
	}

	public Set getOphonestats() {
		return this.ophonestats;
	}

	public void setOphonestats(Set ophonestats) {
		this.ophonestats = ophonestats;
	}

}