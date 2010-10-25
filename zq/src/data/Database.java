package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import tool.Config;
import tool.Toolkit;
import bean.ClientBean;
import bean.InfoBean;
import bean.MailBean;
import bean.RegionBean;
import bean.ReportBean;

/**
 * 数据库操作
 * @author ya
 *
 */
public class Database {
	
	private static Logger log = Logger.getLogger(Database.class.getName());
	
	private String url = Config.getValue("db.connection.url");
	private String user = Config.getValue("db.connection.user");
	private String pwd = Config.getValue("db.connection.pwd");
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	public Database() {
		init();
	}
	
	/**
	 * 获取当前在库的二维码绑定的所有信息
	 * @return 所有个人信息
	 */
	public ArrayList<InfoBean> getInfo() {
		String sql = "select i.*, p.planar from information i, planar p"+
				" where p.planarID = i.planarID and p.flag='1' and i.outime=''";
		ArrayList<InfoBean> info = new ArrayList<InfoBean>();
		
		try {
			log.debug(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				info.add(new InfoBean(rs.getString("checkID"), rs.getString("name"), 
						rs.getString("gender"), rs.getString("phone"), rs.getString("planar")));
			}
		} catch (SQLException e) {
			log.error("查询失败"+ e);
		}
		return info;
	}
	
	/**
	 * 根据二维码得到绑定的信息
	 * @param planar 二维码
	 * @return 信息bean
	 */
	public InfoBean getInfo(String planar) {
		String sql = "select i.*, p.planar from information i, planar p"+
				" where p.planarID = i.planarID and p.flag='1' and i.outime='' and p.planar='"+planar+"'";
		InfoBean info = null;
		
		try {
			log.debug(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				info = new InfoBean(rs.getString("checkID"), rs.getString("name"), 
						rs.getString("gender"), rs.getString("phone"), rs.getString("planar"));
			}
		} catch (SQLException e) {
			log.error("查询失败"+ e);
		}
		return info;
	}
	
