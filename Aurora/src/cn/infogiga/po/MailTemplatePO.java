package cn.infogiga.po;

public class MailTemplatePO {
	private Integer mailTemplateId;
	private String templateName;
	private String content;
	private Integer defaultFlag;
	public Integer getMailTemplateId() {
		return mailTemplateId;
	}
	public void setMailTemplateId(Integer mailTemplateId) {
		this.mailTemplateId = mailTemplateId;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getDefaultFlag() {
		return defaultFlag;
	}
	public void setDefaultFlag(Integer defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
}
