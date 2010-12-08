package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonProvince {
	@Sync(value = "id")
	private String provinceId;
	@Sync(value = "provinceName")
	private String provinceName;
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
