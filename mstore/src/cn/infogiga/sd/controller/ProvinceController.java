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
import cn.infogiga.pojo.City;
import cn.infogiga.pojo.Province;
import cn.infogiga.sd.dto.JsonListBean;
import cn.infogiga.sd.dto.JsonProvince;
import cn.infogiga.sd.service.ManageService;

@Controller
public class ProvinceController {
	@Autowired
	ManageService manageService;
	
	@RequestMapping(value = "/province")
	public String proviceJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonProvince> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Province.class), JsonProvince.class);
		int totalCount = manageService.getManageDAO().getCount(Province.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list,true,null);
		model.addAttribute("object", jsonListBean);
		return "list";

	}
	
	@RequestMapping(value = "/province",params="comboProvince")
	public String comboProvinceJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonProvince> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Province.class), JsonProvince.class);
		model.addAttribute("array", powerList);
		return "list";

	}

	@RequestMapping(value = "/province",params="add")
	public String addProvince(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("provinceName")String provinceName){
		Province province = new Province();
		province.setProvinceName(provinceName);
		try {
			manageService.getManageDAO().save(province);
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

	@RequestMapping(value = "/province",params="delete")
	public String deleteProvince(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("provinceId")Integer provinceId){
		int cityCount = manageService.getManageDAO().getCountByProperty(City.class, "province.id", provinceId);
		if(cityCount > 0){
			model.put("success", false);
			model.put("msg", "该项已经有归属！");
			return "list";
		}
		Province prvince = new Province();
		prvince.setId(provinceId);
		try {
			manageService.getManageDAO().delete(prvince);
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

	@RequestMapping(value = "/province",params="update")
	public String updateProvince(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("provinceId")Integer provinceId,
			@RequestParam("provinceName")String provinceName){
		Province province = new Province();
		province.setId(provinceId);
		province.setProvinceName(provinceName);
		try {
			manageService.getManageDAO().update(province);
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
