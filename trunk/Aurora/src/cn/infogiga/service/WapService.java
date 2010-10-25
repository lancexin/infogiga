package cn.infogiga.service;

import java.util.List;

import cn.infogiga.bean.BaseBean;
import cn.infogiga.dao.WapDAO;

public class WapService extends BaseService{
	
	WapDAO wapDAO;
	public void setWapDAO(WapDAO wapDAO) {
		this.wapDAO = wapDAO;
	}

}
