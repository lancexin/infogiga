package cn.infogiga.exp.service;

import cn.infogiga.exp.webservice.MailServiceClient;

public class EmailService {
	public boolean sendSingleMail(String to,String subject,String sendHtml){
		return MailServiceClient.sendSingleHtmlMail(to,subject,sendHtml);
	}
}	
