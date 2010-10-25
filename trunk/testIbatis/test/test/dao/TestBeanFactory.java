package test.dao;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class TestBeanFactory {
	public static Object getBean(String id){
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("WebRoot/WEB-INF/spring.xml")); 
		return factory.getBean(id);
	}
}
