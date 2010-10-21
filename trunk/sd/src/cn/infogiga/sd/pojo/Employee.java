package cn.infogiga.sd.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Employee entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Employee  extends PowerUser  implements java.io.Serializable {

	// Fields

	private Integer employeeId;
	private Bissinusshall bissinusshall;
	private Set musicdownloadstats = new HashSet(0);
	private Set softdownloadstats = new HashSet(0);

	// Constructors

	/** default constructor */
	public Employee() {
	}
	
	public Employee(Integer employeeId) {
		this.employeeId = employeeId;
	}

	/** full constructor */
	public Employee(Power power, Bissinusshall bissinusshall, String nickName,
			String userName, String passWord, Integer status, Date addTime,
			Set musicdownloadstats, Set softdownloadstats) {
		this.power = power;
		this.bissinusshall = bissinusshall;
		this.nickName = nickName;
		this.userName = userName;
		this.passWord = passWord;
		this.status = status;
		this.addTime = addTime;
		this.musicdownloadstats = musicdownloadstats;
		this.softdownloadstats = softdownloadstats;
	}

	// Property accessors

	public Integer getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Bissinusshall getBissinusshall() {
		return this.bissinusshall;
	}

	public void setBissinusshall(Bissinusshall bissinusshall) {
		this.bissinusshall = bissinusshall;
	}
	
	public Set getMusicdownloadstats() {
		return this.musicdownloadstats;
	}

	public void setMusicdownloadstats(Set musicdownloadstats) {
		this.musicdownloadstats = musicdownloadstats;
	}

	public Set getSoftdownloadstats() {
		return this.softdownloadstats;
	}

	public void setSoftdownloadstats(Set softdownloadstats) {
		this.softdownloadstats = softdownloadstats;
	}

}