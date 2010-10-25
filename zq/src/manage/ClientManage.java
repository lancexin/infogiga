package manage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import listener.RemoteControl;

import org.apache.log4j.Logger;

import bean.ClientBean;
import bean.UserBean;

public class ClientManage extends HttpServlet {

	private static final long serialVersionUID = -1032095656636548372L;
	private static Logger log = Logger.getLogger(ClientManage.class.getName());
	private RemoteControl god = new RemoteControl();
	
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(!checkAuthority(request, response, 0)) {
			return;
		}
		ServletContext context = getServletContext();
		String command = request.getParameter("command");
		String ip = request.getParameter("ip");
		boolean success = false;
		System.out.println(ip+":"+command);
		
		if("shutdown".equals(command)) {			
			success = !god.shutdown(ip);			
		} else if("reboot".equals(command)) {
			success = !god.reboot(ip);
		} else if("logoff".equals(command)) {
			success = !god.logoff(ip);
		} else if("check".equals(command)) {
			success = god.check(ip);
		} 
		Object o = context.getAttribute("deviceInfo");
		if(o == null) {
			context.setAttribute("deviceInfo", god.check());
		}
		ArrayList<ClientBean> list = (ArrayList<ClientBean>) context.getAttribute("deviceInfo");
		list.get(search(list, ip)).setStatus(success?1:0);
		
		response.getWriter().write("###"+ success+"###");
	}
	
	private static int search(ArrayList<ClientBean> list, String ip) {
		for(ClientBean bean: list) {
			if(ip.equals(bean.getIp())) {
				return list.indexOf(bean);
			}
		}
		return -1;
	}
	
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
