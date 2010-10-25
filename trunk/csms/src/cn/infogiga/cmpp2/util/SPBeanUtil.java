package cn.infogiga.cmpp2.util;

import cn.infogiga.cmpp2.msg.MsgCommand;
import cn.infogiga.cmpp2.msg.MsgConnect;
import cn.infogiga.cmpp2.msg.MsgDeliverResp;
import cn.infogiga.cmpp2.msg.MsgSubmit;
import cn.infogiga.cmpp2.msg.MsgSubmitResp;


public class SPBeanUtil {
	
	Config config;
	
	public SPBeanUtil(Config config){
		this.config = config;
	}
	
	public MsgConnect getMsgConnect(){
		MsgConnect msg = new MsgConnect();
		
		msg.Total_Length = 12 + 6 + 16 + 1 + 4;
		msg.Command_Id = MsgCommand.CMPP_CONNECT;
		msg.Sequence_Id = Seq.getSeq();
		
		msg.Source_Addr = config.getSp_id();
		msg.AuthenticatorSource = getLoginMd5(config.getSp_id(), config.getSp_pwd());
		msg.Version = (byte)0;
		msg.Timestamp = Integer.parseInt(getMMDDHHMMSS());
		return msg;
	}
	/*Total_Length: <169>, Command_Id: <CMPP_SUBMIT(0x00000004)>, Sequence_Id: <1>.
	Msg_Id:			<0>
	Pk_total:		<1>
	Pk_number:		<1>
	Registered_Deliverey:	<1>
	Msg_Level:		<0>
	Service_Id:		<M-STORE>
	Fee_UserType:		<0>
	Fee_terminal_Id:	<>
	TP_pid:			<0>
	TP_udhi:		<0>
	Msg_Fmt:		<15>
	Msg_Src:		<411505>
	FeeType:		<02>
	FeeCode:		<000000>
	Valid_Time:		<>
	At_Time:		<>
	Src_Id:			<106583026>
	DestUsr_tl:		<1>
	Dest_terminal_Id:	<15268114857 >
	Msg_Length:		<10>
	Msg_Content:		<测试MT消息>*/
	public MsgSubmit getMsgSubmit(){
		MsgSubmit msg = new MsgSubmit();
		//msg.Total_Length = 12 + 6 + 16 + 1 + 4;
		msg.Command_Id = MsgCommand.CMPP_SUBMIT;
		msg.Sequence_Id = Seq.getSeq();
		
		msg.Msg_Id = (byte)0;
		msg.Pk_total = (byte)1	;//	1	Unsigned Integer
		msg.Pk_number = (byte)1	;//	1	Unsigned Integer
		msg.Registered_Delivery = (byte)0;//	1	Unsigned Integer
		msg.Msg_level = (byte)0;//	1	Unsigned Integer
		msg.Service_Id =config.getService_id();//	10	Octet String
		msg.Fee_UserType = (byte)0;//	1	Unsigned Integer
		//msg.Fee_terminal_Id	;//	21	Unsigned Integer
		msg.TP_pId = (byte)0;//	1	Unsigned Integer
		msg.TP_udhi = (byte)0;//	1	Unsigned Integer
		msg.Msg_Fmt	= (byte)15;//	1	Unsigned Integer
		msg.Msg_src	=config.getSp_id();//	6	Octet String
		msg.FeeType	="01";//	2	Octet String
		//msg.FeeCode	="";//	6	Octet String
		//msg.ValId_Time	;//	17	Octet String
		//msg.At_Time	;//	17	Octet String
		msg.Src_Id = config.getServer_code();//	21	Octet String
		msg.DestUsr_tl = (byte)1;//	1	Unsigned Integer
		//msg.Dest_terminal_Id	;//	21*DestUsr_tl	Octet String
		//msg.Msg_Length		;//1	Unsigned Integer
		//msg.Msg_Content	;//	Octet String
		//msg.Reserve		;//8	Octet String
		return msg;
	}
	
