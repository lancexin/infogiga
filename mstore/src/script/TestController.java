package script;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import cn.infogiga.exp.pojo.Downloadstat;
import cn.infogiga.exp.pojo.Phonebrand;
import cn.infogiga.exp.quartz.ExcelCreatorUtil;
import cn.infogiga.exp.quartz.ExcelUtil;
import cn.infogiga.exp.quartz.RDdownloadExcel;
import cn.infogiga.pojo.Bissinusshall;
import cn.infogiga.pojo.City;
import cn.infogiga.pojo.Phonebrandcategory;
import cn.infogiga.pojo.Phonetype;
import cn.infogiga.pojo.Power;
import cn.infogiga.pojo.Soft;
import cn.infogiga.pojo.Softdownloadstat;
import cn.infogiga.pojo.Users;
import cn.infogiga.sd.dto.JsonSoftDownloadStat;

import scrpit.now.dao.MappingNewDAO;
import scrpit.now.dao.MappingOldDAO;
import cindy.page.beanutils.MyBeanUtils;
import cindy.util.DateUtil;
import cindy.util.ProperiesReader;

public class TestController {
	
	MappingOldDAO oldDAO;
	MappingNewDAO newDAO;
	
	public MappingOldDAO getOldDAO() {
		return oldDAO;
	}

	public void setOldDAO(MappingOldDAO oldDAO) {
		this.oldDAO = oldDAO;
	}

	public MappingNewDAO getNewDAO() {
		return newDAO;
	}

	public void setNewDAO(MappingNewDAO newDAO) {
		this.newDAO = newDAO;
	}
	
	
	@RequestMapping(value = "/mock2")
	public void startMock2(HttpServletRequest request,HttpServletResponse response){
		testPhoneTypeNewToOld();
	}
	
	public void startMock(HttpServletRequest request,HttpServletResponse response){
		System.out.println("mock start ...");
		
		
		//copyTeam();
		//copyEmployee();
		//copyEquipment();
		//copyPhonetype();
		/**
		 * 旧体验数据导入新系统
		 */
		//copyDownloadstat();
		/**
		 * 添加蓝牙mac地址
		 */
		//changePhoneNumber();
		/**
		 * 导出热点数据
		 */
		//exportRD();
		/**
		 * 导出每天体验数据
		 */
		//exportDownloadstat();
		System.out.println("mock end ...");
		
	}

	/**
	 * 导出热点数据
	 */
	private void exportRD(){
		Date startDate = DateUtil.stringToDate("2010-07-05", DateUtil.NOW_DATE);
		Date endDate = DateUtil.stringToDate("2010-12-30", DateUtil.NOW_DATE);
		while(DateUtil.isBefore(startDate, endDate)){
			createRDExcel(startDate,newDAO);
			startDate = DateUtil.getNextDay(startDate);
		}
	}
	
	private void copyEmployee(){
		List<cn.infogiga.exp.pojo.Employee> list = oldDAO.findAll(cn.infogiga.exp.pojo.Employee.class);
		int size = list.size();
		System.out.println(size);
		cn.infogiga.exp.pojo.Employee pt = null;
		for(int i=0;i<size;i++){
			pt = list.get(i);
			Users users = newDAO.findById(Users.class, pt.getEmployeeId());
			if(users != null){//过滤掉已经添加过的
				System.out.println("empId:"+pt.getEmployeeId());
				continue;
			}
			System.out.println("添加一个员工");
			users = new Users();
			users.setId(pt.getEmployeeId());
			users.setAddTime(pt.getAddTime());
			users.setStatus(1);
			users.setNickName(pt.getEmpName());
			users.setUserName(pt.getEmpNo());
			users.setPassWord(pt.getPassword());
			Power power = newDAO.findById(Power.class, 3);
			users.setPower(power);
			users.setPhoneNumber(pt.getPhoneNumber());
			Bissinusshall hall = new Bissinusshall();
			hall.setId(pt.getTeam().getTeamId());
			users.setBissinusshall(hall);
			newDAO.save(users);
		}
		
	}
	
