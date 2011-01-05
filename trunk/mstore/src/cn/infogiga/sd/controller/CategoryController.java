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
	

	/*@Autowired
	MsoftService msoftService;*/
	
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
			@RequestParam("phonebrandId")Integer phonebrandId,
			@RequestParam("pic")String pic){
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
		
		Phonebrandcategory category = new Phonebrandcategory();
		category.setCategoryName(categoryName);
		category.setPic(picUrl);
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
		//msoftService.addCategory(category, request);
		return "list";
	}

	@RequestMapping(value = "/category",params="delete")
	public String deleteCity(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("categoryId")Integer categoryId){
		Phonebrandcategory category = manageService.getManageDAO().findById(Phonebrandcategory.class, categoryId);
		try {
			FileUtil.delete(new File(request.getRealPath(category.getPic())));
			manageService.getManageDAO().delete(category);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，删除失败~");
		}
		//msoftService.deleteCategory(category, request);
		return "list";
	}

	@RequestMapping(value = "/category",params="update")
	public String updateCity(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("categoryId")Integer categoryId,
			@RequestParam("categoryName")String categoryName,
			@RequestParam("phonebrandId")Integer phonebrandId,
			@RequestParam("pic")String pic){
		
		File of = new File(request.getRealPath(pic));
		if(!of.exists()){
			model.put("success", false);
			model.put("msg", "未能找到文件");
			return "list";
		}
		String picUrl = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+of.getName();
		String insertUrl = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+Code.getCode()+"."+ImageUtil.validateFile(of);
		
		
		
		
		Phonebrandcategory category = manageService.getManageDAO().findById(Phonebrandcategory.class, categoryId);
		
		if(category.getPic() == null || !category.getPic().equals(picUrl)){//如果图片不一样或没有
			//将新图片复制到素材区
			boolean bl = FileUtil.copyFile(of, new File(request.getRealPath(insertUrl)),false);
			if(!bl){
				model.put("success", false);
				model.put("msg", "文件复制出现错误");
				return "list";
			}
			//删除原素材图片
			if(category.getPic() != null){
				FileUtil.delete(new File(request.getRealPath(category.getPic())));
			}
			
			category.setPic(insertUrl);
		}
		
		//msoftService.deleteCategory(category, request);
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
		//msoftService.addCategory(category, request);
		return "list";
	}
}
