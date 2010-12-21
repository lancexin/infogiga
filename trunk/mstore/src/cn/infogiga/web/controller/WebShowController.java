package cn.infogiga.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cindy.page.beanutils.MyBeanUtils;
import cn.infogiga.pojo.Attachment;
import cn.infogiga.pojo.Soft;
import cn.infogiga.pojo.Softmenu;
import cn.infogiga.sd.dto.JsonListBean;
import cn.infogiga.sd.dto.JsonSoft;
import cn.infogiga.sd.dto.JsonSoftAttachment;
import cn.infogiga.sd.service.ManageService;

@Controller
public class WebShowController {
	@Autowired
	ManageService manageService;
	
	@RequestMapping(value = "/webLogin")
	public String webLigin(){
		
		return "web/login";
	}
	
	@RequestMapping(value = "/web")
	public String webIndex(ModelMap model){
		List<Softmenu> sList = manageService.getManageDAO().findAll(Softmenu.class);
		model.addAttribute("menus", sList);
		return "web/index";
	}
	
	@RequestMapping(value = "/web",params="list")
	public String item(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start,
			@RequestParam("limit")Integer limit,
			@RequestParam("menuId")Integer menuId,
			@RequestParam("phonetypeId")Integer phonetypeId){

		List<JsonSoft> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().searchSoft(menuId, phonetypeId, start, limit), JsonSoft.class);
		long totalCount = manageService.getManageDAO().searchSoftCount(menuId, phonetypeId);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list,true,null);
		model.addAttribute("object", jsonListBean);
		return "list";
	}
	
	@RequestMapping(value = "/web",params="push")
	public String wapPush(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("phonetypeId")Integer phonetypeId,
			@RequestParam("softId")Integer softId,
			@RequestParam("phoneNumber")String phoneNumber){
		
		try {
			Attachment attachment = manageService.getManageDAO().getAttachment(phonetypeId, softId);
			if(attachment == null){
				model.put("success", false);
				model.put("msg", "您所下的软件已经被移除或者不存在！");
			}else{
				//发送wappush
				model.put("success", true);
				model.put("msg", "软件下载地址已经发送至您的手机,请及时查收！");
			}
		} catch (Exception e) {
			model.put("success", false);
			model.put("msg", "您所下的软件已经被移除或者不存在！");
			e.printStackTrace();
		}
		return "list";
	}
}
