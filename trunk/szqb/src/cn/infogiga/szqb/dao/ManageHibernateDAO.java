package cn.infogiga.szqb.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import cindy.page.hibernate.CirteriaBean;
import cindy.page.hibernate.CriteriaFactory;
import cindy.springframework.dao.AbstractHibernateDAO;

public class ManageHibernateDAO extends AbstractHibernateDAO{
	public  <T extends Serializable> List<T> getListByPage(final Class<T> clazz,final CirteriaBean cBean){
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				//session.createSQLQuery(arg0)
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
}
