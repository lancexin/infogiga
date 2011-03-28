package cn.infogiga.szqb.test;

import cindy.util.HttpToolkit;

public class TestServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String s = HttpToolkit.sendMsg("","http://mm.10086.cn/portal/web/SmsRandomSendAction.do?msisdn=15268114857&type=");
		System.out.println(s);
		
		


	}

}
