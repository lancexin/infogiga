package cn.infogiga.exp.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cindy.util.Code;
import cn.infogiga.exp.pojo.Area;
import cn.infogiga.exp.pojo.Emailservice;
import cn.infogiga.exp.pojo.Emailtemplete;
import cn.infogiga.exp.pojo.Employee;
import cn.infogiga.exp.pojo.Equipment;
import cn.infogiga.exp.pojo.Menu;
import cn.infogiga.exp.pojo.Phonebrand;
import cn.infogiga.exp.pojo.Phonetype;
import cn.infogiga.exp.pojo.Renewal;
import cn.infogiga.exp.pojo.Softinfo;
import cn.infogiga.exp.pojo.Sysinfo;
import cn.infogiga.exp.pojo.Team;
import cn.infogiga.exp.pojo.Userinfo;
import cn.infogiga.exp.power.Power;
import cn.infogiga.exp.power.PowerConfig;
import cn.infogiga.exp.service.ExpService;
import cn.infogiga.exp.service.SmsService;

public class AddController {
	ExpService expService;
	
	SmsService smsService;

	public void setSmsService(SmsService smsService) {
		this.smsService = smsService;
	}

	public void setExpService(ExpService expService) {
		this.expService = expService;
	}
	
	@RequestMapping(value = "/add",params="team")
	public String addTeam(HttpServletRequest request,
			HttpServletResponse response){
		
		List<Area> sysinfoList = expService.findAll(Area.class);
		request.setAttribute("arealist", sysinfoList);
		return "team/team_add";
	}
	
	@RequestMapping(value = "/add",params="renewal")
	public String addRenewal(HttpServletRequest request,
			HttpServletResponse response){
		
		//List<Sysinfo> sysinfoList = expService.findAll(Sysinfo.class);
		List<Sysinfo> sysinfoList = expService.findByProperty(Sysinfo.class,"status", 1);
		request.setAttribute("syslist", sysinfoList);
		return "renewal/renewal_add";
	}
	
	@RequestMapping(value = "/add",params="emailTemplete")
	public String addEmailTemplete(HttpServletRequest request,
			HttpServletResponse response){
		return "email/emailTemplete_add";
	}

	@RequestMapping(value = "/add",params="emailService")
	public String addEmailService(HttpServletRequest request,
			HttpServletResponse response){
		List<Emailtemplete> templeteList = expService.findByProperty(Emailtemplete.class,"status", 1);
		request.setAttribute("templeteList", templeteList);
		return "email/emailService_add";
	}
	
	@RequestMapping(value = "/add",params="equipment")
	public String addEquipment(HttpServletRequest request,
			HttpServletResponse response){
		//List<Team> teamList = expService.findAll(Team.class);
		List<Team> teamList = expService.findByProperty(Team.class,"status",1);
		request.setAttribute("teamList", teamList);
		//List<Sysinfo> sysList = expService.findAll(Sysinfo.class);
		List<Sysinfo> sysList = expService.findByProperty(Sysinfo.class,"status",1);
		request.setAttribute("sysList", sysList);
		return "equipment/equipment_add";
	}
	
	@RequestMapping(value = "/add",params="employee")
	public String addEmployee(HttpServletRequest request,
			HttpServletResponse response){
		//List<Team> teamList = expService.findAll(Team.class);
		List<Team> teamList = expService.findByProperty(Team.class,"status",1);
		request.setAttribute("teamList", teamList);
		
		PowerConfig config = PowerConfig.getInstence();
		Power power = config.getBasePower(request.getRealPath(PowerConfig.POWER_CONFIG));
		request.setAttribute("power", power);
		return "employee/employee_add";
	}
	
	@RequestMapping(value = "/add",params="sysinfo")
	public String addSystem(HttpServletRequest request,
			HttpServletResponse response){
		return "sysinfo/sysinfo_add";
	}
	
	@RequestMapping(value = "/add",params="menu")
	public String addMenu(HttpServletRequest request,
			HttpServletResponse response){
		return "menu/menu_add";
	}
	
	@RequestMapping(value = "/add",params="softinfo")
	public String addSoftinfo(HttpServletRequest request,
			HttpServletResponse response){
		return "softinfo/softinfo_add";
	}
	
	@RequestMapping(value = "/add",params="sendSMS")
	public String addsendSMS(HttpServletRequest request,
			HttpServletResponse response){
		return "customer/sendSMS";
	}
	
