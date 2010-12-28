package cn.infogiga.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Soft entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Soft implements java.io.Serializable {

	// Fields

	private Integer id;
	private Download download;
	private String softName;
	private String description;
	private Date addTime;
	private Integer status;
	private String icon;
	private String pic1;
	private String pic2;
	private String pic3;
	private String pic4;
	private String pic5;
	private String shortName;
	private String softCode;
	private Set softindexes = new HashSet(0);
	private Set softdownloadstats = new HashSet(0);
	private Set attachments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Soft() {
	}

	/** full constructor */
	public Soft(Download download, String softName, String description,
			Date addTime, Integer status, String icon, String pic1,
			String pic2, String pic3, String pic4, String pic5,
			String shortName, String softCode, Set softindexes,
			Set softdownloadstats, Set attachments) {
		this.download = download;
		this.softName = softName;
		this.description = description;
		this.addTime = addTime;
		this.status = status;
		this.icon = icon;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.pic3 = pic3;
		this.pic4 = pic4;
		this.pic5 = pic5;
		this.shortName = shortName;
		this.softCode = softCode;
		this.softindexes = softindexes;
		this.softdownloadstats = softdownloadstats;
		this.attachments = attachments;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Download getDownload() {
		return this.download;
	}

	public void setDownload(Download download) {
		this.download = download;
	}

	public String getSoftName() {
		return this.softName;
	}

	public void setSoftName(String softName) {
		
		this.softName = softName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		if(description == null){
			this.description = description;
			return;
		}
		description = description.replaceAll("\"", "");
		description = description.replaceAll("\'", "");
		description = description.replaceAll(":", "");
		description = description.replaceAll("<", "");
		description = description.replaceAll(">", "");
		description = description.replaceAll("\r\n", "");
		this.description = description;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPic1() {
		return this.pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return this.pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public String getPic3() {
		return this.pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}

	public String getPic4() {
		return this.pic4;
	}

	public void setPic4(String pic4) {
		this.pic4 = pic4;
	}

	public String getPic5() {
		return this.pic5;
	}

	public void setPic5(String pic5) {
		this.pic5 = pic5;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getSoftCode() {
		return this.softCode;
	}

	public void setSoftCode(String softCode) {
		this.softCode = softCode;
	}

	public Set getSoftindexes() {
		return this.softindexes;
	}

	public void setSoftindexes(Set softindexes) {
		this.softindexes = softindexes;
	}

	public Set getSoftdownloadstats() {
		return this.softdownloadstats;
	}

	public void setSoftdownloadstats(Set softdownloadstats) {
		this.softdownloadstats = softdownloadstats;
	}

	public Set getAttachments() {
		return this.attachments;
	}

	public void setAttachments(Set attachments) {
		this.attachments = attachments;
	}

}