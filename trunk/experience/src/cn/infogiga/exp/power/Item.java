package cn.infogiga.exp.power;

import cindy.util.xml.XMLBean;
import cindy.util.xml.XMLElement;
import cindy.util.xml.XMLField;
import cindy.util.xml.XMLTypes;

@XMLElement(value="item",type=XMLTypes.XML_UNROOT)
public class Item implements XMLBean,Cloneable{
	@XMLField(value="code",xmlType=XMLTypes.XML_ELEMENT)
	private String code = null;
	@XMLField(value="name",xmlType=XMLTypes.XML_ELEMENT)
	private String name = null;
	@XMLField(value="url",xmlType=XMLTypes.XML_ELEMENT)
	private String url = null;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Object clone() {  
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null; 
		}
    }
}
