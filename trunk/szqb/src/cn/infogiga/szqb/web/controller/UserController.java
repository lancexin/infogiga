package cn.infogiga.szqb.web.controller;

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

import cn.infogiga.szqb.pojo.Power;
import cn.infogiga.szqb.pojo.Users;
import cn.infogiga.szqb.vo.JsonListBean;
import cn.infogiga.szqb.vo.JsonUser;
import cn.infogiga.szqb.web.service.ManageService;

import cindy.page.beanutils.MyBeanUtils;
import cindy.page.hibernate.CirteriaBean;
import cindy.page.hibernate.CirteriaQuery;
import cindy.page.hibernate.PageBean;
import cindy.util.DateUtil;

@Controller
public class UserController {
	
	@Autowired
	ManageService manageService;
	
	@RequestMapping(value = "/user")
	public String adminJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start,@RequestParam("limit")Integer limit,@RequestParam("status")Integer status){
		
		List<JsonUser> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByProperty(Users.class, "status",status, start, limit,null), JsonUser.class);
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
		return "list";
	}
}
