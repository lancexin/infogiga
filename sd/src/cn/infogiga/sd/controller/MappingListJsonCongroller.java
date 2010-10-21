package cn.infogiga.sd.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cindy.page.beanutils.MyBeanUtils;
import cindy.page.hibernate.CirteriaBean;
import cindy.page.hibernate.CirteriaQuery;
import cindy.page.hibernate.PageBean;
import cindy.page.power.Node;
import cindy.util.DateUtil;
import cn.infogiga.sd.dto.JsonAdmin;
import cn.infogiga.sd.dto.JsonBissinussHall;
import cn.infogiga.sd.dto.JsonCity;
import cn.infogiga.sd.dto.JsonDownloadtype;
import cn.infogiga.sd.dto.JsonEmployee;
import cn.infogiga.sd.dto.JsonEquipment;
import cn.infogiga.sd.dto.JsonListBean;
import cn.infogiga.sd.dto.JsonMusic;
import cn.infogiga.sd.dto.JsonMusicdownloadstat;
import cn.infogiga.sd.dto.JsonMusicindex;
import cn.infogiga.sd.dto.JsonMusicman;
import cn.infogiga.sd.dto.JsonMusicmenu;
import cn.infogiga.sd.dto.JsonPhonebrand;
import cn.infogiga.sd.dto.JsonPhoneplatform;
import cn.infogiga.sd.dto.JsonPhonetype;
import cn.infogiga.sd.dto.JsonPower;
import cn.infogiga.sd.dto.JsonProvince;
import cn.infogiga.sd.dto.JsonSoft;
import cn.infogiga.sd.dto.JsonSoftAttachment;
import cn.infogiga.sd.dto.JsonSoftDownloadStat;
import cn.infogiga.sd.dto.JsonSoftIndex;
import cn.infogiga.sd.dto.JsonSoftMenu;
import cn.infogiga.sd.dto.JsonVideo;
import cn.infogiga.sd.dto.JsonVideomenu;
import cn.infogiga.sd.pojo.Admin;
import cn.infogiga.sd.pojo.Bissinusshall;
import cn.infogiga.sd.pojo.City;
import cn.infogiga.sd.pojo.Downloadtype;
import cn.infogiga.sd.pojo.Employee;
import cn.infogiga.sd.pojo.Equipment;
import cn.infogiga.sd.pojo.Music;
import cn.infogiga.sd.pojo.Musicdownloadstat;
import cn.infogiga.sd.pojo.Musicindex;
import cn.infogiga.sd.pojo.Musicman;
import cn.infogiga.sd.pojo.Musicmenu;
import cn.infogiga.sd.pojo.Phonebrand;
import cn.infogiga.sd.pojo.Phoneplatform;
import cn.infogiga.sd.pojo.Phonetype;
import cn.infogiga.sd.pojo.Power;
import cn.infogiga.sd.pojo.Province;
import cn.infogiga.sd.pojo.Soft;
import cn.infogiga.sd.pojo.Softattachment;
import cn.infogiga.sd.pojo.Softdownloadstat;
import cn.infogiga.sd.pojo.Softindex;
import cn.infogiga.sd.pojo.Softmenu;
import cn.infogiga.sd.pojo.Video;
import cn.infogiga.sd.pojo.Videomenu;
import cn.infogiga.sd.service.ManageService;
import cn.infogiga.sd.service.PowerService;

public class MappingListJsonCongroller {
	

	ManageService manageService;
	
