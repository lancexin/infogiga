package cn.infogiga.exp.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import cindy.util.DateUtil;
import cindy.util.TypeConvert;
import cindy.util.config.ProperiesReader;
import cindy.util.net.HttpToolkit;
import cindy.util.xml.XMLBeanUtil;
import cn.infogiga.exp.pojo.Downloadstat;
import cn.infogiga.exp.pojo.Emailsendlog;
import cn.infogiga.exp.pojo.Emailservice;
import cn.infogiga.exp.pojo.Menu;
import cn.infogiga.exp.quartz.SMSBean;
import cn.infogiga.exp.quartz.MySpringQuartzSchedulerBean;
import cn.infogiga.exp.server.xml.ErrInfo;
import cn.infogiga.exp.server.xml.Intf;
import cn.infogiga.exp.server.xml.ItemInfo;
import cn.infogiga.exp.server.xml.ReceiveBean;
import cn.infogiga.exp.server.xml.RetInfo;
import cn.infogiga.exp.server.xml.StarSoft;
import cn.infogiga.exp.server.xml.TopSoft;
import cn.infogiga.exp.service.CmbService;
import cn.infogiga.exp.service.EmailService;
import cn.infogiga.exp.service.ExperienceService;
import cn.infogiga.exp.service.SmsService;
import cn.infogiga.exp.webservice.CmbBeanFactory;

import org.springframework.web.filter.CharacterEncodingFilter;


public class ServerController {
private static final Log log = LogFactory.getLog(ServerController.class);
	
	CmbService cmbService;
	
	ExperienceService experienceService;
	
	SmsService smsService;
	
	XMLBeanUtil xmlBeanUtil;
	
	EmailService emailService;
	
	MySpringQuartzSchedulerBean scheduler;
	
	public void setScheduler(MySpringQuartzSchedulerBean scheduler) {
		this.scheduler = scheduler;
	}

	public void setXmlBeanUtil(XMLBeanUtil xmlBeanUtil) {
		this.xmlBeanUtil = xmlBeanUtil;
	}

	public void setCmbService(CmbService cmbService) {
		this.cmbService = cmbService;
	}
	
	public void setExperienceService(ExperienceService experienceService) {
		this.experienceService = experienceService;
	}
	
