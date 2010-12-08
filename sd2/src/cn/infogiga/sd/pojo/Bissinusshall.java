package cn.infogiga.sd.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Bissinusshall entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Bissinusshall implements java.io.Serializable {

	// Fields

	private Integer id;
	private City city;
	private String hallName;
	private String description;
	private Date addTime;
	private Integer status;
	private String code;
	private Set userses = new HashSet(0);
	private Set equipments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Bissinusshall() {
	}

	/** full constructor */
	public Bissinusshall(City city, String hallName, String description,
			Date addTime, Integer status, String code, Set userses,
			Set equipments) {
		this.city = city;
		this.hallName = hallName;
		this.description = description;
		this.addTime = addTime;
		this.status = status;
		this.code = code;
		this.userses = userses;
		this.equipments = equipments;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getHallName() {
		return this.hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set getUserses() {
		return this.userses;
	}

	public void setUserses(Set userses) {
		this.userses = userses;
	}

	public Set getEquipments() {
		return this.equipments;
	}

	public void setEquipments(Set equipments) {
		this.equipments = equipments;
	}

}