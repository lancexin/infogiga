package cn.infogiga.szqb.server.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cindy.page.beanutils.MyBeanUtils;
import cindy.page.hibernate.CirteriaBean;
import cindy.page.hibernate.CirteriaQuery;
import cindy.page.hibernate.PageBean;
import cn.infogiga.szqb.pojo.Page;
import cn.infogiga.szqb.pojo.Periodical;
import cn.infogiga.szqb.pojo.Reader;
import cn.infogiga.szqb.pojo.Readtype;
import cn.infogiga.szqb.server.xml.ErrorMgr;
import cn.infogiga.szqb.server.xml.FromMsg;
import cn.infogiga.szqb.server.xml.ToMsg;
import cn.infogiga.szqb.server.xml.XmlError;
import cn.infogiga.szqb.vo.JsonListBean;
import cn.infogiga.szqb.vo.JsonPage;
import cn.infogiga.szqb.vo.JsonPeriodical;
import cn.infogiga.szqb.vo.JsonReader;
import cn.infogiga.szqb.vo.JsonReadtype;
import cn.infogiga.szqb.web.service.ManageService;

@Controller
public class ServerController {
	
	@Autowired
	ManageService manageService;
	
	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}
	
	@RequestMapping(value = "/m")
	public void m(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model) throws IOException{
		FromMsg fromMsg = (FromMsg) request.getAttribute("fromMsg");
		switch (fromMsg.getType()) { 
			case 1: //获得读物类别
				getReadtype(request,response,fromMsg);
				break;
			case 2: //根据根据读物类别id获得读物
				getReader(request,response,fromMsg);
				break;
			case 3: //根据读物id获得期刊列表
				getPeriodical(request, response, fromMsg);
				break;	
			case 4: //期刊下载图片信息
				getPage(request, response, fromMsg);
				break;	
			default: //未解析选项
				readError(request, response, fromMsg);
				break;
		}
	}
	
	public void readError(HttpServletRequest request,HttpServletResponse response,FromMsg fromMsg){
		ToMsg toMsg = new ToMsg();
		XmlError error = ErrorMgr.getNoTypeError();
		toMsg.setError(error);
		request.setAttribute("toMsg", toMsg);
	}
	
	public void getReadtype(HttpServletRequest request,HttpServletResponse response,FromMsg fromMsg){
		ToMsg toMsg = new ToMsg();
		List<JsonReadtype> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Readtype.class,null), JsonReadtype.class);
		toMsg.setReadtypes(list);
		request.setAttribute("toMsg", toMsg);
	}
	
	public void getReader(HttpServletRequest request,HttpServletResponse response,FromMsg fromMsg){
		ToMsg toMsg = new ToMsg();
		List<JsonReader> list;
		if(fromMsg.getReadtypeID() == 0){ //获取全部
			list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Reader.class,null), JsonReader.class);
		}else{
			list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findByProperty(Reader.class, "readtype.id",fromMsg.getReadtypeID(),null), JsonReader.class);
		}
		toMsg.setReaders(list);
		request.setAttribute("toMsg", toMsg);
	}
	
	public void getPeriodical(HttpServletRequest request,HttpServletResponse response,FromMsg fromMsg){
		
		
		
		PageBean pageBean = new PageBean(fromMsg.getStart(),fromMsg.getLimit());
		CirteriaBean cBean = new CirteriaBean("publishTime");
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"r.id",fromMsg.getReaderID()==0?-1:fromMsg.getReaderID(),new String[]{"reader","r"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"status",3,null));
		List<JsonPeriodical> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByPage(Periodical.class, cBean), JsonPeriodical.class);
		ToMsg toMsg = new ToMsg();
		toMsg.setPeriodicals(list);
		request.setAttribute("toMsg", toMsg);
	}
	/**
	 * 获得页面信息
	 * @param request
	 * @param response
	 * @param fromMsg
	 */
	public void getPage(HttpServletRequest request,HttpServletResponse response,FromMsg fromMsg){
		ToMsg toMsg = new ToMsg();
		List<JsonPage> list;
		if(fromMsg.getPeriodicalID() == 0){
			list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Page.class,"addTime"), JsonPage.class);
		}else{
			list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findByProperty(Page.class, "periodical.id", fromMsg.getPeriodicalID(),"addTime"), JsonPage.class);
		}
		
		toMsg.setPages(list);
		request.setAttribute("toMsg", toMsg);
	}
	
}
