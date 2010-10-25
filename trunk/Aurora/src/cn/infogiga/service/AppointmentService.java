package cn.infogiga.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.infogiga.bean.Application;
import cn.infogiga.bean.Comment;
import cn.infogiga.bean.Customer;
import cn.infogiga.bean.Groups;
import cn.infogiga.bean.Invitation;
import cn.infogiga.bean.Manager;
import cn.infogiga.bean.Mmstemplate;
import cn.infogiga.bean.Qrcode;
import cn.infogiga.bean.Setting;
import cn.infogiga.bean.Tmp;
import cn.infogiga.dao.AppointmentDAO;
import cn.infogiga.dao.QrcodeDAO;
import cn.infogiga.po.CustomerPO;
import cn.infogiga.po.InvitationPO;
import cn.infogiga.service.notification.MMSBean;
import cn.infogiga.service.notification.MailBean;
import cn.infogiga.service.notification.Notification;
import cn.infogiga.util.Config;
import cn.infogiga.util.DateUtil;
import cn.infogiga.util.StringToolkit;
import cn.infogiga.util.TemplateBean;

public class AppointmentService extends BaseService{
	
	private final static Log log = LogFactory.getLog(AppointmentService.class);
	private final static int SUCCESS = 1; //发送成功
	private final static int SENDING = 0; //发送中
	private final static int RESEND= -1;  //重新发送
	
	private final static int GUIDER = 100; //接待人员
	private final static int TECHINICIAN = 101; //技术人员
	private final static int MANAGER = 102; //客户经理
	
	private AppointmentDAO appointmentDAO;
	private QrcodeDAO qrcodeDAO;
	private Notification notification; //通知服务
	public AppointmentService() {
		notification = SystemService.getService().getHandler(SystemService.NOTIFICATION);
	}
	public void setAppointmentDAO(AppointmentDAO appointmentDAO) {
		this.appointmentDAO = appointmentDAO;
	}
	public void setQrcodeDAO(QrcodeDAO qrcodeDAO) {
		this.qrcodeDAO = qrcodeDAO;
	}
	
	/**
	 * 返回这一天中的所有的Invitation
	 * @param day example：2010-2-2
	 * @return List<Invitation>
	 * @author cindy 
	 */
	public List<Invitation> getInvitationByDay(String day){
		Date startDate = DateUtil.stringToDate(day+" 0:00:00",DateUtil.NOW_TIME);
		Date endDate = DateUtil.stringToDate(day+" 23:59:59",DateUtil.NOW_TIME);
		return appointmentDAO.getInvitationByTime(startDate, endDate);
	}
	
	
	
	public String getDefaultManagerById(Integer id){
		Manager defaultTechincian = appointmentDAO.findById(id, Manager.class);
		JSONObject json = new JSONObject();
		try {
			json.put("id", defaultTechincian.getManagerId());
			json.put("name", defaultTechincian.getName());
			json.put("mail", defaultTechincian.getMail());
			json.put("phoneNumber", defaultTechincian.getPhoneNumber());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return json.toString();
		}
	}
	
	public String getDefaultmsTempleteById(Integer id){
		Mmstemplate defaultMmsTemplete = appointmentDAO.findById(id, Mmstemplate.class);
		JSONObject json = new JSONObject();
		try {
			json.put("mmsTempleteId", defaultMmsTemplete.getMmstemplateId());
			json.put("templeteName", defaultMmsTemplete.getTemplateName());
			json.put("content", defaultMmsTemplete.getContent());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return json.toString();
		}
	}
	
	
	public Mmstemplate getMmsTempleteById(Integer id){
		return appointmentDAO.findById(id, Mmstemplate.class);
	}
	
	/**
	 * 返回这一周中所有的Invitation
	 * @param day example：2010-2-2
	 * @return List<Invitation> order createTime
	 * @author cindy 
	 */
	public List<Invitation> getInvitationByWeek(String day){
		Date startDate = DateUtil.getSunDay(day+" 00:00:00", DateUtil.NOW_TIME);
		Date endDate = DateUtil.getSaturDay(day+" 23:59:59",DateUtil.NOW_TIME);
	//	System.out.println("startDate:"+startDate.toLocaleString());
	//	System.out.println("endDate:"+endDate.toLocaleString());
		
		
		return appointmentDAO.getInvitationByTime(startDate, endDate);
	}
	
