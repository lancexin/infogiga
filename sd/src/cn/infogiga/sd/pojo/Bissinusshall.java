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

	private Integer hallId;
	private City city;
	private String hallName;
	private String description;
	private Date addTime;
	private Integer status;
	private Set equipments = new HashSet(0);
	private Set employees = new HashSet(0);

	// Constructors

	/** default constructor */
	public Bissinusshall() {
	}
	
	public Bissinusshall(Integer hallId) {
		this.hallId = hallId;
	}

	/** full constructor */
	public Bissinusshall(City city, String hallName, String description,
			Date addTime, Integer status, Set equipments, Set employees) {
		this.city = city;
		this.hallName = hallName;
		this.description = description;
		this.addTime = addTime;
		this.status = status;
		this.equipments = equipments;
		this.employees = employees;
	}

	// Property accessors

	public Integer getHallId() {
		return this.hallId;
	}

	public void setHallId(Integer hallId) {
		this.hallId = hallId;
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

	public Set getEquipments() {
		return this.equipments;
	}

	public void setEquipments(Set equipments) {
		this.equipments = equipments;
	}

	public Set getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set employees) {
		this.employees = employees;
	}

}