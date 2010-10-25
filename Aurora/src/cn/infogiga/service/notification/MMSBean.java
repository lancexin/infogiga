package cn.infogiga.service.notification;

import java.io.Serializable;

import sun.util.logging.resources.logging;

public class MMSBean implements Serializable,Cloneable {

	private static final long serialVersionUID = -2867433198256319652L;
	private String templateName;
	private String id;
	private String qrcode;
	private String phoneNumber;
	private String content;
	
	public MMSBean() {}
	/**
	 * @param templateName  模板的名字
	 * @param id			id号
	 * @param qrcode		二维码
	 * @param phoneNumber	手机号
	 * @param content		内容
	 */
	public MMSBean(String templateName, String id, String qrcode,
			String phoneNumber, String content) {
		this.templateName = templateName;
		this.id = id;
		this.qrcode = qrcode;
		this.phoneNumber = phoneNumber;
		this.content = content;
	}
	
	public Object clone() {
		Object object = null;
		try {
			object = super.clone();
		} catch (CloneNotSupportedException e) {			
			e.printStackTrace();
		}
		return object;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
