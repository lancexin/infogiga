package cn.infogiga.android.experience.bean;



import cn.infogiga.util.xml.XMLBean;
import cn.infogiga.util.xml.XMLElement;
import cn.infogiga.util.xml.XMLField;
import cn.infogiga.util.xml.XMLTypes;

@XMLElement(value="INTF",type=XMLTypes.XML_ROOT)
public class Intf extends XMLBean{
	
	@XMLField(value="OPER_INFO",xmlType=XMLTypes.XML_BEAN,mClass=OperInfo.class)
	private OperInfo operInfo = null;
	
	@XMLField(value="INTF_PARAMS",xmlType=XMLTypes.XML_BEAN,mClass=IntfParams.class)
	private IntfParams intfParams = null;
	
	@XMLField(value="RET_INFO",xmlType=XMLTypes.XML_BEAN,mClass=RetInfo.class)
	private RetInfo retInfo = null;
	
	@XMLField(value="ERR_INFO",xmlType=XMLTypes.XML_BEAN,mClass=ErrInfo.class)
	private ErrInfo errinfo = null;

	public OperInfo getOperInfo() {
		return operInfo;
	}

	public void setOperInfo(OperInfo operInfo) {
		this.operInfo = operInfo;
	}

	public IntfParams getIntfParams() {
		return intfParams;
	}

	public void setIntfParams(IntfParams intfParams) {
		this.intfParams = intfParams;
	}

	public RetInfo getRetInfo() {
		return retInfo;
	}

	public void setRetInfo(RetInfo retInfo) {
		this.retInfo = retInfo;
	}

	public ErrInfo getErrinfo() {
		return errinfo;
	}

	public void setErrinfo(ErrInfo errinfo) {
		this.errinfo = errinfo;
	}
}
