package cn.infogiga.sd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cindy.page.power.Node;
import cn.infogiga.sd.pojo.Admin;
import cn.infogiga.sd.pojo.Employee;
import cn.infogiga.sd.pojo.Power;
import cn.infogiga.sd.pojo.PowerUser;
import cn.infogiga.sd.service.ManageService;
import cn.infogiga.sd.service.PowerService;

public class MappingLoginController {

	ManageService manageService;
	
	PowerService powerService;

	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}

	public void setPowerService(PowerService powerService) {
		this.powerService = powerService;
	}
	
	@RequestMapping(value = "/login")
	public String checkLogin(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		
		if(userName == null || userName.isEmpty() || passWord == null || passWord.isEmpty()){
			model.put("msg", "用户名或密码不能为空！");
			model.put("success", false);
			return "list";
		}
		Admin admin = new Admin();
		admin.setUserName(userName);
		admin.setPassWord(passWord);
		PowerUser pu = manageService.checkLogin(admin);
		if(pu == null){
			model.put("msg", "用户名或密码不错误！");
			model.put("success", false);
			return "list";
		}
		session.setAttribute("user", pu);
		model.put("success", true);
		return "list";
	}

	@RequestMapping(value = "/empLogin")
	public String checkEmpLogin(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		
		if(userName == null || userName.isEmpty() || passWord == null || passWord.isEmpty()){
			model.put("msg", "用户名或密码不能为空！");
			model.put("success", false);
			return "list";
		}
		Employee employee = new Employee();
		employee.setUserName(userName);
		employee.setPassWord(passWord);
		PowerUser pu = manageService.checkLogin(employee);
		if(pu == null){
			model.put("msg", "用户名或密码不错误！");
			model.put("success", false);
			return "list";
		}
		session.setAttribute("user", pu);
		model.put("success", true);
		return "list";
	}
	@RequestMapping(value = "/menu")
	public String menu(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		Power power = ((PowerUser)session.getAttribute("user")).getPower();
		
		List<Node> nodeList = powerService.getPower(power, request.getRealPath("WEB-INF/power/power-config.xml"));
		model.addAttribute("array", nodeList);
		
		return "list";
	}
}
