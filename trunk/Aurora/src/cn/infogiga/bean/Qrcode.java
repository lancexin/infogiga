package cn.infogiga.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Qrcode entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Qrcode extends cn.infogiga.bean.BaseBean implements
		java.io.Serializable {

	// Fields

	private Integer codeId;
	private Customer customer;
	private String wapCode;
	private String mmsCode;
	private Set visits = new HashSet(0);

	// Constructors

	/** default constructor */
	public Qrcode() {
	}

	/** minimal constructor */
	public Qrcode(Customer customer, String wapCode, String mmsCode) {
		this.customer = customer;
		this.wapCode = wapCode;
		this.mmsCode = mmsCode;
	}

	/** full constructor */
	public Qrcode(Customer customer, String wapCode, String mmsCode, Set visits) {
		this.customer = customer;
		this.wapCode = wapCode;
		this.mmsCode = mmsCode;
		this.visits = visits;
	}

	// Property accessors

	public Integer getCodeId() {
		return this.codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getWapCode() {
		return this.wapCode;
	}

	public void setWapCode(String wapCode) {
		this.wapCode = wapCode;
	}

	public String getMmsCode() {
		return this.mmsCode;
	}

	public void setMmsCode(String mmsCode) {
		this.mmsCode = mmsCode;
	}

	public Set getVisits() {
		return this.visits;
	}

	public void setVisits(Set visits) {
		this.visits = visits;
	}

}