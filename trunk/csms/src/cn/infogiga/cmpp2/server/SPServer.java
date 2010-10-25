package cn.infogiga.cmpp2.server;



import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.infogiga.cmpp2.msg.MsgCommand;
import cn.infogiga.cmpp2.msg.MsgDeliverResp;
import cn.infogiga.cmpp2.msg.MsgSubmit;
import cn.infogiga.cmpp2.msg.MsgSubmitResp;
import cn.infogiga.cmpp2.util.CmppLongMessage;
import cn.infogiga.cmpp2.util.Config;
import cn.infogiga.cmpp2.util.Debug;
import cn.infogiga.cmpp2.util.SPBeanUtil;
import cn.infogiga.cmpp2.util.SpWapPushMsgTool;
import cn.infogiga.cmpp2.util.WapPushMsg;



public class SPServer{
	private static final Log log = LogFactory.getLog(SPServer.class);
	
	private static boolean runningStates = false;
	
	private static SPServer server = null;
	
	Timer keepConnect = new Timer();
	
	Thread sendSubmitThread = null;
	
	Thread sendDeliverRespThread = null;
	
	Thread receiveThread = null;
	
	Socket socket = null;
	
	DataInputStream input = null;
	
	DataOutputStream output = null;
	
	Config properties = null;
	
	SPBeanUtil spBeanUtil;
	
	private static List<MsgSubmit> submitList = new ArrayList<MsgSubmit>();
	
	private static List<MsgDeliverResp> deliverRespList = new ArrayList<MsgDeliverResp>();

	private static Map<Integer,MsgSubmit> hasSubmitList = new HashMap<Integer,MsgSubmit>();
	
	public SPServer(){
		properties = new Config();
		spBeanUtil = new SPBeanUtil(properties);
		
		sendSubmitThread = new Thread(sendSubmitRunnable);
		sendDeliverRespThread = new Thread(sendDeliverRespRunnable);
		receiveThread = new Thread(receiveRunnable);
		
		sendSubmitThread.start();//启动发送Submit线程
		sendDeliverRespThread.start();//启动发送DeliverResp线程
		receiveThread.start();//启动接收信息线程
		
		keepConnect.schedule(keepConnectTimerTask, 10*1000, 10*1000);//开启保持链路程序
		connect();
		login();
		
	}
	
	
	private void connect(){
		try {
			socket = new Socket(properties.getSms_host(),properties.getSms_post());
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
			socket.setSoTimeout(10*1000);
		} catch (UnknownHostException e) {
			log.error("UnknownHostException:开启socket出现错误", e);
		} catch (IOException e) {
			log.error("IOException:开启socket出现错误", e);
		}
	}
	
