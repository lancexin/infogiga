package cn.infogiga.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvitationPO {

	private String managerUserName; //客户经理的用户名
	private String guiderName; //接待人员的名字
	private String technicanName;	//技术人员名字
	private String templateName;	//彩信模板名字
	private String mmsContent;		//彩信内容
	private Date visitTime;		//时间
	private Date endTime;
	private String location;	//地点
	private String invitationTitle; //名字
	
	private Integer guiderId = -1;
	private Integer technicanId = -1;
	private Integer mmsId = -1;
	private Integer managerId = -1;
	private ArrayList<CustomerPO> customers = new ArrayList<CustomerPO>();
	public InvitationPO(String managerUserName, String guiderName,
			String technicanName, String templateName, String mmsContent,
			Date visitTime, Date endTime, String location,
			String invitationTitle, Integer guiderId, Integer technicanId,
			Integer mmsId, Integer managerId, ArrayList<CustomerPO> customers) {
		super();
		this.managerUserName = managerUserName;
		this.guiderName = guiderName;
		this.technicanName = technicanName;
		this.templateName = templateName;
		this.mmsContent = mmsContent;
		this.visitTime = visitTime;
		this.endTime = endTime;
		this.location = location;
		this.invitationTitle = invitationTitle;
		this.guiderId = guiderId;
		this.technicanId = technicanId;
		this.mmsId = mmsId;
		this.managerId = managerId;
		this.customers = customers;
	}

	public InvitationPO(){};
	
	public String getMmsContent() {
		return mmsContent;
	}

	public void setMmsContent(String mmsContent) {
		this.mmsContent = mmsContent;
	}

	public String getInvitationTitle() {
		return invitationTitle;
	}
	public void setInvitationTitle(String invitationTitle) {
		this.invitationTitle = invitationTitle;
	}
	public String getManagerUserName() {
		return managerUserName;
	}
	public void setManagerUserName(String managerUserName) {
		this.managerUserName = managerUserName;
	}
	public String getGuiderName() {
		return guiderName;
	}
	public void setGuiderName(String guiderName) {
		this.guiderName = guiderName;
	}
	public String getTechnicanName() {
		return technicanName;
	}
	public void setTechnicanName(String technicanName) {
		this.technicanName = technicanName;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public Date getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public ArrayList<CustomerPO> getCustomers() {
		return customers;
	}
	public void setCustomers(ArrayList<CustomerPO> customers) {
		this.customers = customers;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getGuiderId() {
		return guiderId;
	}

	public void setGuiderId(Integer guiderId) {
		this.guiderId = guiderId;
	}

	public Integer getTechnicanId() {
		return technicanId;
	}

	public void setTechnicanId(Integer technicanId) {
		this.technicanId = technicanId;
	}

	public Integer getMmsId() {
		return mmsId;
	}

	public void setMmsId(Integer mmsId) {
		this.mmsId = mmsId;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	
}