	/**
	 * 返回这个月的中所有的Invitation
	 * @param day example：2010-2-2
	 * @return List<Invitation> order createTime
	 * @author cindy 
	 */
	public List<Invitation> getInvitationByMonth(String day){
		Date start = DateUtil.getFirstDateOfMonth(day+" 00:00:00",  DateUtil.NOW_TIME);
		Date startDate = DateUtil.getSunDay(start);
		Date end = DateUtil.getLastDateOfMonth(day+" 23:59:59",DateUtil.NOW_TIME);
		Date endDate = DateUtil.getSaturDay(end);
		return appointmentDAO.getInvitationByTime(startDate, endDate);
	}
	
	/**
	 * 获得某天Invitation的数量
	 * @param day
	 * @return
	 * @author cindy 
	 */
	public Integer getInvitationCountByDay(String day){
		Date startDate = DateUtil.stringToDate(day+" 0:00:00",DateUtil.NOW_TIME);
		Date endDate = DateUtil.stringToDate(day+" 23:59:59",DateUtil.NOW_TIME);
		return appointmentDAO.getInvitationCountByTime(startDate, endDate);
	}
	/**
	 * 获得某周Invitation的数量
	 * @param day
	 * @return
	 * @author cindy 
	 */
	public Integer getInvitationCountByWeek(String day){
		Date startDate = DateUtil.getSunDay(day+" 00:00:00", DateUtil.NOW_TIME);
		Date endDate = DateUtil.getSaturDay(day+" 23:59:59",DateUtil.NOW_TIME);
		return appointmentDAO.getInvitationCountByTime(startDate, endDate);
	}
	/**
	 * 获得某月Invitation的数量
	 * @param day
	 * @return
	 * @author cindy 
	 */
	public Integer getInvitationCpuntByMonth(String day){
		Date start = DateUtil.getFirstDateOfMonth(day+" 00:00:00",  DateUtil.NOW_TIME);
		Date startDate = DateUtil.getSunDay(start);
		Date end = DateUtil.getLastDateOfMonth(day+" 23:59:59",DateUtil.NOW_TIME);
		Date endDate = DateUtil.getSaturDay(end);
		return appointmentDAO.getInvitationCountByTime(startDate, endDate);
	}
		
	/**
	 * 得到未读的预约请求数量
	 * @return
	 */
	public Integer getUnReadApplicationCount(){
		
		return appointmentDAO.getUnReadApplicationCount();
	}
	
	/**
	 * 设置彩信发送状态
	 * @param id	彩信的id，默认为二维码的id
	 * @param phoneNumber	手机号
	 */
	public void setSendStatus(String id, String phoneNumber) {
		int qrcodeId = Integer.parseInt(id);
		Customer customer = appointmentDAO.findCustomerForQrcode(qrcodeId);
		if(customer == null) {
			log.debug("没找到此客户");
			return;
		}
		appointmentDAO.setSendStatus(customer, 1);
	}
	
	/**
	 * 快速邀请
	 * @param customerList
	 * @return
	 */
	public void fastInvitation(InvitationPO invitation,
			Manager manager, ArrayList<CustomerPO> customerList) {
		invitation.setCustomers(customerList);
		
		int count = customerList.size();
		Invitation inv = createInvitationForDefault(manager, invitation, count);
		sendInvitationMms(inv, invitation, customerList);		
	}

	/**
	 * 发送邀请
	 * @param invitation
	 * @return
	 */
	public Invitation sendInvitation(InvitationPO invitation) {
		ArrayList<CustomerPO> customerList = invitation.getCustomers();
		
		int count = customerList.size();
		Invitation inv = createInvitation(invitation, count);
		sendInvitationMms(inv, invitation, customerList);
		return inv;
	}
	
