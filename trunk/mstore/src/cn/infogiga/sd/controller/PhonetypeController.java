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
import cn.infogiga.pojo.Phonearray;
import cn.infogiga.pojo.Phonebrand;
import cn.infogiga.pojo.Phonebrandcategory;
import cn.infogiga.pojo.Phoneplatform;
import cn.infogiga.pojo.Phonetype;
import cn.infogiga.pojo.Soft;
import cn.infogiga.pojo.Users;
import cn.infogiga.sd.dto.JsonListBean;
import cn.infogiga.sd.dto.JsonPhonetype;
import cn.infogiga.sd.service.ManageService;
import cn.infogiga.sd.service.MsoftService;

@Controller
public class PhonetypeController {
	@Autowired
	ManageService manageService;
	
	@Autowired
	MsoftService msoftService;
	
	@RequestMapping(value = "/phonetype")
	public String phonetypeJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start,@RequestParam("limit")Integer limit){
		List<JsonPhonetype> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByPage(Phonetype.class, start, limit), JsonPhonetype.class);
		int totalCount = manageService.getManageDAO().getCount(Phonetype.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list,true,null);
		model.addAttribute("object", jsonListBean);
		return "list";

	}
	

	@RequestMapping(value = "/phonetype",params="byPhonebrand")
	public String phonetypeJsonList2(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start,@RequestParam("limit")Integer limit,@RequestParam("categoryId")Integer categoryId){
		List<JsonPhonetype> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByProperty(Phonetype.class, "phonebrandcategory.id", categoryId, start, limit), JsonPhonetype.class);
		Integer totalCount = manageService.getManageDAO().getCountByProperty(Phonetype.class, "phonebrandcategory.id", categoryId);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list,true,null);
		model.addAttribute("object", jsonListBean);
		return "list";

	}
	
	
	
	@RequestMapping(value = "/phonetype",params="comboPhonetype")
	public String comboPhonetypeJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonPhonetype> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Phonetype.class), JsonPhonetype.class);
		model.addAttribute("array", powerList);
		return "list";
	}

	@RequestMapping(value = "/phonetype",params="add")
	public String addPhonetype(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("pic")String pic,
			@RequestParam("platformId")Integer platformId,
			@RequestParam("phonearrayId")Integer phonearrayId,
			@RequestParam("categoryId")Integer categoryId,
			@RequestParam("phonetypeName")String phonetypeName,
			@RequestParam("status")Integer status){
		if(pic == null || pic.length() <= 0){
			model.put("success", false);
			model.put("msg", "必须选择图片");
			return "list";
		}
		File of = new File(request.getRealPath(pic));
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
		Phonetype phonetype = new Phonetype();
		
		Phonebrandcategory category = manageService.getManageDAO().findById(Phonebrandcategory.class, categoryId);
		//category.setId(categoryId);
		phonetype.setPhonebrandcategory(category);
		
		Phonearray phonearray = manageService.getManageDAO().findById(Phonearray.class, phonearrayId);
		//phonearray.setId(phonearrayId);
		phonetype.setPhonearray(phonearray);
		
		phonetype.setPhonetypeName(phonetypeName);
		Phoneplatform phoneplatform = new Phoneplatform();
		phoneplatform.setId(platformId);
		phonetype.setPhoneplatform(phoneplatform);
		phonetype.setPic(picUrl);
		phonetype.setStatus(status);
		try {
			manageService.getManageDAO().save(phonetype);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
			return "list";
		}
		manageService.log(Logtype.PHONETYPE, ((Users)session.getAttribute("user")).getNickName(),"添加手机型号信息,型号名称为："+phonetypeName);
		msoftService.addPhonetype(phonetype, request);
		return "list";
	}
	

	@RequestMapping(value = "/phonetype",params="delete")
	public String deletePhonetype(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("phonetypeId")Integer phonetypeId){

		 Phonetype phonetype = manageService.getManageDAO().findById(Phonetype.class, phonetypeId);
		//同时删除素材区的图片
		 FileUtil.delete(new File(request.getRealPath(phonetype.getPic())));
		try {
			manageService.getManageDAO().delete(phonetype);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
			return "list";
		}
		manageService.log(Logtype.PHONETYPE, ((Users)session.getAttribute("user")).getNickName(),"删除手机型号信息,型号名称为："+phonetype.getPhonetypeName());
		msoftService.deletePhoentype(phonetype, request);
		return "list";
	}
	

	@RequestMapping(value = "/phonetype",params="update")
	public String updatePhonetype(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("phonetypeId")Integer phonetypeId,
			@RequestParam("platformId")Integer platformId,
			@RequestParam("phonearrayId")Integer phonearrayId,
			@RequestParam("pic")String pic,
			@RequestParam("categoryId")Integer categoryId,
			@RequestParam("phonetypeName")String phonetypeName,
			@RequestParam("status")Integer status){
		
		File of = new File(request.getRealPath(pic));
		if(!of.exists()){
			model.put("success", false);
			model.put("msg", "未能找到文件");
			return "list";
		}
		String picUrl = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+of.getName();
		String insertUrl = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+Code.getCode()+"."+ImageUtil.validateFile(of);
		Phonetype phonetype = manageService.getManageDAO().findById(Phonetype.class, phonetypeId);
		msoftService.deletePhoentype(phonetype, request);
		if(phonetype.getPic() == null || !phonetype.getPic().equals(picUrl)){//如果图片不一样
			//将新图片复制到素材区
			boolean bl = FileUtil.copyFile(of, new File(request.getRealPath(insertUrl)),false);
			if(!bl){
				model.put("success", false);
				model.put("msg", "文件复制出现错误");
				return "list";
			}
			//删除原素材图片
			FileUtil.delete(new File(request.getRealPath(phonetype.getPic()+"")));
			phonetype.setPic(insertUrl);
		}
		Phonebrandcategory category = manageService.getManageDAO().findById(Phonebrandcategory.class, categoryId);
		//category.setId(categoryId);
		phonetype.setPhonebrandcategory(category);
		
		Phonearray phonearray = manageService.getManageDAO().findById(Phonearray.class, phonearrayId);
		//phonearray.setId(phonearrayId);
		phonetype.setPhonearray(phonearray);
		
		phonetype.setPhonetypeName(phonetypeName);
		Phoneplatform phoneplatform = new Phoneplatform();
		phoneplatform.setId(platformId);
		phonetype.setPhoneplatform(phoneplatform);
		phonetype.setStatus(status);
		try {
			manageService.getManageDAO().save(phonetype);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
			return "list";
		}
		manageService.log(Logtype.PHONETYPE, ((Users)session.getAttribute("user")).getNickName(),"修改手机型号信息,型号名称为："+phonetype.getPhonetypeName());
		msoftService.addPhonetype(phonetype, request);
		return "list";
	}
	
}
