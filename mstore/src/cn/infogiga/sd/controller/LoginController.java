package cn.infogiga.sd.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cindy.page.power.Node;
import cn.infogiga.pojo.Power;
import cn.infogiga.pojo.Users;
import cn.infogiga.sd.service.ManageService;
import cn.infogiga.sd.service.PowerService;

@Controller
public class LoginController {
	@Autowired
	ManageService manageService;
	@Autowired
	PowerService powerService;
	
	@RequestMapping(value = "/login")
	public String checkLogin(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		
		if(userName == null || userName.isEmpty() || passWord == null || passWord.isEmpty()){
			model.put("msg", "用户名或密码不能为空！");
			model.put("success", false);
			return "list";
		}
		Users admin = new Users();
		admin.setUserName(userName);
		admin.setPassWord(passWord);
		Users pu = manageService.checkLogin(admin);
		if(pu == null){
			model.put("msg", "用户名或密码不错误！");
			model.put("success", false);
			return "list";
		}
		session.setAttribute("user", pu);
		model.put("success", true);
		
		Integer eId = (request.getParameter("eId")==null || request.getParameter("eId").length()==0)?-1:Integer.parseInt(request.getParameter("eId"));	
		session.setAttribute("eId", eId);
		return "list";
	}
	
	@RequestMapping(value = "/menu")
	public String menu(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		Power power = ((Users)session.getAttribute("user")).getPower();
		
		List<Node> nodeList = powerService.getPower(power, request.getRealPath("/WEB-INF/power/power-config.xml"));
		model.addAttribute("array", nodeList);
		
		return "list";
	}
}
