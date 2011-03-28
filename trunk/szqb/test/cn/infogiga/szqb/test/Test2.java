package cn.infogiga.szqb.test;

import java.io.IOException;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;


public class Test2 {
	public static void main(String[] args) throws HttpException, IOException {//336526
		/*HttpClient client = new HttpClient();
		HttpMethod method=new GetMethod("http://mm.10086.cn/portal/web/SmsRandomSendAction.do?msisdn=15268114857&type=");
		method.getParams().setParameter("http.protocol.cookie-policy",CookiePolicy.BROWSER_COMPATIBILITY);
		client.executeMethod(method);
		System.out.println(method.getStatusLine());
		Cookie[] cookies = client.getState().getCookies(); 
		System.out.println(cookies.length);
		for(int i=0;i<cookies.length;i++){
			System.out.println(cookies[i].getName()+"   "+cookies[i].getValue());
		}
		System.out.println("response=" + method.getResponseBodyAsString());
	    method.releaseConnection();*/
		
		/*HttpClient client = new HttpClient();
		HttpMethod method=new GetMethod("http://mm.10086.cn/portal/web/checkLoginAction.do?msisdn=15268114857&password=412648&type=logon");
		method.getParams().setParameter("http.protocol.cookie-policy",CookiePolicy.IGNORE_COOKIES);
		method.setRequestHeader("Cookie", "JSESSIONID=57B679708612D4A8A8791F917F09FB5F.mmpps;MMwwwURLtel=f488bf7bf488bb97993cba97;us=120.199.13.26.1299825719816379;userLogontype=sms");
		client.executeMethod(method);
		System.out.println("response=" + method.getResponseBodyAsString());
	    method.releaseConnection();*/
		
		/*HttpClient client = new HttpClient();
		HttpMethod method=new GetMethod("http://mm.10086.cn/portal/web/orderConfirmAction.do?c=300001135978&currentpagenum=&deviceId=2148&downloadType=mobile&fw=500224&keyword=&p=7010&packageCode=&subtype=&xtype=");
		method.getParams().setParameter("http.protocol.cookie-policy",CookiePolicy.IGNORE_COOKIES);
		method.setRequestHeader("Cookie", "JSESSIONID=57B679708612D4A8A8791F917F09FB5F.mmpps;MMwwwURLtel=f488bf7bf488bb97993cba97;us=120.199.13.26.1299825719816379;userLogontype=sms");
		client.executeMethod(method);
		System.out.println("response=" + method.getResponseBodyAsString());
	    method.releaseConnection();*/
	    
		HttpClient client = new HttpClient();
		HttpMethod method=new GetMethod("http://mm.10086.cn/portal/web/orderDownloadAction.do?c=300001135978&currentpagenum=&deviceId=2148&downloadType=mobile&fw=500224&keyword=&p=7010&packageCode=&subtype=&xtype=");
		method.getParams().setParameter("http.protocol.cookie-policy",CookiePolicy.IGNORE_COOKIES);
		method.setRequestHeader("Cookie", "JSESSIONID=57B679708612D4A8A8791F917F09FB5F.mmpps;MMwwwURLtel=f488bf7bf488bb97993cba97;us=120.199.13.26.1299825719816379;userLogontype=sms");
		client.executeMethod(method);
		System.out.println("response=" + method.getResponseBodyAsString());
	    method.releaseConnection();
	}
}
