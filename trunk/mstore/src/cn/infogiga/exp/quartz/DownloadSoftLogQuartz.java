package cn.infogiga.exp.quartz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cindy.util.DateUtil;
import cindy.util.ProperiesReader;
import cn.infogiga.exp.service.ExperienceService;
import cn.infogiga.pojo.Softdownloadstat;

public class DownloadSoftLogQuartz extends QuartzJobBean{
	
	public ExperienceService experienceService;

	public void setExperienceService(ExperienceService experienceService) {
		this.experienceService = experienceService;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		//删除所有wappush暂时统计信息
		boolean bl = experienceService.deleteTempDownloadstat();
		
		//导出每日热点统计信息
		Date yesterday = DateUtil.getYesterday();
		Date startTime = DateUtil.getStartOfDay(yesterday);
		Date endTime = DateUtil.getEndOfDay(yesterday);
		List<Softdownloadstat> aList = experienceService.getDownloadstatByDate(startTime, endTime);
		List<RDdownloadExcel> list = ExcelUtil.getRDdownloadExcelList(aList);
		String fileName = DateUtil.getDateString(yesterday, DateUtil.NOW_TIME3);
		File file = new File(ProperiesReader.getInstence("config.properties").getStringValue("soft.rdexport.url")+fileName+".xls");
		try {
			if(!file.exists()){
				file.createNewFile();
			}else{
				file.delete();
				file.createNewFile();
			}
			String[] title = {"省","市","公司","营业厅","用户名","软件","厂商","型号","时间","手机串号","安装结果"};
			ExcelCreatorUtil.exportExcel(new FileOutputStream(file),title, list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
