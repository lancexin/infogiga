package cn.infogiga.android.experience;


import cn.infogiga.android.experience.service.ExperienceService;
import cn.infogiga.android.experience.service.ExperienceService.ExperienceServiceBinder;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
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

public class StartActivity extends Activity implements OnClickListener{
	
	private static final String tag = "StartActivity";
	Button ok,concel;
	EditText et;
	TextView tv;

	protected static String menuId = null;
	
	protected static String funcId = null;
	
	protected static String pkg = null;
	
	protected static String act = null;
	
	protected static String name = null;
	
	protected static String isMusic = null;
	protected static String sendStr = null;
	
	protected static String selfIntr = null;
	
	
	ExperienceServiceBinder experienceServiceBinder;
	
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
		this.setContentView(R.layout.start);
		
        ok = (Button) findViewById(R.id.start_ok);
        concel = (Button) findViewById(R.id.start_cancel);
        et = (EditText) findViewById(R.id.start_edit);
        et.setText("");
        ok.setOnClickListener(this);
        concel.setOnClickListener(this);
        
       // menuId = experienceServiceBinder.getMenuId(pkg);
       // funcId = bundle.getString("func_id");
        pkg = bundle.getString("pkg");
        act = bundle.getString("act");
        name = bundle.getString("name");
        isMusic = bundle.getString("isMusic");
        sendStr = bundle.getString("sendStr");
        selfIntr = bundle.getString("selfIntr");
        menuId = experienceServiceBinder.getMenuId(pkg);
        funcId = experienceServiceBinder.getFuncId(pkg);
        if(menuId == null || "".equals(menuId)){
        	Toast.makeText(this,"业务数据获取异常,请联系管理员", Toast.LENGTH_LONG).show();
        	finish();
        	return;
        }
        if(!experienceServiceBinder.hasLogin()){
        	Intent intent = new Intent(this,LoginActivity.class);
        	this.startActivity(intent);
        	this.finish();
        	return;
        }
        experienceServiceBinder.sendToPC(name, menuId);
	}

	@Override
	public void onClick(View v) {
		if(v == ok){
			String code = et.getText().toString();
			final String phoneNumber = et.getText().toString();
			if(phoneNumber == null && "".equals(phoneNumber)){
				Toast.makeText(this,R.string.msg_null_phoneNumber, Toast.LENGTH_SHORT).show();
				return;	
			}else if(!phoneNumber.matches("^(13[4-9]|15[0|1|2|7|8|9]|18[8|9]|147)\\d{8}$")){
				Toast.makeText(this,R.string.msg_error_phoneNumber, Toast.LENGTH_SHORT).show(); 
				return;
			}
			
			new Thread(new Runnable(){
				@Override
				public void run() {
					Bundle bundle = new Bundle();
					bundle.putString("menuId", menuId);
					bundle.putString("funcId", funcId);
					bundle.putString("phoneNumber", phoneNumber);
					bundle.putString("name", name);
					bundle.putString("isMusic", isMusic);
					bundle.putString("sendStr",sendStr);
					bundle.putString("selfIntr",selfIntr);
					bundle.putString("pkg",pkg);
					openActivity("cn.infogiga.android.experience", "cn.infogiga.android.experience.ReceiveActivity", bundle);
					
					openActivity(pkg, act);
					//开启一次M-Store体验程序
					//experienceServiceBinder.log(menuId, "3", phoneNumber, "", null, pkg);//.log(menuId, "3", phoneNumber,"3",null);
					experienceServiceBinder.log(menuId, "3", phoneNumber,"3","-",pkg);
					StartActivity.this.finish();
				}
			}).start();
			
		}else if(v == concel){

			this.finish();
		}
	}
	
	protected void openActivity(String pkg,String act){
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setClassName(pkg, act);
		this.startActivity(intent);
	}
	
	protected void openActivity(String pkg,String act,Bundle b){
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setClassName(pkg, act);
		intent.putExtras(b);
		this.startActivity(intent);
	}
	
	protected void openActivityForResult(String pkg,String act,Bundle bundle,int requestCode){
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setClassName(pkg, act);
		intent.putExtras(bundle);
		this.startActivityForResult(intent, requestCode);
	}
	
	ServiceConnection conn = new ServiceConnection() {
	    @Override
	    public void onServiceConnected(ComponentName name, IBinder service) {
	    	experienceServiceBinder = (ExperienceServiceBinder) service;
	    	init();
	        Log.e(tag, "onServiceConnected");
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
	
	private static final int WAIT_DIALOG = 10101;

	@Override
	protected Dialog onCreateDialog(int id) {
		if(id == WAIT_DIALOG){
			ProgressDialog dialog = new ProgressDialog(this);
	        dialog.setMessage("正在加载数据...");
	        dialog.setIndeterminate(true);
	        dialog.setCancelable(true);
	        return dialog;
		}
		return null;
	}
	
	Handler handler = new Handler();
}
