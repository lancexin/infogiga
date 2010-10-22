package cindy.util.page.hibernate;

public class PageBean {
	private int totalSize = 0; //总条数
	private int totalPage = 0; //总页数
	private int nowPage = 0; //当前第几页
	private int pageSize = 10; //每页显示多少条
	private int startSize = 0; //从第几条开始显示
	
	public PageBean(){
		
	}
	
	public PageBean(int totalSize,int nowPage){
		this.totalSize = totalSize;
		this.nowPage = nowPage;
	}
	public int getTotalSize() {
		return totalSize;
	}
	
	public int getNowPage() {
		return nowPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getStartSize() {
		return startSize;
	}
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setStartSize(int startSize) {
		this.startSize = startSize;
	}
	
	public void initize(){
		this.totalPage = (this.totalSize -1)/this.pageSize+1;
		if(this.nowPage > this.totalPage-1){
			this.nowPage = this.totalPage-1;
		}
		if(this.nowPage <0){
			this.nowPage = 0;
		}
		this.startSize = this.pageSize*this.nowPage;
	}
}
