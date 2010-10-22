package cn.infogiga.exp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cindy.util.DateUtil;
import cn.infogiga.exp.pojo.Area;
import cn.infogiga.exp.pojo.Emailservice;
import cn.infogiga.exp.pojo.Emailtemplete;
import cn.infogiga.exp.pojo.Employee;
import cn.infogiga.exp.pojo.Equipment;
import cn.infogiga.exp.pojo.Menu;
import cn.infogiga.exp.pojo.Phonebrand;
import cn.infogiga.exp.pojo.Phonetype;
import cn.infogiga.exp.pojo.Softinfo;
import cn.infogiga.exp.pojo.Sysinfo;
import cn.infogiga.exp.pojo.Team;
import cn.infogiga.exp.pojo.Userinfo;
import cn.infogiga.exp.power.Power;
import cn.infogiga.exp.power.PowerConfig;
import cn.infogiga.exp.service.ExpService;

public class UpdateController {
	ExpService expService;

	public void setExpService(ExpService expService) {
		this.expService = expService;
	}
	
	@RequestMapping(value = "/update",params="team")
	public String updateTeam(HttpServletRequest request,
			HttpServletResponse response){
		Integer teamId = Integer.parseInt(request.getParameter("teamId"));
		Team team = expService.getById(teamId,Team.class);
		request.setAttribute("team",team);
		List<Area> areaList =  expService.findAll(Area.class);
		request.setAttribute("areaList",areaList);
		return "team/team_update";
	}
	@RequestMapping(value = "/update",params="softinfo")
	public String updateSoftinfo(HttpServletRequest request,
			HttpServletResponse response){
		Integer id = Integer.parseInt(request.getParameter("id"));
		Softinfo softinfo = expService.getById(id,Softinfo.class);
		request.setAttribute("item",softinfo);
		return "softinfo/softinfo_update";
	}
	
	@RequestMapping(value = "/update",params="equipment")
	public String updateEquipment(HttpServletRequest request,
			HttpServletResponse response){
		Integer equipmentId = Integer.parseInt(request.getParameter("id"));
		Equipment e = expService.getById(equipmentId,Equipment.class);
		request.setAttribute("item",e);
		//List<Sysinfo> sysList = expService.findAll(Sysinfo.class);
		List<Sysinfo> sysList = expService.findByProperty(Sysinfo.class, "status", 1);
		request.setAttribute("sysList", sysList);
		//List<Team> teamList = expService.findAll(Team.class);
		List<Team> teamList = expService.findByProperty(Team.class,"status",1);
		request.setAttribute("teamList", teamList);
		return "equipment/equipment_update";
	}

	@RequestMapping(value = "/update",params="menu")
	public String updateMenu(HttpServletRequest request,
			HttpServletResponse response){
		Integer mId = Integer.parseInt(request.getParameter("id"));
		Menu m = expService.getById(mId,Menu.class);
		request.setAttribute("item",m);
		return "menu/menu_update";
	}
	
	@RequestMapping(value = "/update",params="emailTemplete")
	public String updateEmailTemplete(HttpServletRequest request,
			HttpServletResponse response){
		Integer mId = Integer.parseInt(request.getParameter("id"));
		Emailtemplete templete = expService.getById(mId, Emailtemplete.class);
		request.setAttribute("item",templete);
		return "email/emailTemplete_update";
	}
	
	@RequestMapping(value = "/update",params="phonebrand")
	public String updatePhonebrand(HttpServletRequest request,
			HttpServletResponse response){
		Integer mId = Integer.parseInt(request.getParameter("id"));
		Phonebrand m = expService.getById(mId,Phonebrand.class);
		request.setAttribute("item",m);
		return "phonebrand/phonebrand_update";
	}
	
	@RequestMapping(value = "/update",params="area")
	public String updateArea(HttpServletRequest request,
			HttpServletResponse response){
		Integer mId = Integer.parseInt(request.getParameter("id"));
		Area m = expService.getById(mId,Area.class);
		request.setAttribute("item",m);
		return "area/area_update";
	}
	
