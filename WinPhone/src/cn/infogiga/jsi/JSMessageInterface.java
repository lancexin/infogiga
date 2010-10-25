package cn.infogiga.jsi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

/**
 * 绑定到js的函数接口类，包含所有操作短彩信的方法
 * @author chroya
 *
 */
public final class JSMessageInterface {
	private Message message;
	private static final String TAG = "JSMI";
	
	public JSMessageInterface(Message message) {
		this.message = message;
	}

	/**
	 * 发送一条短消息
	 * @param dest	号码
	 * @param content	内容
	 * @return	是否发送成功
	 */
	public boolean sendSms(String dest, String content) {
		return message.sendSms(dest, content, -1);
	}
	
	/**
	 * 向多个号码发送短消息
	 * @param jsonObj json字符串<br/>
	 * 格式：{dests:["110","120"], content:"你好"}
	 * @return	是否发送成功<br/>
	 * 如果json格式有误，会返回false
	 */
	public boolean sendSms(String jsonObj) {
		try {
			JSONObject obj = new JSONObject(jsonObj);
			JSONArray destArray = obj.getJSONArray("dests");
			String content = obj.getString("content");
			String[] dests = new String[destArray.length()];
			for(int i=0; i< dests.length; i++) {
				dests[i] = destArray.getString(i);
			}
			return message.sendSms(dests, content);
		} catch (JSONException e) {
			Log.e(TAG, "json exception:"+e);
			return false;
		}
	}
	
	/**
	 * 获取最近发送的count条短信
	 * @param count
	 * @return
	 */
	public JSArray getSentSms(int count) {
		return message.getSentSms(count);
	}
	
	/**
	 * 获取所有已发送的短信
	 * @return
	 */
	public JSArray getSentSms() {
		return message.getSentSms();
	}
	
	/**
	 * 获取count条未读短消息
	 * @param count
	 * @return
	 */
	public JSArray getUnreadSms(int count) {
		return message.getUnreadSms(count);
	}
	
	/**
	 * 获取所有未读短消息
	 * @return
	 */
	public JSArray getUnreadSms() {
		return message.getUnreadSms();
	}
	
	/**
	 * 获取短消息的状态
	 * @param id
	 * @return
	 */
	public int getSendStatus(long id) {
		return message.getSendStatus(id);
	}
	
	/**
	 * 设置此短消息为已读
	 * @param id	短消息id
	 * @return
	 */
	public boolean readSms(String id) {
		return message.readSms(id);
	}
	
	/**
	 * 收件箱中的短消息
	 * @return
	 */
	public JSArray getReceivedSms() {
		return message.getReceivedSms();
	}
	
	public JSArray getReceivedSms(int count) {
		return message.getReceivedSms(count);
	}
	
	/**
	 * 删除一条短信
	 * @param id	短信的id
	 * @return
	 */
	public boolean deleteSms(long id) {
		return message.deleteSms(id);
	}
}