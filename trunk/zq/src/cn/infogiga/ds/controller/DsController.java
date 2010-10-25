package cn.infogiga.ds.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tool.Config;
import tool.HttpToolkit;
import tool.XmlReader;
import tool.XmlToolkit;

import cn.infogiga.ds.dao.DsDAO;
import cn.infogiga.ds.pojo.SmsConfig;

@Controller
public class DsController {
	
	public static String code = "";
	
	DsDAO dsDAO;

	
	public void setDsDAO(DsDAO dsDAO) {
		this.dsDAO = dsDAO;
	}

	@RequestMapping("/bx")
	public void update(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		
		try {
			String xml = request.getReader().readLine();
			System.out.println("接收到xml："+xml);
			System.out.println("短信发送成功");
			//response.getWriter().write("ok");
			XmlReader xmlReader = new XmlReader();
			boolean ok = xmlReader.read(XmlToolkit.String2Stream(xml), "GBK");
			String temp = xmlReader.getAttributeForPC("smswebservers_request");
			String[] t = temp.split("@"); 
			System.out.println("时间："+t[0]);
			System.out.println("手机号码："+t[1]);
			System.out.println("代码："+t[2]);
			if(temp == null){
				System.out.println("没有找到smswebservers_request属性");
				response.getWriter().write("error");
				return;
			}
			if(t[0] == null || t[1] == null || t[2] == null){
				System.out.println("时间或手机号码或代码为空");
				response.getWriter().write("error");
				return;
			}
			boolean b = false;
			SmsConfig smsConfig = new SmsConfig();
			smsConfig.setCode(code);
			smsConfig = dsDAO.findSingleByExample(smsConfig);
			if(smsConfig == null){
				System.out.println("没有找到该代码的值");
				String sendXml = XmlToolkit.createXmlForSMS(t[1],"代码错误");
				System.out.println("发送的短信内容为：");
				System.out.println(sendXml);
				b = HttpToolkit.postMessage(sendXml,Config.getValue("connection.sms.ip").trim(),Config.getInteger("connection.sms.port"));
			}else{
				if(!"*".equals(smsConfig.getPhoneNumber())){
					if(!t[1].equals(smsConfig.getPhoneNumber()) && !t[1].equals("+86"+smsConfig.getPhoneNumber())){
						System.out.println("手机号码不匹配 "+smsConfig.getPhoneNumber()+"  "+t[1]);
						response.getWriter().write("error");
						return;
					}
				}
				String sendXml = XmlToolkit.createXmlForSMS(t[1],smsConfig.getContext2());
				System.out.println("发送的短信内容为：");
				System.out.println(sendXml);
				b = HttpToolkit.postMessage(sendXml,Config.getValue("connection.sms.ip").trim(),Config.getInteger("connection.sms.port"));
			}
			
			
			if(b){
				System.out.println("短信发送成功");
				response.getWriter().write("ok");
			}else{
				System.out.println("短信发送失败");
				response.getWriter().write("error");
			}
			
		} catch (RuntimeException e) {
			response.getWriter().write("error");
			e.printStackTrace();
			return;
		}
		
	}
	
	@RequestMapping("/bx/delete")
	public void delete(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		String temp = request.getParameter("id");
		if(temp == null || temp.length() == 0){
			return;
		}
		Integer id = Integer.parseInt(temp);
		SmsConfig smsConfig = new SmsConfig(id);
		try {
			dsDAO.delete(smsConfig);
			response.getWriter().print("0");
		} catch (RuntimeException e) {
			response.getWriter().print("1");
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/bx/addOrUpdate")
	public void addOrUpdate(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		String out = "";
		String temp = request.getParameter("id");
		String code = request.getParameter("code").trim();
		String context = request.getParameter("context").trim();
		String context2 = request.getParameter("context2").trim();
		String phoneNumber = request.getParameter("phoneNumber").trim();
		System.out.println(temp);
		System.out.println(code);
		System.out.println(context);
		System.out.println(context2);
		System.out.println(phoneNumber);
		if(temp == null || code == null || context == null || 
				phoneNumber == null || context2 == null || 
					temp.length() == 0 || code.length() == 0 || context.length() == 0 || 
						phoneNumber.length() == 0 || context2.length() == 0){
			out = "不能为空";
			response.getWriter().print(out);
			return;
		}
		
		if(!"*".equals(phoneNumber) && !phoneNumber.matches("^(13[4-9]|15[0|1|2|7|8|9]|18[8|9]|147)\\d{8}$")){
			out = "手机号码格式错误";
			response.getWriter().print(out);
			return;
		}
		Integer id = Integer.parseInt(temp);
		if(id == -1){
			SmsConfig smsConfig = new SmsConfig();
			smsConfig.setCode(code);
			smsConfig.setContext(context);
			smsConfig.setContext2(context2);
			smsConfig.setPhoneNumber(phoneNumber);
			dsDAO.save(smsConfig);
			out = "保存成功";
			response.getWriter().print(out);
		}else{
			SmsConfig smsConfig = new SmsConfig(id);
			smsConfig.setCode(code);
			smsConfig.setContext(context);
			smsConfig.setContext2(context2);
			smsConfig.setPhoneNumber(phoneNumber);
			dsDAO.saveOrUpdate(smsConfig);
			out = "修改成功";
			response.getWriter().print(out);
		}
	}
	
	@RequestMapping("/bx/list")
	public String list(HttpServletRequest request,
			HttpServletResponse response){
		List<SmsConfig> list = dsDAO.findAll(SmsConfig.class);
		request.setAttribute("list", list);
		return "ds";
	}
}
