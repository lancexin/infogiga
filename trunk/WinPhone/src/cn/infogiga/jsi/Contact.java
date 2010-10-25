package cn.infogiga.jsi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.Contacts;
import android.util.Log;
import cn.infogiga.phone.Entry;
import cn.infogiga.util.Toolkit;

/**
 * 联系人操作
 * @author chroya
 *
 */
public class Contact extends Activity {
	private Context context;	
	private JSArray peoples;
	private JSArray allRecent;
	private final static String TAG = "Contact";
	private static final String VAR_NAME_CONTACT = "var Data_contact=";
	private static final String VAR_NAME_RECENT = "var Data_recent=";
	//最近联系人的js路径
	private final static String RECENT_PATH = "data/js/recent.js";
	//所有联系人的js路径
	private final static String CONTACT_PATH = "data/js/contact.js";
	
	public Contact(Context context) {
		this.context = context;	
//		initCache();
	}
	
	public void initCache() {
//		peoples = getAll();
		try {
			writeContact();
			writeRecent();
		} catch (JSONException e) {
			Log.e(TAG, "json exception:"+e);
		}		
	}
	
	/**
	 * 从缓存获取所有联系人信息
	 * @return
	 */
	public JSArray getAllFromCache() {
		if(peoples == null) {
			Log.d("Contact", "people is null, get it");
			peoples = getAll();
		}
		return peoples;
	}
	
	/**
	 * 从sim卡获取所有联系人(获取不到)
	 * @return
	 */
	public JSArray getAllFromSim() {
		JSArray people = new JSArray();
		Uri peopleUri = Uri.parse("content://icc/adn");
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(peopleUri, null, null, null, null);
		Log.d("Contact", "count:"+cursor.getCount());
		int index = 0;
		int nameIndex = cursor.getColumnIndexOrThrow("name");
		int numberIndex = cursor.getColumnIndexOrThrow("number");
		while(cursor.moveToNext()) {
			long id = index;
			String name = cursor.getString(nameIndex);
			String number = cursor.getString(numberIndex);
			people.add(new Person(id, name, number, ""));
		}
		
		return people;
	}
   
	/**
	 * 获取所有联系人信息
	 * @return
	 */
	public JSArray getAll() {		
		JSArray people = new JSArray();
		
		Uri peopleUri = Contacts.People.CONTENT_URI;
		ContentResolver resolver = context.getContentResolver();		
		Cursor cursor = resolver.query(peopleUri, null, null, null, null);
		
		int idIndex = cursor.getColumnIndexOrThrow(Contacts.People._ID);
		int nameIndex = cursor.getColumnIndexOrThrow(Contacts.People.NAME);
		int numberIndex = cursor.getColumnIndexOrThrow(Contacts.People.NUMBER);		
		while(cursor.moveToNext()) {
			long id = cursor.getInt(idIndex);
			String name = cursor.getString(nameIndex);
			String number = cursor.getString(numberIndex);
			Face face = new Face(id+"-"+name);
			String facePath = null;
			
			if(face.exists()) {
				facePath = face.createFace(null);
			} else {
				Bitmap bmp = Contacts.People.loadContactPhoto(context,
						ContentUris.withAppendedId(peopleUri, id), 0, null);
				facePath = face.createFace(bmp);
			}			
			people.add(new Person(id, name, number, facePath));			
		}		
		cursor.close();
		return people;
	}
	
	/**
	 * 获取个人简略信息
	 * @param id
	 * @return
	 */
	public Person getInfo(long id) {
		Person p = new Person();
		Uri personUri = ContentUris.withAppendedId(Contacts.People.CONTENT_URI, id);
		ContentResolver resolver = context.getContentResolver();		
		Cursor cursor = resolver.query(personUri, null, null, null, null);
		
		int nameIndex = cursor.getColumnIndexOrThrow(Contacts.People.NAME);
		int numberIndex = cursor.getColumnIndexOrThrow(Contacts.People.NUMBER);		
		while(cursor.moveToNext()) {
			String name = cursor.getString(nameIndex);
			String number = cursor.getString(numberIndex);
			Face face = new Face(id+"-"+name);
			String facePath = null;
			
			if(face.exists()) {
				facePath = face.createFace(null);
			} else {
				Bitmap bmp = Contacts.People.loadContactPhoto(context, personUri, 0, null);
				facePath = face.createFace(bmp);
			}		
			p.id = id;
			p.name = name;
			p.number = number;
			p.face = facePath;						
		}		
		cursor.close();
		
		return p;
	}
	
