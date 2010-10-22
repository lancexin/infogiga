package cn.infogiga.exp.server.xml;

import cindy.util.xml.XMLBean;
import cindy.util.xml.XMLElement;
import cindy.util.xml.XMLField;
import cindy.util.xml.XMLTypes;

@XMLElement(value="SOFT_INFO",type=XMLTypes.XML_UNROOT)
public class TopSoft  implements XMLBean{
	
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
