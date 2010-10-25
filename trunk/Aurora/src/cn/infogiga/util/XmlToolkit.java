package cn.infogiga.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.infogiga.service.notification.MMSBean;
import cn.infogiga.service.qrcode.QrcodeBean;

public class XmlToolkit {

	private static Log log = LogFactory.getLog(XmlToolkit.class);
	private static final String VALID_XML_HEAD = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
	
	/**
	 * 生成二维码图片的xml字符串
	 * @param qrcodes QrcodeBean的list
	 * @return xml字符串
	 */
	public static String createXmlForQrcode(ArrayList<QrcodeBean> qrcodes) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("root");
		
		for (int index = 0; index < qrcodes.size(); index++) {	
			QrcodeBean qrcode = qrcodes.get(index);
			Element server = root.addElement("server");
			server.addAttribute("request_2code", 
					qrcode.getQrcodePicName());
			server.addAttribute("twocode", 
					qrcode.getQrcode());
		}
		return document.asXML();
	}
	
	/**
	 * 生成单个二维码
	 * @param qrcode
	 * @return
	 */
	public static String createXmlForQrcode(QrcodeBean qrcode) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("root");
		Element server = root.addElement("server");
		server.addAttribute("request_2code", 
				qrcode.getQrcodePicName());
		server.addAttribute("twocode", 
				qrcode.getQrcode());
		return document.asXML();
	}
	
	/**
	 * 生成发送彩信的xml字符串
	 * @param mmsList MMSBean的列表
	 * @return
	 */
	public static String createXmlForMMS(ArrayList<MMSBean> mmsList) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("root");
		
		for (int index = 0; index < mmsList.size(); index++) {
			MMSBean mms = mmsList.get(index);
			Element server = root.addElement("server");
			server.addAttribute("request_mms", 
					mms.getTemplateName());
			server.addAttribute("id", 
					mms.getId());
			server.addAttribute("twocode", 
					mms.getQrcode());
			Element to = server.addElement("to");
			to.setText(mms.getPhoneNumber());
			Element text = server.addElement("text");
			text.setText(mms.getContent());
		}
		
		return document.asXML();
	}
	
	/**
	 * 展馆介绍的彩信
	 * @param mms
	 * @return
	 */
	public static String createXmlForMMS_n(MMSBean mms) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("root");
		Element server = root.addElement("server");
		server.addAttribute("request_mms", mms.getTemplateName());
		server.addAttribute("id", mms.getId());
		Element to = server.addElement("to");
		to.setText(mms.getPhoneNumber());
		
		return document.asXML();
	}
	
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
		try {
			buffer = xmlMessage.getBytes("GBK");			
		} catch (UnsupportedEncodingException e) {
			log.error("编码转换异常");
		}
		
		InputStream in = new ByteArrayInputStream(buffer);
		return in;
	}
}
