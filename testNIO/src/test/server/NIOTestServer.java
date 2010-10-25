package test.server;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;



public class NIOTestServer {
	
	
	ServerSocketChannel server;
	Selector selector;
	protected ByteBuffer clientBuffer = ByteBuffer.allocate(10);
	
	protected CharsetDecoder decoder;
	
	public NIOTestServer(int port) throws IOException{
		selector = this.getSelector(port);
		Charset charset = Charset.forName("GB2312");
		decoder = charset.newDecoder();
	}
	
	protected void handleKey(SelectionKey key) throws IOException {
		if (key.isAcceptable()) { // 接收请求
			System.out.println("----------接收请求-------------");
			ServerSocketChannel server = (ServerSocketChannel) key.channel();
			SocketChannel channel = server.accept();
			channel.configureBlocking(false);
			channel.register(selector, SelectionKey.OP_READ);
		} else if (key.isReadable()) { // 读信息
			System.out.println("----------读信息-------------");
			SocketChannel channel = (SocketChannel) key.channel();
			HandleClient handleClient = HandleClientPool.getClient(channel);
			int count = channel.read(handleClient.totalByte);
			if (count > 0) {
				//System.out.println("count:"+count);
				//int position = handleClient.totalByte.position();
				//System.out.println("position:"+position);
			} else{
				handleClient.totalByte.flip();
				CharBuffer charBuffer = decoder.decode(handleClient.totalByte);
				System.out.println("收到信息 >>");
				System.out.println(charBuffer.toString());
				//channel.register(selector,SelectionKey.OP_WRITE);
				channel.close();
				handleClient.totalByte.clear();
				HandleClientPool.removeClient(channel);
			}
		} else if (key.isWritable()) { // 写事件
			SocketChannel channel = (SocketChannel) key.channel();
			System.out.println("----------写事件-------------");
			HandleClient handleClient = HandleClientPool.getClient(channel);
			handleClient.totalByte.flip();
			channel.write(handleClient.totalByte);
			channel.close();
			handleClient.totalByte.clear();
			HandleClientPool.removeClient(channel);
		
		}
	}
	
	protected Selector getSelector(int port) throws IOException {
		ServerSocketChannel server = ServerSocketChannel.open();
		Selector sel = Selector.open();
		server.socket().bind(new InetSocketAddress(port));
		server.configureBlocking(false);
		server.register(sel, SelectionKey.OP_ACCEPT);
		return sel;
	}
	
	protected void startListen(){
		while(true){
			 try {
				selector.select();
				Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
	            while (iter.hasNext()) {
	              
	              SelectionKey key = iter.next();
	              iter.remove();
	              handleKey(key);
	           }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
		}
	}
	
	public static void main(String[] args) {
		try {
			NIOTestServer server = new NIOTestServer(12345);
			server.startListen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
