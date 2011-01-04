package cn.infogiga.exp.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractEquipment entity provides the base persistence definition of the
 * Equipment entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Equipment
		implements java.io.Serializable {

	// Fields

	private Integer equipmentId;
	private Sysinfo sysinfo;
	private Team team;
	private String mac;
	private String ip;
	private Date addTime;
	private Integer status;
	private String code;
	private String equiName;
	private String harddisk;
	private String backup1;
	private String backup2;
	private String backup3;
	private Set statisticses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Equipment() {
	}

	/** full constructor */
	public Equipment(Sysinfo sysinfo, Team team, String mac, String ip,
			Date addTime, Integer status, String code, String equiName,
			String harddisk, String backup1, String backup2, String backup3,
			Set statisticses) {
		this.sysinfo = sysinfo;
		this.team = team;
		this.mac = mac;
		this.ip = ip;
		this.addTime = addTime;
		this.status = status;
		this.code = code;
		this.equiName = equiName;
		this.harddisk = harddisk;
		this.backup1 = backup1;
		this.backup2 = backup2;
		this.backup3 = backup3;
		this.statisticses = statisticses;
	}

	// Property accessors

	public Integer getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public Sysinfo getSysinfo() {
		return this.sysinfo;
	}

	public void setSysinfo(Sysinfo sysinfo) {
		this.sysinfo = sysinfo;
	}

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public String getMac() {
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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

	public String getEquiName() {
		return this.equiName;
	}

	public void setEquiName(String equiName) {
		this.equiName = equiName;
	}

	public String getHarddisk() {
		return this.harddisk;
	}

	public void setHarddisk(String harddisk) {
		this.harddisk = harddisk;
	}

	public String getBackup1() {
		return this.backup1;
	}

	public void setBackup1(String backup1) {
		this.backup1 = backup1;
	}

	public String getBackup2() {
		return this.backup2;
	}

	public void setBackup2(String backup2) {
		this.backup2 = backup2;
	}

	public String getBackup3() {
		return this.backup3;
	}

	public void setBackup3(String backup3) {
		this.backup3 = backup3;
	}

	public Set getStatisticses() {
		return this.statisticses;
	}

	public void setStatisticses(Set statisticses) {
		this.statisticses = statisticses;
	}

}