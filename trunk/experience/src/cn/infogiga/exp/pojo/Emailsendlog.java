package cn.infogiga.exp.pojo;

import java.util.Date;

/**
 * Emailsendlog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Emailsendlog implements java.io.Serializable,cindy.web.pojo.POJO {

	// Fields

	private Integer sendId;
	private Emailtemplete emailtemplete;
	private Date sendTime;
	private String subject;
	private String mailto;

	// Constructors

	/** default constructor */
	public Emailsendlog() {
	}

	/** full constructor */
	public Emailsendlog(Emailtemplete emailtemplete, Date sendTime,
			String subject, String mailto) {
		this.emailtemplete = emailtemplete;
		this.sendTime = sendTime;
		this.subject = subject;
		this.mailto = mailto;
	}

	// Property accessors

	public Integer getSendId() {
		return this.sendId;
	}

	public void setSendId(Integer sendId) {
		this.sendId = sendId;
	}

	public Emailtemplete getEmailtemplete() {
		return this.emailtemplete;
	}

	public void setEmailtemplete(Emailtemplete emailtemplete) {
		this.emailtemplete = emailtemplete;
	}

	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMailto() {
		return this.mailto;
	}

	public void setMailto(String mailto) {
		this.mailto = mailto;
	}

}