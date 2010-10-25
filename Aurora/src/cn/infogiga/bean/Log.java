package cn.infogiga.bean;

import java.util.Date;

/**
 * Log entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Log extends cn.infogiga.bean.BaseBean implements
		java.io.Serializable {

	// Fields

	private Integer logId;
	private Integer userId;
	private String levels;
	private String event;
	private Date createTime;

	// Constructors

	/** default constructor */
	public Log() {
	}

	/** full constructor */
	public Log(Integer userId, String levels, String event, Date createTime) {
		this.userId = userId;
		this.levels = levels;
		this.event = event;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getLogId() {
		return this.logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLevels() {
		return this.levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}