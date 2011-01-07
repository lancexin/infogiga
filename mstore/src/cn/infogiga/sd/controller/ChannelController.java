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
import cn.infogiga.pojo.Channel;
import cn.infogiga.pojo.City;
import cn.infogiga.pojo.Province;
import cn.infogiga.sd.dto.JsonChannel;
import cn.infogiga.sd.dto.JsonListBean;
import cn.infogiga.sd.service.ManageService;

@Controller
public class ChannelController {
	@Autowired
	ManageService manageService;
	
	@RequestMapping(value = "/channel",params="comboChannel")
	public String comboCityJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonChannel> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Channel.class), JsonChannel.class);
		model.addAttribute("array", powerList);
		return "list";
	}
	
	@RequestMapping(value = "/channel")
	public String cityJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonChannel> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Channel.class),JsonChannel.class);
		int totalCount = manageService.getManageDAO().getCount(Channel.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list,true,null);
		model.addAttribute("object", jsonListBean);
		return "list";
	}
	
	@RequestMapping(value = "/channel",params="add")
	public String addCity(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("channelName")String channelName){
		Channel channel = new Channel();
		channel.setChannelName(channelName);
		
		try {
			manageService.getManageDAO().save(channel);
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
	
	@RequestMapping(value = "/channel",params="delete")
	public String deleteCity(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("channelId")Integer channelId){
		Channel channel = new Channel();
		channel.setId(channelId);
		try {
			manageService.getManageDAO().delete(channel);
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

	@RequestMapping(value = "/channel",params="update")
	public String updateCity(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("channelId")Integer channelId,
			@RequestParam("channelName")String channelName){
		Channel channel = manageService.getManageDAO().findById(Channel.class, channelId);
		channel.setChannelName(channelName);
		try {
			manageService.getManageDAO().update(channel);
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
