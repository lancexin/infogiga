package cn.infogiga.sd.dto;

import cindy.page.beanutils.Sync;

public class JsonSoftMenu {
	@Sync(value = "id")
	private String softmenuId;
	
	@Sync(value = "menuName")
	private String softmenuName;
	
	public String getSoftmenuId() {
		return softmenuId;
	}
	public void setSoftmenuId(String softmenuId) {
		this.softmenuId = softmenuId;
	}
	public String getSoftmenuName() {
		return softmenuName;
	}
	public void setSoftmenuName(String softmenuName) {
		this.softmenuName = softmenuName;
	}
}
