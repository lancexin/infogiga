package cn.infogiga.service;

import java.net.MalformedURLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

import cn.infogiga.service.notification.Notification;
import cn.infogiga.service.qrcode.Qrcode;

public class SystemService {

	private static final Log log = LogFactory.getLog(SystemService.class);
	/**
	 * 通知服务
	 */
	public static final int NOTIFICATION = 0;
	/**
	 * 二维码服务
	 */
	public static final int QRCODE = 1;
	
	private static final String notificationServiceUrl =
		"http://127.0.0.1:8888/online/service/Notification";
	private static final String qrcodeServiceUrl = 
		"http://127.0.0.1:8888/online/service/Qrcode";
	
	private static SystemService instance = new SystemService();
	
	private static Object notificationHandler;
	private static Object qrcodeHandler;
	
	private SystemService(){}
		
	/**
	 * 根据type返回webservice的接口
	 * @param <T> webservice的接口
	 * @param type  SystemService.NOTIFICATION
	 * 				SystemService.QRCODE
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends Object> T getHandler(int type) {
		Object returnValue = null;
		switch (type) {
		case NOTIFICATION:
			if(notificationHandler == null) {
				notificationHandler = getHandler(
						Notification.class, notificationServiceUrl);
			}
			returnValue = notificationHandler;
			/*service=new ObjectServiceFactory().create(Notification.class);
			xf=new XFireProxyFactory(XFireFactory.newInstance().getXFire());
			returnValue = xf.create(service, notificationServiceUrl);*/
			break;
		case QRCODE:
			if(qrcodeHandler == null) {
				qrcodeHandler = getHandler(Qrcode.class, qrcodeServiceUrl);					
			}
			returnValue = qrcodeHandler;
			/*service=new ObjectServiceFactory().create(Qrcode.class);
			xf=new XFireProxyFactory(XFireFactory.newInstance().getXFire());
			returnValue = xf.create(service, qrcodeServiceUrl);*/
			break;
		}
		return (T) returnValue;
	}
	
	/**
	 * 获取SystemService对象
	 * @return
	 */
	public static SystemService getService() {
		if(instance == null) {
			instance = new SystemService();
		}
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	private static Object getHandler(Class clazz, String url) {
		Service service=new ObjectServiceFactory().create(clazz);
		XFireProxyFactory xf=new XFireProxyFactory(XFireFactory.newInstance().getXFire());
		Object returnValue = null;
		try {
			returnValue = xf.create(service, url);
		} catch (MalformedURLException e) {
			log.error("url解析错误", e);
		}
		return returnValue;
	}
}
