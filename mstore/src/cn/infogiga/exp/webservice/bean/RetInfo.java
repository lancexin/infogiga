package cn.infogiga.exp.webservice.bean;

import java.util.ArrayList;

import cn.infogiga.util.xml.XMLBean;
import cn.infogiga.util.xml.XMLElement;
import cn.infogiga.util.xml.XMLField;
import cn.infogiga.util.xml.XMLTypes;

@XMLElement(value="RET_INFO",type=XMLTypes.XML_UNROOT)
public class RetInfo extends XMLBean {
	
	
	
	@XMLField(value="MENU_ID",xmlType=XMLTypes.XML_ELEMENT)
	private String menuId = null;
	
	@XMLField(value="FUNC_ID",xmlType=XMLTypes.XML_ELEMENT)
	private String funcId = null;
	
	@XMLField(value="BUSI_NAME",xmlType=XMLTypes.XML_ELEMENT)
	private String busiName = null;
	
	@XMLField(value="BUSI_DESC",xmlType=XMLTypes.XML_ELEMENT)
	private String busiDesc = null;
	
	@XMLField(value="BUSI_DESC_2",xmlType=XMLTypes.XML_ELEMENT)
	private String busiDesc2 = null;
	
	@XMLField(value="SHOW_CUR_STATE",xmlType=XMLTypes.XML_ELEMENT)
	private String showCurState = null;
	
	@XMLField(value="SHOW_MODE",xmlType=XMLTypes.XML_ELEMENT)
	private String showMode = null;
	
	@XMLField(value="BUSI_FEE_DESC",xmlType=XMLTypes.XML_ELEMENT)
	private String busiFeeDesc = null;
	
	@XMLField(value="EFFECT_DESC",xmlType=XMLTypes.XML_ELEMENT)
	private String effectDesc = null;
	
	@XMLField(value="OTHER_DESC",xmlType=XMLTypes.XML_ELEMENT)
	private String otherDesc = null;
	
	@XMLField(value="IS_CONFIRM",xmlType=XMLTypes.XML_ELEMENT)
	private String isConfirm = null;
	
	@XMLField(value="NOTES",xmlType=XMLTypes.XML_ELEMENT)
	private String notes = null;
	
	@XMLField(value="USER_PRODUCT_LIST",xmlType=XMLTypes.XML_LIST,mClass=UserProductRecord.class)
	private ArrayList<UserProductRecord> userProductList = null;
	
	@XMLField(value="TOP_SOFT",xmlType=XMLTypes.XML_LIST,mClass=TopSoft.class)
	private ArrayList<TopSoft> topSoftList = null;
	
	@XMLField(value="STAR_SOFT",xmlType=XMLTypes.XML_BEAN,mClass=StarSoft.class)
	private StarSoft starSoft = null;
	
	@XMLField(value="FX_STATUS",xmlType=XMLTypes.XML_ELEMENT)
	private String fxStatus = null;
	
	@XMLField(value="FX_INFO",xmlType=XMLTypes.XML_ELEMENT)
	private String fxInfo = null;
	
	@XMLField(value="FXJY_STATUS",xmlType=XMLTypes.XML_ELEMENT)
	private String fxjyStatus = null;
	
	@XMLField(value="FXJY_INFO",xmlType=XMLTypes.XML_ELEMENT)
	private String fxjyInfo = null;
	
	@XMLField(xmlType=XMLTypes.XML_LIST,mClass=MenuInfo.class)
	private ArrayList<MenuInfo> menuInfo = null;
	
	@XMLField(value="ITEM_INFO_LIST",xmlType=XMLTypes.XML_LIST,mClass=ItemInfo.class)
	private ArrayList<ItemInfo> itemInfoList = null;

	public ArrayList<ItemInfo> getItemInfoList() {
		return itemInfoList;
	}

	public void setItemInfoList(ArrayList<ItemInfo> itemInfoList) {
		this.itemInfoList = itemInfoList;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	public String getBusiName() {
		return busiName;
	}

	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}

	public String getBusiDesc() {
		return busiDesc;
	}

	public void setBusiDesc(String busiDesc) {
		this.busiDesc = busiDesc;
	}

	public String getBusiDesc2() {
		return busiDesc2;
	}

	public void setBusiDesc2(String busiDesc2) {
		this.busiDesc2 = busiDesc2;
	}

	public String getShowCurState() {
		return showCurState;
	}

	public void setShowCurState(String showCurState) {
		this.showCurState = showCurState;
	}

	public String getShowMode() {
		return showMode;
	}

	public void setShowMode(String showMode) {
		this.showMode = showMode;
	}

	public String getBusiFeeDesc() {
		return busiFeeDesc;
	}

	public void setBusiFeeDesc(String busiFeeDesc) {
		this.busiFeeDesc = busiFeeDesc;
	}

	public String getEffectDesc() {
		return effectDesc;
	}

	public void setEffectDesc(String effectDesc) {
		this.effectDesc = effectDesc;
	}

	public String getOtherDesc() {
		return otherDesc;
	}

	public void setOtherDesc(String otherDesc) {
		this.otherDesc = otherDesc;
	}

	public String getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(String isConfirm) {
		this.isConfirm = isConfirm;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public ArrayList<UserProductRecord> getUserProductList() {
		return userProductList;
	}

	public void setUserProductList(ArrayList<UserProductRecord> userProductList) {
		this.userProductList = userProductList;
	}

	public String getFxStatus() {
		return fxStatus;
	}

	public void setFxStatus(String fxStatus) {
		this.fxStatus = fxStatus;
	}

	public String getFxInfo() {
		return fxInfo;
	}

	public void setFxInfo(String fxInfo) {
		this.fxInfo = fxInfo;
	}

	public String getFxjyStatus() {
		return fxjyStatus;
	}

	public void setFxjyStatus(String fxjyStatus) {
		this.fxjyStatus = fxjyStatus;
	}

	public String getFxjyInfo() {
		return fxjyInfo;
	}

	public void setFxjyInfo(String fxjyInfo) {
		this.fxjyInfo = fxjyInfo;
	}

	public ArrayList<MenuInfo> getMenuInfo() {
		return menuInfo;
	}

	public void setMenuInfo(ArrayList<MenuInfo> menuInfo) {
		this.menuInfo = menuInfo;
	}

	public ArrayList<TopSoft> getTopSoftList() {
		return topSoftList;
	}

	public void setTopSoftList(ArrayList<TopSoft> topSoftList) {
		this.topSoftList = topSoftList;
	}

	public StarSoft getStarSoft() {
		return starSoft;
	}

	public void setStarSoft(StarSoft starSoft) {
		this.starSoft = starSoft;
	}
	
	
	
	
	
	
	
}
