package script;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import cn.infogiga.exp.pojo.Employee;
import cn.infogiga.exp.pojo.Phonebrand;
import cn.infogiga.exp.pojo.Phonetype;
import cn.infogiga.exp.pojo.Softinfo;
import cn.infogiga.exp.pojo.Team;
import cn.infogiga.pojo.Soft;

import scrpit.now.dao.MappingNewDAO;
import scrpit.now.dao.MappingOldDAO;


public class TestSpringMock4 extends AbstractDependencyInjectionSpringContextTests{

	
	
	protected String[] getConfigLocations() {
		String [] config = TestConfig.configUrl;
		return config;
	}
	
	
	public void testChangePhonetype() throws IOException{

		MappingOldDAO oldDAO = (MappingOldDAO) this.applicationContext.getBean ("oldDao");
		Set list=new HashSet();
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(new File("ttt.txt")))); 
		String str=null; 
		while((str=br.readLine())!=null){
			list.add(str.trim()); 
		}
		Iterator ite = list.iterator();
		while(ite.hasNext()){
			String ss = (String)ite.next();
			String[] s = ss.trim().split(",");
			
			
			List<Employee> l1 = oldDAO.findByProperty(Employee.class, "empNo", s[4].trim());
			if(l1.size() > 0){
				//System.out.println("!!!!!!!!!!!!!!!!!"+ss);
				continue;
			}
			/*List<Employee> l3 = es.findByProperty(Employee.class, "empName", s[6].trim());
			if(l3.size() > 0){
				System.out.println("@@@@@@@@@@@@@@@@@"+ss);
				continue;
			}*/
			List<Team> l2 = oldDAO.findByProperty(Team.class, "teamCode", s[1]);
			if(l2.size() <= 0){
				System.out.println(ss);
				continue;
			}
			Team t = l2.get(0);
			Employee emp = new Employee();
			emp.setAddTime(new Date());
			emp.setEmpName(s[3].trim());
			emp.setEmpNo(s[4].trim());
			emp.setPassword("123456");
			if(s.length >= 7){
				emp.setPhoneNumber(s[5].trim());
			}
			
			emp.setPower("C_2,M_2,O_1,");
			emp.setStatus(1);
			emp.setTeam(t);
		}
	}
}
