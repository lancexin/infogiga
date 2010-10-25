package test;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

public class TestGzip {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static void main(String[] args) throws HttpException, IOException {
		HttpClient http = new HttpClient(); 
		  GetMethod get = new GetMethod("http://localhost:8080/online/jqueryui/jquery-1.4.2.min.js"); 
		  try{ 
		  get.addRequestHeader("accept-encoding", "gzip,deflate"); 
		  get.addRequestHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; Alexa Toolbar; Maxthon 2.0)"); 
		  int er = http.executeMethod(get); 
		//  System.out.println(er);
		  if(er==200){ 
		   System.out.println(get.getResponseContentLength()); 
		   String html = get.getResponseBodyAsString(); 
		//   System.out.println(html); 
		   System.out.println(html.getBytes().length); 
		  } 
		}finally{ 
		   get.releaseConnection(); 
		} 

	}

}
