package cn.infogiga.exp.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSysinfo entity provides the base persistence definition of the
 * Sysinfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Sysinfo
		implements java.io.Serializable {

	// Fields

	private Integer systemId;
	private String systemName;
	private String description;
	private Date addTime;
	private Integer status;
	private String backup1;
	private String backup2;
	private String backup3;
	private Set statisticses = new HashSet(0);
	private Set teams = new HashSet(0);
	private Set renewals = new HashSet(0);
	private Set userinfos = new HashSet(0);
	private Set equipments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Sysinfo() {
	}

	/** full constructor */
	public Sysinfo(String systemName, String description, Date addTime,
			Integer status, String backup1, String backup2, String backup3,
			Set statisticses, Set teams, Set renewals, Set userinfos,
			Set equipments) {
		this.systemName = systemName;
		this.description = description;
		this.addTime = addTime;
		this.status = status;
		this.backup1 = backup1;
		this.backup2 = backup2;
		this.backup3 = backup3;
		this.statisticses = statisticses;
		this.teams = teams;
		this.renewals = renewals;
		this.userinfos = userinfos;
		this.equipments = equipments;
	}

	// Property accessors

	public Integer getSystemId() {
		return this.systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
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

	public Set getTeams() {
		return this.teams;
	}

	public void setTeams(Set teams) {
		this.teams = teams;
	}

	public Set getRenewals() {
		return this.renewals;
	}

	public void setRenewals(Set renewals) {
		this.renewals = renewals;
	}

	public Set getUserinfos() {
		return this.userinfos;
	}

	public void setUserinfos(Set userinfos) {
		this.userinfos = userinfos;
	}

	public Set getEquipments() {
		return this.equipments;
	}

	public void setEquipments(Set equipments) {
		this.equipments = equipments;
	}

}