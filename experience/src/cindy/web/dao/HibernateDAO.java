
package cindy.web.dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cindy.web.pojo.POJO;
/**
 * 该方法依赖于Hibernate3以及spring2.5以上
 * @author cindy
 *
 */
public abstract class HibernateDAO extends HibernateDaoSupport implements DAO{

	private static final Log log = LogFactory.getLog(HibernateDAO.class);
	
	public <T extends POJO> void delete(T persistentInstance) {
		log.debug("deleting Action instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public <T extends POJO> List<T> findAll(Class<T> clazz) {
		log.debug("finding all Action instances");
		try {
			String queryString = "from "+clazz.getName();
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public <T extends POJO> List<T> findByExample(T instance) {
		log.debug("finding Action instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public <T extends POJO> T findById(Class<T> clazz,Integer id) {
		log.debug("getting Action instance with id: " + id);
		try {
			T instance = (T) getHibernateTemplate().get(
					clazz.getName(), id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public <T extends POJO> List<T> findByProperties(final Class<T> clazz,
			final Map<String,Object> properties) {
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				final Criteria c = session.createCriteria(clazz);
				
				Set<String> keys = properties.keySet();
				Iterator<String> ite = keys.iterator();
				while(ite.hasNext()){
					String key = ite.next();
					Object property = properties.get(key);
					c.add(Restrictions.eq(key, property));
				}
				return c.list();
			}
		});
	}

	public <T extends POJO> List<T> findByProperty(Class<T> clazz,
			String propertyName, Object value) {
		log.debug("finding Action instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from "+clazz.getName()+" as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public <T extends POJO> T findSingleByExample(final T t) {
		return (T) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				final Criteria c = session.createCriteria(t.getClass());
				c.add(Example.create(t));
				return c.uniqueResult();
			}
		});
	}

	public <T extends POJO> Integer getCount(final Class<T> clazz) {
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria c = session.createCriteria(clazz);
				c.setProjection(Projections.rowCount()); 
				return ((Integer)c.list().get(0)).intValue();
			}
		});
	}

	public <T extends POJO> Integer getCountByExample(final T t) {
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				final Criteria c = session.createCriteria(t.getClass());
				c.add(Example.create(t));
				c.setProjection(Projections.rowCount());
				return c.uniqueResult();
			}
		});
	}

	public <T extends POJO> Integer getCountByProperties(final Class<T> clazz,
			final Map<String,Object> properties) {
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				final Criteria c = session.createCriteria(clazz);
				
				Set<String> keys = properties.keySet();
				Iterator<String> ite = keys.iterator();
				while(ite.hasNext()){
					String key = ite.next();
					Object property = properties.get(key);
					c.add(Restrictions.eq(key, property));
				}
				c.setProjection(Projections.rowCount());
				return c.uniqueResult();
			}
		});
	}

	public <T extends POJO> Integer getCountByProperty(final Class<T> clazz,
			final String property,final Object value) {
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				final Criteria c = session.createCriteria(clazz);
				c.add(Restrictions.eq(property, value));
				c.setProjection(Projections.rowCount());
				return c.uniqueResult();
			}
		});
	}

	public <T extends POJO> void update(T instance) {
		log.debug("attaching dirty Action instance");
		try {
			getHibernateTemplate().update(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public <T extends POJO> void queryDelete(List<T> list) {
		ListIterator<T> ite = list.listIterator();
		while(ite.hasNext()){
			T t = ite.next();
			try {
				getHibernateTemplate().delete(t);
				log.debug("delete successful");
			} catch (RuntimeException re) {
				log.error("delete failed", re);
				throw re;
			}
		}
	}

	public <T extends POJO> void querySave(List<T> list) {
		ListIterator<T> ite = list.listIterator();
		while(ite.hasNext()){
			T t = ite.next();
			try {
				getHibernateTemplate().save(t);
				log.debug("save successful");
			} catch (RuntimeException re) {
				log.error("save failed", re);
				throw re;
			}
		}
	}

	public <T extends POJO> void save(T transientInstance) {
		log.debug("saving Action instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public <T extends POJO> void saveOrUpdate(T instance) {
		log.debug("attaching dirty Action instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

}
