package manage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import tool.Toolkit;
import bean.ClientBean;
import bean.MailBean;
import bean.RegionBean;
import bean.ReportBean;
import bean.ResultBean;
import bean.UserBean;
import chart.Chart;
import data.Database;

/**
 * 二维码的登记信息管理
 * @author ya
 */
public class PageManage extends HttpServlet {
	
	private static final long serialVersionUID = 3864547072181470662L;
	private static Logger log = Logger.getLogger(PageManage.class.getName());
	private Database db = new Database();
	private boolean isChart = false; //是否生成图表
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String type = request.getParameter("type");		
		
		if("login".equals(type)) {//登录
			doLogin(request, response);
		} 		
		else if ("search".equals(type)) {//search请求，则查询
			isChart = true;
			if(checkAuthority(request, response, 1)) {
				doSearch(request);
			}
		}
		else if ("fast".equals(type)) {//快速查询请求
			isChart = false;
			if(checkAuthority(request, response, 1)) {
				doSearch(request);
			}
		}
		else if("append".equals(type)) {//添加
			if(checkAuthority(request, response, 0)) {
				doAppend(request, response);
			}
		}
		else if ("report".equals(type)) {//report请求，则产生报表
			if(checkAuthority(request, response, 0)) {
				doReport(request, response);
			}
		}
		else if ("read".equals(type)) {//read请求，获取属性文件里面的邮件的配置
			if(checkAuthority(request, response, 1)) {
				doRead(request, response);
			}
		}
		else if ("set".equals(type)) {//set请求，设置属性文件
			if(checkAuthority(request, response, 0)) {
				doSet(request, response);
			}
		}
		else if ("updateLink".equals(type)) {//更新链接 
			if(checkAuthority(request, response, 1)) {
				doLink(request, response);
			}
		}
		else if ("sms".equals(type)) {//更改短信内容
			if(checkAuthority(request, response, 0)) {
				updateSms(request, response);
			}
		}
		else if ("client".equals(type)) {//全场控制
			if(checkAuthority(request, response, 0)) {
				doClient(request, response);
			}
		}
	}
	
	/**
	 * 更新当前链接
	 * @param request
	 * @param response
	 */
	protected void doLink(HttpServletRequest request, HttpServletResponse response) {
		String url = request.getParameter("url");
		request.getSession().setAttribute("url", url);		
	}
	
	/**
	 * 登记
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String userName = request.getParameter("user");
		String password = request.getParameter("pwd");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		UserBean user = null;  //userbean
		int flag;
		
		flag = db.login(userName, password);
		log.info("来自IP："+ request.getRemoteAddr()+"，登入用户："+ userName+ ",权限:"+ flag);
		switch (flag) {
		case -2:;
		case -1: 
			out.write(flag+"");
			break;
		case 0:;
		case 1: 
			user = new UserBean(userName, password, flag);
			session.setAttribute("user", user);   //登陆成功，则userbean放入session中
			log.info(userName+"登录成功");
			out.write("true");
			break;
		default: out.write("false");break;
		}
	}
		
	/**
	 * 注销
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	protected void doSearch(HttpServletRequest request)
			throws IOException {
		String addressCode = request.getParameter("area");
		String sellingCode = request.getParameter("selling");
		String regionCode = request.getParameter("region");
		String equipmentCode = request.getParameter("equipment");
		String systemCode = request.getParameter("sys");
		String operateCode = request.getParameter("operate");
		String startTime = request.getParameter("stime");
		String endTime = request.getParameter("etime");
		String mobile = request.getParameter("mobile");
		
		HttpSession session = request.getSession();
		ArrayList<ArrayList<ResultBean>> resultList = new ArrayList<ArrayList<ResultBean>>();
		ArrayList<RegionBean> regionList = new ArrayList<RegionBean>();
		
		if("".equals(regionCode)) {
			for(RegionBean region: db.getRegions()) {//所有统计数据
				resultList.add(Toolkit.getHeaderList(
						db.getReport(addressCode, sellingCode, region.getRegionCode(), 
								equipmentCode, systemCode, operateCode, startTime, endTime, mobile)));
				regionList.add(new RegionBean(region.getRegionCode(), region.getRegionName()));
				session.setAttribute(region.getRegionCode(), resultList.get(resultList.size() -1));
			}
		}
		else {
			session.setAttribute(regionCode, Toolkit.getHeaderList(db.getReport(addressCode, sellingCode, regionCode, 
					equipmentCode, systemCode, operateCode, startTime, endTime, mobile)));
			regionList.add(new RegionBean(regionCode, db.getRegionName(regionCode)));
		}
		session.setAttribute("regionList", regionList);
		ArrayList<ReportBean> list = db.getReport(addressCode, sellingCode, regionCode,
				equipmentCode, systemCode, operateCode, startTime, endTime, mobile);
		ArrayList<ResultBean> headerList = Toolkit.getHeaderList(list);
		
		if(isChart) {//要生成图表
			Chart chart = new Chart();
			String prefix = Toolkit.getNow();
			String barImage = prefix+ "-2.jpg";
			String pieImage = prefix+ "-1.jpg";//图片名字
			String barImageName = getServletContext().getRealPath("/report")+ "\\"+ barImage;
			String pieImageName = getServletContext().getRealPath("/report")+ "\\"+ pieImage;//全路径
			chart.buildBarChart(headerList, barImageName);
			chart.buildPieChart(headerList, pieImageName);
				
			session.setAttribute("barChart", barImage);
			session.setAttribute("pieChart", pieImage);
		}
		session.setAttribute("dataList", list); //数据list放入session中
		session.setAttribute("isChart", isChart);
		session.setAttribute("resultList", resultList);//统计结果
	}
	
	/**
	 *  插入 
	 */
	protected void doAppend(HttpServletRequest request, HttpServletResponse response){		
		String key = request.getParameter("key");
		String name = "";
		String code = "";
		boolean flag = false;
		
		if("area".equals(key)) {
			name = Toolkit.encode(request.getParameter("areaName"), "", "utf-8");
//			name = request.getParameter("areaName");
			code = request.getParameter("areaCode");
			log.info("增加地区："+name+":"+code);
			flag = db.insertData("area", name, code);
		}
		else if("selling".equals(key)) {
			name = Toolkit.encode(request.getParameter("sellingName"), "", "utf-8");
//			name = request.getParameter("sellingName");
			code = request.getParameter("sellingCode");
			log.info("增加营业厅："+name+":"+code);
			flag = db.insertData("selling", name, code);
		}
		else if("equipment".equals(key)) {
			name = Toolkit.encode(request.getParameter("equipmentName"), "", "utf-8");
//			name = request.getParameter("equipmentName");
			code = request.getParameter("equipmentCode");
			log.info("增加设备："+name+":"+code);
			flag = db.insertData("equipment", name, code);
		}
		try {
			response.getWriter().write(flag+"");
		} catch (IOException e) {
			log.error("response回应失败");
		}
	}
	
	/**
	 * 读取mail表中的发送邮件的参数
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	protected void doRead(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		MailBean bean = db.getMailBean();
		StringBuffer buffer = new StringBuffer("{");
		String json;
		
		buffer.append("'smtp':'"+ bean.getSmtpHost()+"',");
		buffer.append("'from':'"+ bean.getFrom()+"',");
		buffer.append("'to':'"+ bean.getTo()+"',");
		buffer.append("'pwd':'"+ bean.getPassword()+"',");
		buffer.append("'cyc':'"+ bean.getCyc()+"',");
		buffer.append("'hour':'"+ bean.getHour()+"',");
		buffer.append("'flag':'"+ bean.getFlag()+"'}");
		json = buffer.toString();
		
		log.debug(json);
		response.getWriter().write(json);	
	}
	
	/**
	 * 设置邮件参数
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	protected void doSet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		MailBean bean = new MailBean();
		String smtp = request.getParameter("smtp");
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String pwd = request.getParameter("pwd");
		String cyc = request.getParameter("cyc");
		String hour = request.getParameter("hour");
		String flag = request.getParameter("flag");
		
		bean.setSmtpHost(smtp);
		bean.setFrom(from);
		bean.setTo(to);
		bean.setUserName(from.split("@")[0]);
		bean.setPassword(pwd);
		bean.setCyc(cyc);
		bean.setHour(hour);
		bean.setFlag(flag);
		
		response.getWriter().write(db.configMail(bean)+ "");		
	}
	
	/**
	 * 报表
	 */
	@SuppressWarnings("unchecked")
	private void doReport(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ArrayList<ReportBean> list = (ArrayList<ReportBean>)session.getAttribute("dataList");
		ArrayList<ArrayList<ResultBean>> resultList = (ArrayList<ArrayList<ResultBean>>)session.getAttribute("resultList");		
		if (list == null) {
			try {
				response.sendRedirect("./admin/search.jsp");
			} catch (IOException e) {
				log.error("response回应失败");
			}
			return;	
		}
		String realPath = session.getServletContext().getRealPath("/report")+ "/";
		String filedownload = Toolkit.getNow()+ ".xls";//即将下载的文件的相对路径
	    String filedisplay = "报表"+ filedownload;//下载文件时显示的文件保存名称
	    
		Report report = new Report();
		report.setList(list);
		//report.setHeaderList(headerList);
		report.setResultList(resultList);
		report.setFile(realPath+ filedownload);
		if(isChart) {
			report.setPictures(new String[]{realPath+ session.getAttribute("barChart"), 
					realPath+ session.getAttribute("pieChart")});
		}
		report.build();
		log.info("产生报表:"+filedownload);
		
		try {
			response.setContentType("application/x-msexcel");//			
		    response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(filedisplay, "UTF-8"));
		    RequestDispatcher dis = session.getServletContext().getRequestDispatcher("/report"+ "/"+ filedownload);
	        if(dis!= null)
	        {	        	  
	            dis.forward(request,response);
	        }
	        response.flushBuffer();
		} catch (IOException e) {
			log.error(e+ "IO异常，response回应失败");
		} catch (ServletException e) {
			log.error(e+ "servlet异常，转发失败");
		}
	}
	
	/**
	 * 更新短信内容
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void updateSms(HttpServletRequest request, HttpServletResponse response) 
			throws IOException {
		String code = request.getParameter("code");
		String sms = request.getParameter("sms");
		
		sms = Toolkit.encode(sms, "ISO-8859-1", "utf-8");
		response.getWriter().write(db.updateSms(code, sms)+"");
	}
	
	/**
	 * 对全场机器的操作
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void doClient(HttpServletRequest request, HttpServletResponse response) 
			throws IOException {
		String command = request.getParameter("command");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String ip = request.getParameter("ip");		
		
		if("add".equals(command)) {
			ClientBean client = new ClientBean(
					Integer.parseInt(id), Toolkit.encode(name, "ISO-8859-1", "UTF-8"),
					Toolkit.encode(subject, "ISO-8859-1", "UTF-8"), ip);
			response.getWriter().write(db.addClient(client)+"");
		} else if("update".equals(command)) {
			ClientBean client = new ClientBean(
					Integer.parseInt(id), Toolkit.encode(name, "ISO-8859-1", "UTF-8"),
					Toolkit.encode(subject, "ISO-8859-1", "UTF-8"), ip);
			response.getWriter().write(db.updateClient(client)+"");
		} else if("delete".equals(command)) {
			response.getWriter().write(db.deleteClient(id)+"");
		} else if("get".equals(command)) {
			response.getWriter().write(db.getClient(id));
		}
	}
	
	/**
	 * 权限控制
	 * @param request
	 * @param response
	 * @param authority
	 * @return 是否允许
	 * @throws IOException
	 * @throws ServletException
	 */
	private boolean checkAuthority(HttpServletRequest request, HttpServletResponse response, int authority) 
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		UserBean user = (UserBean) session.getAttribute("user");
		if(user == null) {
			log.error("未登录, ip:"+ request.getRemoteAddr());			
			return false;
		}
		else if(user.getAuthority() > authority) {
			log.error("没有权限, ip:"+ request.getRemoteAddr());
			return false;
		}
		return true;
	}
}
