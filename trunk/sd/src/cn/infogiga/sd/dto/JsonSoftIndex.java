package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonSoftIndex {
	@Sync(value = "indexId")
	String indexId;
	
	@Sync(value = "phonetype.phonetypeId")
	String phonetypeId;
	
	@Sync(value = "phonetype.phonetypeName")
	String phonetypeName;
	
	@Sync(value = "softattachment.attachmentId")
	String attachmentId;

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public String getPhonetypeId() {
		return phonetypeId;
	}

	public void setPhonetypeId(String phonetypeId) {
		this.phonetypeId = phonetypeId;
	}

	public String getPhonetypeName() {
		return phonetypeName;
	}

	public void setPhonetypeName(String phonetypeName) {
		this.phonetypeName = phonetypeName;
	}
}
