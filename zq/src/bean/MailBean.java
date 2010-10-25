package bean;

public class MailBean {
	
	private String smtpHost;
	private String userName;
	private String password;
	private String from;
	private String to;
	private String cyc;
	private String hour;
	private String flag;
	
	public MailBean() {}
	
	public MailBean(String smtpHost, String userName, String password,
			String from, String to, String cyc, String hour, String flag) {
		this.smtpHost = smtpHost;
		this.userName = userName;
		this.password = password;
		this.from = from;
		this.to = to;
		this.cyc = cyc;
		this.hour = hour;
		this.flag = flag;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCyc() {
		return cyc;
	}

	public void setCyc(String cyc) {
		this.cyc = cyc;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
