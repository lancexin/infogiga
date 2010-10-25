package cn.infogiga.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.infogiga.bean.Application;
import cn.infogiga.bean.Invitation;
import cn.infogiga.bean.Manager;
import cn.infogiga.po.CustomerPO;
import cn.infogiga.po.InvitationPO;
import cn.infogiga.service.AppointmentService;
import cn.infogiga.util.DateUtil;
import cn.infogiga.util.StringToolkit;

@Controller
public class ScheduleController {

	private final static Log log = LogFactory.getLog(ScheduleController.class);
	private AppointmentService appointmentService;
	public void setAppointmentService(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	/**
	 * 显示以日为单位的请求信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/day.do")
	public String day(HttpServletRequest request) {
		String day = request.getParameter("date");
		if(day == null || "".equals(day)){
			day = DateUtil.getDateString(DateUtil.NOW_DATE);
		}
		List<Invitation> invitationList = appointmentService.getInvitationByDay(day);
		
		int size = invitationList.size();

		Invitation[] list = new Invitation[14];
		
		for(int i=0;i<size;i++){
			Invitation invitation = invitationList.get(i);
			int count = invitation.getVisitTime().getHours()-7;
			if(count >=0){
				list[count] = invitation;
			}
		}

		request.setAttribute("invitationList", list);
		request.setAttribute("date", day);
		return "day";
	}
	/**
	 * 显示以周为单位的请求信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/week.do")
	public String week(HttpServletRequest request,
			HttpServletResponse response) {
		String day = request.getParameter("date");		
		if(day == null || "".equals(day)){
			day = DateUtil.getDateString(DateUtil.NOW_DATE);
		}
		List<Invitation> invitationList = appointmentService.getInvitationByWeek(day);
		
		int size = invitationList.size();
		
		Invitation[][] list = new Invitation[14][7];
		
		for(int i=0;i<size;i++){
			Invitation invitation = invitationList.get(i);
			if(invitation != null){
				Date date = invitation.getVisitTime();
				
				if(date != null){
		//			System.out.println(date.toLocaleString());
					int week = date.getDay();
					int hour = date.getHours() - 7;
					
					if(week >= 0 && hour >= 0){
						list[hour][week] = invitation;
					}	
				}
			}
		}
		
		request.setAttribute("invitationList", list);
		request.setAttribute("date", day);
		
		return "week";
	}
	/**
	 * 显示以月为单位的请求信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/month.do")
	public String month(HttpServletRequest request,
			HttpServletResponse response) {
		String day = request.getParameter("date");
		if(day == null || "".equals(day)){
			day = DateUtil.getDateString(DateUtil.NOW_DATE);
		}
		List<Invitation> invitationList = appointmentService.getInvitationByMonth(day);
		int weekCount = DateUtil.getWeekCountOfMonth(day, DateUtil.NOW_DATE);
		List<Invitation>[][] list = new List[weekCount][7];
		int size = invitationList.size();
		
		for(int i = 0;i<size;i++){
			Invitation invitation = invitationList.get(i);
			if(invitation != null){
				Date visitTime = invitation.getVisitTime();
				if(visitTime != null){
					int weekNumber = DateUtil.getWeekNumberOfMonth(visitTime)-1;
					int dayNumber = DateUtil.getDayNumberOfWeek(visitTime)-1;
					if(list[weekNumber][dayNumber] == null){
						list[weekNumber][dayNumber] = new ArrayList<Invitation>();
					}
					list[weekNumber][dayNumber].add(invitation);
				}
			}
		}
		//System.out.println(list.length);
		request.setAttribute("invitationList", list);
		request.setAttribute("date", day);
		return "month";
	}
	
	/**
	 * 显示预约信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/reservation.do")
	public String reservation(HttpServletRequest request,
			HttpServletResponse response) {
		return "reservation";
	}
	
	//接待人员信息
	@RequestMapping(value="/info.do", params="guiders")
	public void getGuiders(HttpServletResponse response) throws IOException {
		JSONArray array = appointmentService.getGuiders();
		String guiders = array.toString();
		log.debug("接待人员："+ guiders);
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		response.getWriter().print(guiders);
	}
	
	//技术人员信息
	@RequestMapping(value="/info.do", params="technicians")
	public void getTechnicians(HttpServletResponse response) throws IOException {
		JSONArray array = appointmentService.getTechnicians();
		String technicians = array.toString();
		log.debug("技术人员："+ technicians);
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		response.getWriter().print(technicians);
	}
	
	//彩信模板信息
	@RequestMapping(value="/info.do", params="MMSTemplates")
	public void getMMSTemplates(HttpServletResponse response) throws IOException {
		JSONArray array = appointmentService.getMMSTemplates();
		String templates = array.toString();
		log.debug("彩信模板："+ templates);
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		response.getWriter().print(templates);
	}
	
	//日单位的预约信息
	@RequestMapping(value="/info.do", params="invitationDays")
	public void getInvitationDays(HttpServletResponse response, @RequestParam("date")String date) 
			throws IOException, JSONException {
		JSONArray array;
		if(StringToolkit.isBlank(date)) {
			array = appointmentService.getInvitationDays();
		} else {
			array = appointmentService.getInvitationDays(date);
		}
		String invitations = array.toString();
		log.debug("邀请信息day："+ invitations);
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		response.getWriter().print(invitations);
	}
	
	/**
	 * 获取单个的邀请信息
	 * @param request
	 * @param id
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping(value="/info.do", params="invitation")
	public void getInvitation(HttpServletRequest request, HttpServletResponse response, @RequestParam("invitationId")String id) 
			throws IOException, JSONException {
		int invitationId = Integer.parseInt(id);
		JSONArray array = appointmentService.getInvitation(invitationId);
		String invitations = array.toString();
		log.debug(invitationId+"的邀请信息："+ invitations);
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		response.getWriter().print(invitations);
	}
	
	//周单位的预约信息
	@RequestMapping(value="/info.do", params="invitationWeeks")
	public void getInvitationWeeks(HttpServletResponse response, @RequestParam("date")String date) 
			throws IOException, JSONException {
		JSONArray array;
		if(StringToolkit.isBlank(date)) {
			array = appointmentService.getInvitationDays();
		} else {
			array = appointmentService.getInvitationDays(date);
		}
		String invitations = array.toString();
		log.debug("邀请信息week："+ invitations);
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		response.getWriter().print(invitations);
	}
	
	//月单位的预约信息
	@RequestMapping(value="/info.do", params="invitationMonths")
	public void getInvitationMonths(HttpServletResponse response, @RequestParam("date")String date) 
			throws IOException, JSONException {
		JSONArray array;
		if(StringToolkit.isBlank(date)) {
			array = appointmentService.getInvitationDays();
		} else {
			array = appointmentService.getInvitationDays(date);
		}
		String invitations = array.toString();
		log.debug("邀请信息month："+ invitations);
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		response.getWriter().print(invitations);
	}
	
	/**
	 * 快速预约
	 * @param request
	 * @param response
	 */
	@RequestMapping("/fastReservation.do")
	public void fastReservation(HttpServletRequest request,
			HttpServletResponse response){
		Manager current = (Manager) request.getSession().getAttribute("userInfo");
		ArrayList<CustomerPO> customerList = new ArrayList<CustomerPO>();
		createCustomerList(request, customerList);
		
		InvitationPO invitation = new InvitationPO();
		String visitTime = request.getParameter("visitTime");		
		invitation.setVisitTime(DateUtil.stringToDate(visitTime,
				new SimpleDateFormat("yyyy-MM-dd HH:mm")));
		appointmentService.fastInvitation(invitation, current, customerList);
	}

	/**
	 * 发送预约
	 * @param request
	 * @param response
	 */
	@RequestMapping("/addReservation.do")
	public void addReservation(HttpServletRequest request,
			HttpServletResponse response) {
		Manager current = (Manager) request.getSession().getAttribute("userInfo");
		
		ArrayList<CustomerPO> customerList = new ArrayList<CustomerPO>();
		createCustomerList(request, customerList);
		
		String guider = request.getParameter("guider");
		String technician = request.getParameter("technician");
		String template = request.getParameter("template");
		String mmsContent = request.getParameter("content");
		String location = request.getParameter("location");
		String title = request.getParameter("invTitle");
		String visitTime = request.getParameter("visitTime");
		Manager manager = current;
		String managerUserName = manager.getUsername();
		
		InvitationPO invitation = new InvitationPO();
		invitation.setGuiderName(guider);
		invitation.setTechnicanName(technician);
		invitation.setTemplateName(template);
		invitation.setMmsContent(mmsContent);
		invitation.setManagerUserName(managerUserName);
		invitation.setCustomers(customerList);
		invitation.setLocation(location);
		invitation.setInvitationTitle(
				StringToolkit.isBlank(title)?request.getParameter("name")+"等参观":title);
		invitation.setVisitTime(DateUtil.stringToDate(visitTime,
				new SimpleDateFormat("yyyy-MM-dd HH:mm")));//约定的参观时间
		
		appointmentService.sendInvitation(invitation);
	}
	
	/**
	 * 获得单个预约信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getSingleReservation.do")
	public void getSingleReservation(HttpServletRequest request,
			HttpServletResponse response){
		
	}
	
	@RequestMapping("/application.do")
	public void createApplication(@RequestParam("name")String name, 
			@RequestParam("phoneNumber")String phoneNumber,
			@RequestParam(value="company",required=false)String company,
			@RequestParam(value="reason", required=false)String reason) {
		
	}
	
	//生成客户列表
	private void createCustomerList(HttpServletRequest request, ArrayList<CustomerPO> customerList) {
		int count = request.getParameterValues("name").length; //客户的数目
		
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
