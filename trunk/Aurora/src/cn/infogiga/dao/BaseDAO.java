package cn.infogiga.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.infogiga.bean.BaseBean;
import cn.infogiga.bean.Customer;
import cn.infogiga.bean.Tmp;
/**
 * spring+hibernate dao泛型接口的实现类
 * @author cindy
 *
 */
public abstract class BaseDAO  extends HibernateDaoSupport implements IBaseDAO {
	
	private static final Log log = LogFactory.getLog(BaseDAO.class);
	
	protected void initDao() {
		// do nothing
	}
	
	public <T extends BaseBean> void attachClean(T instance) {
		log.debug("attaching clean Action instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/**
	 * 根据id匹配 如果有该id项则执行update ，如果没有则执行insert
	 */
	public <T extends BaseBean> void attachDirty(T instance) {
		log.debug("attaching dirty Action instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	/**
	 * 删除某个实例
	 */
	public <T extends BaseBean> void delete(T persistentInstance) {
		log.debug("deleting Action instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	/**
	 * 查找所有实例
	 */
	public <T extends BaseBean> List<T> findAll(Class<T> clazz) {
		log.debug("finding all Action instances");
		try {
			String queryString = "from "+clazz.getName();
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * 多条件查找某个实例
	 */
	public <T extends BaseBean> List<T> findByExample(T instance) {
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
	/**
	 * 单条件查找某个实例
	 */
	public <T extends BaseBean> List<T> findByProperty(Class<T> clazz,
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
	/**
	 * 
	 */
	public <T extends BaseBean> T merge(T detachedInstance) {
		log.debug("merging Action instance");
		try {
			T result = (T) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	/**
	 * 保存某个实例
	 */
	public <T extends BaseBean> void save(T transientInstance) {
		log.debug("saving Action instance");
		try {
			getHibernateTemplate().save(transientInstance);
			//return transientInstance.
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	/**
	 * 根据id查找某个实例
	 */
	public <T extends BaseBean> T findById(Integer id,Class<T> clazz) {
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
	
	/**
	 * 得到某个实例的数量
	 */
	public <T extends BaseBean> Integer getCount(final Class<T> clazz){
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria c = session.createCriteria(clazz);
				c.setProjection(Projections.rowCount()); 
				return ((Integer)c.list().get(0)).intValue();
			}
		});
	}
	

	/**
	 * 根据手机号找客户是否存在
	 * @param phoneNumber
	 * @return
	 */
	public  Customer findCustomer(String phoneNumber) {
		List<Customer> customerList = findByProperty(Customer.class, "phoneNumber", phoneNumber);
		if(customerList == null || customerList.size()<=0) {
			return null;
		}
		return customerList.get(0);
	}
}
