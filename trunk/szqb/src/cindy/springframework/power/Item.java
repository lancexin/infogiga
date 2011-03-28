package cindy.springframework.power;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("item")
public class Item implements Serializable,Cloneable{
	@XStreamAlias("code")
	String code;
	@XStreamAlias("name")
	String name;
	@XStreamAlias("url")
	String url;
	@XStreamAlias("isframe")
	boolean isframe;
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
	public boolean isIsframe() {
		return isframe;
	}
	public void setIsframe(boolean isframe) {
		this.isframe = isframe;
	}
	
}
