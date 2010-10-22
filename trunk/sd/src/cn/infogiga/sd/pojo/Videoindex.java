package cn.infogiga.sd.pojo;

/**
 * Videoindex entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Videoindex implements java.io.Serializable {

	// Fields

	private Integer indexId;
	private Video video;
	private Videomenu videomenu;

	// Constructors

	/** default constructor */
	public Videoindex() {
	}

	/** full constructor */
	public Videoindex(Video video, Videomenu videomenu) {
		this.video = video;
		this.videomenu = videomenu;
	}

	// Property accessors

	public Integer getIndexId() {
		return this.indexId;
	}

	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}

	public Video getVideo() {
		return this.video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Videomenu getVideomenu() {
		return this.videomenu;
	}

	public void setVideomenu(Videomenu videomenu) {
		this.videomenu = videomenu;
	}

}