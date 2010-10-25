package cn.infogiga.quartz.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.infogiga.bean.Customer;
import cn.infogiga.service.AppointmentService;
import cn.infogiga.service.notification.MailBean;
import cn.infogiga.service.notification.Notification;
import cn.infogiga.util.Config;
import cn.infogiga.util.StringToolkit;

 public class ParseFileQuartz extends QuartzJobBean {
	private AppointmentService appointmentService;
	private Notification notification;
	
	public void setAppointmentService(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
 
    public void setNotification(Notification notification) {
		this.notification = notification;
	}

	@Override
    protected void executeInternal(JobExecutionContext jobexecutioncontext)

            throws JobExecutionException {    	
		
		boolean isSend = appointmentService.isSendMail();
		if(!isSend) return;
		List<Customer> list = appointmentService.getVisitedCustomer();
		ArrayList<MailBean> mails = new ArrayList<MailBean>();
		for(Customer customer: list) {
			MailBean mail = createMailBean();
			if(StringToolkit.isBlank(customer.getMail())) {
				mail.setTo(customer.getPhoneNumber()+"@139.com");
			} else {
				mail.setTo(customer.getMail());
			}
			mails.add(mail);
		}
		notification.sendMail(mails);		
    }

	private MailBean createMailBean() {
		MailBean mail = new MailBean();
		mail.setSmtpHost(Config.getValue("smtpHost"));
		mail.setFrom(Config.getValue("from"));
		mail.setUserName(Config.getValue("userName"));
		mail.setPassword(Config.getValue("password"));
		mail.setSubject(Config.getValue("subject"));
		mail.setMsg(Config.getValue("msg"));
		mail.setFileName(Config.getValue("fileName"));
		return mail;
	}
   
}