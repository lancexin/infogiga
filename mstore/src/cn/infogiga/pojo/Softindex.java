package cn.infogiga.pojo;

/**
 * Softindex entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Softindex implements java.io.Serializable {

	// Fields

	private Integer id;
	private Softmenu softmenu;
	private Soft soft;

	// Constructors

	/** default constructor */
	public Softindex() {
	}

	/** full constructor */
	public Softindex(Softmenu softmenu, Soft soft) {
		this.softmenu = softmenu;
		this.soft = soft;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Softmenu getSoftmenu() {
		return this.softmenu;
	}

	public void setSoftmenu(Softmenu softmenu) {
		this.softmenu = softmenu;
	}

	public Soft getSoft() {
		return this.soft;
	}

	public void setSoft(Soft soft) {
		this.soft = soft;
	}

}