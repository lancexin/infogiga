package cn.infogiga.filther;

import java.util.Enumeration;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import cn.infogiga.config.WapConfig;

public class ConfigListener extends HttpServlet {

	private static final long serialVersionUID = 142L;
	private static Log log = LogFactory.getLog(ConfigListener.class);
	private WapConfig wapConfig;
	
	public ConfigListener() {
		wapConfig = WapConfig.getConfig();	
	}

	public void init(ServletConfig config) throws ServletException {
		ResourceBundle bundle = ResourceBundle.getBundle("wapage");
		ResourceBundle bundle2 = ResourceBundle.getBundle("region2pageno");
		ResourceBundle bundle3 = ResourceBundle.getBundle("wapver");
		Enumeration<String> keys = bundle.getKeys();
		Enumeration<String> keys2 = bundle2.getKeys();
		Enumeration<String> keys3 = bundle3.getKeys();
		Map<String, String> pages = wapConfig.getPages();
		Map<String, String> versions = wapConfig.getVersions();
		Map<String, String> regions = wapConfig.getRegions();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			pages.put(key, bundle.getString(key));
		}
		while (keys2.hasMoreElements()) {
			String key = keys2.nextElement();
			regions.put(key, bundle2.getString(key));
		}
		while (keys3.hasMoreElements()) {
			String key = keys3.nextElement();
			versions.put(key, bundle3.getString(key));
		}
		
		log.info("初始化wap配置成功");
	}
}
