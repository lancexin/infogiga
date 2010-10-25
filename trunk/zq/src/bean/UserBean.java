package bean;

public class UserBean {
	
	private String userName;
	private String passWord;
	private int authority;
	
	public UserBean(String userName, String passWord, int authority) {	
		this.userName = userName;
		this.passWord = passWord;
		this.authority = authority;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}	
}
