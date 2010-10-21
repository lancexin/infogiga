package cn.infogiga.sd.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Phonebrand entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Phonebrand implements java.io.Serializable {

	// Fields

	private Integer phonebrandId;
	private String phonebrandName;
	private Integer status;
	private Set phonetypes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Phonebrand() {
	}
	
	public Phonebrand(Integer phonebrandId) {
		this.phonebrandId = phonebrandId;
	}

	/** full constructor */
	public Phonebrand(String phonebrandName, Integer status, Set phonetypes) {
		this.phonebrandName = phonebrandName;
		this.status = status;
		this.phonetypes = phonetypes;
	}

	// Property accessors

	public Integer getPhonebrandId() {
		return this.phonebrandId;
	}

	public void setPhonebrandId(Integer phonebrandId) {
		this.phonebrandId = phonebrandId;
	}

	public String getPhonebrandName() {
		return this.phonebrandName;
	}

	public void setPhonebrandName(String phonebrandName) {
		this.phonebrandName = phonebrandName;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set getPhonetypes() {
		return this.phonetypes;
	}

	public void setPhonetypes(Set phonetypes) {
		this.phonetypes = phonetypes;
	}

}