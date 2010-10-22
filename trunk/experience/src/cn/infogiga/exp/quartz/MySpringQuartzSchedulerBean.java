package cn.infogiga.exp.quartz;

import java.util.Date;
import java.util.Map;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;

public class MySpringQuartzSchedulerBean {
	
	private Scheduler scheduler;
	
	private JobDetail jobDetail;
		
	private SchedulerFactory schedulerFactory;
	
	private QuartzAfterInit sendJobAfterInit;

	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
		
	}

	public void setSchedulerFactory(SchedulerFactory schedulerFactory) {
		this.schedulerFactory = schedulerFactory;
		startScheduler();
	}
	
	public void setSendJobAfterInit(QuartzAfterInit sendJobAfterInit) {
		this.sendJobAfterInit = sendJobAfterInit;
	}
	
	private void startScheduler(){
		try {
			scheduler = schedulerFactory.getScheduler();
			scheduler.addJob(jobDetail, true);
			scheduler.start();
			if(sendJobAfterInit != null){
				sendJobAfterInit.afterInit(this);
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addTrigger(Trigger trigger){
		try {
			trigger.setJobName(jobDetail.getName());
			trigger.setJobGroup(jobDetail.getGroup());
			scheduler.scheduleJob(trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addTrigger(String triggerName,Map jobMap,Date startTime,long repeatInterval,int repeatCount){
		SimpleTrigger trigger = new SimpleTrigger();
		trigger.setName(triggerName);
		trigger.setGroup(jobDetail.getGroup());
		trigger.getJobDataMap().putAll(jobMap);
		trigger.setStartTime(startTime);
		trigger.setRepeatInterval(repeatInterval);
		trigger.setRepeatCount(repeatCount);
		addTrigger(trigger);
	}

	public void removeTrigger(String triggerName){
		try {
			scheduler.unscheduleJob(triggerName, jobDetail.getGroup());
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void pauseTrigger(String triggerName){
		try {
			scheduler.pauseTrigger(triggerName,  jobDetail.getGroup());
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void resetTrigger(String triggerName,Map jobMap,Date startTime,long repeatInterval,int repeatCount){
		 try {
			SimpleTrigger trigger = (SimpleTrigger) scheduler.getTrigger(triggerName,jobDetail.getGroup());
			trigger.setName(triggerName);
			trigger.setGroup(jobDetail.getGroup());
			trigger.getJobDataMap().putAll(jobMap);
			trigger.setStartTime(startTime);
			trigger.setRepeatInterval(repeatInterval);
			trigger.setRepeatCount(repeatCount);
			scheduler.resumeTrigger(triggerName, jobDetail.getGroup());
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
