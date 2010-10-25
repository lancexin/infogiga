package test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;


public class NIOTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	class NIOSocketServer{
		ServerSocketChannel ssc;
		ByteBuffer clientBuffer = ByteBuffer.allocate(4092);
		public NIOSocketServer(){
			try {
				ssc = ServerSocketChannel.open();
				ssc.configureBlocking( false );
				ServerSocket ss = ssc.socket();
				InetSocketAddress address = new InetSocketAddress(10087);
				ss.bind( address );
				Selector selector = Selector.open();
				SelectionKey key = ssc.register( selector, SelectionKey.OP_ACCEPT );
				while (true) {
					int num = selector.select();
					Set selectedKeys = selector.selectedKeys();
					if ((key.readyOps() & SelectionKey.OP_ACCEPT)
					          == SelectionKey.OP_ACCEPT) {
						
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		
		
	}

}
