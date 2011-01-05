package cn.infogiga.sd.controller;

import java.io.File;
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
import cindy.util.FileUtil;
import cindy.util.ImageUtil;
import cindy.util.ProperiesReader;
import cn.infogiga.pojo.Logtype;
import cn.infogiga.pojo.Phonebrand;
import cn.infogiga.pojo.Phonetype;
import cn.infogiga.pojo.Users;
import cn.infogiga.sd.dto.JsonListBean;
import cn.infogiga.sd.dto.JsonPhonebrand;
import cn.infogiga.sd.service.ManageService;
import cn.infogiga.sd.service.MsoftService;

@Controller
public class PhonebrandController {
	@Autowired
	ManageService manageService;
	
	/*@Autowired
	MsoftService msoftService;*/
	

	@RequestMapping(value = "/phonebrand",params="comboPhonebrand")
	public String comboPhonebrandJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonPhonebrand> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Phonebrand.class), JsonPhonebrand.class);
		model.addAttribute("array", powerList);
		return "list";

	}
	
	@RequestMapping(value = "/phonebrand")
	public String phonebrandJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonPhonebrand> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Phonebrand.class), JsonPhonebrand.class);
		int totalCount = manageService.getManageDAO().getCount(Phonebrand.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list,true,null);
		model.addAttribute("object", jsonListBean);
		return "list";

	}
	
	
	@RequestMapping(value = "/phonebrand",params="add")
	public String addPhonebrand(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("phonebrandName")String phonebrandName,
			@RequestParam("url")String url){
		if(url == null || url.length() <= 0){
			model.put("success", false);
			model.put("msg", "必须选择图片");
			return "list";
		}
		File of = new File(request.getRealPath(url));
		String suffix = ImageUtil.validateFile(of);
		if(suffix == null){
			model.put("success", false);
			model.put("msg", "未能找到文件");
			return "list";
		}
		String code = Code.getCode();
		String picUrl = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+code+"."+suffix;
		boolean bl = FileUtil.copyFile(of, new File(request.getRealPath(picUrl)),false);
		if(!bl){
			model.put("success", false);
			model.put("msg", "文件复制出现错误");
			return "list";
		}
		
		Phonebrand phonebrand = new Phonebrand();
		phonebrand.setPhonebrandName(phonebrandName);
		phonebrand.setUrl(picUrl);
		try {
			manageService.getManageDAO().save(phonebrand);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
			return "list";
		}
		manageService.log(Logtype.PHANDBRAND, ((Users)session.getAttribute("user")).getNickName(),"添加手机厂商信息,名称为："+phonebrandName);
		//msoftService.addPhonebrand(phonebrand, request);
		return "list";
	}

	@RequestMapping(value = "/phonebrand",params="delete")
	public String deletePhonebrand(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("phonebrandId")Integer phonebrandId){
		Phonebrand phonebrand =manageService.getManageDAO().findById(Phonebrand.class, phonebrandId);
		
		try {
			FileUtil.delete(new File(request.getRealPath(phonebrand.getUrl())));
			manageService.getManageDAO().delete(phonebrand);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，删除失败~");
			return "list";
		}
		manageService.log(Logtype.PHANDBRAND, ((Users)session.getAttribute("user")).getNickName(),"删除手机厂商信息,名称为："+phonebrand.getPhonebrandName());
		//msoftService.deletePhonebrand(phonebrand, request);
		return "list";
	}

	@RequestMapping(value = "/phonebrand",params="update")
	public String updatePhonebrand(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("phonebrandId")Integer phonebrandId,
			@RequestParam("phonebrandName")String phonebrandName,
			@RequestParam("url")String url){
		File of = new File(request.getRealPath(url));
		if(!of.exists()){
			model.put("success", false);
			model.put("msg", "未能找到文件");
			return "list";
		}
		String picUrl = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+of.getName();
		String insertUrl = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+Code.getCode()+"."+ImageUtil.validateFile(of);
		
		
		Phonebrand phonebrand = manageService.getManageDAO().findById(Phonebrand.class, phonebrandId);
		//msoftService.deletePhonebrand(phonebrand, request);
		if(!phonebrand.getUrl().equals(picUrl)){//如果图片不一样
			//将新图片复制到素材区
			boolean bl = FileUtil.copyFile(of, new File(request.getRealPath(insertUrl)),false);
			if(!bl){
				model.put("success", false);
				model.put("msg", "文件复制出现错误");
				return "list";
			}
			//删除原素材图片
			FileUtil.delete(new File(request.getRealPath(phonebrand.getUrl())));
			
			phonebrand.setUrl(insertUrl);
		}
		
		phonebrand.setPhonebrandName(phonebrandName);

		try {
			manageService.getManageDAO().update(phonebrand);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
			return "list";
		}
		manageService.log(Logtype.PHANDBRAND, ((Users)session.getAttribute("user")).getNickName(),"修改手机厂商信息,名称为："+phonebrand.getPhonebrandName());
		
		//msoftService.addPhonebrand(phonebrand, request);
		return "list";
	}
}
