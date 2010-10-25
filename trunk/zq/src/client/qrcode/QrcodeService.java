package client.qrcode;

/**
 * 二维码服务，调用online的webservice
 * @author chroya
 *
 */
public class QrcodeService {
	private QrcodeClient client;
	private QrcodePortType service;
	private static QrcodeService instance = new QrcodeService(); 
	
	private QrcodeService() {
		client = new QrcodeClient();		
		service = client.getQrcodeHttpPort();
	}
	
	public static QrcodeService getInstance() {
		if(instance == null) {
			instance = new QrcodeService();
		}
		return instance;
	}

	/**
	 * @param equipmentCode	设备代码
	 * @param systemCode	系统代码
	 * @param qrcode		二维码
	 * @param regionCode	区域代码
	 * @param sms			短信内容
	 * @return
	 */
	public boolean isNew(String equipmentCode, String systemCode, String qrcode, String regionCode, String template) {
		return service.isNew(equipmentCode, systemCode, qrcode, regionCode, template);
	}
}