	@RequestMapping(value = "/update",params="phonetype")
	public String updatePhonetype(HttpServletRequest request,
			HttpServletResponse response){
		Integer mId = Integer.parseInt(request.getParameter("id"));
		Phonetype m = expService.getById(mId,Phonetype.class);
		request.setAttribute("item",m);
		List<Phonebrand> phonebrandList = expService.findAll(Phonebrand.class);
		request.setAttribute("phonebrandList", phonebrandList);
		return "phonetype/phonetype_update";
	}

	@RequestMapping(value = "/update",params="sysinfo")
	public String updateSysinfo(HttpServletRequest request,
			HttpServletResponse response){
		Integer systemId = Integer.parseInt(request.getParameter("id"));
		Sysinfo sysinfo = expService.getById(systemId,Sysinfo.class);
		request.setAttribute("item",sysinfo);
		return "sysinfo/sysinfo_update";
	}

	@RequestMapping(value = "/update",params="user")
	public String updateUser(HttpServletRequest request,
			HttpServletResponse response){
		Integer userId = Integer.parseInt(request.getParameter("id"));
		Userinfo user = expService.getById(userId,Userinfo.class);
		List<String> ownPower = new ArrayList<String>();
		String[] powers;
		if(user.getPower() != null){
			powers = user.getPower().split(",");
		}else{
			powers = new String[0];
		}
		
		for(int i=0;i<powers.length;i++){
			ownPower.add(powers[i]);
		}
		PowerConfig config = PowerConfig.getInstence();
		Power power = config.getBasePower(request.getRealPath(PowerConfig.POWER_CONFIG));
		request.setAttribute("power",power);
		request.setAttribute("item",user);
		request.setAttribute("ownPower",ownPower);
		List<Sysinfo> sysinfo = expService.findAll(Sysinfo.class);
		request.setAttribute("sysList", sysinfo);
		return "user/user_update";
	}

	@RequestMapping(value = "/update",params="employee")
	public String updateEmployee(HttpServletRequest request,
			HttpServletResponse response){
		Integer employeeId = Integer.parseInt(request.getParameter("id"));
		Employee employee = expService.getById(employeeId,Employee.class);
		
		List<String> ownPower = new ArrayList<String>();
		String[] powers;
		if(employee.getPower() != null){
			powers = employee.getPower().split(",");
		}else{
			powers = new String[0];
		}
		for(int i=0;i<powers.length;i++){
			ownPower.add(powers[i]);
		}
		PowerConfig config = PowerConfig.getInstence();
		Power power = config.getBasePower(request.getRealPath(PowerConfig.POWER_CONFIG));
		request.setAttribute("power",power);
		request.setAttribute("ownPower",ownPower);
		request.setAttribute("item",employee);
		List<Team> teamList = expService.findAll(Team.class);
		request.setAttribute("teamList", teamList);
		return "employee/employee_update";
	}
	
	@RequestMapping(value = "/update",params="emailService")
	public String updateEmailService(HttpServletRequest request,
			HttpServletResponse response){
		Integer id = Integer.parseInt(request.getParameter("id"));
		Emailservice service = expService.getById(id, Emailservice.class);
		
		List<Emailtemplete> templeteList = expService.findByProperty(Emailtemplete.class,"status", 1);
		request.setAttribute("item", service);
		request.setAttribute("templeteList", templeteList);
		return "email/emailService_update";
	}
	
	@RequestMapping(value = "/exeUpdate",params="equipment")
	public String exeUpdateEquipment(HttpServletRequest request,
			HttpServletResponse response){
		Integer equipmentId = Integer.parseInt(request.getParameter("equipmentId"));
		String equiName = request.getParameter("equiName");
		String mac = request.getParameter("mac");
		String ip = request.getParameter("ip");
		String harddisk = request.getParameter("harddisk");
		String code = request.getParameter("code");
		
		Date addTime = DateUtil.stringToDate(request.getParameter("addTime"), DateUtil.NOW_TIME);
		Integer teamId = Integer.parseInt(request.getParameter("teamId"));
		Integer systemId = Integer.parseInt(request.getParameter("systemId"));
		Integer status = Integer.parseInt(request.getParameter("status"));
		Team team = new Team();
		team.setTeamId(teamId);
		Sysinfo sysinfo = new Sysinfo();
		sysinfo.setSystemId(systemId);
		//Equipment equipment = new Equipment(team,mac,ip,addTime,status,code,equiName,harddisk,null,null,null,null);
		Equipment equipment = new Equipment();
		equipment.setMac(mac);
		equipment.setTeam(team);
		equipment.setIp(ip);
		equipment.setAddTime(addTime);
		equipment.setStatus(status);
		equipment.setCode(code);
		equipment.setEquiName(equiName);
		equipment.setHarddisk(harddisk);
		equipment.setSysinfo(sysinfo);
		equipment.setEquipmentId(equipmentId);
		boolean b = expService.update(equipment);
	
		if(b){
			request.setAttribute("value","修改成功");
		}else{
			request.setAttribute("value","修改失败");
		}
		return "success";
	}
	
