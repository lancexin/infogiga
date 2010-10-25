package cn.infogiga.cmpp2.util;

import java.io.UnsupportedEncodingException;

/**
 * CMPP通过短信网关上发WAP PUSH短信的Msg_Content部分的内容。 </br> 请设置submit短信中以下位： TP_pID = 0
 * TP_udhi = 1 Msg_Fmt = 0x04
 * 
 * @version 1.0
 * @author xmy
 * 
 */
public class SpWapPushMsg {
	/*
	 * 0x0B WAP PUSH头部的总长度 0x05, 0x04, 0x0B, (byte) 0x84, 0x23, (byte) 0xF0
	 * 接下来的内容是一个wap push 0x00 表示是Concatenated Short Messages 0x03 长度 0x03
	 * reference number 参考gsm 9.2.3.6
	 */
	private static byte[] WAP_PUSH_HEADER1 = new byte[] { 0x0B, 0x05, 0x04,
			0x0B, (byte) 0x84, 0x23, (byte) 0xF0, 0x00, 0x03, 0x03 };

	/*
	 * msgCount 拆分后的短消息条数，默认只有一条短信 msgOrder 拆分后的短消息序号，默认短信编号为第一条
	 */
	private byte msgCount = 0x01;
	private byte msgOrder = 0x01;

	/* WSP */
	private static byte[] WAP_PUSH_HEADER2 = new byte[] { 0x29, 0x06, 0x06,
			0x03, (byte) 0xAE, (byte) 0x81, (byte) 0xEA, (byte) 0x8D,
			(byte) 0xCA };

	/*
	 * 0x02 标记位 0x05 -//WAPFORUM//DTD SI 1.0//EN 0x6A URL和TEXT编码方式UTF-8 0x00
	 * 标记开始 0x45 <si> 0xC6 <indication 0x0C href="http:// 0x03 字符串开始
	 */
	private static byte[] WAP_PUSH_INDICATOR = new byte[] { 0x02, 0x05, 0x6A,
			0x00, 0x45, (byte) 0xC6, 0x0C, 0x03 };

	/*
	 * 0x00 URL 字符串结束 0x01 '>' 0x03 内容描述字符串开始
	 */
	private static byte[] WAP_Push_TEXT_HEADER = new byte[] { 0x00, 0x01, 0x03 };

	/*
	 * 0x00 内容描述字符串结束 0x01 </si> 0x01 </indication>
	 */
	private static byte[] END_Of_WAP_PUSH = new byte[] { 0x00, 0x01, 0x01 };

	/* 当url和content空白时候的Msg_Content长度 */
	private static int BASIC_LENGTH = WAP_PUSH_HEADER1.length + 2
			+ WAP_PUSH_HEADER2.length + WAP_PUSH_INDICATOR.length
			+ WAP_Push_TEXT_HEADER.length + END_Of_WAP_PUSH.length;

	/*
	 * Msg_Fmt == 0 ==> MAX_MSG_LENGTH = 160 Msg_Fmt != 0 ==> MAX_MSG_LENGTH =
	 * 140
	 */
	private static int MAX_MSG_LENGTH = 140;

	/* 去掉 "http://" 的URL地址 */
	private String url = null;
	/* 文本消息内容 */
	private String text = null;

	public SpWapPushMsg(String url, String text) {
		this.url = (true == url.contains("http://") ? url.substring(7) : url);
		this.text = text;
	}

	public SpWapPushMsg() {
	};

	/**
	 * 获取Msg_Content内容
	 * 
	 * @return 拆分后的字节数组
	 */
	public byte[][] getMsgContent() {
		byte[] url_ = UTF8Encoding(url);
		byte[] text_ = UTF8Encoding(text);

		/* 计算拆分后的短信条数 */
		msgCount += (url_.length + text_.length)
				/ (MAX_MSG_LENGTH - BASIC_LENGTH);
		System.out.println("msgCount:"+msgCount);
		System.out.println(MAX_MSG_LENGTH);
		System.out.println(BASIC_LENGTH);

		/*
		 * 结果数组
		 * 
		 * 第一维为拆分后的第一条短信编号，第二维为拆分后的第二条短信内容，依次类推，即： msgCountent[0] = 第一条拆分的短信
		 * msgCountent[1] = 第二条拆分的短信 ... msgCountent[n] = 第n+1条拆分的短信
		 */
		byte[] msgContent[] = new byte[msgCount][];

		msgContent = combinMsgContent(msgContent, url_, text_);
		realese();
		return msgContent;
	}