	public void setSmsService(SmsService smsService) {
		this.smsService = smsService;
	}
	

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	
	/**
	 * 彩信内容转发
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value = "/t")
	public void softDownloadLog(HttpServletRequest request, HttpServletResponse response) throws IOException{
		DataInputStream bis = new DataInputStream(new BufferedInputStream(request.getInputStream()));
		int totalLength = bis.readInt();
		byte[] all = new byte[totalLength - 4];
		byte[] buffer = new byte[1024];
		int l = 0;
		int t = 0;
		for(int i=0;(l = bis.read(buffer))>0;t+=l){
			System.arraycopy(buffer, 0, all, t, l);
		}
		ByteArrayInputStream bins=new ByteArrayInputStream(all);
		DataInputStream dins=new DataInputStream(bins);
		int commandId = dins.readInt();
		byte[] sequenceId = new byte[32];
		dins.read(sequenceId, 0, 32);
		int sendCount = dins.readByte();
		
		//System.out.println("commandId:"+commandId);
		//System.out.println("sequenceId:"+new String(sequenceId));
		//System.out.println("sendCount:"+sendCount);

		//List<String> phoneNumberList = new ArrayList<String>();
		byte[] phoneNumber = new byte[32];
		String phoneNumbers = "";
		for(int i=0;i<sendCount;i++){
			dins.read(phoneNumber, 0, 32);
			//System.out.println("phoneNumber "+i+" :"+TypeConvert.bytesToHexStr(phoneNumber));
			String phone = new String(phoneNumber).replaceAll(" ","");
			//System.out.println("phoneNumber "+i+" :"+phone);
			//phoneNumberList.add(new String(phoneNumber));
			phoneNumbers += phone+",";
		}

		byte[] msg = new byte[totalLength-41-32*sendCount];
		dins.readFully(msg);
		//System.out.println("msg:"+TypeConvert.bytesToHexStr(msg));
		response.getWriter().print("ok");
		
		ProperiesReader properies = ProperiesReader.getInstence("config.properties");
		//像彩信服务器发送信息
		HttpToolkit.postMessage(TypeConvert.int2byte(totalLength),all,properies.getStringValue("mms.host"),properies.getIntegerValue("mms.post"));
		//记录彩信发送信息
		experienceService.addMMS(new Date(), phoneNumbers, new String(sequenceId));
	}
	
	/**
	 * 入口
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/m")
	public void info(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String xml = request.getReader().readLine();
		log.info("接收信息："+xml);
		//System.out.println("xml:"+xml);
		String callback = null;
		if(xml == null){
			Intf intf = new Intf();
			ErrInfo errInfo = CmbBeanFactory.getNullErrorInfo();
			intf.setErrinfo(errInfo);
			callback = xmlBeanUtil.xmlBeanToStr(intf);
		}else{
			//解析xml
			ReceiveBean rb = null;
			rb = xmlBeanUtil.getXMLBeanByStr(ReceiveBean.class, xml);
			Integer type = Integer.parseInt(rb.getType());
			System.out.println("type:"+type);
			switch (type) {
			case 1://设备注册
				break;
			case 2://设备开启（登录）
				callback = login(rb);
				break;
			case 3://体验日志记录
				callback = log(rb);
				break;
			case 4://退出体验
				callback = getItemInfo(rb);
				break;
			case 5://订购业务
				String str = checkServerPwd(rb);
				System.out.println("checkServerPwd,str:"+str);
				Intf intf = xmlBeanUtil.getXMLBeanByStr(Intf.class, xml);
				ErrInfo err = intf.getErrinfo();
				System.out.println("0".equals(err.getErrCode()));
				if(!"0".equals(err.getErrCode())){
					callback = str;
					break;
				}
				callback = order(rb);
				break;
			case 6://软件更新
				callback = checkUpdate(rb);
				break;
			case 7://软件下载统计
				callback = logDownLoadSoft(rb);
				break;
			case 8://发送短信
				callback = sendSMS(rb);
				break;
			case 9://发送wap push
				callback = sendWapPush(rb);
				break;
			case 10://获得每周排行榜
				callback = getTopSoft(rb);
				break;
			case 11://获得本周主打星
				callback = getStarSoft(rb);
				break;
			case 12://获得软件id信息
				callback = getMenuInfo(rb);
				break;
			case 13://短信定时群发
				callback = sendArraySMSByTime(rb);
				break;
			case 14://修改彩信发送状态
				callback = updateMMSStatus(rb);
				break;
			case 15://短信群发
				callback = sendArraySMS(rb);
				break;	
			case 16:
				callback = sendMail(rb);
				break;
			default:
				Intf intf2 = new Intf();
				ErrInfo errInfo = CmbBeanFactory.getIllegalErrInfo();
				intf2.setErrinfo(errInfo);
				callback = xmlBeanUtil.xmlBeanToStr(intf2);
				break;
			}
		}
		log.info("发送信息："+callback);
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(callback);
	}
	
	private String getMenuInfo(ReceiveBean rb){
		List<Menu> list = experienceService.findAll(Menu.class);
		Intf intf = new Intf();
		RetInfo retInfo = new RetInfo();
		ArrayList<ItemInfo> itemInfoList = new ArrayList<ItemInfo>();
		int size = list.size();
		Menu menu;
		ErrInfo errInfo;
		try {
			for(int i=0;i<size;i++){
				menu = list.get(i);
				ItemInfo itemInfo = new ItemInfo();
				itemInfo.setId(menu.getMid()+"");
				itemInfo.setMenuId(menu.getMenuId());
				itemInfo.setFuncId(menu.getFuncId());
				itemInfo.setPkg(menu.getPkg());
				itemInfo.setName(menu.getMenuName());
				itemInfoList.add(itemInfo);
				retInfo.setItemInfoList(itemInfoList);
				intf.setRetInfo(retInfo);
			}
			errInfo = CmbBeanFactory.getSuccessErrInfo();
		} catch (RuntimeException e) {
			errInfo = CmbBeanFactory.getItemInfoErrInfo();
			e.printStackTrace();
		}
		intf.setErrinfo(errInfo);
		return xmlBeanUtil.xmlBeanToStr(intf);
		
	}
	
	
	/**
	 * 订购业务
	 * @param rb
	 * @return
	 */
	private String order(ReceiveBean rb){
		String str = cmbService.sendOrder(rb.getMenu_id(), rb.getFunc_id(), rb.getPhone_number(), rb.getTarget_code(), rb.getTarget_name(), rb.getSys_oper(),rb.getAction());
		System.out.println("order:"+str);
		if(str == null){
			Intf intf = new Intf();
			ErrInfo errInfo = CmbBeanFactory.getNoCallbackErrInfo();
			intf.setErrinfo(errInfo);
			return xmlBeanUtil.xmlBeanToStr(intf);
		}
		Intf i = xmlBeanUtil.getXMLBeanByStr(Intf.class, str);
		//如果订购成功则发送短信通知订购用户
		ErrInfo err = i.getErrinfo();
		if("0".equals(err.getErrCode())){
			if("0".equals(rb.getAction())){
				cmbService.sendSMS(rb.getPhone_number(), "您刚刚取消了 "+rb.getTarget_name());
			}else if("1".equals(rb.getAction())){
				cmbService.sendSMS(rb.getPhone_number(), "您刚刚订购了"+rb.getTarget_name()+",谢谢您的订购");
			}
			
		}
		return str;
	}
	
