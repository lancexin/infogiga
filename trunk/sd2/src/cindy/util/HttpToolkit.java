package cindy.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;


public class HttpToolkit {


	
	private final static String HEAD = "POST / HTTP/1.1";
	private final static String CRLF = "\r\n";			
	private final static String CL = "Content-Length: "; 
	private final static String CE = "Content-Encoding: ";
	private final static String CT = "Content-Type: ";
	private final static String UA = "User-Agent: ";	
	private final static String AC = "Accept: ";	
	private final static String ENCODING = "UTF-8";
	
	/**
	 * http post send message by socket
	 * @param message
	 * @param host
	 * @param port
	 * @return send success or not
	 */
	public static boolean postMessage(byte[] message, String host, int port) {
		int length = message.length;		
		StringBuffer buffer = new StringBuffer(HEAD);
		buffer.append(CRLF).append(CL).append(length).append(CRLF)
			.append(CE).append(ENCODING).append(CRLF)
			.append(AC).append("text/html").append(CRLF)
			.append(UA).append("Java/1.6.0_03").append(CRLF)
			.append(CT).append("InfoGiga-MMS-1").append(CRLF).append(CRLF);
		String httpMessage =  buffer.toString();
		
		try {
			Socket socket = new Socket(host, port);
			OutputStream os = socket.getOutputStream();
			os.write(httpMessage.getBytes());
			os.write(message);
			os.flush();
			os.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * send message by UrlConnection
	 * @param message 
	 * @param url  
	 * @return boolean
	 */
	public static boolean postMessage(String message, String url) {
		byte[] messageByte = message.getBytes();
		OutputStream output = null; 
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
//			connection.setConnectTimeout(3);
			output = connection.getOutputStream();
			output.write(messageByte);
			output.close();
			input = connection.getInputStream();
			
//			log.info("response info:"+ connection.getResponseMessage());
			success = true;
			input.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {	
			e.printStackTrace();
		}
		return success;
	}
}