	public byte[] getMsgSubmitBytes(MsgSubmit msg){
		int length = msg.Total_Length;
		System.out.println("length:"+length);
		byte[] bytes = new byte[length];
		
		byte[] total_length = TypeConvert.int2byte(msg.Total_Length);
		System.arraycopy(total_length,0,bytes,0,4);//0-3
		
		byte[] command_id = TypeConvert.int2byte(msg.Command_Id);
		System.arraycopy(command_id,0,bytes,4,4);//4-7
		
		byte[] sequence_id = TypeConvert.int2byte(msg.Sequence_Id);
		System.arraycopy(sequence_id,0,bytes,8,4);//8-11
		
		byte[] Msg_Id = TypeConvert.long2byte(msg.Msg_Id);//8	Unsigned Integer
		System.arraycopy(Msg_Id,0,bytes,12,8);//12-19
		
		//byte Pk_total	;//	1	Unsigned Integer
		bytes[20] = msg.Pk_total;//20
		
		//Pk_number	;//	1	Unsigned Integer
		bytes[21] = msg.Pk_number;//21
		
		//Registered_Delivery	;//	1	Unsigned Integer
		bytes[22] = msg.Registered_Delivery;//22
		
		//Msg_level	;//	1	Unsigned Integer
		bytes[23] = msg.Msg_level;//23
		
		byte[] Service_Id = msg.Service_Id.getBytes();//	10	Octet String
		System.arraycopy(Service_Id,0,bytes,24,Service_Id.length);//24-33
		
		//Fee_UserType	;//	1	Unsigned Integer
		bytes[34] = msg.Fee_UserType;//34
		
		if(msg.Fee_terminal_Id != null){
			byte[] Fee_terminal_Id = msg.Fee_terminal_Id.getBytes();//	21	Unsigned Integer
			System.arraycopy(Fee_terminal_Id,0,bytes,35,Fee_terminal_Id.length);//35-55
		}
		
		//TP_pId	;//	1	Unsigned Integer
		bytes[56] = msg.TP_pId;
		
		//TP_udhi	;//	1	Unsigned Integer
		bytes[57] = msg.TP_udhi;
		
		//Msg_Fmt	;//	1	Unsigned Integer
		bytes[58] = msg.Msg_Fmt;
		
		byte[] Msg_src = msg.Msg_src.getBytes();//	6	Octet String
		System.arraycopy(Msg_src,0,bytes,59,6);//59-64
		
		byte[] FeeType = msg.FeeType.getBytes();//	2	Octet String
		System.arraycopy(FeeType,0,bytes,65,2);//65-66
		
		if(msg.FeeCode != null){
			byte[] FeeCode = msg.FeeCode.getBytes();//	6	Octet String
			System.arraycopy(FeeCode,0,bytes,67,FeeCode.length);//67-72
		}
		
		if(msg.ValId_Time != null){
			byte[] ValId_Time = msg.ValId_Time.getBytes();//	17	Octet String
			System.arraycopy(ValId_Time,0,bytes,73,17);//73-89
		}
		
		if(msg.At_Time != null){
			byte[] At_Time = msg.At_Time.getBytes()	;//	17	Octet String
			System.arraycopy(At_Time,0,bytes,90,17);//90-106
		}
		
		byte[] Src_Id = msg.Src_Id.getBytes()	;//	21	Octet String
		System.arraycopy(Src_Id,0,bytes,107,Src_Id.length);//107-127
		
		//DestUsr_tl	;//	1	Unsigned Integer
		bytes[128] = msg.DestUsr_tl;//139
		
		byte[] Dest_terminal_Id	= msg.Dest_terminal_Id.getBytes();//	21*DestUsr_tl	Octet String
		System.arraycopy(Dest_terminal_Id,0,bytes,129,Dest_terminal_Id.length);//129-149
		
		
		//Msg_Length		;//1	Unsigned Integer
		bytes[150] = msg.Msg_Length;//150
		
		//Msg_Content	;//	Octet String
		System.arraycopy(msg.Msg_Content,0,bytes,151,msg.Msg_Content.length);
		
		
		//byte[] Reserve = msg.Reserve.getBytes();//8	Octet String
		//Reserve略
		return bytes;
		
	}
	
