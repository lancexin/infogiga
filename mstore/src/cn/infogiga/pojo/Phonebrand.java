package cn.infogiga.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Phonebrand entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Phonebrand implements java.io.Serializable {

	// Fields

	private Integer id;
	private String phonebrandName;
	private String url;
	private Set phonebrandcategories = new HashSet(0);

	// Constructors

	/** default constructor */
	public Phonebrand() {
	}

	/** full constructor */
	public Phonebrand(String phonebrandName, String url,
			Set phonebrandcategories) {
		this.phonebrandName = phonebrandName;
		this.url = url;
		this.phonebrandcategories = phonebrandcategories;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhonebrandName() {
		return this.phonebrandName;
	}

	public void setPhonebrandName(String phonebrandName) {
		this.phonebrandName = phonebrandName;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set getPhonebrandcategories() {
		return this.phonebrandcategories;
	}

	public void setPhonebrandcategories(Set phonebrandcategories) {
		this.phonebrandcategories = phonebrandcategories;
	}

}