	/**
	 * 组装WAP PUSH短信的msg_content字段
	 * 
	 * @param msgContent
	 * @return
	 */
	private byte[][] combinMsgContent(byte[][] msgContent, byte[] url,
			byte[] text) {
		// /* 以URL和TEXT填充消息体后的余数,两部分余数在整数填充完后将额外填充成一条短信 */
		// int url_remainder = url.length % (MAX_MSG_LENGTH - BASIC_LENGTH);
		// int text_remainder = text.length % (MAX_MSG_LENGTH - BASIC_LENGTH);
		//           
		// /* 单以URL部分和TEXT部分分别填充短信将会填充的数量 */
		// int url_msg_count = url.length / (MAX_MSG_LENGTH - BASIC_LENGTH);
		// int text_msg_count = text.length / (MAX_MSG_LENGTH - BASIC_LENGTH);
		//           
		// boolean flag = true;
		// for ( ; msgOrder <= msgCount ; msgOrder++) {
		// /* 填充url部分*/
		// for (int pos = 0 ; msgOrder <= url_msg_count ; msgOrder++,pos++) {
		// msgContent[msgOrder - 1] = buildMsgContent(url
		// , pos * (MAX_MSG_LENGTH - BASIC_LENGTH)
		// , (pos + 1) * (MAX_MSG_LENGTH - BASIC_LENGTH)
		// , text,0,0);
		// }
		//               
		// /* 填充URL的余数部分和TEXT的开始一段 */
		// if (flag) {
		// msgContent[msgOrder - 1] = buildMsgContent(url
		// , url.length - url_remainder
		// , url.length
		// , text,0,text_remainder);
		// flag = false;
		// continue; //填充完该条短信后面的代码暂时中止运行
		// }
		//               
		//               
		// /* 填充余下的TEXT部分 */
		// for (int pos = 0 ;msgOrder - url_msg_count - 1 <= text_msg_count;
		// msgOrder++,pos++) {
		// msgContent[msgOrder - 1] = buildMsgContent(url ,0 ,0
		// , text
		// , text_remainder + pos * (MAX_MSG_LENGTH - BASIC_LENGTH)
		// , text_remainder + (pos + 1) * (MAX_MSG_LENGTH - BASIC_LENGTH));
		// }
		//               
		// }

		// 首先对url和text合并成一个新数组，数组长度为两者之和，然后对合并后的数组填充到bytes数组中
		int len = url.length + text.length;
		byte[] bytes = new byte[len];

		System.arraycopy(url, 0, bytes, 0, url.length);
		System.arraycopy(text, 0, bytes, url.length, text.length);

		// 计算完整填充的短信条数以及填充后的余数部分
		int msgs = bytes.length / (MAX_MSG_LENGTH - BASIC_LENGTH);
		int remainder = bytes.length % (MAX_MSG_LENGTH - BASIC_LENGTH);
		System.out.println("msgs:"+msgs);
		System.out.println("remainder:"+remainder);
		// 对能填充成为整数部分的短信内容填充
		for (int i = 0; msgOrder <= msgs; msgOrder++, i++) {
			msgContent[msgOrder - 1] = buildMsgContent(bytes, i
					* (MAX_MSG_LENGTH - BASIC_LENGTH), (i + 1)
					* (MAX_MSG_LENGTH - BASIC_LENGTH), bytes, 0, 0);
		}

		// 对余数部分内容填充
		msgContent[msgOrder - 1] = buildMsgContent(bytes, bytes.length
				- remainder, bytes.length, bytes, 0, 0);

		int url_remainder = url.length % (MAX_MSG_LENGTH - BASIC_LENGTH);

		int text_remainder = text.length % (MAX_MSG_LENGTH - BASIC_LENGTH);

		/* 单以URL部分和TEXT部分分别填充短信将会填充的数量 
		int url_msg_count = url.length / (MAX_MSG_LENGTH - BASIC_LENGTH);
		int text_msg_count = text.length / (MAX_MSG_LENGTH - BASIC_LENGTH);
		System.out.println(url_msg_count);
		System.out.println(text_msg_count);
		boolean flag = true;
		for (; msgOrder <= msgCount; msgOrder++) {
			 填充url部分 
			for (int pos = 0; msgOrder <= url_msg_count; msgOrder++, pos++) {
				msgContent[msgOrder - 1] = buildMsgContent(url, pos
						* (MAX_MSG_LENGTH - BASIC_LENGTH), (pos + 1)
						* (MAX_MSG_LENGTH - BASIC_LENGTH), text, 0, 0);
			}

			 填充URL的余数部分和TEXT的开始一段 
			if (flag) {
				msgContent[msgOrder - 1] = buildMsgContent(url, url.length
						- url_remainder, url.length, text, 0, text_remainder);
				flag = false;
				continue; // 填充完该条短信后面的代码暂时中止运行
			}

			 填充余下的TEXT部分 
			for (int pos = 0; msgOrder - url_msg_count - 1 <= text_msg_count; msgOrder++, pos++) {
				msgContent[msgOrder - 1] = buildMsgContent(url, 0, 0, text,
						text_remainder + pos * (MAX_MSG_LENGTH - BASIC_LENGTH),
						text_remainder + (pos + 1)
								* (MAX_MSG_LENGTH - BASIC_LENGTH));
			}

		}*/

		return msgContent;
	}

