package manage;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import tool.Toolkit;
import tool.XmlReader;
import bean.InfoBean;
import data.Database;

/**
 * 二维码的登记信息查询
 * @author ya
 */
public class QueryManage extends HttpServlet {
	
	private static final long serialVersionUID = 3864547072181470661L;
	private static Logger log = Logger.getLogger(QueryManage.class.getName());
	private Database db = new Database();
		
	private String message = ""; //解析出来的信息
	private String planar = "";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		receive(request, response);
	}
	
	/**
	 * 接收手机端的HTTP请求
	 */
	private void receive(HttpServletRequest request, HttpServletResponse response) {
		try {
			String xmlMessage = request.getReader().readLine();//字符流方式读取
			String reMessage = "";
			log.info("接收到的xml信息："+ xmlMessage);
			resolve(Toolkit.String2Stream(xmlMessage));//解析
			reMessage = Toolkit.encode(getReMessage(planar), "GBK", "ISO-8859-1");
			response.getWriter().write(reMessage);
		} catch (IOException e) {
			log.error("HTTP请求读取失败");
		}
	}
	
	/**
	 * 从输入流中解析出xml数据
	 * @param in 输入流
	 */
	private void resolve(InputStream in) {		
		XmlReader reader = new XmlReader(in);
		message = reader.getData("phone");	
		
		if(Toolkit.check(message)) {
			planar = message.split("-")[5];
		}
		log.debug(message);
	}
	
	/**
	 * 获取回复给手机端的查询请求
	 * @return 二维码绑定的信息
	 */
	private String getReMessage(String planar) {
		InfoBean info = db.getInfo(planar);
		if(info == null) return "此二维码未登记";
		
		String name = info.getName();
		String gender = info.getGender();
		String phone = info.getPhone();		
		return name+";"+gender+";"+phone;
	}
}
