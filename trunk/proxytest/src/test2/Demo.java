package test2;

public class Demo implements IDemo{
	
	@ProxyTag(proxyClass=ProxyClass.class)
	public void sayHello(){
		System.out.println("hello....");
	}
	
	@ProxyTag(proxyClass=ProxyClass2.class)
	public void sayHello2(){
		System.out.println("hello2....");
	}
}
