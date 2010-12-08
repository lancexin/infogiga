package cn.infogiga.sd.pojo;

/**
 * Attachandarray entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Attachandarray implements java.io.Serializable {

	// Fields

	private Integer id;
	private Attachment attachment;
	private Phonearray phonearray;

	// Constructors

	/** default constructor */
	public Attachandarray() {
	}

	/** full constructor */
	public Attachandarray(Attachment attachment, Phonearray phonearray) {
		this.attachment = attachment;
		this.phonearray = phonearray;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Attachment getAttachment() {
		return this.attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public Phonearray getPhonearray() {
		return this.phonearray;
	}

	public void setPhonearray(Phonearray phonearray) {
		this.phonearray = phonearray;
	}

}