package cn.infogiga.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.infogiga.bean.Application;
import cn.infogiga.bean.Comment;
import cn.infogiga.bean.Customer;
import cn.infogiga.bean.Groups;
import cn.infogiga.bean.Invitation;
import cn.infogiga.bean.MailTemplate;
import cn.infogiga.bean.Manager;
import cn.infogiga.bean.Mmstemplate;
import cn.infogiga.bean.Role;
import cn.infogiga.bean.Setting;
import cn.infogiga.dao.SettingDAO;
import cn.infogiga.po.LoginPO;
import cn.infogiga.po.MailTemplatePO;
import cn.infogiga.po.MmsTemplatePO;

public class SettingService extends BaseService{
	SettingDAO settingDAO;
	
	public void setSettingDAO(SettingDAO settingDAO) {
		this.settingDAO = settingDAO;
	}
	
	/**
	 * 根据组名获得用户信息列表
	 * @param groupName
	 * @return List<Manager>
	 * @author cindy
	 */
	public List<Manager> getUserInfoByGroupName(String groupName){
		return settingDAO.getManagerListByGroupName(groupName);
	}
	
	public boolean updateManagerInfo(Manager manager){
		return update(manager, settingDAO);
	}
	
	/**
	 * 根据id获得setting信息
	 * @param id
	 * @return
	 * @author cindy
	 */
	public Setting findSettingById(int id){
		return this.findById(id, Setting.class, settingDAO);
	}
	
	
	public boolean updateSettingInfo(Integer settingId,Integer defaultGuider,Integer defalutTechincian,
				Integer defaultMailTemplete,Integer defaultMmsTemplete,Integer sendMailAfterVisit){
			Setting setting = findById(settingId, Setting.class, settingDAO);
			setting.setDefaultGuiderId(defaultGuider);
			setting.setDefaultTechnicianId(defalutTechincian);
			setting.setDefaultMailtemplateId(defaultMailTemplete);
			setting.setDefaultMmstemplateId(defaultMmsTemplete);
			setting.setSendApplicationToMail(sendMailAfterVisit);
			boolean b = update(setting, settingDAO);
			return b;
		
	}
	
