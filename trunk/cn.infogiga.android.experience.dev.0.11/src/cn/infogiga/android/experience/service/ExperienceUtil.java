package cn.infogiga.android.experience.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import cn.infogiga.android.experience.R;
import cn.infogiga.android.experience.bean.PCInfo;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

public class ExperienceUtil {
	private static final String tag = "ExperienceUtil";
	
	private final static String initPath = "/data/data/cn.infogiga.android.experience/files/config.xml";
	
	public static boolean string2File(String res, String filePath) { 
	        boolean flag = true; 
	        BufferedReader bufferedReader = null; 
	        BufferedWriter bufferedWriter = null; 
	        try { 
	                File distFile = new File(filePath); 
	                if (!distFile.getParentFile().exists()) distFile.getParentFile().mkdirs(); 
	                bufferedReader = new BufferedReader(new StringReader(res)); 
	                bufferedWriter = new BufferedWriter(new FileWriter(distFile)); 
	                char buf[] = new char[1024];         //锟街凤拷锟斤拷锟�?
	                int len; 
	                while ((len = bufferedReader.read(buf)) != -1) { 
	                        bufferedWriter.write(buf, 0, len); 
	                } 
	                bufferedWriter.flush(); 
	                bufferedReader.close(); 
	                bufferedWriter.close(); 
	        } catch (IOException e) { 
	                e.printStackTrace(); 
	                flag = false; 
	                return flag; 
	        } finally { 
	                if (bufferedReader != null) { 
	                        try { 
	                                bufferedReader.close(); 
	                        } catch (IOException e) { 
	                                e.printStackTrace(); 
	                        } 
	                } 
	        } 
	        return flag; 
	}
	
	public static PCInfo stringToPcInfo(String xml){
		Log.e(tag,xml);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document dom;
		PCInfo pcInfo = new PCInfo();
		try {
			builder = factory.newDocumentBuilder();
			dom = builder.parse(new InputSource(new StringReader(xml)));
			Element root = dom.getDocumentElement();
			
			pcInfo.setMac(getElStrByTagName(root, "mac"));
			pcInfo.setCmp_name(getElStrByTagName(root, "cmp_name"));
			pcInfo.setHarddisk(getElStrByTagName(root, "harddisk"));
			pcInfo.setIp(getElStrByTagName(root, "ip"));
		
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pcInfo;
		
	}
	
	private static String getElStrByTagName(Element el,String str){
		if(null == el){
			return null;
		}
		
		NodeList list = el.getElementsByTagName(str);
		Node e = list.item(0);
		if(null == list || null == e){
			return null;
		}
		Node n = e.getFirstChild();
		if(n == null){
			return null;
		}
		return n.getNodeValue();
	}
	
	private static Element getElByTagName(Element el,String str){
		NodeList list = el.getElementsByTagName(str);
		return (Element) list.item(0);
	}
	
	
	
	private static void append(StringBuffer buffer,String tag,String key){
		if(key != null){
			buffer.append("<"+tag+">"+key+"</"+tag+">");
		}
	}
}
