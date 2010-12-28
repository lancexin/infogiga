package cn.infogiga.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Phonebrandcategory entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Phonebrandcategory implements java.io.Serializable {

	// Fields

	private Integer id;
	private Phonebrand phonebrand;
	private String categoryName;
	private String pic;
	private Set phonetypes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Phonebrandcategory() {
	}

	/** full constructor */
	public Phonebrandcategory(Phonebrand phonebrand, String categoryName,
			String pic, Set phonetypes) {
		this.phonebrand = phonebrand;
		this.categoryName = categoryName;
		this.pic = pic;
		this.phonetypes = phonetypes;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Phonebrand getPhonebrand() {
		return this.phonebrand;
	}

	public void setPhonebrand(Phonebrand phonebrand) {
		this.phonebrand = phonebrand;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Set getPhonetypes() {
		return this.phonetypes;
	}

	public void setPhonetypes(Set phonetypes) {
		this.phonetypes = phonetypes;
	}

}