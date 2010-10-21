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

	private Integer phonetypeId;
	private Phonebrand phonebrand;
	private Phoneplatform phoneplatform;
	private String phonetypeName;
	private Integer status;
	private String pic;
	private Set softindexes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Phonetype() {
	}

	/** full constructor */
	public Phonetype(Phonebrand phonebrand, Phoneplatform phoneplatform,
			String phonetypeName, Integer status, String pic, Set softindexes) {
		this.phonebrand = phonebrand;
		this.phoneplatform = phoneplatform;
		this.phonetypeName = phonetypeName;
		this.status = status;
		this.pic = pic;
		this.softindexes = softindexes;
	}

	// Property accessors

	public Integer getPhonetypeId() {
		return this.phonetypeId;
	}

	public void setPhonetypeId(Integer phonetypeId) {
		this.phonetypeId = phonetypeId;
	}

	public Phonebrand getPhonebrand() {
		return this.phonebrand;
	}

	public void setPhonebrand(Phonebrand phonebrand) {
		this.phonebrand = phonebrand;
	}

	public Phoneplatform getPhoneplatform() {
		return this.phoneplatform;
	}

	public void setPhoneplatform(Phoneplatform phoneplatform) {
		this.phoneplatform = phoneplatform;
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

	public Set getSoftindexes() {
		return this.softindexes;
	}

	public void setSoftindexes(Set softindexes) {
		this.softindexes = softindexes;
	}

}