package cn.infogiga.sd.pojo;

/**
 * Menu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Menu implements java.io.Serializable {

	// Fields

	private Integer menuId;
	private String menuName;
	private String pic1;
	private String pic2;
	private String link;

	// Constructors

	/** default constructor */
	public Menu() {
	}

	/** full constructor */
	public Menu(String menuName, String pic1, String pic2, String link) {
		this.menuName = menuName;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.link = link;
	}

	// Property accessors

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getPic1() {
		return this.pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return this.pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}