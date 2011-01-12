package cn.infogiga.sd.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
import cindy.page.hibernate.CirteriaBean;
import cindy.page.hibernate.CirteriaQuery;
import cindy.page.hibernate.PageBean;
import cindy.util.CsvCreatorUtil;
import cindy.util.DateUtil;
import cindy.util.ExcelCreatorUtil;
import cindy.util.ProperiesReader;
import cn.infogiga.pojo.Downloadtype;
import cn.infogiga.pojo.Softdownloadstat;
import cn.infogiga.pojo.Users;
import cn.infogiga.sd.dto.JsonDownloadtype;
import cn.infogiga.sd.dto.JsonListBean;
import cn.infogiga.sd.dto.JsonSoftDownloadStat;
import cn.infogiga.sd.service.ManageService;

@Controller
public class DownloadstatController {
	@Autowired
	ManageService manageService;
	

	@RequestMapping(value = "/downloadstat")
	public String softdownloadstatJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start,@RequestParam("limit")Integer limit){
		
		
		Integer downloadtypeId = (request.getParameter("downloadtypeId")==null || request.getParameter("downloadtypeId").length()==0)?-1:Integer.parseInt(request.getParameter("downloadtypeId"));	
		String employeeName = (request.getParameter("employeeName")==null || request.getParameter("employeeName").length()==0)?null:request.getParameter("employeeName");	
		String employeeNo = (request.getParameter("employeeNo")==null || request.getParameter("employeeNo").length()==0)?null:request.getParameter("employeeNo");	
		String kind = request.getParameter("kind");
		if(kind != null && kind.equals("2")){
			Users users = (Users)session.getAttribute("user");
			if(users != null){
				employeeNo = users.getUserName();
			}else{
				employeeNo = "-1";
			}
		}
		String equipmentName  = (request.getParameter("equipmentName")==null || request.getParameter("equipmentName").length()==0)?null:request.getParameter("equipmentName");	
		String hallName	 = (request.getParameter("hallName")==null || request.getParameter("hallName").length()==0)?null:request.getParameter("hallName");	
		String phonebrandName = (request.getParameter("phonebrandName")==null || request.getParameter("phonebrandName").length()==0)?null:request.getParameter("phonebrandName");	
		String phonetypeName = (request.getParameter("phonetypeName")==null || request.getParameter("phonetypeName").length()==0)?null:request.getParameter("phonetypeName");	
		String categoryName = (request.getParameter("categoryName")==null || request.getParameter("categoryName").length()==0)?null:request.getParameter("categoryName");	
		String softName = (request.getParameter("softName")==null || request.getParameter("softName").length()==0)?null:request.getParameter("softName");	
		Date startTime = (request.getParameter("startTime")==null || request.getParameter("startTime").length()==0)?null:DateUtil.stringToDate(request.getParameter("startTime"), DateUtil.NOW_DATE);	
		Date endTime = (request.getParameter("endTime")==null || request.getParameter("endTime").length()==0)?null:DateUtil.stringToDate(request.getParameter("endTime"), DateUtil.NOW_DATE);	
		
		PageBean pageBean = new PageBean(start,limit);
		CirteriaBean cBean = new CirteriaBean("addTime");
		cBean.setPageBean(pageBean);
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"d.id",downloadtypeId,new String[]{"downloadtype","d"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"emp.nickName",employeeName,new String[]{"users","emp"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"emp.userName",employeeNo,new String[]{"users","emp"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"eq.equipmentName",equipmentName,new String[]{"equipment","eq"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"bh.hallName",hallName,new String[]{"equipment","eq","eq.bissinusshall","bh"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"pt.phonetypeName",phonetypeName,new String[]{"phonetype","pt"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"pb.phonebrandName",phonebrandName,new String[]{"phonetype","pt","pt.phonebrandcategory","pdy","pdy.phonebrand","pb"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"pdy.categoryName",categoryName,new String[]{"phonetype","pt","pt.phonebrandcategory","pdy"}));
		
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"sf.softName",softName,new String[]{"soft","sf"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.BETWEED,CirteriaQuery.IS_OBJECT,"addTime",new Object[]{startTime,endTime},null));
		
		int totalCount = manageService.getManageDAO().getCountByPage(Softdownloadstat.class, cBean);
		
		List<JsonSoftDownloadStat> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByPage(Softdownloadstat.class, cBean), JsonSoftDownloadStat.class);
	
		JsonListBean jsonListBean = new JsonListBean(totalCount,list,true,null);
		model.addAttribute("object", jsonListBean);
		return "list";
	}
	
	@RequestMapping(value = "/downloadstat",params="export")
	public String softdownloadstatExport(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model) throws UnsupportedEncodingException{
		//Date date = ;
		/*response.setContentType("application/vnd.ms-excel");
		response.setHeader("Location", (new Date().getTime())+".xls");
		response.setHeader("Cache-Control", "max-age=" + new Date());
		response.setHeader("Content-Disposition", "attachment; filename="+(new Date().getTime())+".xls");*/
		String fileName = new Date().getTime()+".xls";
		String url = ProperiesReader.getInstence("config.properties").getStringValue("soft.export.url")+fileName;
		File file = new File(url);
		if(file.exists()){
			file.delete();
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Integer downloadtypeId = (request.getParameter("downloadtypeId")==null || request.getParameter("downloadtypeId").length()==0)?-1:Integer.parseInt(request.getParameter("downloadtypeId"));	
		String employeeName = (request.getParameter("employeeName")==null || request.getParameter("employeeName").length()==0)?null:new String(request.getParameter("employeeName").getBytes("iso8859-1"),"utf-8");	
		String employeeNo = (request.getParameter("employeeNo")==null || request.getParameter("employeeNo").length()==0)?null:new String(request.getParameter("employeeNo").getBytes("iso8859-1"),"utf-8");	
		String kind = request.getParameter("kind");
		if(kind != null && kind.equals("2")){
			Users users = (Users)session.getAttribute("user");
			if(users != null){
				employeeNo = users.getUserName();
			}else{
				employeeNo = "-1";
			}
		}
		String equipmentName  = (request.getParameter("equipmentName")==null || request.getParameter("equipmentName").length()==0)?null:new String(request.getParameter("equipmentName").getBytes("iso8859-1"),"utf-8");	
		String hallName	 = (request.getParameter("hallName")==null || request.getParameter("hallName").length()==0)?null:new String(request.getParameter("hallName").getBytes("iso8859-1"),"utf-8");	
		String phonebrandName = (request.getParameter("phonebrandName")==null || request.getParameter("phonebrandName").length()==0)?null:new String(request.getParameter("phonebrandName").getBytes("iso8859-1"),"utf-8");	
		String phonetypeName = (request.getParameter("phonetypeName")==null || request.getParameter("phonetypeName").length()==0)?null:new String(request.getParameter("phonetypeName").getBytes("iso8859-1"),"utf-8");	
		String categoryName = (request.getParameter("categoryName")==null || request.getParameter("categoryName").length()==0)?null:request.getParameter("categoryName");	
		String softName = (request.getParameter("softName")==null || request.getParameter("softName").length()==0)?null:new String(request.getParameter("softName").getBytes("iso8859-1"),"utf-8");	
		Date startTime = (request.getParameter("startTime")==null || request.getParameter("startTime").length()==0)?null:DateUtil.stringToDate(request.getParameter("startTime"), DateUtil.NOW_DATE);	
		Date endTime = (request.getParameter("endTime")==null || request.getParameter("endTime").length()==0)?null:DateUtil.stringToDate(request.getParameter("endTime"), DateUtil.NOW_DATE);	
		CirteriaBean cBean = new CirteriaBean("addTime");

		cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"d.id",downloadtypeId,new String[]{"downloadtype","d"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"emp.nickName",employeeName,new String[]{"users","emp"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"emp.userName",employeeNo,new String[]{"users","emp"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"eq.equipmentName",equipmentName,new String[]{"equipment","eq"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"bh.hallName",hallName,new String[]{"equipment","eq","eq.bissinusshall","bh"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"pt.phonetypeName",phonetypeName,new String[]{"phonetype","pt"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"pb.phonebrandName",phonebrandName,new String[]{"phonetype","pt","pt.phonebrandcategory","pdy","pdy.phonebrand","pb"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"pdy.categoryName",categoryName,new String[]{"phonetype","pt","pt.phonebrandcategory","pdy"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"sf.softName",softName,new String[]{"soft","sf"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.BETWEED,CirteriaQuery.IS_OBJECT,"addTime",new Object[]{startTime,endTime},null));
		
		List<JsonSoftDownloadStat> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByPage(Softdownloadstat.class, cBean), JsonSoftDownloadStat.class);
		int size = list.size();
		if(size >20000){
			model.put("success", false);
			model.put("msg", "您导出的数据量较大,请分条导出");
			return "list";
		}
		try {
			//OutputStream os = response.getOutputStream();
			String[] title = {"序号","设备名称","营业厅","软件名称","手机型号","型号分类","手机厂商","员工姓名","员工账户","下载类型","手机号码","发生时间"};
			ExcelCreatorUtil.exportExcel(new FileOutputStream(file), title, list);
			//response.sendRedirect("excel/"+fileName);
			model.put("success", true);
			model.put("msg", "导出成功");
			model.put("url", "excel/"+fileName);
			return "list";
			
		} catch (Exception e) {
			model.put("success", false);
			model.put("msg", "导出失败,请连接管理员");
			e.printStackTrace();
		}
		return "list";

	}
	

	@RequestMapping(value = "/downloadstat",params="csv")
	public void softdownloadstatCSV(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException{
		response.setContentType("application/CSV");
		response.setHeader("Location", (new Date().getTime())+".csv");
		response.setHeader("Cache-Control", "max-age=" + new Date());
		response.setHeader("Content-Disposition", "attachment; filename="+(new Date().getTime())+".csv");
		
		String fileName = new Date().getTime()+".csv";
		
		Integer downloadtypeId = (request.getParameter("downloadtypeId")==null || request.getParameter("downloadtypeId").length()==0)?-1:Integer.parseInt(request.getParameter("downloadtypeId"));	
		String employeeName = (request.getParameter("employeeName")==null || request.getParameter("employeeName").length()==0)?null:new String(request.getParameter("employeeName").getBytes("iso8859-1"),"utf-8");	
		String employeeNo = (request.getParameter("employeeNo")==null || request.getParameter("employeeNo").length()==0)?null:new String(request.getParameter("employeeNo").getBytes("iso8859-1"),"utf-8");	
		String kind = request.getParameter("kind");
		if(kind != null && kind.equals("2")){
			Users users = (Users)session.getAttribute("user");
			if(users != null){
				employeeNo = users.getUserName();
			}else{
				employeeNo = "-1";
			}
		}
		String equipmentName  = (request.getParameter("equipmentName")==null || request.getParameter("equipmentName").length()==0)?null:new String(request.getParameter("equipmentName").getBytes("iso8859-1"),"utf-8");	
		String hallName	 = (request.getParameter("hallName")==null || request.getParameter("hallName").length()==0)?null:new String(request.getParameter("hallName").getBytes("iso8859-1"),"utf-8");	
		String phonebrandName = (request.getParameter("phonebrandName")==null || request.getParameter("phonebrandName").length()==0)?null:new String(request.getParameter("phonebrandName").getBytes("iso8859-1"),"utf-8");	
		String phonetypeName = (request.getParameter("phonetypeName")==null || request.getParameter("phonetypeName").length()==0)?null:new String(request.getParameter("phonetypeName").getBytes("iso8859-1"),"utf-8");	
		String categoryName = (request.getParameter("categoryName")==null || request.getParameter("categoryName").length()==0)?null:request.getParameter("categoryName");	
		String softName = (request.getParameter("softName")==null || request.getParameter("softName").length()==0)?null:new String(request.getParameter("softName").getBytes("iso8859-1"),"utf-8");	
		Date startTime = (request.getParameter("startTime")==null || request.getParameter("startTime").length()==0)?null:DateUtil.stringToDate(request.getParameter("startTime"), DateUtil.NOW_DATE);	
		Date endTime = (request.getParameter("endTime")==null || request.getParameter("endTime").length()==0)?null:DateUtil.stringToDate(request.getParameter("endTime"), DateUtil.NOW_DATE);	
		CirteriaBean cBean = new CirteriaBean("addTime");

		cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"d.id",downloadtypeId,new String[]{"downloadtype","d"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"emp.nickName",employeeName,new String[]{"users","emp"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"emp.userName",employeeNo,new String[]{"users","emp"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"eq.equipmentName",equipmentName,new String[]{"equipment","eq"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"bh.hallName",hallName,new String[]{"equipment","eq","eq.bissinusshall","bh"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"pt.phonetypeName",phonetypeName,new String[]{"phonetype","pt"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"pb.phonebrandName",phonebrandName,new String[]{"phonetype","pt","pt.phonebrandcategory","pdy","pdy.phonebrand","pb"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"pdy.categoryName",categoryName,new String[]{"phonetype","pt","pt.phonebrandcategory","pdy"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"sf.softName",softName,new String[]{"soft","sf"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.BETWEED,CirteriaQuery.IS_OBJECT,"addTime",new Object[]{startTime,endTime},null));
		
		List<JsonSoftDownloadStat> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByPage(Softdownloadstat.class, cBean), JsonSoftDownloadStat.class);

		try {
			OutputStream os = response.getOutputStream();
			String[] title = {"序号","设备名称","营业厅","软件名称","手机型号","型号分类","手机厂商","员工姓名","员工账户","下载类型","手机号码","发生时间"};
			//ExcelCreatorUtil.exportExcel(new FileOutputStream(file), title, list);
			CsvCreatorUtil.exportCsv(os, title, list);
			//response.sendRedirect("excel/"+fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	

	@RequestMapping(value = "/downloadstat",params="comboDownloadtype")
	public String comboDownloadtypeJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		List<JsonDownloadtype> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().findAll(Downloadtype.class), JsonDownloadtype.class);
		JsonDownloadtype all = new JsonDownloadtype();
		all.setDownloadtypeId("-1");
		all.setDownloadtypeName("全部");
		powerList.add(all);
		model.addAttribute("array", powerList);
		return "list";

	}
}
