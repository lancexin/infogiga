package cn.infogiga.sd.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Softattachment entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Softattachment implements java.io.Serializable {

	// Fields

	private Integer attachmentId;
	private Soft soft;
	private Download download;
	private String attachmentName;
	private Set softindexes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Softattachment() {
	}

	/** full constructor */
	public Softattachment(Soft soft, Download download, String attachmentName,
			Set softindexes) {
		this.soft = soft;
		this.download = download;
		this.attachmentName = attachmentName;
		this.softindexes = softindexes;
	}

	// Property accessors

	public Integer getAttachmentId() {
		return this.attachmentId;
	}

	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}

	public Soft getSoft() {
		return this.soft;
	}

	public void setSoft(Soft soft) {
		this.soft = soft;
	}

	public Download getDownload() {
		return this.download;
	}

	public void setDownload(Download download) {
		this.download = download;
	}


	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public Set getSoftindexes() {
		return this.softindexes;
	}

	public void setSoftindexes(Set softindexes) {
		this.softindexes = softindexes;
	}

}