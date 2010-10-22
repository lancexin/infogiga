package test;

import java.util.Date;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

import cn.infogiga.exp.pojo.Smsarray;
import cn.infogiga.exp.service.ExpService;

public class TestMain {
	public static void main(String[] args) {
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("WebRoot/WEB-INF/spring/applicationContext.xml")); 
		ExpService es = (ExpService) factory.getBean("ExpService");
		for(int i=0;i<5000;i++){
			Smsarray smsarray = new Smsarray();
			smsarray.setContext("111111111111111");
			smsarray.setSendTime(new Date(new Date().getTime()+60*60*1000));
			smsarray.setPhoneNumbers("15268114857,");
			smsarray.setStatus(0);
			es.save(smsarray);
		}
	}
}
