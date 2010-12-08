package cn.infogiga.sd.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Phonetype entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Phonetype implements java.io.Serializable {

	// Fields

	private Integer id;
	private Phonebrandcategory phonebrandcategory;
	private Phoneplatform phoneplatform;
	private Phonearray phonearray;
	private String phonetypeName;
	private Integer status;
	private String pic;
	private Set softdownloadstats = new HashSet(0);

	// Constructors

	/** default constructor */
	public Phonetype() {
	}

	/** full constructor */
	public Phonetype(Phonebrandcategory phonebrandcategory,
			Phoneplatform phoneplatform, Phonearray phonearray,
			String phonetypeName, Integer status, String pic,
			Set softdownloadstats) {
		this.phonebrandcategory = phonebrandcategory;
		this.phoneplatform = phoneplatform;
		this.phonearray = phonearray;
		this.phonetypeName = phonetypeName;
		this.status = status;
		this.pic = pic;
		this.softdownloadstats = softdownloadstats;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Phonebrandcategory getPhonebrandcategory() {
		return this.phonebrandcategory;
	}

	public void setPhonebrandcategory(Phonebrandcategory phonebrandcategory) {
		this.phonebrandcategory = phonebrandcategory;
	}

	public Phoneplatform getPhoneplatform() {
		return this.phoneplatform;
	}

	public void setPhoneplatform(Phoneplatform phoneplatform) {
		this.phoneplatform = phoneplatform;
	}

	public Phonearray getPhonearray() {
		return this.phonearray;
	}

	public void setPhonearray(Phonearray phonearray) {
		this.phonearray = phonearray;
	}

	public String getPhonetypeName() {
		return this.phonetypeName;
	}

	public void setPhonetypeName(String phonetypeName) {
		this.phonetypeName = phonetypeName;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Set getSoftdownloadstats() {
		return this.softdownloadstats;
	}

	public void setSoftdownloadstats(Set softdownloadstats) {
		this.softdownloadstats = softdownloadstats;
	}

}