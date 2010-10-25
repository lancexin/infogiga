package cn.infogiga.phone;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Picture;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
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
import cn.infogiga.jsi.JSMessageInterface;
import cn.infogiga.jsi.Message;

public class Entry2 extends Activity {
	
	private Contact contact;
	private GestureDetector gesturer;
	//主view，container
	private WebView main;
	//锁屏view
	private WebViewClient viewClient;
	private WebChromeClient chromeClient;
	private PictureListener picListener;
	//事件的x、y坐标
	private int x,y;
	//按下之后是否移动了
	private boolean moved;
	//是否锁屏了
	private boolean locked;
	private Handler handler;
	private LayoutParams layoutParams;
	//首页
	private final static String page = "file:///android_asset/WP2/apptest.html";
	
	private final static String TAG = "Entry";
	
	private static int WIDTH;
	private static int HEIGHT;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initWindow();     
    }
    
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
    	/*int dx, dy;
    	if(locked) {
    		Log.d(TAG, "is LockScreen Page");    		
    	}
    	int action = event.getAction();
    	switch (action) {
    	case MotionEvent.ACTION_DOWN:
    		x = (int) event.getX();
    		y = (int) event.getY();
    		main.loadUrl("javascript:Action.down();");
    		break;
    	case MotionEvent.ACTION_MOVE:
    		dx = (int) event.getX() - x;
    		dy = (int) event.getY() - y;
    		x = (int) event.getX();
    		y = (int) event.getY();
    		if(dx > -10 && dx <10 && dy > -10 && dy < 10) {
    		} else {
    			moved = true;
    		}
    		break;
    	case MotionEvent.ACTION_UP:
    		if(!moved) { //直接点击的事件，模拟一个下去
    			Log.d(TAG, "click event");
    			event.setAction(MotionEvent.ACTION_DOWN);    			
    			main.dispatchTouchEvent(event);
    			event.setAction(MotionEvent.ACTION_UP);
    			main.dispatchTouchEvent(event);
    		}
    		moved = false;
    		x = (int) event.getX();
    		y = (int) event.getY();
    		main.loadUrl("javascript:Action.up();");    				
    	}
    	return true;*/
    	return super.dispatchTouchEvent(event);
    }
    
    /**
     * 主窗口的初始化
     */
    private void initWindow() {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);    	
    	main = new WebView(this);
    	main.setBackgroundColor(Color.BLACK);
    	main.setWebChromeClient(chromeClient);
    	main.setWebViewClient(viewClient);
    	main.setVerticalScrollBarEnabled(false);
    	main.setHorizontalScrollBarEnabled(false);
    	main.addJavascriptInterface(new JSMessageInterface(new Message(this)), "message");
    	main.addJavascriptInterface(new JSApplicationInterface(new ApplicationManager(this)), "application");
    	WebSettings setting = main.getSettings();
    	setting.setJavaScriptEnabled(true);
    	main.setFocusable(false); 
    	setting.setLightTouchEnabled(true);
    	setting.setNeedInitialFocus(false);    	
    	main.loadUrl(page); //载入初始化页面
    	setContentView(main);
    	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }    
    
    //显示webview
    private void show() {
//    	((ViewGroup)findViewById(R.id.container)).addView(main);
    }    
    
    /**
     * 初始化本地变量
     */
    private void init() {
    	contact = new Contact(this);
    	WIDTH = getWindowManager().getDefaultDisplay().getWidth();
    	HEIGHT = getWindowManager().getDefaultDisplay().getHeight();
    	gesturer = new GestureDetector(this, new GestureListener());
    	viewClient = new ViewClient();
    	chromeClient = new ChromeClient();
    	picListener = new PicListener();
    	handler = new Handler();
    	layoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);    	
    	Log.d(TAG, "init page's path");
    }      
        
    /**
     * 绑定到js的函数接口类
     */
    final class JSInterface {
    	 /**
    	 * 锁屏
    	 */
    	public void lock() {
    		 locked = true;
    	 }
    	 
    	 /**
    	 * 解锁
    	 */
    	public void unlock() {
    		 locked = false;
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
    		show();
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
    

    
    /**
     * 锁屏动画
     */
    /*class LockScreenAnimation {
    	//采样率
    	private static final int RATE = 50;
    	//时间
    	private int t = 0;
    	//初始速度
    	private int v;
    	//加速度
    	private int a;
    	private Timer timer;
    	    	
    	public LockScreenAnimation(int a, int v) {
			this.a = a;
			this.v = v;
			timer = new Timer();
		}
    	
		public void start() {	
    		timer.schedule(new TimerTask(){

				@Override
				public void run() {
					final int offset = WebViewAnimation.accelerate(t, v, a)- HEIGHT;
										
					if(a*t+v <= 0 || offset >= 0) {
						handler.post(new Runnable() {//先滚动到底部
							public void run() {
								lockView.layout(0, 0, WIDTH, HEIGHT);							
							}
						});
						timer.cancel();
					} else {
						handler.post(new Runnable() {
							public void run() {
								lockView.layout(0, offset, WIDTH, offset+ HEIGHT);							
							}
						});
						Log.d(TAG, "offset:"+offset+",speed:"+ (a*t+v));
					}
					t += 1; //下一个采样点
				}
    			
    		}, 0, RATE);
    	}    	
    }    */
    

}