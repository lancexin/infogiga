package cn.infogiga.bean;

import java.util.Date;

/**
 * Comment entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Comment extends cn.infogiga.bean.BaseBean implements
		java.io.Serializable {

	// Fields

	private Integer commentId;
	private Customer customer;
	private String content;
	private Date receiveTime;
	private String ip;

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** minimal constructor */
	public Comment(Customer customer, String content, Date receiveTime) {
		this.customer = customer;
		this.content = content;
		this.receiveTime = receiveTime;
	}

	/** full constructor */
	public Comment(Customer customer, String content, Date receiveTime,
			String ip) {
		this.customer = customer;
		this.content = content;
		this.receiveTime = receiveTime;
		this.ip = ip;
	}

	// Property accessors

	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReceiveTime() {
		return this.receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}