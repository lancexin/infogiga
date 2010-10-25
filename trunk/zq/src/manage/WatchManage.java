package manage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import tool.Config;

/**
 * 流量监控
 * @author ya
 */
public class WatchManage extends HttpServlet {

	private static final long serialVersionUID = -6018898017471436405L;
	private static Logger log = Logger.getLogger(WatchManage.class.getName());
	private String traffic1 = Config.getValue("traffic1.name");
	private String traffic2 = Config.getValue("traffic2.name");
	private String traffic3 = Config.getValue("traffic3.name");
	private String traffic4 = Config.getValue("traffic4.name");
	private String message1 = "X_0_0";
	private String message2 = "Y_0_0";
	private String message3 = message1;
	private String message4 = message2;
	private String message; //返回的消息
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		
		if(type == null) {
			doRead(request);
		}
		else if("watch".equals(type)) {
			String index = request.getParameter("index");
			doWatch(request, response, index);
		}
	}
	
	/**
	 * 读取post过来的流量信息
	 * @param request
	 * @throws IOException
	 */
	protected void doRead(HttpServletRequest request) throws IOException {
		String client = request.getRemoteAddr();
		log.debug("remote addr:"+ client);
		
		message = request.getReader().readLine();
		match(message.split("_")[0]);		
	}
	
	/**
	 * 处理页面请求的方法
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void doWatch(HttpServletRequest request, HttpServletResponse response, String index) throws IOException {
		log.debug("页面请求一次"+index);
		int i = Integer.parseInt(index);
		switch(i) {
		case 1:message=message1+","+message2;break;
		case 2:message=message3+","+message4;break;
		}
		response.getWriter().write(message);
	}	
	
	/**
	 * 跟配置文件中的名称比较
	 * @param traffic
	 */
	private void match(String traffic) {
		if(traffic.equals(traffic1)) {
			message1 = message;
		} else if(traffic.equals(traffic2)) {
			message2 = message;
		} else if(traffic.equals(traffic3)) {
			message3 = message;
		} else if(traffic.equals(traffic4)) {
			message4 = message;
		} else {
			log.warn("未找到匹配的设备名"+traffic);
		}
	}
}