	@RequestMapping(value = "/add",params="phonetype")
	public String addPhonetype(HttpServletRequest request,
			HttpServletResponse response){
		List<Phonebrand> phonebrandList = expService.findAll(Phonebrand.class);
		request.setAttribute("phonebrandList", phonebrandList);
		return "phonetype/phonetype_add";
	}
	
	@RequestMapping(value = "/add",params="phonebrand")
	public String addPhonebrand(HttpServletRequest request,
			HttpServletResponse response){
		
		return "phonebrand/phonebrand_add";
	}
	
	@RequestMapping(value = "/add",params="area")
	public String addArea(HttpServletRequest request,
			HttpServletResponse response){
		
		return "area/area_add";
	}
	
	@RequestMapping(value = "/add",params="user")
	public String addUser(HttpServletRequest request,
			HttpServletResponse response){
		List<Sysinfo> sysinfo = expService.findAll(Sysinfo.class);
		request.setAttribute("sysList", sysinfo);
		PowerConfig config = PowerConfig.getInstence();
		Power power = config.getBasePower(request.getRealPath(PowerConfig.POWER_CONFIG));
		request.setAttribute("power", power);
		return "user/user_add";
	}
	
	@RequestMapping(value = "/exeAdd",params="team")
	public String exeAddTeam(HttpServletRequest request,
			HttpServletResponse response){
		
		String teamName = request.getParameter("teamName");
		String teamCode = request.getParameter("teamCode");
		Integer areaId = Integer.parseInt(request.getParameter("areaId"));
		String description = request.getParameter("desp");
		String value = null;
		if(teamName == null || "".equals(teamName)){
			value = "组名不能为空";
		}else if(description == null || "".equals(description)){
			value = "描述不能为空";
		}else if(teamCode == null || "".equals(teamCode)){
			value = "营业厅编号为空";
		}else{
			Team team = new Team();
			team.setAddTime(new Date());
			team.setDescription(description);
			team.setTeamName(teamName);
			team.setTeamCode(teamCode);
			Area area = new Area();
			area.setAreaId(areaId);
			team.setArea(area);
			team.setStatus(1);
			boolean b = expService.save(team);
			if(b){
				value="添加成功";
			}else{
				value="添加失败";
			}
		}
		request.setAttribute("value", value);
		return "success";
	}
	
	@RequestMapping(value = "/exeAdd",params="softinfo")
	public String exeAddSoftinfo(HttpServletRequest request,
			HttpServletResponse response){
		
		String softName = request.getParameter("softName");
		String softCode = request.getParameter("softCode");
		int flag = Integer.parseInt(request.getParameter("flag")); 
		String value = null;
		if(softName == null || "".equals(softName)){
			value = "软件不能为空";
		}else{
			Softinfo softinfo = new Softinfo();
			softinfo.setSoftName(softName);
			boolean bl = expService.alreadyHas(softinfo);
			if(bl){
				value="软件名称重复";
			}else{
				softinfo.setAddTime(new Date());
				softinfo.setFlag(flag);
				softinfo.setSoftCode(softCode);
				
				boolean b = expService.save(softinfo);
				if(b){
					value="添加成功";
				}else{
					value="添加失败";
				}
			}
		}
		request.setAttribute("value", value);
		return "success";
	}
	
	@RequestMapping(value = "/exeAdd",params="phonebrand")
	public String exeAddPhonebrand(HttpServletRequest request,
			HttpServletResponse response){
		//int id = Integer.parseInt(request.getParameter("phonebrandId"));
		String phonebrandName = request.getParameter("phonebrandName");

		String value = null;
		if(phonebrandName == null || "".equals(phonebrandName)){
			value = "厂商名字不能为空";
		}else{
			Phonebrand phonebrand = new Phonebrand();
			phonebrand.setPhonebrandName(phonebrandName);
			if(expService.alreadyHas(phonebrand)){
				value="厂商名称重复";
			}else{
				//phonebrand.setPhonebrandId(id);
				
				boolean b = expService.save(phonebrand);
				if(b){
					value="添加成功";
				}else{
					value="添加失败";
				}
			}
		}
		request.setAttribute("value", value);
		return "success";
	}
	