	/**
	 * 根据groupID获得Manager信息
	 * @param id
	 * @return
	 * @author cindy
	 */
	public String findManagerByGroupId(int id){
		List<Manager> managerList =  this.findByProperty(Manager.class, "groups.groupId", id, settingDAO);
		JSONArray array = new JSONArray();
		int size = managerList.size();
		Manager manager;
		
		for(int i=0;i<size;i++){
			manager = managerList.get(i);
			JSONObject object = new JSONObject();
			try {
				object.put("managerId", manager.getManagerId());
				object.put("name", manager.getName());
				object.put("username", manager.getUsername());
				object.put("password", manager.getPassword());
				object.put("phoneNumber", manager.getPhoneNumber());
				object.put("mail", manager.getMail());
				object.put("type",manager.getGroups().getGroupId());
				array.put(object);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(array.toString());
		return array.toString();
	}

	/**
	 * 查看个人信息
	 * @param id
	 * @return LoginPO
	 * @author cindy
	 */
	public LoginPO getSingleUserInfo(Integer id){
		Manager m = settingDAO.findById(id, Manager.class);
		String name = m.getName(); //用户的名字
		String username = m.getUsername();//用户名
		String password = m.getPassword();//密码
		Groups group = m.getGroups();
		int groupId = group.getGroupId();//组id
		String groupName = group.getGroupName();//组名
		String sex = m.getGender();//性别
		String mail = m.getMail();//密码
		String phoneNumber = m.getPhoneNumber();//电话号码
		Role role = m.getRole();
		int roleId = role.getRoleId();//权限id
		String roleName = role.getRoleName();//权限值
		int managerId = m.getManagerId();//ID
		LoginPO loginPO = new LoginPO(managerId,username,password,name,
				sex,mail,phoneNumber,roleId,
				roleName,groupId,groupName);
		return loginPO;
	}
	/**
	 * 修改用户基本信息
	 * @param manager
	 * @param name
	 * @param phoneNumber
	 * @param mail
	 * @return boolean
	 * @author cindy
	 */
	public boolean updateManagerBaseInfo(Manager manager,String name,String phoneNumber,String mail){
		manager.setPhoneNumber(phoneNumber);
		manager.setName(name);
		manager.setMail(mail);
		return update(manager, settingDAO);
	}
	/**
	 * 修改用户密码
	 * @param manager
	 * @param newPassword
	 * @return boolean
	 * @author cindy
	 */
	public boolean updateManagerPassword(Manager manager,String newPassword){

		manager.setPassword(newPassword);
		return update(manager, settingDAO);
	}

	//系统设置
	
	/**
	 * 添加一个子用户
	 * @param manager
	 * @return int 
	 * @author cindy
	 */
	public String addManager(Manager manager){
		this.save(manager, settingDAO);
		JSONObject object = new JSONObject();
		try {
			object.put("managerId", manager.getManagerId());
			object.put("name", manager.getName());
			object.put("username", manager.getUsername());
			object.put("password", manager.getPassword());
			object.put("phoneNumber", manager.getPhoneNumber());
			object.put("type", manager.getGroups().getGroupId());
			object.put("mail", manager.getMail());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return object.toString();
	}
	
	public Integer getManagerCountByProperty(final String propertyName,final Object value){
		return settingDAO.getManagerCountByProperty(propertyName, value);
	}
	/**
	 * 删除一个子用户
	 * @param manager
	 * @return boolean
	 * @author cindy
	 */
	public Manager findManagerById(int id){
		return findById(id, Manager.class, settingDAO);
	}
	
	public boolean deleteManager(Manager manager){
		return this.delete(manager,settingDAO);
	}
	
	/**
	 * 修改一个子帐号
	 * @param manager
	 * @return boolean
	 * @author cindy
	 */
	public String updateManager(Manager manager){
		this.update(manager, settingDAO);
		JSONObject object = new JSONObject();
		try {
			object.put("managerId", manager.getManagerId());
			object.put("name", manager.getName());
			object.put("username", manager.getUsername());
			object.put("password", manager.getPassword());
			object.put("phoneNumber", manager.getPhoneNumber());
			object.put("type", manager.getGroups().getGroupId());
			object.put("mail", manager.getMail());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object.toString();
	}
	/**
	 * 根据用户组id得到用户信息
	 * @param groupId
	 * @return List<Manager>
	 * @author cindy
	 */
	public List<Manager> getManageListByGroupId(int groupId){
		return settingDAO.findByProperty(Manager.class, "groups.groupID", groupId);
	}
	
	/**
	 * 获得所有分组信息
	 * @return List<Groups>
	 * @author cindy
	 */
	public List<Groups> getGroups(){
		return settingDAO.findAll(Groups.class);
	}

	/**
	 * 得到所有彩信模板列表信息
	 * @return List<MmsTemplatePO>
	 * @author cindy
	 */
	public String getMMSTempleteList(){
		//return settingDAO.findAll(Mmstemplate.class);
		List<Mmstemplate> list = settingDAO.findAll(Mmstemplate.class);
		JSONArray array = new JSONArray();
		int size = list.size();
		Mmstemplate mms;
		for(int i=0;i<size;i++){
			JSONObject object = new JSONObject();
			mms = list.get(i);
			try {
				object.put("templeteId", mms.getMmstemplateId());
				object.put("content", mms.getContent());
				object.put("name", mms.getTemplateName());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			array.put(object);
		}
		System.out.println(array.toString());
		return array.toString();
	}
	
	/**
	 * 添加一个彩信模板信息
	 * @param mmsTemplate
	 * @return boolean
	 * @author cindy
	 */
	public String addMmsTemplate(Mmstemplate mmsTemplate){
		boolean b = save(mmsTemplate, settingDAO);
		if(b){
			JSONObject object = new JSONObject();
			try {
				object.put("templeteId", mmsTemplate.getMmstemplateId());
				object.put("name", mmsTemplate.getTemplateName());
				object.put("content", mmsTemplate.getContent());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(object.toString());
			return object.toString();
		}
		return null;
	}
	
	/**
	 * 删除一个彩信模板信息
	 * @param mmsTemplate
	 * @return boolean
	 * @author cindy
	 */
	public boolean deleteMmsTemplate(Mmstemplate mmsTemplate){
		return this.delete(mmsTemplate, settingDAO);
	}
	
	/**
	 * 得到所以邮件模板列表信息
	 * @return List<MailTemplatePO>
	 * @author cindy
	 */
	public String getMailTempleteList(){
		List<MailTemplate> list = settingDAO.findAll(MailTemplate.class);
		JSONArray array = new JSONArray();
		int size = list.size();
		MailTemplate mail;
		for(int i=0;i<size;i++){
			JSONObject object = new JSONObject();
			mail = list.get(i);
			try {
				object.put("templeteId", mail.getMailTemplateId());
				object.put("content", mail.getContent());
				object.put("name", mail.getTemplateName());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			array.put(object);
		}
		System.out.println(array.toString());
		return array.toString();
	}
	
	public MailTemplate getMailTemplateById(int id){
		return settingDAO.findById(id, MailTemplate.class);
	}
	
	public Mmstemplate getMmstemplateById(int id){
		return settingDAO.findById(id, Mmstemplate.class);
	}
	
	public String updateMmsTemplete(Mmstemplate mmstemplate){
		boolean b = update(mmstemplate, settingDAO);
		if(b){
			JSONObject object = new JSONObject();
			try {
				object.put("templeteId", mmstemplate.getMmstemplateId());
				object.put("name", mmstemplate.getTemplateName());
				object.put("content", mmstemplate.getContent());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return object.toString();
		}
		return null;
	}
	
	public String updateMailTemplete(MailTemplate mailTemplete){
		boolean b = this.update(mailTemplete, settingDAO);
		if(b){
			JSONObject object = new JSONObject();
			try {
				object.put("templeteId", mailTemplete.getMailTemplateId());
				object.put("name", mailTemplete.getTemplateName());
				object.put("content", mailTemplete.getContent());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return object.toString();
		}
		return null;
	}
	
	
	
	/**
	 * 添加一个邮件模板信息
	 * @param mailTemplate
	 * @return boolean
	 * @author cindy
	 */
	public String addMailTemplate(MailTemplate mailTemplate){
		boolean b = save(mailTemplate,settingDAO);
		if(b){
			JSONObject object = new JSONObject();
			try {
				object.put("templeteId", mailTemplate.getMailTemplateId());
				object.put("name", mailTemplate.getTemplateName());
				object.put("content", mailTemplate.getContent());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(object.toString());
			return object.toString();
		}
		return null;
	}
	
	
	
	/**
	 * 删除一个邮箱模板信息
	 * @param mailTemplate
	 * @return boolean
	 * @author cindy
	 */
	public boolean deleteMailTemplate(MailTemplate mailTemplate){
		return this.delete(mailTemplate, settingDAO);
	}

	/*******************************************************************************************************/
	/******************************    意见反馈分页查询相关       **********************************************/
	/*******************************************************************************************************/
	
	/**
	 * 第一次获得意见反馈信息
	 * @return List<Comment>
	 * @author cindy
	 */
	public List<Comment> findFirstCommentByPage(){
		return settingDAO.findCommentByPage(null, null, null, null, null, 5, 0);
	}
	
	/**
	 * 获得所有意见反馈信息的条数
	 * @return Integer
	 * @author cindy
	 */
	public Integer findCommentCount(){
		return settingDAO.getCount(Comment.class);
	}
	
	/**
	 * Comment 根据条件分页查询反馈信息 
	 * @param fromTime 查询开始时间
	 * @param toTime 查询结束时间
	 * @param phoneNumber 电话号码
	 * @param company 公司
	 * @param name 姓名
	 * @param pageCount 每页显示页数
	 * @param page 当前页面
	 * @return List<Comment>
	 * @author cindy
	 */
	public List<Comment> findCommentByPage(Date fromTime,Date toTime,String phoneNumber,String company,String name,int pageCount,int page){
		
		return settingDAO.findCommentByPage(fromTime, toTime, phoneNumber, company, name, pageCount, page);
	}
	
	/**
	 * Comment 根据条件分页查询反馈信息个数 
	 * @param fromTime 查询开始时间
	 * @param toTime 查询结束时间
	 * @param phoneNumber 电话号码
	 * @param company 公司
	 * @param name 姓名
	 * @param pageCount 每页显示页数
	 * @param page 当前页面
	 * @return Integer
	 * @author cindy
	 */
	public Integer findCommentCountPyPage(Date fromTime,Date toTime,String phoneNumber,String company,String name){
		return settingDAO.findCommentCountByPage(fromTime, toTime, phoneNumber, company, name);
	}
	
	/*******************************************************************************************************/
	/******************************    预约参观分页查询相关       **********************************************/
	/*******************************************************************************************************/
	
	/**
	 * 第一次获得预约参观信息
	 * @return List<Invitation>
	 * @author cindy
	 */
	public List<Invitation> findFirstInvitationByPage(){
		return settingDAO.findInvitationByPage(null, null, null, null, -1, null, null, null, null, null, 5, 0);
	}
	
	/**
	 * 获得所有预约参观信息的条数
	 * @return Integer
	 * @author cindy
	 */
	public Integer findInvitationCount(){
		return settingDAO.getCount(Invitation.class);
	}
	
	/**
	 * Invitation 分页条件查询预约参观信息
	 * @param manager 此次参观的客户经理
	 * @param guider 此次藏的接待员
	 * @param technician 此次参观的维护人员
	 * @param location 此次参观的地点
	 * @param states 此次参观的状态 （以发送邀请1，未发送邀请0）
	 * @param fromCreateTime 此次参观的发起时间 起始
	 * @param toCreateTime 此次参观的发起时间 结束
	 * @param fromTime 此次参观的时间 起始
	 * @param toTime 此次参观的时间 结束
	 * @param pageCount 每页显示的页数
	 * @param page 显示第几页
	 * @return List<Invitation>
	 * @author cindy
	 */
	public List<Invitation> findInvitationByPage(String manager,String guider,String technician,String location,int states,String title,Date fromCreateTime,Date toCreateTime,Date fromTime,Date toTime,Integer pageCount,Integer page){
		return settingDAO.findInvitationByPage(manager, guider, technician, location, states,title, fromCreateTime, toCreateTime, fromTime, toTime, pageCount, page);
	}
	
	/**
	 * Invitation 分页条件查询预约参观信息
	 * @param manager 此次参观的客户经理
	 * @param guider 此次藏的接待员
	 * @param technician 此次参观的维护人员
	 * @param location 此次参观的地点
	 * @param states 此次参观的状态 （以发送邀请1，未发送邀请0）
	 * @param fromCreateTime 此次参观的发起时间 起始
	 * @param toCreateTime 此次参观的发起时间 结束
	 * @param fromTime 此次参观的时间 起始
	 * @param toTime 此次参观的时间 结束
	 * @param pageCount 每页显示的页数
	 * @param page 显示第几页
	 * @return List<Invitation>
	 * @author cindy
	 */
	public Integer findInvitationCountByPage(String manager,String guider,String technician,String location,int states,String title,Date fromCreateTime,Date toCreateTime,Date fromTime, Date toTime){
		return settingDAO.findInvitationCountByPage(manager, guider, technician, location, states,title, fromCreateTime, toCreateTime, fromTime, toTime);
	}
	
	/*******************************************************************************************************/
	/******************************    预约申请分页查询相关       **********************************************/
	/*******************************************************************************************************/
	
	/**
	 * 第一次获得预约申请信息
	 * @return List<Application>
	 * @author cindy
	 */
	public List<Application> findFirstApplicationByPage(){
		return settingDAO.findApplicationByPage(null, null, null, null, null, null, null, 5, 0);
	}
	
	/**
	 * 获得所有预约申请个数
	 * @return Integer
	 * @author cindy
	 */
	public Integer findApplicationCount(){
		return settingDAO.getCount(Application.class);
	}
	
	/**
	 * Application 预约申请条件分页查询信息
	 * @param name 申请人姓名
	 * @param phoneNumber 申请人电话
	 * @param mail 申请人邮箱
	 * @param company 申请人公司
	 * @param mName 项目经理姓名
	 * @param fromTime 申请时间起始
	 * @param toTime 申请时间结束
	 * @param pageCount 每页显示的页数
	 * @param page 显示第几页
	 * @return List<Application>
	 * @author cindy
	 */
	public List<Application> findApplicationByPage(final String name,final String phoneNumber,final String mail,final String company,final String mName,final Date fromTime,final Date toTime,final Integer pageCount,final Integer page){
		return settingDAO.findApplicationByPage(name, phoneNumber, mail, company, mName, fromTime, toTime, pageCount, page);
	}
	
	/**
	 * Application 预约申请条件分页查询信息个数
	 * @param name 申请人姓名
	 * @param phoneNumber 申请人电话
	 * @param mail 申请人邮箱
	 * @param company 申请人公司
	 * @param mName 项目经理姓名
	 * @param fromTime 申请时间起始
	 * @param toTime 申请时间结束
	 * @param pageCount 每页显示的页数
	 * @param page 显示第几页
	 * @return Integer
	 * @author cindy
	 */
	public Integer findApplicationCountByPage(final String name,final String phoneNumber,final String mail,final String company,final String mName,final Date fromTime,final Date toTime){
		return settingDAO.findApplicationCountByPage(name, phoneNumber, mail, company, mName, fromTime, toTime);
	}
	
	/*******************************************************************************************************/
	/******************************    客户信息分页查询相关       **********************************************/
	/*******************************************************************************************************/
	
	/**
	 * 第一次获得客户信息
	 * @return  List<Customer> 
	 * @author cindy
	 */
	public List<Customer> findFirstCustomerByPage(){
		return settingDAO.findCustomerByPage(null, null, null, null, null, null, null, null, null, -1, null, null, null, 5, 0);
	}
	/**
	 * 获得所有客户个数
	 * @return Integer
	 * @author cindy
	 */
	public Integer findCustomerCount(){
		return settingDAO.getCount(Customer.class);
	}
	
	/**
	 * 分页条件查询客户信息
	 * @param name 客户姓名
	 * @param gender 客户性别
	 * @param phoneNumber 客户电话号码
	 * @param mail 客户邮箱
	 * @param company 客户公司
	 * @param fromLastLoginTime 客户最后登录时间 起始
	 * @param toLastLoginTime 客户最后登录时间 结束
	 * @param fromVisitTime 客户参观时间 起始
	 * @param toVisitTime 客户参观时间 结束
	 * @param sendStatus 参观邀请信息发送状态（已发生：1，未发送：0）
	 * @param guider 接待人员姓名
	 * @param manager 客户经理姓名
	 * @param technician 维护人员姓名
	 * @param pageCount 每页显示的页数
	 * @param page 显示第几页
	 * @return  List<Customer> 
	 * @author cindy
	 */
	public List<Customer> findCustomerByPage(final String name,final String gender,final String phoneNumber,final String mail,final String company,final Date fromLastLoginTime,final Date toLastLoginTime,final Date fromVisitTime,final Date toVisitTime,final Integer sendStatus,final String guider,final String manager,final String technician,final Integer pageCount,final Integer page){
		return settingDAO.findCustomerByPage(name, gender, phoneNumber, mail, company, fromLastLoginTime, toLastLoginTime, fromVisitTime, toVisitTime, sendStatus, guider, manager, technician, pageCount, page);
	}
	/**
	 * 分页条件查询客户信息个数
	 * @param name 客户姓名
	 * @param gender 客户性别
	 * @param phoneNumber 客户电话号码
	 * @param mail 客户邮箱
	 * @param company 客户公司
	 * @param fromLastLoginTime 客户最后登录时间 起始
	 * @param toLastLoginTime 客户最后登录时间 结束
	 * @param fromVisitTime 客户参观时间 起始
	 * @param toVisitTime 客户参观时间 结束
	 * @param sendStatus 参观邀请信息发送状态（已发生：1，未发送：0）
	 * @param guider 接待人员姓名
	 * @param manager 客户经理姓名
	 * @param technician 维护人员姓名
	 * @param pageCount 每页显示的页数
	 * @param page 显示第几页
	 * @return  List<Customer> 
	 * @author cindy
	 */
	public Integer findCustomerCountByPage(final String name,final String gender,final String phoneNumber,final String mail,final String company,final Date fromLastLoginTime,final Date toLastLoginTime,final Date fromVisitTime,final Date toVisitTime,final Integer sendStatus,final String guider,final String manager,final String technician){
		return settingDAO.findCustomerCountByPage(name, gender, phoneNumber, mail, company, fromLastLoginTime, toLastLoginTime, fromVisitTime, toVisitTime, sendStatus, guider, manager, technician);
	}
	
	/**
	 * 根据 invitationId 查找相应的 Customer列表
	 * @param invitationId
	 * @return List<Customer>
	 * @author cindy
	 */
	public List<Customer> findCustomerByInvitationId(Integer invitationId){
		return settingDAO.findByProperty(Customer.class, "invitation.invitationId", invitationId);
	}
}
