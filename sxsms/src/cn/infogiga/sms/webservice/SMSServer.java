package cn.infogiga.sms.webservice;

import com.jasson.im.api.APIClient;

public class SMSServer {
	private APIClient handler = new APIClient();
	
	public static final int RUNNING = 0;
	
	public static final int STOP = 0x8001;
	
	private static int status = STOP;
	
	ProperiesReader properies = ProperiesReader.getInstence("config.properties");
	
	public long getSmType(){
		return Long.parseLong(properies.getStringValue("sms.smType"));
	}
	
	public long getSmId(){
		return Long.parseLong(properies.getStringValue("sms.smId"));
	}
	
	public APIClient getAPIClient(){
		return handler;
	}
	
	public boolean init(){
		if(status == APIClient.IMAPI_SUCC){
			return true;
		}
		release();
		status = handler.init(properies.getStringValue("sms.host"), 
				  properies.getStringValue("sms.name"), 
				  properies.getStringValue("sms.pwd"), 
				  properies.getStringValue("sms.apiId"), 
				  properies.getStringValue("sms.dbName"));
		if(status == APIClient.IMAPI_SUCC){
			return true;
		}
		return false;
	}
	
	public boolean sendWapPush(String phoneNumber,String url,String context){
		if(status != RUNNING){
			init();
		}
		int i = handler.sendSM(phoneNumber,context,getSmId(),url);
		if(i == APIClient.IMAPI_SUCC){
			return true;
		}
		return false;
	}
	
	public boolean sendSMS(String phoneNumber,String context){
		if(status != RUNNING){
			init();
		}
		int i = handler.sendSM(phoneNumber,context,getSmId());
		System.out.println(i);
		if(i == APIClient.IMAPI_SUCC){
			return true;
		}
		return false;
	}
	
	public void release(){
		handler.release();
		status = STOP;
	}
	
	public static void main(String[] args) {
		SMSServer server = new SMSServer();
		boolean b = server.init();
		if(b){
			int i = server.getAPIClient().sendSM("15268114857", "aaa", server.getSmId(), "wap.shou.com");
			System.out.println(i);
		}
		
	}
	
}
