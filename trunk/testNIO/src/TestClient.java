import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class TestClient {
	public static void main(String[] args) {
		
		try {
			String s = "这是一段文字\r\n";
			Socket socket = new Socket("localhost", 10087);
			OutputStream os = socket.getOutputStream();
			InputStream is = socket.getInputStream();
			for(int i=0;i<5;i++){
				os.write(s.getBytes());
				Thread.sleep(5*1000);
			}
			os.flush();
			os.close();
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
