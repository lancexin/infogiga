package cn.infogiga.sd.pojo;

import java.util.Date;

/**
 * Picture entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Picture implements java.io.Serializable {

	// Fields

	private Integer pictureId;
	private String url;
	private String code;
	private Integer width;
	private Integer height;
	private Date addTime;

	// Constructors

	/** default constructor */
	public Picture() {
	}
	
	public Picture(Integer pictureId) {
			this.pictureId = pictureId;
	}

	/** full constructor */
	public Picture(String url, String code, Integer width, Integer height,
			Date addTime) {
		this.url = url;
		this.code = code;
		this.width = width;
		this.height = height;
		this.addTime = addTime;
	}

	// Property accessors

	public Integer getPictureId() {
		return this.pictureId;
	}

	public void setPictureId(Integer pictureId) {
		this.pictureId = pictureId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getWidth() {
		return this.width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return this.height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}