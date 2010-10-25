package cn.infogiga.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * ActionMenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ActionMenu extends cn.infogiga.bean.BaseBean implements
		java.io.Serializable {

	// Fields

	private Integer actionMenuId;
	private String menuIdInPage;
	private String actionMenuName;
	private String comment;
	private Set actions = new HashSet(0);

	// Constructors

	/** default constructor */
	public ActionMenu() {
	}

	/** minimal constructor */
	public ActionMenu(String actionMenuName) {
		this.actionMenuName = actionMenuName;
	}

	/** full constructor */
	public ActionMenu(String menuIdInPage, String actionMenuName,
			String comment, Set actions) {
		this.menuIdInPage = menuIdInPage;
		this.actionMenuName = actionMenuName;
		this.comment = comment;
		this.actions = actions;
	}

	// Property accessors

	public Integer getActionMenuId() {
		return this.actionMenuId;
	}

	public void setActionMenuId(Integer actionMenuId) {
		this.actionMenuId = actionMenuId;
	}

	public String getMenuIdInPage() {
		return this.menuIdInPage;
	}

	public void setMenuIdInPage(String menuIdInPage) {
		this.menuIdInPage = menuIdInPage;
	}

	public String getActionMenuName() {
		return this.actionMenuName;
	}

	public void setActionMenuName(String actionMenuName) {
		this.actionMenuName = actionMenuName;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Set getActions() {
		return this.actions;
	}

	public void setActions(Set actions) {
		this.actions = actions;
	}

}