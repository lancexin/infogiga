package cn.infogiga.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.sun.istack.FinalArrayList;

import cn.infogiga.bean.Customer;
import cn.infogiga.bean.Qrcode;

public class LoginDAO  extends BaseDAO{

	@SuppressWarnings("unchecked")
	public Customer findCustomerByQrcode(final String wapCode) {		
		List<Qrcode> list = findByProperty(Qrcode.class, "wapCode", wapCode);		
		if(list==null || list.size()<=0) {
			return null;
		} else {
			Qrcode code = list.get(0);
			getSession().update(code);
			return code.getCustomer();
		}
	}
}
