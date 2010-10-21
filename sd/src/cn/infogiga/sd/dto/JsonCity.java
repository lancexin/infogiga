package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonCity {
	@Sync(value = "cityId")
	private String cityId;
	@Sync(value = "cityName")
	private String cityName;
	@Sync(value = "province.provinceId")
	private String provinceId;
	@Sync(value = "province.provinceName")
	private String provinceName;
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
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
}
