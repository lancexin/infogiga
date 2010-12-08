package cn.infogiga.sd.service;

import cn.infogiga.sd.dao.ManageHibernateDAO;
import cn.infogiga.sd.pojo.Users;

public interface ManageService {
	
	public ManageHibernateDAO getManageDAO();
		
	public Users checkLogin(Users admin);
	
	public void log(int logTypeId,String name,String things);
}
