package cn.infogiga.cmpp2.util;



import java.io.UnsupportedEncodingException;

public class WapPushMsg {
	//头部1
	private static final byte[] handler = {0x0B,0x05,0x04,0x0b,(byte)0x84,0x23,(byte)0xf0,0x00,0x03,0x03};
	
	private static final int handler_length = handler.length;
	
	//头部2
	private static final byte[] handler2 = {0x29,0x06,0x06,0x03,(byte)0xAE,(byte)0x81,(byte)0xEA,(byte)0x8D,(byte)0xCA};
	
	private static int handler2_length = handler2.length;
	//wap push分几条发送
	private static byte count = 0x01;
	//当前发送的第几条
	private static byte number = 0X01;
	
	private static final  byte[] body = {
					0x02,
					0x05,                 
					0x6A,                 
					0x00,                  
					0x45 ,                 
					(byte)0xC6,                 
					0x0C,                
					0x03};
	
	private static final int body_lenfth = body.length;
	
	private static final byte[] end_url = {0x00,0x01,0x03};
	
	private static final int end_url_length = end_url.length;
	
	private static final byte[] end = {0x00,0x01,0x01};
	
	private static final int end_length = end.length;
	
	
	
	public static byte[][] getWapPushBytes(String url,String context) throws UnsupportedEncodingException{
		url = getUrl(url);
		//System.out.println(url);
		byte[] url_bytes = url.getBytes("utf-8");
		byte[] context_bytes = context.getBytes("utf-8");
		int url_length = url_bytes.length;
		int context_length = context_bytes.length;
	//	int count = ((handler.length+2)*n+handler2.length+body1.length+length1+lenfth2+6)/140 = n
	//	int count =  (handler2.length+body1.length+length1+length2+6)-1/(140-2-handler.length)+1;
		int total_length = (url_length+context_length+handler2_length+body_lenfth+end_url_length+end_length);
		
		int count = (total_length-1)/128+1;
		
		int end_count = total_length%128;
		
		if(end_count == 0){
			end_count = 128;
		}
		
		byte[] total = new byte[total_length];
		
		System.arraycopy(handler2, 0, total, 0, handler2_length);//0 ~ (handler2_length-1)
		//System.out.println(WapPushMsg.byteToHexStr(total));
		System.arraycopy(body, 0, total, handler2_length, body_lenfth);//handler2_length ~ (handler2_length+body_lenfth)-1
		//System.out.println(WapPushMsg.byteToHexStr(total));
		System.arraycopy(url_bytes, 0, total, handler2_length+body_lenfth, url_length);//handler2_length+body_lenfth ~ (handler2_length+body_lenfth+url_length)-1
		//System.out.println(WapPushMsg.byteToHexStr(total));
		System.arraycopy(end_url, 0, total, handler2_length+body_lenfth+url_length, end_url_length);
		//System.out.println(WapPushMsg.byteToHexStr(total));
		System.arraycopy(context_bytes, 0, total, handler2_length+body_lenfth+url_length+end_url_length, context_length);
		//System.out.println(WapPushMsg.byteToHexStr(total));
		System.arraycopy(end, 0, total, handler2_length+body_lenfth+url_length+end_url_length+context_length, end_length);
		
		System.out.println(WapPushMsg.byteToHexStr(total));
		System.out.println();
		byte[][] all = new byte[count][];
		for(int i = 0;i<count;i++){
			byte number = (byte)(i+1);
			if(i < count-1){
				all[i] = new byte[140];
				System.arraycopy(handler, 0 , all[i], 0, handler_length);
				all[i][handler_length] = (byte)count;
				all[i][handler_length+1] = number;
				System.arraycopy(total, i*128, all[i], handler_length+2, 128);
			}else{
				all[i] = new byte[end_count+handler_length+2];
				//System.out.println(end_count);
				System.arraycopy(handler, 0, all[i], 0, handler_length);
				all[i][handler_length] = (byte)count;
				all[i][handler_length+1] = number;
				System.arraycopy(total, i*128, all[i], handler_length+2, end_count);
			}
	
		}
		
		return all;
	}
	
	public static String getUrl(String url) {
		return (true == url.contains("http://") ? url.substring(7) : url);
	}
	
	public static String byteToHexStr(byte[] byteArr) {
		StringBuffer strBuf = new StringBuffer(byteArr.length * 3);

		for (int i = 0; i < byteArr.length; i++) {
			int l = byteArr[i] & 0x0F;
			int h = (byteArr[i] & 0xF0) >> 4;

			char ll = (char) (l > 9 ? 'a' + l - 10 : '0' + l);
			char hh = (char) (h > 9 ? 'a' + h - 10 : '0' + h);

			strBuf.append(hh);
			strBuf.append(ll);
			strBuf.append(" ");
			// strBuf.append(Integer.toHexString(byteArr[i] & 0xFF));
			// strBuf.append(" ");
		}

		return strBuf.toString().toUpperCase();
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String url = "http://blog.csdn.net/eason_cou/archive/2007/05/11/wap.gd.monternet.com/?userType=B&serviceID=04020028";
		String context = "这是一条短信息";
		byte[][] b = WapPushMsg.getWapPushBytes(url, context);
		for(int i=0;i<b.length;i++){
			System.out.println(WapPushMsg.byteToHexStr(b[i]));
			System.out.println("-----------------------------------------------");
		}
		//System.out.println(WapPushMsg.byteToHexStr(url.getBytes("utf-8")));
	}
	
}
