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
import cn.infogiga.sd.dto.JsonSoftMenu;
import cn.infogiga.sd.pojo.Softmenu;
import cn.infogiga.sd.service.ManageService;

@Controller
public class SoftmenuController {
	@Autowired
	ManageService manageService;
	
	@RequestMapping(value = "/softmenu",params="comboSoftmenu")
	public String comboSoftmenuJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonSoftMenu> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Softmenu.class), JsonSoftMenu.class);
		model.addAttribute("array", powerList);
		return "list";

	}
	
	@RequestMapping(value = "/softmenu")
	public String softMenuJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonSoftMenu> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Softmenu.class), JsonSoftMenu.class);
		int totalCount = manageService.getManageDAO().getCount(Softmenu.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list,true,null);
		model.addAttribute("object", jsonListBean);
		return "list";
	}
	
	@RequestMapping(value = "/softmenu",params="delete")
	public String deleteSoftmenu(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("softmenuId")Integer softmenuId){
		Softmenu softmenu = new Softmenu();
		softmenu.setId(softmenuId);
		try {
			manageService.getManageDAO().delete(softmenu);
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

	@RequestMapping(value = "/softmenu",params="update")
	public String updateSoftmenu(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("softmenuId")Integer softmenuId,
			@RequestParam("softmenuName")String softmenuName){
		Softmenu softmenu = new Softmenu();
		softmenu.setId(softmenuId);
		softmenu.setMenuName(softmenuName);
		try {
			manageService.getManageDAO().update(softmenu);
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

	@RequestMapping(value = "/softmenu",params="add")
	public String addSoftmenu(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("softmenuName")String softmenuName){
		Softmenu softmenu = new Softmenu();
		softmenu.setMenuName(softmenuName);
		try {
			manageService.getManageDAO().save(softmenu);
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
}
