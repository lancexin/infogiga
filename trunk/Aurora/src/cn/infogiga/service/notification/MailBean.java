package cn.infogiga.service.notification;

import java.io.Serializable;

public class MailBean implements Serializable {

	private static final long serialVersionUID = -2867433198256319652L;
	
	private String smtpHost;
	private String userName;
	private String password;
	private String from;
	private String to;
	private String subject; //主题
	private String msg; //内容
	private String fileName; //附件文件名
	public MailBean(String smtpHost, String userName, String password,
			String from, String to, String subject, String msg, String fileName) {
		super();
		this.smtpHost = smtpHost;
		this.userName = userName;
		this.password = password;
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.msg = msg;
		this.fileName = fileName;
	}
	public MailBean() {
		super();
	}
	public String getSmtpHost() {
		return smtpHost;
	}
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
