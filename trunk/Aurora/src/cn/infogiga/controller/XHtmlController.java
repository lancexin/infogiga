package cn.infogiga.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

@Controller
public class XHtmlController {
	
	@RequestMapping("/ewap/index.xhtml")
	public ModelAndView sayHello(){
		return null;
	}	
}
