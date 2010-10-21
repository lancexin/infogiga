package cn.infogiga.sd.pojo;

/**
 * Videomenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Videomenu implements java.io.Serializable {

	// Fields

	private Integer videomenuId;
	private String videomenuName;

	// Constructors

	/** default constructor */
	public Videomenu() {
	}

	/** full constructor */
	public Videomenu(String videomenuName) {
		this.videomenuName = videomenuName;
	}

	// Property accessors

	public Integer getVideomenuId() {
		return this.videomenuId;
	}

	public void setVideomenuId(Integer videomenuId) {
		this.videomenuId = videomenuId;
	}

	public String getVideomenuName() {
		return this.videomenuName;
	}

	public void setVideomenuName(String videomenuName) {
		this.videomenuName = videomenuName;
	}

}