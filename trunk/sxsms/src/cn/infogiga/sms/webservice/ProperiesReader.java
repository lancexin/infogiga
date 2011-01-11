package cn.infogiga.sms.webservice;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ProperiesReader {
	
	
	private static Map<String,ProperiesReader> properiesList = new HashMap<String,ProperiesReader>();
	
	private Map<String,String> keyList = null;
	
	private ProperiesReader(String path){
		keyList = new HashMap<String,String>();
		loadProperies(keyList,path);
	}
	
	public static ProperiesReader getInstence(String path){
		synchronized (properiesList) {
			ProperiesReader properies = properiesList.get(path);
			if(properies == null){
				properies = new ProperiesReader(path);
				properiesList.put(path, properies);
				return properies;
			}
			return properies;
		} 
	}
	
	private void loadProperies(Map<String,String> keyList,String configPath){
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
			keyList.put(key, value);
		}
	}
	
	public String getStringValue(String key){
		return keyList.get(key);
	}
	
	public Integer getIntegerValue(String key){
		return Integer.parseInt(keyList.get(key));
	}
	
}
