package cn.infogiga.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.jdbc.JDBCAppender;
import org.apache.log4j.spi.ErrorCode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jmx.HibernateService;

import com.sun.jndi.cosnaming.CNNameParser;

public class JDBCExtAppender extends JDBCAppender {
	
	private Session session;
	
	@Override
	public Connection getConnection() {
		session = HibernateUtil.getCurrentSession();
		return session.connection();
	}
	
	@Override
	public void execute(String sqlString) {
		Connection conn = getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.execute(sqlString);
			stmt.close();
		} catch (SQLException e) {
			errorHandler.error("执行sql失败", e, ErrorCode.GENERIC_FAILURE);
		}
	}
	
	@Override
	public void closeConnection(Connection connection) {
		session.disconnect();
	}
}
