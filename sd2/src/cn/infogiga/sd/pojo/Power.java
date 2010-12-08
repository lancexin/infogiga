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

	private Integer id;
	private String powerValue;
	private String powerName;
	private Integer status;
	private Set userses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Power() {
	}

	/** full constructor */
	public Power(String powerValue, String powerName, Integer status,
			Set userses) {
		this.powerValue = powerValue;
		this.powerName = powerName;
		this.status = status;
		this.userses = userses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Set getUserses() {
		return this.userses;
	}

	public void setUserses(Set userses) {
		this.userses = userses;
	}

}