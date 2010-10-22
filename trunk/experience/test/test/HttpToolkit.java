package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
	 * 向某个地址发送post消息
	 * @param message 消息内容
	 * @param url  地址
	 * @return 是否成功
	 */
	public static boolean postMessage(String message, String url) {
		byte[] messageByte = message.getBytes();//要发送的消息
		OutputStream output = null; //对PC端的输出流
		InputStream input = null;
		URLConnection connection = null;
		boolean success = false;
		
		try {
			URL turl = new URL(url);
			connection = turl.openConnection();	
			connection.setDoOutput(true);
			connection.setDoInput(true);			
			connection.setDefaultUseCaches(false);
//			connection.setRequestMethod("POST");
//			connection.setRequestProperty("Accept", "text/html");
//			connection.setRequestProperty("Content-type", "text/html");
//			connection.setRequestProperty("Content-Length","141");
			connection.setRequestProperty("Content-Encoding", "UTF-8");
//			connection.setConnectTimeout(3);//连接超时时间
			output = connection.getOutputStream();
			output.write(messageByte);//发送
			output.close();
			input = connection.getInputStream();
			
//			log.info("response info:"+ connection.getResponseMessage());
			success = true;
			input.close();
		} catch (MalformedURLException e) {
			log.error("URL不合法！", e);
		} catch (IOException e) {	
			log.error("连接失败！", e);
		}
		return success;
	}
}
