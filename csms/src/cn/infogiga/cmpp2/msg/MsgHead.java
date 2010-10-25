package cn.infogiga.cmpp2.msg;

public class MsgHead {
	//字节数4 类型Unsigned Integer 消息总长度(含消息头及消息体)
	public int  Total_Length;	
	//字节数4 类型Unsigned Integer 命令或响应类型
	public int  Command_Id  ; 
	//字节数4 类型Unsigned Integer 消息流水号,顺序累加,步长为1,循环使用（一对请求和应答消息的流水号必须相同）
	public int  Sequence_Id ;
}
