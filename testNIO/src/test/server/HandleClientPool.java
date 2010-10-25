package test.server;

import java.nio.channels.SocketChannel;
import java.util.HashMap;

import java.util.Map;

public class HandleClientPool {
	private  static Map<SocketChannel,HandleClient> clientPool = new HashMap<SocketChannel,HandleClient>();
	
	public static HandleClient getClient(SocketChannel channel){
		synchronized (clientPool) {
			HandleClient handleClient = clientPool.get(channel);
			if(handleClient == null){
				handleClient = new HandleClient();
				clientPool.put(channel,handleClient);
				System.out.println("没有这个实例，创建一个,还有"+clientPool.size()+"个实例");
			}
			return handleClient;
		}
	}
	
	public static void removeClient(SocketChannel channel){
		synchronized (clientPool) {
			clientPool.remove(channel);
			System.out.println("删除一个实例,还有"+clientPool.size()+"个实例");
		}
	}
	
}
