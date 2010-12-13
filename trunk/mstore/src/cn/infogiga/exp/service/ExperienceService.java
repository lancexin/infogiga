package cn.infogiga.exp.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.infogiga.exp.dao.ExperienceDAO;
import cn.infogiga.exp.webservice.bean.ReceiveBean;
import cn.infogiga.pojo.Downloadtype;
import cn.infogiga.pojo.Equipment;
import cn.infogiga.pojo.Menu;
import cn.infogiga.pojo.Ophonestat;
import cn.infogiga.pojo.Phonetype;
import cn.infogiga.pojo.Scene;
import cn.infogiga.pojo.Sms;
import cn.infogiga.pojo.Soft;
import cn.infogiga.pojo.Softdownloadstat;
import cn.infogiga.pojo.Users;
import cn.infogiga.util.DateUtil;


@Component("experienceService")
public class ExperienceService {
	private static final Log log = LogFactory.getLog(ExperienceService.class);
	
	@Autowired
	ExperienceDAO experienceDAO;
	

	/**
	 * 检查该设备是否可以登录
	 * @param tir
	 * @return
	 */
	public boolean checkLogin(ReceiveBean rb){
		Equipment equipment = new Equipment();
		equipment.setMac(rb.getMac());
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
		Users employee = new Users();
		employee.setUserName(rb.getEmp_no().trim());
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
		Users employee = new Users();
		employee.setUserName((rb.getEmp_no()+"").trim());
		employee.setPassWord((rb.getEmp_pwd()+"").trim());
		employee.setStatus(1);
		int count = experienceDAO.getCountByExample(employee);
		if(count == 0){
			return false;
		}
		return true;
	}
	
	public Users getSingleEmployee(ReceiveBean rb){
		Users employee = new Users();
		if(rb.getEmp_no() == null){
			return null;
		}
		employee.setUserName((rb.getEmp_no()+"").trim());
		employee.setPassWord((rb.getEmp_pwd()+"").trim());
		employee.setStatus(1);
		
		return experienceDAO.findSingleByExample(employee);
	}
	
	public Equipment getSingleEquipment(ReceiveBean rb){
		Equipment equipment = new Equipment();
		
		if(rb.getMac() == null){
			return null;
		}
		equipment.setMac(rb.getMac().trim());
		return experienceDAO.findSingleByExample(equipment);
	}
	
	public Menu getSingleMenu(ReceiveBean rb){
		Menu menu = new Menu();
		if(rb.getMenu_id() == null){
			return null;
		}
		menu.setMenuid(rb.getMenu_id().trim());
		menu.setPkg(rb.getPkg());
		return experienceDAO.findSingleByExample(menu);
	}
	
	public boolean log(ReceiveBean rb){
		try {
			Ophonestat statistics = new  Ophonestat();
			
			Equipment equipment = getSingleEquipment(rb);
			if(equipment == null){
				return false;
			}
			
			Menu menu = getSingleMenu(rb);
			
			Users employee = getSingleEmployee(rb);
			if(employee == null){
				return false;
			}
			
			Scene scene = new Scene();
			statistics.setHappenTime(new Date());
			statistics.setPhoneNumber(rb.getPhone_number().trim());
			scene.setId(Integer.parseInt(rb.getScene().trim()));
			statistics.setEquipment(equipment);
			statistics.setMenu(menu);
			statistics.setScene(scene);
			statistics.setUsers(employee);
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
			sms.setContext(rb.getSms_context());
			sms.setUrl(rb.getSoft_download_url());
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
	
/*	public String checkUpdate(ReceiveBean rb){
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
	}*/
	
	public Soft getStarSoft(ReceiveBean rb){
		Date startTime = DateUtil.getStartOfWeek(new Date());
		Date endTime = DateUtil.getEndOfWeek(new Date());
		
		return experienceDAO.getStarSoft(startTime, endTime);
	}
	
	public List<Soft> getTopSoft(ReceiveBean rb){
		
		return experienceDAO.getTopSoft();
	}
	
	public boolean logDownloadSoft(ReceiveBean rb){
		boolean b = false;
		Softdownloadstat downloadstat = new Softdownloadstat();
		int soft_id = Integer.parseInt(rb.getSoft_id().trim());
		int phone_type_id = Integer.parseInt(rb.getPhone_type_id().trim());
		int phone_brand_id = Integer.parseInt(rb.getPhone_brand_id().trim());
		int download_type_id = Integer.parseInt(rb.getDownload_type().trim());
		Equipment equipment = getSingleEquipment(rb);
		Users employee = getSingleEmployee(rb);
		if(equipment == null){
			return false;
		}
		
		if(employee == null){
			return false;
		}
		downloadstat.setAddTime(new Date());
		Downloadtype downloadtype =new  Downloadtype();
		downloadtype.setId(download_type_id);
		downloadstat.setDownloadtype(downloadtype);
		//downloadstat.set.setDownloadUrl(rb.getSoft_download_url());
		
		downloadstat.setUsers(employee);
		downloadstat.setEquipment(equipment);
		downloadstat.setPhoneNumber(rb.getPhone_number());
		Phonetype phonetype = new Phonetype();
		phonetype.setId(phone_type_id);
		downloadstat.setPhonetype(phonetype);
		Soft softinfo = new Soft();
		softinfo.setId(soft_id);
		downloadstat.setSoft(softinfo);
		try {
			experienceDAO.save(downloadstat);
			return true;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public <T extends Serializable> List<T>  findAll(Class<T> clazz){
		return experienceDAO.findAll(clazz);
	}
	
}
