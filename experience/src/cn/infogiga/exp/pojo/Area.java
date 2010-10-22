package cn.infogiga.exp.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractArea entity provides the base persistence definition of the Area
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Area 
		implements java.io.Serializable,cindy.web.pojo.POJO {

	// Fields

	private Integer areaId;
	private String areaName;
	private String areaCode;
	private Set teams = new HashSet(0);

	// Constructors

	/** default constructor */
	public Area() {
	}

	/** minimal constructor */
	public Area(String areaCode) {
		this.areaCode = areaCode;
	}

	/** full constructor */
	public Area(String areaName, String areaCode, Set teams) {
		this.areaName = areaName;
		this.areaCode = areaCode;
		this.teams = teams;
	}

	// Property accessors

	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Set getTeams() {
		return this.teams;
	}

	public void setTeams(Set teams) {
		this.teams = teams;
	}

}