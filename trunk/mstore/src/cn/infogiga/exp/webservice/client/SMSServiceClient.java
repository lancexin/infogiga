package cn.infogiga.exp.webservice.client;

import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.xfire.client.Client;

import cindy.util.ProperiesReader;

public class SMSServiceClient {
	public static boolean sendSms(String phoneNumber,String context){
		ProperiesReader reader = ProperiesReader.getInstence("config.properties");
		Client client;
		Boolean b = false;
		try {
			client = new Client(new URL(reader.getStringValue("sms_host")));
			Object[] objArray = new Object[2];
			objArray[0] = phoneNumber;
		    objArray[1] = context;
		    Object[] results = client.invoke("sendSMS", objArray);
		    b = (Boolean) results[0];
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return b;
	}
	
	public static boolean sendWapPush(String phoneNumber,String url,String context){
		ProperiesReader reader = ProperiesReader.getInstence("config.properties");
		Client client;
		Boolean b = false;
		try {
			client = new Client(new URL(reader.getStringValue("sms_host")));
			Object[] objArray = new Object[3];
			objArray[0] = phoneNumber;
		    objArray[1] = url;
		    objArray[2] = context;
		    Object[] results = client.invoke("sendSMSByWebPush", objArray);
		    b = (Boolean) results[0];
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return b;
	}
	
	public static void main(String[] args) {
		
	}
}
