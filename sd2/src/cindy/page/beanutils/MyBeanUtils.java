package cindy.page.beanutils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.NestedNullException;

/**
 * 需要 org.apache.commons.beanutils 包以及其依赖包
 * @author Cindy
 */
public class MyBeanUtils {
	
	public static <T,M> T copyProperties(M from,Class<T> toClass){
		Field[] f = toClass.getDeclaredFields();
		T to = (T) newInstance(toClass);
		for(int i=0;i<f.length;i++){
			Sync syoc = f[i].getAnnotation(Sync.class);
			if(syoc == null){
				continue;
			}
			String value = syoc.value();
			try {
				Object vl = null;
				try {
					vl = BeanUtils.getProperty(from, value);
				} catch (NestedNullException e) {
					continue;
				}
				if(vl == null){
					continue;
				}
				Object[] o = {BeanUtils.getProperty(from, value)};
				invokeMethod(to,getSETMethodByFieldName(f[i].getName()),o);
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return to;
	}
	
	public static <T,M> List<T> copyListProperties(List<M> fromList,Class<T> toClass){
		List<T> backList = new ArrayList<T>();
		int size = fromList.size();
		for(int i=0;i<size;i++){
			backList.add(copyProperties(fromList.get(i),toClass));
		}
		return backList;
	}
	
	private static String getSETMethodByFieldName(String name){
		String firstLetter = name.substring(0, 1).toUpperCase();  
		return "set" + firstLetter + name.substring(1);  
	}
	
	private static Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception{        
        Class ownerClass = owner.getClass();
        Class[] argsClass = new Class[args.length];        
        for (int i = 0, j = args.length; i < j; i++) {  
        	if(args[i] == null){
        		return null;
        	}
            argsClass[i] = args[i].getClass();        
        }    
        Method method = ownerClass.getMethod(methodName, argsClass);
        return method.invoke(owner, args);
    }
	
	private static Object newInstance(final Class clazz){
		try {
			Constructor cons = clazz.getConstructor();
			return cons.newInstance(new Class[]{});
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
