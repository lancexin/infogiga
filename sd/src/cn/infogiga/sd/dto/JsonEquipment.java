package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonEquipment {
	@Sync(value = "equipmentId")
	private String equipmentId;
	
	@Sync(value = "equipmentCode")
	private String equipmentCode;
	
	@Sync(value = "equipmentName")
	private String equipmentName;
	
	@Sync(value = "mac")
	private String mac;
	
	@Sync(value = "bissinusshall.hallId")
	private String hallId;
	
	@Sync(value = "bissinusshall.hallName")
	private String hallName;
	
	@Sync(value = "addTime")
	private String addTime;
	
	@Sync(value = "status")
	private String status;
	
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getHallId() {
		return hallId;
	}
	public void setHallId(String hallId) {
		this.hallId = hallId;
	}
	public String getHallName() {
		return hallName;
	}
	public void setHallName(String hallName) {
		this.hallName = hallName;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEquipmentCode() {
		return equipmentCode;
	}
	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	
}
