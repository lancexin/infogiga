package cn.infogiga.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Scene entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Scene implements java.io.Serializable {

	// Fields

	private Integer id;
	private String sceneName;
	private Set ophonestats = new HashSet(0);

	// Constructors

	/** default constructor */
	public Scene() {
	}

	/** full constructor */
	public Scene(String sceneName, Set ophonestats) {
		this.sceneName = sceneName;
		this.ophonestats = ophonestats;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSceneName() {
		return this.sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public Set getOphonestats() {
		return this.ophonestats;
	}

	public void setOphonestats(Set ophonestats) {
		this.ophonestats = ophonestats;
	}

}