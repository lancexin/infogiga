package cn.infogiga.exp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

import cindy.util.DateUtil;
import cindy.util.page.hibernate.CirteriaBean;
import cindy.util.page.hibernate.CirteriaQuery;
import cindy.util.page.hibernate.PageBean;
import cindy.web.pojo.POJO;
import cn.infogiga.exp.pojo.*;
import cn.infogiga.exp.power.PowerConfig;
import cn.infogiga.exp.service.ExpService;

public class ListController {
	
	ExpService expService;

	public void setExpService(ExpService expService) {
		this.expService = expService;
	}
	
	@RequestMapping(value = "/list",params="team")
	public String teamList(HttpServletRequest request,
			HttpServletResponse response){
		String flag = request.getParameter("flag");
		String prame = request.getParameter("prames");
		String totleSizeStr = request.getParameter("totleSize");
		String nowPageStr = request.getParameter("nowPage");
		int totleSize = (totleSizeStr == null || "".equals(totleSizeStr))?-1:Integer.parseInt(totleSizeStr);
		int nowPage = (nowPageStr == null || "".equals(nowPageStr))?0:Integer.parseInt(nowPageStr);
		PageBean pageBean = new PageBean(totleSize,nowPage);
		CirteriaBean cBean = new CirteriaBean("addTime");
		
		if(prame == null || "".equals(prame)){
			prame = "";
			totleSize = expService.getCount(Team.class);
			pageBean.setTotalSize(totleSize);
		}else{
			Sysinfo sysinfo = (Sysinfo) request.getSession().getAttribute("sysinfo");
			String[] params = prame.split(",");
			String teamName =  params[0].equals("-")?null:params[0];
			Date startTime = params[1].equals("-")?null:DateUtil.stringToDate(params[2], DateUtil.NOW_TIME);
			Date endTime = params[2].equals("-")?null:DateUtil.stringToDate(params[3], DateUtil.NOW_TIME);
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"teamName",teamName,null));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"s.systemId",sysinfo.getSystemId(),new String[]{"sysoinfo","s"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.BETWEED,CirteriaQuery.IS_OBJECT,"addTime",new Object[]{startTime,endTime},null));
		}
		
		if(totleSize == -1){
			totleSize = expService.getCountByPage(Team.class, cBean);
			pageBean.setTotalSize(totleSize);
		}
		
		pageBean.initize();
		cBean.setPageBean(pageBean);
		List<Team> list = expService.getListByPage(Team.class, cBean);
		
		pageBean.initize();
		request.setAttribute("list", list);
		request.setAttribute("page", pageBean);
		request.setAttribute("prames", prame);
		request.setAttribute("flag", flag);
		return "team/team_list";
	}
	
	@RequestMapping(value = "/list",params="downloadstat")
	public String downloadstatList(HttpServletRequest request,
			HttpServletResponse response){
		String flag = request.getParameter("flag");
		String prame = request.getParameter("prames");
		String totleSizeStr = request.getParameter("totleSize");
		String nowPageStr = request.getParameter("nowPage");
		int totleSize = (totleSizeStr == null || "".equals(totleSizeStr))?-1:Integer.parseInt(totleSizeStr);
		int nowPage = (nowPageStr == null || "".equals(nowPageStr))?0:Integer.parseInt(nowPageStr);
		PageBean pageBean = new PageBean(totleSize,nowPage);
		CirteriaBean cBean = new CirteriaBean("addTime");
		
		if(prame == null || "".equals(prame)){
			prame = "";
			totleSize = expService.getCount(Downloadstat.class);
			pageBean.setTotalSize(totleSize);
		}else{

			String[] params = prame.split(",");
			//var prames = teamId+","+equipmentId+","+employeeId+","+phoneNumber+","+phonetypeName+","+phonebrandId+","+softName+","+downloadtypeId+","+startTime+","+endTime;
			Integer teamId = params[0].equals("-")?-1:Integer.parseInt(params[0]);
			Integer equipmentId = params[1].equals("-")?-1:Integer.parseInt(params[1]);
			Integer employeeId = params[2].equals("-")?-1:Integer.parseInt(params[2]);
			String phoneNumber = params[3].equals("-")?null:params[3];
			String phonetypeName = params[4].equals("-")?null:params[4];
			Integer phonebrandId = params[5].equals("-")?-1:Integer.parseInt(params[5]);
			String softName = params[6].equals("-")?null:params[6];
			Integer downloadtypeId = params[7].equals("-")?-1:Integer.parseInt(params[7]);
			Date startTime = params[8].equals("-")?null:DateUtil.stringToDate(params[8], DateUtil.NOW_DATE);
			Date endTime = params[9].equals("-")?null:DateUtil.stringToDate(params[9], DateUtil.NOW_DATE);
			
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"t.teamId",teamId,new String[]{"employee","emp","emp.team","t"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"e.equipmentId",equipmentId,new String[]{"equipment","e"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"emp.employeeId",employeeId,new String[]{"employee","emp"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"phoneNumber",phoneNumber,null));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"pt.typeName",phonetypeName,new String[]{"phonetype","pt"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"pb.phonebrandId",phonebrandId,new String[]{"phonetype","pt","pt.phonebrand","pb"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"info.softName",softName,new String[]{"softinfo","info"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"dt.downloadtypeId",downloadtypeId,new String[]{"downloadtype","dt"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.BETWEED,CirteriaQuery.IS_OBJECT,"addTime",new Object[]{startTime,endTime},null));

		}
		
		if(totleSize == -1){
			totleSize = expService.getCountByPage(Downloadstat.class, cBean);
			pageBean.setTotalSize(totleSize);
		}
		
		pageBean.initize();
		cBean.setPageBean(pageBean);
		List<Downloadstat> list = expService.getListByPage(Downloadstat.class, cBean);
		
		pageBean.initize();
		request.setAttribute("list", list);
		request.setAttribute("page", pageBean);
		request.setAttribute("prames", prame);
		request.setAttribute("flag", flag);
		return "downloadstat/downloadstat_list";
	}
	
	@RequestMapping(value = "/list",params="statistics")
	public String statisticsList(HttpServletRequest request,
			HttpServletResponse response){
		String flag = request.getParameter("flag");
		String prame = request.getParameter("prames");
		String totleSizeStr = request.getParameter("totleSize");
		String nowPageStr = request.getParameter("nowPage");
		int totleSize = (totleSizeStr == null || "".equals(totleSizeStr))?-1:Integer.parseInt(totleSizeStr);
		int nowPage = (nowPageStr == null || "".equals(nowPageStr))?0:Integer.parseInt(nowPageStr);
		PageBean pageBean = new PageBean(totleSize,nowPage);
		CirteriaBean cBean = new CirteriaBean("happenTime");
		
		if(prame == null || "".equals(prame)){
			prame = "";
			totleSize = expService.getCount(Statistics.class);
			pageBean.setTotalSize(totleSize);
		}else{

			String[] params = prame.split(",");
			
			//var prames = teamId+","+equipmentId+","+employeeId+","+menuId+","+sceneId+","+phoneNumber+","+empPhone+","+startTime+","+endTime;
			
			Integer teamId = params[0].equals("-")?-1:Integer.parseInt(params[0]);
			Integer equipmentId = params[1].equals("-")?-1:Integer.parseInt(params[1]);
			Integer employeeId = params[2].equals("-")?-1:Integer.parseInt(params[2]);
			Integer menuId = params[3].equals("-")?-1:Integer.parseInt(params[3]);
			Integer sceneId = params[4].equals("-")?-1:Integer.parseInt(params[4]);
			String phoneNumber = params[5].equals("-")?null:params[5];
			String empPhone = params[6].equals("-")?null:params[6];
	
			Date startTime = params[7].equals("-")?null:DateUtil.stringToDate(params[7], DateUtil.NOW_DATE);
			Date endTime = params[8].equals("-")?null:DateUtil.stringToDate(params[8], DateUtil.NOW_DATE);
			
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"t.teamId",teamId,new String[]{"employee","emp","emp.team","t"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"e.equipmentId",equipmentId,new String[]{"equipment","e"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"emp.employeeId",employeeId,new String[]{"employee","emp"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"m.mid",menuId,new String[]{"menu","m"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"s.sceneId",sceneId,new String[]{"scene","s"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"phoneNumber",phoneNumber,null));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"emp.phoneNumber",empPhone,new String[]{"employee","emp"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.BETWEED,CirteriaQuery.IS_OBJECT,"happenTime",new Object[]{startTime,endTime},null));
		}
		
		if(totleSize == -1){
			totleSize = expService.getCountByPage(Statistics.class, cBean);
			pageBean.setTotalSize(totleSize);
		}
		
		pageBean.initize();
		cBean.setPageBean(pageBean);
		List<Statistics> list = expService.getListByPage(Statistics.class, cBean);
		
		pageBean.initize();
		request.setAttribute("list", list);
		request.setAttribute("page", pageBean);
		request.setAttribute("prames", prame);
		request.setAttribute("flag", flag);
		return "statistics/statistics_list";
	}
	
	@RequestMapping(value = "/list",params="equipment")
	public String equipmentList(HttpServletRequest request,
			HttpServletResponse response){
		String flag = request.getParameter("flag");
		String prame = request.getParameter("prames");
		String totleSizeStr = request.getParameter("totleSize");
		String nowPageStr = request.getParameter("nowPage");
		int totleSize = (totleSizeStr == null || "".equals(totleSizeStr))?-1:Integer.parseInt(totleSizeStr);
		int nowPage = (nowPageStr == null || "".equals(nowPageStr))?0:Integer.parseInt(nowPageStr);
		PageBean pageBean = new PageBean(totleSize,nowPage);
		CirteriaBean cBean = new CirteriaBean("addTime");
		
		if(prame == null || "".equals(prame)){
			prame = "";
			totleSize = expService.getCount(Equipment.class);
			pageBean.setTotalSize(totleSize);
		}else{
			//Sysinfo sysinfo = (Sysinfo) request.getSession().getAttribute("sysinfo");
			String[] params = prame.split(",");
			
			String equipmentName = params[0].equals("-")?null:params[0];
			String mac = params[1].equals("-")?null:params[1];
			Integer teamId = params[2].equals("-")?-1:Integer.parseInt(params[2]);
			Date startTime = params[3].equals("-")?null:DateUtil.stringToDate(params[3], DateUtil.NOW_DATE);
			Date endTime = params[4].equals("-")?null:DateUtil.stringToDate(params[4], DateUtil.NOW_DATE);
			
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"equiName",equipmentName,null));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"mac",mac,null));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"t.teamId",teamId,new String[]{"team","t"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.BETWEED,CirteriaQuery.IS_OBJECT,"addTime",new Object[]{startTime,endTime},null));
			
		}
		
		if(totleSize == -1){
			totleSize = expService.getCountByPage(Equipment.class, cBean);
			pageBean.setTotalSize(totleSize);
		}
		
		pageBean.initize();
		cBean.setPageBean(pageBean);
		List<Equipment> list = expService.getListByPage(Equipment.class, cBean);
		
		pageBean.initize();
		request.setAttribute("list", list);
		request.setAttribute("page", pageBean);
		request.setAttribute("prames", prame);
		request.setAttribute("flag", flag);
		
		return "equipment/equipment_list";
	}
	
	@RequestMapping(value = "/list",params="renewal")
	public String renewalList(HttpServletRequest request,
			HttpServletResponse response){
		queryList(Renewal.class, request,"uploadTime");
		return "renewal/renewal_list";
	}
	
	@RequestMapping(value = "/list",params="softinfo")
	public String softinfoList(HttpServletRequest request,
			HttpServletResponse response){
		queryList(Softinfo.class, request,null);
		return "softinfo/softinfo_list";
	}
	
	@RequestMapping(value = "/list",params="phonebrand")
	public String phonebrandList(HttpServletRequest request,
			HttpServletResponse response){
		queryList(Phonebrand.class, request,null);
		return "phonebrand/phonebrand_list";
	}
	
	@RequestMapping(value = "/list",params="emailTemplete")
	public String emailTempleteList(HttpServletRequest request,
			HttpServletResponse response){
		queryList(Emailtemplete.class, request,null);
		return "email/emailTemplete_list";
	}
	
	@RequestMapping(value = "/list",params="emailSendLog")
	public String emailSendLogList(HttpServletRequest request,
			HttpServletResponse response){
		queryList(Emailsendlog.class, request,null);
		return "email/emailSendLog_list";
	}
	
	@RequestMapping(value = "/list",params="emailService")
	public String emailServiceList(HttpServletRequest request,
			HttpServletResponse response){
		queryList(Emailservice.class, request,null);
		return "email/emailService_list";
	}
	
	@RequestMapping(value = "/list",params="phonetype")
	public String phonetypeList(HttpServletRequest request,
			HttpServletResponse response){
		queryList(Phonetype.class, request,"phonebrand.phonebrandId");
		return "phonetype/phonetype_list";
	}
	
	@RequestMapping(value = "/list",params="menu")
	public String menuList(HttpServletRequest request,
			HttpServletResponse response){
		queryList(Menu.class, request,null);
		return "menu/menu_list";
	}
	
	@RequestMapping(value = "/list",params="sysinfo")
	public String sysinfoList(HttpServletRequest request,
			HttpServletResponse response){
		queryList(Sysinfo.class, request,"addTime");
		return "sysinfo/sysinfo_list";
	}
	
	@RequestMapping(value = "/list",params="area")
	public String areaList(HttpServletRequest request,
			HttpServletResponse response){
		queryList(Area.class, request,null);
		return "area/area_list";
	}

	@RequestMapping(value = "/list",params="sms")
	public String smsList(HttpServletRequest request,
			HttpServletResponse response){
		queryList(Sms.class, request,"sendTime");
		return "sms/sms_list";
	}
	
	@RequestMapping(value = "/list",params="user")
	public String userList(HttpServletRequest request,
			HttpServletResponse response){
		queryList(Userinfo.class, request,"addTime");
		return "user/user_list";
	}
	

	@RequestMapping(value = "/list",params="employee")
	public String employeeList(HttpServletRequest request,
			HttpServletResponse response){
		String flag = request.getParameter("flag");
		String prame = request.getParameter("prames");
		String totleSizeStr = request.getParameter("totleSize");
		String nowPageStr = request.getParameter("nowPage");
		int totleSize = (totleSizeStr == null || "".equals(totleSizeStr))?-1:Integer.parseInt(totleSizeStr);
		int nowPage = (nowPageStr == null || "".equals(nowPageStr))?0:Integer.parseInt(nowPageStr);
		PageBean pageBean = new PageBean(totleSize,nowPage);
		CirteriaBean cBean = new CirteriaBean("addTime");
		
		if(prame == null || "".equals(prame)){
			prame = "";
			totleSize = expService.getCount(Statistics.class);
			pageBean.setTotalSize(totleSize);
		}else{
			String[] params = prame.split(",");
			
			String empName = params[0].equals("-")?null:params[0];
			String empNo = params[1].equals("-")?null:params[1];
			String phoneNumber = params[2].equals("-")?null:params[2];
		//	Integer teamId = params[3].equals("-")?-1:Integer.parseInt(params[3]);
			Date startTime = params[4].equals("-")?null:DateUtil.stringToDate(params[4], DateUtil.NOW_DATE);
			Date endTime = params[5].equals("-")?null:DateUtil.stringToDate(params[5], DateUtil.NOW_DATE);
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"empName",empName,null));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"empNo",empNo,null));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"phoneNumber",phoneNumber,null));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"phoneNumber",phoneNumber,null));
		//	cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"t.teamId",teamId,new String[]{"team","t"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.BETWEED,CirteriaQuery.IS_OBJECT,"addTime",new Object[]{startTime,endTime},null));
		}
		
		if(totleSize == -1){
			totleSize = expService.getCountByPage(Employee.class, cBean);
			pageBean.setTotalSize(totleSize);
		}
		
		pageBean.initize();
		cBean.setPageBean(pageBean);
		List<Employee> list = expService.getListByPage(Employee.class, cBean);
		
		pageBean.initize();
		request.setAttribute("list", list);
		request.setAttribute("page", pageBean);
		request.setAttribute("prames", prame);
		request.setAttribute("flag", flag);
		
		return "employee/employee_list";
	}
	
	private <T extends POJO> void queryList(Class<T> clazz,HttpServletRequest request,String order){
		String flag = request.getParameter("flag");
		String prame = request.getParameter("prames");
		String totleSizeStr = request.getParameter("totleSize");
		String nowPageStr = request.getParameter("nowPage");
		int totleSize = (totleSizeStr == null || "".equals(totleSizeStr))?-1:Integer.parseInt(totleSizeStr);
		int nowPage = (nowPageStr == null || "".equals(nowPageStr))?0:Integer.parseInt(nowPageStr);
		PageBean pageBean = new PageBean(totleSize,nowPage);
		CirteriaBean cBean = new CirteriaBean(order);
		
		if(totleSize == -1){
			totleSize = expService.getCountByPage(clazz, cBean);
			pageBean.setTotalSize(totleSize);
		}
		
		pageBean.initize();
		cBean.setPageBean(pageBean);
		List<T> list = expService.getListByPage(clazz, cBean);
		
		request.setAttribute("list", list);
		request.setAttribute("page", pageBean);
		request.setAttribute("prames", prame);
		request.setAttribute("flag", flag);
	}
	
	@RequestMapping(value = "/list",params="power")
	public String powerList(HttpServletRequest request,
			HttpServletResponse response){
		PowerUser info = (PowerUser) request.getSession().getAttribute("user");

		String powers = info.getPower();
		PowerConfig config = PowerConfig.getInstence();
		
		ArrayList<cn.infogiga.exp.power.Menu> list = config.getPower(request.getRealPath(PowerConfig.POWER_CONFIG), powers);
		
		request.setAttribute("list", list);
		
		return "menu";
	}
	
	@RequestMapping(value = "/list",params="mms")
	public String mmsList(HttpServletRequest request,
			HttpServletResponse response){
		queryList(Mms.class, request,"startTime");
		return "sms/mms_list";
	}
	
	@RequestMapping(value = "/list",params="smsarray")
	public String smsArrayList(HttpServletRequest request,
			HttpServletResponse response){
		queryList(Smsarray.class, request,"smsId");
		return "sms/smsarray_list";
	}
}
