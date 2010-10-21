package cn.infogiga.sd.pojo;

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

	private Integer equipmentId;
	private Bissinusshall bissinusshall;
	private String equipmentCode;
	private String mac;
	private Date addTime;
	private Integer status;
	private String equipmentName;
	private Set videodownloadstats = new HashSet(0);
	private Set softdownloadstats = new HashSet(0);
	private Set musicdownloadstats = new HashSet(0);

	// Constructors

	/** default constructor */
	public Equipment() {
	}
	
	public Equipment(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	/** full constructor */
	public Equipment(Bissinusshall bissinusshall, String equipmentCode,
			String mac, Date addTime, Integer status, String equipmentName,
			Set videodownloadstats, Set softdownloadstats,
			Set musicdownloadstats) {
		this.bissinusshall = bissinusshall;
		this.equipmentCode = equipmentCode;
		this.mac = mac;
		this.addTime = addTime;
		this.status = status;
		this.equipmentName = equipmentName;
		this.videodownloadstats = videodownloadstats;
		this.softdownloadstats = softdownloadstats;
		this.musicdownloadstats = musicdownloadstats;
	}

	// Property accessors

	public Integer getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
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

	public Set getVideodownloadstats() {
		return this.videodownloadstats;
	}

	public void setVideodownloadstats(Set videodownloadstats) {
		this.videodownloadstats = videodownloadstats;
	}

	public Set getSoftdownloadstats() {
		return this.softdownloadstats;
	}

	public void setSoftdownloadstats(Set softdownloadstats) {
		this.softdownloadstats = softdownloadstats;
	}

	public Set getMusicdownloadstats() {
		return this.musicdownloadstats;
	}

	public void setMusicdownloadstats(Set musicdownloadstats) {
		this.musicdownloadstats = musicdownloadstats;
	}

}