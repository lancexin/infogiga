package cn.infogiga.dao;

import java.util.Calendar;
import java.util.List;

import cn.infogiga.bean.Application;
import cn.infogiga.bean.Comment;
import cn.infogiga.bean.Customer;
import cn.infogiga.bean.Groups;
import cn.infogiga.bean.Manager;
import cn.infogiga.bean.Mmstemplate;
import cn.infogiga.bean.Qrcode;
import cn.infogiga.bean.Setting;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.sun.istack.FinalArrayList;

import cn.infogiga.bean.Invitation;
import cn.infogiga.util.StringToolkit;

public class AppointmentDAO extends BaseDAO{
	private static Log log = LogFactory.getLog(AppointmentDAO.class); 
	
	/**
	 * 查看某个时间段内的Invitation信息
	 * @param startDate
	 * @param endDate
	 * @return List<Invitation>
	 * @author cindy
	 */
	@SuppressWarnings("unchecked")
	public List<Invitation> getInvitationByTime(final Date startDate,final Date endDate){
		return (List<Invitation>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(final Session session)
					throws HibernateException, SQLException {
				Criteria crit = session.createCriteria(Invitation.class).add(Restrictions.between("visitTime", startDate, endDate)).addOrder(Order.desc("visitTime"));

				return crit.list();
			}
		});
	}	
	
	/**
	 * 某个时间段的意见反馈
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> getCommentByTime(final Date startDate, final Date endDate) {
		return (List<Comment>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(final Session session)
					throws HibernateException, SQLException {
				Criteria crit = session.createCriteria(Comment.class).add(Restrictions.between("receiveTime", startDate, endDate)).addOrder(Order.desc("receiveTime"));

				return crit.list();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> getComments() {
		return (List<Comment>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(final Session session)
					throws HibernateException, SQLException {
				Criteria crit = session.createCriteria(Comment.class).addOrder(Order.desc("receiveTime"));

				return crit.list();
			}
		});
	}
	
	/**
	 * 所有的预约
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Invitation> getInvitations() {
		return (List<Invitation>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(final Session session)
					throws HibernateException, SQLException {
				Criteria crit = session.createCriteria(Invitation.class).addOrder(Order.desc("visitTime"));

				return crit.list();
			}
		});
	}
	
	/**
	 * 指定id的邀请所包含的客户
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Customer> getCustomersForInvitation(final Integer id) {
		return (List<Customer>) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "from Customer c where c.invitation.invitationId = "+ id;
				Query query = session.createQuery(sql);
				return query.list();
			}			
			
		});
	}
	
	/**
	 * 获得某个时间段的 Invitation 的数量
	 * @param startDate
	 * @param endDate
	 * @return Integer
	 * @author cindy
	 */
	public Integer getInvitationCountByTime(final Date startDate,final Date endDate){
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(final Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("select count(*) as count from Invitation as i where createTime between :startTime and :endTime");
				query.setDate("startTime", startDate);
				query.setDate("endTime", endDate);
				int count=((Number)query.iterate().next()).intValue(); 
				return  count;
			}
		});
	}
	
	/**
	 * 得到某个时间段的申请列表
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Application> getApplications(final Date startTime, final Date endTime) {
		return (List<Application>) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria crit = session.createCriteria(Application.class).add(Restrictions.between("createTime", startTime, endTime)).addOrder(Order.desc("createTime"));
				/*String queryString = "from Application as a where a.createTime between :startTime and :endTime";
				Query query = session.createQuery(queryString);
				query.setDate("startTime", startTime);
				query.setDate("endTime", endTime);		*/		
				return crit.list();
			}
			
		});
	}
	
	/**
	 * 某个时间段访问过的用户列表
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Customer> getVisitedCustomer(final Date startTime, final Date endTime) {
		return (List<Customer>) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String queryString = "from Customer as c,Qrcode as q,Visit as v where v.qrcode.customer.customerId = c.customerId and v.visitTime between :startTime and :endTime";
				Query query = session.createQuery(queryString);
				query.setDate("startTime", startTime);
				query.setDate("endTime", endTime);
				return query.list();
			}
			
		});
	}
	
	public Setting getDefaultSetting() {
		return findById(1, Setting.class);
	}

	@SuppressWarnings("unchecked")
	public List<Application> getApplications() {
		return (List<Application>) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria crit = session.createCriteria(Application.class).addOrder(Order.desc("createTime"));
				return crit.list();
			}
		});
	}
	/**
	 * 得到未读的预约请求数量
	 * @param startDate
	 * @param endDate
	 * @return
	 * @author cindy
	 */
	public Integer getUnReadApplicationCount(){
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(final Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("select count(*) as count from Application as i where status = 0");
				int count=((Number)query.iterate().next()).intValue(); 
				return  count;
			}
		});
	}
	/**
	 * 查找彩信模板的id
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int findTempleteIdByName(String name) {
		List<Mmstemplate> templateList = 
			(List<Mmstemplate>) findByProperty(Mmstemplate.class, "templateName", name);
		return templateList.get(0).getMmstemplateId();
	}
	
	/**
	 * 根据二维码id查找对应的客户
	 * @param qrcodeId
	 * @return
	 */
	public Customer findCustomerForQrcode(int qrcodeId) {		
		Qrcode qrcode = findById(qrcodeId, Qrcode.class);
		if(qrcode == null) return null;
		Customer customer = findById(qrcode.getCustomer().getCustomerId(), Customer.class);

		return customer;
	}
	
	/**
	 * 根据手机号查询客户
	 * @param phoneNumber
	 * @return
	 */
	public Customer findCustomerByPhoneNumber(String phoneNumber) {
		List<Customer> list = findByProperty(Customer.class, 
				"phoneNumber", phoneNumber);
		if(list == null || list.size() <= 0) {
			return null;
		}
		return list.get(0);
	}	
	
	/**
	 * 根据key查询客户经理
	 * @param key
	 * @param value
	 * @return
	 */
	public Manager findManager(String key, String value) {
		List<Manager> managers = findByProperty(
				Manager.class, key, value);
		if(managers == null || managers.size() <= 0) {
			return null;
		}
		return managers.get(0);
	}
	
	/**
	 * 设置客户的二维码彩信发送状态
	 * @param customer
	 * @param status
	 */
	public void setSendStatus(Customer customer, int status) {
		customer.setSendStatus(status);
		attachDirty(customer);
	}
	
	/**
	 * 设置邀请的完成状态
	 * @param invitation
	 * @param status
	 */
	public void setCompleteStatus(Invitation invitation, int status) {
		invitation.setCompleteStatus(status);
		attachDirty(invitation);
	}
	
	/**
	 * 创建一个预约请求
	 * @param customer
	 * @param reason
	 */
	public void addApplication(Customer customer, String reason) {		
		Application application = new Application();
		application.setCustomer(customer);
		application.setReason(reason);
		application.setStatus(0);
		application.setCreateTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		save(application);
	}
	
	/**
	 * 添加一个评论
	 * @param customer
	 * @param content
	 * @param ip
	 */
	public void addComment(Customer customer, String content, String ip) {
		Comment comment = new Comment();
		comment.setCustomer(customer);
		comment.setContent(content);
		comment.setIp(ip);
		comment.setReceiveTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		save(comment);
	}
	
	/**
	 * 添加一个客户
	 * @param name
	 * @param phoneNumber
	 */
	public Customer addCustomer(String name, String phoneNumber, String company) {
		Customer customer = new Customer();		
		customer.setPhoneNumber(phoneNumber);
		if(!StringToolkit.isBlank(name)) {
			customer.setName(name);
		}
		if(!StringToolkit.isBlank(company)) {
			customer.setCompany(company);
		}
		save(customer);
		return customer;
	}
	
	/**
	 * 超级管理员
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Manager> getAdmins() {
		return (List<Manager>) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String queryString = "from Manager as m where m.groups.groupId=1";
				Query query = session.createQuery(queryString);
				return query.list();
			}
			
		});
	}
	 
	public Groups findGuiderGroup() {
		return findById(3, Groups.class);
	}
	
	public Groups findTechnicianGroup() {		
		return findById(4, Groups.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Manager> findTechnicianList() {
		return (List<Manager>)getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sqlString = "from Manager m where m.groups.groupId=4";				
				return session.createQuery(sqlString).list();
			}
			
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<Manager> findGuiderList() {
		return (List<Manager>)getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sqlString = "from Manager m where m.groups.groupId=3";				
				return session.createQuery(sqlString).list();
			}
			
		});
	}
	
	public Session currentSession() {
		return getSession();
	}
}
