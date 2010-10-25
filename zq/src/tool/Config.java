package tool;

import java.util.ResourceBundle;

/**
 * 配置文件
 * @author ya
 */
public class Config {
		
	/**
	 * 从资源文件中读取属性
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return ResourceBundle.getBundle("resource").getString(key);
	}
	
	/**
	 * 读取属性，转换为int型返回
	 * @param key
	 * @return
	 */
	public static int getInteger(String key) {
		return Integer.parseInt(ResourceBundle.getBundle("resource").getString(key));
	}
}
