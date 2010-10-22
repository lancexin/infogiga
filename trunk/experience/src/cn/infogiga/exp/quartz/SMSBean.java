package cn.infogiga.exp.quartz;

public class SMSBean {
	public static final int MMS_SINGLE = 0x40000001;
	
	public static final int MMS_ARRAY = 0x40000002;
	
	public static final int MMS_SINGLE_WAPPUSH = 0x40000003;
	
	public static final int MMS_ARRAY_WAPPUSH = 0x40000004;
	
	private String[] phoneNumbers;
	private String phoneNumber;
	private boolean isWapPush = false;
	private String url;
	private String context;
	private Integer sendType;
	private int smsarrayID;
	public int getSmsarrayID() {
		return smsarrayID;
	}
	public void setSmsarrayID(int smsarrayID) {
		this.smsarrayID = smsarrayID;
	}
	public String[] getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(String[] phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isWapPush() {
		return isWapPush;
	}
	public void setWapPush(boolean isWapPush) {
		this.isWapPush = isWapPush;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Integer getSendType() {
		return sendType;
	}
	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}
	public static Integer getMMS_SINGLE() {
		return MMS_SINGLE;
	}
	public static Integer getMMS_ARRAY() {
		return MMS_ARRAY;
	}
	public static Integer getMMS_SINGLE_WAPPUSH() {
		return MMS_SINGLE_WAPPUSH;
	}
	public static Integer getMMS_ARRAY_WAPPUSH() {
		return MMS_ARRAY_WAPPUSH;
	}
	
}
