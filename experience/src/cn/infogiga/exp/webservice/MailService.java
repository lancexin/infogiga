package cn.infogiga.exp.webservice;

public interface MailService {
	public boolean sendHtmlMail(String to[],String subject,String sendHtml);
	public boolean sendSingleHtmlMail(String to,String subject,String sendHtml);
}
