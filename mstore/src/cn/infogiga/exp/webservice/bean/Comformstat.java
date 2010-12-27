package cn.infogiga.exp.webservice.bean;

import cn.infogiga.util.xml.XMLBean;
import cn.infogiga.util.xml.XMLElement;
import cn.infogiga.util.xml.XMLField;
import cn.infogiga.util.xml.XMLTypes;

@XMLElement(value="comform_stat",type=XMLTypes.XML_UNROOT)
public class Comformstat extends XMLBean {
	
	@XMLField(value="code",xmlType=XMLTypes.XML_ELEMENT)
	String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
