package cn.infogiga.ibatis.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.infogiga.ibatis.dao.IbatisDao;

@Service 
public class TestService {
	@Autowired
	private IbatisDao ibatisDao;

	public void setIbatisDao(IbatisDao ibatisDao) {
		this.ibatisDao = ibatisDao;
	}

	public IbatisDao getIbatisDao() {
		return ibatisDao;
	}
	
	public String sayHello(String name){
		return "hello "+name;
	}
	
}