	public byte[] getMsgConnectBytes(){
		
		MsgConnect msg = getMsgConnect();
		byte[] bytes = new byte[msg.Total_Length];
		
		byte[] total_length = TypeConvert.int2byte(msg.Total_Length);
		byte[] command_id = TypeConvert.int2byte(msg.Command_Id);
		byte[] sequence_id = TypeConvert.int2byte(msg.Sequence_Id);
		byte[] timestamp = TypeConvert.int2byte(msg.Timestamp);
		byte[] source_addr = msg.Source_Addr.getBytes();
		System.arraycopy(total_length,0,bytes,0,4);//0-3
		System.arraycopy(command_id,0,bytes,4,4);//4-7
		System.arraycopy(sequence_id,0,bytes,8,4);//8-11
		System.arraycopy(source_addr,0,bytes,12,6);//12-17
		System.arraycopy(msg.AuthenticatorSource,0,bytes,18,16);//18-33
		bytes[34] = msg.Version;
		System.arraycopy(timestamp,0,bytes,35,4);//38
		
		System.out.println("Total_Length:"+msg.Total_Length+" "+Debug.bytesToHexStr(total_length));
		System.out.println("Command_Id:"+msg.Command_Id+" "+Debug.bytesToHexStr(command_id));
		System.out.println("Sequence_Id:"+msg.Sequence_Id+" "+Debug.bytesToHexStr(sequence_id));
		
		return bytes;
	}
	
	public static byte[] getActiveMsgBytes(){
		byte[] total_length = TypeConvert.int2byte(12);
		byte[] comment_id = TypeConvert.int2byte(MsgCommand.CMPP_ACTIVE_TEST);
		byte[] sequence_id = TypeConvert.int2byte(Seq.getSeq());
		byte[] bytes = new byte[12];
		System.arraycopy(total_length,0,bytes,0,4);//0-3
		System.arraycopy(comment_id,0,bytes,4,4);//4-7
		System.arraycopy(sequence_id,0,bytes,8,4);//8-11
		return bytes;
	}
	
	public static byte[] getDeliverRespBytes(MsgDeliverResp msgDeliverResp){
		int length = msgDeliverResp.Total_Length;
		byte[] bytes = new byte[length];
		byte[] total_length = TypeConvert.int2byte(length);
		
		byte[] command_id = TypeConvert.int2byte(msgDeliverResp.Command_Id);
		
		byte[] sequence_id = TypeConvert.int2byte(msgDeliverResp.Sequence_Id);
		
		byte[] msg_id = TypeConvert.long2byte(msgDeliverResp.Msg_Id);
		
		byte result = msgDeliverResp.Result;
		
		System.arraycopy(total_length,0,bytes,0,4);//0-3
		System.arraycopy(command_id,0,bytes,4,4);//4-7
		System.arraycopy(sequence_id,0,bytes,8,4);//8-11
		
		System.arraycopy(msg_id,0,bytes,12,8);//12-19
		bytes[20] = result;
		return bytes;
	}
	
	public static MsgSubmitResp byteToMsgSubmitResp(byte[] b){
		MsgSubmitResp msgSubmitResp = new MsgSubmitResp();
		
		int length = b.length;
		//byte[] totle_length = new byte[4];
		//byte[] commend_id = new byte[4];
		//byte[] sequence_id = new byte[4];
		byte[] msg_id = new byte[8];
		//byte reault = new byte[4];
		//System.arraycopy(b,0,totle_length,0,4);//0-3
		//System.arraycopy(b,0,commend_id,0,4);//0-3
		//System.arraycopy(b,4,sequence_id,0,4);//4-7
		System.arraycopy(b,0,msg_id,0,8);//0-7
		//System.arraycopy(b,8,reault,0,4);//8-12
		
		//msgSubmitResp.setTotal_Length(TypeConvert.byte2int(totle_length));
		//msgSubmitResp.setCommand_Id(TypeConvert.byte2int(commend_id));
		//msgSubmitResp.setSequence_Id(TypeConvert.byte2int(sequence_id));
		msgSubmitResp.Msg_Id = TypeConvert.byte2long(msg_id);
		msgSubmitResp.Result = b[8];
		
		
		
		return msgSubmitResp;
	}
	
	
	public static byte[] getLoginMd5(String spid,String pwd){
		try{
		java.security.MessageDigest md5=  java.security.MessageDigest.getInstance("MD5");
		//MD5（Source_Addr+9 字节的0 +shared secret+timestamp）
		//timestamp格式为：MMDDHHMMSS，即月日时分秒
		String timeStr=getMMDDHHMMSS();
		String s=spid+"\0\0\0\0\0\0\0\0\0"+pwd+timeStr;
		byte[] data=s.getBytes();
		byte[] result=md5.digest(data);
		return result;
		}catch(Exception ef){
			ef.printStackTrace();
		}
		return null;
	}
	
	public static String getMMDDHHMMSS(){
		java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("MMddhhmmss");
		java.util.Date da=new java.util.Date();
		String timeStr=sdf.format(da);
		return timeStr;
	}
}
