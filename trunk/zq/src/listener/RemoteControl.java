package listener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import tool.Config;
import bean.ClientBean;
import data.Database;

public class RemoteControl {
	
	private static Logger log = Logger.getLogger(RemoteControl.class.getName());
	private static int port = Config.getInteger("master.port");
	private Database db = new Database();
	
	public boolean shutdown(String ip) {
		return execute("###SHUTDOWN###", ip);
	}
	
	public boolean reboot(String ip) {
		return execute("###REBOOT###", ip);
	}
	
	public boolean logoff(String ip) {
		return execute("###LOGOFF###", ip);
	}
	
	public boolean check(String ip) {
		OutputStream os;
		OutputStreamWriter osw;
		InputStream is;
		String command = "###CHECK###";
		String result = "";
		int n = 0;
		byte[] buffer = new byte[32];
		
		try {
			Socket client = new Socket(ip, port);
			os = client.getOutputStream();
			is = client.getInputStream();
			osw = new OutputStreamWriter(os);
			osw.write(command);
			osw.flush();
//			os.write(command.getBytes());
			log.info("向"+ ip+ "发送"+ command+ "成功");
			
			n = is.read(buffer);
//			result = new BufferedReader(new InputStreamReader(is)).readLine();
			result = new String(buffer, 0, n);
			log.info("返回:"+ result);
			
			os.close();
			osw.close();
			is.close();
			if("###OK###".equals(result)) {
				return true;
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public ArrayList<ClientBean> check() {
		return db.getClients();		
	}
	
	private boolean execute(String command, String ip) {
		OutputStream os;
		InputStream is;
		byte[] buffer = new byte[32];
		int n=0;
		String result;
		
		try {
			Socket client = new Socket(ip, 63000);
			os = client.getOutputStream();
			is = client.getInputStream();
			os.write(command.getBytes("ISO-8859-1"));
			log.info("向"+ ip+ "发送"+ command+ "成功");
			
			n = is.read(buffer);
			result = new String(buffer, 0, n);
			os.flush();
			os.close();
			if("###OK###".equals(result)) {
				log.info("返回"+ result);
				return true;
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