	/**
	 * 根据id获取详细信息
	 * @param id
	 * @return
	 */
	public AddInfo getDetails(long id) {
		AddInfo info = new AddInfo();
		info.id = id;
		Uri peopleUri = ContentUris.withAppendedId(Contacts.People.CONTENT_URI, id);
		Uri contactUri = Uri.withAppendedPath(peopleUri, Contacts.People.ContactMethods.CONTENT_DIRECTORY);
		
		ContentResolver resolver = context.getContentResolver();		
		//查询联系方式
		Cursor cursor = resolver.query(contactUri, null, null, null, null);
		int typeIndex = cursor.getColumnIndexOrThrow(Contacts.ContactMethods.TYPE);
		int kindIndex = cursor.getColumnIndexOrThrow(Contacts.ContactMethods.KIND);
		int dataIndex = cursor.getColumnIndexOrThrow(Contacts.ContactMethods.DATA);
		
		while(cursor.moveToNext()) {
			int kind = cursor.getInt(kindIndex);
			int type = cursor.getInt(typeIndex);
			switch(kind) {
			case Contacts.KIND_EMAIL:
				putTypeData(info.email, type, cursor.getString(dataIndex));
				break;
			case Contacts.KIND_ORGANIZATION:
				putTypeData(info.organization, type, cursor.getString(dataIndex));
				break;
			case Contacts.KIND_POSTAL:
				putTypeData(info.postal, type, cursor.getString(dataIndex));
				break;
			}
		}
		cursor.close();
			
		//查询电话号码
		Uri phoneUri = Uri.withAppendedPath(peopleUri, Contacts.People.Phones.CONTENT_DIRECTORY);
		cursor = resolver.query(phoneUri, null, null, null, null);
		typeIndex = cursor.getColumnIndexOrThrow(Contacts.Phones.TYPE);
		int numberIndex = cursor.getColumnIndexOrThrow(Contacts.Phones.NUMBER);
		while(cursor.moveToNext()) {
			int type = cursor.getInt(typeIndex);
			Log.d("phone", "type:"+type+",phone:"+ cursor.getString(numberIndex));
			putTypePhone(info.phone, type, cursor.getString(numberIndex));
		}
		cursor.close();
		return info;
	}
	
	/**
	 * 获取指定ID的IM信息
	 * @param id
	 * @return
	 */
	public ImInfo getIm(long id) {
		ImInfo info = new ImInfo();
		Uri peopleUri = ContentUris.withAppendedId(Contacts.People.CONTENT_URI, id);
		Uri imUri = Uri.withAppendedPath(peopleUri, Contacts.People.ContactMethods.CONTENT_DIRECTORY);
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(imUri, null, null, null, null);
		int typeIndex = cursor.getColumnIndex(Contacts.ContactMethods.AUX_DATA);
		int dataIndex = cursor.getColumnIndex(Contacts.ContactMethods.DATA);
		
		while(cursor.moveToNext()) {
			String type = cursor.getString(typeIndex);
			if(type == null || "".equals(type)) continue;
			int aux = Integer.parseInt(type.substring(4));
			Log.d("IMINFO", "aux:"+ aux);
			putTypeIm(info.im, aux, cursor.getString(dataIndex));
		}
		
		cursor.close();
		return info;
	}
	
	/**
	 * 从缓存读取指定数目的最近联系人
	 * @param count
	 * @return
	 */
	public JSArray getRecentFromCache(int count){
		if(allRecent == null) {
			allRecent = getRecent();
		}		
		int size = Math.min(count, allRecent.length());
		JSArray recent = new JSArray(size);
		for(int index=0; index< size; index++) {
			recent.add(allRecent.get(size));
		}
		return recent;
	}
	
