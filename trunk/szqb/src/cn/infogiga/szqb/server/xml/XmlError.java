package cn.infogiga.szqb.server.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("error")
public class XmlError {
	@XStreamAlias("code")
	private String code;
	
	@XStreamAlias("msg")
	private String msg;
	
	@XStreamAlias("hireMsg")
	private String hireMsg;
	
	public String getHireMsg() {
		return hireMsg;
	}
	public void setHireMsg(String hireMsg) {
		this.hireMsg = hireMsg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
