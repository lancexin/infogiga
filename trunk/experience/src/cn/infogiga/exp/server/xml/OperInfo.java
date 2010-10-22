package cn.infogiga.exp.server.xml;

import cindy.util.xml.XMLBean;
import cindy.util.xml.XMLElement;
import cindy.util.xml.XMLField;
import cindy.util.xml.XMLTypes;

@XMLElement(value="OPER_INFO",type=XMLTypes.XML_UNROOT)
public class OperInfo implements XMLBean{
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