	private String getTopSoft(ReceiveBean rb){
		Intf intf = new Intf();
		
		try {
			List<Downloadstat> list = experienceService.getTopSoft(rb);
			int size = list.size();
			ArrayList<TopSoft> topSoftList = new ArrayList<TopSoft>();
			Downloadstat downloadStat = null;
			for(int i=0;i<size;i++){
				downloadStat = list.get(i);
				TopSoft topSoft = new TopSoft();
				topSoft.setSoftName(downloadStat.getSoftName());
				topSoft.setDownloadCount(""+downloadStat.getDownloadCount());
				topSoftList.add(topSoft);
			}
			RetInfo retInfo = new RetInfo();
			retInfo.setTopSoftList(topSoftList);
			intf.setRetInfo(retInfo);
			ErrInfo errinfo = CmbBeanFactory.getSuccessErrInfo();
			intf.setErrinfo(errinfo);
		} catch (RuntimeException e) {
			ErrInfo errinfo = CmbBeanFactory.getTopSoftErrInfo();
			intf.setErrinfo(errinfo);
			e.printStackTrace();
		}
		
		return xmlBeanUtil.xmlBeanToStr(intf);
	}
	
	private String getStarSoft(ReceiveBean rb){
		Intf intf = new Intf();
		
		try {
			List<Downloadstat> list = experienceService.getStarSoft(rb);
			int size = list.size();
			RetInfo retInfo = new RetInfo();
			if(size <= 0){
				StarSoft starSoft = new StarSoft();
				starSoft.setSoftName("UC浏览器");
				starSoft.setDownloadCount("0");
				retInfo.setStarSoft(starSoft);
			}else{
				Downloadstat downloadStat = list.get(0);
				StarSoft starSoft = new StarSoft();
				starSoft.setSoftName(downloadStat.getSoftName());
				starSoft.setDownloadCount(downloadStat.getDownloadCount()+"");
				retInfo.setStarSoft(starSoft);
			}
			intf.setRetInfo(retInfo);
			ErrInfo errinfo = CmbBeanFactory.getSuccessErrInfo();
			intf.setErrinfo(errinfo);
		} catch (RuntimeException e) {
			ErrInfo errinfo = CmbBeanFactory.getTopSoftErrInfo();
			intf.setErrinfo(errinfo);
			e.printStackTrace();
		}
		
		return xmlBeanUtil.xmlBeanToStr(intf);
	}
	