	@RequestMapping(value = "/exeAdd",params="area")
	public String exeAddArea(HttpServletRequest request,
			HttpServletResponse response){
		//int id = Integer.parseInt(request.getParameter("phonebrandId"));
		String areaName = request.getParameter("areaName");
		String areaCode = request.getParameter("areaCode");
		
		String value = null;
		if(areaName == null || "".equals(areaName)){
			value = "地区名称不能为空";
		}else{
			Area area = new Area();
			area.setAreaName(areaName);
			
			if(expService.alreadyHas(area)){
				value="地区名称重复";
			}else{
				//phonebrand.setPhonebrandId(id);
				area.setAreaCode(areaCode);
				boolean b = expService.save(area);
				if(b){
					value="添加成功";
				}else{
					value="添加失败";
				}
			}
		}
		request.setAttribute("value", value);
		return "success";
	}
	@RequestMapping(value = "/exeAdd",params="phonetype")
	public String exeAddPhonetype(HttpServletRequest request,
			HttpServletResponse response){
		//int id = Integer.parseInt(request.getParameter("phonetypeId"));
		String phonetypeName = request.getParameter("phonetypeName");
		int phonebrandId = Integer.parseInt(request.getParameter("phonebrandId"));
		
		String value = null;
		if(phonetypeName == null || "".equals(phonetypeName)){
			value = "厂商名字不能为空";
		}else{
			Phonetype phonebtype = new Phonetype();
			phonebtype.setPhonetypeName(phonetypeName);
			Phonebrand phonebrand = new Phonebrand();
			phonebrand.setPhonebrandId(phonebrandId);
			phonebtype.setPhonebrand(phonebrand);
			phonebtype.setStatus(1);
			
			boolean b = expService.save(phonebtype);
			if(b){
				value="添加成功";
			}else{
				value="添加失败";
			}
		}
		request.setAttribute("value", value);
		return "success";
	}
	
	@RequestMapping(value = "/exeAdd",params="equipment")
	public String exeAddEquipment(HttpServletRequest request,
			HttpServletResponse response){
		Integer teamId = Integer.parseInt(request.getParameter("teamId"));
		Integer systemId = Integer.parseInt(request.getParameter("systemId"));
		String equiName = request.getParameter("equiName");
		String mac = request.getParameter("mac");
		String ip = request.getParameter("ip");
		String harddisk = request.getParameter("harddisk");
		String value = null;
		if(equiName == null || "".equals(equiName)){
			value = "设备名不能为空";
		}else if(mac == null || "".equals(mac)){
			value = "mac不能为空";
		}else if(ip == null || "".equals(ip)){
			value = "ip不能为空";
		}else if(harddisk == null || "".equals(harddisk)){
			value = "硬盘码不能为空";
		}else{
			Equipment e = new Equipment();
			e.setMac(mac);
			
			if(expService.alreadyHas(e)){
				value = "该设备已经存在";
			}else{
				Team t = new Team();
				t.setTeamId(teamId);
				e.setTeam(t);
				Sysinfo sysinfo = new Sysinfo();
				sysinfo.setSystemId(systemId);
				e.setSysinfo(sysinfo);
				e.setAddTime(new Date());
				e.setEquiName(equiName);
				e.setHarddisk(harddisk);
				e.setIp(ip);
				e.setStatus(1);
				String code = Code.getCode();
				e.setCode(code);
				
				boolean b = expService.save(e);
				if(b){
					value="添加成功,设备码为："+code;
				}else{
					value="添加失败";
				}
			}
		}
		request.setAttribute("value", value);
		return "success";
	}
	
	@RequestMapping(value = "/exeAdd",params="employee")
	public String exeAddEmployee(HttpServletRequest request,
			HttpServletResponse response){
		Integer teamId = Integer.parseInt(request.getParameter("teamId"));
		String password = request.getParameter("password");
		String phoneNumber = request.getParameter("phoneNumber");
		String empName = request.getParameter("empName");
		String empNo = request.getParameter("empNo");
		String[] powers = request.getParameterValues("power");
		StringBuffer power = new StringBuffer();
		if(powers != null){
			for(int i=0;i<powers.length;i++){
				power.append(powers[i]+",");
			}
		}
		String value = null;
		if(password == null || "".equals(password)){
			value = "密码不能为空";
		}else if(phoneNumber == null || "".equals(phoneNumber)){
			value = "手机号码不能为空";
		}else if(empName == null || "".equals(empName)){
			value = "员工姓名不能为空";
		}else if(empNo == null || "".equals(empNo)){
			value = "员工编号不能为空";
		}else{
			Employee e = new Employee();
			e.setEmpNo(empNo);
			if(expService.alreadyHas(e)){
				value = "该员工编号已经存在";
			}else{
				e.setAddTime(new Date());
				e.setPassword(password);
				e.setPhoneNumber(phoneNumber);
				e.setEmpName(empName);
				e.setPower(power.toString());
				e.setStatus(1);
				Team t = new Team();
				t.setTeamId(teamId);
				e.setTeam(t);
				boolean b = expService.save(e);
				if(b){
					value="添加成功";
				}else{
					value="添加失败";
				}
			}
		}
		request.setAttribute("value", value);
		return "success";
	}
	
