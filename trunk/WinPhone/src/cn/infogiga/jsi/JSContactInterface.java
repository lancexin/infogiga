package cn.infogiga.jsi;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import cn.infogiga.jsi.Contact.Person;

/**
 * 绑定到js的函数接口类，包含所有操作联系人的方法
 * @author chroya
 *
 */
public final class JSContactInterface {
	private static final String TAG = "JSCI";
	private Contact contact;
	
	public JSContactInterface(Contact contact) {
		this.contact = contact;
	}
	   
	public JSArray getAll() {		
		return contact.getAll();
	}
	
	public JSArray getAllFromSim() {
		return contact.getAllFromSim();
	}
	
	public Object getInfo(long id) {
		return contact.getInfo(id);
	}
	
	public Object getDetails(long id) {
		return contact.getDetails(id);
	}
	
	public Object getIm(long id) {
		return contact.getIm(id);
	}
	
	public JSArray getRecent(int count) {
		Log.d("Contact", "count:"+count);		
		return contact.getRecent(count);
	}
	
	/**
	 * 添加一个联系人
	 * @param jsonObj	json字符串<br/>
	 * 格式：
	 * {name:"警察", number:"110"}
	 * @return	是否添加成功<br/>
	 * json格式有误也会返回false
	 */
	public boolean add(String jsonObj) {
		try {
			JSONObject obj = new JSONObject(jsonObj);
			String name = obj.getString("name");
			String number = obj.getString("number");
			
			Person p = contact.new Person();
			p.name = name;
			p.number = number;
			return contact.add(p);
		} catch (JSONException e) {
			Log.e(TAG, "json exception:"+ e);
			return false;
		}
	}
	
	/**
	 * 更新联系人信息
	 * @param jsonObj
	 * @return
	 */
	public boolean update(String jsonObj) {
		try {
			JSONObject obj = new JSONObject(jsonObj);
			long id = obj.getLong("id");
			String name = obj.getString("name");
			String number = obj.getString("number");
			
			Person p = contact.new Person();
			p.id = id;
			p.name = name;
			p.number = number;
			return contact.update(p);
		} catch (JSONException e) {
			Log.e(TAG, "json exception:"+ e);
			return false;
		}
	}
}