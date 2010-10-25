package bean;

import java.io.Serializable;

public class RegionBean implements Serializable {
	
	private static final long serialVersionUID = -2197075223532747301L;	private String regionCode = "";
	private String regionName = "";
	
	public RegionBean(String regionCode, String regionName) {	
		this.regionCode = regionCode;
		this.regionName = regionName;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}	
}