	@RequestMapping(value = "/exeUpdate",params="softinfo")
	public String exeUpdateSoftinfo(HttpServletRequest request,
			HttpServletResponse response){
		Integer softId = Integer.parseInt(request.getParameter("softId"));
		String softName = request.getParameter("softName");
		String softCode = request.getParameter("softCode");
		
		Date addTime = DateUtil.stringToDate(request.getParameter("addTime"), DateUtil.NOW_TIME);
		Integer flag = Integer.parseInt(request.getParameter("flag"));
		Integer status = Integer.parseInt(request.getParameter("status"));
		
		Softinfo softinfo = new Softinfo();
		softinfo.setSoftName(softName);
		
		if(expService.alreadyHas(softinfo)){
			request.setAttribute("value","软件名重复");
		}else{
			softinfo.setSoftId(softId);
			softinfo.setAddTime(addTime);
			softinfo.setFlag(flag);
			softinfo.setSoftCode(softCode);
			softinfo.setStatus(status);
			
			boolean b = expService.update(softinfo);
			
			if(b){
				request.setAttribute("value","修改成功");
			}else{
				request.setAttribute("value","修改失败");
			}
		}
		return "success";
	}
	
	@RequestMapping(value = "/exeUpdate",params="menu")
	public String exeUpdateMenu(HttpServletRequest request,
			HttpServletResponse response){
		Integer mid = Integer.parseInt(request.getParameter("mId"));
		String menuId = request.getParameter("menuId");
		String funcId = request.getParameter("funcId");
		String parentMenuId = request.getParameter("parentMenuId");
		String menuDesc = request.getParameter("menuDesc");
		String domainId = request.getParameter("domainId");
		String menuUrl = request.getParameter("menuUrl");
		String menuPicUrl = request.getParameter("menuPicUrl");
		String menuIdx = request.getParameter("menuIdx");
		String needLogin = request.getParameter("needLogin");
		String needEcontrct = request.getParameter("needEcontrct");
		String needSecondPasswd = request.getParameter("needSecondPasswd");
		String stopShow = request.getParameter("stopShow");
		String notLoginShow = request.getParameter("notLoginShow");
		String busiKind = request.getParameter("busiKind");
		String isUsed = request.getParameter("isUsed");
		String validDate = request.getParameter("validDate");
		String expireDate = request.getParameter("expireDate");
		String lastDayCando = request.getParameter("lastDayCando");
		String helpUrl = request.getParameter("helpUrl");
		String channelLevel = request.getParameter("channelLevel");
		String menuName = request.getParameter("menuName");
		String pkg = request.getParameter("pkg");
		
		Menu e = new Menu(menuId,funcId,parentMenuId,menuDesc,domainId,menuUrl,menuPicUrl,menuIdx,needLogin,needEcontrct,needSecondPasswd,stopShow,notLoginShow,busiKind,isUsed,validDate,expireDate,lastDayCando,helpUrl,channelLevel,menuName,pkg,null,null);
		e.setMid(mid);
		
		boolean b = expService.update(e);
		if(b){
			request.setAttribute("value", "修改成功");
		}else{
			request.setAttribute("value", "修改失败");
		}
		
		return "success";
	}
	
	@RequestMapping(value = "/exeUpdate",params="sysinfo")
	public String exeUpdateSysinfo(HttpServletRequest request,
			HttpServletResponse response){
		Integer systemId = Integer.parseInt(request.getParameter("systemId"));
		String systemName = request.getParameter("systemName");
		Date addTime = DateUtil.stringToDate(request.getParameter("addTime"), DateUtil.NOW_TIME);
		Integer status = Integer.parseInt(request.getParameter("status"));
		Sysinfo sysinfo = new Sysinfo();
		sysinfo.setSystemId(systemId);
		sysinfo.setSystemName(systemName);
		sysinfo.setAddTime(addTime);
		sysinfo.setStatus(status);
		boolean b = expService.update(sysinfo);
	
		if(b){
			request.setAttribute("value","修改成功");
		}else{
			request.setAttribute("value","修改失败");
		}
		return "success";
	}
	
