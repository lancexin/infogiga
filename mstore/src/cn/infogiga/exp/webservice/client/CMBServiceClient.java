package cn.infogiga.exp.webservice.client;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.xfire.client.Client;


import cindy.util.ProperiesReader;

public class CMBServiceClient {
	private static final Log log = LogFactory.getLog(CMBServiceClient.class);
	
	public static String send(String webServiceUrl,String host,String sendStr){
		String callback = null;
		try {
			Client client = new Client(new URL(webServiceUrl));
			Object[] objArray = new Object[2];
			objArray[0] = sendStr;
		    objArray[1] = host;
		    Object[] results = client.invoke("sendMsg", objArray);
		    callback = (String) results[0];
		} catch (MalformedURLException e) {
			log.error("发送boss webservice信息出现错误!", e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error("发送boss webservice信息出现错误!", e);
			e.printStackTrace();
		}
		return callback;
	}
	
	public static String send(String host,String sendStr){
		ProperiesReader reader = ProperiesReader.getInstence("config.properties");
		String callback = null;
		try {
			Client client = new Client(new URL(reader.getStringValue("send_host")));
			Object[] objArray = new Object[2];
			objArray[0] = sendStr;
			objArray[1] = host;
			Object[] results = client.invoke("sendMsg", objArray);
			callback = (String) results[0];
		} catch (MalformedURLException e) {
			log.error("发送boss webservice信息出现错误!", e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error("发送boss webservice信息出现错误!", e);
			e.printStackTrace();
		}
		return callback;
	}
	
	public static String send(String sendStr){
		System.out.println("发送boss的信息："+sendStr);
		ProperiesReader reader = ProperiesReader.getInstence("config.properties");
		String callback = null;
		try {
			Client client = new Client(new URL(reader.getStringValue("send_host")));
			Object[] objArray = new Object[2];
			objArray[0] = sendStr;
			objArray[1] = reader.getStringValue("cmb_host");
			Object[] results = client.invoke("sendMsg", objArray);
			callback = (String) results[0];
		} catch (MalformedURLException e) {
			log.error("发送boss webservice信息出现错误!", e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error("发送boss webservice信息出现错误!", e);
			e.printStackTrace();
		}
		return callback;
	}
	
	public static void main(String[] s) throws UnsupportedEncodingException{
		String str = "<?xml version='1.0' encoding='UTF-8'?>"+
					"<INTF>"+
						"<OPER_INFO>"+
							"<OP_ID>hzxj</OP_ID>"+
							"<DOMAIN_ID>40</DOMAIN_ID>"+
							"<PASSWORD>fH34ovIe</PASSWORD >"+
						"</OPER_INFO >"+
						"<INTF_PARAMS>"+
							"<INTF_CODE>1094</INTF_CODE>"+
							"<BILL_ID></BILL_ID>"+
							"<PARENT_MENU_ID></PARENT_MENU_ID>"+
							"<EXT_NOTES></EXT_NOTES>"+ 
							"<EXT_NOTES_1></EXT_NOTES_1>"+
						"</INTF_PARAMS >"+
					"</INTF>";


		/*String str = "<?xml version='1.0' encoding='UTF-8'?>"+
		"<INTF>"+
			"<OPER_INFO>"+
				"<OP_ID>hzxj</OP_ID>"+
				"<DOMAIN_ID>40</DOMAIN_ID>"+
				"<PASSWORD>fH34ovIe</PASSWORD>"+
			"</OPER_INFO>"+
			"<INTF_PARAMS>"+
				"<INTF_CODE>1090</INTF_CODE>"+
				"<BILL_ID>13454479574</BILL_ID>"+
				"<USER_PWD>123456</USER_PWD>"+
				"<EXT_NOTES></EXT_NOTES>"+
				"<EXT_NOTES_1></EXT_NOTES_1>"+
			"</INTF_PARAMS>"+
		"</INTF>";*/
		
		/*String str = "<?xml version='1.0' encoding='UTF-8'?>"+
					"<INTF>"+
						"<OPER_INFO>"+
							"<OP_ID>hzxj</OP_ID>"+
							"<DOMAIN_ID>40</DOMAIN_ID>"+
							"<PASSWORD>fH34ovIe</PASSWORD >"+
						"</OPER_INFO >"+
						"<INTF_PARAMS>"+
							"<INTF_CODE>1005</INTF_CODE>"+
							"<MENU_ID>20888</MENU_ID>"+
							"<BILL_ID>13454471133</BILL_ID>"+
		
							"<EXT_NOTES></EXT_NOTES>"+ 
							"<EXT_NOTES_1></EXT_NOTES_1>"+
						"</INTF_PARAMS >"+
					"</INTF>";*/
		/*String str = "<?xml version='1.0' encoding='UTF-8'?>"+
				"<INTF>"+
				"<OPER_INFO>"+
					"<OP_ID>sys_xywty</OP_ID>"+
					"<DOMAIN_ID>2</DOMAIN_ID>"+
					"<PASSWORD>Aa888888</PASSWORD >"+
				"</OPER_INFO >"+
				"<INTF_PARAMS>"+
					"<INTF_CODE>1006</INTF_CODE>"+
					"<MENU_ID>11892</MENU_ID>"+
					"<BILL_ID>15268114857</BILL_ID>"+
		
					"<EXT_NOTES></EXT_NOTES>"+ 
					"<EXT_NOTES_1></EXT_NOTES_1>"+
				"</INTF_PARAMS >"+
			"</INTF>";*/
		/*String str = "<?xml version='1.0' encoding='UTF-8'?>"+
				"<INTF>"+
				    "<OPER_INFO>"+
				        "<OP_ID>admin2</OP_ID>"+
				        "<DOMAIN_ID>2</DOMAIN_ID>"+
				        "<PASSWORD>123456</PASSWORD>"+
				    "</OPER_INFO>"+
					"<INTF_PARAMS>"+
						"<INTF_CODE>1005</INTF_CODE>"+
						"<MENU_ID>10098</MENU_ID>"+
						"<BILL_ID></BILL_ID>"+
						"<EXT_NOTES></EXT_NOTES>"+
						"<EXT_NOTES_1></EXT_NOTES_1>"+
					"</INTF_PARAMS>"+
				"<INTF>";*/
		String ss =CMBServiceClient.send("http://192.168.1.2:8888/cmbs/cmb.ws?wsdl", "http://10.70.201.48:8080/intf/IntfService", str);
		//String sss = new String(ss.getBytes(),"utf-8");
		System.out.print(ss);
		//ss = new String("utf-8");
	}
}