	private void copyEquipment(){
		List<cn.infogiga.exp.pojo.Equipment> list = oldDAO.findAll(cn.infogiga.exp.pojo.Equipment.class);
		int size = list.size();
		cn.infogiga.exp.pojo.Equipment pt = null;
		for(int i=0;i<size;i++){
			pt = list.get(i);
			cn.infogiga.pojo.Equipment equipment = newDAO.findById(cn.infogiga.pojo.Equipment.class, pt.getEquipmentId());
			if(equipment != null){
				continue;
			}
			equipment = new cn.infogiga.pojo.Equipment();
			equipment.setId(pt.getEquipmentId());
			equipment.setAddTime(pt.getAddTime());
			equipment.setEquipmentCode(pt.getCode());
			equipment.setEquipmentName(pt.getEquiName());
			equipment.setMac(pt.getMac());
			
			Bissinusshall hall = new Bissinusshall();
			hall.setId(pt.getTeam().getTeamId());
			equipment.setBissinusshall(hall);
			
			equipment.setStatus(pt.getStatus());
			newDAO.save(equipment);
		}
	}
	
	private void copyTeam(){
		List<cn.infogiga.exp.pojo.Team> list = oldDAO.findAll(cn.infogiga.exp.pojo.Team.class);
		int size = list.size();
		cn.infogiga.exp.pojo.Team p1 = null;
		for(int i=0;i<size;i++){
			p1 = list.get(i);
			cn.infogiga.pojo.Bissinusshall hall = newDAO.findById(cn.infogiga.pojo.Bissinusshall.class, p1.getTeamId());
			if(hall != null){
				continue;
			}
			hall = new Bissinusshall();
			hall.setId(p1.getTeamId());
			
			City city = newDAO.findById(City.class, 1);
			hall.setCity(city);
			hall.setStatus(p1.getStatus());
			hall.setCode(p1.getTeamCode());
			hall.setDescription(p1.getDescription());
			hall.setHallName(p1.getTeamName());
			hall.setAddTime(p1.getAddTime());
			//syso
			newDAO.save(hall);
		}
	}

	private void copyPhonetype(){
		List<cn.infogiga.exp.pojo.Phonetype> list = oldDAO.findAll(cn.infogiga.exp.pojo.Phonetype.class);
		
		int size = list.size();
		cn.infogiga.exp.pojo.Phonetype pt = null;
		for(int i=0;i<size;i++){
			pt = list.get(i);
			Phonetype phonetype = 
				 newDAO.findById(Phonetype.class, pt.getPhonetypeId());
			if(phonetype != null){
				continue;
			}
			
			phonetype = new Phonetype();
			phonetype.setId(pt.getPhonetypeId());
			phonetype.setPhonetypeName(pt.getPhonetypeName());
			phonetype.setStatus(pt.getStatus());
			newDAO.save(phonetype);
		}
	}

	public void testPhoneTypeNewToOld(){

		List<cn.infogiga.pojo.Phonetype> newList = newDAO.findAll(cn.infogiga.pojo.Phonetype.class);
		cn.infogiga.pojo.Phonetype p1;
		for(int i=0;i<newList.size();i++){
			p1 = newList.get(i);
			if(p1.getPhonetypeName() == null ){
				continue;
			}
			int count = oldDAO.getCountByProperty(cn.infogiga.exp.pojo.Phonetype.class, "phonetypeName", p1.getPhonetypeName());
			if(count == 0){
				cn.infogiga.exp.pojo.Phonetype op = new cn.infogiga.exp.pojo.Phonetype();
				op.setPhonetypeName(p1.getPhonetypeName());
				Phonebrandcategory category = newDAO.findById(Phonebrandcategory.class, p1.getPhonebrandcategory().getId());
				cn.infogiga.exp.pojo.Phonebrand phonebrand = new cn.infogiga.exp.pojo.Phonebrand();
				phonebrand.setPhonebrandId(category.getPhonebrand().getId());
				op.setPhonebrand(phonebrand);
				op.setStatus(1);
				System.out.println(p1.getPhonetypeName()+"不存在");
				oldDAO.save(op);
			}
		}
		
		
	}
		
