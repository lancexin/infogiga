package cn.infogiga.cmpp2.msg;

public class MsgSubmit extends MsgHead{
	public long Msg_Id	;//8	Unsigned Integer
	public byte Pk_total	;//	1	Unsigned Integer
	public byte Pk_number	;//	1	Unsigned Integer
	public byte Registered_Delivery	;//	1	Unsigned Integer
	public byte Msg_level	;//	1	Unsigned Integer
	public String Service_Id	;//	10	Octet String
	public byte Fee_UserType	;//	1	Unsigned Integer
	public String Fee_terminal_Id	;//	21	Unsigned Integer
	public byte TP_pId	;//	1	Unsigned Integer
	public byte TP_udhi	;//	1	Unsigned Integer
	public byte Msg_Fmt	;//	1	Unsigned Integer
	public String Msg_src	;//	6	Octet String
	public String FeeType	;//	2	Octet String
	public String FeeCode	;//	6	Octet String
	public String ValId_Time	;//	17	Octet String
	public String At_Time	;//	17	Octet String
	public String Src_Id	;//	21	Octet String
	public byte DestUsr_tl	;//	1	Unsigned Integer
	public String Dest_terminal_Id	;//	21*DestUsr_tl	Octet String
	public byte Msg_Length		;//1	Unsigned Integer
	public byte[] Msg_Content	;//	Octet String
	public String Reserve		;//8	Octet String
}
