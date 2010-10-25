package cn.infogiga.phone;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

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
import cn.infogiga.phone.R;

public class Entry3 extends Activity {
	
	private Contact contact;
	private cn.infogiga.jsi.Message message;
	private ApplicationManager appManager;
	
	private GestureDetector gesturer;
	//主view，container
	private ViewGroup main;
	private View welcomeView;
	//当前的view
	private WebView currentView;
	//锁屏view
	private WebViewClient viewClient;
	private WebChromeClient chromeClient;
	private PictureListener picListener;
	//事件的x、y坐标
	private int x,y;
	//按下之后是否移动了
	private boolean moved;
	//是否锁屏了
//	private boolean locked = false;
	private boolean isLoading = true;
//	private WebView lockView;
	private Handler handler;
	private LayoutParams layoutParams;
	private Map<String, String> pages = new HashMap<String, String>();
	private final static Map<String, WebView> views = new HashMap<String, WebView>();
	//根目录
	private final static String baseUri = "file:///android_asset/WP7/";
	
	private final static String TAG = "Entry";
	private static String param = "";
	
	private static int WIDTH;
	private static int HEIGHT;
	
	private long lastBack = 0;
//	private int currentLoading = 0;//当前载入的页面
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initWindow();
//        initView("start");
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
    			if(currentView.equals(views.get("start"))) {
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
        
    private void initView(String name) {
		WebView view = new WebView(this);
		initWebSetting(view.getSettings());
		initWebView(view);
		view.setWebChromeClient(chromeClient);
		view.setWebViewClient(viewClient);
		view.loadUrl(baseUri+ pages.get(name));
		views.put(name, view); //view放到list里面
		Log.d(TAG, "create webview "+ name);
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
    	pages.put("Start.start", "Start/start.html");
    	pages.put("People.people", "People/people.html");
    	pages.put("People.contactDetail", "People/contactDetail.html");    	
//    	pages.put("lock", "spec/lockScreen.html");    	
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
        view.addJavascriptInterface(new JSInterface(), "base");
        view.addJavascriptInterface(new JSContactInterface(contact), "contact");
        view.addJavascriptInterface(new JSMessageInterface(message), "message");
        view.addJavascriptInterface(new JSApplicationInterface(appManager), "application");
        view.addJavascriptInterface(new JSMotionInterface(), "motion");
        view.setFocusable(false);         
    }
        
    /**
     * 绑定到js的函数接口类
     */
    final class JSInterface {
    	
    	/**
    	 * 锁屏
    	 */
    	public void lock() {
    		/*handler.post(new Runnable() {	
				public void run() {
					lockView.layout(0, -HEIGHT, WIDTH, 0);	
					main.addView(lockView);
				}
			});
    		handler.post(new Runnable() {
    			public void run() {
    				new LockScreenAnimation(-2, 46).start();    				
    			}
    		});
    		locked = true;*/
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
    		Log.d(TAG, "activate:"+name);
    		if(name == null || "".equals(name)) {
    			return;
    		}
//    		views.get(name).loadUrl(Notice.INITIALIZE);
    		handler.post(new Runnable() {
    			public void run() {
	    			showView(name);
	    			param = params;
	    			currentView.loadUrl("javascript:initialize("+ param+")");
    			}
    		});
    		/*activate(name);
    		param = params;
    		currentView = views.get("contactDetail");
    		currentView.loadUrl("javascript:initialize("+ param+")");*/
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
//    		views.get(name).loadUrl(Notice.INITIALIZE);
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