package cn.infogiga.po;

public class MmsTemplatePO {
	private Integer mmstemplateId;
	private String templateName;
	private String content;
	private Integer defaultFlag;
	public Integer getMmstemplateId() {
		return mmstemplateId;
	}
	public void setMmstemplateId(Integer mmstemplateId) {
		this.mmstemplateId = mmstemplateId;
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
