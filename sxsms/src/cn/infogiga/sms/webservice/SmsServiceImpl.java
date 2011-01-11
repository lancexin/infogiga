package cn.infogiga.sms.webservice;

public class SmsServiceImpl implements SMSService{
	
	SMSServer server;
	
	public SMSServer getServer() {
		return server;
	}

	public void setServer(SMSServer server) {
		this.server = server;
	}

	public boolean sendSMS(String phoneNumber, String context) {
		return server.sendSMS(phoneNumber, context);
		
	}

	public boolean sendSMSByWebPush(String phoneNumber, String url,String context) {
		return server.sendWapPush(phoneNumber, url, context);
		
	}

}
