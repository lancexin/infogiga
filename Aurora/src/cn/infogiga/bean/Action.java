package cn.infogiga.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Action entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Action extends cn.infogiga.bean.BaseBean implements
		java.io.Serializable {

	// Fields

	private Integer actionId;
	private ActionMenu actionMenu;
	private String actionName;
	private String actions;
	private String comment;
	private Set actionRoles = new HashSet(0);

	// Constructors

	/** default constructor */
	public Action() {
	}

	/** minimal constructor */
	public Action(String actions) {
		this.actions = actions;
	}

	/** full constructor */
	public Action(ActionMenu actionMenu, String actionName, String actions,
			String comment, Set actionRoles) {
		this.actionMenu = actionMenu;
		this.actionName = actionName;
		this.actions = actions;
		this.comment = comment;
		this.actionRoles = actionRoles;
	}

	// Property accessors

	public Integer getActionId() {
		return this.actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	public ActionMenu getActionMenu() {
		return this.actionMenu;
	}

	public void setActionMenu(ActionMenu actionMenu) {
		this.actionMenu = actionMenu;
	}

	public String getActionName() {
		return this.actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActions() {
		return this.actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Set getActionRoles() {
		return this.actionRoles;
	}

	public void setActionRoles(Set actionRoles) {
		this.actionRoles = actionRoles;
	}

}