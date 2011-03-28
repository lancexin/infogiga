package cn.infogiga.szqb.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import cindy.page.beanutils.Sync;

@XStreamAlias("readtype")
public class JsonReadtype {
	
	@Sync(value = "id")
	@XStreamAlias("ID")
	String readtypeId;
		
	@Sync(value = "typeName")
	@XStreamAlias("typeName")
	String typeName;
	
	@Sync(value = "shortName")
	@XStreamAlias("shortName")
	String typeShortName;
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public String getReadtypeId() {
		return readtypeId;
	}
	public void setReadtypeId(String readtypeId) {
		this.readtypeId = readtypeId;
	}
	public String getTypeShortName() {
		return typeShortName;
	}
	public void setTypeShortName(String typeShortName) {
		this.typeShortName = typeShortName;
	}
}
