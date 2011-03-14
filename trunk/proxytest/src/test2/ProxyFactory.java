package test2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProxyFactory {
	
	
	public static <T> T getProxyBean(Class<T> clazz){
		T t = (T) newInstance(clazz);
		Method[] methods = clazz.getMethods();
		
		for(int i=0;i<methods.length;i++){
			ProxyTag pt = methods[i].getAnnotation(ProxyTag.class);
			if(pt == null){
				continue;
			}
			ProxyBean pb = (ProxyBean) newInstance(pt.proxyClass());
			t = (T) pb.bind(t, pb, methods[i].getName());
		}
		return t;
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
