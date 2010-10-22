package cn.infogiga.exp.pojo;

/**
 * Emailservice entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Emailservice  implements java.io.Serializable,cindy.web.pojo.POJO{

	// Fields

	private Integer serviceId;
	private Emailtemplete emailtemplete;
	private String serviceName;
	private String serviceCode;
	private Integer status;

	// Constructors

	/** default constructor */
	public Emailservice() {
	}

	/** full constructor */
	public Emailservice(Emailtemplete emailtemplete, String serviceName,
			String serviceCode, Integer status) {
		this.emailtemplete = emailtemplete;
		this.serviceName = serviceName;
		this.serviceCode = serviceCode;
		this.status = status;
	}

	// Property accessors

	public Integer getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Emailtemplete getEmailtemplete() {
		return this.emailtemplete;
	}

	public void setEmailtemplete(Emailtemplete emailtemplete) {
		this.emailtemplete = emailtemplete;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceCode() {
		return this.serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}