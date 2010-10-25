package cn.infogiga.jsi;

import android.content.Intent;

/**
 * 绑定到js的函数接口类，负责给js传递手机内的数据信息
 * @author chroya
 *
 */
public final class JSApplicationInterface {
   
	private ApplicationManager appManager;

	public JSApplicationInterface(ApplicationManager appManager) {	
		this.appManager = appManager;
	}
	
	public JSArray getAll() {
		return appManager.getAll();
	}
	
	/**
	 * 开启一个activity
	 * @param packageName
	 * @param className
	 */
	public void start(String packageName, String className) {
		Intent intent = new Intent();
		intent.setClassName(packageName, className);
		appManager.startActivity(intent);
	}
}