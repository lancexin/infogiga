package cn.infogiga.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.infogiga.bean.Application;
import cn.infogiga.bean.Comment;
import cn.infogiga.bean.Invitation;
import cn.infogiga.config.PowerConfig;
import cn.infogiga.filther.LoginInfo;
import cn.infogiga.po.CustomerPO;
import cn.infogiga.po.InvitationPO;
import cn.infogiga.service.AppointmentService;
import cn.infogiga.service.CalendarService;
import cn.infogiga.util.DateUtil;
import cn.infogiga.util.StringToolkit;

@Controller
@RequestMapping("/items.htm")
public class CalendarController {
	private final static Log log = LogFactory.getLog(CalendarController.class);
	
	private CalendarService calendarService;
	
	private AppointmentService appointmentService;
	
	public void setCalendarService(CalendarService calendarService) {
		this.calendarService = calendarService;
	}
	
	public void setAppointmentService(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}	

	@RequestMapping("/calendar.htm")
	public String calendar(HttpServletRequest request,
			HttpServletResponse response){
	//	request.getSession().removeAttribute("userInfo");
		LoginInfo info = (LoginInfo)request.getSession().getAttribute("userInfo");
		if(info == null){
			try {
				response.sendRedirect("/signin.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		JSONArray array1 = appointmentService.getTechnicians();
		JSONArray array2 = appointmentService.getGuiders();
		JSONArray array3 = appointmentService.getMMSTemplates();
		String technicians = array1.toString();
		String guiders = array2.toString();
		String mmsTempletes = array3.toString();
		//System.out.println(mmsTempletes);
		//System.out.println(technicians);
		//System.out.println(guiders);
		request.setAttribute("technicians",technicians);
		request.setAttribute("guiders", guiders);
		request.setAttribute("mmsTempletes", mmsTempletes);
		
		String defaultTechincian = appointmentService.getDefaultManagerById(info.defaultTechnicianId);
		String defaultGuider = appointmentService.getDefaultManagerById(info.defaultGuiderId);
		String defaultMMSTemplate = appointmentService.getDefaultmsTempleteById(info.defaultMmstemplateId);
		
		
		List<String> power = PowerConfig.getPower(request.getRealPath(PowerConfig.PATH),info.power);
		
		request.setAttribute("defaultTechincian",defaultTechincian);
		request.setAttribute("defaultGuider",defaultGuider);
		request.setAttribute("defaultMMSTemplate",defaultMMSTemplate);
		request.setAttribute("power", power);
		//System.out.println(power.size());
		return "calendar";
	}

	@RequestMapping(params = "add",method = RequestMethod.POST)
	public void add(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("calendarTitle")String calendarTitle,
			@RequestParam("startTime")String startTime,
			@RequestParam("endTime")String endTime,
			@RequestParam(value="guider", required=false)Integer guiderId,
			@RequestParam(value="mmsContent", required=false)Integer mmsTempleteId,
			@RequestParam(value="technician", required=false)Integer techincianId){
		
		ArrayList<CustomerPO> customerList = new ArrayList<CustomerPO>();
		createCustomerList(request, customerList);
		LoginInfo manager = (LoginInfo)request.getSession().getAttribute("userInfo");
		String managerUserName = manager.username;
		Integer managerId = manager.managerId;
		
		InvitationPO invitation = new InvitationPO();
		if(guiderId == null || guiderId == -1){ //IE下可能为null
			Integer defaultGuiderId = manager.defaultGuiderId;
			invitation.setGuiderId(defaultGuiderId);
		}else{
			invitation.setGuiderId(guiderId);
		}
		
		if(techincianId == null || techincianId == -1){
			Integer deuaultTechincianId = manager.defaultTechnicianId;
			invitation.setTechnicanId(deuaultTechincianId);	
		}else{
		  invitation.setTechnicanId(techincianId);	
		}
		
		if(mmsTempleteId == null || mmsTempleteId == -1){
			Integer defalutMmsId = manager.defaultMmstemplateId;
			invitation.setMmsId(defalutMmsId);	
		}else{
		  invitation.setMmsId(mmsTempleteId);	
		}
		
		invitation.setManagerUserName(managerUserName);
		invitation.setManagerId(managerId);
		invitation.setCustomers(customerList);
		invitation.setInvitationTitle(calendarTitle);
		invitation.setLocation(manager.defaultLocation);
		invitation.setVisitTime(DateUtil.stringToDate(startTime,
				DateUtil.NOW_TIME2));//约定的参观时间
		invitation.setEndTime(DateUtil.stringToDate(endTime,
				DateUtil.NOW_TIME2));
		Invitation inv = appointmentService.sendInvitation(invitation);
		
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().print("{'IsSuccess':true,'Msg':'操作成功!','Data':"+inv.getInvitationId()+"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params = "show",method = RequestMethod.POST)
	public void show(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("showdate")String showdate,
			@RequestParam("viewtype")String viewtype,
			@RequestParam("timezone")String timezone){
	
		
		String out = "";
		if("day".equals(viewtype)){
			out = calendarService.getInvitationByDay(showdate);
		}else if("week".equals(viewtype)){
			out = calendarService.getInvitationByWeek(showdate);
		}else if("month".equals(viewtype)){
			out = calendarService.getInvitationByMonth(showdate);
		}else{
			out = calendarService.getInvitationByWeek(DateUtil.getDateString(DateUtil.NOW_DATE));
		}
		
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/update.do")
	public void update(HttpServletRequest request,
			HttpServletResponse response){
		
	}
	
	@RequestMapping("/quickUpdate.do")
	public void quickUpdate(HttpServletRequest request,
			HttpServletResponse response){
		
	}
	
	/**
	 * 请求信息
	 * @param key
	 * @param request
	 * @return
	 */
	@RequestMapping("/request.htm") 
	public String getApplicationInfo(@RequestParam("key")String key,
			HttpServletRequest request) {
		List<Application> list = null;
		if("this".equals(key)) {
			list = appointmentService.getApplicationThisWeek();
		} else if("last".equals(key)) {
			list = appointmentService.getApplicationLastWeek();
		} else if("all".equals(key)) {
			list = appointmentService.getApplications();
		} else {
			list = appointmentService.getApplications();
		}
		request.setAttribute("requests", list);
		return "sub-request";
	}
	
	/**
	 * 预约信息
	 * @param key
	 * @param request
	 * @return
	 */
	@RequestMapping("/reserve.htm") 
	public String getInvitationInfo(@RequestParam("key")String key,
			HttpServletRequest request) {
		List<Invitation> list = null;
		if("this".equals(key)) {
			list = appointmentService.getInvitationThisWeek();
		} else if("last".equals(key)) {
			list = appointmentService.getInvitationLastWeek();
		} else if("all".equals(key)) {
			list = appointmentService.getInvitations();
		} else {
			list = appointmentService.getInvitations();
		}
		request.setAttribute("invitations", list);
		return "sub-reserve";
	}
	
	/**
	 * 意见反馈
	 * @param key
	 * @param request
	 * @return
	 */
	@RequestMapping("/feedback.htm")
	public String getFeedbackInfo(@RequestParam("key")String key,
			HttpServletRequest request) {
		List<Comment> list = null;
		if("this".equals(key)) {
			list = appointmentService.getCommentThisWeek();
		} else if("last".equals(key)) {
			list = appointmentService.getCommentLastWeek();
		} else if("all".equals(key)) {
			list = appointmentService.getComments();
		} else {
			list = appointmentService.getComments();
		}
		request.setAttribute("feedbacks", list);
		return "sub-feedback";
	}
	
	private void createCustomerList(HttpServletRequest request, ArrayList<CustomerPO> customerList) {
		int count = request.getParameterValues("name").length; //客户的数目
		//System.out.println(count);
		for(int index=0; index<count; index++) {
			String name = request.getParameterValues("name")[index];
			String phoneNumber = request.getParameterValues("phoneNumber")[index];
			String company = request.getParameterValues("company")[index];
			
			if(!StringToolkit.isBlank(name)) {//不空则添加
				CustomerPO customer = new CustomerPO();
				customer.setName(name);
				customer.setPassword(StringToolkit.createPassword());
				customer.setPhoneNumber(phoneNumber);
				customer.setCompany(company);
				customer.setSendStatus(0);

				customerList.add(customer);
			}
			log.debug(name+","+phoneNumber+","+company);
		}
	}	
}
