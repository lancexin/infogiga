package bean;

public class DeviceBean {
	
	private String deviceName;
	private String name;
	private String ip;
	private int status = 0;
	
	public DeviceBean(String deviceName, String name, String ip) {		
		this.deviceName = deviceName;
		this.name = name;
		this.ip = ip;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}	
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
}
