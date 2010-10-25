package cn.infogiga.phone;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Picture;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebView.PictureListener;
import cn.infogiga.jsi.ApplicationManager;
import cn.infogiga.jsi.Contact;
import cn.infogiga.jsi.JSApplicationInterface;
import cn.infogiga.jsi.JSArray;
import cn.infogiga.jsi.JSContactInterface;
import cn.infogiga.jsi.JSMessageInterface;

public class Entry extends Activity {
	
	private Contact contact;
	private cn.infogiga.jsi.Message message;
	private ApplicationManager appManager;
	
	private GestureDetector gesturer;
	//主view，container
	private ViewGroup main;
	private View welcomeView;
	private WebView currentView;
	//当前的view
	//锁屏view
	private WebViewClient viewClient;
	private WebChromeClient chromeClient;
	private PictureListener picListener;
	//事件的x、y坐标
	private int x,y;
	//按下之后是否移动了
	private boolean moved;
	//是否加载中
	private boolean isLoading = true;
	private Handler handler;
	private LayoutParams layoutParams;
	private Map<String, String> pages = new HashMap<String, String>();
	private final static Map<String, WebView> views = new HashMap<String, WebView>();
	//根目录
	//private final static String baseUri = "file:///android_asset/WP/page/";
	private final static String BASE = "WP7";
	private final static String assetUri = "file:///android_asset/WP7/";
	
	/**
	 * 程序的根目录，形如file:///path，用于loadurl和页面调用
	 */
	private static String baseUri;	
	/**
	 * 文件根目录，形如/data/data的形式
	 */
	public static String path; 
	//程序名字
	private final static String APPNAME = "WinPhone7";
	//日志tag
	private final static String TAG = "Entry";
	private static String param = "";
	
