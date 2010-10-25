package bean;

public class FinalTableBean {
	
	private String equipmentCode = "";
	private String operateCode = "";
	private String validateCode= "";
	private String stateCode = "";
	
	public FinalTableBean() {}
	
	public FinalTableBean(String equipmentCode, String operateCode,
			String validateCode, String stateCode) {		
		this.equipmentCode = equipmentCode;
		this.operateCode = operateCode;
		this.validateCode = validateCode;
		this.stateCode = stateCode;
	}

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public String getOperateCode() {
		return operateCode;
	}

	public void setOperateCode(String operateCode) {
		this.operateCode = operateCode;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}	
}
