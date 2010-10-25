package cn.infogiga.util;

import java.util.Date;

public class TemplateBean {

	private String name;
	private Date time;
	private String location;
	private String url;
	
	public TemplateBean() {
		super();
	}
	public TemplateBean(String name, Date time, String location, String url) {
		super();
		this.name = name;
		this.time = time;
		this.location = location;
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
