package cn.infogiga.service.qrcode;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.infogiga.service.QrcodeService;
import cn.infogiga.util.Config;
import cn.infogiga.util.HttpToolkit;
import cn.infogiga.util.StringToolkit;
import cn.infogiga.util.XmlToolkit;

/**
 * 二维码webservice服务
 * @author chroya
 *
 */
public class QrcodeImpl implements Qrcode{

	private static Log log = LogFactory.getLog(QrcodeImpl.class);
	private QrcodeService qrcodeService;	//二维码导览服务
	public void setQrcodeService(QrcodeService qrcodeService) {
		this.qrcodeService = qrcodeService;
	}
	public QrcodeService getQrcodeService() {
		return qrcodeService;
	}

	/**
	 * 生成多个二维码图片
	 * @see cn.infogiga.service.qrcode.Qrcode#createQrcode(java.util.ArrayList)
	 */
	public void createQrcode(ArrayList<QrcodeBean> qrcodes) {
		String xmlString = XmlToolkit.createXmlForQrcode(qrcodes);
		HttpToolkit.postMessage(xmlString, Config.HOST_S, Config.PORT_S);
	}	
	
	/**
	 * 生成单个二维码图片
	 * @param qrcode
	 */
	public void createQrcode(QrcodeBean qrcode) {
		String xmlString = XmlToolkit.createXmlForQrcode(qrcode);
		HttpToolkit.postMessage(xmlString, Config.HOST_S, Config.PORT_S);
	}
	
	/**
	 * 是否是新格式二维码，是，则保存体验记录，并跳转否则什么也不做
	 * @see cn.infogiga.service.qrcode.Qrcode#isNew(java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean isNew(String equipmentCode, String systemCode, String qrcode, String regionCode, String template) {		
		if(StringToolkit.isNewQrcode(qrcode)) {
			/*******新的访问*******/
			qrcodeService.visit(equipmentCode, systemCode, qrcode, regionCode, template);
			log.info("成功");
			return true;
		}
		log.info("失败");
		return false;
	}
}
