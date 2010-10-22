package cn.infogiga.exp.service;

import java.util.Date;

import cn.infogiga.exp.dao.ExperienceDAO;
import cn.infogiga.exp.pojo.Sms;
import cn.infogiga.exp.webservice.SMSServiceClient;



public class SmsService {
	
	ExperienceDAO experienceDAO;

	public ExperienceDAO getExperienceDAO() {
		return experienceDAO;
	}

	public void setExperienceDAO(ExperienceDAO experienceDAO) {
		this.experienceDAO = experienceDAO;
	}
	
	
	public boolean sendSMS(String phoneNumber,String context){
		boolean bl = SMSServiceClient.sendSms(phoneNumber,context);
		if(bl){
			Sms sms = new Sms();
			sms.setContext(context);
			sms.setPhoneNumber(phoneNumber);
			sms.setSendTime(new Date());
			sms.setStatus(1);
			experienceDAO.save(sms);
		}
		return bl;
	}

	public boolean sendArraySMS(String[] phoneNumbers,String context){
		return SMSServiceClient.sendArraySms(phoneNumbers, context);
	}

	
}	
