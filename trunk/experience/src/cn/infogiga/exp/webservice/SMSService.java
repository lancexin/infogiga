package cn.infogiga.exp.webservice;

public interface SMSService {
	public boolean sendSMS(String phoneNumber,String context);
	
	public boolean sendSMSByWebPush(String phoneNumber,String url,String context);
	
	public boolean sendArraySMS(String[] phoneNumbers,String context);
	
	public boolean sendArraySMSByWebPush(String[] phoneNumbers,String url,String context);
	
	public boolean sendSMSOneByOne(String[] phoneNumbers,String[] contexts);
	
	public boolean sendWebPushOneByOne(String[] phoneNumbers,String[] url,String[] context);
}
