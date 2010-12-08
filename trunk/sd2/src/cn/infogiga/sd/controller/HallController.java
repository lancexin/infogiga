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
import cn.infogiga.sd.dto.JsonBissinussHall;
import cn.infogiga.sd.dto.JsonListBean;
import cn.infogiga.sd.pojo.Bissinusshall;
import cn.infogiga.sd.pojo.City;
import cn.infogiga.sd.pojo.Logtype;
import cn.infogiga.sd.pojo.Users;
import cn.infogiga.sd.service.ManageService;

@Controller
public class HallController {
	@Autowired
	ManageService manageService;
	
	@RequestMapping(value = "/hall",params="comboHall")
	public String comboHallJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonBissinussHall> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Bissinusshall.class), JsonBissinussHall.class);
		model.addAttribute("array", powerList);
		return "list";
	}
	
	@RequestMapping(value = "/hall")
	public String hallJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start,@RequestParam("limit")Integer limit){
		List<JsonBissinussHall> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByPage(Bissinusshall.class, start, limit), JsonBissinussHall.class);
		int totalCount = manageService.getManageDAO().getCount(Bissinusshall.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,powerList,true,null);
		model.addAttribute("object", jsonListBean);
		return "list";
	}
	
	
	@RequestMapping(value = "/hall",params="add")
	public String addHall(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("hallName")String hallName,
			@RequestParam("description")String description,
			@RequestParam("code")String code,
			@RequestParam("status")Integer status,
			@RequestParam("cityId")Integer cityId){
		Bissinusshall hall = new Bissinusshall();
		hall.setHallName(hallName);
		hall.setDescription(description);
		hall.setCode(code);
		hall.setStatus(status);
		City city = new City();
		city.setId(cityId);
		hall.setCity(city);
		hall.setAddTime(new Date());
		try {
			manageService.getManageDAO().save(hall);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
			return "list";
		}
		manageService.log(Logtype.HALL, ((Users)session.getAttribute("user")).getNickName(),"添加营业厅信息,营业厅名称为："+hallName);
		return "list";
	}
	
	@RequestMapping(value = "/hall",params="delete")
	public String deleteHall(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("hallId")Integer hallId){
		Bissinusshall hall = manageService.getManageDAO().findById(Bissinusshall.class, hallId);
		hall.setId(hallId);
		try {
			manageService.getManageDAO().delete(hall);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，删除失败~");
			return "list";
		}
		manageService.log(Logtype.HALL, ((Users)session.getAttribute("user")).getNickName(),"删除营业厅信息,营业厅名称为："+hall.getHallName());
		return "list";
	}
	
	@RequestMapping(value = "/hall",params="update")
	public String updateHall(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("hallId")Integer hallId,
			@RequestParam("hallName")String hallName,
			@RequestParam("description")String description,
			@RequestParam("code")String code,
			
			@RequestParam("status")Integer status,
			@RequestParam("addTime")String addTime,
			@RequestParam("cityId")Integer cityId){
		Bissinusshall hall = new Bissinusshall();
		hall.setId(hallId);
		hall.setHallName(hallName);
		hall.setCode(code);
		hall.setDescription(description);
		hall.setStatus(status);
		City city = new City();
		city.setId(cityId);
		hall.setCity(city);
		hall.setAddTime(DateUtil.stringToDate(addTime, DateUtil.NOW_TIME));
		try {
			manageService.getManageDAO().update(hall);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
			return "list";
		}
		manageService.log(Logtype.HALL, ((Users)session.getAttribute("user")).getNickName(),"修改营业厅信息,营业厅名称为："+hallName);
		return "list";
	}
}
