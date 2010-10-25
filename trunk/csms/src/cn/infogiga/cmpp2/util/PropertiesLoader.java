package cn.infogiga.cmpp2.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


public class PropertiesLoader {
	private static Map<String,String> configList = new HashMap<String,String>();
	
	private static final String configPath = "config.properties";
	
	public PropertiesLoader(){
		InputStream in = getClass().getClassLoader().getResourceAsStream(configPath);
		Properties props = new Properties();
		try {
			props.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<Object> set = props.keySet();
		Iterator<Object> iterator = set.iterator();
		
		while(iterator.hasNext()){
			String key = (String) iterator.next();
			String value = props.getProperty(key).trim();
			configList.put(key, value);
		}
		
	}
	
	
	public static String getStringParameter(String key){
		return configList.get(key);
	}
	
	public static Integer getIntParameter(String key){
		return Integer.parseInt(configList.get(key));
	}
}
