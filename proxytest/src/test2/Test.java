package test2;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		IDemo demo = ProxyFactory.getProxyBean(Demo.class);
		demo.sayHello();
		System.out.println("-----------------------------");
		demo.sayHello2();

	}
}
