package cn.infogiga.sd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cindy.page.beanutils.MyBeanUtils;
import cn.infogiga.sd.dto.JsonEquipment;
import cn.infogiga.sd.dto.JsonListBean;
import cn.infogiga.sd.pojo.Bissinusshall;
import cn.infogiga.sd.pojo.Employee;
import cn.infogiga.sd.pojo.Equipment;
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
		Employee employee = (Employee) session.getAttribute("user");
		model.addAttribute("object", employee);
		return "list";
	}

	@RequestMapping(value = "/manager/update")
	public String updateSelfInfo(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("employeeId")Integer employeeId,
			@RequestParam("userName")String userName,
			@RequestParam("passWord")String passWord,
			@RequestParam("nickName")String nickName){
		try {
			Employee employee = manageService.getManageDAO().findById(Employee.class, employeeId);
			employee.setUserName(userName);
			employee.setNickName(nickName);
			employee.setPassWord(passWord);
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

	@RequestMapping(value = "/manager/equipment/list")
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

	@RequestMapping(value = "/manager/equipment/add")
	public String addEquipment(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start,@RequestParam("limit")Integer limit){
		return "list";
	}

	@RequestMapping(value = "/manager/equipment/update")
	public String updateEquipment(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		return "list";
	}

	@RequestMapping(value = "/manager/equipment/delete")
	public String deleteEquipment(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start,@RequestParam("limit")Integer limit){
		return "list";
	}
	
}
