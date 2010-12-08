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

import cn.infogiga.sd.dto.JsonListBean;
import cn.infogiga.sd.dto.JsonPhonearray;

import cn.infogiga.sd.pojo.Phonearray;
import cn.infogiga.sd.service.ManageService;

@Controller
public class PhonearrayController {

	@Autowired
	ManageService manageService;
	
	@RequestMapping(value = "/phonearray",params="comboPhonearray")
	public String comboPhonearrayJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonPhonearray> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Phonearray.class), JsonPhonearray.class);
		model.addAttribute("array", powerList);
		return "list";
	}
	
	@RequestMapping(value = "/phonearray")
	public String phonearrayJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonPhonearray> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Phonearray.class), JsonPhonearray.class);
		int totalCount = manageService.getManageDAO().getCount(Phonearray.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list,true,null);
		model.addAttribute("object", jsonListBean);
		return "list";

	}
	
	@RequestMapping(value = "/phonearray",params="add")
	public String addPhonearray(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("phonearrayName")String phonearrayName){
		Phonearray phonearray = new Phonearray();
		phonearray.setPhonearrayName(phonearrayName);

		try {
			manageService.getManageDAO().save(phonearray);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}
	
	@RequestMapping(value = "/phonearray",params="delete")
	public String deletePhonebrand(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("phonearrayId")Integer phonearrayId){
		Phonearray phonearray = new Phonearray();
		phonearray.setId(phonearrayId);
		try {
			manageService.getManageDAO().delete(phonearray);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，删除失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/phonearray",params="update")
	public String updatePhonebrand(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("phonearrayId")Integer phonearrayId,
			@RequestParam("phonearrayName")String phonearrayName){
		Phonearray phonearray = new Phonearray();
		phonearray.setId(phonearrayId);
		
		phonearray.setPhonearrayName(phonearrayName);

		try {
			manageService.getManageDAO().update(phonearray);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
		}
		return "list";
	}
}
