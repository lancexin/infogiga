package test2;

public class ProxyClass2 extends ProxyBean{

	public void after() {
		System.out.println("after2....");
	}

	public void before() {
		System.out.println("before2....");
	}
}
