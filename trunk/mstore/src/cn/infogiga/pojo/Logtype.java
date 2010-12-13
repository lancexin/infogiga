package cn.infogiga.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Logtype entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Logtype implements java.io.Serializable {
	
	public static final int POWER = 1;
	public static final int AREA = 2;
	public static final int USER = 3;
	public static final int HALL = 4;
	public static final int EQUIPMENT = 5;
	public static final int EMPLOYEE = 6;
	public static final int PHANDBRAND = 7;
	public static final int PLATFORM = 8;
	public static final int SOFTMENU = 9;
	public static final int PHONEARRY= 10;
	public static final int PHONETYPE= 11;
	public static final int SOFT = 12;
	

	private Integer id;
	private String value;
	private Set logs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Logtype() {
	}

	/** minimal constructor */
	public Logtype(String value) {
		this.value = value;
	}

	/** full constructor */
	public Logtype(String value, Set logs) {
		this.value = value;
		this.logs = logs;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Set getLogs() {
		return this.logs;
	}

	public void setLogs(Set logs) {
		this.logs = logs;
	}

}