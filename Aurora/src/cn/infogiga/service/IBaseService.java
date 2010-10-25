package cn.infogiga.service;

import java.util.List;

import cn.infogiga.bean.BaseBean;
import cn.infogiga.dao.BaseDAO;


public interface IBaseService {
	/**
	 * 根据id获得单个实例
	 * @param <T>
	 * @param id
	 * @param clazz
	 * @return
	 */
	public <T extends BaseBean,E extends BaseDAO> T findById(java.lang.Integer id,Class<T> clazz,E dao);
	/**
	 * 根据一个实例获得和这个实例匹配的所以实例列表
	 * @param <T>
	 * @param instance
	 * @return
	 */
	public <T extends BaseBean,E extends BaseDAO> List<T> findByExample(T instance,E dao);
	/**
	 * 根据摸个字段获得实例列表
	 * @param <T>
	 * @param clazz
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T extends BaseBean,E extends BaseDAO> List<T>  findByProperty(Class<T> clazz,String propertyName, Object value,E dao);
	/**
	 * 保存一个实例
	 * @param <T>
	 * @param transientInstance
	 */
	public <T extends BaseBean,E extends BaseDAO> boolean save(T transientInstance,E dao);
	/**
	 * 删除一个实例
	 * @param <T>
	 * @param persistentInstance
	 */
	public <T extends BaseBean,E extends BaseDAO> boolean delete(T persistentInstance,E dao);
	/**
	 * 获得某个表的所有实例
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public <T extends BaseBean,E extends BaseDAO> List<T> findAll(Class<T> clazz,E dao);
}
