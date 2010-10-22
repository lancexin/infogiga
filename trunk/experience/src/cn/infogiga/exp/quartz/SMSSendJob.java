package cn.infogiga.exp.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.infogiga.exp.service.ExperienceService;
import cn.infogiga.exp.webservice.SMSServiceClient;

public class SMSSendJob extends QuartzJobBean{
	
	ExperienceService experienceService;
	
	public void setExperienceService(ExperienceService experienceService) {
		this.experienceService = experienceService;
	}


	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		SMSBean bean = (SMSBean) context.getMergedJobDataMap().get("bean");
		System.out.println("任务执行一次,phoneNumbers:"+bean.getPhoneNumber()+" context:"+bean.getContext());
		boolean bl = false;
		switch (bean.getSendType()) {
			case SMSBean.MMS_SINGLE:
				bl = SMSServiceClient.sendSms(bean.getPhoneNumber(),bean.getContext());
				break;
			case SMSBean.MMS_ARRAY:
				bl = SMSServiceClient.sendArraySms(bean.getPhoneNumbers(),bean.getContext());
				//bl = true;
				break;
				
			case SMSBean.MMS_SINGLE_WAPPUSH:
				bl = SMSServiceClient.sendWapPush(bean.getPhoneNumber(), bean.getUrl(), bean.getContext());
				break;
				
			case SMSBean.MMS_ARRAY_WAPPUSH:
				bl = SMSServiceClient.sendArraySmsByWapPush(bean.getPhoneNumbers(), bean.getUrl(), bean.getContext());
				break;
				
			default:
				break;
		}
		if(bl){//是否发送成功
			experienceService.updateSMSArrayStatus(bean.getSmsarrayID(), 1);
		}else{
			experienceService.updateSMSArrayStatus(bean.getSmsarrayID(), 2);
		}
	}
	
	
}
