package cn.infogiga.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ��ݿ����
 * @author ya
 *
 */
public class Database {
	
	private static Log log = LogFactory.getLog(Database.class);
	
	private String url = "jdbc:jtds:sqlserver://localhost:1433/zq";
	private String user = "sa";
	private String pwd = "sa";
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	public Database() {
		init();
	}
	
	public ArrayList<String> getLastQrcode() {
		try {
			ArrayList<String> qrcodes = new ArrayList<String>();
			String sql = "select top 10 mmsCode from Qrcode order by codeID desc";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				qrcodes.add(rs.getString("mmsCode"));
			}
			return qrcodes;
		} catch (SQLException e) {
			log.error(""+ e);
		}		
		return null;
	}
	
	/**
	 * ��ʼ����ݿ�
	 */
	private void init() {
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pwd);
//			conn.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			log.error(""+ e);			
		} catch (SQLException e) {
			log.error("������ݿ�l��ʧ��"+ e);
		}
	}
}