	PowerService powerService;

	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}

	public void setPowerService(PowerService powerService) {
		this.powerService = powerService;
	}
	

	@RequestMapping(value = "/json",params="comboAllPower")
	public String comboAllPowerJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<Node> nodeList = powerService.getBaseNode(request.getRealPath("WEB-INF/power/power-config.xml"));
		model.addAttribute("array", nodeList);
		return "list";

	}
	
	@RequestMapping(value = "/json",params="comboPower")
	public String comboPowerJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonPower> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findByProperty(Power.class, "status", 1), JsonPower.class);
		model.addAttribute("array", powerList);
		return "list";

	}
	
	@RequestMapping(value = "/json",params="comboProvince")
	public String comboProvinceJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonProvince> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Province.class), JsonProvince.class);
		model.addAttribute("array", powerList);
		return "list";

	}

	@RequestMapping(value = "/json",params="comboCity")
	public String comboCityJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("provinceId")Integer provinceId){
		List<JsonCity> powerList;
		if(provinceId == -1){
			powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(City.class), JsonCity.class);
		}else{
			powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findByProperty(City.class, "province.provinceId", provinceId), JsonCity.class);
		}
		model.addAttribute("array", powerList);
		return "list";

	}
	
	@RequestMapping(value = "/json",params="comboHall")
	public String comboHallJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonBissinussHall> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Bissinusshall.class), JsonBissinussHall.class);
		model.addAttribute("array", powerList);
		return "list";

	}

	@RequestMapping(value = "/json",params="comboPhonebrand")
	public String comboPhonebrandJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonPhonebrand> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Phonebrand.class), JsonPhonebrand.class);
		model.addAttribute("array", powerList);
		return "list";

	}

	@RequestMapping(value = "/json",params="comboPlatform")
	public String comboPlatformJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonPhoneplatform> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Phoneplatform.class), JsonPhoneplatform.class);
		model.addAttribute("array", powerList);
		return "list";

	}

	@RequestMapping(value = "/json",params="comboSoftmenu")
	public String comboSoftmenuJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonSoftMenu> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Softmenu.class), JsonSoftMenu.class);
		model.addAttribute("array", powerList);
		return "list";

	}
	
	@RequestMapping(value = "/json",params="comboSoft")
	public String comboSoftJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonSoft> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Soft.class), JsonSoft.class);
		model.addAttribute("array", powerList);
		return "list";

	}
	
	@RequestMapping(value = "/json",params="comboPhonetype")
	public String comboPhonetypeJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonPhonetype> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Phonetype.class), JsonPhonetype.class);
		
		model.addAttribute("array", powerList);
		return "list";

	}
	
	@RequestMapping(value = "/json",params="comboDownloadtype")
	public String comboDownloadtypeJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonDownloadtype> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Downloadtype.class), JsonDownloadtype.class);
		JsonDownloadtype all = new JsonDownloadtype();
		all.setDownloadtypeId("-1");
		all.setDownloadtypeName("全部");
		powerList.add(all);
		model.addAttribute("array", powerList);
		return "list";

	}
	
	@RequestMapping(value = "/json",params="comboMusicmenu")
	public String comboMusicmenu(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonMusicmenu> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findByProperty(Musicmenu.class, "isLeaf", 1), JsonMusicmenu.class);
		model.addAttribute("array", powerList);
		return "list";

	}
	
	@RequestMapping(value = "/json",params="powerList")
	public String powerJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start,@RequestParam("limit")Integer limit){
		//List<Power> powerList = manageService.getManageDAO().getListByPage(Power.class, start, limit);
		List<JsonPower> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByPage(Power.class, start, limit), JsonPower.class);
		int totalCount = manageService.getManageDAO().getCount(Power.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,powerList);
		model.addAttribute("object", jsonListBean);
		return "list";

	}
	
	
	@RequestMapping(value = "/json",params="adminList")
	public String adminJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start,@RequestParam("limit")Integer limit){
		List<JsonAdmin> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByPage(Admin.class, start, limit), JsonAdmin.class);
		int totalCount = manageService.getManageDAO().getCount(Admin.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,powerList);
		model.addAttribute("object", jsonListBean);
		return "list";

	}

	@RequestMapping(value = "/json",params="musicmanList")
	public String musicmanJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonMusicman> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Musicman.class), JsonMusicman.class);
		int totalCount = manageService.getManageDAO().getCount(Musicman.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,powerList);
		model.addAttribute("object", jsonListBean);
		return "list";
	}
	
	@RequestMapping(value = "/json",params="hallList")
	public String hallJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start,@RequestParam("limit")Integer limit){
		List<JsonBissinussHall> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByPage(Bissinusshall.class, start, limit), JsonBissinussHall.class);
		int totalCount = manageService.getManageDAO().getCount(Bissinusshall.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,powerList);
		model.addAttribute("object", jsonListBean);
		return "list";

	}
	
	@RequestMapping(value = "/json",params="equiList")
	public String equiJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start,@RequestParam("limit")Integer limit){
		List<JsonEquipment> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByPage(Equipment.class, start, limit), JsonEquipment.class);
		int totalCount = manageService.getManageDAO().getCount(Equipment.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,powerList);
		model.addAttribute("object", jsonListBean);
		return "list";

	}
	

	@RequestMapping(value = "/json",params="empList")
	public String employeeJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start,@RequestParam("limit")Integer limit){
		List<JsonEmployee> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByPage(Employee.class, start, limit), JsonEmployee.class);
		int totalCount = manageService.getManageDAO().getCount(Employee.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list);
		model.addAttribute("object", jsonListBean);
		return "list";

	}

	@RequestMapping(value = "/json",params="provinceList")
	public String proviceJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonProvince> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Province.class), JsonProvince.class);
		int totalCount = manageService.getManageDAO().getCount(Province.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list);
		model.addAttribute("object", jsonListBean);
		return "list";

	}

	@RequestMapping(value = "/json",params="cityList")
	public String cityJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("provinceId")Integer provinceId){
		List<JsonCity> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findByProperty(City.class, "province.provinceId", provinceId), JsonCity.class);
		int totalCount = manageService.getManageDAO().getCount(City.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list);
		model.addAttribute("object", jsonListBean);
		return "list";

	}

	@RequestMapping(value = "/json",params="phonebrandList")
	public String phonebrandJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonPhonebrand> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Phonebrand.class), JsonPhonebrand.class);
		int totalCount = manageService.getManageDAO().getCount(Phonebrand.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list);
		model.addAttribute("object", jsonListBean);
		return "list";

	}

	@RequestMapping(value = "/json",params="platformList")
	public String phoneplatformJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonPhoneplatform> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Phoneplatform.class), JsonPhoneplatform.class);
		int totalCount = manageService.getManageDAO().getCount(Phoneplatform.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list);
		model.addAttribute("object", jsonListBean);
		return "list";

	}

	@RequestMapping(value = "/json",params="phonetypeList")
	public String phonetypeJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonPhonetype> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Phonetype.class), JsonPhonetype.class);
		int totalCount = manageService.getManageDAO().getCount(Phonetype.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list);
		model.addAttribute("object", jsonListBean);
		return "list";

	}

	@RequestMapping(value = "/json",params="softmenuList")
	public String softMenuJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonSoftMenu> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Softmenu.class), JsonSoftMenu.class);
		int totalCount = manageService.getManageDAO().getCount(Softmenu.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list);
		model.addAttribute("object", jsonListBean);
		return "list";
	}
	
	@RequestMapping(value = "/json",params="softList")
	public String softJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start,@RequestParam("limit")Integer limit){
		List<JsonSoft> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByPage(Soft.class, start, limit), JsonSoft.class);
		int totalCount = manageService.getManageDAO().getCount(Soft.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list);
		model.addAttribute("object", jsonListBean);
		return "list";
	}

	@RequestMapping(value = "/json",params="videoList")
	public String videoJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start,@RequestParam("limit")Integer limit){
		List<JsonVideo> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByPage(Video.class, start, limit), JsonVideo.class);
		int totalCount = manageService.getManageDAO().getCount(Video.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list);
		model.addAttribute("object", jsonListBean);
		return "list";
	}
	
	
	@RequestMapping(value = "/json",params="musicList")
	public String musicJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("musicmanId")Integer musicmanId){
		List<JsonMusic> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findByProperty(Music.class, "musicman.musicmanId", musicmanId), JsonMusic.class);
		int totalCount = manageService.getManageDAO().getCountByProperty(Music.class, "musicman.musicmanId", musicmanId);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list);
		model.addAttribute("object", jsonListBean);
		return "list";
	}
	
	@RequestMapping(value = "/json",params="musicindexList")
	public String musicindexJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("musicId")Integer musicId){
		List<JsonMusicindex> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findByProperty(Musicindex.class, "music.musicId", musicId), JsonMusicindex.class);
		int totalCount = manageService.getManageDAO().getCountByProperty(Musicindex.class, "music.musicId", musicId);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list);
		model.addAttribute("object", jsonListBean);
		return "list";
	}
	
	@RequestMapping(value = "/json",params="softattactmentList")
	public String softattmentJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonSoftAttachment> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Softattachment.class), JsonSoftAttachment.class);
		int totalCount = manageService.getManageDAO().getCountByProperty(Soft.class, "status", 1);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list);
		model.addAttribute("object", jsonListBean);
		return "list";
	}

	@RequestMapping(value = "/json",params="softindexList")
	public String softindexJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("attachmentId")Integer attachmentId){
		List<JsonSoftIndex> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findByProperty(Softindex.class, "softattachment.attachmentId", attachmentId), JsonSoftIndex.class);
		int totalCount = manageService.getManageDAO().getCountByProperty(Soft.class, "status", 1);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list);
		model.addAttribute("object", jsonListBean);
		return "list";
	}
	
	@RequestMapping(value = "/json",params="musicmenuList")
	public String musicmenuJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("isLeaf")Integer isLeaf){
		switch (isLeaf) {
		case 0:
			return  musicmenuJsonList(request,response,session, model);
		case 1:
			return  musicmenuLeafJsonList(request,response,session, model);
		default:
			return "list";
		}
		
	}
	
	public String musicmenuLeafJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		Integer fathermusicmenuId = Integer.parseInt(request.getParameter("fathermusicmenuId"));
		List<JsonMusicmenu> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findByProperty(Musicmenu.class, "musicmenu.musicmenuId", fathermusicmenuId), JsonMusicmenu.class);
		int totalCount = manageService.getManageDAO().getCountByProperty(Musicmenu.class, "musicmenu.musicmenuId", fathermusicmenuId);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list);
		model.addAttribute("object", jsonListBean);
		return "list";
	}

	public String musicmenuJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonMusicmenu> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findByProperty(Musicmenu.class, "isLeaf", 0), JsonMusicmenu.class);
		int totalCount = manageService.getManageDAO().getCountByProperty(Musicmenu.class, "isLeaf", 0);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list);
		model.addAttribute("object", jsonListBean);
		return "list";
	}

	@RequestMapping(value = "/json",params="videomenuList")
	public String videomenuJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonVideomenu> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Videomenu.class), JsonVideomenu.class);
		int totalCount = manageService.getManageDAO().getCount(Videomenu.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list);
		model.addAttribute("object", jsonListBean);
		return "list";
	}
	
	@RequestMapping(value = "/json",params="softdownloadstatList")
	public String softdownloadstatJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start,@RequestParam("limit")Integer limit){
		Integer downloadtypeId = (request.getParameter("downloadtypeId")==null || request.getParameter("downloadtypeId").length()==0)?-1:Integer.parseInt(request.getParameter("downloadtypeId"));	
		String employeeName = (request.getParameter("employeeName")==null || request.getParameter("employeeName").length()==0)?null:request.getParameter("employeeName");	
		String employeeNo = (request.getParameter("employeeNo")==null || request.getParameter("employeeNo").length()==0)?null:request.getParameter("employeeNo");	
		String equipmentName  = (request.getParameter("equipmentName")==null || request.getParameter("equipmentName").length()==0)?null:request.getParameter("equipmentName");	
		String hallName	 = (request.getParameter("hallName")==null || request.getParameter("hallName").length()==0)?null:request.getParameter("hallName");	
		String phonebrandName = (request.getParameter("phonebrandName")==null || request.getParameter("phonebrandName").length()==0)?null:request.getParameter("phonebrandName");	
		String phonetypeName = (request.getParameter("phonetypeName")==null || request.getParameter("phonetypeName").length()==0)?null:request.getParameter("phonetypeName");	
		String softName = (request.getParameter("softName")==null || request.getParameter("softName").length()==0)?null:request.getParameter("softName");	
		Date startTime = (request.getParameter("startTime")==null || request.getParameter("startTime").length()==0)?null:DateUtil.stringToDate(request.getParameter("startTime"), DateUtil.NOW_DATE);	
		Date endTime = (request.getParameter("endTime")==null || request.getParameter("endTime").length()==0)?null:DateUtil.stringToDate(request.getParameter("endTime"), DateUtil.NOW_DATE);	
		
		PageBean pageBean = new PageBean(start,limit);
		CirteriaBean cBean = new CirteriaBean("addTime");
		cBean.setPageBean(pageBean);
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"d.downloadtypeId",downloadtypeId,new String[]{"downloadtype","d"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"emp.nickName",employeeName,new String[]{"employee","emp"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"emp.userName",employeeNo,new String[]{"employee","emp"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"eq.equipmentName",equipmentName,new String[]{"equipment","eq"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"bh.hallName",hallName,new String[]{"equipment","eq","eq.bissinusshall","bh"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"pt.phonetypeName",phonetypeName,new String[]{"phonetype","pt"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"pb.phonebrandName",phonebrandName,new String[]{"phonetype","pt","pt.phonebrand","pb"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"sf.softName",softName,new String[]{"soft","sf"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.BETWEED,CirteriaQuery.IS_OBJECT,"addTime",new Object[]{startTime,endTime},null));
		
		int totalCount = manageService.getManageDAO().getCountByPage(Softdownloadstat.class, cBean);
		
		List<JsonSoftDownloadStat> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByPage(Softdownloadstat.class, cBean), JsonSoftDownloadStat.class);
	
		JsonListBean jsonListBean = new JsonListBean(totalCount,list);
		model.addAttribute("object", jsonListBean);
		return "list";

	}
	
	
	@RequestMapping(value = "/json",params="musicdownloadstatList")
	public String musicdownloadstatJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start,@RequestParam("limit")Integer limit){
		Integer downloadtypeId = (request.getParameter("downloadtypeId")==null || request.getParameter("downloadtypeId").length()==0)?-1:Integer.parseInt(request.getParameter("downloadtypeId"));	
		String employeeName = (request.getParameter("employeeName")==null || request.getParameter("employeeName").length()==0)?null:request.getParameter("employeeName");	
		String employeeNo = (request.getParameter("employeeNo")==null || request.getParameter("employeeNo").length()==0)?null:request.getParameter("employeeNo");	
		String equipmentName  = (request.getParameter("equipmentName")==null || request.getParameter("equipmentName").length()==0)?null:request.getParameter("equipmentName");	
		String hallName	 = (request.getParameter("hallName")==null || request.getParameter("hallName").length()==0)?null:request.getParameter("hallName");	
		String musicName = (request.getParameter("musicName")==null || request.getParameter("musicName").length()==0)?null:request.getParameter("musicName");	
		String musicmanName = (request.getParameter("musicmanName")==null || request.getParameter("musicmanName").length()==0)?null:request.getParameter("musicmanName");	
		Date startTime = (request.getParameter("startTime")==null || request.getParameter("startTime").length()==0)?null:DateUtil.stringToDate(request.getParameter("startTime"), DateUtil.NOW_DATE);	
		Date endTime = (request.getParameter("endTime")==null || request.getParameter("endTime").length()==0)?null:DateUtil.stringToDate(request.getParameter("endTime"), DateUtil.NOW_DATE);	
		
		PageBean pageBean = new PageBean(start,limit);
		CirteriaBean cBean = new CirteriaBean("addTime");
		cBean.setPageBean(pageBean);
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"d.downloadtypeId",downloadtypeId,new String[]{"downloadtype","d"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"emp.nickName",employeeName,new String[]{"employee","emp"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"emp.userName",employeeNo,new String[]{"employee","emp"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"eq.equipmentName",equipmentName,new String[]{"equipment","eq"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"bh.hallName",hallName,new String[]{"equipment","eq","eq.bissinusshall","bh"}));

		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"mu.musicName",musicName,new String[]{"music","mu"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"mn.musicmanName",musicmanName,new String[]{"music","mu","mu.musicman","mn"}));
		
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.BETWEED,CirteriaQuery.IS_OBJECT,"addTime",new Object[]{startTime,endTime},null));
		
		int totalCount = manageService.getManageDAO().getCountByPage(Musicdownloadstat.class, cBean);
		
		List<JsonMusicdownloadstat> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByPage(Musicdownloadstat.class, cBean), JsonMusicdownloadstat.class);
	
		JsonListBean jsonListBean = new JsonListBean(totalCount,list);
		model.addAttribute("object", jsonListBean);
		return "list";

	}
}
