package cn.infogiga.exp.controller;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

import cindy.util.DateUtil;
import cindy.util.ExcelCreatorUtil;
import cindy.util.page.hibernate.CirteriaBean;
import cindy.util.page.hibernate.CirteriaQuery;
import cn.infogiga.exp.excel.DownloadstatExcel;
import cn.infogiga.exp.excel.ExcelBeanUtil;
import cn.infogiga.exp.excel.StatisticsExcel;
import cn.infogiga.exp.pojo.Downloadstat;
import cn.infogiga.exp.pojo.Statistics;
import cn.infogiga.exp.pojo.Sysinfo;
import cn.infogiga.exp.service.ExpService;

public class ExportController {
	
	ExpService expService;

	public void setExpService(ExpService expService) {
		this.expService = expService;
	}

	@RequestMapping(value = "/export",params="statistics")
	public void exportStatistics(HttpServletRequest request,
			HttpServletResponse response){
		String prame = request.getParameter("prames");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Location", new Date()+".xls");
		response.setHeader("Cache-Control", "max-age=" + new Date());
		response.setHeader("Content-Disposition", "attachment; filename="+new Date()+".xls");
		
		CirteriaBean cBean = new CirteriaBean("happenTime");
		if(prame != null && !"".equals(prame)){
			String[] params = prame.split(",");
			Integer teamId = params[0].equals("-")?-1:Integer.parseInt(params[0]);
			Integer equipmentId = params[1].equals("-")?-1:Integer.parseInt(params[1]);
			Integer employeeId = params[2].equals("-")?-1:Integer.parseInt(params[2]);
			Integer menuId = params[3].equals("-")?-1:Integer.parseInt(params[3]);
			Integer sceneId = params[4].equals("-")?-1:Integer.parseInt(params[4]);
			String phoneNumber = params[5].equals("-")?null:params[5];
			String empPhone = params[6].equals("-")?null:params[6];
	
			Date startTime = params[7].equals("-")?null:DateUtil.stringToDate(params[7], DateUtil.NOW_DATE);
			Date endTime = params[8].equals("-")?null:DateUtil.stringToDate(params[8], DateUtil.NOW_DATE);
			
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"t.teamId",teamId,new String[]{"employee","emp","emp.team","t"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"e.equipmentId",equipmentId,new String[]{"equipment","e"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"emp.employeeId",employeeId,new String[]{"employee","emp"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"m.mid",menuId,new String[]{"menu","m"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"s.sceneId",sceneId,new String[]{"scene","s"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"phoneNumber",phoneNumber,null));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"emp.empPhone",empPhone,new String[]{"employee","emp"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.BETWEED,CirteriaQuery.IS_OBJECT,"happenTime",new Object[]{startTime,endTime},null));
		}
		List<Statistics> list = expService.getListByPage(Statistics.class, cBean);
		
		List<StatisticsExcel> excel = ExcelBeanUtil.getStatisticsExcelList(list);
		
		try {
			OutputStream os = response.getOutputStream();
			String[] title = {"序号","员工姓名","员工编号","营业厅","设备名称","客户电话","业务名称","情景","套餐名称","发生时间"};
			ExcelCreatorUtil.exportExcel(os, title, excel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/export",params="downloadstat")
	public void exportDownloadstat(HttpServletRequest request,
			HttpServletResponse response){
		String prame = request.getParameter("prames");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Location", new Date()+".xls");
		response.setHeader("Cache-Control", "max-age=" + new Date());
		response.setHeader("Content-Disposition", "attachment; filename="+new Date()+".xls");
		
		CirteriaBean cBean = new CirteriaBean("addTime");
		System.out.println(prame);
		if(prame != null && !"".equals(prame)){
			String[] params = prame.split(",");
			
			Integer teamId = params[0].equals("-")?-1:Integer.parseInt(params[0]);
			Integer equipmentId = params[1].equals("-")?-1:Integer.parseInt(params[1]);
			Integer employeeId = params[2].equals("-")?-1:Integer.parseInt(params[2]);
			String phoneNumber = params[3].equals("-")?null:params[3];
			String phonetypeName = params[4].equals("-")?null:params[4];
			Integer phonebrandId = params[5].equals("-")?-1:Integer.parseInt(params[5]);
			String softName = params[6].equals("-")?null:params[6];
			Integer downloadtypeId = params[7].equals("-")?-1:Integer.parseInt(params[7]);
			Date startTime = params[8].equals("-")?null:DateUtil.stringToDate(params[8], DateUtil.NOW_DATE);
			Date endTime = params[9].equals("-")?null:DateUtil.stringToDate(params[9], DateUtil.NOW_DATE);
			
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"t.teamId",teamId,new String[]{"employee","emp","emp.team","t"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"e.equipmentId",equipmentId,new String[]{"equipment","e"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"emp.employeeId",employeeId,new String[]{"employee","emp"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"phoneNumber",phoneNumber,null));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"pt.typeName",phonetypeName,new String[]{"phonetype","pt"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"pb.phonebrandId",phonebrandId,new String[]{"phonetype","pt","pt.phonebrand","pb"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"info.softName",softName,new String[]{"softinfo","info"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"dt.downloadtypeId",downloadtypeId,new String[]{"downloadtype","dt"}));
			cBean.addQuery(new CirteriaQuery(CirteriaQuery.BETWEED,CirteriaQuery.IS_OBJECT,"addTime",new Object[]{startTime,endTime},null));
		}
		List<Downloadstat> list = expService.getListByPage(Downloadstat.class, cBean);
		
		List<DownloadstatExcel> excel = ExcelBeanUtil.getDownloadstatExcelList(list);
		
		try {
			OutputStream os = response.getOutputStream();
			String[] title = {"序号","员工姓名","员工编号","营业厅","设备名称","下载类别","客户手机","软件名称","手机厂商","手机型号","下载时间"};
			ExcelCreatorUtil.exportExcel(os, title, excel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
