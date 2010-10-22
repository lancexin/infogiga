package cn.infogiga.exp.webservice;

import java.net.MalformedURLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

import cindy.util.config.ProperiesReader;

public class MailServiceClient {
	private static final Log log = LogFactory.getLog(MailServiceClient.class);
	
	public static MailService getMailService() throws MalformedURLException{
		ProperiesReader properties = ProperiesReader.getInstence("config.properties");
		Service servicemodel =new ObjectServiceFactory().create(MailService.class); 
	    XFireProxyFactory factory =new XFireProxyFactory(XFireFactory.newInstance().getXFire()); 
	    MailService testService = (MailService)factory.create(servicemodel,properties.getStringValue("email.host"));
		return testService;
	}
	
	public static boolean sendSingleHtmlMail(String to,String subject,String sendHtml){
		MailService mailService;
		try {
			mailService = getMailService();
			return mailService.sendSingleHtmlMail(to, subject, sendHtml);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean sendHtmlMail(String[] to,String subject,String sendHtml){
		MailService mailService;
		try {
			mailService = getMailService();
			return mailService.sendHtmlMail(to, subject, sendHtml);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
}
