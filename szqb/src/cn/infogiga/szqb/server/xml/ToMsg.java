package cn.infogiga.szqb.server.xml;

import java.util.ArrayList;
import java.util.List;

import cn.infogiga.szqb.vo.JsonPage;
import cn.infogiga.szqb.vo.JsonPeriodical;
import cn.infogiga.szqb.vo.JsonReader;
import cn.infogiga.szqb.vo.JsonReadtype;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("msg")
public class ToMsg implements Msg{
	
	@XStreamImplicit
	private List<JsonReadtype> readtypes = new ArrayList<JsonReadtype>();
	
	@XStreamImplicit
	private List<JsonReader> readers = new ArrayList<JsonReader>();

	@XStreamImplicit
	private List<JsonPeriodical> periodicals = new ArrayList<JsonPeriodical>();

	@XStreamImplicit
	private List<JsonPage> pages = new ArrayList<JsonPage>();
	
	private XmlError error;
	
	public List<JsonReader> getReaders() {
		return readers;
	}


	public void setReaders(List<JsonReader> readers) {
		this.readers = readers;
	}


	public List<JsonReadtype> getReadtypes() {
		return readtypes;
	}


	public void setReadtypes(List<JsonReadtype> readtypes) {
		this.readtypes = readtypes;
	}


	public XmlError getError() {
		return error;
	}


	public void setError(XmlError error) {
		this.error = error;
	}


	public static void main(String[] args) {
		XStream xstream = new XStream();
		xstream.autodetectAnnotations(true);
		ToMsg toMsg = new ToMsg();
		//List<JsonReadtype> list = new ArrayList<JsonReadtype>();
		/*List<JsonReader> list = new ArrayList<JsonReader>();
		for(int i=0;i<10;i++){
			JsonReader r = new JsonReader();
			r.setReaderId(i+"");
			r.setReaderShortName("short"+i);
			r.setReaderName("name"+i);
			r.setTypeName("type"+i);
			//r.s
			list.add(r);
		}
		toMsg.setReaders(list);*/
		XmlError error = new XmlError();
		error.setCode("aaa");
		error.setHireMsg("aaa");
		error.setMsg("aa");
		toMsg.setError(error);
		System.out.println(xstream.toXML(toMsg));
	}


	public List<JsonPeriodical> getPeriodicals() {
		return periodicals;
	}


	public void setPeriodicals(List<JsonPeriodical> periodicals) {
		this.periodicals = periodicals;
	}


	public List<JsonPage> getPages() {
		return pages;
	}


	public void setPages(List<JsonPage> pages) {
		this.pages = pages;
	}
}
