package cn.infogiga.jsi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import cn.infogiga.provider.Telephony;
import cn.infogiga.provider.Telephony.Sms;
import cn.infogiga.receiver.SmsReceiver;
import cn.infogiga.util.Toolkit;

/**
 * 短消息
 * @author chroya
 *
 */
public class Message extends Activity {
	public static final String MESSAGE_SENT_ACTION = "cn.infogiga.message.SENT";
	
	private static final String TAG = "Message";
	private Context context;	
	private MessageObserver msgObserver;
	
	public Message(Context context) {
		this.context = context;	
		msgObserver = new MessageObserver(new Handler());
		context.getContentResolver().registerContentObserver(
				Telephony.Sms.CONTENT_URI, false, msgObserver);
	}	
	
	/**
	 * 获取收件箱里面最新的count条短消息
	 * @param count
	 * @return
	 */
	public JSArray getReceivedSms(int count) {
		Uri receivedUri = Telephony.Sms.Inbox.CONTENT_URI;
		return getSmsInfoFromUri(receivedUri, count);		
	}
	
	/**
	 * 获取所有收件箱中的短消息
	 * @return
	 */
	public JSArray getReceivedSms() {
		return getReceivedSms(-1);
	}
	
	/**
	 * 读取草稿箱中的count条短消息
	 * @param count
	 * @return
	 */
	public JSArray getDraftSms(int count) {		
		Uri draftUri = Telephony.Sms.Draft.CONTENT_URI;
		return getSmsInfoFromUri(draftUri, count);
	}
	
	/**
	 * 读取草稿箱中所有的短消息
	 * @return
	 */
	public JSArray getDraftSms() {
		return getDraftSms(-1);
	}		
	
	/**
	 * 获取最近count条已发送短信
	 * @param count	数量
	 * @return
	 */
	public JSArray getSentSms(int count) {
		Uri sentUri = Telephony.Sms.Sent.CONTENT_URI;
		return getSmsInfoFromUri(sentUri, count);
	}
	
	/**
	 * 获取已发送的短消息列表
	 * @return
	 */
	public JSArray getSentSms() {
		return getSentSms(-1);
	}
	
