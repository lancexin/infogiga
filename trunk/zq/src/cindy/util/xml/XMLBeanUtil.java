package cindy.util.xml;

import java.io.File;
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




public class XMLBeanUtil {
	
	public <T extends XMLBean> T getXMLBeanByFile(Class<T> clazz,File file){
		return null;
	}
	
	public <T extends XMLBean> T getXMLBeanByStr(Class<T> clazz,String xmlStr) throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document dom = builder.parse(new InputSource(new StringReader(xmlStr)));
		Element root = dom.getDocumentElement();
		T t = (T) newInstance(clazz);;
		parse(t,root);
		return t;
	}
	
	public <T extends XMLBean> String xmlBeanToStr(T t) throws Exception{
		StringBuffer buffer = new StringBuffer();
		XMLElement xmlElement = t.getClass().getAnnotation(XMLElement.class);
		String fatherTAG = xmlElement.value();
		int isRoot = xmlElement.type();
		Field[] f = t.getClass().getDeclaredFields();
		if(isRoot == XMLTypes.XML_ROOT){
			buffer.append("<?xml version='1.0' encoding='UTF-8'?>");
		}
		buffer.append("<"+fatherTAG+">");
		for(int i = 0;i<f.length;i++){
			XMLField xmlField = f[i].getAnnotation(XMLField.class);
			if(xmlField == null){
				continue;
			}
			
			int type = xmlField.xmlType();
			if(type == XMLTypes.XML_ELEMENT){
				String o = (String) invokeMethod(t,getGETMethodByFieldName(f[i].getName()),new Object[]{});
				xmlAppend(xmlField.value(), o, buffer);
			}else if(type == XMLTypes.XML_LIST){
				List<XMLBean> list = (List<XMLBean>) invokeMethod(t,getGETMethodByFieldName(f[i].getName()),new Object[]{});
				if(list != null){
					if(!"".equals(xmlField.value())){
						buffer.append("<"+xmlField.value()+">");
					}
					
					int size = list.size();
					XMLBean bean = null;
					for(int j=0;j<size;j++){
						bean = list.get(j);
						System.out.println(xmlBeanToStr(bean));
						buffer.append(xmlBeanToStr(bean));
					}
					
					if(!"".equals(xmlField.value())){
						buffer.append("</"+xmlField.value()+">");
					}
				}
			}else if(type == XMLTypes.XML_BEAN){
				XMLBean bean = (XMLBean) invokeMethod(t,getGETMethodByFieldName(f[i].getName()),new Object[]{});
				if(bean != null){
					buffer.append(xmlBeanToStr(bean));
				}
			}
		}
		buffer.append("</"+fatherTAG+">");
		return buffer.toString();
	}
	
	public <T extends XMLBean> boolean xmlBeanToFile(T t,File file){
		return false;
	}
	
	private <T extends XMLBean>  void parse(T t,Element element) throws Exception{
		if(element == null){
			return;
		}
		Field[] f = t.getClass().getDeclaredFields();
		
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
					invokeMethod(t,getSETMethodByFieldName(f[i].getName()),o);
				}
				
			}else if(type == XMLTypes.XML_LIST){
				List<XMLBean> xList = new ArrayList<XMLBean>();
				XMLBean xBean = (XMLBean) newInstance(xmlField.mClass());
				XMLElement xmlElement = xBean.getClass().getAnnotation(XMLElement.class);
				
				NodeList list = element.getElementsByTagName(xmlElement.value());
				if(list != null && list.getLength() != 0){
					int size = list.getLength();
					for(int j = 0;j<size;j++){
						Element el = (Element) list.item(j);
						XMLBean bean = (XMLBean) newInstance(xmlField.mClass());
						parse(bean,el);
						xList.add(bean);
					}
					Object[] o = {xList};
					invokeMethod(t,getSETMethodByFieldName(f[i].getName()),o);
				}
			}else if(type == XMLTypes.XML_BEAN){
				XMLBean xBean = (XMLBean) newInstance(xmlField.mClass());
				XMLElement xmlElement = xBean.getClass().getAnnotation(XMLElement.class);
				Element e = getElByTagName(element, xmlElement.value());
				parse(xBean,e);
				Object[] o = {xBean};
				invokeMethod(t,getSETMethodByFieldName(f[i].getName()),o);
			}
		}
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
		return n.getNodeValue();
	}
	
	private Element getElByTagName(Element el,String str){
		NodeList list = el.getElementsByTagName(str);
		return (Element) list.item(0);
	}
	
	private void xmlAppend(String tag,String value,StringBuffer buffer){
		if(value != null){
			buffer.append("<");buffer.append(tag);buffer.append(">");
			buffer.append(value);
			buffer.append("</");buffer.append(tag);buffer.append(">");
		}
	}
	
	
	private Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception{        
        Class ownerClass = owner.getClass();
        Class[] argsClass = new Class[args.length];        
        for (int i = 0, j = args.length; i < j; i++) {        
            argsClass[i] = args[i].getClass();        
        }    
        //System.out.println(methodName);
        Method method = ownerClass.getMethod(methodName, argsClass);
        return method.invoke(owner, args);
    }
	
	private Object newInstance(final Class clazz){
		try {
			Constructor cons = clazz.getConstructor();
			return cons.newInstance(new Class[]{});
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
}
