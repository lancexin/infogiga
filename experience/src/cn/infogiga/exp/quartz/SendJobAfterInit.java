package cn.infogiga.exp.quartz;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.infogiga.exp.pojo.Smsarray;
import cn.infogiga.exp.service.ExperienceService;
import cindy.util.DateUtil;

public class SendJobAfterInit implements QuartzAfterInit{
	
	ExperienceService experienceService;

	public void setExperienceService(ExperienceService experienceService) {
		this.experienceService = experienceService;
	}
	public void afterInit(MySpringQuartzSchedulerBean scheduler) {
		//获得所有未完成的job
		List<Smsarray> list = experienceService.getExperienceDAO().findByProperty(Smsarray.class, "status", 0);
		int size = list.size();
		Smsarray smsarray;
		for(int i=0;i<size;i++){
			smsarray = list.get(i);
			Date sendTime = smsarray.getSendTime();
			String context = smsarray.getContext();
			String[] phoneNumbers = smsarray.getPhoneNumbers().split(",");
			Integer smsarrayID = smsarray.getSmsId();
			
			SMSBean bean = new SMSBean();
			bean.setPhoneNumbers(phoneNumbers);
			bean.setSendType(SMSBean.MMS_ARRAY);
			bean.setContext(context);
			bean.setSmsarrayID(smsarrayID);
			Map beanMap = new HashMap();
			beanMap.put("bean", bean);
			System.out.println(i+" 添加一个任务：phoneNmubers:"+smsarray.getPhoneNumbers()+" sendTime:"+DateUtil.getDateString(sendTime, DateUtil.NOW_TIME));
			scheduler.addTrigger("sms"+smsarrayID, beanMap, sendTime, 0, 0);
		}
		
	}

}
