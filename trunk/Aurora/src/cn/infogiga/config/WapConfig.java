package cn.infogiga.config;

import java.util.HashMap;
import java.util.Map;

public class WapConfig {

	private Map<String, String> pages = new HashMap<String, String>();
	private Map<String, String> versions = new HashMap<String, String>();
	private Map<String, String> regions = new HashMap<String, String>();
	private static WapConfig instance = new WapConfig();
	private WapConfig() {		
	}
	public static WapConfig getConfig() {
		if(instance == null) {
			instance = new WapConfig();
		}
		return instance;
	}
	
	public Map<String, String> getPages() {
		return pages;
	}
	public void setPages(Map<String, String> pages) {
		this.pages = pages;
	}
	public Map<String, String> getVersions() {
		return versions;
	}
	public void setVersions(Map<String, String> versions) {
		this.versions = versions;
	}
	public Map<String, String> getRegions() {
		return regions;
	}
	public void setRegions(Map<String, String> regions) {
		this.regions = regions;
	}
}
