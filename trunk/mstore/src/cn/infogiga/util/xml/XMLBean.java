package cn.infogiga.util.xml;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import java.lang.reflect.Method; 
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
/**
 * @author Cindy Lee
 */
public class XMLBean {
	
	protected String xmlStr = null;
	
	public boolean hasInitlize = false;
	
	public XMLBean(){}
	
	public XMLBean(String xml){
		xmlStr = xml;
	}
	
	public void parse(String xml) throws Exception{
		xmlStr = xml;
		parse();
	}
	
	private void parse(Element element) throws Exception{
		if(element == null){
			return;
		}
		Field[] f = this.getClass().getDeclaredFields();
		
		for(int i=0;i<f.length;i++){
			XMLField xmlField = f[i].getAnnotation(XMLField.class);
			
			if(xmlField == null){
				continue;
			}
			
			int type = xmlField.xmlType();
			if(type == XMLTypes.XML_ELEMENT){
				String value = getElStrByTagName(element, xmlField.value());
				if(value != null){
					Object[] o = {value};
					invokeMethod(this,getSETMethodByFieldName(f[i].getName()),o);
				}
				
			}else if(type == XMLTypes.XML_LIST){
				List<XMLBean> xList = new ArrayList<XMLBean>();
				XMLBean xBean = newInstance(xmlField.mClass());
				XMLElement xmlElement = xBean.getClass().getAnnotation(XMLElement.class);
				
				NodeList list = element.getElementsByTagName(xmlElement.value());
				if(list != null && list.getLength() != 0){
					int size = list.getLength();
					for(int j = 0;j<size;j++){
						Element el = (Element) list.item(j);
						XMLBean bean = newInstance(xmlField.mClass());
						bean.parse(el);
						xList.add(bean);
					}
					Object[] o = {xList};
					invokeMethod(this,getSETMethodByFieldName(f[i].getName()),o);
				}
			}else if(type == XMLTypes.XML_BEAN){
				XMLBean xBean = newInstance(xmlField.mClass());
				XMLElement xmlElement = xBean.getClass().getAnnotation(XMLElement.class);
				Element e = getElByTagName(element, xmlElement.value());
				xBean.parse(e);
				Object[] o = {xBean};
				invokeMethod(this,getSETMethodByFieldName(f[i].getName()),o);
			}
		}
	}
	
	public void parse() throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document dom;
		try {
			builder = factory.newDocumentBuilder();
			dom = builder.parse(new InputSource(new StringReader(xmlStr)));
			Element root = dom.getDocumentElement();
			
			parse(root);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		hasInitlize = true;
		
	}
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		XMLElement xmlElement = this.getClass().getAnnotation(XMLElement.class);
		String fatherTAG = xmlElement.value();
		int type = xmlElement.type();
		Field[] f = this.getClass().getDeclaredFields();
		if(type == XMLTypes.XML_ROOT){
			buffer.append("<?xml version='1.0' encoding='UTF-8'?>");
		}
		buffer.append("<"+fatherTAG+">");
		for(int i = 0;i<f.length;i++){
			XMLField xmlField = f[i].getAnnotation(XMLField.class);
			if(xmlField == null){
				continue;
			}
			
			int type2 = xmlField.xmlType();
			if(type2 == XMLTypes.XML_ELEMENT){
				try {				
					String o = (String) invokeMethod(this,getGETMethodByFieldName(f[i].getName()),new Object[]{});
					//String o = (String) getFieldProperty(this.getClass().toString(), f[i].getName());
					append(xmlField.value(), o, buffer);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(type2 == XMLTypes.XML_LIST){
				try {
					List<XMLBean> list = (List<XMLBean>) invokeMethod(this,getGETMethodByFieldName(f[i].getName()),new Object[]{});
					if(list != null){
						if(!"".equals(xmlField.value())){
							buffer.append("<"+xmlField.value()+">");
						}
						
						int size = list.size();
						XMLBean bean = null;
						for(int j=0;j<size;j++){
							bean = list.get(j);
							buffer.append(bean.toString());
						}
						
						if(!"".equals(xmlField.value())){
							buffer.append("</"+xmlField.value()+">");
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(type2 == XMLTypes.XML_BEAN){
				try {
					XMLBean bean = (XMLBean) invokeMethod(this,getGETMethodByFieldName(f[i].getName()),new Object[]{});
				if(bean != null){
					buffer.append(bean);
				}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		buffer.append("</"+fatherTAG+">");
		return buffer.toString();
	}
	
	
	private String getGETMethodByFieldName(String name){
		String firstLetter = name.substring(0, 1).toUpperCase();  
		return "get" + firstLetter + name.substring(1);  
	}
	
	private String getSETMethodByFieldName(String name){
		String firstLetter = name.substring(0, 1).toUpperCase();  

		return "set" + firstLetter + name.substring(1);  
	}
	
	private String getElStrByTagName(Element el,String str){
		if(el == null){
			return null;
		}
		NodeList list = el.getElementsByTagName(str);
		if(list == null){
			return null;
		}
		Node e = list.item(0);
		if(e == null){
			return null;
		}
		Node n = e.getFirstChild();
		if(n == null){
			return null;
		}
		NodeList nl = e.getChildNodes();
		return n.getNodeValue();
	}
	
	private Element getElByTagName(Element el,String str){
		NodeList list = el.getElementsByTagName(str);
		return (Element) list.item(0);
	}
	
	private void append(String tag,String value,StringBuffer buffer){
		if(value != null){
			buffer.append("<");buffer.append(tag);buffer.append(">");
			buffer.append(value);
			buffer.append("</");buffer.append(tag);buffer.append(">");
		}
	}
	
	private XMLBean newInstance(final Class clazz){
		try {
			Constructor cons = clazz.getConstructor();
			return (XMLBean) cons.newInstance(new Class[]{});
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception{        
        Class ownerClass = owner.getClass();
        Class[] argsClass = new Class[args.length];        
        for (int i = 0, j = args.length; i < j; i++) {        
            argsClass[i] = args[i].getClass();        
        }    
        //System.out.println(methodName);
        Method method = ownerClass.getMethod(methodName, argsClass);
        return method.invoke(owner, args);
    } 
	
}
