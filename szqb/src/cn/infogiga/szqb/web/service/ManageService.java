package cn.infogiga.szqb.web.service;

import cn.infogiga.szqb.dao.ManageHibernateDAO;
import cn.infogiga.szqb.pojo.Users;



public interface ManageService {
	
	public ManageHibernateDAO getManageDAO();
		
	public Users checkLogin(Users admin);
}
