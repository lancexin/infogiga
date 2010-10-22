package cn.infogiga.exp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cn.infogiga.exp.pojo.Employee;
import cn.infogiga.exp.pojo.Sysinfo;
import cn.infogiga.exp.pojo.Userinfo;
import cn.infogiga.exp.service.ExpService;

public class LoginController {
	ExpService expService;

	public void setExpService(ExpService expService) {
		this.expService = expService;
	}
	
	
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) {
		String userName = request.getParameter("username");
		String userPassword = request.getParameter("password");
		if (userName == null || "".equals(userName)) {
			request.setAttribute("value", "用户名不能为空");
			return new ModelAndView("login.jsp");
		}

		if (userPassword == null || "".equals(userPassword)) {
			request.setAttribute("value", "密码不能为空");
			return new ModelAndView("login.jsp");
		}
		Userinfo user = expService.checkLogin(userName, userPassword);
		
		System.out.println(userName);
		System.out.println(userPassword);
		if (user == null) {
			request.setAttribute("value", "用户名或密码错误");
			return new ModelAndView("login.jsp");
		}
		Sysinfo sysinfo = user.getSysinfo();
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("sysinfo", sysinfo);
		return new ModelAndView(new RedirectView("page/index.jsp"));
	}
	
	@RequestMapping(value = "/empLogin")
	public ModelAndView empLogin(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("username")
			String userName, @RequestParam("password")
			String userPassword) {
		if (userName == null || "".equals(userName)) {
			request.setAttribute("value", "工号不能为空");
			return new ModelAndView("empLogin.jsp");
		}
		
		if (userPassword == null || "".equals(userPassword)) {
			request.setAttribute("value", "密码不能为空");
			return new ModelAndView("empLogin.jsp");
		}
		Employee employee = expService.checkEmpLogin(userName, userPassword);
		
		System.out.println(userName);
		System.out.println(userPassword);
		if (employee == null) {
			request.setAttribute("value", "工号或密码错误");
			return new ModelAndView("empLogin.jsp");
		}
		request.getSession().setAttribute("user", employee);
		return new ModelAndView(new RedirectView("page/index.jsp"));
	}
}
