package cn.infogiga.sd.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cindy.page.beanutils.MyBeanUtils;
import cindy.util.DateUtil;
import cn.infogiga.pojo.Bissinusshall;
import cn.infogiga.pojo.Equipment;
import cn.infogiga.pojo.Logtype;
import cn.infogiga.pojo.Users;
import cn.infogiga.sd.dto.JsonEquipment;
import cn.infogiga.sd.dto.JsonListBean;
import cn.infogiga.sd.service.ManageService;

@Controller
public class EquipmentController {
	@Autowired
	ManageService manageService;
	
	@RequestMapping(value = "/equipment")
	public String equiJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start,@RequestParam("limit")Integer limit){
		List<JsonEquipment> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByPage(Equipment.class, start, limit), JsonEquipment.class);
		int totalCount = manageService.getManageDAO().getCount(Equipment.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,powerList,true,null);
		model.addAttribute("object", jsonListBean);
		return "list";

	}
	

	@RequestMapping(value = "/equipment",params="update")
	public String updateEquipment(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("equipmentId")Integer equipmentId,
			@RequestParam("equipmentCode")String equipmentCode,
			@RequestParam("equipmentName")String equipmentName,
			@RequestParam("addTime")String addTime,
			@RequestParam("mac")String mac,
			@RequestParam("status")Integer status,
			@RequestParam("hallId")Integer hallId){
		Equipment equipment = new Equipment();
		equipment.setId(equipmentId);
		Bissinusshall hall = new Bissinusshall();
		hall.setId(hallId);
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
			return "list";
		}
		manageService.log(Logtype.EQUIPMENT, ((Users)session.getAttribute("user")).getNickName(),"修改设备信息,设备名称为："+equipmentName);
		return "list";
	}
	
	@RequestMapping(value = "/equipment",params="delete")
	public String deleteEquipment(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("equipmentId")Integer equipmentId){
		Equipment equipment = manageService.getManageDAO().findById(Equipment.class, equipmentId);
		equipment.setId(equipmentId);
		try {
			manageService.getManageDAO().delete(equipment);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，删除失败~");
			return "list";
		}
		manageService.log(Logtype.EQUIPMENT, ((Users)session.getAttribute("user")).getNickName(),"修改设备信息,设备名称为："+equipment.getEquipmentName());
		return "list";
	}
	
	@RequestMapping(value = "/equipment",params="add")
	public String addEquipment(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("equipmentName")String equipmentName,
			@RequestParam("mac")String mac,
			@RequestParam("status")Integer status,
			@RequestParam("hallId")Integer hallId){
		Equipment equipment = new Equipment();
		equipment.setAddTime(new Date());
		Bissinusshall hall = new Bissinusshall();
		hall.setId(hallId);
		equipment.setBissinusshall(hall);
		equipment.setMac(mac);
		equipment.setEquipmentCode(cindy.util.Code.getCode(8));
		equipment.setStatus(status);
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
			return "list";
		}
		manageService.log(Logtype.EQUIPMENT, ((Users)session.getAttribute("user")).getNickName(),"添加设备信息,设备名称为："+equipment.getEquipmentName());
		return "list";
	}
}
