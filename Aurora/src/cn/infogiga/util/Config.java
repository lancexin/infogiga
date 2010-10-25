package cn.infogiga.util;

import java.util.ResourceBundle;

public class Config {

	public final static String URL = "http://192.168.1.4:5555";
	public final static String HOST_S = "192.168.1.4";
	public final static String HOST_M = "192.168.1.37";
	public final static int PORT = 5555;
	public final static int PORT_S = 5555;
	
	private static ResourceBundle bundle = ResourceBundle.getBundle("mail");
	public static String getValue(String key) {
		return bundle.getString(key);
	}
}
