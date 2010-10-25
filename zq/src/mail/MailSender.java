package mail;

import java.io.File;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;

import bean.MailBean;

/**
 * 邮件发送程序
 * @author ya
 */
public class MailSender {
	
	private static Logger log = Logger.getLogger(MailSender.class);
	
	private String smtpHost;
	private String userName;
	private String password;
	private String from;
	private String to;
	private String subject; //主题
	private String msg = "报表";
	private String fileName; //附件文件名
	
	public MailSender(String subject, String fileName, MailBean config) {
		this.subject = subject;
		this.fileName = fileName;
		
		smtpHost = config.getSmtpHost();
		userName = config.getUserName();
		password = config.getPassword();
		from = config.getFrom();
		to = config.getTo();
	}
	
	/**
	 * 发送
	 */
	public void send() {
		MultiPartEmail email = new MultiPartEmail();//发送带附件的				
		email.setHostName(smtpHost);//主机名
		email.setAuthentication(userName, password);//用户名，密码
		email.setCharset("UTF-8");//编码
		try {
			email.setFrom(from);//来自
			email.addTo(to);//发往
			email.setSubject(subject);//主题
			email.setMsg(msg);//内容
			
			File file = new File(fileName);//附件地址
			
			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath(file.getPath());//附件路径
			attachment.setName(file.getName());//附件名字
			attachment.setDescription("描述");//附件描述
			attachment.setDisposition(EmailAttachment.ATTACHMENT);//附件类型
			
			email.attach(attachment);//附属上
			email.send();//发送
		} catch(Exception e) {
			log.error("邮件发送失败："+ e);
		}		
	}
}