	/**
	 * 设备登录
	 * @param rb
	 * @return
	 */
	private String login(ReceiveBean rb){
		boolean b = experienceService.checkLogin(rb);
		Intf intf = new Intf();
		ErrInfo errInfo = null;
		if(b){
			//Employee employee = experienceService.getSingleEmployee(rb);
			boolean bol = experienceService.checkEmployeeWithPassword(rb);
			if(!bol){
				errInfo = CmbBeanFactory.getNoSuchEmployeeErrInfo();
			}else{
				errInfo = CmbBeanFactory.getLoginSuccessErrInfo();
			}
		}else{
			errInfo = CmbBeanFactory.getNoSuchEquipmentErrInfo();
		}
		intf.setErrinfo(errInfo);
		return xmlBeanUtil.xmlBeanToStr(intf);
	}
	
	
	/**
	 * 记录日志
	 * @param rb
	 * @return
	 */
	private String log(ReceiveBean rb){
		boolean b = experienceService.log(rb);
		Intf intf = new Intf();
		ErrInfo errInfo = null;
		if(b){
			errInfo = CmbBeanFactory.getAddSuccessErrInfo();
			
		}else{
			errInfo = CmbBeanFactory.getAddFailureErrInfo();
		}
		intf.setErrinfo(errInfo);
		return xmlBeanUtil.xmlBeanToStr(intf);
		
	}
	
	
	/**
	 * 软件下载统计
	 * @param rb
	 * @return
	 */
	private String logDownLoadSoft(ReceiveBean rb){
		Intf intf = new Intf();
		ErrInfo errInfo = null;
		
		boolean bl = experienceService.logDownloadSoft(rb);
		if(bl){
			errInfo = CmbBeanFactory.getSuccessErrInfo();
		}else{
			errInfo = CmbBeanFactory.getAddFailureErrInfo();
		}
		
		intf.setErrinfo(errInfo);
		return xmlBeanUtil.xmlBeanToStr(intf);
	}
	
	private String sendSMS(ReceiveBean rb){
		boolean b = false;
		Intf intf = new Intf();
		ErrInfo errInfo = null;

		if(rb.getSms_context() != null && !"".equals(rb.getSms_context().trim())){
			b = cmbService.sendSMS(rb.getPhone_number(), rb.getSms_context());
		}
		
		if(b){
			boolean bll = experienceService.logSMS(rb);
			if(bll){
				errInfo = CmbBeanFactory.getSendSmsSuccessErrInfo();
			}else{
				errInfo = CmbBeanFactory.getSendSmsSuccessButLogFailureErrInfo();
			}
		}else{
			errInfo = CmbBeanFactory.getSendSmsFailureErrInfo();
		}
		intf.setErrinfo(errInfo);
		return xmlBeanUtil.xmlBeanToStr(intf);
	}
	
	private String sendWapPush(ReceiveBean rb){
		boolean b = false;
		Intf intf = new Intf();
		ErrInfo errInfo = null;
		
		if(rb.getSms_context() != null && !"".equals(rb.getSms_context().trim())){
			b = cmbService.sendWapPush(rb.getPhone_number(), rb.getSoft_download_url(), rb.getSms_context());
		}
		
		if(b){
			boolean bll = experienceService.logSMS(rb);
			if(bll){
				errInfo = CmbBeanFactory.getSendSmsSuccessErrInfo();
			}else{
				errInfo = CmbBeanFactory.getSendSmsSuccessButLogFailureErrInfo();
			}
		}else{
			errInfo = CmbBeanFactory.getSendSmsFailureErrInfo();
		}
		intf.setErrinfo(errInfo);
		return xmlBeanUtil.xmlBeanToStr(intf);
	}
	
	/**
	 * 获得业务信息
	 * @param rb
	 * @return
	 */
	private String getItemInfo(ReceiveBean rb){
		String str = cmbService.getBusiInfo(rb.getMenu_id(), rb.getPhone_number());
		Intf intf = new Intf();
		if(str == null){
			ErrInfo errInfo = CmbBeanFactory.getNoCallbackErrInfo();
			intf.setErrinfo(errInfo);
			return xmlBeanUtil.xmlBeanToStr(intf);
		}else{
			return str;
		}
	}
	
	/**
	 * 检查客户服务密码是否正确
	 * @param rb
	 * @return
	 */
	private String checkServerPwd(ReceiveBean rb){
		String checkInfo = cmbService.checkServerPassword(rb.getPhone_number(), rb.getServer_pwd());
		
		System.out.println("checkInfo:"+checkInfo);
		if(checkInfo == null){
			Intf intf = new Intf();
			ErrInfo errInfo = CmbBeanFactory.getNoCallbackErrInfo();
			intf.setErrinfo(errInfo);
			return xmlBeanUtil.xmlBeanToStr(intf);
		}else{
			return checkInfo;
		}
	}
	
	/**
	 * 检查更新
	 * @param rb
	 * @return
	 */
	private String checkUpdate(ReceiveBean rb){
		String str = experienceService.checkUpdate(rb);
		Intf intf = new Intf();
		ErrInfo errInfo = null;
		if(str == null){
			errInfo = CmbBeanFactory.getNoUpdateErrInfo();
			
		}else{
			errInfo = CmbBeanFactory.getHasUpdateErrInfo();
			errInfo.setErrMsg(str);
		}
		intf.setErrinfo(errInfo);
		return xmlBeanUtil.xmlBeanToStr(intf);
	}
	
