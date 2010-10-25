package cn.infogiga.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import cn.infogiga.jsi.Message;
import cn.infogiga.provider.Telephony.Sms;
import cn.infogiga.util.Toolkit;

/**
 * 短消息广播接收
 * @author chroya
 *
 */
public class SmsReceiver extends BroadcastReceiver {
	private static final String TAG = "SmsReceiverReceiver";	

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if(Toolkit.eq(action, Message.MESSAGE_SENT_ACTION)) {
			Uri messageUri = intent.getData();
			int resultCode = getResultCode();
			switch(resultCode) {
			case Activity.RESULT_OK:
				//发送成功
				Sms.moveMessageToFolder(context, messageUri, Sms.MESSAGE_TYPE_SENT);
				break;
			case SmsManager.RESULT_ERROR_RADIO_OFF:
			case SmsManager.RESULT_ERROR_NO_SERVICE:
				//网络问题，加入发送队列
				Sms.moveMessageToFolder(context, messageUri, Sms.MESSAGE_TYPE_QUEUED);
				break;
			default:
				//发送失败
				Sms.moveMessageToFolder(context, messageUri, Sms.MESSAGE_TYPE_FAILED);
			}
		}
	}	
}