	private void shutdown(){
		try {
			runningStates = false;
			socket.close();
			input.close();
			output.close();
			socket = null;
			input = null;
			output = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static SPServer getSPServer(){
		synchronized (SPServer.class){  
               if(server == null){  
            	   server = new SPServer();
               }  
         } 
		return server;
	}
	
	public void sendMessage(String phoneNumber,String context){
		boolean bool = false;
		byte[] b = context.getBytes();
		int length = b.length;
		if(length < 140){//表示它是条短短信
			//System.out.println("开始发送长短信");
			long time = new Date().getTime();
			  int total_length = 4+4+4+8+1+1+1+1+10+1+21+1+1+1+6+2+6+17+17+21+1+21+1+length+8;
			//int total_length = 4+4+4+8+1+1+1+1+10+1+32+1+1+1+1+6+2+6+17+17+21+1+32+1+1+length+20;
			//System.out.println("total_length:"+total_length);
			MsgSubmit msgSubmit = spBeanUtil.getMsgSubmit();
			msgSubmit.Total_Length = total_length;
			//msgSubmit.Msg_Id = 0;

			msgSubmit.Msg_Length = (byte)length;
			msgSubmit.Msg_Content = b;
			msgSubmit.Dest_terminal_Id = phoneNumber;
			submitList.add(msgSubmit);
			
		}else{//表示它是条长短信
			//System.out.println("开始发送长短信");
			byte[][] contexts = CmppLongMessage.sendLongMessage(context);
			int count = contexts.length;
			for(int i=0;i<count;i++){
			//	long time = new Date().getTime();
				MsgSubmit msgSubmit = spBeanUtil.getMsgSubmit();
				int l = contexts[i].length;
				int total_length = 4+4+4+8+1+1+1+1+10+1+21+1+1+1+6+2+6+17+17+21+1+21+1+l+8;
				//int total_length = 4+4+4+8+1+1+1+1+10+1+32+1+1+1+1+6+2+6+17+17+21+1+32+1+1+l+20;
				msgSubmit.Total_Length = total_length;
				//System.out.println(l);
			//	msgSubmit.Msg_Id = time;
				msgSubmit.Pk_total = (byte)count;
				msgSubmit.Pk_number = (byte)(i+1);
				msgSubmit.TP_pId = (byte)0;
				msgSubmit.TP_udhi = (byte)1;
				msgSubmit.Msg_Fmt = (byte)8;
				msgSubmit.Msg_Length = (byte)l;
				msgSubmit.Msg_Content = contexts[i];
				msgSubmit.Dest_terminal_Id = phoneNumber;
				submitList.add(msgSubmit);
			}
		}
	}
	
	public void sendMessageByWapPush(String phoneNumber,String url,String context) throws UnsupportedEncodingException{
		//byte[][] contexts = SpWapPushMsgTool.getMsgContent(url,context);
		byte[][] contexts = WapPushMsg.getWapPushBytes(url,context);
		int count = contexts.length;
		for(int i=0;i<count;i++){
			long time = new Date().getTime();
			MsgSubmit msgSubmit = spBeanUtil.getMsgSubmit();
			int l = contexts[i].length;
			int total_length = 4+4+4+8+1+1+1+1+10+1+21+1+1+1+6+2+6+17+17+21+1+21+1+l+8;
			//int total_length = 4+4+4+8+1+1+1+1+10+1+32+1+1+1+1+6+2+6+17+17+21+1+32+1+1+l+20;
			msgSubmit.Total_Length = total_length;
			//System.out.println(l);
			//	msgSubmit.Msg_Id = time;
			msgSubmit.Pk_total = (byte)count;
			msgSubmit.Pk_number = (byte)(i+1);
			msgSubmit.TP_pId = (byte)0;
			msgSubmit.TP_udhi = (byte)1;
			msgSubmit.Msg_Fmt = (byte)4;
			msgSubmit.Msg_Length = (byte)l;
			msgSubmit.Msg_Content = contexts[i];
			msgSubmit.Dest_terminal_Id = phoneNumber;
	
			submitList.add(msgSubmit);
		}
	}
	
	Runnable sendDeliverRespRunnable = new Runnable(){
		public void run() {
			while (runningStates) {
				try {
					Thread.sleep(3*1000);//默认这个线程每3秒钟执行一次
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (output) {
					int size = deliverRespList.size();
					for(int i=size-1;i>=0;i--){
						MsgDeliverResp msgDeliverResp = deliverRespList.get(i);
						byte[] b = spBeanUtil.getDeliverRespBytes(msgDeliverResp);
					//	Debug.printBytes(b, "发送一条MsgDeliverResp信息");
						try {
							output.write(b);
							output.flush();
							deliverRespList.remove(msgDeliverResp);
						} catch (IOException e) {
							runningStates = false;
							e.printStackTrace();
						}
					}
				}
				try {
					Thread.sleep(1*1000);//默认这个线程每1秒执行一次
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};
	
	Runnable sendSubmitRunnable = new Runnable(){
		public void run() {
			while(true){
				if(runningStates){
					synchronized (output) {
						int size = submitList.size();
						for(int i=size-1;i>=0;i--){//使用倒序循环可以有效的不考虑数组又与添加的情况
							MsgSubmit msgSubmit = submitList.get(i);
							//Debug.printMsgSubmit(msgSubmit);
							byte[] b = spBeanUtil.getMsgSubmitBytes(msgSubmit);
							System.out.println("发送一条MsgSubmit信息");
							System.out.println("Total_Length:"+msgSubmit.Total_Length);
							System.out.println("Command_Id:"+msgSubmit.Command_Id);
							System.out.println("Sequence_Id:"+msgSubmit.Sequence_Id);
							System.out.println("Msg_Id:"+msgSubmit.Msg_Id);
							System.out.println("Pk_total:"+msgSubmit.Pk_total);
							System.out.println("Pk_number:"+msgSubmit.Pk_number);
							System.out.println("Registered_Delivery:"+msgSubmit.Registered_Delivery);
							System.out.println("Msg_level:"+msgSubmit.Msg_level);
							System.out.println("Service_Id:"+msgSubmit.Service_Id);
							System.out.println("Fee_UserType:"+msgSubmit.Fee_UserType);
							System.out.println("Fee_terminal_Id:"+msgSubmit.Fee_terminal_Id);
							System.out.println("TP_pId:"+msgSubmit.TP_pId);
							System.out.println("TP_udhi:"+msgSubmit.TP_udhi);
							System.out.println("Msg_Fmt:"+msgSubmit.Msg_Fmt);
							System.out.println("Msg_src:"+msgSubmit.Msg_src);
							System.out.println("FeeType:"+msgSubmit.FeeType);
							System.out.println("FeeCode:"+msgSubmit.FeeCode);
							System.out.println("ValId_Time:"+msgSubmit.ValId_Time);
							System.out.println("At_Time:"+msgSubmit.At_Time);
							System.out.println("Src_Id:"+msgSubmit.Src_Id);
							System.out.println("DestUsr_tl:"+msgSubmit.DestUsr_tl);
							System.out.println("Dest_terminal_Id:"+msgSubmit.Dest_terminal_Id);
							System.out.println("Msg_Length:"+(msgSubmit.Msg_Length & 0xFF));
							System.out.println("Msg_Content:"+Debug.bytesToHexStr(msgSubmit.Msg_Content));
							System.out.println("Reserve:"+msgSubmit.Reserve);
							try {
								output.write(b);
								output.flush();
								hasSubmitList.put(msgSubmit.Sequence_Id, msgSubmit);
								submitList.remove(msgSubmit);
							} catch (IOException e) {
								runningStates = false;
								e.printStackTrace();
							}
						}
					}
				}
				
				
				try {
					Thread.sleep(1*1000);//默认这个线程每1秒执行一次
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};
	
	Runnable receiveRunnable = new Runnable(){
		public void run() {
			while(true){
				try {
					Thread.sleep(1*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(runningStates){
					synchronized (input) {
						try {
							int avalibable = input.available();
							if(avalibable <= 0){
								continue;
							}
							byte[] all = new byte[avalibable];
							//将所有流全部缓存
							input.readFully(all);
							//将所有缓存的流输出以便解析
							ByteArrayInputStream bins=new ByteArrayInputStream(all);
							DataInputStream dins=new DataInputStream(bins);
							while(dins.available() > 0){
								int length = dins.readInt();
								int commant_id = dins.readInt();
								int sequence_Id = dins.readInt();
								if(commant_id == MsgCommand.CMPP_SUBMIT_RESP){
									System.out.println("获得一个短信提交回复...");
									byte[] b = new byte[length-12];
									dins.readFully(b);
									MsgSubmitResp msgSubmitResp = spBeanUtil.byteToMsgSubmitResp(b);
									System.out.println("length:"+length);
									System.out.println("commant_id:"+commant_id);
									System.out.println("sequence_Id:"+sequence_Id);
									System.out.println("msg_id:"+msgSubmitResp.Msg_Id);
									System.out.println("result:"+(msgSubmitResp.Result & 0xFF));
									System.out.println("result hex:"+Integer.toHexString(msgSubmitResp.Result & 0xFF));

									if(msgSubmitResp.Result == 0){
										//System.out.println("回复成功...");
										hasSubmitList.remove(sequence_Id);
									}else{
										MsgSubmit ms = hasSubmitList.get(sequence_Id);
										if(ms != null){
											//submitList.add(ms);
										}
									}
								}else if(commant_id == MsgCommand.CMPP_DELIVER){
									System.out.println("接收到MsgCommand.CMPP_DELIVER");
									System.out.println("sequence_Id-->"+sequence_Id);
									System.out.println("--------------------------------");
									long msg_id = dins.readLong();
									byte[] bbb = new byte[length-20];
									//input.r
									dins.readFully(bbb);
									int l = 4+4+4+8+4;
									int seq_id = sequence_Id;
									int com_id = MsgCommand.CMPP_DELIVER_RESP;
									byte result = (byte)0;
									MsgDeliverResp msgDeliverResp = new MsgDeliverResp();
									msgDeliverResp.Total_Length = l;//.setTotal_Length(l);
									msgDeliverResp.Command_Id = com_id;//.setCommand_Id(com_id);
									msgDeliverResp.Sequence_Id = seq_id;//.setSequence_Id(seq_id);
									msgDeliverResp.Result = result;//.setResult(result);
									msgDeliverResp.Msg_Id = msg_id;//.setMsg_Id(msg_id);
									deliverRespList.add(msgDeliverResp);
								}else if(commant_id == MsgCommand.CMPP_ACTIVE_TEST_RESP){
									byte[] b = new byte[length-12];
									dins.readFully(b);
									System.out.println("接受到链路维持信息"+Debug.bytesToHexStr(b));
								}else{
									System.out.println("接收到无法解析的信息");
									byte[] b = new byte[length-12];
									dins.readFully(b);
								}
							}
						} catch (IOException e) {
							runningStates = false;
							e.printStackTrace();
						}
					}
				}
			}
		}
	};
		
	//唤醒线程,保持这个连接不会断开
	TimerTask keepConnectTimerTask = new TimerTask(){
		@Override
		public void run() {
			sendKeepConnect();
		}
	};
	/**
	 * 发送链路维持信息
	 */
	private void sendKeepConnect(){
		if(runningStates){
			byte[] b = SPBeanUtil.getActiveMsgBytes();
			System.out.println("发送链路信息："+Debug.bytesToHexStr(b));
			synchronized (output) {
				try {
					output.write(b);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					shutdown();
				}
			}
		}else{
			connect();
			login();
		}
	}
	
	
	private void login(){
		System.out.println("发送连接信息.....");
		byte[] b = spBeanUtil.getMsgConnectBytes();
		
		try {
			output.write(b);
			output.flush();
			int totle_length = input.readInt();
			int commend_id = input.readInt();
			int sequence_id = input.readInt();
			byte status = input.readByte();
			byte[] authenticatorISMG = new byte[16];
			input.read(authenticatorISMG);
			byte version = input.readByte();
		//	int timestamp = input.readInt();
			
			System.out.println("totle_length:"+totle_length);
			System.out.println("commend_id:"+commend_id);
			System.out.println("sequence_id:"+sequence_id);
			System.out.println("status:"+status);
			System.out.println("authenticatorISMG:"+Debug.bytesToHexStr(authenticatorISMG));
			System.out.println("version:"+version);
			//System.out.println("timestamp:"+timestamp);
			
			if(status == 0){
				System.out.println("连接成功.....");
				runningStates = true;
			
			}else{
				System.out.println("连接失败.....");
				shutdown();
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
