package cn.infogiga.exp.server.xml;

import cindy.util.xml.XMLBean;
import cindy.util.xml.XMLElement;
import cindy.util.xml.XMLField;
import cindy.util.xml.XMLTypes;

@XMLElement(value="MENU_ENTITY_PRIV",type=XMLTypes.XML_UNROOT)
public class MenuEntityPriv implements XMLBean{
	
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
