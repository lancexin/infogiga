package cn.infogiga.sd.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Power entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Power implements java.io.Serializable {

	// Fields

	private Integer powerId;
	private String powerValue;
	private String powerName;
	private Integer status;
	private Set employees = new HashSet(0);
	private Set admins = new HashSet(0);

	// Constructors

	/** default constructor */
	public Power() {
	}
	
	public Power(Integer powerId) {
		this.powerId = powerId;
	}

	/** full constructor */
	public Power(String powerValue, String powerName, Integer status,
			Set employees, Set admins) {
		this.powerValue = powerValue;
		this.powerName = powerName;
		this.status = status;
		this.employees = employees;
		this.admins = admins;
	}

	// Property accessors

	public Integer getPowerId() {
		return this.powerId;
	}

	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}

	public String getPowerValue() {
		return this.powerValue;
	}

	public void setPowerValue(String powerValue) {
		this.powerValue = powerValue;
	}

	public String getPowerName() {
		return this.powerName;
	}

	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set employees) {
		this.employees = employees;
	}

	public Set getAdmins() {
		return this.admins;
	}

	public void setAdmins(Set admins) {
		this.admins = admins;
	}

}