package cn.infogiga.service.notification;

import java.util.ArrayList;
import cn.infogiga.util.Config;
import cn.infogiga.util.HttpToolkit;
import cn.infogiga.util.MailSender;
import cn.infogiga.util.XmlToolkit;

public class NotificationImpl implements Notification {		
	
	/* 发送预约的彩信
	 * @see cn.infogiga.service.notification.Notification#sendMMS(java.util.ArrayList)
	 */
	public boolean sendMMS(ArrayList<MMSBean> mmsList) {
		String xmlString = XmlToolkit.createXmlForMMS(mmsList);
		System.out.println(xmlString);
		return HttpToolkit.postMessage(xmlString, Config.HOST_M, Config.PORT);
	}
	
	/* 
	 * 发送展馆介绍的彩信，只需要模板名字，手机号码和id
	 * @see cn.infogiga.service.notification.Notification#sendMMS_n(cn.infogiga.service.notification.MMSBean)
	 */
	public boolean sendMMS_n(MMSBean mms) {
		String xmlString = XmlToolkit.createXmlForMMS_n(mms);
		System.out.println(xmlString);
		return HttpToolkit.postMessage(xmlString, Config.HOST_M, Config.PORT);
	}
	
	public boolean sendMail(ArrayList<MailBean> mails) {
		for(MailBean mail: mails) {
			MailSender.send(mail);
		}
		return true;
	}

	public boolean sendMail_n(MailBean mail) {
		MailSender.send(mail);
		return true;
	}

	public boolean sendSMS(ArrayList<String> phoneList, String content) {
		String xmlString = XmlToolkit.createXmlForSMS(phoneList, content);
//		String old = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><server request=\"13575754002_HelloWorld\"/></root>";
		System.out.println(xmlString);
		return HttpToolkit.postMessage(xmlString, Config.HOST_S, Config.PORT);
	}
}
