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
import cn.infogiga.pojo.Logtype;
import cn.infogiga.pojo.Phoneplatform;
import cn.infogiga.pojo.Users;
import cn.infogiga.sd.dto.JsonListBean;
import cn.infogiga.sd.dto.JsonPhoneplatform;
import cn.infogiga.sd.service.ManageService;

@Controller
public class PhoneplatformController {
	@Autowired
	ManageService manageService;
	

	@RequestMapping(value = "/platform",params="comboPlatform")
	public String comboPlatformJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonPhoneplatform> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Phoneplatform.class), JsonPhoneplatform.class);
		model.addAttribute("array", powerList);
		return "list";
	}
	

	@RequestMapping(value = "/platform")
	public String phoneplatformJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonPhoneplatform> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Phoneplatform.class), JsonPhoneplatform.class);
		int totalCount = manageService.getManageDAO().getCount(Phoneplatform.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list,true,null);
		model.addAttribute("object", jsonListBean);
		return "list";
	}
	

	@RequestMapping(value = "/platform",params="add")
	public String addPhoneplatform(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("platformName")String platformName){
		Phoneplatform phoneplatform = new Phoneplatform();
		phoneplatform.setPlatformName(platformName);
		try {
			manageService.getManageDAO().save(phoneplatform);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
			return "list";
		}
		manageService.log(Logtype.PLATFORM, ((Users)session.getAttribute("user")).getNickName(),"添加手机平台信息,平台名称为："+platformName);
		return "list";
	}

	@RequestMapping(value = "/platform",params="delete")
	public String deletePhoneplatform(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("platformId")Integer platformId){
		Phoneplatform phoneplatform = manageService.getManageDAO().findById(Phoneplatform.class, platformId);
		phoneplatform.setId(platformId);
		try {
			manageService.getManageDAO().delete(phoneplatform);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
			return "list";
		}
		manageService.log(Logtype.PLATFORM, ((Users)session.getAttribute("user")).getNickName(),"删除手机平台信息,平台名称为："+phoneplatform.getPlatformName());
		return "list";
	}

	@RequestMapping(value = "/platform",params="update")
	public String updatePhoneplatform(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("platformId")Integer platformId,
			@RequestParam("platformName")String platformName){
		Phoneplatform phoneplatform = new Phoneplatform();
		phoneplatform.setId(platformId);
		phoneplatform.setPlatformName(platformName);
		try {
			manageService.getManageDAO().update(phoneplatform);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
			return "list";
		}
		manageService.log(Logtype.PLATFORM, ((Users)session.getAttribute("user")).getNickName(),"修改手机平台信息,平台名称为："+phoneplatform.getPlatformName());
		return "list";
	}
}
