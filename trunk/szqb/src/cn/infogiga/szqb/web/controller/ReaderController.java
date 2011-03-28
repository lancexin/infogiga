package cn.infogiga.szqb.web.controller;



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
import cn.infogiga.szqb.pojo.Periodical;
import cn.infogiga.szqb.pojo.Reader;
import cn.infogiga.szqb.pojo.Readtype;
import cn.infogiga.szqb.vo.JsonListBean;
import cn.infogiga.szqb.vo.JsonReader;
import cn.infogiga.szqb.web.service.ManageService;


@Controller
public class ReaderController {

	@Autowired
	ManageService manageService;
	
	@RequestMapping(value = "/reader")
	public String jsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model
			,@RequestParam("readtypeId")Integer readtypeId){
		List<JsonReader> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findByProperty(Reader.class, "readtype.id",readtypeId,null), JsonReader.class);
		int totalCount = manageService.getManageDAO().getCountByProperty(Reader.class, "readtype.id",readtypeId);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list,true,null);
		model.addAttribute("object", jsonListBean);
		return "list";

	}
	
	@RequestMapping(value = "/reader",params="comboReader")
	public String comboJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonReader> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Reader.class,null), JsonReader.class);
		model.addAttribute("array", powerList);
		return "list";

	}

	@RequestMapping(value = "/reader",params="add")
	public String add(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("readtypeId")Integer readtypeId,
			@RequestParam("readerName")String readerName,
			@RequestParam("readerShortName")String shortName){
		int count = manageService.getManageDAO().getCountByProperty(Reader.class, "shortName", shortName);
		if(count > 0){
			model.put("success", false);
			model.put("msg", "缩写已经存在！");
			return "list";
		}
		Reader reader = new Reader();
		reader.setReaderName(readerName);
		reader.setShortName(shortName);
		Readtype readtype = new Readtype();
		readtype.setId(readtypeId);
		reader.setReadtype(readtype);
		try {
			manageService.getManageDAO().save(reader);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "添加失败,请联系管理员！");
		}
		return "list";
	}

	@RequestMapping(value = "/reader",params="delete")
	public String delete(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("readerId")Integer readerId){
		int count = manageService.getManageDAO().getCountByProperty(Periodical.class, "reader.id", readerId);
		if(count > 0){
			model.put("success", false);
			model.put("msg", "该项已经有归属！");
			return "list";
		}
		Reader reader = new Reader();
		reader.setId(readerId);
		try {
			manageService.getManageDAO().delete(reader);
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

	@RequestMapping(value = "/reader",params="update")
	public String update(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("readerId")Integer readerId,
			@RequestParam("readtypeId")Integer readtypeId,
			@RequestParam("readerName")String readerName,
			@RequestParam("readerShortName")String shortName){
		int count = manageService.getManageDAO().getCountByProperty(Periodical.class, "reader.id", readerId);
		Reader reader = manageService.getManageDAO().findById(Reader.class, readerId);
		if(count > 0 && !reader.getShortName().equals(shortName)){
			model.put("success", false);
			model.put("msg", "缩写已经存在！");
			return "list";
		}
		reader.setReaderName(readerName);
		reader.setShortName(shortName);
		Readtype readtype = new Readtype();
		readtype.setId(readtypeId);
		reader.setReadtype(readtype);
		try {
			manageService.getManageDAO().update(reader);
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
