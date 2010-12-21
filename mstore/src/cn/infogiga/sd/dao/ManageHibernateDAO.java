package cn.infogiga.sd.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Component;

import cn.infogiga.pojo.Attachment;
import cn.infogiga.pojo.Soft;

import cindy.page.hibernate.CirteriaBean;
import cindy.page.hibernate.CriteriaFactory;
import cindy.web.AbstractHibernateDAO;

public class ManageHibernateDAO extends AbstractHibernateDAO{
	public  <T extends Serializable> List<T> getListByPage(final Class<T> clazz,final CirteriaBean cBean){
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				CriteriaFactory factory = CriteriaFactory.getCriteriaFactory();
				//Criteria criteria = factory.get(cBean, session,CriteriaFactory.LIST);
				Criteria criteria = session.createCriteria(clazz);
				criteria = factory.get(cBean, criteria, CriteriaFactory.LIST);
				return criteria.list();
			}
		});
	}
	
	public <T extends Serializable> Integer getCountByPage(final Class<T> clazz,final CirteriaBean cBean){
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				CriteriaFactory factory = CriteriaFactory.getCriteriaFactory();
				Criteria criteria = session.createCriteria(clazz);
				criteria = factory.get(cBean, criteria, CriteriaFactory.COUNT);
				//System.out.println(criteria.uniqueResult());
				return ((Integer) criteria.uniqueResult()).intValue();
			}
		});
	}
	
	public void deleteAll(Collection collection){
		getHibernateTemplate().deleteAll(collection);
	}
	
	public List<Soft> searchSoft(final int menuId,final int phonetypeId,final int start,final int limit){
		return (List<Soft>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				//Criteria criteria = session.createCriteria(Attachment.class);
				//criteria.setProjection(arg0)
				String hql = "from Soft s where (s.id in ("+
					"select a.soft.id from Attachment a where (a.id in ("+
							"select ad.attachment.id from Attachandarray ad where ad.phonearray.id in ("+
									"select ar.phonearray.id from Phonetype ar where ar.id =?"+
									")))) or -1 =?) and"+
				"(s.id in (select si.soft.id from Softindex si where si.softmenu.id =?) or -1 = ?)";
				Query query = session.createQuery(hql);
				query.setInteger(0, phonetypeId);
				query.setInteger(1, phonetypeId);
				query.setInteger(2, menuId);
				query.setInteger(3, menuId);
				query.setFirstResult(start);
				query.setMaxResults(limit);

				return query.list();
			}
		});
	}

	public Long searchSoftCount(final int menuId,final int phonetypeId){
		return (Long) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				//Criteria criteria = session.createCriteria(Attachment.class);
				//criteria.setProjection(arg0)
				String hql = "select count(s.id) from Soft s where (s.id in ("+
					"select a.soft.id from Attachment a where (a.id in ("+
							"select ad.attachment.id from Attachandarray ad where ad.phonearray.id in ("+
									"select ar.phonearray.id from Phonetype ar where ar.id =?"+
									")))) or -1 =?) and"+
				"(s.id in (select si.soft.id from Softindex si where si.softmenu.id =?) or -1 = ?)";
				Query query = session.createQuery(hql);
				query.setInteger(0, phonetypeId);
				query.setInteger(1, phonetypeId);
				query.setInteger(2, menuId);
				query.setInteger(3, menuId);
				return query.uniqueResult();
			}
		});
	}
	
	public Attachment getAttachment(final int phonetypeId,final int softId){
		return (Attachment) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				//Criteria criteria = session.createCriteria(Attachment.class);
				//criteria.setProjection(arg0)
				String hql = "select at.* from attachment at where at.softID = ? and at.id in ("+
					"select ar.attachmentID from attachandarray ar where ar.arrayID = ( select pb.phonearrayID from phonetype pb where pb.id = ?));";
				Query query = session.createQuery(hql);
				query.setInteger(0, softId);
				query.setInteger(1, phonetypeId);
				return query.uniqueResult();
			}
		});
	}
}
