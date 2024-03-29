package cn.infogiga.exp.webservice.bean;

import cn.infogiga.util.xml.XMLBean;
import cn.infogiga.util.xml.XMLElement;
import cn.infogiga.util.xml.XMLField;
import cn.infogiga.util.xml.XMLTypes;

@XMLElement(value="SOFT_INFO",type=XMLTypes.XML_UNROOT)
public class TopSoft extends XMLBean {
	
	@XMLField(value="SOFT_NAME",xmlType=XMLTypes.XML_ELEMENT)
	String softName = null;
	
	@XMLField(value="DOWNLOAD_COUNT",xmlType=XMLTypes.XML_ELEMENT)
	String downloadCount = null;

	public String getSoftName() {
		return softName;
	}

	public void setSoftName(String softName) {
		this.softName = softName;
	}

	public String getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(String downloadCount) {
		this.downloadCount = downloadCount;
	}
}
