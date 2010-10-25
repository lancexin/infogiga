package cn.infogiga.service;

import java.util.List;

import cn.infogiga.bean.Customer;
import cn.infogiga.bean.Manager;
import cn.infogiga.dao.LoginDAO;
import cn.infogiga.util.StringToolkit;

public class LoginService extends BaseService{
	
	LoginDAO loginDAO;
	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}
	
	/**
	 * 检查该用户是否存在
	 * @param username
	 * @param password
	 * @return 如果存在则返回第一跟该实例，如果不存在则返回null
	 */
	public Manager checkLogin(String username,String password){
		Manager manager = new Manager();
		manager.setUsername(username);
		manager.setPassword(password);
		
		List<Manager> list = this.findByExample(manager, loginDAO);
		if(list != null && list.size() != 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * wap登录
	 * @param wapCode  wap的二维码
	 * @param password 密码
	 * @return
	 */
	public boolean checkWapLogin(String wapCode, String password) {
		Customer customer = loginDAO.findCustomerByQrcode(wapCode);
		if(customer == null) return false;
		return StringToolkit.eq(customer.getPassword(), password);		
	}
}
