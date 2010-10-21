package cn.infogiga.sd.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cindy.page.beanutils.MyBeanUtils;
import cindy.page.hibernate.CirteriaBean;
import cindy.page.hibernate.CirteriaQuery;
import cindy.util.DateUtil;
import cindy.util.ExcelCreatorUtil;
import cn.infogiga.sd.dto.JsonListBean;
import cn.infogiga.sd.dto.JsonMusicdownloadstat;
import cn.infogiga.sd.dto.JsonSoftDownloadStat;
import cn.infogiga.sd.pojo.Musicdownloadstat;
import cn.infogiga.sd.pojo.Softdownloadstat;
import cn.infogiga.sd.service.ManageService;

public class MappingExportController {
	
	ManageService manageService;
	
	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}
	
	@RequestMapping(value = "/export",params="softdownloadstat")
	public void softdownloadstatExport(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException{
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Location", (new Date().getTime())+".xls");
		response.setHeader("Cache-Control", "max-age=" + new Date());
		response.setHeader("Content-Disposition", "attachment; filename="+(new Date().getTime())+".xls");
		Integer downloadtypeId = (request.getParameter("downloadtypeId")==null || request.getParameter("downloadtypeId").length()==0)?-1:Integer.parseInt(request.getParameter("downloadtypeId"));	
		String employeeName = (request.getParameter("employeeName")==null || request.getParameter("employeeName").length()==0)?null:new String(request.getParameter("employeeName").getBytes("iso8859-1"),"utf-8");	
		String employeeNo = (request.getParameter("employeeNo")==null || request.getParameter("employeeNo").length()==0)?null:new String(request.getParameter("employeeNo").getBytes("iso8859-1"),"utf-8");	
		String equipmentName  = (request.getParameter("equipmentName")==null || request.getParameter("equipmentName").length()==0)?null:new String(request.getParameter("equipmentName").getBytes("iso8859-1"),"utf-8");	
		String hallName	 = (request.getParameter("hallName")==null || request.getParameter("hallName").length()==0)?null:new String(request.getParameter("hallName").getBytes("iso8859-1"),"utf-8");	
		String phonebrandName = (request.getParameter("phonebrandName")==null || request.getParameter("phonebrandName").length()==0)?null:new String(request.getParameter("phonebrandName").getBytes("iso8859-1"),"utf-8");	
		String phonetypeName = (request.getParameter("phonetypeName")==null || request.getParameter("phonetypeName").length()==0)?null:new String(request.getParameter("phonetypeName").getBytes("iso8859-1"),"utf-8");	
		String softName = (request.getParameter("softName")==null || request.getParameter("softName").length()==0)?null:new String(request.getParameter("softName").getBytes("iso8859-1"),"utf-8");	
		Date startTime = (request.getParameter("startTime")==null || request.getParameter("startTime").length()==0)?null:DateUtil.stringToDate(request.getParameter("startTime"), DateUtil.NOW_DATE);	
		Date endTime = (request.getParameter("endTime")==null || request.getParameter("endTime").length()==0)?null:DateUtil.stringToDate(request.getParameter("endTime"), DateUtil.NOW_DATE);	
		CirteriaBean cBean = new CirteriaBean("addTime");

		cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"d.downloadtypeId",downloadtypeId,new String[]{"downloadtype","d"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"emp.nickName",employeeName,new String[]{"employee","emp"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"emp.userName",employeeNo,new String[]{"employee","emp"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"eq.equipmentName",equipmentName,new String[]{"equipment","eq"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"bh.hallName",hallName,new String[]{"equipment","eq","eq.bissinusshall","bh"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"pt.phonetypeName",phonetypeName,new String[]{"phonetype","pt"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"pb.phonebrandName",phonebrandName,new String[]{"phonetype","pt","pt.phonebrand","pb"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"sf.softName",softName,new String[]{"soft","sf"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.BETWEED,CirteriaQuery.IS_OBJECT,"addTime",new Object[]{startTime,endTime},null));
		
		//int totalCount = manageService.getManageDAO().getCountByPage(Softdownloadstat.class, cBean);
		
		List<JsonSoftDownloadStat> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByPage(Softdownloadstat.class, cBean), JsonSoftDownloadStat.class);
	
		try {
			OutputStream os = response.getOutputStream();
			String[] title = {"序号","设备名称","营业厅","软件名称","手机型号","手机厂商","员工姓名","员工账户","下载类型","手机号码","发生时间"};
			ExcelCreatorUtil.exportExcel(os, title, list);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value = "/export",params="musicdownloadstat")
	public void musicdownloadstatExport(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException{
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Location", (new Date().getTime())+".xls");
		response.setHeader("Cache-Control", "max-age=" + new Date());
		response.setHeader("Content-Disposition", "attachment; filename="+(new Date().getTime())+".xls");
		
		Integer downloadtypeId = (request.getParameter("downloadtypeId")==null || request.getParameter("downloadtypeId").length()==0)?-1:Integer.parseInt(request.getParameter("downloadtypeId"));	
		String employeeName = (request.getParameter("employeeName")==null || request.getParameter("employeeName").length()==0)?null:new String(request.getParameter("employeeName").getBytes("iso8859-1"),"utf-8");	
		String employeeNo = (request.getParameter("employeeNo")==null || request.getParameter("employeeNo").length()==0)?null:new String(request.getParameter("employeeNo").getBytes("iso8859-1"),"utf-8");	
		String equipmentName  = (request.getParameter("equipmentName")==null || request.getParameter("equipmentName").length()==0)?null:new String(request.getParameter("equipmentName").getBytes("iso8859-1"),"utf-8");	
		String hallName	 = (request.getParameter("hallName")==null || request.getParameter("hallName").length()==0)?null:new String(request.getParameter("hallName").getBytes("iso8859-1"),"utf-8");	
		String musicName = (request.getParameter("musicName")==null || request.getParameter("musicName").length()==0)?null:new String(request.getParameter("musicName").getBytes("iso8859-1"),"utf-8");	
		String musicmanName = (request.getParameter("musicmanName")==null || request.getParameter("musicmanName").length()==0)?null:new String(request.getParameter("musicmanName").getBytes("iso8859-1"),"utf-8");	
		Date startTime = (request.getParameter("startTime")==null || request.getParameter("startTime").length()==0)?null:DateUtil.stringToDate(request.getParameter("startTime"), DateUtil.NOW_DATE);	
		Date endTime = (request.getParameter("endTime")==null || request.getParameter("endTime").length()==0)?null:DateUtil.stringToDate(request.getParameter("endTime"), DateUtil.NOW_DATE);	
		CirteriaBean cBean = new CirteriaBean("addTime");

		cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"d.downloadtypeId",downloadtypeId,new String[]{"downloadtype","d"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"emp.nickName",employeeName,new String[]{"employee","emp"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"emp.userName",employeeNo,new String[]{"employee","emp"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"eq.equipmentName",equipmentName,new String[]{"equipment","eq"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"bh.hallName",hallName,new String[]{"equipment","eq","eq.bissinusshall","bh"}));

		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"mu.musicName",musicName,new String[]{"music","mu"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"mn.musicmanName",musicmanName,new String[]{"music","mu","mu.musicman","mn"}));
		
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.BETWEED,CirteriaQuery.IS_OBJECT,"addTime",new Object[]{startTime,endTime},null));
		
		
		List<JsonMusicdownloadstat> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByPage(Musicdownloadstat.class, cBean), JsonMusicdownloadstat.class);
	
		try {
			OutputStream os = response.getOutputStream();
			String[] title = {"序号","设备名称","营业厅","音乐名称","歌手姓名","员工姓名","员工账户","下载类型","手机号码","发生时间"};
			ExcelCreatorUtil.exportExcel(os, title, list);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
