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
import cn.infogiga.jsi.JSContactInterface;
import cn.infogiga.jsi.JSMessageInterface;
import cn.infogiga.jsi.Message;

public class Res extends Activity {
	
	public static void getContactInterface() {
//		new JSContactInterface(new Contact(this));
	}
}