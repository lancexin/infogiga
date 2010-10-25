package cn.infogiga.jsi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.infogiga.phone.Entry;
import cn.infogiga.util.Toolkit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * 应用程序管理
 * @author chroya
 *
 */
public class ApplicationManager extends Activity {
	
	private static final String TAG = "AppManager";
	//js里面application的变量定义头
	private static final String VAR_NAME_APP = "var Data_application=";
	//application的js文件路径
	private static final String APP_PATH = "data/js/application.js";
	private Context context;
	private JSArray appList;
		
	public ApplicationManager(Context context) {
		this.context = context;	
//		initCache();
	}
	
	/**
	 * 初始化缓存
	 */
	public void initCache() {
//		appList = getAll();
		try {
			writeApplications();
		} catch (JSONException e) {
			Log.e(TAG, "json exception:"+e);
		}
	}
	
	/**
	 * 从缓存读取数据
	 * @return
	 */
	public JSArray getAllFromCache() {
		if(appList == null) {
			appList = getAll();
		}
		return appList;
	}
	
	/**
	 * 获取所有应用程序信息
	 * @return
	 */
	public JSArray getAll() {
		PackageManager pm = context.getPackageManager();
		Intent mainIntent = new Intent(Intent.ACTION_MAIN);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> resolveList = pm.queryIntentActivities(mainIntent, 0);
		
		return getApplications(resolveList, pm);
	}	
	
	private JSArray getApplications(List<ResolveInfo> list, PackageManager pm) {
		JSArray array = new JSArray();
		for(ResolveInfo info: list) {
			String name = info.loadLabel(pm).toString();
			String pkg = info.activityInfo.packageName;
			String cls = info.activityInfo.name;
			String iconPath = "";
			Drawable drawable = info.loadIcon(pm);	
			if(drawable instanceof BitmapDrawable) {
				BitmapDrawable bd = (BitmapDrawable) drawable;
				Icon icon = new Icon(cls);
				iconPath = icon.createIcon(bd.getBitmap());				
			}
			AppInfo appInfo = new AppInfo();
			appInfo.cls = cls;
			appInfo.pkg = pkg;
			appInfo.name = name;
			appInfo.icon = iconPath;
			
			array.add(appInfo);
		}
		
		return array;
	}
	
	private boolean writeApplications() throws JSONException {
		JSArray list = getAll();
		JSONArray json = new JSONArray();
		for(int index=0; index<list.length(); index++) {
			AppInfo app = (AppInfo) list.get(index);
			JSONObject obj = new JSONObject();
			obj.put("name", app.name);
			obj.put("pkg", app.pkg);
			obj.put("cls", app.cls);
			obj.put("icon", app.icon);
			json.put(obj);
		}
		String app = json.toString();
		String jsContent = VAR_NAME_APP+ app;
		return Toolkit.write(jsContent, Entry.path+ APP_PATH);
	}
	
	/**
	 * icon类
	 *
	 */
	class Icon {
		private String name;
		
		public Icon(String name) {
			this.name = name;
		}
		
		/**
		 * 此icon是否存在
		 * @return
		 */
		public boolean exists() {
			return context.getFileStreamPath(name).exists();
		}
		
		/**
		 * 创建icon
		 * @param bmp
		 * @return
		 */
		public String createIcon(Bitmap bmp) {
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
			bmp = Bitmap.createScaledBitmap(bmp, 40, 40, false);
			return bmp.compress(CompressFormat.PNG, 70, fos)?
					"file:///"+ context.getFileStreamPath(name).getAbsolutePath():null;
		}
	}
	
	class AppInfo {
		String name;
		String pkg;
		String cls;
		String icon;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPkg() {
			return pkg;
		}
		public void setPkg(String pkg) {
			this.pkg = pkg;
		}
		public String getCls() {
			return cls;
		}
		public void setCls(String cls) {
			this.cls = cls;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}		
	}
}