package cn.infogiga.sd.dto;

import java.util.List;

public class JsonListBean {
	List<?> data;
	Integer totalCount;
	public JsonListBean(Integer totalCount,List<?> data) {
		super();
		this.data = data;
		this.totalCount = totalCount;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
}