	@RequestMapping(value = "/exeUpdate",params="emailTemplete")
	public String exeUpdateEmailTemplete(HttpServletRequest request,
			HttpServletResponse response){
		String templeteId =request.getParameter("templeteId");
		String status =request.getParameter("status");
		String templeteName = request.getParameter("templeteName");
		if(templeteName == null){
			request.setAttribute("value", "模板名称不能为空");
			return "success";
		}
		String templeteView = request.getParameter("templeteView");
		System.out.println(templeteView);
		request.setAttribute("templeteId", templeteId);
		request.setAttribute("status", status);
		request.setAttribute("templeteName", templeteName);
		request.setAttribute("templeteView", templeteView);
		return "email/emailTemplete_add_review";
	}
	
	
	@RequestMapping(value = "/exeUpdate",params="phonetype")
	public String exeUpdatePhonetype(HttpServletRequest request,
			HttpServletResponse response){
		Integer phonetypeId = Integer.parseInt(request.getParameter("phonetypeId"));
		Integer phonebrandId = Integer.parseInt(request.getParameter("phonebrandId"));
		String phonetypeName = request.getParameter("phonetypeName");

		Integer status = Integer.parseInt(request.getParameter("status"));

		Phonetype phonetype = new Phonetype();
		phonetype.setPhonetypeId(phonetypeId);
		Phonebrand phonebrand = new Phonebrand();
		phonebrand.setPhonebrandId(phonebrandId);
		phonetype.setPhonebrand(phonebrand);
		phonetype.setPhonetypeName(phonetypeName);
		phonetype.setStatus(status);
		boolean b = expService.update(phonetype);
		
		if(b){
			request.setAttribute("value","修改成功");
		}else{
			request.setAttribute("value","修改失败");
		}
		return "success";
	}
	
	@RequestMapping(value = "/exeUpdate",params="phonebrand")
	public String exeUpdatePhonebrand(HttpServletRequest request,
			HttpServletResponse response){
		Integer phonebrandId = Integer.parseInt(request.getParameter("phonebrandId"));
		String phonebrandName = request.getParameter("phonebrandName");
		
		Phonebrand phonebrand = new Phonebrand();
		phonebrand.setPhonebrandName(phonebrandName);
		if(expService.alreadyHas(phonebrand)){
			request.setAttribute("value","厂商名字重复");
		}else{
			phonebrand.setPhonebrandId(phonebrandId);
			boolean b = expService.update(phonebrand);
			
			if(b){
				request.setAttribute("value","修改成功");
			}else{
				request.setAttribute("value","修改失败");
			}
		}
		return "success";
	}
	
	@RequestMapping(value = "/exeUpdate",params="user")
	public String exeUpdateUser(HttpServletRequest request,
			HttpServletResponse response){
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String nickName = request.getParameter("nickName");
		Date addTime = DateUtil.stringToDate(request.getParameter("addTime"), DateUtil.NOW_TIME);
		String[] powers = request.getParameterValues("power");
		StringBuffer power = new StringBuffer();
		for(int i=0;i<powers.length;i++){
			power.append(powers[i]+",");
		}
		Integer status = Integer.parseInt(request.getParameter("status"));
		Integer systemId = Integer.parseInt(request.getParameter("systemId"));
		Userinfo user = new Userinfo();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setNickName(nickName);
		user.setAddTime(addTime);
		user.setStatus(status);
		user.setPower(power.toString());
		Sysinfo sysinfo = new Sysinfo();
		sysinfo.setSystemId(systemId);
		user.setSysinfo(sysinfo);
		boolean b = expService.update(user);
	
		if(b){
			request.setAttribute("value","修改成功");
		}else{
			request.setAttribute("value","修改失败");
		}
		return "success";
	}
	
