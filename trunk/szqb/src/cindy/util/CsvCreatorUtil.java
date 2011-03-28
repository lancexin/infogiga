package cindy.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CsvCreatorUtil {
	
	
	public static void exportCsv(OutputStream os, String[] title, List list) throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		readHead(os, title);
		readBody(os,list);
		os.flush();
		os.close();
	}
	
	private static void readHead(OutputStream os, String[] title) throws IOException{
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<title.length;i++){
			buffer.append(title[i]);
			buffer.append(",");
		}
		buffer.append("\r\n");
		os.write(buffer.toString().getBytes());
	}
	
	private static void readBody(OutputStream os, List list) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		int size = list.size();
		for(int i=0;i<size;i++){
			Object obj = list.get(i);
			String context = praseIterator(obj);
			os.write(context.getBytes());
		}
	}

	// 解析iterator
	public static String praseIterator(Object obj) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		String result = "";
		if (obj != null) {
			Class ownerClass = obj.getClass();
			Field[] fields = ownerClass.getDeclaredFields();
			for (Field f : fields) {
				String fieldName = f.getName();
				String methodName = fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				Method method = null;
				try {
					method = ownerClass.getMethod("get" + methodName);
					String value = (String) method.invoke(obj);
					System.out.println(value);
					result+=(value+",");
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					System.out.println("找不到get" + methodName + "方法！");
				}
				
			}
		}
		result+="\r\n";
		return result;
	}
}
