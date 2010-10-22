package cn.infogiga.exp.pojo;

/**
 * AbstractPhonebrand entity provides the base persistence definition of the
 * Phonebrand entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Phonebrand implements java.io.Serializable,cindy.web.pojo.POJO {

	// Fields

	private Integer phonebrandId;
	private String phonebrandName;

	// Constructors

	/** default constructor */
	public Phonebrand() {
	}

	/** full constructor */
	public Phonebrand(String phonebrandName) {
		this.phonebrandName = phonebrandName;
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

}