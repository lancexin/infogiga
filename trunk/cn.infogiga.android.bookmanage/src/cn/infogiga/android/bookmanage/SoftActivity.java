package cn.infogiga.android.bookmanage;



import android.app.Activity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;


public class SoftActivity extends Activity{
	private static final String tag = "SoftActivity";
	
	protected static final int CALLBACK_ACTIVITY_ORDER = 101;
	
	protected static final int INSERT_PHONENUMBER_DIALOG = 1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle b = new Bundle();
		b.putString("menu_id", getString(R.string.menu_id));
		b.putString("func_id",getString(R.string.func_id));
		b.putString("pkg", getString(R.string.pkg));
		b.putString("act", getString(R.string.act));
		b.putString("name", getString(R.string.exp_name));
		b.putString("selfIntr", getString(R.string.self_intr));
		openActivity(getString(R.string.open_pkg),getString(R.string.open_act),b);
		this.finish(); 
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
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
