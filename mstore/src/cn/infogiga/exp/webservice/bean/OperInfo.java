package cn.infogiga.exp.webservice.bean;

import cn.infogiga.util.xml.XMLBean;
import cn.infogiga.util.xml.XMLElement;
import cn.infogiga.util.xml.XMLField;
import cn.infogiga.util.xml.XMLTypes;

@XMLElement(value="OPER_INFO",type=XMLTypes.XML_UNROOT)
public class OperInfo extends XMLBean {
	@XMLField(value="OP_ID",xmlType=XMLTypes.XML_ELEMENT)
	private String opId = null;
	
	@XMLField(value="DOMAIN_ID",xmlType=XMLTypes.XML_ELEMENT)
	private String domainId = null;
	
	@XMLField(value="PASSWORD",xmlType=XMLTypes.XML_ELEMENT)
	private String password = null;

	public String getOpId() {
		return opId;
	}

	public void setOpId(String opId) {
		this.opId = opId;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
