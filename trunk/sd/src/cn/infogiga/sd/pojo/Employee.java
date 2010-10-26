package cn.infogiga.sd.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Employee entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Employee extends PowerUser implements java.io.Serializable {

	// Fields


	private Bissinusshall bissinusshall;
	private Set musicdownloadstats = new HashSet(0);
	private Set videodownloadstats = new HashSet(0);
	private Set softdownloadstats = new HashSet(0);

	// Constructors

	/** default constructor */
	public Employee() {
	}

	/** full constructor */
	public Employee(Power power, Bissinusshall bissinusshall, String nickName,
			String userName, String passWord, Integer status, Date addTime,
			Set musicdownloadstats, Set videodownloadstats,
			Set softdownloadstats) {
		super.power = power;
		super.nickName = nickName;
		super.userName = userName;
		super.passWord = passWord;
		super.status = status;
		super.addTime = addTime;
		this.bissinusshall = bissinusshall;
		this.musicdownloadstats = musicdownloadstats;
		this.videodownloadstats = videodownloadstats;
		this.softdownloadstats = softdownloadstats;
	}

	// Property accessors

	

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

}