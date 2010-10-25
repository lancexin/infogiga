package manage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import bean.UserBean;

import data.Database;

/**
 * 二维码的登记信息管理
 * @author ya
 */
public class PlanarManage extends HttpServlet {
	
	private static final long serialVersionUID = 3864547072181470661L;
	private static Logger log = Logger.getLogger(PlanarManage.class.getName());
	private Database db = new Database();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type");
		
		if("in".equals(type)) {//登记
			if(checkAuthority(request, response, 0)) {
				checkIn(request, response);
			}
		} 
		else if("out".equals(type)) {//注销
			if(checkAuthority(request, response, 0)) {
				checkOut(request, response);
			}
		}
		else if("freeCode".equals(type)) {//剩余二维码
			if(checkAuthority(request, response, 1)) {
				freeCode(request, response);
			}
		}
	}
	
	/**
	 * 登记
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void checkIn(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String planar = request.getParameter("planar");		
		
		String flag = db.insertInfo(name, gender, phone, planar)+ "";
		log.debug("插入记录："+flag);
		response.getWriter().write(flag);
	}
	
	/**
	 * 注销
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	protected void checkOut(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String id = request.getParameter("id");
		
		String flag = db.updateInfo(id)+ "";
		log.debug("更新记录："+ flag);
		response.getWriter().write(flag);
	}
	

	/**
	 * 获取剩余的二维码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void freeCode(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String freePlanar = db.getFreePlanar();
		
		response.getWriter().write(freePlanar);
	}
	
	/**
	 * 权限大于等于authority的才能执行
	 * @param request
	 * @param response
	 * @param authority
	 * @return
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
