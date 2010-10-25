package cn.infogiga.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Tmp entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tmp extends cn.infogiga.bean.BaseBean implements
		java.io.Serializable {

	// Fields

	private Integer tmpId;
	private String tmpName;
	private String type;
	private String isDefault;

	// Constructors

	public Tmp(Integer tmpId, String tmpName, String type, String isDefault) {
		super();
		this.tmpId = tmpId;
		this.tmpName = tmpName;
		this.type = type;
		this.isDefault = isDefault;
	}

	/** default constructor */
	public Tmp() {
	}

	public Integer getTmpId() {
		return tmpId;
	}

	public void setTmpId(Integer tmpId) {
		this.tmpId = tmpId;
	}

	public String getTmpName() {
		return tmpName;
	}

	public void setTmpName(String tmpName) {
		this.tmpName = tmpName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
}