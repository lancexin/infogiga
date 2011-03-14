package script;



import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import cn.infogiga.exp.pojo.Phonebrand;
import cn.infogiga.exp.pojo.Phonetype;

import scrpit.now.dao.MappingNewDAO;
import scrpit.now.dao.MappingOldDAO;


public class TestSpringMock2 extends AbstractDependencyInjectionSpringContextTests{

	
	
	protected String[] getConfigLocations() {
		String [] config = TestConfig.configUrl;
		return config;
	}
	
	
	public void testPhoneTypeNewToOld(){
		TestController cont = (TestController) this.applicationContext.getBean ("testController");
		
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/mock2"); 

        request.setSession(new MockHttpSession(null)); 

        HttpServletResponse response = new MockHttpServletResponse(); 

        cont.startMock2(request,response);

		
		
	}	
}
