package cn.infogiga.szqb.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.infogiga.szqb.dao.ManageHibernateDAO;
import cn.infogiga.szqb.pojo.Users;


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
	
	
}
