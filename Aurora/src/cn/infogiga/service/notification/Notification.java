package cn.infogiga.service.notification;

import java.util.ArrayList;

public interface Notification {

	public boolean sendSMS(ArrayList<String> phoneList, String content);
	public boolean sendMMS(ArrayList<MMSBean> mmsList);
	public boolean sendMMS_n(MMSBean mms);
	public boolean sendMail(ArrayList<MailBean> mails);
	public boolean sendMail_n(MailBean mail);
}
