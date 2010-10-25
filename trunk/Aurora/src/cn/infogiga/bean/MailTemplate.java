package cn.infogiga.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * MailTemplate entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MailTemplate extends cn.infogiga.bean.BaseBean implements
		java.io.Serializable {

	// Fields

	private Integer mailTemplateId;
	private String templateName;
	private String content;
	private Integer defaultFlag;
	private Set settings = new HashSet(0);

	// Constructors

	/** default constructor */
	public MailTemplate() {
	}

	/** full constructor */
	public MailTemplate(String templateName, String content,
			Integer defaultFlag, Set settings) {
		this.templateName = templateName;
		this.content = content;
		this.defaultFlag = defaultFlag;
		this.settings = settings;
	}

	// Property accessors

	public Integer getMailTemplateId() {
		return this.mailTemplateId;
	}

	public void setMailTemplateId(Integer mailTemplateId) {
		this.mailTemplateId = mailTemplateId;
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

}