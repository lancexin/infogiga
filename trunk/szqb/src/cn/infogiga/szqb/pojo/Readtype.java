package cn.infogiga.szqb.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Readtype entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Readtype implements java.io.Serializable {

	// Fields

	private Integer id;
	private String typeName;
	private String shortName;
	private Set readers = new HashSet(0);

	// Constructors

	/** default constructor */
	public Readtype() {
	}

	/** full constructor */
	public Readtype(String typeName, String shortName, Set readers) {
		this.typeName = typeName;
		this.shortName = shortName;
		this.readers = readers;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Set getReaders() {
		return this.readers;
	}

	public void setReaders(Set readers) {
		this.readers = readers;
	}

}