package cindy.springframework;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

public class UrlTypeInperceptor implements HandlerInterceptor  {
	
	private String typeName = "type";
	private Map<String,View> views;

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public void setViews(Map<String, View> views) {
		this.views = views;
	}

	public void afterCompletion(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, Object paramObject,
			Exception paramException) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, Object paramObject,
			ModelAndView paramModelAndView) throws Exception {
		String type = paramHttpServletRequest.getParameter(typeName);
		if(type == null || type.isEmpty()){
			return;
		}
		
		View view = views.get(type);
		if(view != null){
			paramModelAndView.setView(view);
		}
	}

	public boolean preHandle(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, Object paramObject)
			throws Exception {
		// TODO Auto-generated method stub
		return true;
	}




}
