package cn.infogiga.android.experience;

import java.io.File;

import cn.infogiga.android.experience.bean.ErrInfo;
import cn.infogiga.android.experience.bean.Intf;
import cn.infogiga.android.experience.service.ExperienceService;
import cn.infogiga.android.experience.service.ExperienceUtil;
import cn.infogiga.android.experience.service.ExperienceService.ExperienceServiceBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{
    
	
	private static final String tag = "LoginActivity";
	
	ExperienceServiceBinder experienceServiceBinder;
	Button ok,concel;
	EditText et,pwd;
	TextView tv;


	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
        	Intent i = new Intent();
            i.setClass(this, ExperienceService.class);
            this.startService(i);
			bindService(i, conn, Context.BIND_AUTO_CREATE);
		} catch (RuntimeException e) {
			Toast.makeText(this,R.string.no_service, Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
    }
    
    public void init(){
    	setContentView(R.layout.login);
    	
    	 ok = (Button) findViewById(R.id.reg_ok);
         concel = (Button) findViewById(R.id.reg_cancel);
         et = (EditText) findViewById(R.id.reg_edit);
         tv = (TextView) findViewById(R.id.reg_str);
         ok.setOnClickListener(this);
         concel.setOnClickListener(this);
         pwd = (EditText) findViewById(R.id.reg_pwd);
        if(experienceServiceBinder.hasLogin()){
        	String empNo = experienceServiceBinder.getEmpNo();
        	tv.setText("当前用户已经登录,登录员工编号为："+empNo+",如需修改请填入要修改的员工编号：");
        }
    }

	@Override
	public void onClick(View v) {
		if(v == ok){
			String code = et.getText().toString();
			String p = pwd.getText().toString();
			if(code == null || "".equals(code.trim())){
				Toast.makeText(this,R.string.not_null, Toast.LENGTH_LONG).show();
				return;
			}
			if(p == null || "".equals(p.trim())){
				Toast.makeText(this,R.string.not_null, Toast.LENGTH_LONG).show();
				return;
			}
			Intf intf = experienceServiceBinder.login(code,p);
			ErrInfo errInfo = intf.getErrinfo();
			if(errInfo == null){
				Toast.makeText(this,R.string.msg_error_code, Toast.LENGTH_LONG).show();
    			return;
			}
			
			if(!"0".equals(errInfo.getErrCode())){
				Toast.makeText(this,errInfo.getErrHint(), Toast.LENGTH_LONG).show();
    			return;
			}
			experienceServiceBinder.setLoginState(true);
    		Log.e(tag, "reg callback:"+errInfo.getErrMsg());
    		Toast.makeText(this,R.string.login_success, Toast.LENGTH_SHORT).show();
    		
    		this.finish();
		}else if(v == concel){
			Log.e(tag, "reg concel");
			this.finish();
		}
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
	    	Log.e(tag, "onServiceDisconnected");
	    }
	};


	@Override
	protected void onDestroy() {
		unbindService(conn);
		super.onDestroy();
	}
}