	/**
	 * 获取指定id的短消息发送状态
	 * @param id	
	 * @return	状态:-1为发送失败，0为发送中，1为发送完成，2为发送成功
	 */
	public int getSendStatus(long id) {
		int statusCode;
		
		Uri uri = ContentUris.withAppendedId(Telephony.Sms.CONTENT_URI, id);
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(uri, null, null, null, null);
		cursor.moveToFirst();
		int status = cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Sms.STATUS));
		switch(status) {
		case Telephony.Sms.STATUS_COMPLETE:statusCode=1;break;
		case Telephony.Sms.STATUS_FAILED:statusCode=-1;break;
		case Telephony.Sms.STATUS_PENDING:statusCode=0;break;
		case Telephony.Sms.STATUS_NONE:statusCode=2;break;
		default:statusCode=-100;
		}
		
		return statusCode;
	}
	
	/**
	 * 获取count条未读短消息(有bug，获取的都是已读的)
	 * @param count
	 * @return
	 */
	public JSArray getUnreadSms(int count) {
		boolean limit = count>0; //count大于0，则加上limit
		JSArray smsArray = new JSArray();
		ContentResolver resolver = context.getContentResolver();
		//查询短消息详细信息，按日期降序排列
		Cursor cursor = resolver.query(Telephony.Sms.Inbox.CONTENT_URI, null,
				Telephony.Sms.Inbox.READ+"=?", new String[]{"0"}, 
				Telephony.Sms.DEFAULT_SORT_ORDER+ (limit?" limit 0,"+ count:""));
		int idIndex = cursor.getColumnIndexOrThrow(Telephony.Sms._ID);
		int addressIndex = cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS);
		int dateIndex = cursor.getColumnIndexOrThrow(Telephony.Sms.DATE);
		int pidIndex = cursor.getColumnIndexOrThrow(Telephony.Sms.PERSON_ID);
		int bodyIndex = cursor.getColumnIndexOrThrow(Telephony.Sms.BODY);
		
		while(cursor.moveToNext()) {
			long id = cursor.getLong(idIndex);
			String address = cursor.getString(addressIndex);
			long pid = cursor.getLong(pidIndex);
			long date = cursor.getLong(dateIndex);
			String body = cursor.getString(bodyIndex);
			
			SmsInfo sms = new SmsInfo(body, date, address, id, pid);
			smsArray.add(sms);
		}
		return smsArray;
	}
	
	/**
	 * 获取所有未读短消息
	 * @return
	 */
	public JSArray getUnreadSms() {
		return getUnreadSms(-1);
	}
	
	/**
	 * 将短信设置为已读
	 * @param id	短信ID
	 * @return		是否成功
	 */
	public boolean readSms(String id) {
		ContentResolver resolver = context.getContentResolver();
		ContentValues values = new ContentValues();
		values.put(Telephony.Sms.Inbox.READ, "1");
		return 1 == resolver.update(Telephony.Sms.Inbox.CONTENT_URI,
				values, Telephony.Sms.Inbox._ID+ "=?", new String[]{id});
	}
	
	/**
	 * 发送短消息（未听取发送和接受成功的广播）
	 * @param number	号码
	 * @param content	内容
	 * @return 是否成功
	 */
	public boolean sendSms(String dest, String content, long threadId) {
		SmsManager manager = SmsManager.getDefault();
		HashSet<String> recipients = new HashSet<String>();
		recipients.add(dest);
		threadId = threadId > 0?threadId:Telephony.Threads.getOrCreateThreadId(context, recipients);
		
		if(Toolkit.isBlank(dest) || Toolkit.isBlank(content)) {
			Log.w(TAG, "空的联系人或短消息");
			return false;
		}
		ArrayList<String> parts = manager.divideMessage(content);
		int msgCount = parts.size();
		ArrayList<PendingIntent> sentIntents = new ArrayList<PendingIntent>(msgCount);
		//写入发件箱
		Uri uri = Sms.Outbox.addMessage(context.getContentResolver(), 
				dest, content, null, System.currentTimeMillis(), false, threadId);
		
		for(int i=0; i<msgCount; i++) {
			PendingIntent sentIntent = PendingIntent.getBroadcast(context, 0, 
					new Intent(MESSAGE_SENT_ACTION, uri, context, SmsReceiver.class), 0);
			sentIntents.add(sentIntent);		
		}
		manager.sendMultipartTextMessage(dest, null, parts, sentIntents, null);
		
		return true;
	}
	
	/**
	 * 发送短消息，多个收件人
	 * @param dests
	 * @param content
	 * @return
	 */
	public boolean sendSms(String[] dests, String content) {
		long threadId = Telephony.Threads.getOrCreateThreadId(context, 
				new HashSet<String>(Arrays.asList(dests)));
		if(Toolkit.isBlank(content)) {
			Log.w(TAG, "空的短消息");
			return false;
		}
		if(dests.length == 0) {
			Log.d(TAG, "空的收件人列表");
			return false;
		}
		for(int i=0; i<dests.length; i++) {
			sendSms(dests[i], content, threadId);
		}
		
		return true;
	}
	
	/**
	 * 删除这条短信
	 * @param id	短信的id
	 * @return
	 */
	public boolean deleteSms(long id) {
		Uri uri = ContentUris.withAppendedId(Telephony.Sms.CONTENT_URI, id);
		ContentResolver resolver = context.getContentResolver();
		return 1 == resolver.delete(uri, null, null);
	}
	
	/**
	 * 从uri中获取SmsInfo对象数组
	 * @param uri
	 * @param count
	 * @return
	 */
	private JSArray getSmsInfoFromUri(Uri uri, int count) {
		boolean limit = count>0; //count大于0，则加上limit
		JSArray smsArray = new JSArray();
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(uri, null, null, null,
				Telephony.Sms.DEFAULT_SORT_ORDER+ (limit?" limit 0,"+count:""));
		
		int idIndex = cursor.getColumnIndexOrThrow(Telephony.Sms._ID);
		int addressIndex = cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS);
		int dateIndex = cursor.getColumnIndexOrThrow(Telephony.Sms.DATE);
		int pidIndex = cursor.getColumnIndexOrThrow(Telephony.Sms.PERSON_ID);
		int bodyIndex = cursor.getColumnIndexOrThrow(Telephony.Sms.BODY);
		
		while(cursor.moveToNext()) {
			long id = cursor.getLong(idIndex);
			String address = cursor.getString(addressIndex);
			long pid = cursor.getLong(pidIndex);
			long date = cursor.getLong(dateIndex);
			String body = cursor.getString(bodyIndex);
			
			SmsInfo sms = new SmsInfo(body, date, address, id, pid);
			smsArray.add(sms);
		}
		return smsArray;
	}
	
	class SmsInfo {
		long id;
		long pid;
		String dest;
		String content;
		long date;
		public SmsInfo(){}		
		public SmsInfo(String content, long date, String dest, long id, long pid) {
			this.content = content;
			this.date = date;
			this.dest = dest;
			this.id = id;
			this.pid = pid;
		}

		public long getId() {
			return id;
		}
		public long getPid() {
			return pid;
		}
		public String getDest() {
			return dest;
		}
		public String getContent() {
			return content;
		}
		public long getDate() {
			return date;
		}				
	}
	
	/**
	 * 监听短消息数据库变化
	 *
	 */
	class MessageObserver extends ContentObserver {

		public MessageObserver(Handler handler) {
			super(handler);
		}
		
		@Override
		public void onChange(boolean selfChange) {
			
			super.onChange(selfChange);
		}
	}
}