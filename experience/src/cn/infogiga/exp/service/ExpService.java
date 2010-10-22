package cn.infogiga.exp.service;

import java.util.List;

import cindy.util.page.hibernate.CirteriaBean;
import cindy.web.pojo.POJO;
import cn.infogiga.exp.dao.ExperienceDAO;
import cn.infogiga.exp.pojo.Employee;
import cn.infogiga.exp.pojo.Userinfo;



public class ExpService {
	ExperienceDAO experienceDAO;

	public ExperienceDAO getExperienceDAO() {
		return experienceDAO;
	}

	public void setExperienceDAO(ExperienceDAO experienceDAO) {
		this.experienceDAO = experienceDAO;
	}

	public Userinfo checkLogin(String userName, String userPassword) {
		Userinfo user = new Userinfo();
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		return experienceDAO.findSingleByExample(user);
	}
	
	public Employee checkEmpLogin(String empNo, String password) {
		Employee emp = new Employee();
		emp.setEmpNo(empNo);
		emp.setPassword(password);
		return experienceDAO.findSingleByExample(emp);
	}
	
	public <T extends POJO> Integer getCount(Class<T> clazz){
		return experienceDAO.getCount(clazz);
	}
	
	public <T extends POJO> List<T> findAll(Class<T> clazz){
		return experienceDAO.findAll(clazz);
	}
	
	public <T extends POJO> List<T> getListByPage(Class<T> clazz,CirteriaBean cBean){
		return experienceDAO.getListByPage(clazz, cBean); 
	}
	
	public <T extends POJO> Integer getCountByPage(Class<T> clazz,CirteriaBean cBean){
		int i = experienceDAO.getCountByPage(clazz, cBean);
		return i;
	}
	
	public <T extends POJO> boolean save(T t){
		boolean b = false;
		try {
			experienceDAO.save(t);
			b = true;
		} catch (RuntimeException e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}
	
	public <T extends POJO> T getById(int id,Class<T> clazz){
		return experienceDAO.findById(clazz,id);
	}
	
	public <T extends POJO> boolean update(T t){
		boolean b = false;
		try {
			experienceDAO.update(t);
			b = true;
		} catch (RuntimeException e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}
	
	public void disRenewalDefalut() {
		experienceDAO.disRenewalDefault();
	}
	
	public <T extends POJO> boolean alreadyHas(T t){
		int count = experienceDAO.getCountByExample(t);
		if(count != 0){
			return true;
		}
		return false;
	}
	
	public <T extends POJO> List<T> findByProperty(Class<T> chazz,String propertyName,Object value){
		return experienceDAO.findByProperty(chazz, propertyName, value);
	}
}
