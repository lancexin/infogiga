package cn.infogiga.exp.webservice;

import java.net.MalformedURLException;

import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

import cindy.util.config.ProperiesReader;


public class SMSServiceClient {
	
	public static SMSService getSMSService() throws MalformedURLException{
		ProperiesReader properties = ProperiesReader.getInstence("config.properties");
		Service servicemodel =new ObjectServiceFactory().create(SMSService.class); 
	    XFireProxyFactory factory =new XFireProxyFactory(XFireFactory.newInstance().getXFire()); 
	    SMSService testService = (SMSService)factory.create(servicemodel,properties.getStringValue("sms_host"));
		return testService;
	}
	
	public static boolean sendSms(String phoneNumber,String context){
		SMSService smsService;
		try {
			smsService = getSMSService();
			smsService.sendSMS(phoneNumber, context);
			return true;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public static boolean sendArraySms(String[] phoneNumbers,String[] contexts){
		SMSService smsService;
		try {
			smsService = getSMSService();
			smsService.sendSMSOneByOne(phoneNumbers, contexts);
			return true;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean sendWapPush(String phoneNumber,String url,String context){
		SMSService smsService;
		try {
			smsService = getSMSService();
			smsService.sendSMSByWebPush(phoneNumber, url, context);
			return true;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean sendArraySms(String[] phoneNumbers,String context){
		SMSService smsService;
		try {
			smsService = getSMSService();
			smsService.sendArraySMS(phoneNumbers, context);
			return true;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean sendArraySmsByWapPush(String[] phoneNumbers,String[] url,String[] context){
		SMSService smsService;
		try {
			smsService = getSMSService();
			smsService.sendWebPushOneByOne(phoneNumbers, url, context);
			return true;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean sendArraySmsByWapPush(String[] phoneNumber,String url,String context){
		SMSService smsService;
		try {
			smsService = getSMSService();
			smsService.sendArraySMSByWebPush(phoneNumber, url, context);
			return true;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static void main(String[] args) {
		String[] phoneNumbers = {"15268114857","13588296200"};
		String[] contexts = {"这是一条短信b31","这是一条短信b32"};
		String[] url = {"www.baidu.com","www.google.com"};
		
		boolean b1 =SMSServiceClient.sendSms("15268114857", "这是一条短信b1");
		System.out.println("b1:"+b1);
		
		boolean b2 =SMSServiceClient.sendArraySms(phoneNumbers, "这是一条短信b2");
		System.out.println("b2:"+b2);
		
		boolean b3 = SMSServiceClient.sendArraySms(phoneNumbers, contexts);
		System.out.println("b3:"+b3);
		
		
		boolean b4 = SMSServiceClient.sendWapPush("15268114857", "www.baidu.com", "这是一条短信b4");
		System.out.println("b4:"+b4);
		
		boolean b5 = SMSServiceClient.sendArraySmsByWapPush(phoneNumbers, "www.baidu.com", "这是一条短信b5");
		System.out.println("b5:"+b5);
		
		boolean b6 = SMSServiceClient.sendArraySmsByWapPush(phoneNumbers, url, contexts);
		System.out.println("b6:"+b6);
		
	}
}
