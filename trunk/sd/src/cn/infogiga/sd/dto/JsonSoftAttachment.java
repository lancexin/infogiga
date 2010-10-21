package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonSoftAttachment {
	@Sync(value = "attachmentId")
	String attachmentId;
	
	@Sync(value = "attachmentName")
	String attachmentName;
	
	@Sync(value = "soft.softId")
	String softId;
	
	@Sync(value = "soft.softName")
	String softName;
	
	@Sync(value = "download.downloadId")
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