	//创建邀请
	private Invitation createInvitation(InvitationPO invitation, int count) {
				
		//保存邀请
		Invitation inv = new Invitation();
		
		if(invitation.getManagerId() != -1) {
			Manager manager = new Manager();
			manager.setManagerId(invitation.getManagerId());
			inv.setManagerByManagerId(manager);
		}
		//接待人员
		
		if(invitation.getGuiderId() != -1) {
			Manager guider = new Manager();
			guider.setManagerId(invitation.getGuiderId());
			inv.setManagerByGuiderId(guider);
		}
		//技术人员
		
		if(invitation.getTechnicanId() != -1) {
			Manager technician = new Manager();
			technician.setManagerId(invitation.getTechnicanId());
			inv.setManagerByTechnicianId(technician);
		}
		//彩信内容模板
		if(invitation.getMmsId() != -1){
			Mmstemplate mms = new Mmstemplate();
			mms.setMmstemplateId(invitation.getMmsId());
			inv.setMmstemplate(mms);
			Mmstemplate temp = appointmentDAO.findById(invitation.getMmsId(), Mmstemplate.class);
			String mmsContent = temp.getContent();
			invitation.setMmsContent(mmsContent);
		}
		//彩信模板
		Tmp tmp = new Tmp();
		tmp.setIsDefault("1");
		tmp.setType("0");
		List<Tmp> list = appointmentDAO.findByExample(tmp);
		if(list !=null && list.size() >0) {
			invitation.setTemplateName(list.get(0).getTmpName());
		}
		//地点		
		
		inv.setVisitTime(invitation.getVisitTime()); //参观时间
		inv.setEndTime(invitation.getEndTime());
		inv.setLocation(invitation.getLocation()); 	//参观地点
		inv.setCreateTime(new Date()); 	//邀请发出的时间
		inv.setCompleteStatus(0); //完成状态
		inv.setPenpleCount(count); //参观的人数
		inv.setInvitationTitle(invitation.getInvitationTitle()); //标题
		appointmentDAO.save(inv);
		
		return inv;
	}	
	
	//创建默认设置的邀请
	private Invitation createInvitationForDefault(Manager manager, 
			InvitationPO invitation, int count) {		
		//保存邀请
		Invitation inv = new Invitation();
		Setting setting = manager.getSetting();
		//客户经理
		inv.setManagerByManagerId(manager);
		//接待人员
		Manager guider = 
			findById(setting.getDefaultGuiderId(), Manager.class, appointmentDAO);
		inv.setManagerByGuiderId(guider);
		//技术人员
		Manager technician = 
			findById(setting.getDefaultTechnicianId(), Manager.class, appointmentDAO);
		inv.setManagerByTechnicianId(technician);		
		//彩信模板
		Mmstemplate template = 
			findById(setting.getDefaultMmstemplateId(), Mmstemplate.class, appointmentDAO);
		inv.setMmstemplate(template);		
		//默认的地点和模板内容
		invitation.setLocation(setting.getDefaultLocation());//放到invitation对象里，为后来的发送彩信提供地点
		invitation.setMmsContent(template.getContent());
		
		inv.setVisitTime(invitation.getVisitTime()); //参观时间
		inv.setLocation(setting.getDefaultLocation()); 	//参观地点
		inv.setCreateTime(new Date()); 	//邀请发出的时间
		inv.setCompleteStatus(0); //完成状态
		inv.setPenpleCount(count); //参观的人数
		inv.setInvitationTitle("新参观"); //标题
		appointmentDAO.save(inv);
		
		return inv;
	}
	
