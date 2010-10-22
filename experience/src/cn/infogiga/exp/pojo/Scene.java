package cn.infogiga.exp.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractScene entity provides the base persistence definition of the Scene
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Scene implements java.io.Serializable,cindy.web.pojo.POJO {

	// Fields

	private Integer sceneId;
	private String senceName;
	private String backup1;
	private String backup2;
	private String backup3;
	private Set statisticses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Scene() {
	}

	/** full constructor */
	public Scene(String senceName, String backup1, String backup2,
			String backup3, Set statisticses) {
		this.senceName = senceName;
		this.backup1 = backup1;
		this.backup2 = backup2;
		this.backup3 = backup3;
		this.statisticses = statisticses;
	}

	// Property accessors

	public Integer getSceneId() {
		return this.sceneId;
	}

	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	public String getSenceName() {
		return this.senceName;
	}

	public void setSenceName(String senceName) {
		this.senceName = senceName;
	}

	public String getBackup1() {
		return this.backup1;
	}

	public void setBackup1(String backup1) {
		this.backup1 = backup1;
	}

	public String getBackup2() {
		return this.backup2;
	}

	public void setBackup2(String backup2) {
		this.backup2 = backup2;
	}

	public String getBackup3() {
		return this.backup3;
	}

	public void setBackup3(String backup3) {
		this.backup3 = backup3;
	}

	public Set getStatisticses() {
		return this.statisticses;
	}

	public void setStatisticses(Set statisticses) {
		this.statisticses = statisticses;
	}

}