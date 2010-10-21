package cn.infogiga.sd.service;

import cn.infogiga.sd.dao.ManageHibernateDAO;
import cn.infogiga.sd.pojo.Admin;
import cn.infogiga.sd.pojo.Employee;

public interface ManageService {
	
	public ManageHibernateDAO getManageDAO();
	
	public void setManageDAO(ManageHibernateDAO manageDAO);
	
	public Employee checkLogin(Employee employee);
	
	public Admin checkLogin(Admin admin);
}
