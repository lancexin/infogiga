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
import cn.infogiga.sd.dto.JsonCity;
import cn.infogiga.sd.dto.JsonListBean;
import cn.infogiga.sd.service.ManageService;

@Controller
public class CityController {

	@Autowired
	ManageService manageService;
	
	@RequestMapping(value = "/city",params="comboCity")
	public String comboCityJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("provinceId")Integer provinceId){
		List<JsonCity> powerList;
		if(provinceId == -1){
			powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(City.class), JsonCity.class);
		}else{
			powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findByProperty(City.class, "province.id", provinceId), JsonCity.class);
		}
		model.addAttribute("array", powerList);
		return "list";

	}

	@RequestMapping(value = "/city")
	public String cityJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("provinceId")Integer provinceId){
		List<JsonCity> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findByProperty(City.class, "province.id", provinceId), JsonCity.class);
		int totalCount = manageService.getManageDAO().getCountByProperty(City.class, "province.id", provinceId);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list,true,null);
		model.addAttribute("object", jsonListBean);
		return "list";

	}

	@RequestMapping(value = "/city",params="add")
	public String addCity(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("cityName")String cityName,
			@RequestParam("provinceId")Integer provinceId){
		City city = new City();
		city.setCityName(cityName);
		Province province = new Province();
		province.setId(provinceId);
		city.setProvince(province);
		try {
			manageService.getManageDAO().save(city);
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

	@RequestMapping(value = "/city",params="delete")
	public String deleteCity(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("cityId")Integer cityId){
		City city = new City();
		city.setId(cityId);
		try {
			manageService.getManageDAO().delete(city);
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

	@RequestMapping(value = "/city",params="update")
	public String updateCity(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("cityId")Integer cityId,
			@RequestParam("provinceId")Integer provinceId,
			@RequestParam("cityName")String cityName){
		City city = new City();
		city.setId(cityId);
		city.setCityName(cityName);
		Province province = new Province();
		province.setId(provinceId);
		city.setProvince(province);
		try {
			manageService.getManageDAO().update(city);
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
