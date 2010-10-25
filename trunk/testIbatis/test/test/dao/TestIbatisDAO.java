package test.dao;

import java.util.List;

import cn.infogiga.ibatis.dao.IbatisDao;
import cn.infogiga.ibatis.pojo.Power;
import cn.infogiga.ibatis.pojo.User;

public class TestIbatisDAO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IbatisDao ibatisDao = (IbatisDao) TestBeanFactory.getBean("ibatisDao");
		//PowerDao powerDao = (PowerDao) TestBeanFactory.getBean("powerDao");
		//ibatis 查询事例1
		User user = ibatisDao.findUserByIdWithCascade(1);
		System.out.println("userID:"+user.getUserID());
		System.out.println("userName:"+user.getUserName());
		System.out.println("passWord:"+user.getPassWord());
		Power power = user.getPower();
		System.out.println("powerName:"+power.getPowerName());
		System.out.println("powerValue:"+power.getPowerValue());
		System.out.println();
		
		//ibatis 查询事例2
		User user2 = ibatisDao.findUserById(1);
		System.out.println("userID:"+user2.getUserID());
		System.out.println("userName:"+user2.getUserName());
		System.out.println("passWord:"+user2.getPassWord());
		Power power2 = user2.getPower();
		System.out.println("powerName:"+power2.getPowerName());
		System.out.println("powerValue:"+power2.getPowerValue());
		System.out.println();
		//ibatis 查询事例3
		User user3 = new User();
		user3.setUserName("admin");
		
		user3 = ibatisDao.findUserByExample(user3);
		System.out.println("userID:"+user3.getUserID());
		System.out.println("userName:"+user3.getUserName());
		System.out.println("passWord:"+user3.getPassWord());
		Power power3 = user3.getPower();
		System.out.println("powerName:"+power3.getPowerName());
		System.out.println("powerValue:"+power3.getPowerValue());
		
		System.out.println();
		System.out.println();
		//ibatis 查询事例4
		User user4 = new User();
		//user4.setUserName("admin");
		//user4.setPassWord("admin");
		Power power4 = new Power();
		power4.setPowerName("管");
		user4.setPower(power4);
		List<User> list = ibatisDao.findUserByExample2(user4);
		int size = list.size();
		for(int i=0;i<size;i++){
			User u = list.get(i);
			System.out.println("u.userID:"+u.getUserID());
			System.out.println("u.userName:"+u.getUserName());
			System.out.println("u.passWord:"+u.getPassWord());
			
			Power p = u.getPower();
			System.out.println("p.powerID:"+p.getPowerID());
			System.out.println("p.powerName:"+p.getPowerName());
			System.out.println("p.powerValue:"+p.getPowerValue());
			System.out.println();
		}
		
	}

}
