package cn.infogiga.service.qrcode;

import java.util.ArrayList;

public interface Qrcode {

	public void createQrcode(ArrayList<QrcodeBean> qrcodes);
	public void createQrcode(QrcodeBean qrcode);
	public boolean isNew(String equipmentCode, String systemCode, String qrcode, String regionCode, String template);
}
