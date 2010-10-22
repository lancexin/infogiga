package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonVideodownloadstat {
	@Sync(value = "statId")
	String statId;
	
	@Sync(value = "equipment.equipmentName")
	String equipmentName;
	
	@Sync(value = "equipment.bissinusshall.hallName")
	String hallName;
	
	@Sync(value = "employee.nickName")
	String employeeName;
	
	@Sync(value = "employee.userName")
	String employeeNo;

	@Sync(value = "video.videoName")
	String videoName;
	
	@Sync(value = "downloadtype.downloadtypeName")
	String downloadtypeName;

	@Sync(value = "phoneNumber")
	String phoneNumber;
	
	@Sync(value = "addTime")
	String addTime;

	public String getStatId() {
		return statId;
	}

	public void setStatId(String statId) {
		this.statId = statId;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getDownloadtypeName() {
		return downloadtypeName;
	}

	public void setDownloadtypeName(String downloadtypeName) {
		this.downloadtypeName = downloadtypeName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
}
