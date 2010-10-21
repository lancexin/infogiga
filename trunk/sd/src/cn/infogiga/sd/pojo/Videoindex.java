package cn.infogiga.sd.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Videoindex entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Videoindex implements java.io.Serializable {

	// Fields

	private Integer indexId;
	private Videoindex videoindex;
	private Video video;
	private Integer videomenuId;
	private Set videoindexes = new HashSet(0);

	// Constructors

	/** default constructor */
	public Videoindex() {
	}

	/** minimal constructor */
	public Videoindex(Videoindex videoindex) {
		this.videoindex = videoindex;
	}

	/** full constructor */
	public Videoindex(Videoindex videoindex, Video video, Integer videomenuId,
			Set videoindexes) {
		this.videoindex = videoindex;
		this.video = video;
		this.videomenuId = videomenuId;
		this.videoindexes = videoindexes;
	}

	// Property accessors

	public Integer getIndexId() {
		return this.indexId;
	}

	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}

	public Videoindex getVideoindex() {
		return this.videoindex;
	}

	public void setVideoindex(Videoindex videoindex) {
		this.videoindex = videoindex;
	}

	public Video getVideo() {
		return this.video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Integer getVideomenuId() {
		return this.videomenuId;
	}

	public void setVideomenuId(Integer videomenuId) {
		this.videomenuId = videomenuId;
	}

	public Set getVideoindexes() {
		return this.videoindexes;
	}

	public void setVideoindexes(Set videoindexes) {
		this.videoindexes = videoindexes;
	}

}