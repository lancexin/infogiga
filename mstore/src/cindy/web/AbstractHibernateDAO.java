
package cindy.web;

import java.io.Serializable;
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

/**
 * 该方法依赖于Hibernate3以及spring2.5以上
 * @author cindy
 *
 */
public abstract class AbstractHibernateDAO extends HibernateDaoSupport implements DAO{

	private static final Log log = LogFactory.getLog(AbstractHibernateDAO.class);
	
	public <T extends Serializable> void delete(T persistentInstance) {
		log.debug("deleting Action instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public <T extends Serializable> List<T> findAll(Class<T> clazz) {
		log.debug("finding all Action instances");
		try {
			String queryString = "from "+clazz.getName();
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public <T extends Serializable> List<T> findByExample(T instance) {
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

	public <T extends Serializable> T findById(Class<T> clazz,Integer id) {
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

	public <T extends Serializable> List<T> findByProperties(final Class<T> clazz,
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
	
	public <T extends Serializable> T findSingleByProperties(final Class<T> clazz,
			final Map<String,Object> properties) {
		return (T) getHibernateTemplate().execute(new HibernateCallback(){
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
				return c.uniqueResult();
			}
		});
	}
	
	public <T extends Serializable> T findSingleByProperty(final Class<T> clazz,
			final String propertyName,final Object value) {
		return (T) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				final Criteria c = session.createCriteria(clazz);
				c.add(Restrictions.eq(propertyName, value));

				return c.uniqueResult();
			}
		});
	}

	public <T extends Serializable> List<T> findByProperty(Class<T> clazz,
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

	public <T extends Serializable> T findSingleByExample(final T t) {
		return (T) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				final Criteria c = session.createCriteria(t.getClass());
				c.add(Example.create(t));
				return c.uniqueResult();
			}
		});
	}

	public <T extends Serializable> Integer getCount(final Class<T> clazz) {
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria c = session.createCriteria(clazz);
				c.setProjection(Projections.rowCount()); 
				return ((Integer)c.list().get(0)).intValue();
			}
		});
	}

	public <T extends Serializable> Integer getCountByExample(final T t) {
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

	public <T extends Serializable> Integer getCountByProperties(final Class<T> clazz,
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

	public <T extends Serializable> Integer getCountByProperty(final Class<T> clazz,
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

	public <T extends Serializable> void update(T instance) {
		log.debug("attaching dirty Action instance");
		try {
			getHibernateTemplate().update(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public <T extends Serializable> void queryDelete(List<T> list) {
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

	public <T extends Serializable> void querySave(List<T> list) {
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

	public <T extends Serializable> void save(T transientInstance) {
		log.debug("saving Action instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public <T extends Serializable> void saveOrUpdate(T instance) {
		log.debug("attaching dirty Action instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public <T extends Serializable> List<T> getListByPage(final Class<T> clazz,final Integer start,final Integer limit){
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				final Criteria c = session.createCriteria(clazz);
				c.setMaxResults(limit);
				c.setFirstResult(start);
				return c.list();
			}
		});
	}

	public <T extends Serializable> List<T> getListByProperty(final Class<T> clazz,final String property,final Object value,final Integer start,final Integer limit){
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				final Criteria c = session.createCriteria(clazz);
				c.add(Restrictions.eq(property, value));
				c.setMaxResults(limit);
				c.setFirstResult(start);
				return c.list();
			}
		});
	}

	public <T extends Serializable> List<T> getListByProperties(final Class<T> clazz,final Map<String,Object> properties,final Integer start,final Integer limit){
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				final Criteria c = session.createCriteria(clazz);
				Set<String> keySet = properties.keySet();
				Iterator<String> ite = keySet.iterator();
				while(ite.hasNext()){
					String key = ite.next();
					c.add(Restrictions.eq(key, properties.get(key)));
				}
				c.setMaxResults(limit);
				c.setFirstResult(start);
				return c.list();
			}
		});
	}
}