	@RequestMapping(value = "/exeAdd",params="sysinfo")
	public String exeAddSystem(HttpServletRequest request,
			HttpServletResponse response){
		String systemName = request.getParameter("systemName");
		String value = null;
		if(systemName == null || "".equals(systemName)){
			value = "系统名称不能为空";
		}else{
			Sysinfo e = new Sysinfo();
			e.setAddTime(new Date());
			e.setSystemName(systemName);
			e.setStatus(1);
			boolean b = expService.save(e);
			if(b){
				value="添加成功";
			}else{
				value="添加失败";
			}
		}
		request.setAttribute("value", value);
		return "success";
	}
	
	@RequestMapping(value = "/exeAdd",params="sendSMS")
	public String exeAddsendSMS(HttpServletRequest request,
			HttpServletResponse response){
		String value = "ok";
		String context = request.getParameter("context");
		if(context == null || context.length() <= 0){
			value ="发送内容不能为空！";
		}else{
			String[] names = request.getParameterValues("sendName");
			String[] sendPhone = request.getParameterValues("sendPhone");
			if(names == null && sendPhone == null){
				value = "姓名或手机号不能为空！";
			}else{
				for(int i=0;i<names.length;i++){
					if(names[i] == null || names[i].length() <= 0){
						continue;
					}
					if(sendPhone[i] == null || sendPhone[i].length() <= 0){
						continue;
					}
					if(!sendPhone[i].matches("^(13[4-9]|15[0|1|2|7|8|9]|18[8|9]|147)\\d{8}$")){
						continue;
					}
					String sendContext = context.replace("{name}",names[i]);
					sendContext = sendContext.replace("{phoneNumber}",sendPhone[i]);
					smsService.sendSMS(sendPhone[i], sendContext);
					//System.out.println(sendPhone[i]);
					//System.out.println(sendContext);
				}
			}
			
		}
		
		request.setAttribute("value", value);
		return "success";
	}
	
	@RequestMapping(value = "/exeAdd",params="user")
	public String exeAddUser(HttpServletRequest request,
			HttpServletResponse response){
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String nickName = request.getParameter("nickname");
		Integer systemId = Integer.parseInt(request.getParameter("systemId"));
		String[] powers = request.getParameterValues("power");
		StringBuffer power = new StringBuffer();
		if(powers != null){
			for(int i=0;i<powers.length;i++){
				power.append(powers[i]+",");
			}
		}
		
		String value = null;
		if(userName == null || "".equals(userName)){
			value = "用户名不能为空";
		}else if(userPassword == null || "".equals(userPassword)){
			value = "密码不能为空";
		}else if(nickName == null || "".equals(nickName)){
			value = "姓名不能为空";
		}else{
			Userinfo userinfo = new Userinfo();
			userinfo.setUserName(userName);
			
			if(expService.alreadyHas(userinfo)){
				value="该用户名已经存在";
			}else{
				userinfo.setAddTime(new Date());
				
				userinfo.setUserPassword(userPassword);
				userinfo.setNickName(nickName);
				userinfo.setPower(power.toString());
				userinfo.setStatus(1);
				Sysinfo sysinfo = new Sysinfo();
				sysinfo.setSystemId(systemId);
				userinfo.setSysinfo(sysinfo);
				boolean b = expService.save(userinfo);
				if(b){
					value="添加成功";
				}else{
					value="添加失败";
				}
			}
			
		}
		request.setAttribute("value", value);
		return "success";
	}
	

	@RequestMapping(value = "/exeAdd",params="menu")
	public String exeAddMenu(HttpServletRequest request,
			HttpServletResponse response){
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
		
		boolean b = expService.save(e);
		if(b){
			request.setAttribute("value", "添加成功");
		}else{
			request.setAttribute("value", "添加失败");
		}
		
		return "success";
	}
	
	@RequestMapping(value = "/exeAdd",params="emailService")
	public String exeEmalService(HttpServletRequest request,
			HttpServletResponse response){
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
		service.setServiceCode(serviceCode);
		service.setServiceName(serviceName);
		Emailtemplete templete = new Emailtemplete();
		templete.setTempleteId(templeteId);
		service.setEmailtemplete(templete);
		service.setStatus(1);
		boolean b = expService.save(service);
		if(b){
			request.setAttribute("value", "添加成功");
		}else{
			request.setAttribute("value", "添加失败");
		}
		
		return "success";
	}
	
