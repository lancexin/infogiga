package cn.infogiga.exp.server.xml;


import cindy.util.xml.XMLBean;
import cindy.util.xml.XMLElement;
import cindy.util.xml.XMLField;
import cindy.util.xml.XMLTypes;

@XMLElement(value="ERR_INFO",type=XMLTypes.XML_UNROOT)
public class ErrInfo implements XMLBean{
	
	@XMLField(value="ERROR_CODE",xmlType=XMLTypes.XML_ELEMENT)
	private String errCode = null;
	
	@XMLField(value="ERROR_MSG",xmlType=XMLTypes.XML_ELEMENT)
	private String errMsg = null;
	
	@XMLField(value="ERROR_HINT",xmlType=XMLTypes.XML_ELEMENT)
	private String errHint = null;
	
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getErrHint() {
		return errHint;
	}
	public void setErrHint(String errHint) {
		this.errHint = errHint;
	}
}
