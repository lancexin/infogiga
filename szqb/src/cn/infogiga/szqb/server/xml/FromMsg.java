package cn.infogiga.szqb.server.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("msg")
public class FromMsg implements Msg {
	
	@XStreamAlias("type")
	private Integer type;
	@XStreamAlias("readtypeID")
	private Integer readtypeID;
	@XStreamAlias("readerID")
	private Integer readerID;
	@XStreamAlias("start")
	private Integer start;
	@XStreamAlias("limit")
	private Integer limit;
	@XStreamAlias("periodicalID")
	private Integer periodicalID;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getReadtypeID() {
		return readtypeID;
	}
	public void setReadtypeID(Integer readtypeID) {
		this.readtypeID = readtypeID;
	}
	public Integer getReaderID() {
		return readerID;
	}
	public void setReaderID(Integer readerID) {
		this.readerID = readerID;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getPeriodicalID() {
		return periodicalID;
	}
	public void setPeriodicalID(Integer periodicalID) {
		this.periodicalID = periodicalID;
	}
}
