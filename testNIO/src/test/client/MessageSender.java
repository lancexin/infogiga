package test.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

public class MessageSender implements Runnable{
	private static List<String> sendList = new LinkedList<String>();
	
	private String host = "localhost";
	private int post = 12345; 
	
	public MessageSender(String host,int post){
		this.host = host;
		this.post = post;
	}
	
	public static void addSendMessage(String message){
		synchronized(sendList){
			sendList.add(sendList.size(),message);
			System.out.println("add a message ,the size is:"+sendList.size());
		}
		
	}
	
	public static void startSend(){
		synchronized(sendList){
			System.out.println("start to send messages");
			//唤醒所有发送线程
			sendList.notifyAll();
		}
	}
	

	public void run() {
		while(true){
			synchronized (sendList) {
				int size = sendList.size();
				if(size == 0){
					try {
						sendList.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					continue;
				}
				
				String message = sendList.remove(0);
				handleMessage(message);
			}
		}
	}
	
	public void handleMessage(String message){
		System.out.println("发送信息："+message);
		try {
			Socket socket = new Socket(host, post);
			OutputStream os = socket.getOutputStream();
			os.write(message.getBytes());
			
			os.flush();
			os.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
