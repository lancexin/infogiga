package cn.infogiga.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import cn.infogiga.bean.Application;
import cn.infogiga.bean.Comment;
import cn.infogiga.bean.Customer;
import cn.infogiga.bean.Invitation;
import cn.infogiga.bean.Manager;


public class SettingDAO extends BaseDAO {
	/**
	 * 根据组名得到Manager
	 * @param groupName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Manager> getManagerListByGroupName(final String groupName){
		return (List<Manager>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(final Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("FROM Manager as m WHERE m.groups.groupID in (SELECT g.groupID FROM Groups as g WHERE g.groupName = :gName)");
				query.setString("gName", groupName);
				return query.list();
			}
		});
	}
	/**
	 * Comment 根据条件分页查询反馈信息 
	 * @param fromTime 查询开始时间
	 * @param toTime 查询结束时间
	 * @param phoneNumber 电话号码
	 * @param company 公司
	 * @param name 姓名
	 * @param pageCount 每页显示页数
	 * @param page 当前页面
	 * @return List<Comment>
	 * @author cindy
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> findCommentByPage(final Date fromTime,final Date toTime,final String phoneNumber,final String company,final String name,final int pageCount,final int page){
		return (List<Comment>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(final Session session)
					throws HibernateException, SQLException {
				
				Criteria crit = session.createCriteria(Comment.class).createAlias("customer", "b");
				
				//根据反馈时间查询
				if(fromTime != null && toTime != null){
					crit.add(Restrictions.between("receiveTime", fromTime, toTime));
				}
				//根据电话号码查询
				if(phoneNumber != null && !"".equals(phoneNumber)){
					crit.add(Restrictions.like("b.phoneNumber", "%"+phoneNumber+"%"));
				}
				//根据公司查询
				if(company != null && !"".equals(company)){
					crit.add(Restrictions.like("b.company", "%"+company+"%"));
				}
				//根据姓名查询
				if(name != null && !"".equals(name)){
					crit.add(Restrictions.like("b.name", "%"+name+"%"));
				}

				return crit.setFirstResult(pageCount*page).setMaxResults(pageCount).addOrder(Order.desc("commentId")).list();
			}
		});
	}
	
	
	public Integer getManagerCountByProperty(final String propertyName,final Object value){
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria crit = session.createCriteria(Manager.class);
				crit.add(Restrictions.eq(propertyName, value));
				return ((Integer) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			}
		});
	}
	
	/**
	 * Comment 根据条件分页查询反馈信息个数 
	 * @param fromTime 查询开始时间
	 * @param toTime 查询结束时间
	 * @param phoneNumber 电话号码
	 * @param company 公司
	 * @param name 姓名
	 * @param pageCount 每页显示页数
	 * @param page 当前页面
	 * @return Integer
	 * @author cindy
	 */
	@SuppressWarnings("unchecked")
	public Integer findCommentCountByPage(final Date fromTime,final Date toTime,final String phoneNumber,final String company,final String name){
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(final Session session)
					throws HibernateException, SQLException {
				
				Criteria crit = session.createCriteria(Comment.class).createAlias("customer", "b");
				
				//根据反馈时间查询
				if(fromTime != null && toTime != null){
					crit.add(Restrictions.between("receiveTime", fromTime, toTime));
				}
				//根据电话号码查询
				if(phoneNumber != null && !"".equals(phoneNumber)){
					crit.add(Restrictions.like("b.phoneNumber", "%"+phoneNumber+"%"));
				}
				//根据公司查询
				if(company != null && !"".equals(company)){
					crit.add(Restrictions.like("b.company", "%"+company+"%"));
				}
				//根据姓名查询
				if(name != null && !"".equals(name)){
					crit.add(Restrictions.like("b.name", "%"+name+"%"));
				}
				//crit.setProjection(Projections.rowCount()); 
				//return ((Integer)crit.list().get(0)).intValue();
				return ((Integer) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			}
		});
	}
	
	/**
	 * Invitation 分页条件查询预约参观信息
	 * @param manager 此次参观的客户经理
	 * @param guider 此次藏的接待员
	 * @param technician 此次参观的维护人员
	 * @param location 此次参观的地点
	 * @param states 此次参观的状态 （以发送邀请1，未发送邀请0）
	 * @param fromCreateTime 此次参观的发起时间 起始
	 * @param toCreateTime 此次参观的发起时间 结束
	 * @param fromTime 此次参观的时间 起始
	 * @param toTime 此次参观的时间 结束
	 * @param pageCount 每页显示的页数
	 * @param page 显示第几页
	 * @return List<Invitation>
	 * @author cindy
	 */
	@SuppressWarnings("unchecked")
	public List<Invitation> findInvitationByPage(final String manager,final String guider,final String technician,final String location,final int states,final String title,final Date fromCreateTime,final Date toCreateTime,final Date fromTime,final Date toTime,final Integer pageCount,final Integer page){
		return (List<Invitation>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(final Session session)
					throws HibernateException, SQLException {
				Criteria crit = session.createCriteria(Invitation.class).setFirstResult(pageCount*page).setMaxResults(pageCount).addOrder(Order.desc("visitTime"));
				
				//以客户经理为条件查询
				if(manager != null && !"".equals(manager)){
					crit.createAlias("managerByManagerId", "b");
					crit.add(Restrictions.like("b.name", "%"+manager+"%"));
				}
				//以接待人员为条件查询
				if(guider != null && !"".equals(guider)){
					crit.createAlias("managerByGuiderId", "a");
					crit.add(Restrictions.like("a.name", "%"+guider+"%"));
				}
				//以维护人员为条件查询
				if(technician != null && !"".equals(technician)){
					crit.createAlias("managerByTechnicianId", "c");
					crit.add(Restrictions.like("c.name", "%"+technician+"%"));
				}
				//以接待地点为条件
				if(location != null && !"".equals(location)){
					crit.add(Restrictions.like("location", "%"+location+"%"));
				}
				//以接待名称为条件
				if(title != null && !"".equals(title)){
					crit.add(Restrictions.like("invitationTitle","%"+title+"%"));
				}
				//以发送状态为条件(如果为-1此条件不查询)
				if(states != -1){
					crit.add(Restrictions.eq("completeStatus", states));
				}
				//以创建时间为条件
				if(fromCreateTime != null && toCreateTime != null){
					crit.add(Restrictions.between("createTime", fromCreateTime, toCreateTime));
				}
				//以参观时间为条件
				if(fromTime != null && toTime != null){
					crit.add(Restrictions.between("visitTime", fromTime, toTime));
				}
				
				return crit.list();
			}
		});
	}
	
	/**
	 * Invitation 分页条件查询预约参观信息
	 * @param manager 此次参观的客户经理
	 * @param guider 此次参观接待员
	 * @param technician 此次参观的维护人员
	 * @param location 此次参观的地点
	 * @param states 此次参观的状态 （以发送邀请1，未发送邀请0）
	 * @param fromCreateTime 此次参观的发起时间 起始
	 * @param toCreateTime 此次参观的发起时间 结束
	 * @param fromTime 此次参观的时间 起始
	 * @param toTime 此次参观的时间 结束
	 * @param pageCount 每页显示的页数
	 * @param page 显示第几页
	 * @return List<Invitation>
	 * @author cindy
	 */
	@SuppressWarnings("unchecked")
	public Integer findInvitationCountByPage(final String manager,final String guider,final String technician,final String location,final int states,final String title,final Date fromCreateTime,final Date toCreateTime,final Date fromTime,final Date toTime){
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(final Session session)
			throws HibernateException, SQLException {
				Criteria crit = session.createCriteria(Invitation.class);
				
				//以客户经理为条件查询
				if(manager != null && !"".equals(manager)){
					crit.createAlias("managerByManagerId", "b");
					crit.add(Restrictions.like("b.name", "%"+manager+"%"));
				}
				//以接待人员为条件查询
				if(guider != null && !"".equals(guider)){
					crit.createAlias("managerByGuiderId", "a");
					crit.add(Restrictions.like("a.name", "%"+guider+"%"));
				}
				//以维护人员为条件查询
				if(technician != null && !"".equals(technician)){
					crit.createAlias("managerByTechnicianId", "c");
					crit.add(Restrictions.like("c.name", "%"+technician+"%"));
				}
				//以接待地点为条件
				if(location != null && !"".equals(location)){
					crit.add(Restrictions.like("location", "%"+location+"%"));
				}
				//以接待名称为条件
				if(title != null && !"".equals(title)){
					crit.add(Restrictions.like("invitationTitle","%"+title+"%"));
				}
				//以发送状态为条件(如果为-1此条件不查询)
				if(states != -1){
					crit.add(Restrictions.eq("completeStatus", states));
				}
				//以创建时间为条件
				if(fromCreateTime != null && toCreateTime != null){
					crit.add(Restrictions.between("createTime", fromCreateTime, toCreateTime));
				}
				//以参观时间为条件
				if(fromTime != null && toTime != null){
					crit.add(Restrictions.between("visitTime", fromTime, toTime));
				}
				return ((Integer) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			}
		});
	}
	
	/**
	 * Application 预约申请条件分页查询信息
	 * @param name 申请人姓名
	 * @param phoneNumber 申请人电话
	 * @param mail 申请人邮箱
	 * @param company 申请人公司
	 * @param mName 项目经理姓名
	 * @param fromTime 申请时间起始
	 * @param toTime 申请时间结束
	 * @param pageCount 每页显示的页数
	 * @param page 显示第几页
	 * @return List<Application>
	 * @author cindy
	 */
	@SuppressWarnings("unchecked")
	public List<Application> findApplicationByPage(final String name,final String phoneNumber,final String mail,final String company,final String mName,final Date fromTime,final Date toTime,final Integer pageCount,final Integer page){
		return (List<Application>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(final Session session)
					throws HibernateException, SQLException {
				Criteria crit = session.createCriteria(Application.class).createAlias("customer", "a").createAlias("manager", "b").setFirstResult(pageCount*page).setMaxResults(pageCount).addOrder(Order.desc("createTime"));
				
				//以申请人姓名为查询条件
				if(name != null && !"".equals(name)){
					crit.add(Restrictions.like("a.name", name));
				}
				
				//以申请人电话为查询条件
				if(phoneNumber != null && !"".equals(phoneNumber)){
					crit.add(Restrictions.like("a.phoneNumber", "%"+phoneNumber+"%"));
				}
				
				//以申请人邮箱为查询条件
				if(mail != null && !"".equals(mail)){
					crit.add(Restrictions.like("a.mail", "%"+mail+"%"));
				}
				
				//以申请人邮箱为查询条件
				if(company != null && !"".equals(company)){
					crit.add(Restrictions.like("a.company", "%"+company+"%"));
				}
				
				//以项目经理姓名为查询条件
				if(mName != null && !"".equals(mName)){
					crit.add(Restrictions.like("b.mName", "%"+mName+"%"));
				}
				
				//以申请预约时间为条件
				if(fromTime != null && toTime != null){
					crit.add(Restrictions.between("createTime", fromTime, toTime));
				}
				
				return crit.list();
			}
		});
	}
	
	/**
	 * Application 预约申请条件分页查询信息个数
	 * @param name 申请人姓名
	 * @param phoneNumber 申请人电话
	 * @param mail 申请人邮箱
	 * @param company 申请人公司
	 * @param mName 项目经理姓名
	 * @param fromTime 申请时间起始
	 * @param toTime 申请时间结束
	 * @param pageCount 每页显示的页数
	 * @param page 显示第几页
	 * @return Integer
	 * @author cindy
	 */
	@SuppressWarnings("unchecked")
	public Integer findApplicationCountByPage(final String name,final String phoneNumber,final String mail,final String company,final String mName,final Date fromTime,final Date toTime){
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(final Session session)
			throws HibernateException, SQLException {
				Criteria crit = session.createCriteria(Application.class).createAlias("customer", "a").createAlias("manager", "b");
				
				//以申请人姓名为查询条件
				if(name != null && !"".equals(name)){
					crit.add(Restrictions.like("a.name", name));
				}
				
				//以申请人电话为查询条件
				if(phoneNumber != null && !"".equals(phoneNumber)){
					crit.add(Restrictions.like("a.phoneNumber", "%"+phoneNumber+"%"));
				}
				
				//以申请人邮箱为查询条件
				if(mail != null && !"".equals(mail)){
					crit.add(Restrictions.like("a.mail", "%"+mail+"%"));
				}
				
				//以申请人邮箱为查询条件
				if(company != null && !"".equals(company)){
					crit.add(Restrictions.like("a.company", "%"+company+"%"));
				}
				
				//以项目经理姓名为查询条件
				if(mName != null && !"".equals(mName)){
					crit.add(Restrictions.like("b.mName", "%"+mName+"%"));
				}
				
				//以申请预约时间为条件
				if(fromTime != null && toTime != null){
					crit.add(Restrictions.between("createTime", fromTime, toTime));
				}
				return ((Integer) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			}
		});
	}
	/**
	 * 分页条件查询客户信息
	 * @param name 客户姓名
	 * @param gender 客户性别
	 * @param phoneNumber 客户电话号码
	 * @param mail 客户邮箱
	 * @param company 客户公司
	 * @param fromLastLoginTime 客户最后登录时间 起始
	 * @param toLastLoginTime 客户最后登录时间 结束
	 * @param fromVisitTime 客户参观时间 起始
	 * @param toVisitTime 客户参观时间 结束
	 * @param sendStatus 参观邀请信息发送状态（已发生：1，未发送：0）
	 * @param guider 接待人员姓名
	 * @param manager 客户经理姓名
	 * @param technician 维护人员姓名
	 * @param pageCount 每页显示的页数
	 * @param page 显示第几页
	 * @return  List<Customer> 
	 * @author cindy
	 */
	@SuppressWarnings("unchecked")
	public List<Customer> findCustomerByPage(final String name,final String gender,final String phoneNumber,final String mail,final String company,final Date fromLastLoginTime,final Date toLastLoginTime,final Date fromVisitTime,final Date toVisitTime,final Integer sendStatus,final String guider,final String manager,final String technician,final Integer pageCount,final Integer page){
		return (List<Customer>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(final Session session)
					throws HibernateException, SQLException {
				Criteria crit = session.createCriteria(Customer.class).createAlias("invitation", "a").createAlias("a.managerByGuiderId", "b").createAlias("a.managerByManagerId", "c").createAlias("a.managerByTechnicianId", "d").setFirstResult(pageCount*page).setMaxResults(pageCount).addOrder(Order.desc("customerId"));
				
				if(name != null && !"".equals(name)){
					crit.add(Restrictions.like("name", name));
				}
				
				if(gender != null && !"".equals(gender)){
					crit.add(Restrictions.like("gender", "%"+gender+"%"));
				}
				
				if(phoneNumber != null && !"".equals(phoneNumber)){
					crit.add(Restrictions.like("phoneNumber", "%"+phoneNumber+"%"));
				}
				
				if(mail != null && !"".equals(mail)){
					crit.add(Restrictions.like("mail", "%"+mail+"%"));
				}
				
				if(company != null && !"".equals(company)){
					crit.add(Restrictions.like("company", "%"+company+"%"));
				}
				
				if(fromLastLoginTime != null && toLastLoginTime != null){
					crit.add(Restrictions.between("lastLoginTime", fromLastLoginTime, toLastLoginTime));
				}
				
				if(sendStatus != -1){
					crit.add(Restrictions.eq("sendStatus", sendStatus));
				}
				
				if(fromVisitTime != null && toVisitTime != null){
					crit.add(Restrictions.between("a.visitTime", fromVisitTime, toVisitTime));
				}
				
				if(guider != null && !"".equals(guider)){
					crit.add(Restrictions.like("b.name", "%"+guider+"%"));
				}
				
				if(manager != null && !"".equals(manager)){
					crit.add(Restrictions.like("c.name", "%"+manager+"%"));
				}
				
				if(technician != null && !"".equals(technician)){
					crit.add(Restrictions.like("d.name", "%"+technician+"%"));
				}
				
				return crit.list();
			}
		});
	}
	/**
	 * 分页条件查询客户信息个数
	 * @param name 客户姓名
	 * @param gender 客户性别
	 * @param phoneNumber 客户电话号码
	 * @param mail 客户邮箱
	 * @param company 客户公司
	 * @param fromLastLoginTime 客户最后登录时间 起始
	 * @param toLastLoginTime 客户最后登录时间 结束
	 * @param fromVisitTime 客户参观时间 起始
	 * @param toVisitTime 客户参观时间 结束
	 * @param sendStatus 参观邀请信息发送状态（已发生：1，未发送：0）
	 * @param guider 接待人员姓名
	 * @param manager 客户经理姓名
	 * @param technician 维护人员姓名
	 * @param pageCount 每页显示的页数
	 * @param page 显示第几页
	 * @return  List<Customer> 
	 * @author cindy
	 */
	@SuppressWarnings("unchecked")
	public Integer findCustomerCountByPage(final String name,final String gender,final String phoneNumber,final String mail,final String company,final Date fromLastLoginTime,final Date toLastLoginTime,final Date fromVisitTime,final Date toVisitTime,final Integer sendStatus,final String guider,final String manager,final String technician){
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(final Session session)
			throws HibernateException, SQLException {
				Criteria crit = session.createCriteria(Customer.class).createAlias("invitation", "a").createAlias("a.managerByGuiderId", "b").createAlias("a.managerByManagerId", "c").createAlias("a.managerByTechnicianId", "d");
				
				if(name != null && !"".equals(name)){
					crit.add(Restrictions.like("name", name));
				}
				
				if(gender != null && !"".equals(gender)){
					crit.add(Restrictions.like("gender", "%"+gender+"%"));
				}
				
				if(phoneNumber != null && !"".equals(phoneNumber)){
					crit.add(Restrictions.like("phoneNumber", "%"+phoneNumber+"%"));
				}
				
				if(mail != null && !"".equals(mail)){
					crit.add(Restrictions.like("mail", "%"+mail+"%"));
				}
				
				if(company != null && !"".equals(company)){
					crit.add(Restrictions.like("company", "%"+company+"%"));
				}
				
				if(fromLastLoginTime != null && toLastLoginTime != null){
					crit.add(Restrictions.between("lastLoginTime", fromLastLoginTime, toLastLoginTime));
				}
				
				if(sendStatus != -1){
					crit.add(Restrictions.eq("sendStatus", sendStatus));
				}
				
				if(fromVisitTime != null && toVisitTime != null){
					crit.add(Restrictions.between("a.visitTime", fromVisitTime, toVisitTime));
				}
				
				if(guider != null && !"".equals(guider)){
					crit.add(Restrictions.like("b.name", "%"+guider+"%"));
				}
				
				if(manager != null && !"".equals(manager)){
					crit.add(Restrictions.like("c.name", "%"+manager+"%"));
				}
				
				if(technician != null && !"".equals(technician)){
					crit.add(Restrictions.like("d.name", "%"+technician+"%"));
				}
				return ((Integer) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			}
		});
	}
	
}
