package cn.infogiga.sd.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Music entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Music implements java.io.Serializable {

	// Fields

	private Integer musicId;
	private Download download;
	private Musicman musicman;
	private String musicName;
	private String description;
	private Date addTime;
	private Integer status;
	private Integer downloadCount;
	private Set musicindexes = new HashSet(0);
	private Set musicdownloadstats = new HashSet(0);

	// Constructors

	/** default constructor */
	public Music() {
	}

	/** full constructor */
	public Music(Download download, Musicman musicman, String musicName,
			String description, Date addTime, Integer status,
			Integer downloadCount, Set musicindexes, Set musicdownloadstats) {
		this.download = download;
		this.musicman = musicman;
		this.musicName = musicName;
		this.description = description;
		this.addTime = addTime;
		this.status = status;
		this.downloadCount = downloadCount;
		this.musicindexes = musicindexes;
		this.musicdownloadstats = musicdownloadstats;
	}

	// Property accessors

	public Integer getMusicId() {
		return this.musicId;
	}

	public void setMusicId(Integer musicId) {
		this.musicId = musicId;
	}

	public Download getDownload() {
		return this.download;
	}

	public void setDownload(Download download) {
		this.download = download;
	}

	public Musicman getMusicman() {
		return this.musicman;
	}

	public void setMusicman(Musicman musicman) {
		this.musicman = musicman;
	}

	public String getMusicName() {
		return this.musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
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

	public Integer getDownloadCount() {
		return this.downloadCount;
	}

	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

	public Set getMusicindexes() {
		return this.musicindexes;
	}

	public void setMusicindexes(Set musicindexes) {
		this.musicindexes = musicindexes;
	}

	public Set getMusicdownloadstats() {
		return this.musicdownloadstats;
	}

	public void setMusicdownloadstats(Set musicdownloadstats) {
		this.musicdownloadstats = musicdownloadstats;
	}

}