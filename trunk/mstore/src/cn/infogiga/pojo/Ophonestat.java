package cn.infogiga.pojo;

import java.util.Date;

/**
 * Ophonestat entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Ophonestat implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Menu menu;
	private Equipment equipment;
	private Scene scene;
	private Date happenTime;
	private String phoneNumber;
	private Integer status;
	private String comboName;

	// Constructors

	/** default constructor */
	public Ophonestat() {
	}

	/** full constructor */
	public Ophonestat(Users users, Menu menu, Equipment equipment, Scene scene,
			Date happenTime, String phoneNumber, Integer status,
			String comboName) {
		this.users = users;
		this.menu = menu;
		this.equipment = equipment;
		this.scene = scene;
		this.happenTime = happenTime;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.comboName = comboName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Equipment getEquipment() {
		return this.equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Scene getScene() {
		return this.scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public Date getHappenTime() {
		return this.happenTime;
	}

	public void setHappenTime(Date happenTime) {
		this.happenTime = happenTime;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getComboName() {
		return this.comboName;
	}

	public void setComboName(String comboName) {
		this.comboName = comboName;
	}

}