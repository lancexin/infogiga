package cn.infogiga.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.xml.internal.fastinfoset.util.StringArray;

import cn.infogiga.config.WapConfig;
import cn.infogiga.service.AppointmentService;
import cn.infogiga.service.LoginService;
import cn.infogiga.service.QrcodeService;
import cn.infogiga.service.SystemService;
import cn.infogiga.service.qrcode.Qrcode;
import cn.infogiga.service.qrcode.QrcodeBean;
import cn.infogiga.util.StringToolkit;

@Controller
public class WpController {		
	private static final Log log = LogFactory.getLog(WpController.class);
	
	private static WapConfig wapConfig;
	private static Map<String, String> pages;
	private static Map<String, String> versions;
	private static Map<String, String> regions;
	private static final String DEFAULT_PAGE = "1";
	private static final String ERROR_PAGE = "0";
	private static final String LOGIN_PAGE = "4";
	private static final String DEFAULT_VERSION = "2";
	
	private LoginService loginService;
	private QrcodeService qrcodeService;//二维码导览服务
	private AppointmentService appointmentService;
	private Qrcode qrWebService;	 //二维码的webservice
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	public void setQrcodeService(QrcodeService qrcodeService) {
		this.qrcodeService = qrcodeService;
	}	
	public void setAppointmentService(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	public WpController() {
		wapConfig = WapConfig.getConfig();
		pages = wapConfig.getPages();
		versions = wapConfig.getVersions();
		regions = wapConfig.getRegions();
		qrWebService = SystemService.getService().getHandler(SystemService.QRCODE);
	}
	
	/**
	 * 检查是否刷过二维码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/to", method=RequestMethod.POST)	
	public void checkCode(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String wapcode = (String)session.getAttribute("qrcode"); //session中取得二维码
		String regionCode = qrcodeService.getRegionCode(wapcode);//然后得到此二维码刷过的区域码
		if(regionCode == null) {
			response.getWriter().write("0");
		} else {
			response.getWriter().write("1");
		}
	}

	/**
	 * wap页面上刷二维码进行访问
	 * @param request
	 * @param v
	 * @return
	 */
	@RequestMapping(value="/to", method=RequestMethod.GET)	
	public String qrcode(HttpServletRequest request,
			@RequestParam(value="v", required=false)String v) {		
		String version = versions.get(v);
		HttpSession session = request.getSession();
		String wapcode = (String)session.getAttribute("qrcode"); //session中取得二维码
		String regionCode = qrcodeService.pickRegionCode(wapcode);//然后得到此二维码刷过的区域码
		String page = pages.get(regions.get(regionCode));
		System.out.println("qrcode:"+page);
		if(page == null && version == null) {
			//页面和版本都是空，返回首页
			page = pages.get(DEFAULT_PAGE);
			version = versions.get(DEFAULT_VERSION);			
		} else if(version == null) {
			//只有页面，没有版本，用默认版本
			version = versions.get(DEFAULT_VERSION);
		} else if(page == null) {
			//只有版本，没有页面，返回错误页面
			page = pages.get(ERROR_PAGE);
		}
		return version+ "/"+ page;
	}
	
	/**
	 * 进入页面
	 * @param p
	 * @param v
	 * @param qrCode
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/q",method = RequestMethod.GET)	
	public String enter(HttpServletRequest request,
			@RequestParam(value="p",required=false)String p, 
			@RequestParam(value="v",required=false)String v,
			@RequestParam(value="code",required=false)String code,
			@RequestParam(value="pwd",required=false)String pwd,
			@RequestParam(value="sc",required=false)String showCode){
		String page = pages.get(p);
		String version = versions.get(v);
		String wapCode = code;
		String password = pwd;
		
		login(request, wapCode, password);//尝试登录
		if(showCode !=null && request.getSession().getAttribute("qrcodePic")!=null) {
			System.out.println("showCode:"+showCode);
			request.getSession().setAttribute("showCode", "1".equals(showCode)?true:false);
		}
		System.out.println("enter:"+page);
		if(page == null && version == null) {
			//页面和版本都是空，返回首页
			page = pages.get(DEFAULT_PAGE);
			version = versions.get(DEFAULT_VERSION);			
		} else if(version == null) {
			//只有页面，没有版本，用默认版本
			version = versions.get(DEFAULT_VERSION);
		} else if(page == null) {
			//只有版本，没有页面，返回错误页面
			page = pages.get(ERROR_PAGE);
		}
		return version+ "/"+ page;			
	}
		
	/**
	 * 登录
	 * @param request
	 * @param v
	 * @param code
	 * @param pwd
	 * @return
	 */
	@RequestMapping(value="/q",method = RequestMethod.POST, params="login")	
	public String login(HttpServletRequest request,
			@RequestParam(value="v",required=false)String v,
			@RequestParam(value="code")String code,
			@RequestParam(value="pwd")String pwd) {
		String version = versions.get(v);		
		String wapCode = code;
		String password = pwd;
		String page = pages.get(LOGIN_PAGE);
		boolean success = login(request, wapCode, password);
		
		if(version == null) {
			version = versions.get(DEFAULT_VERSION);
		}
		page = success?pages.get(DEFAULT_PAGE):page;		
		request.setAttribute("loginInfo", success);
		return version+"/"+ page;
	}
	
	/**
	 * 处理预约请求
	 * @param name
	 * @param phoneNumber
	 * @param company
	 * @param reason
	 */
	@RequestMapping(value="/app", method=RequestMethod.POST)
	public String createApplication(HttpServletRequest request,
			@RequestParam(value="name")String name, 
			@RequestParam(value="phonenumber")String phoneNumber,
			@RequestParam(value="company",required=false)String company,
			@RequestParam(value="reason", required=false)String reason,
			@RequestParam(value="p")String p, 
			@RequestParam(value="v")String v) {
		appointmentService.sendApplication(name, phoneNumber, company, reason);
		request.setAttribute("reserve", true);
		return versions.get(v)+"/"+pages.get(p);
	}
	
	/**
	 * 创建评论和意见建议
	 * @param request
	 * @param name
	 * @param phoneNumber
	 * @param content
	 * @param p
	 * @param v
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/comment", method=RequestMethod.POST)
	public String createComment(HttpServletRequest request,
//			@RequestParam(value="name", required=false)String name, 
			@RequestParam(value="phonenumber")String phoneNumber,
//			@RequestParam(value="content")String content,
			@RequestParam(value="p")String p, 
			@RequestParam(value="v")String v) throws UnsupportedEncodingException {
		String ip = request.getRemoteAddr();
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		/*if(name != null) {
			name = new String(name.getBytes("UTF-8"), "GBK");
		}
		content = new String(content.getBytes("UTF-8"), "GBK");*/
		
		appointmentService.comment(name, phoneNumber, content, ip);
		request.setAttribute("comment", true);
		return versions.get(v)+"/"+pages.get(p);
	}
	
	private boolean login(HttpServletRequest request, String wapCode, String password) {
		//二维码和密码都不空
		if(!StringToolkit.isBlank(wapCode) && !StringToolkit.isBlank(password)) {
			//是有效的wap版二维码
			if(qrcodeService.validate(wapCode) && qrcodeService.isWapVersion(wapCode)) {
				//登录, 成功则图片放入session
				if(loginService.checkWapLogin(wapCode, password)) {
					HttpSession session = request.getSession();
					if(session.getAttribute("qrcodePic") == null) {//检查是否已经登陆过
						QrcodeBean qrb = new QrcodeBean(wapCode, 4, "gif");
						qrWebService.createQrcode(qrb);	//生成二维码图片
						String picName = qrb.getQrcodePicName();	//得到图片名字
						session.setAttribute("qrcode", wapCode);	
						session.setAttribute("showCode", true);
						session.setAttribute("qrcodePic", picName);	//放入session中
						return true;
					}
				} else {//登录失败
					return false;
				}
			}
		}
		return false;
	}
}
