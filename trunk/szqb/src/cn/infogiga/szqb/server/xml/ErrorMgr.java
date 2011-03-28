package cn.infogiga.szqb.server.xml;

public class ErrorMgr {
	
	public static String COMMON_ERROR = "01";
	public static String FORMAT_ERROR = "02";
	public static String OTHER_ERROR = "03";
	public static String NO_TYPE_ERROR = "04";
	
	public static XmlError getError(String code,String msg,String hireMsg){
		XmlError error = new XmlError();
		error.setCode(code);
		error.setHireMsg(hireMsg);
		error.setMsg(msg);
		return error;
	}
	
	public static XmlError getFormatError(){
		return getError(FORMAT_ERROR, "xml解析出现错误", "访问服务器错误,请稍后再试");
	}
	
	public static XmlError getOtherError(){
		return getError(OTHER_ERROR, "其它错误", "访问服务器错误,请稍后再试");
	}
	
	public static XmlError getNoTypeError(){
		return getError(NO_TYPE_ERROR, "没有这个指令", "非法请求");
	}
}	
