package mail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimerTask;

import manage.Report;

import org.apache.log4j.Logger;

import tool.Toolkit;
import bean.MailBean;
import bean.RegionBean;
import bean.ReportBean;
import bean.ResultBean;
import data.Database;

/**
 * 邮件发送任务
 * @author ya
 */
public class MailTask extends TimerTask {
	
	private static Logger log = Logger.getLogger(MailTask.class);
	
	private MailBean config; //邮件配置
	private MailSender sender;
	private static int TIME_HOUR;//几点执行
	private static int DAY_CYC;//间隔几天
	private static int cyc = 0;
	private Database db = new Database();
	private Report report = new Report();
	private String path = "";
	private String fileName = "";
	
	public MailTask(String path) {
		this.path = path;
		config = db.getMailBean();
		TIME_HOUR = Integer.parseInt(config.getHour());
		DAY_CYC = Integer.parseInt(config.getCyc());
	}

	@Override
	public void run() {
		Calendar c = Calendar.getInstance();		
		if(TIME_HOUR == c.get(Calendar.HOUR_OF_DAY)) {//到达指定时间
			cyc++;
			if(cyc != DAY_CYC) return; 
			fileName = buildReport();
			sender = new MailSender(Toolkit.getToday(), fileName, config);			
			sender.send(); //发送
			log.info("发送邮件:"+fileName);
		}
	}
	
	/**
	 * 生成报表作为附件
	 * @return
	 */
	private String buildReport() {
		ArrayList<ReportBean> list = db.getReport("", "", "", "", "", "", Toolkit.getToday(0), Toolkit.getToday(1), "");
		ArrayList<ArrayList<ResultBean>> resultList = new ArrayList<ArrayList<ResultBean>>();
		for(RegionBean region: db.getRegions()) {//所有统计数据
			resultList.add(Toolkit.getHeaderList(
					db.getReport("", "", region.getRegionCode(), "", "", "", Toolkit.getToday(0), Toolkit.getToday(1), "")));
		}
		String fileName = path+ "\\"+ Toolkit.getNow()+ ".xls";
		report.setList(list);
		report.setResultList(resultList);
		report.setFile(fileName);
		report.build();
		return fileName;
	}
}
