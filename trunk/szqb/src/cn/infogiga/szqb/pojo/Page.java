package cn.infogiga.szqb.pojo;

import java.util.Date;

/**
 * Page entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Page implements java.io.Serializable {

	// Fields

	private Integer id;
	private Periodical periodical;
	private String shortName;
	private String imgUrl;
	private String originalUrl;
	private Date addTime;
	private String imgMd5;
	private Integer status;

	// Constructors

	/** default constructor */
	public Page() {
	}

	/** full constructor */
	public Page(Periodical periodical, String shortName, String imgUrl,
			String originalUrl, Date addTime, String imgMd5, Integer status) {
		this.periodical = periodical;
		this.shortName = shortName;
		this.imgUrl = imgUrl;
		this.originalUrl = originalUrl;
		this.addTime = addTime;
		this.imgMd5 = imgMd5;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Periodical getPeriodical() {
		return this.periodical;
	}

	public void setPeriodical(Periodical periodical) {
		this.periodical = periodical;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getOriginalUrl() {
		return this.originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getImgMd5() {
		return this.imgMd5;
	}

	public void setImgMd5(String imgMd5) {
		this.imgMd5 = imgMd5;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}