package cn.infogiga.szqb.web.controller;

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
import cn.infogiga.szqb.pojo.Reader;
import cn.infogiga.szqb.pojo.Readtype;
import cn.infogiga.szqb.vo.JsonListBean;
import cn.infogiga.szqb.vo.JsonReadtype;
import cn.infogiga.szqb.web.service.ManageService;



@Controller
public class ReadtypeController {

	@Autowired
	ManageService manageService;

	@RequestMapping(value = "/readtype")
	public String jsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonReadtype> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Readtype.class,null), JsonReadtype.class);
		int totalCount = manageService.getManageDAO().getCount(Readtype.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list,true,null);
		model.addAttribute("object", jsonListBean);
		return "list";

	}
	
	@RequestMapping(value = "/readtype",params="comboReadtype")
	public String comboJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonReadtype> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Readtype.class,null), JsonReadtype.class);
		model.addAttribute("array", powerList);
		return "list";

	}

	@RequestMapping(value = "/readtype",params="add")
	public String add(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("typeShortName")String shortName,@RequestParam("typeName")String typeName){
		
		//判断名称缩写是否有重复
		int count = manageService.getManageDAO().getCountByProperty(Readtype.class, "shortName", shortName);
		if(count > 0){
			model.put("success", false);
			model.put("msg", "缩写已经存在！");
			return "list";
		}
		
		Readtype readtype = new Readtype();
		readtype.setShortName(shortName);
		readtype.setTypeName(typeName);
		try {
			manageService.getManageDAO().save(readtype);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "添加失败,请联系管理员！");
		}
		return "list";
	}

	@RequestMapping(value = "/readtype",params="delete")
	public String delete(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("readtypeId")Integer readtypeId){
		int cityCount = manageService.getManageDAO().getCountByProperty(Reader.class, "readtype.id", readtypeId);
		if(cityCount > 0){
			model.put("success", false);
			model.put("msg", "该项已经有归属！");
			return "list";
		}
		Readtype readtype = new Readtype();
		readtype.setId(readtypeId);
		try {
			manageService.getManageDAO().delete(readtype);
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

	@RequestMapping(value = "/readtype",params="update")
	public String update(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("readtypeId")Integer readtypeId,
			@RequestParam("shortName")String shortName,
			@RequestParam("typeShortName")String typeName){
		//判断是否有重复
		int count = manageService.getManageDAO().getCountByProperty(Readtype.class, "shortName", shortName);
		Readtype readtype = manageService.getManageDAO().findById(Readtype.class, readtypeId);
		if(count > 0 && !readtype.getShortName().equals(shortName)){
			model.put("success", false);
			model.put("msg", "缩写已经存在！");
			return "list";
		}
	
		readtype.setShortName(shortName);
		readtype.setTypeName(typeName);
		try {
			manageService.getManageDAO().update(readtype);
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