	@RequestMapping(value = "/exeAdd",params="emailTemplete")
	public String exeEmalTemplete(HttpServletRequest request,
			HttpServletResponse response){
		String templeteName = request.getParameter("templeteName");
		if(templeteName == null){
			request.setAttribute("value", "模板名称不能为空");
			return "success";
		}
		String templeteView = request.getParameter("templeteView");
		System.out.println(templeteView);
		request.setAttribute("templeteName", templeteName);
		request.setAttribute("templeteView", templeteView);
		return "email/emailTemplete_add_review";
	}
	
	@RequestMapping(value = "/exeAdd",params="emailTempleteReview")
	public String exeEmailtempleteReview(HttpServletRequest request,
			HttpServletResponse response){
		String templeteIdStr = request.getParameter("templeteId");
		String templeteName = request.getParameter("templeteName");
		String templeteView = request.getParameter("templeteView");
		System.out.println("templeteView:"+templeteView);
		boolean b = false;
		if(templeteIdStr == null || "".equals(templeteIdStr)){
			Emailtemplete templete = new Emailtemplete();
			templete.setTempleteName(templeteName);
			templete.setTempleteView(templeteView);
			templete.setStatus(1);
			b = expService.save(templete);
		}else{
			Emailtemplete templete = new Emailtemplete();
			templete.setTempleteId(Integer.parseInt(templeteIdStr));
			templete.setTempleteName(templeteName);
			templete.setTempleteView(templeteView);
			templete.setStatus(Integer.parseInt(request.getParameter("status")));
			b = expService.update(templete);
		}
		if(b){
			request.setAttribute("value", "操作成功");
		}else{
			request.setAttribute("value", "操作失败");
		}
		return "success";
	}
	
	
	@RequestMapping(value = "/exeAdd",params="renewal")
	public String exeAddMenuRenewal(HttpServletRequest request,
			HttpServletResponse response){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String version = multipartRequest.getParameter("version");
		String status = multipartRequest.getParameter("status");
		int systemId = Integer.parseInt(multipartRequest.getParameter("systemId"));
		if (version == null || "".equals(version)) {
			request.setAttribute("value", "版本号不能为空");
			return "success";
		}

		MultipartFile mFile = multipartRequest.getFile("file");

		if (mFile == null) {
			request.setAttribute("value", "附件不能为空");
			return "success";
		}
		if (validateFile(mFile)) {
			File file = this.getFile(request, mFile);
			if (file != null) {
				Renewal renewal = new Renewal();
				renewal.setUploadTime(new Date());
				renewal.setVersion(version);
				Sysinfo sysinfo = new Sysinfo();
				sysinfo.setSystemId(systemId);
				renewal.setSysinfo(sysinfo);
				renewal.setUrl("upload/" + file.getName());
				
				if ("1".equals(status)) {
					expService.disRenewalDefalut();
					renewal.setStatus(1);
				}else{
					renewal.setStatus(0);
				}
				
				boolean b = expService.save(renewal);
				if (b) {
					request.setAttribute("value", "添加成功");
				} else {
					request.setAttribute("value", "添加失败");
				}
				return "success";
			} else {
				request.setAttribute("value", "该文件已经存在，或者未知问题");
				return "success";
			}
		} else {
			request.setAttribute("value", "上传文件格式不正确或大小超出范围");
			return "success";
		}
	}
	
	// 上传文件并返回文件的实例
	private File getFile(HttpServletRequest request, MultipartFile imgFile) {
		File file = null;
		try {
			// 将文件保存在upload文件夹下面
			String path = request.getSession().getServletContext().getRealPath(
					"/");
			System.out.println(path);
			file = new File(path + "upload/" + imgFile.getOriginalFilename());
			if (file.exists()) {
				return null;
			}
			imgFile.transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}
	
	// 验证文件是否符合标准
	private boolean validateFile(MultipartFile file) {
		// 判断flie的大小不能大于2000000
		if (file.getSize() < 0 || file.getSize() > 20000000) {
			return false;
		}
		String filename = file.getOriginalFilename();
		String extName = filename.substring(filename.lastIndexOf("."))
				.toLowerCase();
		// file确定file的后缀名
		if (extName.equals(".apk")) {
			return true;
		} else {
			return false;
		}
	}
}
