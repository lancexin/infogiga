package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonSoftAttachment {
	@Sync(value = "id")
	String attachmentId;
	
	@Sync(value = "name")
	String attachmentName;
	
	@Sync(value = "soft.id")
	String softId;
	
	@Sync(value = "soft.softName")
	String softName;
	
	@Sync(value = "soft.download.id")
	String downloadId;

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		
		this.attachmentId = attachmentId;
	}



	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getSoftId() {
		return softId;
	}

	public void setSoftId(String softId) {
		this.softId = softId;
	}

	public String getSoftName() {
		return softName;
	}

	public void setSoftName(String softName) {
		this.softName = softName;
	}

	public String getDownloadId() {
		return downloadId;
	}

	public void setDownloadId(String downloadId) {
		this.downloadId = downloadId;
	}
}
