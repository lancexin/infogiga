package cn.infogiga.quartz.service;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJob  implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		 Date date = new Date();
		 //MethodInvokingTimerTaskFactoryBean
		 System.out.println("触发一个任务，现在时间："+date.toLocaleString()+" "+QuartzJob.class.getName());
	}

}
