package cn.infogiga.cmpp2.util;


/**
 * 将SpWapPushMsg封装成一个SINGLETON模式的工具类。
 * 
 * @version 1.0
 * @author xmy
 * 
 */
public class SpWapPushMsgTool {
	private static SpWapPushMsg instance;

	/**
	 * 获取submit短消息的Msg_Content部分内容
	 * 
	 * @param url
	 *            URL地址
	 * @param text
	 *            文本字符串
	 * @return 分包后的WAP PUSH消息的Msg_Content.</br> 其返回为一个二维数组，如下:</br> 返回结果[0] =
	 *         第一条拆分的短信</br> 返回结果[1] = 第二条拆分的短信</br> ...</br> 返回结果[n] =
	 *         第n+1条拆分的短信</br>
	 */
	public static byte[][] getMsgContent(String url, String text) {
		if (null == instance) {
			instance = new SpWapPushMsg(url, text);
		} else {
			instance.setText(text);
			instance.setUrl(url);
		}
		return instance.getMsgContent();
	}
	
	public static void main(String[] args) {
		String url = "http://www.baidu.com";
		String context = "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊";
		byte[][] b = SpWapPushMsgTool.getMsgContent(url, context);
		 int count = b.length;
		  System.out.println("一共有"+count+"条信息");
		  for(int i=0;i<count;i++){
			  System.out.println("这是第"+(i+1)+"信息");
			  byte[] c = b[i];
			  int l = c.length;
			  System.out.println("这条信息的长度是："+l);
			  System.out.println("内容是："+SpWapPushMsg.ByteToHexStr(c));
		  }
	}
}

