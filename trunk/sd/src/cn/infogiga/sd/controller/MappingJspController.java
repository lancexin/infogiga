package cn.infogiga.sd.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class MappingJspController {
	
	@RequestMapping(value = "/admin")
	public String admin(){
		return "admin2";
	}
	
	@RequestMapping(value = "/p",params="powerList")
	public String powerList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "system/power/power";
	}
	
	@RequestMapping(value = "/p",params="adminList")
	public String adminList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "system/admin/admin2";
	}
	
	@RequestMapping(value = "/p",params="hallList")
	public String hallList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "system/bissinusshall/bissinusshall";
	}
	
	@RequestMapping(value = "/p",params="equiList")
	public String equiList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "system/equipment/equipment";
	}

	@RequestMapping(value = "/p",params="empList")
	public String empList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "system/employee/employee";
	}

	@RequestMapping(value = "/p",params="areaList")
	public String areaList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "system/area/area_list";
	}

	@RequestMapping(value = "/p",params="phonebaseinfo")
	public String phonebaseinfo(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "software/phonebaseinfo/phonebaseinfo";
	}

	@RequestMapping(value = "/p",params="phonetypeList")
	public String phonetypeList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "software/phonetype/phonetype";
	}

	@RequestMapping(value = "/p",params="softList")
	public String softList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "software/softinfo/softinfo";
	}

	@RequestMapping(value = "/p",params="softattactmentList")
	public String softattactmentList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "software/softattachment/softattachment";
	}

	@RequestMapping(value = "/p",params="softdownloadstatList")
	public String softdownloadstatList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "software/softdownloadstat/softdownloadstat_list";
	}

	@RequestMapping(value = "/p",params="musicmenuList")
	public String musicmenuList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "music/musicmenu/musicmenu";
	}

	@RequestMapping(value = "/p",params="musicbaseinfo")
	public String musicbaseinfoList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "music/musicbaseinfo/musicbaseinfo";
	}

	@RequestMapping(value = "/p",params="musicdownloadstat")
	public String musicdownloadstat(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "music/musicdownloadstat/musicdownloadstat";
	}

	@RequestMapping(value = "/p",params="videobaseinfo")
	public String videomenuList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "video/videobaseinfo/videobaseinfo";
	}

	@RequestMapping(value = "/p",params="videodownloadstat")
	public String videodownloadstatList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "video/videodownloadstat/videodownloadstat";
	}

	@RequestMapping(value = "/p",params="selfinfo")
	public String selfinfoList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "shop/selfinfo/selfinfo";
	}

	@RequestMapping(value = "/p",params="shopequipment")
	public String shopequipmentList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "shop/equipment/equipment";
	}

	@RequestMapping(value = "/p",params="shopemployee")
	public String shopemployeeList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "shop/employee/employee";
	}

	@RequestMapping(value = "/p",params="shopstat")
	public String shopstatList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "shop/shopstat/shopstat";
	}
}
