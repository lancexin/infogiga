package cn.infogiga.exp.server.xml;

import cindy.util.xml.XMLBean;
import cindy.util.xml.XMLElement;
import cindy.util.xml.XMLField;
import cindy.util.xml.XMLTypes;

@XMLElement(value="pc",type=XMLTypes.XML_UNROOT)
public class PCInfo  implements XMLBean{
	@XMLField(value="mac",xmlType=XMLTypes.XML_ELEMENT)
	public String mac = "";
	
	@XMLField(value="harddisk",xmlType=XMLTypes.XML_ELEMENT)
	public String harddisk = "";
	
	@XMLField(value="ip",xmlType=XMLTypes.XML_ELEMENT)
	public String ip = "";
	
	@XMLField(value="cmp_name",xmlType=XMLTypes.XML_ELEMENT)
	public String cmp_name = "";

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getHarddisk() {
		return harddisk;
	}

	public void setHarddisk(String harddisk) {
		this.harddisk = harddisk;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCmp_name() {
		return cmp_name;
	}

	public void setCmp_name(String cmp_name) {
		this.cmp_name = cmp_name;
	}
}
