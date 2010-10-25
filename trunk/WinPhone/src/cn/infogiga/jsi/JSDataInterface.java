package cn.infogiga.jsi;

/**
 * 绑定到js的函数接口类，负责给js传递手机内的数据信息
 * @author chroya
 *
 */
final class JSDataInterface {
   
	public int getSignalStrength() {
		return 100;
	}
}