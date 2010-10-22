package cn.infogiga.exp.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cindy.util.DateUtil;
import cindy.web.pojo.POJO;
import cn.infogiga.exp.dao.ExperienceDAO;
import cn.infogiga.exp.pojo.Downloadstat;
import cn.infogiga.exp.pojo.Downloadtype;
import cn.infogiga.exp.pojo.Employee;
import cn.infogiga.exp.pojo.Equipment;
import cn.infogiga.exp.pojo.Menu;
import cn.infogiga.exp.pojo.Mms;
import cn.infogiga.exp.pojo.Phonetype;
import cn.infogiga.exp.pojo.Renewal;
import cn.infogiga.exp.pojo.Scene;
import cn.infogiga.exp.pojo.Sms;
import cn.infogiga.exp.pojo.Smsarray;
import cn.infogiga.exp.pojo.Softinfo;
import cn.infogiga.exp.pojo.Statistics;
import cn.infogiga.exp.pojo.Team;
import cn.infogiga.exp.server.xml.ReceiveBean;

public class ExperienceService {
	private static final Log log = LogFactory.getLog(ExperienceService.class);
	ExperienceDAO experienceDAO;
	
	public void setExperienceDAO(ExperienceDAO experienceDAO) {
		this.experienceDAO = experienceDAO;
	}

	/**
	 * 检查该设备是否可以登录
	 * @param tir
	 * @return
	 */
	public boolean checkLogin(ReceiveBean rb){
		Equipment equipment = new Equipment();
		equipment.setMac(rb.getMac().trim()+"");
		equipment.setStatus(1);
		int count = experienceDAO.getCountByExample(equipment);
		if(count == 0){
			return false;
		}
		return true;
	}
	
	/**
	 * 检查是否有该用户
	 * @param tir
	 * @return
	 */
	public boolean checkEmployee(ReceiveBean rb){
		Employee employee = new Employee();
		employee.setEmpNo(rb.getEmp_no().trim()+"");
		employee.setStatus(1);
		int count = experienceDAO.getCountByExample(employee);
		if(count == 0){
			return false;
		}
		return true;
	}
	
	/**
	 * 检查是否有该用户,并且验证是否密码正确
	 * @param tir
	 * @return
	 */
	public boolean checkEmployeeWithPassword(ReceiveBean rb){
		Employee employee = new Employee();
		employee.setEmpNo(rb.getEmp_no()+"");
		employee.setPassword(rb.getEmp_pwd()+"");
		employee.setStatus(1);
		int count = experienceDAO.getCountByExample(employee);
		if(count == 0){
			return false;
		}
		return true;
	}
	
	public Employee getSingleEmployee(ReceiveBean rb){
		Employee employee = new Employee();
		if(rb.getEmp_no() == null){
			return null;
		}
		employee.setEmpNo(rb.getEmp_no().trim()+"");
		employee.setPassword(rb.getEmp_pwd().trim()+"");
		employee.setStatus(1);
		
		return experienceDAO.findSingleByExample(employee);
	}
	
	public Equipment getSingleEquipment(ReceiveBean rb){
		Equipment equipment = new Equipment();
		Team team = new Team();
		team.setTeamId(Integer.parseInt(rb.getTeam_id().trim()));
		
		if(rb.getMac() == null){
			return null;
		}
		equipment.setMac(rb.getMac().trim()+"");
		return experienceDAO.findSingleByExample(equipment);
	}
	
	public Menu getSingleMenu(ReceiveBean rb){
		Menu menu = new Menu();
		if(rb.getMenu_id() == null){
			return null;
		}
		menu.setMenuId(rb.getMenu_id().trim()+"");
		menu.setPkg(rb.getPkg()+"");
		return experienceDAO.findSingleByExample(menu);
	}
	
