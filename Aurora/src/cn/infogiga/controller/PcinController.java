package cn.infogiga.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.infogiga.service.AppointmentService;
import cn.infogiga.service.QrcodeService;
import cn.infogiga.util.XmlReader;
import cn.infogiga.util.XmlToolkit;

@Controller
public class PcinController {
	
	private static final Log log = LogFactory.getLog(PcinController.class);
	
	private QrcodeService qrcodeService;
	private AppointmentService appointmentService;
	
	public void setQrcodeService(QrcodeService qrcodeService) {
		this.qrcodeService = qrcodeService;
	}

	public void setAppointmentService(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	/**
	 * 注册二维码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/qr.in", method = RequestMethod.POST)
	public void inQrcode(HttpServletRequest request, HttpServletResponse response) {
		String ip =request.getRemoteAddr();
		try {
			String xmlMessage = request.getReader().readLine();
			log.debug("读取post消息："+xmlMessage);
			XmlReader reader = new XmlReader(XmlToolkit.String2Stream(xmlMessage));			
			if("register".equals(reader.getType("type"))) {
				String phoneNumber = reader.getElementValue("phone");
				log.debug("手机号："+ phoneNumber);
				//交给二维码导览系统
				boolean success = qrcodeService.inUser(phoneNumber);
				response.getWriter().write(""+success);
			} else {
				log.debug("无效的消息");
			}
		} catch (IOException e) {
			log.error("从"+ ip+ "读取post消息失败", e);
		}
	}
	
	/**
	 * 彩信发送的回复
	 * @param request
	 */
	@RequestMapping(value="/mms.in", method = RequestMethod.POST)
	public void responseMMS(HttpServletRequest request, HttpServletResponse response) {
		String ip =request.getRemoteAddr();
		try {
			String xmlMessage = request.getReader().readLine();
			log.debug("读取post消息："+xmlMessage);
			XmlReader reader = new XmlReader(XmlToolkit.String2Stream(xmlMessage));			
			if("mms".equals(reader.getType("response"))) {
				String mmsId = reader.getElementValue("id");
				String mmsTo = reader.getElementValue("to");
				response.getWriter().write("ok");
				log.info(mmsId+","+mmsTo);
				//交给预约管理系统
				appointmentService.setSendStatus(mmsId, mmsTo);
			} else {
				log.debug("无效的消息");
			}
		} catch (IOException e) {
			log.error("从"+ ip+ "读取post消息失败", e);
		}
	}
}
