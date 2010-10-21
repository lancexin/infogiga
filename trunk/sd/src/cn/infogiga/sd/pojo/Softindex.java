package cn.infogiga.sd.pojo;

/**
 * Softindex entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Softindex implements java.io.Serializable {

	// Fields

	private Integer indexId;
	private Phonetype phonetype;
	private Softattachment softattachment;

	// Constructors

	/** default constructor */
	public Softindex() {
	}

	/** full constructor */
	public Softindex(Phonetype phonetype, Softattachment softattachment) {
		this.phonetype = phonetype;
		this.softattachment = softattachment;
	}

	// Property accessors

	public Integer getIndexId() {
		return this.indexId;
	}

	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}

	public Phonetype getPhonetype() {
		return this.phonetype;
	}

	public void setPhonetype(Phonetype phonetype) {
		this.phonetype = phonetype;
	}

	public Softattachment getSoftattachment() {
		return this.softattachment;
	}

	public void setSoftattachment(Softattachment softattachment) {
		this.softattachment = softattachment;
	}

}