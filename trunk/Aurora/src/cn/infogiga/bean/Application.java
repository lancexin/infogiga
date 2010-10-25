package cn.infogiga.bean;

import java.util.Date;

/**
 * Application entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Application extends cn.infogiga.bean.BaseBean implements
		java.io.Serializable {

	// Fields

	private Integer applicationId;
	private Customer customer;
	private Manager manager;
	private String reason;
	private Integer status;
	private Date createTime;

	// Constructors

	/** default constructor */
	public Application() {
	}

	/** minimal constructor */
	public Application(Customer customer, Integer status) {
		this.customer = customer;
		this.status = status;
	}

	/** full constructor */
	public Application(Customer customer, Manager manager, String reason,
			Integer status, Date createTime) {
		this.customer = customer;
		this.manager = manager;
		this.reason = reason;
		this.status = status;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Manager getManager() {
		return this.manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}