	private void copyDownloadstat(){
		List<Downloadstat> list = oldDAO.findAll(Downloadstat.class);
		int size = list.size();
		Downloadstat pt = null;
		for(int i=0;i<size;i++){
			pt = list.get(i);
			Softdownloadstat stat = newDAO.findById(Softdownloadstat.class, pt.getDownloadstatId());
			if(stat != null){
				System.out.println("已经添加，跳过...");
				continue;
			}
			//System.out.println("已经添加，跳过...");
			stat = new Softdownloadstat();
			stat.setAddTime(pt.getAddTime());
			cn.infogiga.pojo.Downloadtype downloadtype = new cn.infogiga.pojo.Downloadtype();
			downloadtype.setId(pt.getDownloadtype().getDownloadtypeId());
			stat.setDownloadtype(downloadtype);
			
			cn.infogiga.pojo.Equipment equipment = newDAO.findById(cn.infogiga.pojo.Equipment.class, pt.getEquipment().getEquipmentId());
			if(equipment == null){
				continue;
			}
			stat.setEquipment(equipment);
			stat.setId(pt.getDownloadstatId());
			stat.setPhoneNumber(pt.getPhoneNumber());
			cn.infogiga.pojo.Phonetype phonetype = new cn.infogiga.pojo.Phonetype();
			phonetype.setId(pt.getPhonetype().getPhonetypeId());
			stat.setPhonetype(phonetype);
			Soft soft = newDAO.findById(Soft.class, pt.getSoftinfo().getSoftId());
			if(soft == null){
				continue;
			}
			//soft.setId(pt.getSoftinfo().getSoftId());
			stat.setSoft(soft);
			Users users = newDAO.findById(Users.class, pt.getEmployee().getEmployeeId());
			if(users == null){
				continue;
			}
			
			//users.setId(pt.getEmployee().getEmployeeId());
			stat.setUsers(users);
			newDAO.save(stat);
		}
	}
	
	private void exportDownloadstat(){
		Date startDate = DateUtil.stringToDate("2010-07-05", DateUtil.NOW_DATE);
		Date endDate = DateUtil.stringToDate("2010-12-30", DateUtil.NOW_DATE);
		while(DateUtil.isBefore(startDate, endDate)){
			createExcel(startDate,newDAO);
			startDate = DateUtil.getNextDay(startDate);
		}
	}
	

	private void createExcel(Date date,MappingNewDAO newDAO){
		Date startTime = DateUtil.getStartOfDay(date);
		Date endTime = DateUtil.getEndOfDay(date);
		String fileName = DateUtil.getDateString(date, DateUtil.NOW_TIME3);
		File file = new File(ProperiesReader.getInstence("config.properties").getStringValue("soft.export.url")+fileName+".xls");
		if(file.exists()){
			try {
				file.delete();
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(file.getAbsolutePath());
		List<JsonSoftDownloadStat> list = newDAO.getJsonDownloadStatByDate(startTime, endTime);
		try {
			OutputStream os = new FileOutputStream(file);
			String[] title = {"序号","设备名称","营业厅","软件名称","手机型号","型号分类","手机厂商","员工姓名","员工账户","下载类型","手机号码","发生时间"};
			ExcelCreatorUtil.exportExcel(os, title, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	private void createRDExcel(Date date,MappingNewDAO newDAO){//生成第几天的excel
		Date startTime = DateUtil.getStartOfDay(date);
		Date endTime = DateUtil.getEndOfDay(date);
		List<Softdownloadstat> aList = newDAO.getDownloadStatByDate(startTime, endTime);
		List<RDdownloadExcel> list = ExcelUtil.getRDdownloadExcelList(aList);
		
		String fileName = DateUtil.getDateString(date, DateUtil.NOW_TIME3);
		File file = new File(ProperiesReader.getInstence("config.properties").getStringValue("soft.rdexport.url")+fileName+".xls");
		if(file.exists()){
			try {
				file.delete();
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println(file.getAbsolutePath());
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
	
	/**
	 * 把所有体验数据添加蓝牙地址
	 */
	protected void changePhoneNumber(){
		newDAO.updatePhonenumber();
		System.out.println("change phoneNumber success ...");
	}
}
