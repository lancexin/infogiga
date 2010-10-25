package cn.infogiga.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Invitation entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Invitation extends cn.infogiga.bean.BaseBean implements
		java.io.Serializable {

	// Fields

	private Integer invitationId;
	private Manager managerByGuiderId;
	private Manager managerByManagerId;
	private Mmstemplate mmstemplate;
	private Manager managerByTechnicianId;
	private Date visitTime;
	private String location;
	private Integer completeStatus;
	private Date createTime;
	private Integer penpleCount;
	private String invitationTitle;
	private Date endTime;
	private Set customers = new HashSet(0);

	// Constructors

	/** default constructor */
	public Invitation() {
	}

	/** full constructor */
	public Invitation(Manager managerByGuiderId, Manager managerByManagerId,
			Mmstemplate mmstemplate, Manager managerByTechnicianId,
			Date visitTime, String location, Integer completeStatus,
			Date createTime, Integer penpleCount, String invitationTitle,
			Date endTime, Set customers) {
		this.managerByGuiderId = managerByGuiderId;
		this.managerByManagerId = managerByManagerId;
		this.mmstemplate = mmstemplate;
		this.managerByTechnicianId = managerByTechnicianId;
		this.visitTime = visitTime;
		this.endTime = endTime;
		this.location = location;
		this.completeStatus = completeStatus;
		this.createTime = createTime;
		this.penpleCount = penpleCount;
		this.invitationTitle = invitationTitle;
		this.endTime = endTime;
		this.customers = customers;
	}

	// Property accessors

	public Integer getInvitationId() {
		return this.invitationId;
	}

	public void setInvitationId(Integer invitationId) {
		this.invitationId = invitationId;
	}

	public Manager getManagerByGuiderId() {
		return this.managerByGuiderId;
	}

	public void setManagerByGuiderId(Manager managerByGuiderId) {
		this.managerByGuiderId = managerByGuiderId;
	}

	public Manager getManagerByManagerId() {
		return this.managerByManagerId;
	}

	public void setManagerByManagerId(Manager managerByManagerId) {
		this.managerByManagerId = managerByManagerId;
	}

	public Mmstemplate getMmstemplate() {
		return this.mmstemplate;
	}

	public void setMmstemplate(Mmstemplate mmstemplate) {
		this.mmstemplate = mmstemplate;
	}

	public Manager getManagerByTechnicianId() {
		return this.managerByTechnicianId;
	}

	public void setManagerByTechnicianId(Manager managerByTechnicianId) {
		this.managerByTechnicianId = managerByTechnicianId;
	}

	public Date getVisitTime() {
		return this.visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getCompleteStatus() {
		return this.completeStatus;
	}

	public void setCompleteStatus(Integer completeStatus) {
		this.completeStatus = completeStatus;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getPenpleCount() {
		return this.penpleCount;
	}

	public void setPenpleCount(Integer penpleCount) {
		this.penpleCount = penpleCount;
	}

	public String getInvitationTitle() {
		return this.invitationTitle;
	}

	public void setInvitationTitle(String invitationTitle) {
		this.invitationTitle = invitationTitle;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Set getCustomers() {
		return this.customers;
	}

	public void setCustomers(Set customers) {
		this.customers = customers;
	}

}