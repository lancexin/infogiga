package cindy.util;

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
			connection.setRequestProperty("Content-Encoding", "UTF-8");
			output = connection.getOutputStream();
			output.write(messageByte);
			output.close();
			input = connection.getInputStream();
			//BufferedReader reader = new BufferedReader();
			//success = true;
			input.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {	
			e.printStackTrace();
		}
		return success;
	}
	
	public static String sendMsg(String xml, String serverUrl) {
		String callback = null;
		URL url = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		HttpURLConnection httpUrlConnection = null;
		try {
			url = new URL(serverUrl);
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
