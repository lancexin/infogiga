package cindy.util.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpToolkit {

	private static Log log = LogFactory.getLog(HttpToolkit.class);
	
	private final static String HEAD = "POST / HTTP/1.1";//http头
	private final static String CRLF = "\r\n";			//换行
	private final static String CL = "Content-Length: "; //content length
	private final static String CE = "Content-Encoding: ";//编码
	private final static String CT = "Content-Type: ";	//type
	private final static String UA = "User-Agent: ";	//ua
	private final static String AC = "Accept: ";	//accept
	
	private final static String ENCODING = "UTF-8";	//编码
	
	/**
	 * socket模拟的发送post消息
	 * @param message
	 * @param host
	 * @param port
	 * @return
	 */
	public static boolean postMessage(String message, String host, int port) {
		int length = message.getBytes().length;		//消息长度
		StringBuffer buffer = new StringBuffer(HEAD);
		buffer.append(CRLF).append(CL).append(length).append(CRLF)
			.append(CE).append(ENCODING).append(CRLF)
			.append(AC).append("text/html").append(CRLF)
			.append(UA).append("Java/1.6.0_03").append(CRLF)
			.append(CT).append("text/html").append(CRLF).append(CRLF)
			.append(message);
		String httpMessage =  buffer.toString();
		
		try {
			Socket socket = new Socket(host, port);
			OutputStream os = socket.getOutputStream();
			os.write(httpMessage.getBytes());
			os.flush();
			os.close();
		} catch (UnknownHostException e) {
			log.error("未知主机", e);
			return false;
		} catch (IOException e) {
			log.error("连接失败", e);
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param xml
	 * @param urlStr
	 * @return
	 */
	public static String sendMsg(String xml, String urlStr) {
		String callback = null;
		URL url = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		HttpURLConnection httpUrlConnection = null;
		try {
			url = new URL(urlStr);
			httpUrlConnection = (HttpURLConnection) url.openConnection();
			
			httpUrlConnection.setRequestMethod("POST");
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setRequestProperty("Charset", "UTF-8");
			httpUrlConnection.setConnectTimeout(10000); 
			httpUrlConnection.setReadTimeout(20000);
			
			outputStream = httpUrlConnection.getOutputStream();
			outputStream.write(xml.getBytes());
				
			inputStream = httpUrlConnection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
			StringBuilder buffer = new StringBuilder();
			String line = null;
			while((line = reader.readLine()) != null){
				buffer.append(line);
			}
			callback = buffer.toString();
			outputStream.close();
			inputStream.close();
			outputStream = null;
			inputStream = null;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return callback;
	}
}
