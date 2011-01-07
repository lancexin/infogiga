package cn.infogiga.exp.dao;


import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import cindy.web.AbstractHibernateDAO;
import cn.infogiga.pojo.Soft;
import cn.infogiga.pojo.Softdownloadstat;


public class ExperienceDAO extends AbstractHibernateDAO{

	public List<Soft> getTopSoft(){
		return (List<Soft>) getHibernateTemplate().execute(new HibernateCallback(){
			public List<Soft> doInHibernate(Session session)
					throws HibernateException, SQLException {
				final Criteria c = session.createCriteria(Soft.class);
				c.createAlias("download", "d");
				c.addOrder(Order.desc("d.downloadCount"));
				c.setMaxResults(10);
				c.setFirstResult(0);
				return c.list();
			}
		});
	}
	
	public Soft getStarSoft(final Date startTime,final Date endTime){
		return (Soft) getHibernateTemplate().execute(new HibernateCallback(){
			public Soft doInHibernate(Session session)
					throws HibernateException, SQLException {
				final Criteria c = session.createCriteria(Soft.class);
				c.createAlias("download", "d");
				c.addOrder(Order.desc("d.downloadCount"));
				c.setMaxResults(1);
				c.setFirstResult(0);
				return (Soft) c.list().get(0);
			}
		});
	}
	
	public Boolean deleteTempDownloadstat(final Date beforeTime){
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Boolean doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("delete from Tempdownloadstat where addTime < ?");
				query.setDate(1, beforeTime);
				query.executeUpdate();
				//session.getSessionFactory().evict(Tempdownloadstat.class);
				return true;
			}
		});
	}
	
	public List<Softdownloadstat> getDownloadStatByDate(final Date startTime,final Date endTime){
		return (List<Softdownloadstat>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				//session.
				Criteria criteria = session.createCriteria(Softdownloadstat.class);
				criteria.add(Restrictions.between("addTime", startTime, endTime));
				//criteria.createAlias("soft", "si");
				 
				//criteria.add(Restrictions.);
				return criteria.list();
			}
		});
	}
	
}