	@RequestMapping(value = "/exeUpdate",params="employee")
	public String exeUpdateEmployee(HttpServletRequest request,
			HttpServletResponse response){
		Integer employeeId = Integer.parseInt(request.getParameter("employeeId"));
		String empName = request.getParameter("empName");
		String empNo = request.getParameter("empNo");
		Integer teamId = Integer.parseInt(request.getParameter("teamId"));
		String password = request.getParameter("password");
		String phoneNumber = request.getParameter("phoneNumber");
		Integer status = Integer.parseInt(request.getParameter("status"));
		Date addTime = DateUtil.stringToDate(request.getParameter("addTime"), DateUtil.NOW_DATE);
		
		String[] powers = request.getParameterValues("power");
		StringBuffer power = new StringBuffer();
		for(int i=0;i<powers.length;i++){
			power.append(powers[i]+",");
		}
		
		
		Team team = new Team();
		team.setTeamId(teamId);
		Employee employee = new Employee();
		employee.setEmployeeId(employeeId);
		employee.setEmpName(empName);
		employee.setEmpNo(empNo);
		employee.setPassword(password);
		employee.setPhoneNumber(phoneNumber);
		employee.setStatus(status);
		employee.setEmployeeId(employeeId);
		employee.setTeam(team);
		employee.setAddTime(addTime);
		employee.setPower(power.toString());
		boolean b = expService.update(employee);
	
		if(b){
			request.setAttribute("value","修改成功");
		}else{
			request.setAttribute("value","修改失败");
		}
		return "success";
	}
	
	
	@RequestMapping(value = "/exeUpdate",params="team")
	public String exeUpdateTeam(HttpServletRequest request,
			HttpServletResponse response){
		Integer teamId = Integer.parseInt(request.getParameter("teamId"));
		Integer areaId = Integer.parseInt(request.getParameter("areaId"));
		Integer status = Integer.parseInt(request.getParameter("status"));
		Date addTime = DateUtil.stringToDate(request.getParameter("addTime"), DateUtil.NOW_TIME);
		String teamName = request.getParameter("teamName");
		String teamCode = request.getParameter("teamCode");
		String description = request.getParameter("description");
		Team t = new Team();
		t.setTeamId(teamId);
		t.setAddTime(addTime);
		t.setTeamName(teamName);
		t.setTeamCode(teamCode);
		Area area = new Area();
		area.setAreaId(areaId);
		t.setArea(area);
		t.setDescription(description);
		t.setStatus(status);

		boolean b = expService.update(t);
		if(b){
			request.setAttribute("value", "修改成功");
		}else{
			request.setAttribute("value", "修改失败");
		}
		return "success";
	}
	
	
	@RequestMapping(value = "/exeUpdate",params="area")
	public String exeUpdateArea(HttpServletRequest request,
			HttpServletResponse response){
		Integer areaId = Integer.parseInt(request.getParameter("id"));
		String areaName = request.getParameter("areaName");
		String areaCode = request.getParameter("areaCode");

		if(areaName == null || "".equals(areaName)){
			request.setAttribute("value", "区域名称不能为空");
		}else if(areaCode == null || "".equals(areaCode)){
			request.setAttribute("value", "区域编号不能为空");
		}else{
			Area area = new Area();
			area.setAreaId(areaId);
			area.setAreaName(areaName);
			area.setAreaCode(areaCode);
			
			boolean b = expService.update(area);
			if(b){
				request.setAttribute("value", "修改成功");
			}else{
				request.setAttribute("value", "修改失败");
			}
		}
		
		return "success";
	}
	
	@RequestMapping(value = "/exeUpdate",params="emailService")
	public String exeUpdateEmailService(HttpServletRequest request,
			HttpServletResponse response){
		Integer serviceId = Integer.parseInt(request.getParameter("serviceId"));
		Integer status = Integer.parseInt(request.getParameter("status"));
		String serviceName = request.getParameter("serviceName");
		if(serviceName == null || "".equals(serviceName)){
			request.setAttribute("value", "服务名称不能为空");
			return "success";
		}
		String serviceCode = request.getParameter("serviceCode");
		if(serviceCode == null || "".equals(serviceCode)){
			request.setAttribute("value", "服务码不能为空");
			return "success";
		}
		Integer templeteId = Integer.parseInt(request.getParameter("templeteId"));
		
		Emailservice service = new Emailservice();
		service.setServiceId(serviceId);
		service.setServiceCode(serviceCode);
		service.setServiceName(serviceName);
		service.setStatus(status);
		Emailtemplete templete = new Emailtemplete();
		templete.setTempleteId(templeteId);
		service.setEmailtemplete(templete);
		boolean b = expService.update(service);
		if(b){
			request.setAttribute("value", "修改成功");
		}else{
			request.setAttribute("value", "修改失败");
		}
		
		return "success";
	}
	
