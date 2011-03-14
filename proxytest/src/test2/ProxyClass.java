package test2;

public class ProxyClass extends ProxyBean{

	public void after() {
		System.out.println("after....");
	}

	public void before() {
		System.out.println("before....");
	}
}
