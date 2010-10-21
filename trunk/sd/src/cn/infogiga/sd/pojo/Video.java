package cn.infogiga.sd.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Video entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Video implements java.io.Serializable {

	// Fields

	private Integer videoId;
	private Download download;
	private String videoName;
	private String description;
	private Date addTime;
	private Integer status;
	private String pic1;
	private String pic2;
	private Integer downloadCount;
	private Set videodownloadstats = new HashSet(0);
	private Set videoindexes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Video() {
	}

	/** full constructor */
	public Video(Download download, String videoName, String description,
			Date addTime, Integer status, String pic1, String pic2,
			Integer downloadCount, Set videodownloadstats, Set videoindexes) {
		this.download = download;
		this.videoName = videoName;
		this.description = description;
		this.addTime = addTime;
		this.status = status;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.downloadCount = downloadCount;
		this.videodownloadstats = videodownloadstats;
		this.videoindexes = videoindexes;
	}

	// Property accessors

	public Integer getVideoId() {
		return this.videoId;
	}

	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}

	public Download getDownload() {
		return this.download;
	}

	public void setDownload(Download download) {
		this.download = download;
	}

	public String getVideoName() {
		return this.videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
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

	public Integer getDownloadCount() {
		return this.downloadCount;
	}

	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

	public Set getVideodownloadstats() {
		return this.videodownloadstats;
	}

	public void setVideodownloadstats(Set videodownloadstats) {
		this.videodownloadstats = videodownloadstats;
	}

	public Set getVideoindexes() {
		return this.videoindexes;
	}

	public void setVideoindexes(Set videoindexes) {
		this.videoindexes = videoindexes;
	}

}