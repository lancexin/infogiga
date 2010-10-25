package cn.infogiga.phone;

import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;

/**
 * 手势监听
 * @author chroya
 *
 */
class GestureListener extends SimpleOnGestureListener {
   
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		return super.onFling(e1, e2, velocityX, velocityY);
	}
	
}