	/**
	 * 插入记录
	 * @param name 名字
	 * @param gender 性别
	 * @param phone 手机号
	 * @param planar 二维码
	 * @return flag  using:二维码使用中，fail：失败，notin：二维码不存在，id：成功
	 */
	public String insertInfo(String name, String gender, String phone, String planar) {
		String[] planars = getPlanarID(planar);
		String planarID = planars[0];
		String flag = planars[1];
		
		StringBuffer bufferSql = new StringBuffer("insert into information values('");	
		String sql2 = "update planar set flag = '1' where planar = '"+ planar+ "'";
		String id = Toolkit.getNow();
		bufferSql.append(id+ "', '");
		bufferSql.append(name+ "', '");
		bufferSql.append(gender+ "', '");
		bufferSql.append(phone+ "', ");
		bufferSql.append(planarID+ ", '");
		bufferSql.append(id+ "', '')"); //开始时间
		String sql = bufferSql.toString();
		int count = 0; //插入的记录数量
		
		if ("1".equals(flag)) {	return "using";} //如果是1，直接返回
		if ("".equals(planarID)) { return "notin";} //是2，则二维码不存在
		try {
			log.debug(sql);	
			log.debug(sql2);
			stmt = conn.createStatement();	
			stmt.executeUpdate(sql2); //更新planar的标志位		
			count = stmt.executeUpdate(sql);	
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				log.error("回滚失败"+ e);
			}
			log.error("插入失败"+ e);
		}
		return count == 0?"fail":id;
	}
	
	/**
	 * 根据ID更新时间
	 * @param id checkid
	 * @return 是否更新成功
	 */
	public boolean updateInfo(String id) {
		String sql = "update information set outime='"+ 
				Toolkit.getNow()+ "' where checkID = '"+ id+ "'";
		String sql2 = "update planar set flag='0' where planarID=" +
				"(select planarID from information where checkID = '"+id+"')";
		int count = 0; //更新的记录数量
	
		try {
			log.debug(sql);
			log.debug(sql2);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql2);
			count = stmt.executeUpdate(sql);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				log.error("回滚失败"+ e);
			}
			log.error("更新失败"+ e);
		}
		return count == 0?false:true;
	}
	
	/**
	 * 保存参观记录
	 * @param equipmentCode 设备代码
	 * @param systemCode 系统代码
	 * @param planar 二维码
	 */
	public void save(String equipmentCode, String systemCode, String planar) {
		String vtime = Toolkit.getNow();
		String querySql = "select planarID from planar where planar='"+ planar+ "'";
		String updateSql = "insert into visitTable values(?, ?, ?, ?)";
		String id = ""; //二维码id
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(querySql);
			while(rs.next()) {
				id = rs.getString("planarID");
			}
			
			PreparedStatement ps = conn.prepareStatement(updateSql);
			ps.setString(1, equipmentCode);
			ps.setString(2, systemCode);
			ps.setInt(3, Integer.parseInt(id));
			ps.setString(4, vtime);
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			log.error("插入失败"+ e);
		}
	}
	
	/**
	 * 保存体验记录
	 * @param equipmentCode 设备代码
	 * @param systemCode 系统代码
	 * @param machineCode 机器代码（电脑，手机）
	 * @param operateCode 业务代码
	 * @param handCode 操作代码
	 */
	public void save(String equipmentCode, String systemCode, 
			String machineCode, String operateCode, String handCode) {
		String rtime = Toolkit.getNow();
		String sql = "insert into record values(?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, equipmentCode);
			ps.setString(2, systemCode);
			ps.setString(3, machineCode);
			ps.setString(4, operateCode);
			ps.setString(5, handCode);
			ps.setString(6, rtime);
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				log.error("回滚"+ e1);
			}
			log.error("插入失败"+ e);
		}
	}
	
	/**
	 * 登录
	 * @param userName
	 * @param password
	 * @return flag -2：没有这个用户，-1：密码错误，0：登陆成功，管理员，1：普通用户
	 */
	public int login(String userName, String password) {
		String sql = "";		
		int authority = 2; //权限
		
		try {
			stmt = conn.createStatement();
			sql = "select authority from users where userName='"+ userName+ "'";
			log.debug(sql);
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				authority = rs.getInt(1); //能查出权限，说明有这个用户存在
				log.debug("用户存在,权限为:"+authority);
			}
			else {//否则，用户不存在
				log.debug("用户不存在");
				return -2;
			}
			
			sql = "select * from users where userName='" + userName+ "' and passWord='"+ password+ "'";
			log.debug(sql);
			rs = stmt.executeQuery(sql);
			if(rs.next()) {//用户名和密码正确，则返回权限
				return authority;
			}
			else {//否则，返回密码不正确
				return -1;
			}
		} catch (SQLException e) {
			log.error("查询失败"+e);			
		}
		
		return -2;
	}
	
	/**
	 * 插入数据
	 * @param table 哪个表
	 * @param name 名字
	 * @param code 代码
	 * @return 是否正确插入
	 */
	public boolean insertData(String table, String name, String code) {
		String sql = "";
		int n = 0;
		
		if("area".equals(table) && code.length() == 4 && !name.equals(null)) {
			sql = "insert into addressTable values('"+ code+ "','"+ name+ "')";
		}
		else if("selling".equals(table) && code.length() == 8 && !name.equals(null)) {
			sql = "insert into sellingTable values('"+ code+ "','"+ name+ "','"+ code.substring(0, 4)+ "')";
		}
		else if("equipment".equals(table) && code.length() == 16 && !name.equals(null)) {
			sql = "insert into equipmentTable values('"+ code+ "','"+ name+ "','"+ 
					code.substring(0, 4)+ "','"+ code.substring(0, 8)+ "','"+ code.substring(0, 10)+ "')";
		}
		log.debug(sql);
		
		try {
			stmt = conn.createStatement();
			n = stmt.executeUpdate(sql);
			conn.commit();
		} catch (SQLException e) {
			log.error("插入失败"+e);
			return false;
		} 
		return n==0?false:true;
	}
	
	/**
	 * 获取二维码绑定的手机号码
	 * @param planar 二维码
	 * @return 手机号
	 */
	public String getPhone(String planar) {
		String sql = "select phone from information where planarID=" +
				"(select planarID from planar where planar = '"+ planar+ "') and outime=''";
		String phone = "";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				phone = rs.getString("phone");
			}
		} catch (SQLException e) {
			log.error("查询失败"+ e);
		}
		return phone;
		
	}
	
	/**
	 * 根据二维码获取ID
	 * @param planar 二维码
	 * @return 二维码ID和标志位数组
	 */
	private String[] getPlanarID(String planar) {
		String sql = "select planarID, flag from planar where planar='"+planar+"'";		
		String id = "";
		String flag = "";
	
		try {
			log.debug(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				id = rs.getString(1);
				flag = rs.getString(2);
			}
		} catch (SQLException e) {
			log.error("更新失败"+ e);
		}
		return new String[]{id, flag};
	}
	
	/**
	 * 地区的json数据
	 * @return
	 */
	public String getAddressJson() {
		return getJson("addressTable", "addressCode", "addressName");
	}

	/**
	 * 营业厅的json数据
	 * @return
	 */
	public String getSellingJson() {
		return getJson("sellingTable", "sellingCode", "sellingName");		
	}
	
	/**
	 * 区域的json数据
	 * @return
	 */
	public String getRegionJson() {
		return getJson("regionTable", "regionCode", "regionName");
	}
	
	/**
	 * 设备的json数据
	 * @return
	 */
	public String getEquipmentJson() {
		return getJson("equipmentTable", "equipmentCode", "equipmentName");
	}
	
	/**
	 * 系统的json数据
	 * @return
	 */
	public String getSystemJson() {
		return getJson("systemTable", "systemCode", "systemName");
	}
	
	/**
	 * 业务的json数据
	 * @return
	 */
	public String getOperateJson() {
		return getJson("operateTable", "operateCode", "operateName");
	}

	/**
	 * 从数据库中查出数据，转化为json格式
	 * @param tableName 表名
	 * @param key  code
	 * @param value  name
	 * @return  json
	 */
	private String getJson(String tableName, String key, String value) {
		String json = "";
		StringBuffer buffer1 = new StringBuffer("{'").append(key).append("':[");
		StringBuffer buffer2 = new StringBuffer("'").append(value).append("':[");
		String sql = "select "+ key+ ", "+ value+ " from "+ tableName;
		int i = 0;
	
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				buffer1.append(i==0?"'"+ rs.getString(1)+ "'":",'"+ rs.getString(1)+ "'");
				buffer2.append(i==0?"'"+ rs.getString(2)+ "'":",'"+ rs.getString(2)+ "'");
				i++;
			}
			buffer1.append("],");
			buffer2.append("]}");
		} catch (SQLException e) {
			log.error("查询失败"+e);
		} 
		
		json = buffer1.append(buffer2).toString();	
		log.debug(json);
		return json;
	}
	
	/**
	 * 获取所有空余的二维码
	 * @return arraylist
	 */
	public String getFreePlanar() {
		String sql = "select top 10 planar from planar where flag=0";
		StringBuffer buffer = new StringBuffer("{'planar':[");
		String json = "";
		int i = 0;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				buffer.append(i==0?"'"+ rs.getString(1)+ "'":",'"+ rs.getString(1)+ "'");
				i++;
			}
			buffer.append("]}");
		} catch (SQLException e) {
			log.error("查询失败"+e);
		} 
		
		json = buffer.toString();
		return json;
	}
	
	/**
	 * 获取区域代码列表
	 * @return
	 */
	public ArrayList<RegionBean> getRegions() {
		String sql = "select regionCode, regionName from regionTable";
		ArrayList<RegionBean> regions = new ArrayList<RegionBean>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				regions.add(new RegionBean(rs.getString("regionCode"), rs.getString("regionName")));
			}
		} catch (SQLException e) {
			log.error("查询失败"+e);	
		}
		return regions;
	}
	
	/**
	 * 根据区域代码获取区域名字
	 * @param regionCode
	 * @return
	 */
	public String getRegionName(String regionCode) {
		String sql = "select regionName from regionTable where regionCode = '"+ regionCode+ "'";
		String regionName = "";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				regionName = rs.getString("regionName");
			}
		} catch (SQLException e) {
			log.error("查询失败"+e);	
		}
		return regionName;
	}
	
	/**
	 * 获取此区域的模板名字
	 * @param regionCode
	 * @return
	 */
	public String getTemplateName(String regionCode) {
		String sql = "select templateName from regionTable where regionCode = '"+ regionCode+ "'";
		String template = "";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				template = rs.getString("templateName");
			}
		} catch (SQLException e) {
			log.error("查询模板名字失败"+e);	
		}
		return template;
	}
	
	/**
	 * 根据区域代码获取短信内容
	 * @param regionCode
	 * @return 短信内容
	 */
	public String getSms(String regionCode) {
		String sql = "select sms from regionTable where regionCode = '"+ regionCode+ "'";
		String sms = "";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				sms = rs.getString("sms");
			}
		} catch (SQLException e) {
			log.error("查询短信内容失败"+e);	
		}
		return sms;
	}
	
	/**
	 * 根据区域代码更新短信内容
	 * @param regionCode 区域代码
	 * @param sms 短信内容
	 * @return
	 */
	public boolean updateSms(String regionCode, String sms) {
		String sql = "update regionTable set sms='"+ sms+ "' where regionCode='"+ regionCode+ "'";
		boolean success = false;
		
		try {
			stmt = conn.createStatement();
			success = stmt.executeUpdate(sql)==0?false:true;
			conn.commit();
		} catch (SQLException e) {
			log.error("更新短信内容失败"+e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				log.error("回滚失败"+e);
			}
		}
		return success;
	}
	
	/**
	 * 取最后一行数据
	 * @return mailBean
	 */
	public MailBean getMailBean(){
		String sql = "select top 1 * from mailTable order by id desc";
		MailBean bean = new MailBean();
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				bean.setSmtpHost(rs.getString("smtpHost"));
				bean.setUserName(rs.getString("userName"));
				bean.setPassword(rs.getString("password"));
				bean.setFrom(rs.getString("mfrom"));
				bean.setTo(rs.getString("mto"));
				bean.setHour(rs.getString("sendTime"));
				bean.setCyc(rs.getString("sendCyc"));
				bean.setFlag(rs.getString("isSend"));
			}
		} catch (SQLException e) {
			log.error("获取mail配置失败"+e);
		}
		
		return bean;
	}
	
	/**
	 * 插入一条mail表配置记录
	 * @param bean
	 * @return 是否更新成功
	 */
	public boolean configMail(MailBean bean) {
		String sql = "insert into mailTable values(?,?,?,?,?,?,?,?)";
		boolean success = false;
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, bean.getSmtpHost());
			ps.setString(2, bean.getUserName());
			ps.setString(3, bean.getPassword());
			ps.setString(4, bean.getFrom());
			ps.setString(5, bean.getTo());
			ps.setString(6, bean.getHour());
			ps.setString(7, bean.getCyc());
			ps.setString(8, bean.getFlag());
			success = ps.execute();
			conn.commit();
		} catch (SQLException e) {
			log.error("插入mail表失败"+e);			
		}
		return success;
	}
	
	/**
	 * 根据id获取client
	 * @param id
	 * @return
	 */
	public String getClient(String id) {
		  String sql = "select * from clientTable where eid=?";		 
		  StringBuffer json = new StringBuffer("{");
		  
		  try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				json.append("eid:'").append(rs.getInt("eid")).append("',");
				json.append("ename:'").append(rs.getString("ename")).append("',");
				json.append("subject:'").append(rs.getString("subject")).append("',");
				json.append("ip:'").append(rs.getString("ip")).append("'");
			}
			json.append("}");
			log.debug(json.toString());
		  } catch (SQLException e) {
			  log.error("获取设备失败"+e);
		  }
		  
		  return json.toString();
	}
	
	/**
	 * 获取所有客户机器列表
	 * @return
	 */
	public ArrayList<ClientBean> getClients() {
		ArrayList<ClientBean> clients = new ArrayList<ClientBean>();
		String sql = "select * from clientTable";
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				clients.add(
						new ClientBean(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4))
				);
			}
		} catch (SQLException e) {
			log.error("查询设备表失败"+e);
		}
		return clients;
	}
	
	/**
	 * 添加客户端机器
	 * @param client
	 * @return
	 */
	public boolean addClient(ClientBean client) {
		String sql = "insert into clientTable values(?,?,?,?)";
		boolean success = false;
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, client.getId());
			ps.setString(2, client.getName());
			ps.setString(3, client.getSubject());
			ps.setString(4, client.getIp());
			success = ps.execute();
			conn.commit();
		} catch (SQLException e) {
			log.error("插入client表失败"+e);
		}
		return success;
	}
	
	/**
	 * 删除eid是id的记录
	 * @param id
	 * @return
	 */
	public boolean deleteClient(String id) {
		String sql = "delete from clientTable where eid="+ id;
		boolean success = false;
		
		try {
			stmt = conn.createStatement();
			success = stmt.executeUpdate(sql)==0?false:true;
			conn.commit();
		} catch (SQLException e) {
			log.error("删除client表记录失败"+e);
		}
		
		return success;
	}
	
	/**
	 * 更新此机器信息
	 * @param client bean
	 * @return
	 */
	public boolean updateClient(ClientBean client) {
		String sql = "update clientTable set ename=?,subject=?,ip=? where eid=?";
		boolean success = false;
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, client.getName());
			ps.setString(2, client.getSubject());
			ps.setString(3, client.getIp());
			ps.setInt(4, client.getId());
			success = ps.execute();
			conn.commit();
		} catch (SQLException e) {
			log.error("更新client表记录失败"+e);
		}
		
		return success;
	}
	
	/**
	 * 查询
	 * @param addressCode
	 * @param sellingCode
	 * @param equipmentCode
	 * @param systemCode
	 * @param operateCode
	 * @param startTime
	 * @param endTime
	 * @param mobile
	 * @return
	 */
	public ArrayList<ReportBean> getReport(String addressCode, String sellingCode, String regionCode,
										String equipmentCode, String systemCode, String operateCode,
										String startTime, String endTime, String mobile) {
		ArrayList<ReportBean> list = new ArrayList<ReportBean>();
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer("select a.addressName, s.sellingName,\n").append(
					"e.equipmentName, o.operateName, h.handName, r.rtime\n").append(
					"from record r,equipmentTable e, addressTable a,		\n").append(
					"sellingTable s, systemTable sys, operateTable o, hand h\n").append(
					"where r.equipmentCode=e.equipmentCode					\n").append(
					"and r.systemCode = sys.systemCode						\n").append(
					"and r.operateCode=o.operateCode						\n").append(
					"and r.handCode = h.handCode							\n").append(
					"and e.addressCode = a.addressCode						\n").append(
					"and e.sellingCode = s.sellingCode						\n");
		if(isValue(addressCode)) {
			sql.append("and a.addressCode = '"+ addressCode+ "' \n");
		}
		if(isValue(sellingCode)) {
			sql.append("and s.sellingCode = '"+ sellingCode+ "' \n");
		}
		if(isValue(regionCode)) {
			sql.append("and e.regionCode = '"+ regionCode+ "' \n");
		}
		if(isValue(equipmentCode)) {
			sql.append("and e.equipmentCode = '"+ equipmentCode+ "' \n");
		}
		if(isValue(systemCode)) {
			sql.append("and sys.systemCode = '"+ systemCode+ "' \n");
		}
		if(isValue(operateCode)) {
			sql.append("and o.operateCode = '"+ operateCode+ "' \n");
		}
		if(isValue(startTime)) {
			sql.append("and r.rtime > '"+ startTime+ "' \n");
		}
		if(isValue(endTime)) {
			sql.append("and r.rtime < '"+ endTime+ "' \n");
		}
		sql.append("order by a.addressName,s.sellingName,e.equipmentName,o.operateName,h.handName");
				
		try {
			log.debug(sql);
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(
						new ReportBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)
								,rs.getString(5),rs.getString(6)));
			}
		} catch (SQLException e) {
			log.error("查询报表失败"+e);
		} 
		return list;
	}
	
	/**
	 * str是否有值
	 * @param str
	 * @return
	 */
	private boolean isValue(String str) {
		if(str == null) {
			return false;
		}
		if("".equals(str)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 初始化数据库
	 */
	private void init() {
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pwd);
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			log.error("驱动类未找到");			
		} catch (SQLException e) {
			log.error("创建数据库连接失败"+ e);
		}
	}
}
