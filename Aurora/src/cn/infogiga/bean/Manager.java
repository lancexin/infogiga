package cn.infogiga.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Manager entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Manager extends cn.infogiga.bean.BaseBean implements
		java.io.Serializable {

	// Fields

	private Integer managerId;
	private Setting setting;
	private Integer creatorId;
	private Groups groups;
	private Role role;
	private String username;
	private String password;
	private String name;
	private String gender;
	private String phoneNumber;
	private String mail;
	private Set invitationsForTechnicianId = new HashSet(0);
	private Set invitationsForGuiderId = new HashSet(0);
	private Set invitationsForManagerId = new HashSet(0);
	private Set applications = new HashSet(0);

	// Constructors

	/** default constructor */
	public Manager() {
	}

	/** full constructor */
	public Manager(Setting setting, Groups groups, Role role,
			String username, String password, String name, String gender,
			String phoneNumber, String mail, Set invitationsForTechnicianId,
			Set invitationsForGuiderId,
			Set invitationsForManagerId, Set applications) {
		this.setting = setting;
		this.groups = groups;
		this.role = role;
		this.username = username;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.mail = mail;
		this.invitationsForTechnicianId = invitationsForTechnicianId;
		this.invitationsForGuiderId = invitationsForGuiderId;
		this.invitationsForManagerId = invitationsForManagerId;
		this.applications = applications;
	}

	// Property accessors

	public Integer getManagerId() {
		return this.managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public Setting getSetting() {
		return this.setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}


	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Groups getGroups() {
		return this.groups;
	}

	public void setGroups(Groups groups) {
		this.groups = groups;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Set getInvitationsForTechnicianId() {
		return this.invitationsForTechnicianId;
	}

	public void setInvitationsForTechnicianId(Set invitationsForTechnicianId) {
		this.invitationsForTechnicianId = invitationsForTechnicianId;
	}


	public Set getInvitationsForGuiderId() {
		return this.invitationsForGuiderId;
	}

	public void setInvitationsForGuiderId(Set invitationsForGuiderId) {
		this.invitationsForGuiderId = invitationsForGuiderId;
	}

	public Set getInvitationsForManagerId() {
		return this.invitationsForManagerId;
	}

	public void setInvitationsForManagerId(Set invitationsForManagerId) {
		this.invitationsForManagerId = invitationsForManagerId;
	}

	public Set getApplications() {
		return this.applications;
	}

	public void setApplications(Set applications) {
		this.applications = applications;
	}

}