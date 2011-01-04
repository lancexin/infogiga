package cn.infogiga.sd.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.infogiga.pojo.Attachment;
import cn.infogiga.pojo.Log;
import cn.infogiga.pojo.Logtype;
import cn.infogiga.pojo.Users;
import cn.infogiga.sd.dao.ManageHibernateDAO;
import cn.infogiga.sd.service.ManageService;

@Component("manageService")
public class ManageServiceImpl implements ManageService{
	@Autowired
	ManageHibernateDAO manageDAO;

	public void setManageDAO(ManageHibernateDAO manageDAO) {
		this.manageDAO = manageDAO;
	}

	public ManageHibernateDAO getManageDAO() {
		return manageDAO;
	}

	public Users checkLogin(Users admin) {
		admin = manageDAO.findSingleByExample(admin);
		return admin;
	}
	
	public void log(int logTypeId,String name,String things){
		Log log = new Log();
		log.setDo_(things);
		log.setLogTime(new Date());
		log.setName(name);
		Logtype type = new Logtype();
		type.setId(logTypeId);
		log.setLogtype(type);
		manageDAO.save(log);
	}
	
}
