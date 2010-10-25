package cn.infogiga.po;
/**
 * 个人信息表
 * @author cindy
 *
 */
public class LoginPO {
	private int userId;
	private String username;
	private String password;
	private String name;
	private String sex;
	private String mail;
	private String phoneNumber;
	private int roleId;
	private String roleName;
	private int groupId;
	private String groupName;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public LoginPO(int userId, String username, String password, String name,
			String sex, String mail, String phoneNumber, int roleId,
			String roleName, int groupId, String groupName) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.mail = mail;
		this.phoneNumber = phoneNumber;
		this.roleId = roleId;
		this.roleName = roleName;
		this.groupId = groupId;
		this.groupName = groupName;
	}
}
