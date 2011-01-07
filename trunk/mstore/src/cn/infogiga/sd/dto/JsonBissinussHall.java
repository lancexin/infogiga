package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonBissinussHall {
	
	@Sync(value = "id")
	private String hallId;
	
	@Sync(value = "hallName")
	private String hallName;
	
	@Sync(value = "description")
	private String description;
	
	@Sync(value = "addTime")
	private String addTime;
	
	@Sync(value = "status")
	private String status;
	
	@Sync(value = "city.id")
	private String cityId;
	
	@Sync(value = "city.cityName")
	private String cityName;
	
	@Sync(value = "code")
	private String code;
	
	@Sync(value = "channel.id")
	private String channelId;
	
	@Sync(value = "channel.channelName")
	private String channelName;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
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
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
}