	/**
	 * 从缓存读取最近联系人
	 * @return
	 */
	public JSArray getRecentFromCache() {
		if(allRecent == null) {
			allRecent = getRecent();
		}
		return allRecent;
	}
	
	/**
	 * 过滤最近联系人，剔除重复的
	 * @param all
	 */
	private void filterRecent(JSArray all) {
		JSArray recent = new JSArray();
		for(int index=0; index<all.length(); index++) {
			Person p = (Person) all.get(index);
			if(Toolkit.contains(all, p)) {
				Log.d("Contact", "repeat:"+ p.id);
				continue;
			}
			recent.add(p);
		}
		all = recent;
	}
	
	/**
	 * 获取所有最近联系人，倒序
	 * @return
	 */
	private JSArray getRecent() {
		JSArray people = new JSArray();
		int ic = 0;
		Uri callUri = CallLog.Calls.CONTENT_URI;
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(callUri, null, null, null,
				CallLog.Calls._ID+" DESC");
		int numberIndex = cursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER);
		Log.d("Contact", "record:"+ cursor.getCount());
		while(cursor.moveToNext()) {
			String number = cursor.getString(numberIndex);
			Person p = getPersonFromNumber(number);			
			Log.d("Contact", "p:"+ p.id);
			people.add(p);
			ic++;
		}
		cursor.close();	
		
