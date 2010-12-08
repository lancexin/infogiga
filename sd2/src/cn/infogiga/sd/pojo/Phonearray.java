package cn.infogiga.sd.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Phonearray entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Phonearray implements java.io.Serializable {

	// Fields

	private Integer id;
	private String phonearrayName;
	private Set phonetypes = new HashSet(0);
	private Set attachandarraies = new HashSet(0);
	private Set attachments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Phonearray() {
	}

	/** full constructor */
	public Phonearray(String phonearrayName, Set phonetypes,
			Set attachandarraies, Set attachments) {
		this.phonearrayName = phonearrayName;
		this.phonetypes = phonetypes;
		this.attachandarraies = attachandarraies;
		this.attachments = attachments;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhonearrayName() {
		return this.phonearrayName;
	}

	public void setPhonearrayName(String phonearrayName) {
		this.phonearrayName = phonearrayName;
	}

	public Set getPhonetypes() {
		return this.phonetypes;
	}

	public void setPhonetypes(Set phonetypes) {
		this.phonetypes = phonetypes;
	}

	public Set getAttachandarraies() {
		return this.attachandarraies;
	}

	public void setAttachandarraies(Set attachandarraies) {
		this.attachandarraies = attachandarraies;
	}

	public Set getAttachments() {
		return this.attachments;
	}

	public void setAttachments(Set attachments) {
		this.attachments = attachments;
	}

}