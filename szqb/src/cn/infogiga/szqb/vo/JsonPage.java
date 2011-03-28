package cn.infogiga.szqb.vo;

import cindy.page.beanutils.Sync;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("page")
public class JsonPage {
	@Sync(value = "id")
	@XStreamAlias("ID")
	String pageId;
	
	@Sync(value = "shortName")
	@XStreamAlias("shortName")
	String shortName;
	
	@Sync(value = "addTime")
	@XStreamAlias("addTime")
	String addTime;
	
	@Sync(value = "imgUrl")
	@XStreamAlias("imgUrl")
	String imgUrl;
	
	@Sync(value = "originalUrl")
	@XStreamAlias("originalUrl")
	String originalUrl;
	
	@Sync(value = "imgMd5")
	@XStreamAlias("imgMd5")
	String imgMd5;
	
	@Sync(value = "status")
	@XStreamAlias("status")
	String status;
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getOriginalUrl() {
		return originalUrl;
	}
	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	public String getImgMd5() {
		return imgMd5;
	}
	public void setImgMd5(String imgMd5) {
		this.imgMd5 = imgMd5;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
