package cn.infogiga.bean;

/**
 * ActionRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ActionRole extends cn.infogiga.bean.BaseBean implements
		java.io.Serializable {

	// Fields

	private Integer actionRoleId;
	private Role role;
	private Action action;

	// Constructors

	/** default constructor */
	public ActionRole() {
	}

	/** full constructor */
	public ActionRole(Role role, Action action) {
		this.role = role;
		this.action = action;
	}

	// Property accessors

	public Integer getActionRoleId() {
		return this.actionRoleId;
	}

	public void setActionRoleId(Integer actionRoleId) {
		this.actionRoleId = actionRoleId;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Action getAction() {
		return this.action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

}