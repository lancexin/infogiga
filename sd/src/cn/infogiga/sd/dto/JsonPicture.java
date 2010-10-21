package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonPicture {
	@Sync(value = "pictureId")
	private String pictureId;
	
	@Sync(value = "code")
	private String code;
	
	@Sync(value = "url")
	private String url;
	
	@Sync(value = "width")
	private String width;
	
	@Sync(value = "height")
	private String height;
	
	@Sync(value = "addTime")
	private String addTime;
	
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getPictureId() {
		return pictureId;
	}
	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
}
