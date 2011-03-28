package cn.infogiga.szqb.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import cindy.page.beanutils.Sync;

@XStreamAlias("periodical")
public class JsonPeriodical {
	@Sync(value = "id")
	@XStreamAlias("ID")
	String periodicalId;
	
	@Sync(value = "shortName")
	@XStreamAlias("shortName")
	String shortName;
	
	@Sync(value = "number")
	@XStreamAlias("number")
	String number;
	
	@Sync(value = "reader.id")
	@XStreamAlias("readerID")
	String readerId;
	
	@Sync(value = "reader.readerName")
	@XStreamAlias("readerName")
	String readerName;
	
	@Sync(value = "reader.shortName")
	@XStreamAlias("readerShortName")
	String readerShortName;
	
	@Sync(value = "indexPic")
	@XStreamAlias("indexPic")
	String indexPic;
	
	@Sync(value = "addTime")
	@XStreamAlias("addTime")
	String addTime;
	
	@Sync(value = "publishTime")
	@XStreamAlias("publishTime")
	String publishTime;
	
	@Sync(value = "attachmentUrl")
	@XStreamAlias("attachmentUrl")
	String attachmentUrl;
	
	@Sync(value = "tabloidPic")
	@XStreamAlias("tabloidPic")
	String tabloidPic;
	
	@Sync(value = "attachmentMd5")
	@XStreamAlias("attachmentMd5")
	String attachmentMd5;
	
	@Sync(value = "status")
	@XStreamAlias("status")
	String status;

	public String getPeriodicalId() {
		return periodicalId;
	}

	public void setPeriodicalId(String periodicalId) {
		this.periodicalId = periodicalId;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getReaderId() {
		return readerId;
	}

	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}

	public String getReaderName() {
		return readerName;
	}

	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}

	public String getReaderShortName() {
		return readerShortName;
	}

	public void setReaderShortName(String readerShortName) {
		this.readerShortName = readerShortName;
	}

	public String getIndexPic() {
		return indexPic;
	}

	public void setIndexPic(String indexPic) {
		this.indexPic = indexPic;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getAttachmentUrl() {
		return attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	public String getTabloidPic() {
		return tabloidPic;
	}

	public void setTabloidPic(String tabloidPic) {
		this.tabloidPic = tabloidPic;
	}

	public String getAttachmentMd5() {
		return attachmentMd5;
	}

	public void setAttachmentMd5(String attachmentMd5) {
		this.attachmentMd5 = attachmentMd5;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
