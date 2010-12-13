package cn.infogiga.sd.service;

import cn.infogiga.pojo.Users;
import cn.infogiga.sd.dao.ManageHibernateDAO;

public interface ManageService {
	
	public ManageHibernateDAO getManageDAO();
		
	public Users checkLogin(Users admin);
	
	public void log(int logTypeId,String name,String things);
}
