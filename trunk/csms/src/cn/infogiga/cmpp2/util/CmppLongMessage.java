package cn.infogiga.cmpp2.util;

public class CmppLongMessage {
	public static void main(final String[] args) {
		byte[][] b = CmppLongMessage
				.sendLongMessage("http://www.baidu.com这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容http://www.baidu.com这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容");
		int count = b.length;
		System.out.println("一共有" + count + "条信息");
		for (int i = 0; i < count; i++) {
			System.out.println("这是第" + (i + 1) + "信息");
			byte[] c = b[i];
			int l = c.length;
			System.out.println("这条信息的长度是：" + l);
			System.out.println("内容是：" + CmppLongMessage.bytesToHexStr(c));
		}
	}

	public static byte[][] sendLongMessage(String message) {
		int retStatus = 0;
		// String
		// message="作者：eric_cheung709@hotmail.com;关于cmpp长短信发送，这是测试文本，将分条下发，在用户手机上一条全显示；如果有问题欢迎email交流联系；下面的是测试代码，可以做为参考；本实现已经通过实际测试，在NOKIA
		// 6680和LG KG90上正常显示";
		byte[][] contexts = null;

		try {
			byte[] messageUCS2;
			messageUCS2 = message.getBytes("UnicodeBigUnmarked");
			// System.out.println(message + " -(UCS2)编码: " +
			// bytesToHexStr(messageUCS2));
			int messageUCS2Len = messageUCS2.length;// 长短信长度
			System.out.println(messageUCS2Len);
			int maxMessageLen = 140;
			if (messageUCS2Len > maxMessageLen) {// 长短信发送
				int tpUdhi = 1;
				int msgFmt = 0x08;
				int messageUCS2Count = messageUCS2Len / (maxMessageLen - 6) + 1;// 长短信分为多少条发送
				contexts = new byte[messageUCS2Count][];
				byte[] tp_udhiHead = new byte[6];
				tp_udhiHead[0] = 0x05;
				tp_udhiHead[1] = 0x00;
				tp_udhiHead[2] = 0x03;
				tp_udhiHead[3] = 0x0A;
				tp_udhiHead[4] = (byte) messageUCS2Count;
				tp_udhiHead[5] = 0x01;// 默认为第一条
				for (int i = 0; i < messageUCS2Count; i++) {
					tp_udhiHead[5] = (byte) (i + 1);
					byte[] msgContent;
					if (i != messageUCS2Count - 1) {// 不为最后一条
						msgContent = CmppLongMessage.byteAdd(tp_udhiHead,
								messageUCS2, i * (maxMessageLen - 6), (i + 1)
										* (maxMessageLen - 6));
					} else {
						msgContent = CmppLongMessage.byteAdd(tp_udhiHead,
								messageUCS2, i * (maxMessageLen - 6),
								messageUCS2Len);
					}
					/*
					 * System.out.println("正在发送第"+tp_udhiHead[5]+"条长短信");
					 * System.out.println("UCS2:"+CmppLongMessage.bytesToHexStr(msgContent));
					 * System.out.println("总长度："+msgContent.length);
					 */
					contexts[i] = msgContent;
				}// for end
				return contexts;
			}else{
				contexts = new byte[1][];
				contexts[0] = messageUCS2;
			}// if end
		} catch (Exception e) {
			retStatus = -1;
			e.printStackTrace();
			return contexts;
		}
		return contexts;
	}

	/**
	 * 功能： 将字节转换为16进制码（在此只是为了调试输出，此函数没有实际意义）
	 * 
	 * @param b
	 * @return 转化后的16进制码
	 * @Author: eric(eric_cheung709@hotmail.com) created in 2007/04/28 16:33:06
	 */
	public static String bytesToHexStr(byte[] b) {
		if (b == null)
			return "";
		StringBuffer strBuffer = new StringBuffer(b.length * 3);
		for (int i = 0; i < b.length; i++) {
			strBuffer.append(Integer.toHexString(b[i] & 0xff));
			strBuffer.append(" ");
		}
		return strBuffer.toString();
	}

	/***************************************************************************
	 * * 功能： 将src里的字节与add里的从start开始到end（不包括第end个位置)的字节串连在一起返回
	 * 
	 * @param src
	 * @param add
	 * @param start
	 *            add 开始位置
	 * @param end
	 *            add 的结束位置(不包括end位置)
	 * @return 也即实现类似String类型的src+add.subString(start,end)功能
	 */
	public static byte[] byteAdd(byte[] src, byte[] add, int start, int end) {
		byte[] dst = new byte[src.length + end - start];
		for (int i = 0; i < src.length; i++) {
			dst[i] = src[i];
		}
		for (int i = 0; i < end - start; i++) {
			dst[src.length + i] = add[start + i];
		}
		return dst;
	}
}