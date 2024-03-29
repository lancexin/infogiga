package cn.infogiga.exp.webservice.bean;

import cn.infogiga.util.xml.XMLBean;
import cn.infogiga.util.xml.XMLElement;
import cn.infogiga.util.xml.XMLField;
import cn.infogiga.util.xml.XMLTypes;

@XMLElement(value="MENU_ENTITY_PRIV",type=XMLTypes.XML_UNROOT)
public class MenuEntityPriv extends XMLBean{
	
	@XMLField(value="REGION_CODE",xmlType=XMLTypes.XML_ELEMENT)
	private String regionCode = null;
	
	@XMLField(value="COUNTY_CODE",xmlType=XMLTypes.XML_ELEMENT)
	private String countryCode = null;
	
	@XMLField(value="TRADE_MARK",xmlType=XMLTypes.XML_ELEMENT)
	private String tradeMark = null;

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getTradeMark() {
		return tradeMark;
	}

	public void setTradeMark(String tradeMark) {
		this.tradeMark = tradeMark;
	}
}
