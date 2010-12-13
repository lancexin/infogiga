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
import cindy.page.power.Node;
import cn.infogiga.pojo.City;
import cn.infogiga.pojo.Power;
import cn.infogiga.pojo.Province;
import cn.infogiga.sd.dto.JsonListBean;
import cn.infogiga.sd.dto.JsonPower;
import cn.infogiga.sd.dto.JsonProvince;
import cn.infogiga.sd.service.ManageService;
import cn.infogiga.sd.service.PowerService;

@Controller
public class PowerController {
	@Autowired
	ManageService manageService;
	@Autowired
	PowerService powerService;
	
	@RequestMapping(value = "/power")
	public String powerJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model
			,@RequestParam("start")Integer start,@RequestParam("limit")Integer limit){
		List<JsonPower> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByPage(Power.class, start, limit), JsonPower.class);
		int totalCount = manageService.getManageDAO().getCount(Power.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,powerList,true,null);
		model.addAttribute("object", jsonListBean);
		return "list";

	}
	
	@RequestMapping(value = "/power",params="allCombo")
	public String comboAllPowerJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<Node> nodeList = powerService.getBaseNode(request.getRealPath("WEB-INF/power/power-config.xml"));
		model.addAttribute("array", nodeList);
		return "list";

	}
	
	@RequestMapping(value = "/power",params="comboPower")
	public String comboPowerJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonPower> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Power.class), JsonPower.class);
		model.addAttribute("array", powerList);
		return "list";

	}
	
	@RequestMapping(value = "/power",params="comboLowPower")
	public String comboLowPowerJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonPower> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findByProperty(Power.class, "status", 1), JsonPower.class);
		model.addAttribute("array", powerList);
		return "list";

	}
	
	@RequestMapping(value = "/power",params="add")
	public String addPower(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("powerName")String powerName,@RequestParam("powerValue")String powerValue,@RequestParam("status")Integer status){
		Power power = new Power();
		power.setPowerName(powerName);
		power.setPowerValue(powerValue);
		power.setStatus(status);
		try {
			manageService.getManageDAO().save(power);
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
	
	@RequestMapping(value = "/power",params="delete")
	public String deletePower(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("powerId")Integer powerId){
		Power power = new Power();
		power.setId(powerId);
		try {
			manageService.getManageDAO().delete(power);
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

	@RequestMapping(value = "/power",params="update")
	public String updatePower(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("powerId")Integer powerId,
			@RequestParam("powerValue")String powerValue,
			@RequestParam("powerName")String powerName,
			@RequestParam("status")Integer status){
		Power power = new Power();
		power.setId(powerId);
		power.setPowerName(powerName);
		power.setPowerValue(powerValue);
		power.setStatus(status);
		try {
			manageService.getManageDAO().update(power);
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
