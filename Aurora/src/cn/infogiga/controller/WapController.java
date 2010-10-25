package cn.infogiga.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;

@Controller
public class WapController {
	
	/**
	 * 不带任何参数，则直接进入index.jsp，或者进入登录页面
	 * @return
	 */
	@RequestMapping(value="/wap/index.wml",method = RequestMethod.GET)	
	public ModelAndView index(){		
		return new  ModelAndView("index.jsp");
	}
	
	/**
	 *  用形如wap/index.wml?login&user=abc这种url访问的时候，进入登录程序
	 * @param username
	 * @return
	 */
	@RequestMapping(value="/wap/index.wml",params="login",method = RequestMethod.GET)	
	public ModelAndView login(@RequestParam("username")String username){		
		return new  ModelAndView("index.jsp?login=m&username="+username);
	}	
	
	@RequestMapping(value="/wap/index.wml",params="logout",method = RequestMethod.GET)	
	public String logout(){		
		return "index.jsp";
	}	
}
