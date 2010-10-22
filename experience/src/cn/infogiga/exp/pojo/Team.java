package cn.infogiga.exp.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTeam entity provides the base persistence definition of the Team
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Team implements java.io.Serializable,cindy.web.pojo.POJO {

	// Fields

	private Integer teamId;
	private Area area;
	private String teamName;
	private String description;
	private Date addTime;
	private Integer status;
	private String backup1;
	private String backup2;
	private String backup3;
	private String teamCode;
	private Set employees = new HashSet(0);
	private Set equipments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Team() {
	}

	/** full constructor */
	public Team(Area area, String teamName, String description,
			Date addTime, Integer status, String backup1, String backup2,
			String backup3, String teamCode, Set employees, Set equipments) {
		this.area = area;
		this.teamName = teamName;
		this.description = description;
		this.addTime = addTime;
		this.status = status;
		this.backup1 = backup1;
		this.backup2 = backup2;
		this.backup3 = backup3;
		this.teamCode = teamCode;
		this.employees = employees;
		this.equipments = equipments;
	}

	// Property accessors

	public Integer getTeamId() {
		return this.teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getTeamName() {
		return this.teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
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

	public String getTeamCode() {
		return this.teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public Set getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set employees) {
		this.employees = employees;
	}

	public Set getEquipments() {
		return this.equipments;
	}

	public void setEquipments(Set equipments) {
		this.equipments = equipments;
	}

}