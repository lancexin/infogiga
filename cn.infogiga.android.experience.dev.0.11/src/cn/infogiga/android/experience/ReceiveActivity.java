package cn.infogiga.android.experience;

import cn.infogiga.android.experience.bean.ErrInfo;
import cn.infogiga.android.experience.bean.Intf;
import cn.infogiga.android.experience.service.ExperienceService;
import cn.infogiga.android.experience.service.ExperienceService.ExperienceServiceBinder;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ReceiveActivity extends Activity{
	
	private static final String tag = "ReceiveActivity";

	//private static final int SHOW_DIALOG = 100001;
	
	private static final int PROGRESS_DIALOG = 100001;
	
	private static final int CONFIRM_DIALOG = 100002;
	
	protected String menuId = null;
	protected String funcId = null;
	protected String phoneNumber = null;
	protected String name = null;
	protected String isMusic =null;
	protected String sendStr =null;
	protected String pkg =null;
	protected String selfIntr =null;

	ExperienceServiceBinder experienceServiceBinder;
	//CmwapHelper cmwapHelper= CmwapHelper.getCmwapHelper();
	//CmnetHelper cmwapHelper= CmnetHelper.getCmwapHelper();
	Button ok,concel;
	EditText et;
	TextView tv;
	
	Handler handler = new Handler();
	
	
	
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case PROGRESS_DIALOG:
			ProgressDialog dialog = new ProgressDialog(this);
            dialog.setMessage("正在加载数据...");
            dialog.setIndeterminate(true);
            dialog.setCancelable(true);
            return dialog;
		case CONFIRM_DIALOG:
			String str = "\"音乐随身听\"是无线音乐俱乐部用户新增特色服务功能，通过客户端软件的方式，向用户提供海量音乐的在线听歌、全曲下载和便捷的彩铃订购、振铃下载、音乐搜索等服务。您可在此处获取软件下载地址。";
			return new AlertDialog.Builder(this)
			.setTitle("确认信息")
			.setMessage(str)
			.setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	showDialog(CONFIRM_DIALOG);
                	handler.post(new Runnable(){
						@Override
						public void run() {
							Intf intf = experienceServiceBinder.sendSMS(phoneNumber, sendStr, menuId);
							if(intf != null){
								ErrInfo info = intf.getErrinfo();
								if("0".equals(info.getErrCode())){
									Toast.makeText(ReceiveActivity.this,"发送成功", Toast.LENGTH_LONG).show();
									Intent intent = new Intent("cn.infogiga.android.killprocess");
				                	sendBroadcast(intent);
				                	finish();
								}else{
									Toast.makeText(ReceiveActivity.this,info.getErrHint(), Toast.LENGTH_LONG).show();
								}
							}else{
								Toast.makeText(ReceiveActivity.this,"暂时服务连接网络,请稍后再试", Toast.LENGTH_LONG).show();
							}
							
						}
                	});
                }
            })
            .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	Intent intent = new Intent("cn.infogiga.android.killprocess");
                	sendBroadcast(intent);
                	finish();
                }
            })
			.create();
		default:
			return null;
		}
	}



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			Intent i = new Intent();
			i.setClass(this, ExperienceService.class);
			this.startService(i);
			bindService(i, conn, Context.BIND_AUTO_CREATE);
		} catch (RuntimeException e) {
			Toast.makeText(this,R.string.no_service, Toast.LENGTH_LONG).show();
			this.finish();
			e.printStackTrace();
		}
	}
	
	public void init(){
		
		Bundle bundle = getIntent().getExtras();
		menuId = bundle.getString("menuId");
		funcId = bundle.getString("funcId");
		phoneNumber = bundle.getString("phoneNumber");
		name = bundle.getString("name");
		isMusic = bundle.getString("isMusic");
		sendStr = bundle.getString("sendStr");
		selfIntr = bundle.getString("selfIntr");
		pkg = bundle.getString("pkg");
		//Log.e(tag, "menuId="+menuId+",funcId="+funcId+",phoneNumber="+phoneNumber);
		setContentView(R.layout.receive);
		this.setTitle("提示信息");
		ok = (Button) findViewById(R.id.receive_ok);
		concel = (Button) findViewById(R.id.receive_cancel);
		et = (EditText) findViewById(R.id.receive_edit);
		tv = (TextView) findViewById(R.id.recieive_msg);
		et.setText(phoneNumber);
		tv.setText("您是否希望获得"+name+"的详细信息?如果希望请确定您的手机号并点击确定,如果不希望请点击取消.");
		ok.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				final String number = et.getText().toString();
				if(number == null && "".equals(number)){
					Toast.makeText(ReceiveActivity.this, R.string.msg_null_phoneNumber, Toast.LENGTH_SHORT).show();
					return;	
				}else if(!number.matches("^(13[4-9]|15[0|1|2|7|8|9]|18[8|9]|147)\\d{8}$")){
					Toast.makeText(ReceiveActivity.this,R.string.msg_error_phoneNumber, Toast.LENGTH_SHORT).show(); 
					et.setText("");
					return;
				}
				if("1".equals(isMusic)){
					showDialog(CONFIRM_DIALOG);
					return;
				}
				//cmwapHelper.order(service, number);
				//experienceServiceBinder.order(service, number);
				showDialog(PROGRESS_DIALOG);
				new Thread(new Runnable(){
					@Override
					public void run() {
						handler.post(new Runnable(){
							@Override
							public void run() {
								//String info = experienceServiceBinder.getItemInfo(menuId, phoneNumber,isFetion);
								/*if(info == null){
									Toast.makeText(ReceiveActivity.this,"数据获取失败,请检查网络是否联通,或联系管理员", Toast.LENGTH_SHORT).show(); 
									ReceiveActivity.this.finish();
									return;
								}*/
								Intent intent = new Intent(ReceiveActivity.this,ComboActivity.class);
								Bundle b = new Bundle();
								//b.putString("info", info);
								b.putString("phoneNumber", number);
								b.putString("menuId", menuId);
								b.putString("funcId", funcId);
								b.putString("name", name);
								b.putString("pkg", pkg);
								b.putString("selfIntr", selfIntr);
								intent.putExtras(b);
								ReceiveActivity.this.startActivity(intent);
								dismissDialog(PROGRESS_DIALOG);
								ReceiveActivity.this.finish();
							}
						});
					}
				}).start();
			}
		});
		
		concel.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent("cn.infogiga.android.killprocess");
				ReceiveActivity.this.sendBroadcast(intent);
				ReceiveActivity.this.finish();
			}
		});
	}
	
	
	ServiceConnection conn = new ServiceConnection() {
	    @Override
	    public void onServiceConnected(ComponentName name, IBinder service) {
	    	experienceServiceBinder = (ExperienceServiceBinder) service;
	    	init();
	        Log.e(tag, "onServiceConnected");
	        //doInit();
	    }

	    @Override
	    public void onServiceDisconnected(ComponentName arg0) {
	    	experienceServiceBinder = null;
	    	Log.e(tag, "onServiceDisconnected");
	    }
	};


	@Override
	protected void onDestroy() {
		unbindService(conn);
		super.onDestroy();
	}
	
}
