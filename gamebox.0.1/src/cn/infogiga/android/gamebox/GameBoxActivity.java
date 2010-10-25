package cn.infogiga.android.gamebox;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;

public class GameBoxActivity extends Activity{

	private static final String TAG = "GameBoxActivity";
	private static ArrayList<AppInfo> mApplications;
	HomeIconAdapter had; 
	static GridView mGrid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		loadApplocations();
		initGridView();
		
	}
	
	private void loadApplocations(){
		Resources res = this.getResources();
		String[] acts = res.getStringArray(R.array.acts);
		String[] pkgs = res.getStringArray(R.array.pkgs);
		PackageManager manager = getPackageManager();
		mApplications = new ArrayList<AppInfo>();
		for(int i=0;i<acts.length;i++){
			Intent intent = new Intent();
			System.out.println(acts[i]+"     "+pkgs[i]);
			intent.setClassName(pkgs[i],acts[i]);
			
			AppInfo appInfo = getApplicationInfo(manager,intent);
			if(appInfo != null)
				mApplications.add(appInfo);
		}
	}
	
	private static AppInfo getApplicationInfo(PackageManager manager,
			Intent intent) {
		final ResolveInfo resolveInfo = manager.resolveActivity(intent, 0);

		if (resolveInfo == null) {
			Log.e(TAG,"resolveInfo == null");
			return null;
		}

		final AppInfo info = new AppInfo();
		info.intent = intent;
		final ActivityInfo activityInfo = resolveInfo.activityInfo;
		info.icon = activityInfo.loadIcon(manager);
		if (info.title == null || info.title.length() == 0) {
			info.title = activityInfo.loadLabel(manager);
		}
		if (info.title == null) {
			info.title = "";
		}
		return info;
	}
	
	public void initGridView(){
		mGrid = (GridView) findViewById(R.id.gridview);
		had = new HomeIconAdapter(this, mApplications);
		mGrid.setAdapter(had);
		mGrid.setWillNotDraw(true);
		mGrid.setOnItemClickListener(new ApplicationLauncher());

	}
	
	private class ApplicationLauncher implements AdapterView.OnItemClickListener {
    	public void onItemClick(AdapterView parent, View v, int position, long id) {
    		AppInfo app = (AppInfo) parent.getItemAtPosition(position);
            startActivity(app.intent);
        }
    }

}
