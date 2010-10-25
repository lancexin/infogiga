package cn.infogiga.cmpp2.msg;

public class MsgDeliver extends MsgHead{
	public long Msg_Id;//	8	Unsigned Integer
	public String Dest_Id;//	21	Octet String
	public String Service_Id;//	10	Octet String
	public byte TP_pid;//	1	Unsigned Integer
	public byte TP_udhi;//	1	Unsigned Integer
	public byte Msg_Fmt;//	1	Unsigned Integer
	public String Src_terminal_Id;//	21	Octet String
	public byte Registered_Delivery;//	1	Unsigned Integer
	public byte Msg_Length;//	1	Unsigned Integer
	public String Msg_Content;//	Msg_length	Octet String
	public String Reserved;//	8	Octet String
}
