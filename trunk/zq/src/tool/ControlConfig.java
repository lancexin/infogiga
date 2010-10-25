package tool;

import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * 配置文件
 * @author ya
 */
public class ControlConfig {
	
	private static ResourceBundle resource = ResourceBundle.getBundle("control");
	
	/**
	 * 从资源文件中读取属性
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return resource.getString(key);
	}
		
	/**
	 * 是否有这个key
	 * @param key
	 * @return
	 */
	public static boolean hasKey(String key) {
		return resource.containsKey(key);
	}
	
	/**
	 * 所有的key
	 * @return
	 */
	public static Enumeration<String> allKeys() {
		return resource.getKeys();
	}
	/**
	 * 读取属性，转换为int型返回
	 * @param key
	 * @return
	 */
	public static int getInteger(String key) {
		return Integer.parseInt(resource.getString(key));
	}
}
