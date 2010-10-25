package test.client;

public class SendMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//发送的进程数
		int socketCount = 200;
		//发送信息条数
		int messageCount = 1000;
		
		for(int i=0;i<socketCount;i++){
			//发送地址localhost，端口12345
			new Thread(new MessageSender("localhost",12345),"t"+i).start();
		}
		try {
			//等待10s待所有Thread初始化完成
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//添加发送的信息
		for(int i=0;i<messageCount;i++){
			MessageSender.addSendMessage("这是一条发送信息"+i);
		}
		
		try {
			System.out.println(".......5s钟之后开始发送......");
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//开始发送
		MessageSender.startSend();
	}

}
