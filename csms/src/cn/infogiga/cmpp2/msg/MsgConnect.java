package cn.infogiga.cmpp2.msg;

public class MsgConnect extends MsgHead{
	public String Source_Addr;		//6
	public byte[] AuthenticatorSource;	//16
	public byte Version;	//1
	public int Timestamp;	//4
}
