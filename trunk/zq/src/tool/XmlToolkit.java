package tool;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlToolkit {

	private static Log log = LogFactory.getLog(XmlToolkit.class);
	private static final String VALID_XML_HEAD = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		
	/**
	 * 生成发送短消息的xml字符串
	 * @param phoneList  手机号列表
	 * @param content 发送的内容
	 * @return
	 */
	public static String createXmlForSMS(ArrayList<String> phoneList, String content) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("root");
		String toList = "";
		
		for (int index = 0; index < phoneList.size(); index++) {
			toList += phoneList.get(index)+ ";";
		}
		Element server = root.addElement("server");
		server.addAttribute("request_sms", 
				phoneList.get(0)+ "_"+ content);
		Element to = root.addElement("to");
		to.setText(toList);
		Element text = root.addElement("text");
		text.setText(content);
		
		return document.asXML().trim();
	}
	
	public static String createXmlForSMS(String phoneNumber, String content) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("root");
		String toList = "";
		
		/*for (int index = 0; index < phoneList.size(); index++) {
			toList += phoneList.get(index)+ ";";
		}*/
		Element server = root.addElement("server");
		server.addAttribute("request_sms","");
		Element to = root.addElement("to");
		to.setText(phoneNumber);
		Element text = root.addElement("text");
		text.setText(content);
		
		return document.asXML().trim();
	}
	
	/**
	 * 创建回复给手机的消息
	 * @return
	 */
	public static String createXmlForResponse() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("root");
		Element server = root.addElement("Server");
		server.addAttribute("Response", "Feedback");
		server.addAttribute("state", "00");
		
		return document.asXML().trim();
	}
	
	/**
	 * 字符串转为字符数组流
	 * @param xmlMessage xml字符串
	 * @return 字符数组流
	 */
	public static InputStream String2Stream(String xmlMessage) {
		byte[] buffer = null;		
		if(!xmlMessage.startsWith(VALID_XML_HEAD)
				&& xmlMessage.length() < 40) {
			log.warn("xml文件格式不规范或长度不够");
			return null;
		}
//		buffer = xmlMessage.getBytes();
		try {
			buffer = xmlMessage.getBytes("GBK");			
		} catch (UnsupportedEncodingException e) {
			log.error("编码转换异常");
		}
		
		InputStream in = new ByteArrayInputStream(buffer);
		return in;
	}
}
