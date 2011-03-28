package cn.infogiga.szqb.test;

import java.net.URL;

import cindy.util.HttpToolkit;

public class TestMMarket {
	/**
	 * http://mm.10086.cn/portal/web/SmsRandomSendAction.do
	 * msisdn=15268114857
	 * type
	 */
	static String getCodeUrl = "http://mm.10086.cn/portal/web/SmsRandomSendAction.do";
	/**
	 * http://mm.10086.cn/portal/web/checkLoginAction.do
	 * msisdn=15268114857
	 * password=156576
	 * type=logon
	 */
	static String checkLoginUrl = "http://mm.10086.cn/portal/web/checkLoginAction.do";
	
	/**
	 * http://mm.10086.cn/portal/web/orderConfirmAction.do
	 * c=300001135978
	 * devicename=NokiaNokia 5320
	 * downloadType=mobile
	 * p=7010
	 * subtype
	 */
	static String orderConformUrl = "http://mm.10086.cn/portal/web/orderConfirmAction.do";
	/**
	 *  c	300001135978
		currentpagenum	
		deviceId	2148
		downloadType	mobile
		fw	500224
		keyword	
		p	7010
		packageCode	
		subtype	
		xtype
	 */
	static String orderDowonAction = "http://mm.10086.cn/portal/web/orderDownloadAction.do";
	
	public static void main(String[] args) {
		String s2 = HttpToolkit.sendMsg("","http://mm.10086.cn/portal/web/SmsRandomSendAction.do?msisdn=15268114857&password=613753");
		System.out.println(s2);
	}
}
