package bean;

public class SearchFormBean {
	
	private String addressName = "";  //地区
	private String sellingName = "";  //营业厅
	private String equipmentName = "";  //设备
	private String systemName = "";  //
	private String operateName = "";
	private String startTime = "";
	private String endTime = "";
	private String phone = "";
	
	public SearchFormBean() {}
	
	public SearchFormBean(String addressName, String sellingName,
			String equipmentName, String systemName, String operateName,
			String startTime, String endTime, String phone) {
		this.addressName = addressName;
		this.sellingName = sellingName;
		this.equipmentName = equipmentName;
		this.systemName = systemName;
		this.operateName = operateName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.phone = phone;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getSellingName() {
		return sellingName;
	}

	public void setSellingName(String sellingName) {
		this.sellingName = sellingName;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
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
}
