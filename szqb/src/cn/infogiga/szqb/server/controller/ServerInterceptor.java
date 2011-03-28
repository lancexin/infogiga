package cn.infogiga.szqb.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.infogiga.szqb.server.xml.ErrorMgr;
import cn.infogiga.szqb.server.xml.FromMsg;
import cn.infogiga.szqb.server.xml.ToMsg;
import cn.infogiga.szqb.server.xml.XmlError;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ServerInterceptor implements HandlerInterceptor  {
	
	private static final Log log = LogFactory.getLog(ServerInterceptor.class);

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object o, Exception exception)
			throws Exception {
		request.removeAttribute("toMsg");
		request.removeAttribute("fromMsg");
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object o, ModelAndView mav) throws Exception {
		XStream xstream = new XStream();
		xstream.autodetectAnnotations(true);
		ToMsg toMsg = (ToMsg) request.getAttribute("toMsg");
		String callback = xstream.toXML(toMsg);
		log.info("返回请求："+callback);
		response.setCharacterEncoding("utf-8");
		response.getWriter().println(callback);
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object o) throws Exception {
		XStream xstream = new XStream(new DomDriver());
		xstream.autodetectAnnotations(true);
		xstream.alias("msg", FromMsg.class);
		String xml = request.getReader().readLine();
		log.info("接收请求："+xml);
		if(xml == null || xml.length() <= 0){
			XmlError error = ErrorMgr.getFormatError();
			ToMsg toMsg = new ToMsg();
			toMsg.setError(error);
			response.getWriter().println(xstream.toXML(toMsg));
			return false;
		}
		FromMsg fMsg;
		try {
			fMsg = (FromMsg) xstream.fromXML(xml);
			if(fMsg == null){
				XmlError error = ErrorMgr.getFormatError();
				ToMsg toMsg = new ToMsg();
				toMsg.setError(error);
				response.getWriter().println(xstream.toXML(toMsg));
				return false;
			}
			request.setAttribute("fromMsg", fMsg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

}
