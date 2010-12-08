package cn.infogiga.sd.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Attachment entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Attachment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Soft soft;
	private Phonearray phonearray;
	private String url;
	private String code;
	private String name;
	private Set attachandarraies = new HashSet(0);

	// Constructors

	/** default constructor */
	public Attachment() {
	}

	/** full constructor */
	public Attachment(Soft soft, Phonearray phonearray, String url,
			String code, String name, Set attachandarraies) {
		this.soft = soft;
		this.phonearray = phonearray;
		this.url = url;
		this.code = code;
		this.name = name;
		this.attachandarraies = attachandarraies;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Soft getSoft() {
		return this.soft;
	}

	public void setSoft(Soft soft) {
		this.soft = soft;
	}

	public Phonearray getPhonearray() {
		return this.phonearray;
	}

	public void setPhonearray(Phonearray phonearray) {
		this.phonearray = phonearray;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getAttachandarraies() {
		return this.attachandarraies;
	}

	public void setAttachandarraies(Set attachandarraies) {
		this.attachandarraies = attachandarraies;
	}

}