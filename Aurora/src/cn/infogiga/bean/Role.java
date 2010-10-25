package cn.infogiga.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Role entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Role extends cn.infogiga.bean.BaseBean implements
		java.io.Serializable {

	// Fields

	private Integer roleId;
	private String roleName;
	private String comment;
	private Set managers = new HashSet(0);
	private Set actionRoles = new HashSet(0);

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** full constructor */
	public Role(String roleName, String comment, Set managers, Set actionRoles) {
		this.roleName = roleName;
		this.comment = comment;
		this.managers = managers;
		this.actionRoles = actionRoles;
	}

	// Property accessors

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Set getManagers() {
		return this.managers;
	}

	public void setManagers(Set managers) {
		this.managers = managers;
	}

	public Set getActionRoles() {
		return this.actionRoles;
	}

	public void setActionRoles(Set actionRoles) {
		this.actionRoles = actionRoles;
	}

}