	@RequestMapping(value = "/update",params="password")
	public String updatePassword(HttpServletRequest request,
			HttpServletResponse response){
		return "user/user_update_password";
	}
	
	@RequestMapping(value = "/update",params="empwd")
	public String updateEmpPassword(HttpServletRequest request,
			HttpServletResponse response){
		return "employee/employee_update_pwd";
	}
	
	@RequestMapping(value = "/exeUpdate",params="pwd")
	public String exeUpdatePassword(HttpServletRequest request,
			HttpServletResponse response) {
		String userName = request.getParameter("username");
		String userPassword = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		String reNewPassword = request.getParameter("reNewPassword");
		System.out.println(userName);
		System.out.println(userPassword);
		System.out.println(newPassword);
		System.out.println(reNewPassword);
		if (userName == null || "".equals(userName)) {
			request.setAttribute("value", "用户名不能为空");
			return "success";
		}

		if (userPassword == null || "".equals(userPassword)) {
			request.setAttribute("value", "密码不能为空");
			return "success";
		}

		if (newPassword == null || "".equals(newPassword)) {
			request.setAttribute("value", "密码不能为空");
			return "success";
		}

		if (reNewPassword == null || "".equals(reNewPassword)) {
			request.setAttribute("value", "密码不能为空");
			return "success";
		}

		if (!newPassword.equals(reNewPassword)) {
			request.setAttribute("value", "两次密码输入不同");
			return "success";
		}
		Userinfo user = (Userinfo) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("value", "用户操作过期，请从新登录");
			return "success";
		}
		
		if(!(user.getUserName()).equals(userName)){
			request.setAttribute("value", "用户名错误");
			return "success";
		}
		if(!(user.getUserPassword()).equals(userPassword)){
			request.setAttribute("value", "密码不正确");
			return "success";
		}
		
		user.setUserPassword(newPassword);
		boolean b = expService.update(user);
		if (!b) {
			request.setAttribute("value", "修改失败");
			return "success";
		}
		request.getSession().setAttribute("user", user);
		request.setAttribute("value", "修改成功");
		return "success";
	}
	
	@RequestMapping(value = "/exeUpdate",params="empwd")
	public String exeUpdateEmpPassword(HttpServletRequest request,
			HttpServletResponse response) {
		String userName = request.getParameter("username");
		String userPassword = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		String reNewPassword = request.getParameter("reNewPassword");
		if (userName == null || "".equals(userName)) {
			request.setAttribute("value", "工号不能为空");
			return "success";
		}
		
		if (userPassword == null || "".equals(userPassword)) {
			request.setAttribute("value", "密码不能为空");
			return "success";
		}
		
		if (newPassword == null || "".equals(newPassword)) {
			request.setAttribute("value", "密码不能为空");
			return "success";
		}
		
		if (reNewPassword == null || "".equals(reNewPassword)) {
			request.setAttribute("value", "密码不能为空");
			return "success";
		}
		
		if (!newPassword.equals(reNewPassword)) {
			request.setAttribute("value", "两次密码输入不同");
			return "success";
		}
		Employee employee = (Employee) request.getSession().getAttribute("user");
		if (employee == null) {
			request.setAttribute("value", "用户操作过期，请从新登录");
			return "success";
		}
		
		if(!(employee.getEmpNo()).equals(userName)){
			request.setAttribute("value", "工号错误");
			return "success";
		}
		if(!(employee.getPassword()).equals(userPassword)){
			request.setAttribute("value", "密码不正确");
			return "success";
		}
		
		employee.setPassword(newPassword);
		boolean b = expService.update(employee);
		if (!b) {
			request.setAttribute("value", "修改失败");
			return "success";
		}
		request.getSession().setAttribute("user", employee);
		request.setAttribute("value", "修改成功");
		return "success";
	}
}
