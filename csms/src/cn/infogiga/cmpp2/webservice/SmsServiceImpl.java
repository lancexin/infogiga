package cn.infogiga.cmpp2.webservice;

import java.io.UnsupportedEncodingException;

import cn.infogiga.cmpp2.server.SPServer;


public class SmsServiceImpl implements SMSService{
	
	SPServer spServer;
	


	public void setSpServer(SPServer spServer) {
		this.spServer = spServer;
	}

	public boolean sendSMS(String phoneNumber, String context) {
		boolean b = false;
		try {
			spServer.sendMessage(phoneNumber, context);
			b = true;
		} catch (RuntimeException e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

	public boolean sendSMSByWebPush(String phoneNumber, String url,String context) {
		boolean b = false;
		try {
			spServer.sendMessageByWapPush(phoneNumber, url, context);
			b = true;
		} catch (RuntimeException e) {
			b = false;
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}

}
