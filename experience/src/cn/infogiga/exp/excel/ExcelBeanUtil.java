package cn.infogiga.exp.excel;

import java.util.ArrayList;
import java.util.List;

import cindy.util.DateUtil;
import cn.infogiga.exp.pojo.Downloadstat;
import cn.infogiga.exp.pojo.Downloadtype;
import cn.infogiga.exp.pojo.Employee;
import cn.infogiga.exp.pojo.Equipment;
import cn.infogiga.exp.pojo.Menu;
import cn.infogiga.exp.pojo.Phonebrand;
import cn.infogiga.exp.pojo.Phonetype;
import cn.infogiga.exp.pojo.Scene;
import cn.infogiga.exp.pojo.Softinfo;
import cn.infogiga.exp.pojo.Statistics;
import cn.infogiga.exp.pojo.Sysinfo;
import cn.infogiga.exp.pojo.Team;

public class ExcelBeanUtil {
	
	
	private static StatisticsExcel  getStatisticsExcel(Statistics statistics,Integer number){
		StatisticsExcel  excel = new StatisticsExcel();
		excel.setNumber(number);
		Equipment equipment = statistics.getEquipment();
		excel.setEquiName(equipment==null?"-":(equipment.getEquiName()==null?"-":equipment.getEquiName()));

		excel.setHappenTime(statistics.getHappenTime()==null?"-":DateUtil.getDateString(statistics.getHappenTime(), DateUtil.NOW_TIME));
		//excel.setPhoneNumber(statistics.getPhoneNumber()==null?"-":statistics.getPhoneNumber());
		excel.setCustomPhone(statistics.getPhoneNumber()==null?"-":statistics.getPhoneNumber());
		Menu menu = statistics.getMenu();
		excel.setMenuName(menu==null?"-":(menu.getMenuName()==null?"-":menu.getMenuName()));
		Scene scene = statistics.getScene();
		excel.setSceneName(scene==null?"-":(scene.getSenceName()==null?"-":scene.getSenceName()));
		Employee employee = statistics.getEmployee();
		excel.setEmpName(employee==null?"-":(employee.getEmpName()==null?"-":employee.getEmpName()));
		excel.setEmpNo(employee==null?"-":(employee.getEmpNo()==null?"-":employee.getEmpNo()));
		Team team = employee.getTeam();
		excel.setTeamName(team==null?"-":(team.getTeamName()==null?"-":team.getTeamName()));
		
		excel.setComboName(statistics.getComboName()==null?"-":statistics.getComboName());
		return excel;
	}
	
	
	private static DownloadstatExcel  getStatisticsExcel(Downloadstat downloadstat,Integer number){
		DownloadstatExcel  excel = new DownloadstatExcel();
		excel.setNumber(number);
		Equipment equipment = downloadstat.getEquipment();
		excel.setEquiName(equipment==null?"-":(equipment.getEquiName()==null?"-":equipment.getEquiName()));

		excel.setAddTime(downloadstat.getAddTime()==null?"-":DateUtil.getDateString(downloadstat.getAddTime(), DateUtil.NOW_TIME));
		excel.setPhoneNumber(downloadstat.getPhoneNumber()==null?"-":downloadstat.getPhoneNumber());

		Employee employee = downloadstat.getEmployee();
		excel.setEmpName(employee==null?"-":(employee.getEmpName()==null?"-":employee.getEmpName()));
		excel.setEmpNo(employee==null?"-":(employee.getEmpNo()==null?"-":employee.getEmpNo()));
		Team team = employee.getTeam();
		excel.setTeamName(team==null?"-":(team.getTeamName()==null?"-":team.getTeamName()));
		Downloadtype downloadtype = downloadstat.getDownloadtype();
		excel.setDownloadType(downloadtype.getTypeName());
		
		Phonetype phonetype = downloadstat.getPhonetype();
		excel.setPhonetype(phonetype==null?"-":phonetype.getPhonetypeName());
		if(phonetype != null){
			Phonebrand phonebrand = phonetype.getPhonebrand();
			excel.setPhonebrand(phonebrand==null?"-":phonebrand.getPhonebrandName());
		}
		Softinfo softinfo = downloadstat.getSoftinfo();
		excel.setSoftName(softinfo==null?"-":softinfo.getSoftName());
		excel.setAddTime(DateUtil.getDateString(downloadstat.getAddTime(), DateUtil.NOW_TIME));
		return excel;
	}
	
	
	public static List<StatisticsExcel> getStatisticsExcelList(List<Statistics> list){
		
		List<StatisticsExcel> callback = new ArrayList<StatisticsExcel>();
		int size = list.size();
		Statistics statistics = null;
		for(int i=0;i<size;i++){
			statistics = list.get(i);
			callback.add(getStatisticsExcel(statistics,i+1));
		}
		
		return callback;
	}
	
	public static List<DownloadstatExcel> getDownloadstatExcelList(List<Downloadstat> list){
		
		List<DownloadstatExcel> callback = new ArrayList<DownloadstatExcel>();
		int size = list.size();
		Downloadstat statistics = null;
		for(int i=0;i<size;i++){
			statistics = list.get(i);
			callback.add(getStatisticsExcel(statistics,i+1));
		}
		
		return callback;
	}
	
}
