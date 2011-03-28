package cn.infogiga.szqb.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import cindy.page.beanutils.Sync;

@XStreamAlias("reader")
public class JsonReader {
	
	@Sync(value = "id")
	@XStreamAlias("ID")
	String readerId;
	
	@Sync(value = "readtype.id")
	@XStreamAlias("readtypeID")
	String readtypeId;
	
	@Sync(value = "readtype.typeName")
	@XStreamAlias("typeName")
	String typeName;
	
	@Sync(value = "readtype.shortName")
	@XStreamAlias("typeShortName")
	String typeShortName;
	
	@Sync(value = "readerName")
	@XStreamAlias("readerName")
	String readerName;
	
	@Sync(value = "shortName")
	@XStreamAlias("shortName")
	String readerShortName;

	public String getReaderId() {
		return readerId;
	}

	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}

	public String getReadtypeId() {
		return readtypeId;
	}

	public void setReadtypeId(String readtypeId) {
		this.readtypeId = readtypeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeShortName() {
		return typeShortName;
	}

	public void setTypeShortName(String typeShortName) {
		this.typeShortName = typeShortName;
	}

	public String getReaderName() {
		return readerName;
	}

	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}

	public String getReaderShortName() {
		return readerShortName;
	}

	public void setReaderShortName(String readerShortName) {
		this.readerShortName = readerShortName;
	}
}
