package cn.infogiga.sd.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Province entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Province implements java.io.Serializable {

	// Fields

	private Integer id;
	private String provinceName;
	private Set cities = new HashSet(0);

	// Constructors

	/** default constructor */
	public Province() {
	}

	/** full constructor */
	public Province(String provinceName, Set cities) {
		this.provinceName = provinceName;
		this.cities = cities;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Set getCities() {
		return this.cities;
	}

	public void setCities(Set cities) {
		this.cities = cities;
	}

}