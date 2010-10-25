package cn.infogiga.android.experience.bean;


import cn.infogiga.util.xml.XMLBean;
import cn.infogiga.util.xml.XMLElement;
import cn.infogiga.util.xml.XMLField;
import cn.infogiga.util.xml.XMLTypes;

@XMLElement(value="USER_PRODUCT_RECORD",type=XMLTypes.XML_UNROOT)
public class UserProductRecord extends XMLBean {
	
	@XMLField(value="PROD_CODE",xmlType=XMLTypes.XML_ELEMENT)
	public String prodCode = null;
	
	@XMLField(value="PROD_NAME",xmlType=XMLTypes.XML_ELEMENT)
	public String prodName = null;
	
	@XMLField(value="CAN_OPEN",xmlType=XMLTypes.XML_ELEMENT)
	public String canOpen = null;
	
	@XMLField(value="CAN_CANCEL",xmlType=XMLTypes.XML_ELEMENT)
	public String canCancel = null;
	
	@XMLField(value="BUSI_FEE",xmlType=XMLTypes.XML_ELEMENT)
	public String BusiFee = null;
	
	@XMLField(value="SHOW_IDX",xmlType=XMLTypes.XML_ELEMENT)
	public String showIdx = null;
	
	@XMLField(value="CURRENT_STATUS",xmlType=XMLTypes.XML_ELEMENT)
	public String currentStatus = null;
	
	@XMLField(value="PROD_COL",xmlType=XMLTypes.XML_ELEMENT)
	public String prodCol = null;
	
	@XMLField(value="PROD_COL_CON",xmlType=XMLTypes.XML_ELEMENT)
	public String prodColCon = null;

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getCanOpen() {
		return canOpen;
	}

	public void setCanOpen(String canOpen) {
		this.canOpen = canOpen;
	}

	public String getCanCancel() {
		return canCancel;
	}

	public void setCanCancel(String canCancel) {
		this.canCancel = canCancel;
	}

	public String getBusiFee() {
		return BusiFee;
	}

	public void setBusiFee(String busiFee) {
		BusiFee = busiFee;
	}

	public String getShowIdx() {
		return showIdx;
	}

	public void setShowIdx(String showIdx) {
		this.showIdx = showIdx;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getProdCol() {
		return prodCol;
	}

	public void setProdCol(String prodCol) {
		this.prodCol = prodCol;
	}

	public String getProdColCon() {
		return prodColCon;
	}

	public void setProdColCon(String prodColCon) {
		this.prodColCon = prodColCon;
	}
}