	//发送邀请彩信
	private void sendInvitationMms(Invitation inv, InvitationPO invitation,
			ArrayList<CustomerPO> customerList) {
		ArrayList<MMSBean> mmsList = new ArrayList<MMSBean>();//要发送的彩信列表
		String firstQrcode = "";
		
		//保存客户资料
		for(CustomerPO customer: customerList) {
			Customer c = new Customer();
			c.setName(customer.getName());
			c.setCompany(customer.getCompany());
			c.setUsername(customer.getUsername());
			c.setPassword(customer.getPassword());
			c.setPhoneNumber(customer.getPhoneNumber());
			c.setCopyFlag(customer.getCopyFlag());
			c.setSendStatus(customer.getSendStatus());	
			c.setInvitation(inv);
			//为每个客户生成二维码
			Qrcode qrcode = new Qrcode();
			String[] qrcodes = new String[2];
			qrcodeDAO.makeQrcode(qrcodes, 'M');
			if(StringToolkit.isBlank(firstQrcode)) {
				firstQrcode = qrcodes[1];
			}
			qrcode.setWapCode(qrcodes[0]);
			qrcode.setMmsCode(qrcodes[1]);
			qrcode.setCustomer(c);			
			
			c.getQrcodes().add(qrcode);
			qrcodeDAO.save(c);
			qrcodeDAO.save(qrcode);
			
			//生成彩信消息			
			MMSBean mms = new MMSBean();
			mms.setPhoneNumber(customer.getPhoneNumber());			
			mms.setQrcode(qrcodes[1]);	
			mms.setId(""+ qrcode.getCodeId()); //彩信的id，根据二维码的id来的
//			mms.setTemplateName(invitation.getTemplateName());
			mms.setTemplateName("Template_C00001");
			TemplateBean templateBean = new TemplateBean();
			templateBean.setName(customer.getName());
			templateBean.setTime(invitation.getVisitTime());
			templateBean.setLocation(invitation.getLocation());	
			templateBean.setUrl(StringToolkit.createUrl("2", qrcodes[0], customer.getPassword()));
			mms.setContent(
					StringToolkit.parseTemplateContent(
							invitation.getMmsContent(), templateBean));//内容
			mmsList.add(mms);
//			if(c.getCopyFlag() == 1) {
				/***需要抄送给接待人员和技术人员****/
				
//			}
		}
		
		Manager guider = inv.getManagerByGuiderId();
		guider = (Manager) appointmentDAO.currentSession().get(Manager.class, guider.getManagerId());
		MMSBean mms = new MMSBean();
		mms.setPhoneNumber(guider.getPhoneNumber());
		mms.setQrcode(firstQrcode);
		mms.setId("0");
		mms.setTemplateName("Template_C00001");
		TemplateBean templateBean = new TemplateBean();
		templateBean.setTime(invitation.getVisitTime());
		templateBean.setLocation(invitation.getLocation());
		mms.setContent(StringToolkit.parseTemplateForGuider(templateBean, customerList.size()+""));
		mmsList.add(mms);
		
		Manager technichan = inv.getManagerByTechnicianId();
		technichan = (Manager) appointmentDAO.currentSession().get(Manager.class, technichan.getManagerId());
		MMSBean mmsc = (MMSBean)mms.clone();
		mmsc.setPhoneNumber(technichan.getPhoneNumber());
		mmsc.setContent(StringToolkit.parseTemplateForGuider(templateBean, customerList.size()+""));
		mmsList.add(mmsc);
		
		//发送MMS
		/*******需要改为:失败则等待一段时间再次发送*******/
		//
		boolean success = notification.sendMMS(mmsList);
		if(!success) {//发送失败，邀请中的所有客户的发送状态都设置为0
			for(CustomerPO customer: customerList) {			
				Customer c = 
					appointmentDAO.findCustomerByPhoneNumber(customer.getPhoneNumber());
				appointmentDAO.setSendStatus(c, SENDING);
			}			
			appointmentDAO.setCompleteStatus(inv, SENDING); //邀请的完成状态
		} else {
			for(CustomerPO customer: customerList) {			
				Customer c = 
					appointmentDAO.findCustomerByPhoneNumber(customer.getPhoneNumber());
				appointmentDAO.setSendStatus(c, SUCCESS);
			}
			appointmentDAO.setCompleteStatus(inv, SUCCESS);
		}
	}
	
