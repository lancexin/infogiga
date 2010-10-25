package bean;

/**
 * @author ya
 *
 */
public class InfoBean {
	
	private String id;
	private String name;
	private String gender;
	private String phone;
	private String planar;
	
	/**
	 * @param id checkid
	 * @param name 名字
	 * @param gender 性别
	 * @param phone 手机号
	 * @param planar 二维码
	 */
	public InfoBean(String id, String name, String gender, String phone, String planar) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.planar = planar;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPlanar() {
		return planar;
	}

	public void setPlanar(String planar) {
		this.planar = planar;
	}	
}
