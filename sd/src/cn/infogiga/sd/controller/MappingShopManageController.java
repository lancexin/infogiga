package cn.infogiga.sd.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cindy.page.beanutils.MyBeanUtils;
import cindy.util.DateUtil;
import cn.infogiga.sd.dto.JsonEmployee;
import cn.infogiga.sd.dto.JsonEquipment;
import cn.infogiga.sd.dto.JsonListBean;
import cn.infogiga.sd.dto.JsonPowerUser;
import cn.infogiga.sd.pojo.Bissinusshall;
import cn.infogiga.sd.pojo.Employee;
import cn.infogiga.sd.pojo.Equipment;
import cn.infogiga.sd.pojo.Power;
import cn.infogiga.sd.pojo.PowerUser;
import cn.infogiga.sd.service.ManageService;

public class MappingShopManageController {
	ManageService manageService;

	public ManageService getManageService() {
		return manageService;
	}

	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}
	
	@RequestMapping(value = "/manager")
	public String getSelfInfo(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		PowerUser employee = (PowerUser) session.getAttribute("user");
		if(employee == null){
			model.put("success", false);
			model.put("msg", "请重新登录！");
			return "list";
		}
		JsonPowerUser powerUser = MyBeanUtils.copyProperties(employee, JsonPowerUser.class);
		List<JsonPowerUser> list = new ArrayList<JsonPowerUser>();
		list.add(powerUser);
		JsonListBean jsonListBean = new JsonListBean(1,list,true,null);
		model.addAttribute("object", jsonListBean);
		return "list";
	}

	@RequestMapping(value = "/manager",params="update")
	public String updateSelfInfo(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("userId")Integer employeeId,
			@RequestParam("userName")String userName,
			@RequestParam("passWord")String passWord,
			@RequestParam("newPassWord")String newPassWord,
			@RequestParam("nickName")String nickName){
		
		try {
			Employee employee = manageService.getManageDAO().findById(Employee.class, employeeId);
			if(!passWord.equals(employee.getPassWord())){
				model.put("success", false);
				model.put("msg", "密码不正确！");
				return "list";
			}
			employee.setUserName(userName);
			employee.setNickName(nickName);
			employee.setPassWord(newPassWord);
			model.put("success", true);
			model.put("msg", "修改成功！");
			manageService.getManageDAO().save(employee);
		} catch (RuntimeException e) {
			model.put("success", false);
			model.put("msg", "未知错误！");
			e.printStackTrace();
		}
		return "list";
	}

	@RequestMapping(value = "/manager",params="equipmentlist")
	public String getEquipment(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start,@RequestParam("limit")Integer limit){
		Employee employee = (Employee) session.getAttribute("user");
		if(employee == null){
			JsonListBean jsonListBean = new JsonListBean(0,null,false,"请重新登录");
			model.addAttribute("object", jsonListBean);
			return "list";
		}
		Bissinusshall hall = employee.getBissinusshall();
		
		List<JsonEquipment> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByProperty(Equipment.class, "bissinusshall.hallId", hall.getHallId(), start, limit), JsonEquipment.class);
		int totalCount = manageService.getManageDAO().getCountByProperty(Equipment.class, "bissinusshall.hallId", hall.getHallId());
		JsonListBean jsonListBean = new JsonListBean(totalCount,powerList,true,null);
		model.addAttribute("object", jsonListBean);
		return "list";
	}

	@RequestMapping(value = "/manager",params="equipmentadd")
	public String addEquipment(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("equipmentName")String equipmentName,
			@RequestParam("mac")String mac){
		Employee employee = (Employee) session.getAttribute("user");
		if(employee == null){
			model.put("success", false);
			model.put("msg", "请重新以店员身份登录！");
			return "list";
		}
		Bissinusshall hall = employee.getBissinusshall();
		Equipment equipment = new Equipment();
		equipment.setAddTime(new Date());
		equipment.setBissinusshall(hall);
		equipment.setMac(mac);
		equipment.setEquipmentCode(cindy.util.Code.getCode(8));
		equipment.setStatus(1);
		equipment.setEquipmentName(equipmentName);
		try {
			manageService.getManageDAO().save(equipment);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/manager",params="equipmentupdate")
	public String updateEquipment(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("equipmentId")Integer equipmentId,
			@RequestParam("equipmentCode")String equipmentCode,
			@RequestParam("equipmentName")String equipmentName,
			@RequestParam("addTime")String addTime,
			@RequestParam("mac")String mac,
			@RequestParam("status")Integer status){
		Employee employee = (Employee) session.getAttribute("user");
		if(employee == null){
			model.put("success", false);
			model.put("msg", "请重新以店员身份登录！");
			return "list";
		}
		Bissinusshall hall = employee.getBissinusshall();
		Equipment equipment = new Equipment();
		equipment.setEquipmentId(equipmentId);
		equipment.setBissinusshall(hall);
		equipment.setMac(mac);
		equipment.setEquipmentCode(equipmentCode);
		equipment.setStatus(status);
		equipment.setEquipmentName(equipmentName);
		equipment.setAddTime(DateUtil.stringToDate(addTime, DateUtil.NOW_TIME));
		try {
			manageService.getManageDAO().update(equipment);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/manager",params="shopemployee")
	public String employeeJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start,@RequestParam("limit")Integer limit){
		Employee employee = (Employee) session.getAttribute("user");
		if(employee == null){
			model.put("success", false);
			model.put("msg", "请重新以店员身份登录！");
			return "list";
		}
		Bissinusshall hall = employee.getBissinusshall();
		
		
		List<JsonEmployee> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByProperty(Employee.class, "bissinusshall.hallId", hall.getHallId(), start, limit), JsonEmployee.class);
		int totalCount = manageService.getManageDAO().getCount(Employee.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list,true,null);
		model.addAttribute("object", jsonListBean);
		return "list";

	}
	
	@RequestMapping(value = "/manager",params="employeeadd")
	public String addEmployee(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("nickName")String nickName,
			@RequestParam("userName")String userName,
			@RequestParam("passWord")String passWord,
			@RequestParam("powerId")Integer powerId,
			@RequestParam("status")Integer status){
		Employee employee2 = (Employee) session.getAttribute("user");
		if(employee2 == null){
			model.put("success", false);
			model.put("msg", "请重新以店员身份登录！");
			return "list";
		}
		Bissinusshall hall = employee2.getBissinusshall();
		
		Employee employee = new Employee();
		employee.setNickName(nickName);
		employee.setUserName(userName);
		employee.setPassWord(passWord);
		employee.setBissinusshall(hall);
		employee.setPower(new Power(powerId));
		employee.setAddTime(new Date());
		employee.setStatus(status);
		try {
			manageService.getManageDAO().save(employee);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/manager",params="employeeupdate")
	public String updateEmployee(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("employeeId")Integer employeeId,
			@RequestParam("nickName")String nickName,
			@RequestParam("userName")String userName,
			@RequestParam("passWord")String passWord,
			@RequestParam("addTime")String addTime,
			@RequestParam("powerId")Integer powerId,
			@RequestParam("status")Integer status){
		Employee employee2 = (Employee) session.getAttribute("user");
		if(employee2 == null){
			model.put("success", false);
			model.put("msg", "请重新以店员身份登录！");
			return "list";
		}
		Bissinusshall hall = employee2.getBissinusshall();
		
		Employee employee = new Employee();
		employee.setUserId(employeeId);
		employee.setNickName(nickName);
		employee.setUserName(userName);
		employee.setPassWord(passWord);
		employee.setBissinusshall(hall);
		employee.setPower(new Power(powerId));
		employee.setStatus(status);
		employee.setAddTime(DateUtil.stringToDate(addTime, DateUtil.NOW_TIME));
		try {
			manageService.getManageDAO().update(employee);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
		}
		return "list";
	}
}
