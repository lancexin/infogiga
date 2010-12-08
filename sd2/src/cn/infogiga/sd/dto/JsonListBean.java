package cn.infogiga.sd.dto;

import java.util.List;

public class JsonListBean {
	List<?> data;
	Integer totalCount;
	Boolean success;
	String msg;
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public JsonListBean(Integer totalCount,List<?> data,Boolean success,String msg) {
		super();
		this.data = data;
		this.totalCount = totalCount;
		this.success = success;
		this.msg = msg;
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
