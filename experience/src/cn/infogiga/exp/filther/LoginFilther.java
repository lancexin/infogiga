package cn.infogiga.exp.filther;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class LoginFilther implements Filter {
	private final static Log log = LogFactory.getLog(LoginFilther.class);

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpSession session=((HttpServletRequest)request).getSession();
		
		if(session.getAttribute("user") == null ){
			HttpServletRequest hrequest = (HttpServletRequest)request;
			((HttpServletResponse)response).sendRedirect(hrequest.getContextPath()+"/");
		}else{
			chain.doFilter(request, response);
		}
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}