	private static int WIDTH;
	private static int HEIGHT;
	//上次按back键的时间
	private long lastBack = 0;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   
        initBaseDir();
        updatePage();
        init();
        initWindow();
        initWebView();  
    }
        
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
    	int dx, dy;
    	if(isLoading) return super.dispatchTouchEvent(event);
    	
    	int action = event.getAction();
    	switch (action) {
    	case MotionEvent.ACTION_DOWN:
    		x = (int) event.getX();
    		y = (int) event.getY();
    		currentView.loadUrl(Notice.TOUCHDOWN);
    		break;
    	case MotionEvent.ACTION_MOVE:
    		dx = (int) event.getX() - x;
    		dy = (int) event.getY() - y;
    		x = (int) event.getX();
    		y = (int) event.getY();
    		Log.d(TAG, "dx:"+dx+",dy:"+dy);
    		if(dx > -5 && dx <5 && dy > -5 && dy < 5) {
    		} else {
    			moved = true;
    		}
    		break;
    	case MotionEvent.ACTION_UP:
    		if(!moved) { //直接点击的事件，模拟一个下去
    			Log.d(TAG, "click event");
    			event.setAction(MotionEvent.ACTION_DOWN);    			
    			currentView.dispatchTouchEvent(event);
    			event.setAction(MotionEvent.ACTION_UP);
    			currentView.dispatchTouchEvent(event);
    		}
    		moved = false;
    		x = (int) event.getX();
    		y = (int) event.getY();
    		currentView.loadUrl(Notice.TOUCHUP);    				
    	}
    	return true;
    }
        
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {  
    	if(isLoading) return super.dispatchKeyEvent(event);
    	int action = event.getAction();
    	if(action == KeyEvent.ACTION_DOWN) {
    		int keyCode = event.getKeyCode();
    		switch(keyCode) {
    		case KeyEvent.KEYCODE_MENU:
        		Log.d(TAG, "menu");        		
        		currentView.loadUrl(Notice.MENUKEY);
        		break;
    		case KeyEvent.KEYCODE_BACK:
    			if(currentView.equals(views.get("Start.start"))) {
    				if(lastBack == 0) {
    					lastBack = System.currentTimeMillis();
    				} else {//两次back，退出
    					if(System.currentTimeMillis() - lastBack < 1000) {
    						Log.d(TAG, "back in start twice:finish");
    						finish();
    					}
    					lastBack = System.currentTimeMillis();
    				}
    			}
    			currentView.loadUrl(Notice.BACKKEY);
    			return true;
        	}
    	}
    	return super.dispatchKeyEvent(event);
    }
    
    /**
     * 主窗口的初始化
     */
    private void initWindow() {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	setContentView(R.layout.main);
    	main = (ViewGroup) findViewById(R.id.container);
    	main.setBackgroundColor(Color.BLACK);    	
    	welcomeView = findViewById(R.id.welcome);
    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    
    /**
     * 根据初始化信息，创建出所有webview
     */
    private void initWebView() {
    	Set<String> set = pages.keySet();
    	Iterator<String> iterator = set.iterator();
    	
    	
    	while(iterator.hasNext()) {
    		String name = iterator.next();
    		WebView view = new WebView(this);    		
    		initWebSetting(view.getSettings());
    		initWebView(view);
    		view.setWebChromeClient(chromeClient);
    		view.setWebViewClient(viewClient);
    		view.loadUrl(baseUri+ pages.get(name));
    		views.put(name, view); //view放到list里面
//    		main.addView(view, layoutParams);
    	}
    	Log.d(TAG, "create all webview complete, size:"+views.size());
    }
    
    /**
     * 根据名字显示view
     * @param name
     */
    private void showView(String name) {    	
    	WebView view = views.get(name);
    	if(view == null) {
    		Log.e(TAG, "Can't show view:"+name);
    		return;
    	}
    	main.addView(view, layoutParams);
//    	main.bringChildToFront(view);
//    	view.bringToFront();
    	currentView = view;
    	Log.d(TAG, "show view:"+name);
    }
    
    /**
     * 隐藏某个view
     * @param name
     */
    private void hideView(String name) {
    	WebView view = views.get(name);  
    	if(view == null) {
    		Log.e(TAG, "Can't hide view:"+name);
    		return;
    	}
    	main.removeView(view);
    	Log.d(TAG, "hide view:"+name);
    }
    
    /**
     * 初始化本地变量
     */
    private void init() {
    	contact = new Contact(this);
    	message = new cn.infogiga.jsi.Message(this);
    	appManager = new ApplicationManager(this);
    	WIDTH = getWindowManager().getDefaultDisplay().getWidth();
    	HEIGHT = getWindowManager().getDefaultDisplay().getHeight();
    	gesturer = new GestureDetector(this, new GestureListener());
    	viewClient = new ViewClient();
    	chromeClient = new ChromeClient();
    	picListener = new PicListener();
    	handler = new Handler();
    	layoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
    	//pages.put("start", "start.html");    	
    	pages.put("Start.start", "Start/start.html");
    	pages.put("People.people", "People/people.html");
    	pages.put("People.contactDetail", "People/contactDetail.html");
    	Log.d(TAG, "init page's path");
    }
    
    /**
     * 初始化websetting
     * @param setting
     */
    private void initWebSetting(WebSettings setting) {
    	setting.setJavaScriptEnabled(true);
    }
    
    /**
     * 初始化webview，进行必须的设置
     * @param view
     */
    private void initWebView(WebView view) {
    	view.setVerticalScrollBarEnabled(false);
        view.setHorizontalScrollBarEnabled(false);
        view.setBackgroundColor(0);
        view.addJavascriptInterface(new JSInterface(), "base");
        view.addJavascriptInterface(new JSContactInterface(contact), "contact");
        view.addJavascriptInterface(new JSMessageInterface(message), "message");
        view.addJavascriptInterface(new JSApplicationInterface(appManager), "application");
        view.addJavascriptInterface(new JSMotionInterface(), "motion");
        view.setFocusable(false);         
    }
    
    private void initBaseDir() {
    	File baseDir = getFilesDir();	//目标文件夹files
    	path = baseDir.getAbsolutePath()+ File.separator+ APPNAME+ File.separator;
    	baseUri = "file://"+ path;
    }
    
    /**
     * 将页面文件从assets文件夹中复制到指定目录
     * @param path		目标目录
     */
    private void updatePage() {    	
    	try {
			copyFolder(BASE, new File(path));
		} catch (IOException e) {
			Log.e(TAG, "IO Exception:"+ e);
		}
		Log.d(TAG, "copy file complete");
    }
    
    /**
     * 复制文件夹
     * @param src		源文件夹
     * @param dest		目标文件夹
     * @throws IOException
     */
    private void copyFolder(String src, File dest) throws IOException {    	
    	if(!dest.exists()) {
    		dest.mkdir();
    	}
    	String[] children = getAssets().list(src);
    	for(String path: children) {
    		if(path.indexOf(".") == -1) {//是目录
    			copyFolder(src+ File.separator+ path, new File(dest.getPath()+ File.separator+ path));
    		} else if(path.endsWith("jpg") || path.endsWith("png") || path.endsWith("gif")) {
    			copyByteFile(src+ File.separator+ path, new File(dest.getPath()+ File.separator+ path));
    		} else { //文件
    			copyFile(src+ File.separator+ path, new File(dest.getPath()+ File.separator+ path));
    		}
    	}
    }
    
    /**
     * 字节的形式复制文件
     * @param src
     * @param dest
     * @throws IOException
     */
    private void copyByteFile(String src, File dest) throws IOException {
    	if(!dest.exists()) {
    		dest.createNewFile();
    	}
    	
    	InputStream is = getAssets().open(src);
    	int size = is.available();
    	BufferedInputStream bis = new BufferedInputStream(is, size>0?size:1024);
    	BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest), size>0?size:1024);
    	byte[] buffer = new byte[1024];
    	while(bis.read(buffer) != -1) {
    		bos.write(buffer);
    	}
    	bos.close();
    	bis.close();
    }
    
    /**
     * 复制文件
     * @param src		源文件路径
     * @param dest		目标文件
     * @throws IOException	创建或写入目标文件失败，会抛出IO异常
     */
    private void copyFile(String src, File dest) throws IOException {
    	if(!dest.exists()) {
    		dest.createNewFile();
    	}
    	
    	InputStream is = getAssets().open(src);
    	int size = is.available();
    	BufferedReader reader = new BufferedReader(new InputStreamReader(is), size>0?size:1024);
    	BufferedWriter writer = new BufferedWriter(new FileWriter(dest), size>0?size:1024);
    	String LF = "\r\n";
    	String buffer = "";
    	while((buffer = reader.readLine()) != null) {
    		writer.write(buffer+ LF);
    	}
    	reader.close();
    	writer.close();
    }        
        
    /**
     * 绑定到js的函数接口类
     */
    final class JSInterface {  	
    	
    	public void debug(String info) {
    		Log.d("Page", " info:"+info);
    	}
    	
    	/**
    	 * 初始化页面
    	 * <br/>
    	 * js调用：window.base.initialize("start", "start.html");
    	 * @param name	页面名字
    	 * @param path	相对路径
    	 */
    	public void initialize(String name, String path) {
    		pages.put(name, path);
    	}
    	
    	/**
    	 * 打开一个程序
    	 * @param packageName	包名
    	 * @param className		类名
    	 */
    	public void start(String packageName, String className) {
    		Intent intent = new Intent();
    		intent.setClassName(packageName, className);
    		startActivity(intent);
    	}
    	
    	/**
    	 * 带参数的activate
    	 * @param name
    	 * @param params
    	 */
    	public void activate(final String name, final String params) {
    		Log.d(TAG, "activate:"+name+",param:"+params);
    		if(name == null || "".equals(name)) {
    			return;
    		}
    		handler.post(new Runnable() {
    			public void run() {
	    			showView(name);
	    			param = params;
	    			currentView.loadUrl("javascript:initialize("+ param+")");
    			}
    		});
    	}
    	
    	/**
    	 * 激活页面
    	 * @param name
    	 */
    	public void activate(final String name) {
    		Log.d(TAG, "activate:"+name);
    		if(name == null || "".equals(name)) {
    			return;
    		}
    		handler.post(new Runnable() {
    			public void run() {
	    			showView(name);
    			}
    		});
    	}
    	
    	/**
    	 * 移除页面
    	 * @param name
    	 */
    	public void deactivate(final String name) {
    		Log.d(TAG, "deactivate:"+name);    		
    		if(name == null || "".equals(name)) {
    			return;
    		}
    		handler.post(new Runnable() {
    			public void run() {
    				hideView(name);    	
    			}
    		});
    	}
    	
    	/**
    	 * 刷新页面
    	 * @param name
    	 */
    	public void reload(String name) {
    		Log.d(TAG, "reload "+ name);
    		views.get(name).reload();
    	}
    }
    
    /**
     * 事件接口
     */
    final class JSMotionInterface {    	
    	/**
    	 * 获取x坐标
    	 * @return
    	 */
    	public int getX() {
    		return x;
    	}
    	
    	/**
    	 * 获取y坐标
    	 * @return
    	 */
    	public int getY() {
    		return y;
    	}
    	
    	/**
    	 * 获取位置
    	 * @return 二维整型数组，0是x坐标，1是y坐标<br/>
    	 * 	JS中获取方式：<br/>
    	 * 	x = window.motion.getXY().get(0),<br/>
    	 * 	y = window.motion.getXY().get(1);
    	 */
    	public JSArray getXY() {
    		return new JSArray(new Integer[]{x,y});
    	}
    	
    	/**
    	 * 获取位置
    	 * @return 位置对象<br/>
    	 * 	获取方式x = window.motion.getPosition().getX();
    	 */
    	public Position getPosition() {
    		return new Position();
    	}
    	
    	private class Position {
    		public int getX() {
    			return x;
    		}
    		public int getY() {
    			return y;
    		}
    	}
    }
    
    /**
     * 手势监听
     */
    class GestureListener extends SimpleOnGestureListener {
    	
    	@Override
    	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
    		return true;
    	}
       
    	@Override
    	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    		if(velocityY <= -1000) {//向上    			
    		}
    		if(velocityY >= 1000) {//向下
    		}
    		return true;
    	}
    	
    }
    
    /**
     * 图片监听
     */
    class PicListener implements PictureListener {

		@Override
		public void onNewPicture(WebView view, Picture picture) {
			
		}
    	
    }
    
    /**
     * WebView的WebViewClient,处理各种通知、请求事件
     */
    class ViewClient extends WebViewClient {
    	
    	@Override
    	public void onLoadResource(WebView view, String url) {
    		Log.d(TAG, "resource:"+ url+" has loaded");
    	}
    	
    	@Override
    	public void onPageStarted(WebView view, String url, Bitmap favicon) {
    		Log.d(TAG, "page:"+ url+" start");    		
    	}
    	
    	@Override
    	public void onPageFinished(WebView view, String url) {
    		Log.d(TAG, "page:"+ url+" has loaded");  
    		if(view == views.get("Start.start")) {
    			showView("Start.start");
    			currentView.loadUrl(Notice.INITIALIZE);
    			//载入完毕之后再初始化数据
    			main.removeView(welcomeView);
    			isLoading = false;
    		}
    	}
    }
     
    /**
     * WebView的WebChromeClient,处理Javascript的对话框，网站图标，网站title，加载进度等
     */
    class ChromeClient extends WebChromeClient {
    	
    	@Override
    	public void onCloseWindow(WebView window) {
    		
    	}
    	
    	@Override
    	public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, Message resultMsg) {
    		return super.onCreateWindow(view, dialog, userGesture, resultMsg);
    	}
    	
    	@Override
    	public void onProgressChanged(WebView view, int newProgress) {
    		
    	}
    	
    	@Override
    	public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
    		return super.onJsAlert(view, url, message, result);
    	}
    	
    	@Override
    	public boolean onJsBeforeUnload(WebView  view, String  url, String  message, JsResult  result) {
    		return super.onJsBeforeUnload(view, url, message, result);
    	}
    	
    	@Override
    	public void onReceivedTitle(WebView view, String title) {
    		
    	}
    	
    	@Override
    	public void onReceivedIcon(WebView view, Bitmap icon) {
    		
    	}
    	
    	@Override
    	public void onRequestFocus(WebView view) {
    		
    	}
    }
}
