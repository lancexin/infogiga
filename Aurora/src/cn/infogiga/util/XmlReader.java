package cn.infogiga.util;

import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXWriter;

/**
 * XML读取工具
 * @author ya
 *
 */
public class XmlReader {
	
	private static Log log = LogFactory.getLog(XmlReader.class); 
	
	private SAXReader reader;
	private Document document;
	
	public XmlReader() {}
	
	public XmlReader(String fileName) {
		read(fileName);				
	}	
	
	public XmlReader(InputStream in) {
		read(in);
	}
	
	public String getType(String type) {
		Element root = document.getRootElement();		
		String data = root.element("pc").attribute(type).getText();
		
		return data;
	}
	
	public String getElementValue(String node) {
		return document.getRootElement().element("pc").element(node).getText();
	}
	
	public void read(String fileName) {
		reader = new SAXReader();		
		try {
			document = reader.read(fileName);
		} catch (DocumentException e) {
			log.error("XML读取失败");
		}
	}
	
	public void read(InputStream in) {
		reader = new SAXReader();		
		try {
			document = reader.read(in);
		} catch (DocumentException e) {
			log.error("XML读取失败");
		}
	}
	
	public void read(InputStream in, String encoding) {
		reader = new SAXReader();		
		reader.setEncoding(encoding);
		try {
			document = reader.read(in);
		} catch (DocumentException e) {
			log.error("XML读取失败");
		}
	}
}
