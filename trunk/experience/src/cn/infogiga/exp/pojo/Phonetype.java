package cn.infogiga.exp.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractPhonetype entity provides the base persistence definition of the
 * Phonetype entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Phonetype implements java.io.Serializable,cindy.web.pojo.POJO {

	// Fields

	private Integer phonetypeId;
	private Phonebrand phonebrand;
	private String phonetypeName;
	private Integer status;
	private String backup1;
	private String backup2;
	private String backup3;
	private Set downloadstats = new HashSet(0);

	// Constructors

	/** default constructor */
	public Phonetype() {
	}

	/** full constructor */
	public Phonetype(Phonebrand phonebrand, String phonetypeName,
			Integer status, String backup1, String backup2, String backup3,
			Set downloadstats) {
		this.phonebrand = phonebrand;
		this.phonetypeName = phonetypeName;
		this.status = status;
		this.backup1 = backup1;
		this.backup2 = backup2;
		this.backup3 = backup3;
		this.downloadstats = downloadstats;
	}

	// Property accessors

	public Integer getPhonetypeId() {
		return this.phonetypeId;
	}

	public void setPhonetypeId(Integer phonetypeId) {
		this.phonetypeId = phonetypeId;
	}

	public Phonebrand getPhonebrand() {
		return this.phonebrand;
	}

	public void setPhonebrand(Phonebrand phonebrand) {
		this.phonebrand = phonebrand;
	}

	public String getPhonetypeName() {
		return this.phonetypeName;
	}

	public void setPhonetypeName(String phonetypeName) {
		this.phonetypeName = phonetypeName;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBackup1() {
		return this.backup1;
	}

	public void setBackup1(String backup1) {
		this.backup1 = backup1;
	}

	public String getBackup2() {
		return this.backup2;
	}

	public void setBackup2(String backup2) {
		this.backup2 = backup2;
	}

	public String getBackup3() {
		return this.backup3;
	}

	public void setBackup3(String backup3) {
		this.backup3 = backup3;
	}

	public Set getDownloadstats() {
		return this.downloadstats;
	}

	public void setDownloadstats(Set downloadstats) {
		this.downloadstats = downloadstats;
	}

}