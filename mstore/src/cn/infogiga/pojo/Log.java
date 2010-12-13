package cn.infogiga.pojo;

import java.util.Date;

/**
 * Log entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Log implements java.io.Serializable {

	// Fields

	private Integer id;
	private Logtype logtype;
	private String name;
	private String do_;
	private Date logTime;

	// Constructors

	/** default constructor */
	public Log() {
	}

	/** full constructor */
	public Log(Logtype logtype, String name, String do_, Date logTime) {
		this.logtype = logtype;
		this.name = name;
		this.do_ = do_;
		this.logTime = logTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Logtype getLogtype() {
		return this.logtype;
	}

	public void setLogtype(Logtype logtype) {
		this.logtype = logtype;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDo_() {
		return this.do_;
	}

	public void setDo_(String do_) {
		this.do_ = do_;
	}

	public Date getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

}