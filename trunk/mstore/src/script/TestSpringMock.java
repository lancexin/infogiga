package script;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import cindy.page.beanutils.MyBeanUtils;
import cindy.util.DateUtil;
import cindy.util.ProperiesReader;
import cn.infogiga.exp.pojo.Downloadstat;
import cn.infogiga.exp.quartz.ExcelCreatorUtil;
import cn.infogiga.exp.quartz.ExcelUtil;
import cn.infogiga.exp.quartz.RDdownloadExcel;
import cn.infogiga.pojo.Phonetype;
import cn.infogiga.pojo.Softdownloadstat;
import cn.infogiga.sd.dto.JsonSoftDownloadStat;

import scrpit.now.dao.MappingNewDAO;
import scrpit.now.dao.MappingOldDAO;


public class TestSpringMock{

	
	protected String[] getConfigLocations() {
		String [] config = TestConfig.configUrl;
		return config;
	}
	
	
	public void testStart(){
	/*//	TestController cont = (TestController) this.applicationContext.getBean ("testController");
		
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/mock"); 

        request.setSession(new MockHttpSession(null)); 

        HttpServletResponse response = new MockHttpServletResponse(); 

        cont.startMock(request,response);*/
		
		
	}	
}
