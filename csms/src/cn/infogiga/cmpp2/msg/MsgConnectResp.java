package cn.infogiga.cmpp2.msg;

public class MsgConnectResp extends MsgHead{
	public byte Status;	//1	Unsigned Integer
	public String AuthenticatorISMG;	//16	Octet String
	public byte Version;	//1	Unsigned Integer
}
