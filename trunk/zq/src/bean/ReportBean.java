package bean;

import java.io.Serializable;

import tool.Toolkit;

public class ReportBean implements Serializable{
	
	private static final long serialVersionUID = -5070393765262639199L;
	
	private String addressName = "";
	private String sellingName = "";
	private String equipmentName = "";
	private String systemName = "";
	private String operateName = "";
	private String time = "";
	
	public ReportBean() {}
	
	public ReportBean(String addressName, String sellingName,
			String equipmentName, String systemName, String operateName,
			String time) {
		this.addressName = addressName;  //地区
		this.sellingName = sellingName;  //营业厅
		this.equipmentName = equipmentName; //设备
		this.systemName = systemName;    //
		this.operateName = operateName;  //操作
		this.time = Toolkit.String2Time(time);  //体验时间
		//
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

	public String getTime() {
		return time;		
	}

	public void setTime(String time) {		
		this.time = time;
	}
}
