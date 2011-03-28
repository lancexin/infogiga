package cn.infogiga.szqb.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Periodical entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Periodical implements java.io.Serializable {

	// Fields

	private Integer id;
	private Reader reader;
	private Integer number;
	private String shortName;
	private String periodicalName;
	private String indexPic;
	private Date addTime;
	private Date publishTime;
	private String attachmentUrl;
	private String tabloidPic;
	/**
	 * 0 未完成            ----- 表示还没有上传pdf文件
	 * 1 图片转换中         ----- pdf文件已经上传完成,正在转换
	 * 2 待审核     ----- pdf文件已经转换完成
	 * 3 已发布     ----- 期刊已经发布
	 * 4 发布失败   ----- 文件转换期间出现错误
	 */
	private Integer status;
	private String attachmentMd5;
	private Set pages = new HashSet(0);

	// Constructors

	/** default constructor */
	public Periodical() {
	}

	/** full constructor */
	public Periodical(Reader reader, Integer number, String shortName,
			String periodicalName, String indexPic, Date addTime,
			Date publishTime, String attachmentUrl, String tabloidPic,
			Integer status, String attachmentMd5, Set pages) {
		this.reader = reader;
		this.number = number;
		this.shortName = shortName;
		this.periodicalName = periodicalName;
		this.indexPic = indexPic;
		this.addTime = addTime;
		this.publishTime = publishTime;
		this.attachmentUrl = attachmentUrl;
		this.tabloidPic = tabloidPic;
		this.status = status;
		this.attachmentMd5 = attachmentMd5;
		this.pages = pages;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Reader getReader() {
		return this.reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getPeriodicalName() {
		return this.periodicalName;
	}

	public void setPeriodicalName(String periodicalName) {
		this.periodicalName = periodicalName;
	}

	public String getIndexPic() {
		return this.indexPic;
	}

	public void setIndexPic(String indexPic) {
		this.indexPic = indexPic;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getAttachmentUrl() {
		return this.attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	public String getTabloidPic() {
		return this.tabloidPic;
	}

	public void setTabloidPic(String tabloidPic) {
		this.tabloidPic = tabloidPic;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAttachmentMd5() {
		return this.attachmentMd5;
	}

	public void setAttachmentMd5(String attachmentMd5) {
		this.attachmentMd5 = attachmentMd5;
	}

	public Set getPages() {
		return this.pages;
	}

	public void setPages(Set pages) {
		this.pages = pages;
	}

}