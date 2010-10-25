package cn.infogiga.service.qrcode;

import java.io.Serializable;

public class QrcodeBean implements Serializable {
	
	private static final long serialVersionUID = 7967805135949343033L;
	private String qrcode;
	private int scale;
	private String format;
	private String qrcodePicName;
	
	public QrcodeBean() {
	}

	/**
	 * @param qrcode  二维码
	 * @param scale   大小 如4，5，6
	 * @param format  格式  如png，bmp，gif
	 */
	public QrcodeBean(String qrcode, int scale, String format) {
		this.qrcode = qrcode;
		this.scale = scale;
		this.format = format;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
	/**
	 * 保存二维码图片的名字
	 * @return
	 */
	public String getQrcodePicName() {
		return getQrcode()+ "@"+ getScale()+ "."+ getFormat();
	}
	
	public void setQrcodePicName(String picName) {	
		this.qrcodePicName = getQrcode()+ "@"+ getScale()+ "."+ getFormat();
	}
}
