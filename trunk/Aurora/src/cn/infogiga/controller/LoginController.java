package cn.infogiga.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.infogiga.bean.Manager;
import cn.infogiga.filther.LoginInfo;
import cn.infogiga.service.LoginService;


@Controller
public class LoginController {
	
	private LoginService loginService;
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	/**
	 * 判断用户的用户名和密码
	 * @param request
	 * @param response
	 */
	@RequestMapping("/checkLogin.htm")
	public void checkLogin(HttpServletRequest request,
			HttpServletResponse response){

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String out = null;
		if(username == null || "".equals(username)){
			out = "noUsername";
		}else if(password == null || "".equals(password)){
			out = "noPassword";
		}else{
			Manager manager = loginService.checkLogin(username, password);
			if(manager == null){
				out = "no";
			}else{
				HttpSession session = request.getSession();
				LoginInfo info = new LoginInfo();
				info.gender = manager.getGender();
				info.groups = manager.getGroups();
				info.mail = manager.getMail();
				info.managerId = manager.getManagerId();
				info.name = manager.getName();
				info.password = manager.getPassword();
				info.phoneNumber = manager.getPhoneNumber();
				info.username = manager.getUsername();
				info.role = manager.getRole();
				if(null != info.role){
					info.roleId = manager.getRole().getRoleId();
				}
				info.setting = manager.getSetting();
				if(null != info.setting){
					info.settingId = manager.getSetting().getSettingId();
					info.defaultGuiderId = manager.getSetting().getDefaultGuiderId();
					info.defaultTechnicianId = manager.getSetting().getDefaultTechnicianId();
					info.defaultMmstemplateId = manager.getSetting().getDefaultMmstemplateId();
					info.defaultMailtemplateId = manager.getSetting().getDefaultMailtemplateId();
					info.isAttachment = manager.getSetting().getIsAttachment();
					info.attachmentURI = manager.getSetting().getAttachmentUri();
					info.defaultLocation = manager.getSetting().getDefaultLocation();
					
				}
				
				info.groups =  manager.getGroups();
				if(null != info.groups){
					info.groupId = manager.getGroups().getGroupId();
					info.power = manager.getGroups().getPower();
				}
				
				info.creatorId = manager.getCreatorId();
				System.out.println(info.creatorId);
				
				session.setAttribute("userInfo",info);
				out = "yes";
			}
		}
		
		try {
			response.getWriter().print(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 */
	@RequestMapping("/exit.htm")
	public void exit(HttpServletRequest request,
			HttpServletResponse response){
		System.out.println("用户注销");
		request.getSession().removeAttribute("userInfo");
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("退出成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
}
