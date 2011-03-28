package cn.infogiga.szqb.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cindy.page.beanutils.MyBeanUtils;
import cn.infogiga.szqb.pojo.Page;
import cn.infogiga.szqb.pojo.Periodical;
import cn.infogiga.szqb.vo.JsonPeriodical2;
import cn.infogiga.szqb.web.service.ManageService;



@Controller
public class JspController {
	
	@Autowired
	ManageService manageService;
	

	@RequestMapping(value = "/admin")
	public String admin(){
		return "admin";
	}
		
	@RequestMapping(value = "/p",params="powerList")
	public String powerList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "system/power";
	}
	
	@RequestMapping(value = "/p",params="adminList")
	public String adminList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "system/admin";
	}

	@RequestMapping(value = "/p",params="baseinfo")
	public String baseinfo(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "reader/base_info";
	}

	@RequestMapping(value = "/p",params="periodicalList")
	public String periodicalList(ModelMap model,@RequestParam("code")String code,@RequestParam("name")String name){
		model.put("code", code);
		model.put("name", name);
		return "reader/periodical_list";
	}
	
	@RequestMapping(value = "/p",params="addPeriodical")
	public String addPeriodical(ModelMap model){
		return "reader/add_periodical";
	}

	@RequestMapping(value = "/p",params="updatePeriodical")
	public String updatePeriodical(ModelMap model,@RequestParam("periodicalId")Integer periodicalId){
		Periodical periodical = manageService.getManageDAO().findById(Periodical.class, periodicalId);
		JsonPeriodical2 jp2 = MyBeanUtils.copyProperties(periodical, JsonPeriodical2.class);
		model.put("model", jp2);
		
		return "reader/update_periodical";
	}

	@RequestMapping(value = "/p",params="reviewPeriodical")
	public String reviewPeriodical(ModelMap model,@RequestParam("periodicalId")Integer periodicalId){
		Periodical periodical = manageService.getManageDAO().findById(Periodical.class, periodicalId);
		List<Page> pages = manageService.getManageDAO().findByProperty(Page.class, "periodical.id", periodicalId,"addTime");
		model.put("periodical", periodical);
		model.put("pages", pages);
		return "reader/review_periodical";
	}

	@RequestMapping(value = "/p",params="searchPeriodical")
	public String searchPeriodical(ModelMap model,@RequestParam("periodicalId")Integer periodicalId){
		Periodical periodical = manageService.getManageDAO().findById(Periodical.class, periodicalId);
		List<Page> pages = manageService.getManageDAO().findByProperty(Page.class, "periodical.id", periodicalId,"addTime");
		model.put("periodical", periodical);
		model.put("pages", pages);
		return "reader/search_periodical";
	}

	@RequestMapping(value = "/p",params="addPage")
	public String addPage(ModelMap model,@RequestParam("periodicalId")Integer periodicalId){
		model.put("periodicalId", periodicalId);
		return "reader/add_page";
	}
	
}
