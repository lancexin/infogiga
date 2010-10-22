package cn.infogiga.exp.pojo;

import java.util.Date;

/**
 * AbstractStatistics entity provides the base persistence definition of the
 * Statistics entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Statistics implements java.io.Serializable,cindy.web.pojo.POJO {

	// Fields

	public Statistics() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Integer statisticsId;
	private Equipment equipment;
	private Employee employee;
	private Scene scene;
	private Menu menu;
	private Date happenTime;
	private String phoneNumber;
	private Integer status;
	private String backup1;
	private String backup2;
	private String backup3;
	private String comboName;

	// Constructors


	/** full constructor */
	public Statistics(Equipment equipment, Employee employee,
			Scene scene, Menu menu, Date happenTime, String phoneNumber,
			Integer status, String backup1, String backup2, String backup3,
			String comboName) {
		this.equipment = equipment;
		this.employee = employee;
		this.scene = scene;
		this.menu = menu;
		this.happenTime = happenTime;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.backup1 = backup1;
		this.backup2 = backup2;
		this.backup3 = backup3;
		this.comboName = comboName;
	}

	// Property accessors

	public Integer getStatisticsId() {
		return this.statisticsId;
	}

	public void setStatisticsId(Integer statisticsId) {
		this.statisticsId = statisticsId;
	}

	public Equipment getEquipment() {
		return this.equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Scene getScene() {
		return this.scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
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

	public String getComboName() {
		return this.comboName;
	}

	public void setComboName(String comboName) {
		this.comboName = comboName;
	}

}