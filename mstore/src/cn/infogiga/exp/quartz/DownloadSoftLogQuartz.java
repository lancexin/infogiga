package cn.infogiga.exp.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.infogiga.exp.service.ExperienceService;

public class DownloadSoftLogQuartz extends QuartzJobBean{
	
	public ExperienceService experienceService;

	public void setExperienceService(ExperienceService experienceService) {
		this.experienceService = experienceService;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		System.out.println("系统执行了一次");
		
	}

}