	public boolean log(ReceiveBean rb){
		try {
			Statistics statistics = new  Statistics();
			
			Equipment equipment = getSingleEquipment(rb);
			
			Team team = new Team();
			team.setTeamId(Integer.parseInt(rb.getTeam_id().trim()));
			
			
			Menu menu = getSingleMenu(rb);
			
			Employee employee = getSingleEmployee(rb);
			
			Scene scene = new Scene();
			statistics.setHappenTime(new Date());
			statistics.setPhoneNumber(rb.getPhone_number().trim());
			scene.setSceneId(Integer.parseInt(rb.getScene().trim()));
			statistics.setEquipment(equipment);
			statistics.setMenu(menu);
			statistics.setScene(scene);
			statistics.setEmployee(employee);
			statistics.setStatus(1);
			statistics.setComboName(rb.getCombo_name().trim());
			experienceDAO.save(statistics);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean logSMS(ReceiveBean rb){
		try {
			Sms sms = new Sms();
			sms.setContext(rb.getSms_context()+rb.getSoft_download_url()==null?"":rb.getSoft_download_url());
			sms.setSendTime(new Date());
			sms.setStatus(1);
			sms.setPhoneNumber(rb.getPhone_number().trim());
			experienceDAO.save(sms);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public String checkUpdate(ReceiveBean rb){
		Renewal renewal = new Renewal();
		renewal.setVersion(rb.getVersion());
		renewal.setStatus(1);
		Equipment equipment = getSingleEquipment(rb);
		if(equipment != null){
			renewal.setSysinfo(equipment.getSysinfo());
			Renewal r = experienceDAO.findSingleByExample(renewal);
			if(r == null){
				return null;
			}else{
				return r.getUrl();
			}
		}else{
			return null;
		}
	}
	
	public List<Downloadstat> getStarSoft(ReceiveBean rb){
		Date startTime = DateUtil.getStartOfWeek(new Date());
		Date endTime = DateUtil.getEndOfWeek(new Date());
		
		return experienceDAO.getStarSoft(startTime, endTime);
	}
	
	public List<Downloadstat> getTopSoft(ReceiveBean rb){
		
		return experienceDAO.getTopSoft();
	}
	
	public boolean logDownloadSoft(ReceiveBean rb){
		boolean b = false;
		Downloadstat downloadstat = new Downloadstat();
		int soft_id = Integer.parseInt(rb.getSoft_id().trim());
		int phone_type_id = Integer.parseInt(rb.getPhone_type_id().trim());
		int phone_brand_id = Integer.parseInt(rb.getPhone_brand_id().trim());
		int download_type_id = Integer.parseInt(rb.getDownload_type().trim());
		Equipment equipment = getSingleEquipment(rb);
		Employee employee = getSingleEmployee(rb);
		if(equipment == null){
			return false;
		}
		
		if(employee == null){
			return false;
		}
		downloadstat.setAddTime(new Date());
		Downloadtype downloadtype =new  Downloadtype();
		downloadtype.setDownloadtypeId(download_type_id);
		downloadstat.setDownloadtype(downloadtype);
		downloadstat.setDownloadUrl(rb.getSoft_download_url());
		
		downloadstat.setEmployee(employee);
		downloadstat.setEquipment(equipment);
		downloadstat.setPhoneNumber(rb.getPhone_number());
		Phonetype phonetype = new Phonetype();
		phonetype.setPhonetypeId(phone_type_id);
		downloadstat.setPhonetype(phonetype);
		Softinfo softinfo = new Softinfo();
		softinfo.setSoftId(soft_id);
		downloadstat.setSoftinfo(softinfo);
		downloadstat.setStatus(1);
		try {
			experienceDAO.save(downloadstat);
			return true;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public <T extends POJO> List<T>  findAll(Class<T> clazz){
		return experienceDAO.findAll(clazz);
	}
	
	public Integer addSMSArraySend(String phoneNumbers,String context,Date sendTime,Integer status){
		Smsarray smsarray = new Smsarray(sendTime,status,phoneNumbers,context);
		experienceDAO.save(smsarray);
		return smsarray.getSmsId();
	}
	
	public void updateSMSArrayStatus(Integer smsArrayID,Integer status){
		Smsarray smsarray = experienceDAO.findById(Smsarray.class, smsArrayID);
		smsarray.setStatus(status);
		experienceDAO.update(smsarray);
	}
	
	public void addMMS(Date startTime,String phoneNumbers,String sequenceId){
		Mms mms = new Mms(phoneNumbers,startTime,null,0,sequenceId);
		experienceDAO.save(mms);
	}
	
	public void updateMMSStatusBySecquenceId(String sequenceId,int status){
		Mms mms = new Mms();
		mms.setSequenceId(sequenceId);
		mms = experienceDAO.findSingleByExample(mms);
		mms.setStatus(status);
		mms.setEndTime(new Date());
		experienceDAO.update(mms);
	}

	public ExperienceDAO getExperienceDAO() {
		return experienceDAO;
	}
	
	
}
