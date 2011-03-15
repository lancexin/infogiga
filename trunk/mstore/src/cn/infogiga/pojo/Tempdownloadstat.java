package cn.infogiga.pojo;

import java.util.Date;

/**
 * Tempdownloadstat entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tempdownloadstat implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer statId;
	private String code;

	// Constructors

	/** default constructor */
	public Tempdownloadstat() {
	}

	/** full constructor */
	public Tempdownloadstat(Integer statId,String code) {
		this.code = code;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatId() {
		return statId;
	}

	public void setStatId(Integer statId) {
		this.statId = statId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	

}