		return people;
	}
	
	/**
	 * 获取最近联系人信息，从数据库读取，最好用getRecentFromCache替代
	 * @param count		需要获取的记录数
	 * @return
	 */
	public JSArray getRecent(int count) {
		JSArray people = new JSArray();
		int ic = 0;
		Uri callUri = CallLog.Calls.CONTENT_URI;
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(callUri, null, null, null,
				CallLog.Calls._ID+" DESC");
		int numberIndex = cursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER);
		Log.d("Contact", "record:"+ cursor.getCount());
		while(cursor.moveToNext()) {
			if(ic >= count) break;
			String number = cursor.getString(numberIndex);
			Person p = getPersonFromNumber(number);
			if(Toolkit.contains(people, p)) {
				Log.d("Contact", "repeat:"+ p.id);
				continue;
			}
			Log.d("Contact", "p:"+ p.id);
			people.add(p);
			ic++;
		}
		cursor.close();	
		
		return people;
	}
	
	/**
	 * 根据电话号码得到联系人
	 * @param number
	 * @return
	 */
	public Person getPersonFromNumber(String number) {
		Person p = new Person();
		Uri phoneUri = Contacts.Phones.CONTENT_URI;
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(phoneUri, null, null, null, null);
		int personIndex = cursor.getColumnIndexOrThrow(Contacts.Phones.PERSON_ID);
		int numberIndex = cursor.getColumnIndexOrThrow(Contacts.Phones.NUMBER);
		int pid = -1;
		
		while(cursor.moveToNext()) {
			String localNumber = cursor.getString(numberIndex);
			if(Toolkit.eq(localNumber, number)) {
				pid = cursor.getInt(personIndex);
				p.number = number;
				p.id = pid;
				break;
			}
		}
		cursor.close();
		
		if(pid == -1) {
			p.number = number;
			p.id = pid;
			return p;
		}
		
		return getInfo(pid);		
	}
	
	/**
	 * 添加联系人
	 * @param person
	 * @return
	 */
	public boolean add(Person person) {
		Uri peopleUri = Contacts.People.CONTENT_URI;
		ContentResolver resolver = context.getContentResolver();
		ContentValues values = new ContentValues();
		if(!Toolkit.isBlank(person.name)) {
			values.put(Contacts.People.NAME, person.name);			
		}
		
		Uri uri = Contacts.People.createPersonInMyContactsGroup(resolver, values);
		peopleUri = Uri.withAppendedPath(uri, Contacts.People.Phones.CONTENT_DIRECTORY);
		values.clear();
		values.put(Contacts.Phones.TYPE, Contacts.Phones.TYPE_MOBILE);
		values.put(Contacts.Phones.NUMBER, person.number);
		return resolver.insert(peopleUri, values)!=null;
	}
	
	/**
	 * 更新某个联系
	 * @param person	要更新的联系人
	 * @return
	 */
	public boolean update(Person person) {
		long id = person.id;
		Uri peopleUri = ContentUris.withAppendedId(Contacts.People.CONTENT_URI, id);
		Uri numberUri = Uri.withAppendedPath(peopleUri, Contacts.People.Phones.CONTENT_DIRECTORY);
//		ContentUris.withAppendedId(Contacts.Phones.CONTENT_URI, id);
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(Contacts.Phones.CONTENT_URI, null, Contacts.Phones.PERSON_ID+"=?", new String[]{id+""}, null);
		cursor.moveToFirst();
		
		ContentValues values = new ContentValues();
		if(!Toolkit.isBlank(person.name)) {
			values.put(Contacts.People.NAME, person.name);
			resolver.update(peopleUri, values, null, null);
			values.clear();
		}
		if(!Toolkit.isBlank(person.number)) {
			values.put(Contacts.Phones.NUMBER, person.number);
			resolver.update(numberUri, values, null, null);
		}
		
		return true;
	}
	
	/**
	 * 删除某个联系人
	 * @param id	要删除的联系人ID
	 * @return
	 */
	public boolean delete(int id) {		
		Uri peopleUri = ContentUris.withAppendedId(Contacts.People.CONTENT_URI, id);
		ContentResolver resolver = context.getContentResolver();
		return resolver.delete(peopleUri, null, null) == 1;
	}
	
	/**
	 * 清空所有联系人（返回值未经验证）
	 * @return
	 */
	public boolean clear() {
		ContentResolver resolver = context.getContentResolver();
		return resolver.delete(Contacts.People.CONTENT_URI, null, null) == 1;
	}
	
	/**
	 * 写联系人的js
	 * @return
	 * @throws JSONException
	 */
	private boolean writeContact() throws JSONException {
		JSArray allArray = getAll();
		JSONArray json = new JSONArray(); 
		for(int index=0; index<allArray.length(); index++) {
			Person p = (Person) allArray.get(index);
			JSONObject obj = new JSONObject();
			obj.put("pid", p.id);
			obj.put("name", p.name);
			obj.put("number", p.number);
			obj.put("face", p.face);
			json.put(obj);
		}
		String all = json.toString();
		String jsContent = VAR_NAME_CONTACT + all;
		return Toolkit.write(jsContent, Entry.path+ CONTACT_PATH);
	}
	
	/**
	 * 写最近联系人的js
	 * @return
	 * @throws JSONException
	 */
	private boolean writeRecent() throws JSONException {		
		JSArray recentArray = getRecent();
		JSONArray json = new JSONArray();
		for(int index=0; index<recentArray.length(); index++) {
			Person p = (Person) recentArray.get(index);
			JSONObject obj = new JSONObject();
			obj.put("pid", p.id);
			obj.put("name", p.name);
			obj.put("number", p.number);
			obj.put("face", p.face);
			json.put(obj);
		}
		String recent = json.toString();
		String jsContent = VAR_NAME_RECENT + recent;
		return Toolkit.write(jsContent, Entry.path+ RECENT_PATH);			
	}
	
	/**
	 * IM信息
	 *
	 */
	class ImInfo {
		public int id;
		public Map<String, String> im = new HashMap<String, String>();
		public Map<String, String> getIm() {
			return im;
		}
	}
	
	/**
	 * 个人详细信息
	 *
	 */
	class AddInfo {
		public long id;
		//根据kind查找，里面的map根据type查找
		public Map<String, Map<String, String>> contactMethod = new HashMap<String, Map<String, String>>();
		public Map<String, String> phone = new HashMap<String, String>();
		public Map<String, String> email = new HashMap<String, String>();
		public Map<String, String> im = new HashMap<String, String>();
		public Map<String, String> organization = new HashMap<String, String>();
		public Map<String, String> postal = new HashMap<String, String>();
		
		public AddInfo() {
			contactMethod.put("phone", phone);
			contactMethod.put("email", email);
			contactMethod.put("im", im);
			contactMethod.put("organization", organization);
			contactMethod.put("postal", postal);
		}
		public Map<String, Map<String, String>> getContactMethod() {
			return contactMethod;
		}
	}
	
	/**
	 * 个人基本信息
	 *
	 */
	public class Person {		
		public long id;		//id
		public String name; //姓名
		public String number;//手机号
		public String face;//头像地址
		public Person(){}
		public Person(long id, String name, String number, String face) {
			super();
			this.face = face;
			this.id = id;
			this.name = name;
			this.number = number;
		}
		public long getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		public String getNumber() {
			return number;
		}
		public String getFace() {
			return face;
		}
		@Override
		public boolean equals(Object obj) {			
			if(obj == this) {
				return true;
			}
			if(!(obj instanceof Person)) {
				return false;
			}
			Person p = (Person) obj;
			if(this.number.equals(p.number)) {
				return true;
			}			
			return false;
		}
	}
	
	/**
	 * 头像相关
	 *
	 */
	class Face {
		private String name;
		
		public Face(String name) {
			this.name = name;
		}
		
		/**
		 * 此头像是否存在
		 * @return
		 */
		public boolean exists() {
			return context.getFileStreamPath(name).exists();
		}
		
		/**
		 * 创建头像
		 * @param bmp	bitmap
		 * @return	图片的绝对路径，如果有异常，则返回null
		 */
		public String createFace(Bitmap bmp) {
			if(exists()) {//已经存在
				return "file://"+ context.getFileStreamPath(name).getAbsolutePath();
			}
			if(bmp == null) {
				return null;
			}
			FileOutputStream fos = null;
			try {
				fos = context.openFileOutput(name, MODE_WORLD_READABLE);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			return bmp.compress(CompressFormat.PNG, 70, fos)?
					"file:///"+ context.getFileStreamPath(name).getAbsolutePath():null;
		}
	}
	
	/**
	 * 把指定值放入im的map中
	 * @param map
	 * @param aux		key
	 * @param value		值
	 */
	private void putTypeIm(Map<String, String> map, int aux, String value) {
		switch(aux) {
		case Contacts.ContactMethods.PROTOCOL_QQ:
			map.put("qq", value);
			break;
		case Contacts.ContactMethods.PROTOCOL_AIM:
			map.put("aim", value);
			break;
		case Contacts.ContactMethods.PROTOCOL_GOOGLE_TALK:
			map.put("gtalk", value);
			break;
		case Contacts.ContactMethods.PROTOCOL_ICQ:
			map.put("icq", value);
			break;
		case Contacts.ContactMethods.PROTOCOL_JABBER:
			map.put("jabber", value);
			break;
		case Contacts.ContactMethods.PROTOCOL_MSN:
			map.put("msn", value);
			break;
		case Contacts.ContactMethods.PROTOCOL_SKYPE:
			map.put("skype", value);
			break;
		case Contacts.ContactMethods.PROTOCOL_YAHOO:
			map.put("yahoo", value);
			break;
		}
	}
	
	private void putTypePhone(Map<String, String> map, int type, String value) {
		switch(type) {
		case Contacts.Phones.TYPE_MOBILE:
			map.put("mobile", value);
			break;
		case Contacts.Phones.TYPE_HOME:
			map.put("home", value);
			break;
		case Contacts.Phones.TYPE_WORK:
			map.put("work", value);
			break;
		case Contacts.Phones.TYPE_OTHER:
			map.put("other", value);
			break;
		}
	}
	
	private void putTypeData(Map<String, String> map, int type, String value) {
		switch(type) {
		case Contacts.ContactMethods.TYPE_HOME:
			map.put("home", value);
			break;
		case Contacts.ContactMethods.TYPE_WORK:
			map.put("work", value);
			break;
		case Contacts.ContactMethods.TYPE_OTHER:
			map.put("other", value);
			break;
		}
	}	
}