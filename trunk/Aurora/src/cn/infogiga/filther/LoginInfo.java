package cn.infogiga.filther;

import java.util.HashSet;
import java.util.Set;

import cn.infogiga.bean.Groups;
import cn.infogiga.bean.Manager;
import cn.infogiga.bean.Role;
import cn.infogiga.bean.Setting;

public class LoginInfo {
	public Integer managerId;
	public Setting setting;
	public Integer settingId = -1;
	public Integer defaultGuiderId = -1;
	public Integer defaultTechnicianId = -1;
	public Integer defaultMmstemplateId = -1;
	public Integer defaultMailtemplateId = -1;
	public Integer isAttachment = 0;
	public String attachmentURI;
	public Integer creatorId = -1;
	public Manager creator;
	public Integer groupId = -1;
	public Groups groups;
	public Role role;
	public Integer roleId = -1;
	public String defaultLocation;
	public String username;
	public String password;
	public String name;
	public String gender;
	public String phoneNumber;
	public String mail;
	public String power;
}
