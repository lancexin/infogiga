package cn.infogiga.jsi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 返回给js的包装数组
 * @author chroya
 *
 */
public final class JSArray {
   
	private List<Object> array;
//	private Object[]
	
	/**
	 * 默认构造
	 */
	public JSArray() {
		array = new ArrayList<Object>();
	}
	/**
	 * 指定初始大小构造
	 * @param size
	 */
	public JSArray(int size) {
		array = new ArrayList<Object>(size);
	}
	/**
	 * 用数组构造
	 * @param array
	 */
	public JSArray(Object[] objs) {
		this.array = Arrays.asList(objs);
	}
	
	/**
	 * 数组的长度
	 * @return
	 */
	public int length() {
		return array.size();
	}
	
	/**
	 * 获取第index个对象
	 * @param index
	 * @return 对象
	 */
	public Object get(int index) {
		return array.get(index);
	}
	
	/**
	 * 将一个对象放入指定位置
	 * @param index
	 * @param obj
	 */
	public void put(int index, Object obj) {
		array.set(index, obj);
	}
	
	/**
	 * 添加一个对象到尾部
	 * @param obj
	 */
	public void add(Object obj) {
		array.add(obj);
	}
	
	/**
	 * 数组是否包含此对象
	 * @param obj
	 * @return
	 */
	/*public boolean contains(Object obj) {
		for(Object o: array) {
			if(o.equals(obj)) {
				return true;
			}
		}
		return false;
	}*/
}