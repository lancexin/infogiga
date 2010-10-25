package cn.infogiga.config;

public class Page {

	private int pageNo;
	private String pageName;
	private String pageUri;
	
	public Page() {
		super();
	}
	public Page(int pageNo, String pageName, String pageUri) {
		super();
		this.pageNo = pageNo;
		this.pageName = pageName;
		this.pageUri = pageUri;
	}
	
	public String getPageUri() {
		return pageUri;
	}
	public void setPageUri(String pageUri) {
		this.pageUri = pageUri;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	
}
