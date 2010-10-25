package listener;

import java.util.Timer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

import mail.MailTask;

/**
 * 邮件发送监听器
 * @author ya
 *
 */
public class MailListener implements ServletContextListener {

	private static final long serialVersionUID = 1L;
	private Timer timer = null;
	
	public void init(ServletConfig config) throws ServletException {		
	}

	public void contextDestroyed(ServletContextEvent arg0) {		
	}

	public void contextInitialized(ServletContextEvent e) {
		timer = new Timer(true);//设为守护线程 
		timer.schedule(new MailTask(e.getServletContext().getRealPath("report")), 0, 60*60*1000); //一小时执行一次		
	}
}
