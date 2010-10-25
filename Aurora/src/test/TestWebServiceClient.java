package test;

import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.xfire.client.Client;
public class TestWebServiceClient {
	public static void main(String[] args) {
		try {
			Client client = new Client(new URL("http://192.168.1.4:8888/online/service/HelloWorld?wsdl"));
			Object[] objArray = new Object[1];
		    objArray[0] = "cindy";
		    //调用特定的Web Service方法
		    Object[] results = client.invoke("hello", objArray);
		    System.out.println("result: " + results[0]);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
