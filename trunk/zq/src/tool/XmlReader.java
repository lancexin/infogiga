package tool;

import java.io.InputStream;
import java.io.StringReader;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

/**
 * XML文件读取类
 * @author ya
 */
public class XmlReader {	
	
	private static Logger log = Logger.getLogger(XmlReader.class.getName());
	
	private SAXReader reader;
	private Document document;
	
	/**
	 * 默认构造器
	 */
	public XmlReader() {}
	
	
	public XmlReader(String xml){
		read(xml);
	}	
	
	/**
	 * 输入流构造
	 * @param in 输入流
	 */
	public XmlReader(InputStream in) {
		read(in);
	}
	
	/**
	 * 获取xml中的数据
	 * @param node 节点的名字，pc或者phone
	 * @return map xml的属性名和属性值
	 */
	public String getData(String node) {
		Element root = document.getRootElement();
		String data = root.element(node).attribute("request").getText();//node节点下的request属性值
		
		if(data.equals(null)) {
			data = "";
		}
		return data;
	}
	
	/**
	 * 读取xml文件
	 * @param fileName xml文件名
	 */
	public void read(String xml) {
		reader = new SAXReader();
		
		try {
			document = reader.read(new InputSource(new StringReader(xml)));

		} catch (DocumentException e) {
			log.error("XML读取失败");
		}
	}
	
	/**
	 * 从输入流读取xml文件
	 * @param in 输入流
	 */
	public void read(InputStream in) {
		reader = new SAXReader();
		reader.setEncoding("GBK");
		try {
			document = reader.read(in);
		} catch (DocumentException e) {
			log.error("XML读取失败");
		}
	}
	
	public boolean read(InputStream in, String encoding) {
		reader = new SAXReader();		
		reader.setEncoding(encoding);
		try {
			document = reader.read(in);
		} catch (DocumentException e) {
			log.error("XML读取失败"+ e);
			return false;
		}
		return true;
	}
	
	public String getAttributeForPC(String node) {
		Element root = document.getRootElement();		
		String data = root.element("pc").attribute(node).getText();
		
		return data;
	}
}
