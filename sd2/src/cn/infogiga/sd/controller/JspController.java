package cn.infogiga.sd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.infogiga.sd.pojo.Phonearray;
import cn.infogiga.sd.pojo.Soft;
import cn.infogiga.sd.pojo.Softmenu;
import cn.infogiga.sd.service.ManageService;

@Controller
public class JspController {
	
	@Autowired
	ManageService manageService;
	
	@RequestMapping(value = "/p",params="test")
	public String test(ModelMap model){
		List<Phonearray> list = manageService.getManageDAO().findAll(Phonearray.class);
		model.addAttribute("phonearray", list);
		
		List<Softmenu> mlist = manageService.getManageDAO().findAll(Softmenu.class);
		model.addAttribute("softmenu", mlist);
		return "test";
	}
	
	@RequestMapping(value = "/admin")
	public String admin(){
		return "admin";
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
		return "system/admin/admin";
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

	@RequestMapping(value = "/p",params="phonebrandList")
	public String phonebrandList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "software/phonebrand/phonebrand";
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
	
	@RequestMapping(value = "/p",params="attachmentList")
	public String attachmentList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "software/attachment/attachment";
	}
	
	@RequestMapping(value = "/p",params="addphonesoft")
	public String addPhoneSoftPage(ModelMap model){
		List<Phonearray> list = manageService.getManageDAO().findAll(Phonearray.class);
		model.addAttribute("phonearray", list);
		
		List<Softmenu> mlist = manageService.getManageDAO().findAll(Softmenu.class);
		model.addAttribute("softmenu", mlist);
		return "software/softinfo/addsoft2";
	}

	@RequestMapping(value = "/p",params="updatephonesoft")
	public String updatePhoneSoftPage(ModelMap model,@RequestParam("softId")Integer softId){
		Soft soft = manageService.getManageDAO().findById(Soft.class, softId);
		model.addAttribute("soft", soft);
		
		List<Softmenu> mlist = manageService.getManageDAO().findAll(Softmenu.class);
		model.addAttribute("softmenu", mlist);
		
		List<Phonearray> list = manageService.getManageDAO().findAll(Phonearray.class);
		model.addAttribute("phonearray", list);
		return "software/softinfo/updatesoft2";
	}
}
