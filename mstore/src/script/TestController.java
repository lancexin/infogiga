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
import cn.infogiga.exp.quartz.ExcelCreatorUtil;
import cn.infogiga.exp.quartz.ExcelUtil;
import cn.infogiga.exp.quartz.RDdownloadExcel;
import cn.infogiga.pojo.Phonetype;
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
	
	@RequestMapping(value = "/mock")
	public void startMock(HttpServletRequest request,HttpServletResponse response){
		System.out.println("mock start ...");
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
		
	private void copyDownloadstat(){
		List<Downloadstat> list = oldDAO.findAll(Downloadstat.class);
		int size = list.size();
		Downloadstat pt = null;
		for(int i=0;i<size;i++){
			pt = list.get(i);
			Softdownloadstat stat = new Softdownloadstat();
			stat.setAddTime(pt.getAddTime());
			cn.infogiga.pojo.Downloadtype downloadtype = new cn.infogiga.pojo.Downloadtype();
			downloadtype.setId(pt.getDownloadtype().getDownloadtypeId());
			stat.setDownloadtype(downloadtype);
			
			cn.infogiga.pojo.Equipment equipment = new cn.infogiga.pojo.Equipment();
			equipment.setId(pt.getEquipment().getEquipmentId());
			stat.setEquipment(equipment);
			stat.setId(pt.getDownloadstatId());
			stat.setPhoneNumber(pt.getPhoneNumber());
			cn.infogiga.pojo.Phonetype phonetype = new cn.infogiga.pojo.Phonetype();
			phonetype.setId(pt.getPhonetype().getPhonetypeId());
			stat.setPhonetype(phonetype);
			Soft soft = new Soft();
			soft.setId(pt.getSoftinfo().getSoftId());
			stat.setSoft(soft);
			Users users = new Users();
			users.setId(pt.getEmployee().getEmployeeId());
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
