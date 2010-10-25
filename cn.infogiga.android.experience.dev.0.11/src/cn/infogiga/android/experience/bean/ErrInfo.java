package cn.infogiga.android.experience.bean;



import cn.infogiga.util.xml.XMLBean;
import cn.infogiga.util.xml.XMLElement;
import cn.infogiga.util.xml.XMLField;
import cn.infogiga.util.xml.XMLTypes;

@XMLElement(value="ERR_INFO",type=XMLTypes.XML_UNROOT)
public class ErrInfo extends XMLBean{
	
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
