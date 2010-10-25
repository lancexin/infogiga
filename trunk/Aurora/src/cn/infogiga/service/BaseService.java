package cn.infogiga.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.infogiga.bean.BaseBean;
import cn.infogiga.bean.Customer;
import cn.infogiga.dao.BaseDAO;


public abstract class BaseService implements IBaseService {
	private static final Log log = LogFactory.getLog(BaseService.class);
	
	
	public <T extends BaseBean, E extends BaseDAO> boolean delete(
			T persistentInstance, E dao) {
		// TODO Auto-generated method stub
		try {
			dao.delete(persistentInstance);
			return true;
		} catch (RuntimeException e) {
			log.error("delete error", e);
			return false;
		}
	}

	public <T extends BaseBean, E extends BaseDAO> List<T> findAll(
			Class<T> clazz, E dao) {
		// TODO Auto-generated method stub
		return dao.findAll(clazz);
	}

	public <T extends BaseBean, E extends BaseDAO> List<T> findByExample(
			T instance, E dao) {
		// TODO Auto-generated method stub
		return dao.findByExample(instance);
	}
	
	public <T extends BaseBean, E extends BaseDAO> boolean update(T t,E dao){
		try {
			dao.attachDirty(t);
			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
	}

	public <T extends BaseBean, E extends BaseDAO> T findById(Integer id,
			Class<T> clazz, E dao) {
		// TODO Auto-generated method stub
		return dao.findById(id, clazz);
	}

	public <T extends BaseBean, E extends BaseDAO> List<T> findByProperty(
			Class<T> clazz, String propertyName, Object value, E dao) {
		// TODO Auto-generated method stub
		return dao.findByProperty(clazz, propertyName, value);
	}

	public <T extends BaseBean, E extends BaseDAO> boolean save(
			T transientInstance, E dao) {
		// TODO Auto-generated method stub
		try {
			dao.save(transientInstance);
			return true;
		} catch (RuntimeException e) {
			log.error("save error", e);
			e.printStackTrace();
			return false;
		}
	}
	
	public <T extends BaseBean, E extends BaseDAO> T megre(
			T transientInstance, E dao) {
		// TODO Auto-generated method stub
		return dao.merge(transientInstance);
	}
	
	/**
	 * 获得张表中的总条数
	 * @param <T>
	 * @param <E>
	 * @param clazz
	 * @param dao
	 * @return
	 */
	public <T extends BaseBean,E extends BaseDAO> Integer getCount(Class<T> clazz,E dao){
		return dao.getCount(clazz);
	}	
}
