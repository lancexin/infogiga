package listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogListener extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static Logger log = Logger.getLogger(LogListener.class);

	public LogListener() {
	}

	public void init(ServletConfig config) throws ServletException {
		String prefix = config.getServletContext().getRealPath("/");

		String properties = config.getInitParameter("log4j_properties");
		String logger = config.getInitParameter("logFile");
		String errors = config.getInitParameter("errorFile");
		
		String logFile = (prefix + logger).replace('\\', '/');// 设置日志文件路径
		String errorFile = (prefix + errors).replace('\\', '/');//错误日志文件路径
		
		String propertiesPath = prefix + properties;
		Properties props = new Properties();
		
		try {
			FileInputStream istream = new FileInputStream(propertiesPath);
			props.load(istream);
			istream.close();
			
			props.setProperty("log4j.appender.D.File", logFile);
			props.setProperty("log4j.appender.E.File", errorFile);

			PropertyConfigurator.configure(props);// 装入log4j配置信息

			log.info("LOG被加载 日志文件位置："
					+ props.getProperty("log4j.appender.D.File"));
			log.info("error LOG被加载 日志文件位置："
					+ props.getProperty("log4j.appender.E.File"));

		} catch (IOException ioe) {
			log.error(ioe);
			return;
		}
	}
}