	/**
	 * 构建拆分后的Msg_Content骨架，URL和TEXT指定的字节开始和结束添加到到骨架中
	 * 
	 * @param url
	 *            URL
	 * @param urlBegin
	 *            URL开始字节下标
	 * @param urlEnd
	 *            URL结束字节下标
	 * @param text
	 *            文本说明字符串
	 * @param textBegin
	 *            文本开始字节下标
	 * @param TextEnd
	 *            文本结束字节下标
	 * @return 一条拆分好的短消息
	 */
	private byte[] buildMsgContent(byte[] url, int urlBegin, int urlEnd,
			byte[] text, int textBegin, int textEnd) {
		// System.out.println("\nBASIC_LENGTH : " + BASIC_LENGTH);
		// System.out.println("URL_LENGTH : " + (urlEnd - urlBegin));
		// System.out.println("TEXT_LENGTH : " + (textEnd - textBegin));

		int size = BASIC_LENGTH + (urlEnd - urlBegin) + (textEnd - textBegin);
		byte[] res = new byte[size];

		System.arraycopy(WAP_PUSH_HEADER1, 0, res, 0, WAP_PUSH_HEADER1.length);
		System.arraycopy(new byte[] { msgCount, msgOrder }, 0, res,
				WAP_PUSH_HEADER1.length, 2);

		System.arraycopy(WAP_PUSH_HEADER2, 0, res, WAP_PUSH_HEADER1.length + 2,
				WAP_PUSH_HEADER2.length);

		System.arraycopy(WAP_PUSH_INDICATOR, 0, res, WAP_PUSH_HEADER1.length
				+ 2 + WAP_PUSH_HEADER2.length, WAP_PUSH_INDICATOR.length);

		System.arraycopy(url, urlBegin, res, WAP_PUSH_HEADER1.length + 2
				+ WAP_PUSH_HEADER2.length + WAP_PUSH_INDICATOR.length, urlEnd
				- urlBegin);

		System.arraycopy(WAP_Push_TEXT_HEADER, 0, res, WAP_PUSH_HEADER1.length
				+ 2 + WAP_PUSH_HEADER2.length + WAP_PUSH_INDICATOR.length
				+ (urlEnd - urlBegin), WAP_Push_TEXT_HEADER.length);

		System.arraycopy(text, textBegin, res, WAP_PUSH_HEADER1.length + 2
				+ WAP_PUSH_HEADER2.length + WAP_PUSH_INDICATOR.length
				+ (urlEnd - urlBegin) + WAP_Push_TEXT_HEADER.length, textEnd
				- textBegin);

		System.arraycopy(END_Of_WAP_PUSH, 0, res, WAP_PUSH_HEADER1.length + 2
				+ WAP_PUSH_HEADER2.length + WAP_PUSH_INDICATOR.length
				+ (urlEnd - urlBegin) + WAP_Push_TEXT_HEADER.length
				+ (textEnd - textBegin), END_Of_WAP_PUSH.length);

		return res;
	}

	/**
	 * 将内容UTF8编码为字节数组
	 * 
	 * @param s
	 *            待编码字符
	 * @return 字节数组
	 */
	private byte[] UTF8Encoding(String s) {
		if (null != s) {
			try {
				return s.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * byte数组转化为hex字符串，终端打印调试用
	 * 
	 * @return
	 */
	public static String ByteToHexStr(byte[] byteArr) {
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

	private void realese() {
		msgCount = 0x01;
		msgOrder = 0x01;
	}

	public void setUrl(String url) {
		this.url = (true == url.contains("http://") ? url.substring(7) : url);
	}

	public void setText(String text) {
		this.text = text;
	}

}
