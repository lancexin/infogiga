package cn.infogiga.sms.webservice;

public interface SMSService {
	public boolean sendSMS(String phoneNumber,String context);
	
	public boolean sendSMSByWebPush(String phoneNumber,String url,String context);
}
