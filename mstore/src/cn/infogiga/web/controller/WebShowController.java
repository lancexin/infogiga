package cn.infogiga.web.controller;

import java.util.Date;
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
import cindy.util.Code;
import cindy.util.ProperiesReader;
import cn.infogiga.exp.service.CmbService;
import cn.infogiga.exp.service.ExperienceService;
import cn.infogiga.pojo.Attachment;
import cn.infogiga.pojo.Soft;
import cn.infogiga.pojo.Softmenu;
import cn.infogiga.pojo.Tempdownloadstat;
import cn.infogiga.pojo.Users;
import cn.infogiga.sd.dto.JsonListBean;
import cn.infogiga.sd.dto.JsonSoft;
import cn.infogiga.sd.service.ManageService;

@Controller
public class WebShowController {
	@Autowired
	ManageService manageService;
	
	@Autowired
	CmbService cmbService;
	
	@Autowired
	ExperienceService experienceService;
	
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
		
			Users users = ((Users) session.getAttribute("user"));
			
			if(users == null){
				model.put("success", false);
				model.put("msg", "请重新登录！");
				return "list";
			}
		
		try {
			Attachment attachment = manageService.getManageDAO().getAttachment(phonetypeId, softId);
			if(attachment == null){
				model.put("success", false);
				model.put("msg", "您所下的软件已经被移除或者不存在！");
			}else{
				//记录临时统计信息
				
				String sendUrl = null;
				Soft soft = attachment.getSoft();
				String sendStr = soft.getSoftName()+"的下载地址是：";
				if(soft.getSoftCode() != null && soft.getSoftCode().trim().length() > 0){
					sendUrl = ProperiesReader.getInstence("config.properties")
						.getStringValue("mstore.rd.download.post")+"?aid="+soft.getSoftCode()+"&e="+users.getUserName();
				}else{
					String code = Code.getCode();
					sendUrl = ProperiesReader.getInstence("config.properties")
						.getStringValue("msoft.download.post")+attachment.getName()+"?id="+code;
					
					//像短信表里添加一条
					Tempdownloadstat temp = new Tempdownloadstat();
					temp.setCode(code);
					temp.setPhoneNumber(phoneNumber);
					temp.setPhonetypeId(phonetypeId);
					temp.setSoftId(softId);
					temp.setDownloadtypeId(4);//4表示wappush
					temp.setAddTime(new Date());
					temp.setUserId(users.getId());
					manageService.getManageDAO().save(temp);
				}
				//发送wappush
				cmbService.sendWapPush(phoneNumber, sendUrl, sendStr);
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
	
	@RequestMapping(value = "/web",params="layout")
	public String layout(HttpSession session, ModelMap model){
		session.removeAttribute("user");
		model.put("success", true);
		model.put("msg", "退出成功");
		return "list";
	}
	
	public static void main(String[] args) {
		System.out.println(Code.getCode());
	}
}
