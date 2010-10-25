package cn.infogiga.ibatis.pojo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Power entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Power implements
		java.io.Serializable {

	// Fields

	private Integer powerID;
	private String powerName;
	private String powerValue;
	private List<User> users;

	// Constructors



	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	/** default constructor */
	public Power() {
	}

	/** full constructor */
	public Power(String powerName, String powerValue) {
		this.powerName = powerName;
		this.powerValue = powerValue;
	}

	// Property accessors


	public String getPowerName() {
		return this.powerName;
	}

	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}

	public String getPowerValue() {
		return this.powerValue;
	}

	public void setPowerValue(String powerValue) {
		this.powerValue = powerValue;
	}

	public Integer getPowerID() {
		return powerID;
	}

	public void setPowerID(Integer powerID) {
		this.powerID = powerID;
	}

}