package cindy.web;

import java.io.Serializable;
import java.util.List;
import java.util.Map;



public interface DAO extends java.io.Serializable{
	/**
	 * 根据主键获得对象
	 * @param <T>
	 * @param clazz
	 * @param id
	 * @return
	 */
	public <T extends Serializable> T findById(Class<T> clazz,Integer id);
	
	/**
	 * 获得全部
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public <T extends Serializable> List<T> findAll(Class<T> clazz);
	
	/**
	 * 根据单个表字段获得
	 * @param <T>
	 * @param clazz
	 * @param property
	 * @param value
	 * @return
	 */
	public <T extends Serializable> List<T> findByProperty(Class<T> clazz,String property,Object value);
	
	/**
	 * 根据多个表字段获得
	 * @param <T>
	 * @param clazz
	 * @param properties
	 * @return
	 */
	public <T extends Serializable> List<T> findByProperties(Class<T> clazz,Map<String,Object> properties);
	
	/**
	 * 根据对象获得
	 * @param <T>
	 * @param pojo
	 * @return
	 */
	public <T extends Serializable> List<T> findByExample(T pojo);
	
	/**
	 * 根据对象获得单个信息
	 * @param <T>
	 * @param pojo
	 * @return
	 */
	public <T extends Serializable> T findSingleByExample(T pojo);
	
	
	/**
	 * 保存一个对象
	 * @param <T>
	 * @param pojo
	 */
	public <T extends Serializable> void save(T pojo);
	
	
	/**
	 * 集体保存
	 * @param <T>
	 * @param list
	 */
	public <T extends Serializable> void querySave(List<T> list);
	
	
	/**
	 * 删除一个对象
	 * @param <T>
	 * @param pojo
	 */
	public <T extends Serializable> void delete(T pojo);
	
	/**
	 * 修改一个对象
	 * @param <T>
	 * @param pojo
	 */
	public <T extends Serializable> void update(T pojo);
	
	/**
	 * 保存或修改一个对象（根据主键）
	 * @param <T>
	 * @param pojo
	 */
	public <T extends Serializable> void saveOrUpdate(T pojo);
	
	/**
	 * 集体删除
	 * @param <T>
	 * @param list
	 */
	public <T extends Serializable> void queryDelete(List<T> list);
	
	/**
	 * 获得表个数
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public <T extends Serializable> Integer getCount(Class<T> clazz);
	
	/**
	 * 根据对象获得表个数
	 * @param <T>
	 * @param t
	 * @return
	 */
	public <T extends Serializable> Integer getCountByExample(T t);
	
	/**
	 * 根据字段获得对象个数
	 * @param <T>
	 * @param clazz
	 * @param property
	 * @param value
	 * @return
	 */
	public <T extends Serializable> Integer getCountByProperty(Class<T> clazz,String property,Object value);
	
	/**
	 * 根据单个对象获得个数
	 * @param <T>
	 * @param clazz
	 * @param properties
	 * @return
	 */
	public <T extends Serializable> Integer getCountByProperties(Class<T> clazz,Map<String,Object> properties);
	
	public <T extends Serializable> List<T> getListByPage(Class<T> clazz,Integer start,Integer limit);
	
	
}