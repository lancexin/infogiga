package cn.infogiga.sd.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Download entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Download implements java.io.Serializable {

	// Fields

	private Integer downloadId;
	private String url;
	private String downloadCode;
	private Set musicindexes = new HashSet(0);
	private Set videoindexes = new HashSet(0);
	private Set softindexes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Download() {
	}

	/** full constructor */
	public Download(String url, String downloadCode, Set musicindexes,
			Set videoindexes, Set softindexes) {
		this.url = url;
		this.downloadCode = downloadCode;
		this.musicindexes = musicindexes;
		this.videoindexes = videoindexes;
		this.softindexes = softindexes;
	}

	// Property accessors

	public Integer getDownloadId() {
		return this.downloadId;
	}

	public void setDownloadId(Integer downloadId) {
		this.downloadId = downloadId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDownloadCode() {
		return this.downloadCode;
	}

	public void setDownloadCode(String downloadCode) {
		this.downloadCode = downloadCode;
	}

	public Set getMusicindexes() {
		return this.musicindexes;
	}

	public void setMusicindexes(Set musicindexes) {
		this.musicindexes = musicindexes;
	}

	public Set getVideoindexes() {
		return this.videoindexes;
	}

	public void setVideoindexes(Set videoindexes) {
		this.videoindexes = videoindexes;
	}

	public Set getSoftindexes() {
		return this.softindexes;
	}

	public void setSoftindexes(Set softindexes) {
		this.softindexes = softindexes;
	}

}