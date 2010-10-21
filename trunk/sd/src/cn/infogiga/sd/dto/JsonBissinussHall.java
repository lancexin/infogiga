package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonBissinussHall {
	
	@Sync(value = "hallId")
	private String hallId;
	
	@Sync(value = "hallName")
	private String hallName;
	
	@Sync(value = "description")
	private String description;
	
	@Sync(value = "addTime")
	private String addTime;
	
	@Sync(value = "status")
	private String status;
	
	@Sync(value = "city.cityId")
	private String cityId;
	
	@Sync(value = "city.cityName")
	private String cityName;
	public String getHallName() {
		return hallName;
	}
	public void setHallName(String hallName) {
		this.hallName = hallName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getHallId() {
		return hallId;
	}
	public void setHallId(String hallId) {
		this.hallId = hallId;
	}
	
}
