package cn.infogiga.exp.dao;


import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;

import cindy.web.AbstractHibernateDAO;
import cn.infogiga.pojo.Soft;


public class ExperienceDAO extends AbstractHibernateDAO{

	public List<Soft> getTopSoft(){
		return (List<Soft>) getHibernateTemplate().execute(new HibernateCallback(){
			public List<Soft> doInHibernate(Session session)
					throws HibernateException, SQLException {
				final Criteria c = session.createCriteria(Soft.class);
				c.addOrder(Order.desc("download.downloadCount"));
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
				c.addOrder(Order.desc("download.downloadCount"));
				c.setMaxResults(1);
				c.setFirstResult(0);
				return (Soft) c.list().get(0);
			}
		});
	}
	
	
}
