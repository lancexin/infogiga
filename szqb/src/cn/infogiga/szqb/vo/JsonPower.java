package cn.infogiga.szqb.vo;

import cindy.page.beanutils.Sync;


public class JsonPower {
	
	@Sync(value = "id")
	private String powerId;
	@Sync(value = "powerValue")
	private String powerValue;
	@Sync(value = "powerName")
	private String powerName;
	@Sync(value = "status")
	private String status;

	public String getPowerValue() {
		return powerValue;
	}
	public void setPowerValue(String powerValue) {
		this.powerValue = powerValue;
	}
	public String getPowerName() {
		return powerName;
	}
	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}
	public String getPowerId() {
		return powerId;
	}
	public void setPowerId(String powerId) {
		this.powerId = powerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
