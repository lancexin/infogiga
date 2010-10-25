package cn.infogiga.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Mmstemplate entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Mmstemplate extends cn.infogiga.bean.BaseBean implements
		java.io.Serializable {

	// Fields

	private Integer mmstemplateId;
	private String templateName;
	private String content;
	private Integer defaultFlag;
	private Set settings = new HashSet(0);
	private Set invitations = new HashSet(0);

	// Constructors

	/** default constructor */
	public Mmstemplate() {
	}

	/** full constructor */
	public Mmstemplate(String templateName, String content,
			Integer defaultFlag, Set settings, Set invitations) {
		this.templateName = templateName;
		this.content = content;
		this.defaultFlag = defaultFlag;
		this.settings = settings;
		this.invitations = invitations;
	}

	// Property accessors

	public Integer getMmstemplateId() {
		return this.mmstemplateId;
	}

	public void setMmstemplateId(Integer mmstemplateId) {
		this.mmstemplateId = mmstemplateId;
	}

	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getDefaultFlag() {
		return this.defaultFlag;
	}

	public void setDefaultFlag(Integer defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public Set getSettings() {
		return this.settings;
	}

	public void setSettings(Set settings) {
		this.settings = settings;
	}

	public Set getInvitations() {
		return this.invitations;
	}

	public void setInvitations(Set invitations) {
		this.invitations = invitations;
	}

}