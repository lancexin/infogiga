package cn.infogiga.phone;

/**
 *
 *
 */
class WebViewAnimation {
   
	public static int accelerate(int t, int v, int a) {
		return (int) (v*t+ a*Math.pow(t, 2)/2);
	}
}