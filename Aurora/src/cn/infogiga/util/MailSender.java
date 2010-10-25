package cn.infogiga.util;

import java.io.File;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;

import cn.infogiga.service.notification.MailBean;

public class MailSender {
	
	private static Logger log = Logger.getLogger(MailSender.class);
	
	public static void send(MailBean mail) {
//		MultiPartEmail email = new MultiPartEmail();//发送带附件的
		HtmlEmail email = new HtmlEmail();
		email.setHostName(mail.getSmtpHost());//主机名
		email.setAuthentication(mail.getUserName(), mail.getPassword());//用户名，密码
		email.setCharset("UTF-8");//编码
		try {
			email.setFrom(mail.getFrom());//来自
			email.addTo(mail.getTo());//发往
			email.setSubject(mail.getSubject());//主题
			email.setHtmlMsg(mail.getMsg());//内容

			if(!StringToolkit.isBlank(mail.getFileName())) {
				File file = new File(mail.getFileName());//附件地址
				
				EmailAttachment attachment = new EmailAttachment();
				attachment.setPath(file.getPath());//附件路径
				attachment.setName(file.getName());//附件名字
				attachment.setDisposition(EmailAttachment.ATTACHMENT);//附件类型
			
				email.attach(attachment);//附属上
			}
			email.send();//发送
		} catch(Exception e) {
			log.error("邮件发送失败："+ e);
		}		
	}
}