	private String sendArraySMSByTime(ReceiveBean rb){
		Intf intf = new Intf();
		try {
			String[] phoneNumbers = rb.getPhone_numbers().split(",");
			Date sendTime = DateUtil.stringToDate(rb.getSms_send_time(), DateUtil.NOW_TIME);
			String context = rb.getSms_context();
			int smsarrayID = experienceService.addSMSArraySend(rb.getPhone_numbers(), context, sendTime,0);
			//在这之前需要先在数据库里记录发送字段
			//experienceService.
			SMSBean bean = new SMSBean();
			bean.setPhoneNumbers(phoneNumbers);
			bean.setSendType(SMSBean.MMS_ARRAY);
			bean.setContext(context);
			bean.setSmsarrayID(smsarrayID);
			Map beanMap = new HashMap();
			beanMap.put("bean", bean);
			scheduler.addTrigger("sms"+smsarrayID, beanMap, sendTime, 0, 0);
			intf.setErrinfo(CmbBeanFactory.getSuccessErrInfo());
		} catch (RuntimeException e) {
			intf.setErrinfo(CmbBeanFactory.getUnknowErrorInfo());
			e.printStackTrace();
		}
		return xmlBeanUtil.xmlBeanToStr(intf);
		
	}
	
	private String sendArraySMS(ReceiveBean rb){
		Intf intf = new Intf();
		try {
			String[] phoneNumbers = rb.getPhone_numbers().split(",");
			String context = rb.getSms_context();
			
			boolean bl = smsService.sendArraySMS(phoneNumbers, context);
			if(bl){
				experienceService.addSMSArraySend(rb.getPhone_numbers(), context, new Date(),1);
				intf.setErrinfo(CmbBeanFactory.getSuccessErrInfo());
			}else{
				intf.setErrinfo(CmbBeanFactory.getUnknowErrorInfo());
			}
		} catch (RuntimeException e) {
			intf.setErrinfo(CmbBeanFactory.getUnknowErrorInfo());
			e.printStackTrace();
		}
		return xmlBeanUtil.xmlBeanToStr(intf);
	}
	
	public String updateMMSStatus(ReceiveBean rb){
		Intf intf = new Intf();
		
		try {
			String sequenceId = rb.getMms_sequenceid();
			Integer status = Integer.parseInt(rb.getMms_send_result());
			if(sequenceId == null || "".equals(sequenceId)){
				intf.setErrinfo(CmbBeanFactory.getUnknowErrorInfo());
			}else{
				experienceService.updateMMSStatusBySecquenceId(sequenceId.trim(), status);
				intf.setErrinfo(CmbBeanFactory.getSuccessErrInfo());
			}
			
		} catch (NumberFormatException e) {
			intf.setErrinfo(CmbBeanFactory.getUnknowErrorInfo());
			e.printStackTrace();
		}
		return xmlBeanUtil.xmlBeanToStr(intf);
	}

	
	public String sendMail(ReceiveBean rb){
		Intf intf = new Intf();
		String serviceCode = rb.getMail_templete_code().trim();
		String mailTo = rb.getMail_to().trim();
		Emailservice service = new Emailservice();
		service.setServiceCode(serviceCode);
		service = experienceService.getExperienceDAO().findSingleByExample(service);
		if(service == null){
			intf.setErrinfo(CmbBeanFactory.getUnknowErrorInfo());
		}else{
			String sendHtml = service.getEmailtemplete().getTempleteView();
			String subject = service.getEmailtemplete().getTempleteName();
			boolean b = emailService.sendSingleMail(mailTo, subject, sendHtml);
			if(b){
				Emailsendlog emailsendlog = new Emailsendlog();
				emailsendlog.setEmailtemplete(service.getEmailtemplete());
				emailsendlog.setSendTime(new Date());
				emailsendlog.setSubject(subject);
				emailsendlog.setMailto(mailTo);
				experienceService.getExperienceDAO().save(emailsendlog);
			}
			intf.setErrinfo(CmbBeanFactory.getSuccessErrInfo());
		}
		return xmlBeanUtil.xmlBeanToStr(intf);
	}


}
