package cn.infogiga.controller;



import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.infogiga.bean.Application;
import cn.infogiga.bean.Comment;
import cn.infogiga.bean.Groups;
import cn.infogiga.bean.Invitation;
import cn.infogiga.bean.MailTemplate;
import cn.infogiga.bean.Manager;
import cn.infogiga.bean.Mmstemplate;
import cn.infogiga.bean.Role;
import cn.infogiga.bean.Setting;
import cn.infogiga.filther.LoginInfo;
import cn.infogiga.po.MailTemplatePO;
import cn.infogiga.po.MmsTemplatePO;
import cn.infogiga.service.AppointmentService;
import cn.infogiga.service.LoginService;
import cn.infogiga.service.SettingService;
import cn.infogiga.util.DateUtil;

@Controller
public class CommonController {
	
	private AppointmentService appointmentService;
	private SettingService settingService;
	
	public void setAppointmentService(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	public void setSettingService(SettingService settingService) {
		this.settingService = settingService;
	}
	
	/*********************************************************************/
	/****************                 首页信息              ***************/
	/*********************************************************************/
	
	/**
	 * 用户登录后加载首页信息
	 * @param request
	 * @param response
	 * @author cindy
	 */
	@RequestMapping("/index.do")
	public String index(HttpServletRequest request,
			HttpServletResponse response){

		
		return "index";
	}
	

	/*********************************************************************/
	/****************                 设置信息              ***************/
	/*********************************************************************/
	
	/**
	 * 初始化设置信息
	 * @param request
	 * @param response
	 * @author cindy
	 */
	@RequestMapping("/setup.do")
	public String setup(HttpServletRequest request,
			HttpServletResponse response){
		//获得用户组信息
		List<Groups> groupList = settingService.getGroups();
		request.setAttribute("groupList", groupList);
	/*	//获得邮箱模板信息
		List<MailTemplatePO> mailTempleteList = settingService.getMailTempleteList();
		request.setAttribute("mailTempleteList", mailTempleteList);
		//获得彩信模板信息
		List<MmsTemplatePO> mmsTempleteList = settingService.getMMSTempleteList();
		request.setAttribute("mmsTempleteList", mmsTempleteList);*/
		//获得用户信息
		Manager manager = (Manager)request.getSession().getAttribute("userInfo");
		request.setAttribute("userInfo", manager);
		
		return "setup";
	}
	
	/**
	 * 修改用户基本信息
	 * @param request
	 * @param response
	 * @author cindy
	 */
	@RequestMapping("/updateUserCommonInfo.do")
	public void updateUserCommonInfo(HttpServletRequest request,
			HttpServletResponse response){
		String name = request.getParameter("name");
		String phoneNumber = request.getParameter("phoneNumber");
		String mail = request.getParameter("mail");
		String out = null;
		
		LoginInfo info =(LoginInfo)request.getSession().getAttribute("userInfo");
		
		Manager manager = settingService.findManagerById(info.managerId);
		manager.setName(name);
		manager.setPhoneNumber(phoneNumber);
		manager.setMail(mail);
		//manager.setMail(info.mail);
		//manager.setManagerId(info.managerId);
		
		//manager.setGender(info.gender);
		
		/*Groups group = new Groups();
		group.setGroupId(info.groups.getGroupId());
		manager.setGroups(group);*/
		
		
		
		/*Manager up = new Manager();
		up.setManagerId(info.creatorId);
		manager.setManager(up);*/
		
		/*manager.setPassword(info.password);*/
		
		//manager.setUsername(info.username);
		
		/*Role role = new Role();
		role.setRoleId(info.roleId);
		manager.setRole(role);*/
		
		/*Setting setting = new Setting();
		setting.setSettingId(info.settingId);
		manager.setSetting(setting);*/
		 
		//boolean b = settingService..update(t, dao).updateManagerBaseInfo(manager,name,phoneNumber,mail);
		boolean b = settingService.updateManagerInfo(manager);
		if(b){
			info.name =  name;
			info.phoneNumber =  phoneNumber;
			info.mail =  mail;
			request.getSession().setAttribute("userInfo", info);
			
		}
		
		try {
			response.getWriter().print(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改用户密码
	 * @param request
	 * @param response
	 * @author cindy
	 */
	@RequestMapping("/updateUserPassword.do")
	public void updateUserPassword(HttpServletRequest request,
			HttpServletResponse response){
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		LoginInfo info = (LoginInfo)request.getSession().getAttribute("userInfo");
		if((info.password).equals(oldPassword)){

			Manager manager = settingService.findManagerById(info.managerId);
			
			manager.setPassword(newPassword);
			
			boolean b = settingService.updateManagerInfo(manager);
			if(b){
				info.password = newPassword;
				request.getSession().setAttribute("userInfo", info);
			}
			try {
				response.getWriter().print(b);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().print("the old password is wrong!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 删除一个用户信息
	 * @param request
	 * @param response
	 * @author cindy
	 */
	@RequestMapping("/deleteUser.do")
	public void deleteUser(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("managerId") Integer managerId){
		
		//int userId = Integer.parseInt(request.getParameter("userId"));
		String str = "";
		Manager manager = settingService.findManagerById(managerId);
		if(null == manager.getCreatorId()){
			str = "{success:false,error:'系统设置的人员无法删除！'}";
		}else{
			boolean b = settingService.deleteManager(manager);
			str = "{success:true,error:''}";
		}
		//manager.setManagerId(managerId);
		
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 修改一个用户信息
	 * @param request
	 * @param response
	 * @author cindy
	 */
	@RequestMapping("/updateUser.do")
	public void updateUser(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("name")String name,
			@RequestParam("username")String username,
			@RequestParam("password")String password,
			@RequestParam("phoneNumber")String phoneNumber,
			@RequestParam("mail")String mail,
			@RequestParam("type")Integer groupId,
			@RequestParam("managerId")Integer managerId
			){
		String callback = null;
		
		Integer c = settingService.getManagerCountByProperty("name", name);
		
		if(c == 0){
			Integer c2 = settingService.getManagerCountByProperty("username", username);
			if(c2 == 0){
				if(managerId == -1){//当 managerId == -1 则添加这个用户
					LoginInfo info = (LoginInfo)request.getSession().getAttribute("userInfo");
					Manager manager = new Manager();
					Groups groups = new Groups();
					groups.setGroupId(groupId);
					
					manager.setCreatorId(info.managerId);
					
					Setting setting = new Setting();
					setting.setDefaultLocation("政企");
					
					if(info.defaultGuiderId != -1){
						setting.setDefaultGuiderId(info.defaultGuiderId);
					}
					
					if(info.defaultMmstemplateId != -1){
						setting.setDefaultMmstemplateId(info.defaultMmstemplateId);
					}
					
					if(info.defaultTechnicianId != -1){
						setting.setDefaultTechnicianId(info.defaultTechnicianId);
					}
					
					if(info.defaultMailtemplateId != -1){
						setting.setDefaultMailtemplateId(info.defaultMailtemplateId);
					}
					setting.setDefaultLocation(info.defaultLocation);
					setting.setIsAttachment(info.isAttachment);
					setting.setAttachmentUri(info.attachmentURI);
					
					manager.setSetting(setting);
					
					manager.setUsername(username);
					manager.setPassword(password);
					manager.setName(name);
					manager.setMail(mail);
					manager.setGroups(groups);
					manager.setGender("m");
					
					manager.setPhoneNumber(phoneNumber);
					String s = settingService.addManager(manager);
					callback = "{type:\"insert\",success:true,data:"+s+"}";
				}else{
					Manager manager = settingService.findManagerById(managerId);
					manager.setManagerId(managerId);
					
					Groups groups = new Groups();
					groups.setGroupId(groupId);
					manager.setUsername(username);
					manager.setPassword(password);
					manager.setName(name);
					manager.setMail(mail);
					manager.setGroups(groups);
					manager.setPhoneNumber(phoneNumber);
					String s = settingService.updateManager(manager);
					callback = "{type:\"update\",success:true,data:"+s+"}";
				}
			}else{
				callback = "{type:\"\",error:\"用户名不能重复\"}";
			}
		}else{
			callback = "{type:\"\",error:\"姓名不能重复\"}";
		}
		

		
		
		System.out.println("callback:"+callback);
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(callback);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getSelectManagers.do")
	public void getSelectManagers(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("groupId")Integer groupId){
		String managerList = settingService.findManagerByGroupId(groupId);
		
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(managerList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getSelectMailTemplete.do")
	public void getSelectMailTemplete(HttpServletRequest request,
			HttpServletResponse response){
		String managerList = settingService.getMailTempleteList();
		
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(managerList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/updateSettingOption.do")
	public void updateSttingOption(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("defaultGuider")Integer defaultGuider,
			@RequestParam("defalutTechincian")Integer defalutTechincian,
			@RequestParam("defaultMailTemplete")Integer defaultMailTemplete,
			@RequestParam("defaultMmsTemplete")Integer defaultMmsTemplete,
			@RequestParam("sendMailAfterVisit")Integer sendMailAfterVisit){
		
		LoginInfo loginInfo = (LoginInfo)request.getSession().getAttribute("userInfo");
		int settingId = loginInfo.settingId;
		boolean b = settingService.updateSettingInfo(settingId,defaultGuider,defalutTechincian,defaultMailTemplete,defaultMmsTemplete,sendMailAfterVisit);
		
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getSelectMmsTemplete.do")
	public void getSelectMmsTemplete(HttpServletRequest request,
			HttpServletResponse response){
		String managerList = settingService.getMMSTempleteList();
		
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(managerList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 获得指定分组的用户信息
	 * @param request
	 * @param response
	 * @return
	 * @author cindy
	 */
	@RequestMapping("/getManagers.do")
	public void getManagers(HttpServletRequest request,
			HttpServletResponse response){
		String groupId = request.getParameter("managerType");

		Integer id = -1;
		
		if(groupId != null && !"".equals(groupId)){
			id = Integer.parseInt(groupId);
		}
		
		String managerList = settingService.findManagerByGroupId(id);
		
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(managerList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 获得指定分组的模板信息
	 * @param request
	 * @param response
	 * @return
	 * @author cindy
	 */
	@RequestMapping("/getTempletes.do")
	public void getTempletes(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("type")String templeteType
			){
		//String templeteType = request.getParameter("templeteType");
		String callback = null;
		if("mms".equals(templeteType)){
			//获得彩信模板信息
			String str = settingService.getMMSTempleteList();
			callback = "{type:\"mms\",data:"+str+"}";
			//request.setAttribute("templeteList", mmsTempleteList);
		}else if("mail".equals(templeteType)){
			//获得邮箱模板信息
			String str = settingService.getMailTempleteList();
			callback = "{type:\"mail\",data:"+str+"}";
			//request.setAttribute("templeteList", mailTempleteList);
		}else{
			callback = "false";
		}
		///request.setAttribute("templeteType", templeteType);
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(callback);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 修改模板信息
	 * @param request
	 * @param response
	 * @author cindy
	 */
	@RequestMapping("/updateTemplete.do")
	public void updateTemplete(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("type")String templeteType,
			@RequestParam("templeteId")Integer templeteId,
			@RequestParam("templeteName")String templeteName,
			@RequestParam("templeteContent")String templeteContent){
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String str = null;
		if(templeteId == -1){//当templeteId == -1是表示为添加templete
			if("mms".equals(templeteType)){
				Mmstemplate mmsTemplate = new Mmstemplate();
				mmsTemplate.setTemplateName(templeteName);
				mmsTemplate.setContent(templeteContent);
				String s = settingService.addMmsTemplate(mmsTemplate);
				str = "{type:\"insert\",data:"+s+"}";
			}else if("mail".equals(templeteType)){
				MailTemplate mailTemplate = new MailTemplate();
				mailTemplate.setTemplateName(templeteName);
				mailTemplate.setContent(templeteContent);
				String s = settingService.addMailTemplate(mailTemplate);
				str = "{type:\"insert\",data:"+s+"}";
			}
			
		}else{
			if("mms".equals(templeteType)){
				Mmstemplate mmstemplate = settingService.getMmstemplateById(templeteId);
				mmstemplate.setTemplateName(templeteName);
				mmstemplate.setContent(templeteContent);
				String b = settingService.updateMmsTemplete(mmstemplate);
				str = "{type:\"update\",data:"+b+"}";
				
			}else if("mail".equals(templeteType)){
				MailTemplate mailTemplate = settingService.getMailTemplateById(templeteId);
				mailTemplate.setTemplateName(templeteName);
				mailTemplate.setContent(templeteContent);
				String b = settingService.updateMailTemplete(mailTemplate);
				str = "{type:\"update\",data:"+b+"}";
			}
		}

		
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 删除模板信息
	 * @param request
	 * @param response
	 * @author cindy
	 */
	@RequestMapping("/deleteTemplete.do")
	public void deleteTemplete(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("type")String templeteType,
			@RequestParam("templeteId")Integer templeteId){

		boolean b = false;
		if("mms".equals(templeteType)){
			Mmstemplate mmstemplate = new Mmstemplate();
			mmstemplate.setMmstemplateId(templeteId);
			b = settingService.deleteMmsTemplate(mmstemplate);
			
		}else if("mail".equals(templeteType)){
			MailTemplate mailTemplate = new MailTemplate();
			mailTemplate.setMailTemplateId(templeteId);
			b = settingService.deleteMailTemplate(mailTemplate);
		}
		
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*********************************************************************/
	/****************                意见反馈               ***************/
	/*********************************************************************/
	
	/**
	 * 初始化并显示意见反馈信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @author cindy
	 */
	@RequestMapping("/feedback.do")
	public String feedback(HttpServletRequest request,
			HttpServletResponse response) {
		
		Integer commentCount = settingService.findCommentCount();
		List<Comment> commentList = settingService.findFirstCommentByPage();
		request.setAttribute("commentCount", commentCount);
		request.setAttribute("commentList", commentList);	
		return "feedback";
	}
	
	/**
	 * 意见反馈搜索以及翻页
	 * @param request
	 * @param response
	 * @return
	 * @author cindy
	 */
	@RequestMapping("/feedbackSelection.do")
	public String feedbackSelection(HttpServletRequest request,
			HttpServletResponse response){
		String fromTime = request.getParameter("fromTime");
		String toTime = request.getParameter("toTime");
		String phoneNumber = request.getParameter("phoneNumber");
		String company = request.getParameter("company");
		String name = request.getParameter("name");
		String pageCount = request.getParameter("pageCount");
		String page = request.getParameter("page");
		String method = request.getParameter("method");
		Date fromDate = null;
		Date toDate = null;
		
		if(fromTime != null && toTime !=null && !"".equals(fromTime) && !"".equals(toTime)){
			fromDate = DateUtil.stringToDate(fromTime, DateUtil.NOW_DATE);
			toDate = DateUtil.stringToDate(toTime, DateUtil.NOW_DATE);
		}
		
		Integer pCount = 5;
		
		if(pageCount != null && !"".equals(pageCount)){
			pCount = Integer.parseInt(pageCount);
		}
		if("1".equals(method)){
			int commentCount = settingService.findCommentCountPyPage(fromDate, toDate, phoneNumber, company, name);
			request.setAttribute("commentCount", commentCount+"");
		}
		
		Integer p = 0;
		
		if(page != null && !"".equals(page)){
			p = Integer.parseInt(page);
		}
		
		List<Comment> commentList = settingService.findCommentByPage(fromDate, toDate, phoneNumber, company, name, pCount, p);
		request.setAttribute("commentList", commentList);
		
		return "feedbackSelection";
	}
	
	/*********************************************************************/
	/****************                预约申请               ***************/
	/*********************************************************************/
	
	/**
	 * 初始化并显示预约申请信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @author cindy
	 */
	@RequestMapping("/request.do")
	public String request(HttpServletRequest request,
			HttpServletResponse response) {
		Integer applicationCount = settingService.findApplicationCount();
		List<Application> applicationList = settingService.findFirstApplicationByPage();
		request.setAttribute("applicationCount", applicationCount);
		request.setAttribute("applicationList", applicationList);	

		return "request";
	}
	
	/**
	 * 预约申请搜索以及翻页
	 * @param request
	 * @param response
	 * @return
	 * @author cindy
	 */
	@RequestMapping("/requestSelection.do")
	public String requestSelection(HttpServletRequest request,
			HttpServletResponse response){
		String name = request.getParameter("name");
		String phoneNumber = request.getParameter("phoneNumber");
		String mail = request.getParameter("mail");
		String company = request.getParameter("company");
		String mName = request.getParameter("mName");
		String fromTime = request.getParameter("fromTime");
		String toTime = request.getParameter("toTime");
		String pageCount = request.getParameter("pageCount");
		String page = request.getParameter("page");
		String method = request.getParameter("method");
		
		Date fromDate = null;
		Date toDate = null;
		
		if(fromTime != null && toTime !=null && !"".equals(fromTime) && !"".equals(toTime)){
			fromDate = DateUtil.stringToDate(fromTime, DateUtil.NOW_DATE);
			toDate = DateUtil.stringToDate(toTime, DateUtil.NOW_DATE);
		}
		
		Integer pCount = 5;
		
		if(pageCount != null && !"".equals(pageCount)){
			pCount = Integer.parseInt(pageCount);
		}
		
		if("1".equals(method)){
			int applicationCount = settingService.findApplicationCountByPage(name, phoneNumber, mail, company, mName, fromDate, toDate);
			request.setAttribute("applicationCount", applicationCount+"");
		}
		
		Integer p = 0;
		
		if(page != null && "".equals(page)){
			p = Integer.parseInt(page);
		}
		
		List<Application> applicationList = settingService.findApplicationByPage(name, phoneNumber, mail, company, mName, fromDate, toDate, pCount, p);
		request.setAttribute("applicationList", applicationList);
		
		return "requestSelection";
	}
	
	/*********************************************************************/
	/****************                预约日程               ***************/
	/*********************************************************************/
	
	/**
	 * 初始化并显示预约信息
	 * @param request
	 * @param response
	 * @return
	 * @author cindy
	 */
	@RequestMapping("/reservation.do")
	public String reservation(HttpServletRequest request,
			HttpServletResponse response) {
		Integer reservationCount = settingService.findInvitationCount();
		List<Invitation> reservationList = settingService.findFirstInvitationByPage();
		request.setAttribute("reservationCount", reservationCount);
		request.setAttribute("reservationList", reservationList);	
		
		return "reservation";
	}
	
	/**
	 * 预约日程搜索及翻页
	 * @param request
	 * @param response
	 * @return
	 * @author cindy
	 */
	@RequestMapping("/reservationSelection.do")
	public String reservationSelection(HttpServletRequest request,
			HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String manager = request.getParameter("manager");
		String guider = request.getParameter("guider");
		String technician = request.getParameter("technician");
		String location = request.getParameter("location");
		String states = request.getParameter("states");
		String fromCreateTime = request.getParameter("fromCreateTime");
		String toCreateTime = request.getParameter("toCreateTime");
		String fromTime = request.getParameter("fromTime");
		String toTime = request.getParameter("toTime");
		String pageCount = request.getParameter("pageCount");
		String page = request.getParameter("page");
		String title = request.getParameter("title");
		String method = request.getParameter("method");
		
		Date fromDate = null;
		Date toDate = null;
		Date fromCreateDate = null;
		Date toCreateDate = null;
		
		if(fromTime != null && toTime !=null && !"".equals(fromTime) && !"".equals(toTime)){
			fromDate = DateUtil.stringToDate(fromTime, DateUtil.NOW_DATE);
			toDate = DateUtil.stringToDate(toTime, DateUtil.NOW_DATE);
		}
		
		if(fromCreateTime != null && toCreateTime !=null && !"".equals(fromCreateTime) && !"".equals(toCreateTime)){
			fromCreateDate = DateUtil.stringToDate(fromCreateTime, DateUtil.NOW_DATE);
			toCreateDate = DateUtil.stringToDate(toCreateTime, DateUtil.NOW_DATE);
		}
		Integer cStates = -1;
		
		if(states != null && !"".equals(states)){
			cStates = Integer.parseInt(states);
		}
		
		Integer pCount = 5;
		
		if(pageCount != null && !"".equals(pageCount)){
			pCount = Integer.parseInt(pageCount);
		}
		
		if("1".equals(method)){
			int invitationCount =	settingService.findInvitationCountByPage(manager, guider, technician, location, cStates, title, fromCreateDate, toCreateDate, fromDate, toDate);
			request.setAttribute("reservationCount", invitationCount);
		}
		
		Integer p = 0;
		
		if(page != null && !"".equals(page)){
			p = Integer.parseInt(page);
		}
		
		List<Invitation> invitationList = settingService.findInvitationByPage(manager, guider, technician, location, cStates,title, fromCreateDate, toCreateDate, fromDate, toDate, pCount, p);
		request.setAttribute("reservationList", invitationList);
		
		return "reservationSelection";
	}
}
