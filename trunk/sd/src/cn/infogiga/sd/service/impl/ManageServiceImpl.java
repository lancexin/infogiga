package cn.infogiga.sd.service.impl;

import cn.infogiga.sd.dao.ManageHibernateDAO;
import cn.infogiga.sd.pojo.Admin;
import cn.infogiga.sd.pojo.Employee;
import cn.infogiga.sd.service.ManageService;

public class ManageServiceImpl implements ManageService{
	ManageHibernateDAO manageDAO;

	public ManageHibernateDAO getManageDAO() {
		return manageDAO;
	}

	public void setManageDAO(ManageHibernateDAO manageDAO) {
		this.manageDAO = manageDAO;
	}

	public Employee checkLogin(Employee employee) {
		employee = manageDAO.findSingleByExample(employee);
		return employee;
	}

	public Admin checkLogin(Admin admin) {
		admin = manageDAO.findSingleByExample(admin);
		return admin;
	}
}
