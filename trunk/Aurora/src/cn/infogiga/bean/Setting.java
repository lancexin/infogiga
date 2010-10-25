package cn.infogiga.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Setting entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Setting extends BaseBean implements java.io.Serializable {

	// Fields

	private Integer settingId;
	private Mmstemplate mmstemplate;
	private MailTemplate mailTemplate;
	private Integer sendApplicationToMail;
	private String defaultLocation;
	private Integer defaultGuiderId;
	private Integer defaultTechnicianId;
	private Integer defaultMmstemplateId;
	private Integer defaultMailtemplateId;
	
	private String sendPressbook;
	private Integer isAttachment;
	private String attachmentUri;
	private Manager manager;

	// Constructors

	/** default constructor */
	public Setting() {
	}

	

	// Property accessors

	public Integer getSettingId() {
		return this.settingId;
	}

	public void setSettingId(Integer settingId) {
		this.settingId = settingId;
	}

	public Mmstemplate getMmstemplate() {
		return this.mmstemplate;
	}

	public void setMmstemplate(Mmstemplate mmstemplate) {
		this.mmstemplate = mmstemplate;
	}

	public MailTemplate getMailTemplate() {
		return this.mailTemplate;
	}

	public void setMailTemplate(MailTemplate mailTemplate) {
		this.mailTemplate = mailTemplate;
	}

	

	public Integer getSendApplicationToMail() {
		return sendApplicationToMail;
	}



	public void setSendApplicationToMail(Integer sendApplicationToMail) {
		this.sendApplicationToMail = sendApplicationToMail;
	}



	public String getDefaultLocation() {
		return this.defaultLocation;
	}

	public void setDefaultLocation(String defaultLocation) {
		this.defaultLocation = defaultLocation;
	}

	public Integer getDefaultGuiderId() {
		return this.defaultGuiderId;
	}

	public void setDefaultGuiderId(Integer defaultGuiderId) {
		this.defaultGuiderId = defaultGuiderId;
	}

	public Integer getDefaultTechnicianId() {
		return this.defaultTechnicianId;
	}

	public void setDefaultTechnicianId(Integer defaultTechnicianId) {
		this.defaultTechnicianId = defaultTechnicianId;
	}

	public Integer getDefaultMmstemplateId() {
		return this.defaultMmstemplateId;
	}

	public void setDefaultMmstemplateId(Integer defaultMmstemplateId) {
		this.defaultMmstemplateId = defaultMmstemplateId;
	}

	public String getSendPressbook() {
		return this.sendPressbook;
	}

	public void setSendPressbook(String sendPressbook) {
		this.sendPressbook = sendPressbook;
	}

	
	
	public Integer getIsAttachment() {
		return isAttachment;
	}



	public void setIsAttachment(Integer isAttachment) {
		this.isAttachment = isAttachment;
	}



	public Manager getManager() {
		return manager;
	}



	public void setManager(Manager manager) {
		this.manager = manager;
	}



	public String getAttachmentUri() {
		return this.attachmentUri;
	}

	public void setAttachmentUri(String attachmentUri) {
		this.attachmentUri = attachmentUri;
	}



	public Integer getDefaultMailtemplateId() {
		return defaultMailtemplateId;
	}



	public void setDefaultMailtemplateId(Integer defaultMailtemplateId) {
		this.defaultMailtemplateId = defaultMailtemplateId;
	}

	

}