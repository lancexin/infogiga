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
import cn.infogiga.pojo.Logtype;
import cn.infogiga.pojo.Power;
import cn.infogiga.pojo.Users;
import cn.infogiga.sd.dto.JsonListBean;
import cn.infogiga.sd.dto.JsonUser;
import cn.infogiga.sd.service.ManageService;

@Controller
public class UserController {
	
	@Autowired
	ManageService manageService;
	
	@RequestMapping(value = "/user")
	public String adminJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start,@RequestParam("limit")Integer limit,@RequestParam("status")Integer status){
		List<JsonUser> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByProperty(Users.class, "status",status, start, limit), JsonUser.class);
		int totalCount = manageService.getManageDAO().getCountByProperty(Users.class, "status", status);
		JsonListBean jsonListBean = new JsonListBean(totalCount,powerList,true,null);
		model.addAttribute("object", jsonListBean);
		return "list";

	}
	
	
	@RequestMapping(value = "/user",params="add")
	public String addAdmin(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("nickName")String nickName,
			@RequestParam("userName")String userName,
			@RequestParam("passWord")String passWord,
			@RequestParam("powerId")Integer powerId,
			@RequestParam("status")Integer status){
		Users user = new Users();
		user.setNickName(nickName);
		user.setUserName(userName);
		user.setPassWord(passWord);
		Power power = new Power();
		power.setId(powerId);
		user.setPower(power);
		user.setStatus(status);
		user.setAddTime(new Date());
		if(status == 1){
			Integer hallId = Integer.parseInt(request.getParameter("hallId"));
			Bissinusshall hall = new Bissinusshall();
			hall.setId(hallId);
			user.setBissinusshall(hall);
		}
		try {
			manageService.getManageDAO().save(user);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		manageService.log(Logtype.USER, ((Users)session.getAttribute("user")).getNickName(),"添加用户信息,名称为："+nickName);
		return "list";
	}
	
	@RequestMapping(value = "/user",params="delete")
	public String deleteAdmin(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("userId")Integer userId){
		Users user = manageService.getManageDAO().findById(Users.class, userId);
		try {
			manageService.getManageDAO().delete(user);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，删除失败~");
			return "list";
		}
		manageService.log(Logtype.USER, ((Users)session.getAttribute("user")).getNickName(),"添加用户信息,名称为："+user.getNickName());
		return "list";
	}
	

	@RequestMapping(value = "/user",params="update")
	public String updateAdmin(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("userId")Integer userId,
			@RequestParam("nickName")String nickName,
			@RequestParam("userName")String userName,
			@RequestParam("passWord")String passWord,
			@RequestParam("powerId")Integer powerId,
			@RequestParam("addTime")String addTime,
			@RequestParam("status")Integer status){
		Users user = new Users();
		user.setId(userId);
		user.setNickName(nickName);
		user.setUserName(userName);
		user.setPassWord(passWord);
		Power power = new Power();
		power.setId(powerId);
		user.setPower(power);
		user.setStatus(status);
		user.setAddTime(DateUtil.stringToDate(addTime, DateUtil.NOW_TIME));
		if(status == 1){
			Integer hallId = Integer.parseInt(request.getParameter("hallId"));
			Bissinusshall bissinusshall = new Bissinusshall();
			bissinusshall.setId(hallId);
			user.setBissinusshall(bissinusshall);
		}
		try {
			manageService.getManageDAO().update(user);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
			return "list";
		}
		manageService.log(Logtype.USER, ((Users)session.getAttribute("user")).getNickName(),"修改用户信息,名称为："+user.getNickName());
		return "list";
	}
}
