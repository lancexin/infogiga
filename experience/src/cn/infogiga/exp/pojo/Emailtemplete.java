package cn.infogiga.exp.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Emailtemplete entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Emailtemplete implements java.io.Serializable,cindy.web.pojo.POJO {

	// Fields

	private Integer templeteId;
	private String templeteName;
	private String templeteView;
	private Integer status;
	private Set emailservices = new HashSet(0);

	// Constructors

	/** default constructor */
	public Emailtemplete() {
	}

	/** full constructor */
	public Emailtemplete(String templeteName, String templeteView,
			Integer status, Set emailservices) {
		this.templeteName = templeteName;
		this.templeteView = templeteView;
		this.status = status;
		this.emailservices = emailservices;
	}

	// Property accessors

	public Integer getTempleteId() {
		return this.templeteId;
	}

	public void setTempleteId(Integer templeteId) {
		this.templeteId = templeteId;
	}

	public String getTempleteName() {
		return this.templeteName;
	}

	public void setTempleteName(String templeteName) {
		this.templeteName = templeteName;
	}

	public String getTempleteView() {
		return this.templeteView;
	}

	public void setTempleteView(String templeteView) {
		this.templeteView = templeteView;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set getEmailservices() {
		return this.emailservices;
	}

	public void setEmailservices(Set emailservices) {
		this.emailservices = emailservices;
	}

}