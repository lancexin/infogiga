package cn.infogiga.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.infogiga.bean.BaseBean;


/**
 * 这是一个公用的spring+hibernate dao泛型接口
 * @author cindy
 *
 */
public interface IBaseDAO {
	/**
	 * 根据id获得单个实例
	 * @param <T>
	 * @param id
	 * @param clazz
	 * @return
	 */
	public <T extends BaseBean> T findById(java.lang.Integer id,Class<T> clazz);
	/**
	 * 根据一个实例获得和这个实例匹配的所以实例列表
	 * @param <T>
	 * @param instance
	 * @return
	 */
	public <T extends BaseBean> List<T> findByExample(T instance);
	/**
	 * 根据摸个字段获得实例列表
	 * @param <T>
	 * @param clazz
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T extends BaseBean> List<T>  findByProperty(Class<T> clazz,String propertyName, Object value);
	/**
	 * 保存一个实例
	 * @param <T>
	 * @param transientInstance
	 */
	public <T extends BaseBean> void save(T transientInstance);
	/**
	 * 删除一个实例
	 * @param <T>
	 * @param persistentInstance
	 */
	public <T extends BaseBean> void delete(T persistentInstance);
	/**
	 * 获得某个表的所有实例
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public <T extends BaseBean> List<T> findAll(Class<T> clazz);
	/**
	 * 将传入的detached状态的对象的属性复制到持久化对象中，并返回该持久化对象 
	 * @param <T>
	 * @param detachedInstance
	 * @return
	 */
	public <T extends BaseBean> T merge(T detachedInstance);
	/**
	 * 将传入的对象持久化并保存。如果对象未保存（Transient状态），调用save方法保存
	 * @param <T>
	 * @param instance
	 */
	public <T extends BaseBean> void attachDirty(T instance);
	/**
	 * 将传入的对象状态设置为Transient状态
	 * @param <T>
	 * @param instance
	 */
	public <T extends BaseBean> void attachClean(T instance);
	/**
	 * 获得某个实例的数量
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public <T extends BaseBean> Integer getCount(Class<T> clazz);
	
	
}