	/**
	 * 是否发送邮件
	 * @return
	 */
	public boolean isSendMail() {
		Setting setting = appointmentDAO.getDefaultSetting();
		String send = setting.getSendPressbook();
		if(send == null || "0".equals(send)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 今天访问过的客户
	 * @return
	 */
	public List<Customer> getVisitedCustomer() {
		DateUtil.getStartOfDay();
		Date startDate = DateUtil.getStartOfDay();
		Date endDate = DateUtil.getEndOfDay();
		return appointmentDAO.getVisitedCustomer(startDate, endDate);
	}
	
	public void sendInvitationIntime() {
		
	}
	
	/**
	 * 提交预约申请
	 * @param name
	 * @param phoneNumber
	 * @param company
	 * @param reason
	 */
	public void sendApplication(String name, String phoneNumber, String company, String reason) {
		Customer customer = appointmentDAO.addCustomer(name, phoneNumber, company);
		appointmentDAO.addApplication(customer, reason);
		List<Manager> admins = appointmentDAO.getAdmins();
		ArrayList<MailBean> mails = new ArrayList<MailBean>();
		
		for(Manager manager: admins) {
			MailBean mail = createMailBean();
			mail.setMsg("手机号："+phoneNumber+",<br/>申请理由："+reason);
			mail.setSubject("来自"+name+"的预约申请");
			mail.setTo(StringToolkit.isBlank(manager.getMail())?manager.getPhoneNumber()+"@139.com":manager.getMail());
			mails.add(mail);
		}
		notification.sendMail(mails);
	}
	
	private MailBean createMailBean() {
		MailBean mail = new MailBean();
		mail.setSmtpHost(Config.getValue("smtpHost"));
		mail.setFrom(Config.getValue("from"));
		mail.setUserName(Config.getValue("userName"));
		mail.setPassword(Config.getValue("password"));
		mail.setSubject(Config.getValue("subject"));
		mail.setMsg(Config.getValue("msg"));
		mail.setFileName(Config.getValue("fileName"));
		return mail;
	}
	
	/**
	 * 提交评论
	 * @param name
	 * @param phoneNumber
	 * @param content
	 * @param ip
	 */
	public void comment(String name, String phoneNumber, String content, String ip) {
		Customer customer = appointmentDAO.addCustomer(name, phoneNumber, null);
		appointmentDAO.addComment(customer, content, ip);
		List<Manager> admins = appointmentDAO.getAdmins();
		ArrayList<MailBean> mails = new ArrayList<MailBean>();
		
		for(Manager manager: admins) {
			MailBean mail = createMailBean();
			mail.setMsg("手机号："+phoneNumber+",<br/>反馈内容："+content+",<br/>IP："+ip);
			mail.setSubject("来自"+name+"的反馈");
			mail.setTo(StringToolkit.isBlank(manager.getMail())?manager.getPhoneNumber()+"@139.com":manager.getMail());
			mails.add(mail);
		}
		notification.sendMail(mails);
	}
	
	/**
	 * 获取接待人员列表
	 * @return
	 */
	public JSONArray getGuiders() {
		JSONArray array = new JSONArray();
		/*Groups guiderGroup = appointmentDAO.findGuiderGroup();
		Manager instance = new Manager();
		instance.setGroups(guiderGroup);*/
		
		List<Manager> guiders = appointmentDAO.findByProperty(Manager.class, "groups.groupId", 3);
		for(Manager guider: guiders) {
			JSONObject object = new JSONObject();
			try {
				object.put("id", guider.getManagerId());
				object.put("name", guider.getName());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			array.put(object);
		}
		return array;
	}
	
	/**
	 * 获取技术人员列表
	 * @return
	 */
	public JSONArray getTechnicians() {
		JSONArray array = new JSONArray();
		/*Groups technicianGroup = appointmentDAO.findTechnicianGroup();
		
		Manager instance = new Manager();
		instance.setGroups(technicianGroup);*/
		
		List<Manager> technicians = appointmentDAO.findByProperty(Manager.class, "groups.groupId", 4);	
		for(Manager technician: technicians) {
			JSONObject object = new JSONObject();
			try {
				object.put("id", technician.getManagerId());
				object.put("name", technician.getName());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			array.put(object);
		}
		return array;
	}
	
	/**
	 * 获取彩信模板列表
	 * @return
	 */
	public JSONArray getMMSTemplates() {
		JSONArray array = new JSONArray();
		List<Mmstemplate> templates = findAll(Mmstemplate.class, appointmentDAO);
		for(Mmstemplate template: templates) {
			JSONObject obj = new JSONObject();
			try {
				obj.put("id", template.getMmstemplateId());
				obj.put("name", template.getTemplateName());
				obj.put("content", template.getContent());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			array.put(obj);
		}
		return array;
	}
	
	/**
	 * 单个邀请信息
	 * @param invitationId
	 * @return
	 * @throws JSONException
	 */
	public JSONArray getInvitation(int invitationId) throws JSONException {
		Invitation invitation = getInvitationById(invitationId);
		List<Invitation> invList = new ArrayList<Invitation>();
		invList.add(invitation);
		
		return getInvitations(invList);
	}
	
	/**
	 * 天为单位的邀请信息
	 * @return
	 * @throws JSONException
	 */
	public JSONArray getInvitationDays() throws JSONException {	
		Date today = new Date();
		Date startOfDay = DateUtil.getStartOfDay(today);
		Date endOfDay = DateUtil.getEndOfDay(today);
		List<Invitation> invList = appointmentDAO.getInvitationByTime(startOfDay, endOfDay);
		
		return getInvitations(invList);
	}	
	/**
	 * 天为单位的邀请信息
	 * @param 字符串格式的日期
	 * @return
	 * @throws JSONException
	 */
	public JSONArray getInvitationDays(String date) throws JSONException {	
		Date currentDate = DateUtil.stringToDate(date, new SimpleDateFormat("yyyy-MM-dd")); 
		Date startOfDay = DateUtil.getStartOfDay(currentDate);
		Date endOfDay = DateUtil.getEndOfDay(currentDate);
		List<Invitation> invList = appointmentDAO.getInvitationByTime(startOfDay, endOfDay);
		
		return getInvitations(invList);
	}
	
	/**
	 * 周为单位的邀请信息
	 * @return
	 * @throws JSONException
	 */
	public JSONArray getInvitationWeeks() throws JSONException {
		Date today = new Date();
		Date startOfWeek = DateUtil.getStartOfWeek(today);
		Date endOfWeek = DateUtil.getEndOfWeek(today);
		List<Invitation> invList = appointmentDAO.getInvitationByTime(startOfWeek, endOfWeek);
		
		return getInvitations(invList);
	}
	/**
	 * 周为单位的邀请信息
	 * @param 字符串格式的日期
	 * @return
	 * @throws JSONException
	 */
	public JSONArray getInvitationWeeks(String date) throws JSONException {	
		Date currentDate = DateUtil.stringToDate(date, new SimpleDateFormat("yyyy-MM-dd")); 
		Date startOfWeek = DateUtil.getStartOfWeek(currentDate);
		Date endOfWeek = DateUtil.getEndOfWeek(currentDate);
		List<Invitation> invList = appointmentDAO.getInvitationByTime(startOfWeek, endOfWeek);
		
		return getInvitations(invList);
	}
	
	/**
	 * 月为单位的邀请信息
	 * @return
	 * @throws JSONException
	 */
	public JSONArray getInvitationMonths() throws JSONException {
		Date today = new Date();
		Date startOfMonth = DateUtil.getStartOfMonth(today);
		Date endOfMonth = DateUtil.getEndOfMonth(today);
		List<Invitation> invList = appointmentDAO.getInvitationByTime(startOfMonth, endOfMonth);
		
		return getInvitations(invList);
	}
	/**
	 * 月为单位的邀请信息
	 * @param 字符串格式的日期
	 * @return
	 * @throws JSONException
	 */
	public JSONArray getInvitationMonths(String date) throws JSONException {
		Date currentDate = DateUtil.stringToDate(date, new SimpleDateFormat("yyyy-MM-dd"));
		Date startOfMonth = DateUtil.getStartOfMonth(currentDate);
		Date endOfMonth = DateUtil.getEndOfMonth(currentDate);
		List<Invitation> invList = appointmentDAO.getInvitationByTime(startOfMonth, endOfMonth);
		
		return getInvitations(invList);
	}
	
	/**
	 * 根据id查找邀请
	 * @param id
	 * @return
	 */
	public Invitation getInvitationById(int id) {
		return (Invitation) appointmentDAO.currentSession().get(Invitation.class, id);
	}
	
	/**
	 * 本周的意见反馈
	 * @return
	 */
	public List<Comment> getCommentThisWeek() {
		Date currentDate = new Date();
		Date startOfWeek = DateUtil.getStartOfWeek(currentDate);
		Date endOfWeek = DateUtil.getEndOfWeek(currentDate);
		return appointmentDAO.getCommentByTime(startOfWeek, endOfWeek);
	}
	
	/**
	 * 上周的反馈
	 * @return
	 */
	public List<Comment> getCommentLastWeek() {
		Date currentDateInLastWeek = DateUtil.getLastWeek();
		Date startOfWeek = DateUtil.getStartOfWeek(currentDateInLastWeek);
		Date endOfWeek = DateUtil.getEndOfWeek(currentDateInLastWeek);
		return appointmentDAO.getCommentByTime(startOfWeek, endOfWeek);
	}
	
	/**
	 * 所有的意见反馈
	 * @return
	 */
	public List<Comment> getComments() {
		return appointmentDAO.getComments();
	}
	
	/**
	 * 本周的预约
	 * @return
	 */
	public List<Invitation> getInvitationThisWeek() {
		Date currentDate = new Date();
		Date startOfWeek = DateUtil.getStartOfWeek(currentDate);
		Date endOfWeek = DateUtil.getEndOfWeek(currentDate);
		return appointmentDAO.getInvitationByTime(startOfWeek, endOfWeek);
	}
	
	/**
	 * 上周的预约
	 * @return
	 */
	public List<Invitation> getInvitationLastWeek() {
		Date currentDateInLastWeek = DateUtil.getLastWeek();
		Date startOfWeek = DateUtil.getStartOfWeek(currentDateInLastWeek);
		Date endOfWeek = DateUtil.getEndOfWeek(currentDateInLastWeek);
		return appointmentDAO.getInvitationByTime(startOfWeek, endOfWeek);
	}
	
	/**
	 * 所有的预约
	 * @return
	 */
	public List<Invitation> getInvitations() {
		return appointmentDAO.getInvitations();
	}
	
	/**
	 * 本周的请求
	 * @return
	 */
	public List<Application> getApplicationThisWeek() {
		Date currentDate = new Date();
		Date startOfWeek = DateUtil.getStartOfWeek(currentDate);
		Date endOfWeek = DateUtil.getEndOfWeek(currentDate);
		return appointmentDAO.getApplications(startOfWeek, endOfWeek);		
	}
	
	/**
	 * 上周的请求
	 * @return
	 */
	public List<Application> getApplicationLastWeek() {
		Date currentDateInLastWeek = DateUtil.getLastWeek();
		Date startOfWeek = DateUtil.getStartOfWeek(currentDateInLastWeek);
		Date endOfWeek = DateUtil.getEndOfWeek(currentDateInLastWeek);
		return appointmentDAO.getApplications(startOfWeek, endOfWeek);		
	}
	
	/**
	 * 所有的请求
	 * @return
	 */
	public List<Application> getApplications() {
		return appointmentDAO.getApplications();
	}
	
	/**
	 * 查找id对应的customer
	 * @param id
	 * @return
	 */
	public List<Customer> getCustomersByInvitationId(int id) {
		return appointmentDAO.getCustomersForInvitation(id);
	}
	
	/**
	 * 得到邀请信息的json数组
	 * @param invList
	 * @return
	 * @throws JSONException
	 */
	private JSONArray getInvitations(List<Invitation> invList) throws JSONException {
		JSONArray array = new JSONArray();		
		
		for(Invitation invitation: invList) {
			JSONObject json = new JSONObject();
			json.append("title", StringToolkit.upString(invitation.getInvitationTitle(), "")); //标题
			json.append("time", invitation.getVisitTime());	//参观时间
			
			if(invitation.getManagerByGuiderId() != null) {
				Manager guider = (Manager)appointmentDAO.currentSession().get(
						Manager.class, invitation.getManagerByGuiderId().getManagerId());
				JSONObject guiderInfo = new JSONObject();
				guiderInfo.append("name", guider.getName());
				guiderInfo.append("gender", guider.getGender());
				guiderInfo.append("phoneNumber", guider.getPhoneNumber());
				guiderInfo.append("mail", guider.getMail());
				json.append("guider", guiderInfo);			//接待人员
			} else {
				json.append("guider", "");
			}
			
			if(invitation.getManagerByTechnicianId() != null) {
				Manager technician = (Manager) appointmentDAO.currentSession().get(
						Manager.class, invitation.getManagerByTechnicianId().getManagerId());
				JSONObject technicianInfo = new JSONObject();
				technicianInfo.append("name", technician.getName());
				technicianInfo.append("gender", technician.getGender());
				technicianInfo.append("phoneNumber", technician.getPhoneNumber());
				technicianInfo.append("mail", technician.getMail());			
				json.append("technician", technicianInfo);	//技术人员
			} else {			
				json.append("technician", "");
			}
			
			List<Customer> customerList = 
				appointmentDAO.getCustomersForInvitation(invitation.getInvitationId());
			JSONArray customers = new JSONArray(); //一个邀请中的客户列表
			for(Customer customer: customerList) {
				JSONObject oneOfCustomer = new JSONObject();//单个客户的信息
				oneOfCustomer.append("id", customer.getCustomerId());
				oneOfCustomer.append("name", StringToolkit.upString(customer.getName(), ""));
				oneOfCustomer.append("phoneNumber", StringToolkit.upString(customer.getPhoneNumber(), ""));
				oneOfCustomer.append("company", StringToolkit.upString(customer.getCompany(), ""));
				oneOfCustomer.append("sendStatus", customer.getSendStatus());
				customers.put(oneOfCustomer);
			}
			json.append("customers", customers); //客户列表			
			array.put(json); //放入每个邀请
		}
		
		return array;
	}
}
