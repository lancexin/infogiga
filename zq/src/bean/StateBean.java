package bean;

public class StateBean {
	
	private String id = "";
	private String startTime = "";
	private String endTime = "";
	private String phone = "";
	private String message = "";
	
	public StateBean() {}
	
	public StateBean(String id, String startTime, String endTime, String phone, String message) {		
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.phone = phone;
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStartTime() {
		return startTime;	
	}

	public void setStartTime(String startTime) {		
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;		
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPhone() {		
		return phone;	
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}			
}
