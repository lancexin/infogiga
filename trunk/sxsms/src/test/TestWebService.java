package test;

import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.xfire.client.Client;

public class TestWebService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Client client;
		try {
			client = new Client(new URL("http://localhost:8888/csms/sms.ws?wsdl"));
			Object[] objArray = new Object[3];
			objArray[0] = "15268114857";
			objArray[1] = "wap.sohu.com";
		    objArray[2] = "这是一条短信信息凑数";
			//objArray[1] = "这是一条短信信息凑数";
		    Object[] results = client.invoke("sendSMSByWebPush", objArray);
		    Boolean b = (Boolean) results[0];
		    //System.out.println(b);
			/*client = new Client(new URL("http://localhost:8080/csms/sms.ws?wsdl"));
			Object[] objArray = new Object[3];
			objArray[0] = "15958189755";
		//	objArray[0] = "13588296200";
			objArray[1] = "http://blog.csdn.net/eason_cou/archive/2007/05/11/wap.gd.monternet.com/?userType=B&serviceID=04020028";
		    objArray[2] = "神秘激情地带,江湖儿女情长神秘激情地带,江湖儿女情长神秘激情地带,江湖儿女情长";
		    Object[] results = client.invoke("sendSMSByWebPush", objArray);
		    Boolean b = (Boolean) results[0];*/
			
			
		    if(b){
		    	System.out.println("发送成功");
		    }else{
		    	System.out.println("发送失败");
		    }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
