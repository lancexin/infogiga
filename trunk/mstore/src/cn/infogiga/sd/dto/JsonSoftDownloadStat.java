package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonSoftDownloadStat {
	@Sync(value = "id")
	String statId;
	
	@Sync(value = "equipment.equipmentName")
	String equipmentName;
	
	@Sync(value = "equipment.bissinusshall.hallName")
	String hallName;
	
	@Sync(value = "soft.softName")
	String softName;
	
	@Sync(value = "phonetype.phonetypeName")
	String phonetypeName;
	
	@Sync(value = "phonetype.phonebrandcategory.categoryName")
	String categoryName;
	
	@Sync(value = "phonetype.phonebrandcategory.phonebrand.phonebrandName")
	String phonebrandName;
	
	@Sync(value = "users.nickName")
	String employeeName;
	

	@Sync(value = "users.userName")
	String employeeNo;
	
	@Sync(value = "downloadtype.downloadtypeName")
	String downloadtypeName;
	
	@Sync(value = "phoneNumber")
	String phoneNumber;
	
	@Sync(value = "addTime")
	String addTime;
	
	@Sync(value = "equipment.bissinusshall.channel.channelName")
	String channelName;
	
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
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

	public String getSoftName() {
		return softName;
	}
	public void setSoftName(String softName) {
		this.softName = softName;
	}

	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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

	public String getPhonetypeName() {
		return phonetypeName;
	}
	public void setPhonetypeName(String phonetypeName) {
		this.phonetypeName = phonetypeName;
	}

	public String getPhonebrandName() {
		return phonebrandName;
	}
	public void setPhonebrandName(String phonebrandName) {
		this.phonebrandName = phonebrandName;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
