package test2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class  ProxyBean implements InvocationHandler{
	
	private Object o;

	private ProxyBean proxyBean;
	
	private String methodName;
	
	public Object bind(Object obj,ProxyBean proxyBean,String methodName){
		this.o = obj;
		this.proxyBean = proxyBean;
		this.methodName = methodName;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
	}
	
	
	public Object invoke(Object proxy, Method method, Object[] obj)
			throws Throwable {
		if(method.getName().equals(methodName)){
			proxyBean.before();
			Object result = method.invoke(o, obj);
			proxyBean.after();
			return result;
		}else{
			Object result = method.invoke(o, obj);
			return result;
		}
		
	}
	
	public abstract void before();
	
	public abstract void after();
}
