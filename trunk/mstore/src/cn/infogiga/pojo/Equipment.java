package cn.infogiga.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Equipment entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Equipment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Bissinusshall bissinusshall;
	private String equipmentCode;
	private String mac;
	private Date addTime;
	private Integer status;
	private String equipmentName;
	private Set ophonestats = new HashSet(0);
	private Set softdownloadstats = new HashSet(0);

	// Constructors

	/** default constructor */
	public Equipment() {
	}

	/** full constructor */
	public Equipment(Bissinusshall bissinusshall, String equipmentCode,
			String mac, Date addTime, Integer status, String equipmentName,
			Set ophonestats, Set softdownloadstats) {
		this.bissinusshall = bissinusshall;
		this.equipmentCode = equipmentCode;
		this.mac = mac;
		this.addTime = addTime;
		this.status = status;
		this.equipmentName = equipmentName;
		this.ophonestats = ophonestats;
		this.softdownloadstats = softdownloadstats;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Bissinusshall getBissinusshall() {
		return this.bissinusshall;
	}

	public void setBissinusshall(Bissinusshall bissinusshall) {
		this.bissinusshall = bissinusshall;
	}

	public String getEquipmentCode() {
		return this.equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public String getMac() {
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
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

	public String getEquipmentName() {
		return this.equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public Set getOphonestats() {
		return this.ophonestats;
	}

	public void setOphonestats(Set ophonestats) {
		this.ophonestats = ophonestats;
	}

	public Set getSoftdownloadstats() {
		return this.softdownloadstats;
	}

	public void setSoftdownloadstats(Set softdownloadstats) {
		this.softdownloadstats = softdownloadstats;
	}

}