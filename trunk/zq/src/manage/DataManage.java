package manage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.infogiga.ds.controller.DsController;

import client.qrcode.QrcodeService;

import tool.Config;
import tool.HttpToolkit;
import tool.Toolkit;
import tool.XmlReader;
import data.Database;

/**
 * 二维码数据管理
 * @author ya
 */
public class DataManage extends HttpServlet {

	private static final long serialVersionUID = -6503960637328362051L;
	private static Logger log = Logger.getLogger(DataManage.class.getName());

	private Database db = new Database();
	private String message = ""; //xml数据内容

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		readContent(request);
	}
	
	/**
	 * 读取http请求中的内容
	 * @param HttpServletRequest
	 * */
	private void readContent(HttpServletRequest request) {
		try {
			String xmlMessage = request.getReader().readLine();
			System.out.println(xmlMessage);
			//String xmlMessage = Toolkit.encode(s, "ISO-8859-1");
			//log.info("接收到的xml信息："+ xmlMessage);
			resolve(Toolkit.String2Stream(xmlMessage));//解析
		} catch (IOException e) {
			log.error("HTTP请求读取失败");
		}
	}
	
	/**
	 * 从输入流中解析出xml数据
	 * @param in 输入流
	 */
	private void resolve(InputStream in) {
		XmlReader reader = new XmlReader(in);		
		message = reader.getData("pc");
		String[] values = message.split("-");
		log.info("解析出的信息："+ message);
		//数据合法则直接返回
		if(!Toolkit.check(message)) { 
			log.info("无效");
			return;
		}
		if("GA01100".equals(values[4])) {
			String regionCode = values[1]+values[2]+values[3].substring(0, 2);
			String template = db.getTemplateName(regionCode); //短信内容
			//是新二维码，交由online处理
			boolean isNew = QrcodeService.getInstance().isNew(
					values[1]+ values[2]+ values[3], values[4], values[5], regionCode, template);
			if(isNew) {
				log.info("新的二维码，交由online处理");
				return;
			}
		}
		
		//表示这是保险定损
		if("GA05100".equals(values[4])){
			log.info("进入保险定损");
			String[] sa = values[5].split("_");
			int length = sa.length;
			if(length != 6){
				return;
			}
			//DsController.code2 = values[5].split("_")[5];//记录临时选择的地址
			dealOther();//发送短消息
			save(message);
			return;
		}
		
		
		if(values[5].split("_").length == 5) {
			//发短信的请求
			if(Config.getValue("connection.sms.send").equals("true")) {
				dealOther();
				save(message, "SendSMS");
			}
			return;
		}
		save(message);
		if("GA01100".equals(values[4]) &&
				Config.getValue("connection.sms.send").equals("true")) {
			//是导览系统，则发送短消息
			dealPlanar();
		}
	}
	
	//处理二维码导览的请求
	private void dealPlanar() {
		String[] values = message.split("-");
		String planar = values[5]; //二维码
		String phoneNumber = db.getPhone(planar); //手机号
		String regionCode = values[1]+values[2]+values[3].substring(0, 2);
		String mes = db.getSms(regionCode); //短信内容
		
		if("".equals(phoneNumber)) return;
		sendShortMessage(phoneNumber, mes);
	}
	
	//处理其他的请求
	private void dealOther() {
		String[] values = message.split("-");
		String operation = values[5];
		String[] operations = operation.split("_");
		String phoneNumber = operations[3];
		String mes = operations[4];
		
		sendShortMessage(phoneNumber, mes);
	}
	
	/**
	 * 发送短消息
	 */
	private void sendShortMessage(String phoneNumber, String mes) {		
		String host = "http://"+ Config.getValue("connection.sms.ip") + 
							":"+ Config.getValue("connection.sms.port");//发往的主机
		String xmlMessage = "<?xml version=\"1.0\" encoding=\"utf-8\"?><root><server request=\"" + 
							phoneNumber+ "_"+ mes+ ";\" /></root>";
		HttpToolkit.postMessage(xmlMessage,Config.getValue("connection.sms.ip"),Config.getInteger("connection.sms.port"));
		
		//sendMessage(xmlMessage, host);//发送post
		log.info("向"+ host+ "发送的xml消息:"+ xmlMessage);			
	}
/*	
	*//**
	 * 给PC端发送信息
	 *//*
	private void sendMessage(String m, String url) {
		byte[] message = m.getBytes();//要发送的消息
		OutputStream output = null; //对PC端的输出流
		HttpURLConnection connection = null;

		try {
			URL turl = new URL(url);
			connection = (HttpURLConnection)turl.openConnection();	
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setDefaultUseCaches(false);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept", "text/html");
			connection.setRequestProperty("Content-type", "text/html");
			connection.setRequestProperty("Content-Length",""+message.length);
			connection.setRequestProperty("Content-Encoding", "");
			connection.setConnectTimeout(3);//连接超时时间
			output = connection.getOutputStream();//需要放到setDoOutput方法后面
			output.write(message);//发送			
			getResponseCode方法很重要，调用它才会将请求发送出去
			log.info("response code:"+ connection.getResponseCode());
			output.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			log.error("URL不合法！");
		} catch (IOException e) {	
			e.printStackTrace();
			log.error("连接失败！");
		}
	}*/
	
	/**
	 * 保存数据
	 * @param message 接收到的数据
	 */
	private void save(String message) {
		String[] values = message.split("-");
		String equipmentCode = values[1]+ values[2]+ values[3];
		String systemCode = values[4];
		
		if("GA01100".equals(systemCode)) {
			//存入导览系统的表
			String planar = values[5];
			db.save(equipmentCode, systemCode, planar);
		} else {
			//存入体验的表
			String operation = values[5];
			String[] operations = operation.split("_");
			db.save(equipmentCode, systemCode, operations[0], systemCode+operations[1], operations[2]);
		}		
	}
	
	private void save(String message, String code) {
		String[] values = message.split("-");
		String equipmentCode = values[1]+ values[2]+ values[3];
		String systemCode = values[4];
		String operation = values[5];
		String[] operations = operation.split("_");
		
		db.save(equipmentCode, systemCode, operations[0], systemCode+code, code);
	}
}
