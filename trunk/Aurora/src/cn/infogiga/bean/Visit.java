package cn.infogiga.bean;

import java.util.Date;

/**
 * Visit entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Visit extends cn.infogiga.bean.BaseBean implements
		java.io.Serializable {

	// Fields

	private Integer visitId;
	private Qrcode qrcode;
	private String equipmentCode;
	private String systemCode;
	private String checkMethod;
	private Date visitTime;

	// Constructors

	/** default constructor */
	public Visit() {
	}

	/** full constructor */
	public Visit(Qrcode qrcode, String equipmentCode, String systemCode,
			String checkMethod, Date visitTime) {
		this.qrcode = qrcode;
		this.equipmentCode = equipmentCode;
		this.systemCode = systemCode;
		this.checkMethod = checkMethod;
		this.visitTime = visitTime;
	}

	// Property accessors

	public Integer getVisitId() {
		return this.visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	public Qrcode getQrcode() {
		return this.qrcode;
	}

	public void setQrcode(Qrcode qrcode) {
		this.qrcode = qrcode;
	}

	public String getEquipmentCode() {
		return this.equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public String getSystemCode() {
		return this.systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getCheckMethod() {
		return this.checkMethod;
	}

	public void setCheckMethod(String checkMethod) {
		this.checkMethod = checkMethod;
	}

	public Date getVisitTime() {
		return this.visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

}