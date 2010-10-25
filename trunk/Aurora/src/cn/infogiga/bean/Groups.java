package cn.infogiga.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Groups entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Groups extends cn.infogiga.bean.BaseBean implements
		java.io.Serializable {

	// Fields

	private Integer groupId;
	private String groupName;
	private String power;
	private Set managers = new HashSet(0);

	// Constructors

	/** default constructor */
	public Groups() {
	}

	/** full constructor */
	public Groups(String groupName, String power, Set managers) {
		this.groupName = groupName;
		this.power = power;
		this.managers = managers;
	}

	// Property accessors

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getPower() {
		return this.power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public Set getManagers() {
		return this.managers;
	}

	public void setManagers(Set managers) {
		this.managers = managers;
	}

}