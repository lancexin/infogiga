package cn.infogiga.sd.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * City entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class City implements java.io.Serializable {

	// Fields

	private Integer cityId;
	private Province province;
	private String cityName;
	private Set bissinusshalls = new HashSet(0);

	// Constructors

	/** default constructor */
	public City() {
	}
	
	public City(Integer cityId) {
		this.cityId = cityId;
	}

	/** full constructor */
	public City(Province province, String cityName, Set bissinusshalls) {
		this.province = province;
		this.cityName = cityName;
		this.bissinusshalls = bissinusshalls;
	}

	// Property accessors

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
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