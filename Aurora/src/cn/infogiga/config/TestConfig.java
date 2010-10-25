package cn.infogiga.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TestConfig {
	public static String PATH = "WEB-INF/power-config.xml";
	
	//public static power
	/**
	 * @param args
	 * @throws DocumentException 
	 */
	public static void main(String[] args) throws DocumentException {
		File configXml = new File("WebRoot/WEB-INF/power-config.xml").getAbsoluteFile(); 
		
		String power = "A,B,C,F,F_A,F_C";
		
		String[] powers = power.split(",");
		
		List<Element> list = new ArrayList<Element>();
		
		
		
		SAXReader reader = new SAXReader(); 
		Document doc = reader.read(configXml); 
		Element root = doc.getRootElement();
		
		TestConfig config = new TestConfig();
		config.choose(root, powers, list);
	    //System.out.println(list.size());  
	    List<String> l = config.getJspInclude(list);
	    System.out.println(l.size());
	}
	
	public void choose(Element root,String[] powers,List<Element> list){
		List menusList = root.elements("item");
		int menusize = menusList.size();
		
		int length = powers.length;
		for(int i=0;i<menusize;i++){
			Element el = (Element) menusList.get(i);
			String id = el.attributeValue("id");
			for(int j=0;j<length;j++){
				if(id.equals(powers[j])){
					list.add(el);
				}
			}
		}		
	}
	
	public List<String> getJspInclude(List<Element> elList){
		List<String> l = new ArrayList<String>();
		int size = elList.size();
		for(int i=0;i<size;i++){
			Element el = elList.get(i);
			List<Element> list = el.elements();
			int lsize = list.size();
			for(int j=0;j<lsize;j++){
				Element el2 = list.get(j);
				if("jsp-include".equals(el2.getName())){
					l.add(el2.getStringValue());
				}
				System.out.println(el2.getName());
				System.out.println(el2.getStringValue());
				
			}
		}
		
		return l;
	}
	
	public String getScript(){
		
		
		return null;
	}

}
