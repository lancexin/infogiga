package cn.infogiga.ds.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tool.Config;
import tool.HttpToolkit;
import tool.Toolkit;
import tool.XmlReader;

import client.qrcode.QrcodeService;
import cn.infogiga.ds.dao.DsDAO;
import cn.infogiga.ds.pojo.SmsConfig;
import data.Database;

@Controller
public class DataController {
	
	private static Logger log = Logger.getLogger(DataController.class.getName());

	DsDAO dsDAO;
		
	public void setDsDAO(DsDAO dsDAO) {
		this.dsDAO = dsDAO;
	}

	@RequestMapping("/in")
	public void in(HttpServletRequest request,
			HttpServletResponse response){
		
		readContent(request);
		
	}
	
	private void sendShortMessage(String phoneNumber, String mes) {		
		String host = "http://"+ Config.getValue("connection.sms.ip") + 
							":"+ Config.getValue("connection.sms.port");//发往的主机
		String xmlMessage = "<?xml version=\"1.0\" encoding=\"utf-8\"?><root><server request=\"" + 
							phoneNumber+ "_"+ mes+ ";\" /></root>";
		HttpToolkit.postMessage(xmlMessage,Config.getValue("connection.sms.ip"),Config.getInteger("connection.sms.port"));
		
		//sendMessage(xmlMessage, host);//发送post
		log.info("向"+ host+ "发送的xml消息:"+ xmlMessage);			
	}
	
	
	private void readContent(HttpServletRequest request) {
		try {
			String xmlMessage = request.getReader().readLine();
			System.out.println(xmlMessage);
			//String xmlMessage = Toolkit.encode(s, "ISO-8859-1");
			//log.info("接收到的xml信息："+ xmlMessage);
			Database database = new Database();
			resolve(Toolkit.String2Stream(xmlMessage),database);//解析
		} catch (IOException e) {
			log.error("HTTP请求读取失败");
		}
	}
	
	/**
	 * 从输入流中解析出xml数据
	 * @param in 输入流
	 */
	private void resolve(InputStream in,Database database) {
		XmlReader reader = new XmlReader(in);		
		String message = reader.getData("pc");
		String[] values = message.split("-");
		log.info("解析出的信息："+ message);
		//数据合法则直接返回
		if(!Toolkit.check(message)) { 
			log.info("无效");
			return;
		}
		if("GA01100".equals(values[4])) {
			String regionCode = values[1]+values[2]+values[3].substring(0, 2);
			String template = database.getTemplateName(regionCode); //短信内容
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
				save(message,database);
				return;
			}
			DsController.code = values[5].split("_")[5];//记录临时选择的地址
			//dealOther(message);//发送短消息
			SmsConfig smsConfig = dsDAO.findSingleByProperty(SmsConfig.class, "code", DsController.code);
			if(smsConfig != null && smsConfig.getContext() != null){
				sendShortMessage(sa[3],smsConfig.getContext());
				save(message,database);
			}
			return;
		}
		
		
		if(values[5].split("_").length == 5) {
			//发短信的请求
			if(Config.getValue("connection.sms.send").equals("true")) {
				dealOther(message);
				save(message, "SendSMS",database);
			}
			return;
		}
		
		save(message,database);
		if("GA01100".equals(values[4]) &&
				Config.getValue("connection.sms.send").equals("true")) {
			//是导览系统，则发送短消息
			dealPlanar(message,database);
		}
	}
	
	//处理其他的请求
	private void dealOther(String message) {
		String[] values = message.split("-");
		String operation = values[5];
		String[] operations = operation.split("_");
		String phoneNumber = operations[3];
		String mes = operations[4];
		
		sendShortMessage(phoneNumber, mes);
	}
	
	//处理二维码导览的请求
	private void dealPlanar(String message,Database database) {
		String[] values = message.split("-");
		String planar = values[5]; //二维码
		String phoneNumber = database.getPhone(planar); //手机号
		String regionCode = values[1]+values[2]+values[3].substring(0, 2);
		String mes = database.getSms(regionCode); //短信内容
		
		if("".equals(phoneNumber)) return;
		sendShortMessage(phoneNumber, mes);
	}
	
	/**
	 * 保存数据
	 * @param message 接收到的数据
	 */
	private void save(String message,Database database) {
		String[] values = message.split("-");
		String equipmentCode = values[1]+ values[2]+ values[3];
		String systemCode = values[4];

		if("GA01100".equals(systemCode)) {
			//存入导览系统的表
			String planar = values[5];
			
			database.save(equipmentCode, systemCode, planar);
		} else {
			//存入体验的表
			String operation = values[5];
			String[] operations = operation.split("_");
			database.save(equipmentCode, systemCode, operations[0], systemCode+operations[1], operations[2]);
		}		
	}
	
	private void save(String message, String code,Database database) {
		String[] values = message.split("-");
		String equipmentCode = values[1]+ values[2]+ values[3];
		String systemCode = values[4];
		String operation = values[5];
		String[] operations = operation.split("_");

		database.save(equipmentCode, systemCode, operations[0], systemCode+code, code);
	}
}
