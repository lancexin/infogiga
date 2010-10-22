package cn.infogiga.exp.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractEmployee entity provides the base persistence definition of the
 * Employee entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Employee extends PowerUser implements java.io.Serializable,cindy.web.pojo.POJO {

	// Fields

	private Integer employeeId;
	private Team team;
	private String empName;
	private String empNo;
	private String password;
	private String phoneNumber;
	private Integer status;
	private Date addTime;
	private Set statisticses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Employee() {
	}

	/** full constructor */
	public Employee(Team team, String empName, String empNo,
			String password, String phoneNumber, Integer status, Date addTime,
			String power, Set statisticses) {
		this.team = team;
		this.empName = empName;
		this.empNo = empNo;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.addTime = addTime;
		this.power = power;
		this.statisticses = statisticses;
	}

	// Property accessors

	public Integer getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpNo() {
		return this.empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Set getStatisticses() {
		return this.statisticses;
	}

	public void setStatisticses(Set statisticses) {
		this.statisticses = statisticses;
	}

}