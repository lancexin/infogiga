package cn.infogiga.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * City entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class City implements java.io.Serializable {

	// Fields

	private Integer id;
	private Province province;
	private String cityName;
	private Set bissinusshalls = new HashSet(0);

	// Constructors

	/** default constructor */
	public City() {
	}

	/** full constructor */
	public City(Province province, String cityName, Set bissinusshalls) {
		this.province = province;
		this.cityName = cityName;
		this.bissinusshalls = bissinusshalls;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Province getProvince() {
		return this.province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Set getBissinusshalls() {
		return this.bissinusshalls;
	}

	public void setBissinusshalls(Set bissinusshalls) {
		this.bissinusshalls = bissinusshalls;
	}

}