package cn.infogiga.sd.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Softmenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Softmenu implements java.io.Serializable {

	// Fields

	private Integer softmenuId;
	private String softmenuName;
	private Set softs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Softmenu() {
	}
	
	public Softmenu(Integer softmenuId) {
		this.softmenuId = softmenuId;
	}

	/** full constructor */
	public Softmenu(String softmenuName, Set softs) {
		this.softmenuName = softmenuName;
		this.softs = softs;
	}

	// Property accessors

	public Integer getSoftmenuId() {
		return this.softmenuId;
	}

	public void setSoftmenuId(Integer softmenuId) {
		this.softmenuId = softmenuId;
	}

	public String getSoftmenuName() {
		return this.softmenuName;
	}

	public void setSoftmenuName(String softmenuName) {
		this.softmenuName = softmenuName;
	}

	public Set getSofts() {
		return this.softs;
	}

	public void setSofts(Set softs) {
		this.softs = softs;
	}

}