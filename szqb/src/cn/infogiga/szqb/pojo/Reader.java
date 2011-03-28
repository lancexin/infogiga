package cn.infogiga.szqb.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Reader entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Reader implements java.io.Serializable {

	// Fields

	private Integer id;
	private Readtype readtype;
	private String readerName;
	private String shortName;
	private String pic;
	private Set periodicals = new HashSet(0);

	// Constructors

	/** default constructor */
	public Reader() {
	}

	/** full constructor */
	public Reader(Readtype readtype, String readerName, String shortName,
			String pic, Set periodicals) {
		this.readtype = readtype;
		this.readerName = readerName;
		this.shortName = shortName;
		this.pic = pic;
		this.periodicals = periodicals;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Readtype getReadtype() {
		return this.readtype;
	}

	public void setReadtype(Readtype readtype) {
		this.readtype = readtype;
	}

	public String getReaderName() {
		return this.readerName;
	}

	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Set getPeriodicals() {
		return this.periodicals;
	}

	public void setPeriodicals(Set periodicals) {
		this.periodicals = periodicals;
	}

}