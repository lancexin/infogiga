package cn.infogiga.android.experience.bean;



import cn.infogiga.util.xml.XMLElement;
import cn.infogiga.util.xml.XMLField;
import cn.infogiga.util.xml.XMLTypes;

@XMLElement(value="pc",type=XMLTypes.XML_UNROOT)
public class PCInfo {
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
