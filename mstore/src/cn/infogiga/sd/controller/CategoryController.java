package cn.infogiga.sd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cindy.page.beanutils.MyBeanUtils;
import cn.infogiga.pojo.Phonebrand;
import cn.infogiga.pojo.Phonebrandcategory;
import cn.infogiga.sd.dto.JsonListBean;
import cn.infogiga.sd.dto.JsonPhonebrandcategory;
import cn.infogiga.sd.service.ManageService;
import cn.infogiga.sd.service.MsoftService;

@Controller
public class CategoryController {
	@Autowired
	ManageService manageService;
	

	@Autowired
	MsoftService msoftService;
	
	@RequestMapping(value = "/category",params="comboCategory")
	public String comboCityJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("phonebrandId")Integer phonebrandId){
		List<JsonPhonebrandcategory> powerList;
		if(phonebrandId == -1){
			powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Phonebrandcategory.class), JsonPhonebrandcategory.class);
		}else{
			powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findByProperty(Phonebrandcategory.class, "phonebrand.id", phonebrandId), JsonPhonebrandcategory.class);
		}
		model.addAttribute("array", powerList);
		return "list";

	}

	@RequestMapping(value = "/category")
	public String cityJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("phonebrandId")Integer phonebrandId){
		List<JsonPhonebrandcategory> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findByProperty(Phonebrandcategory.class, "phonebrand.id", phonebrandId), JsonPhonebrandcategory.class);
		int totalCount = manageService.getManageDAO().getCountByProperty(Phonebrandcategory.class, "phonebrand.id", phonebrandId);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list,true,null);
		model.addAttribute("object", jsonListBean);
		return "list";

	}

	@RequestMapping(value = "/category",params="add")
	public String addCity(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("categoryName")String categoryName,
			@RequestParam("phonebrandId")Integer phonebrandId){
		Phonebrandcategory category = new Phonebrandcategory();
		category.setCategoryName(categoryName);
		Phonebrand phonebrand = manageService.getManageDAO().findById(Phonebrand.class, phonebrandId);
		
		category.setPhonebrand(phonebrand);
		try {
			manageService.getManageDAO().save(category);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
			return "list";
		}
		msoftService.addCategory(category, request);
		return "list";
	}

	@RequestMapping(value = "/category",params="delete")
	public String deleteCity(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("categoryId")Integer categoryId){
		Phonebrandcategory category = manageService.getManageDAO().findById(Phonebrandcategory.class, categoryId);
		try {
			manageService.getManageDAO().delete(category);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，删除失败~");
		}
		msoftService.deleteCategory(category, request);
		return "list";
	}

	@RequestMapping(value = "/category",params="update")
	public String updateCity(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("categoryId")Integer categoryId,
			@RequestParam("categoryName")String categoryName,
			@RequestParam("phonebrandId")Integer phonebrandId){
		Phonebrandcategory category = manageService.getManageDAO().findById(Phonebrandcategory.class, categoryId);
		msoftService.deleteCategory(category, request);
		category.setCategoryName(categoryName);
		Phonebrand phonebrand = new Phonebrand();
		phonebrand.setId(phonebrandId);
		category.setPhonebrand(phonebrand);
		try {
			manageService.getManageDAO().update(category);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
			return "list";
		}
		msoftService.addCategory(category, request);
		return "list";
	}
}
