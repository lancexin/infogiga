package script;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import cn.infogiga.exp.pojo.Downloadstat;
import cn.infogiga.exp.pojo.Phonebrand;
import cn.infogiga.exp.quartz.ExcelCreatorUtil;
import cn.infogiga.exp.quartz.ExcelUtil;
import cn.infogiga.exp.quartz.RDdownloadExcel;
import cn.infogiga.pojo.Bissinusshall;
import cn.infogiga.pojo.City;
import cn.infogiga.pojo.Phonetype;
import cn.infogiga.pojo.Power;
import cn.infogiga.pojo.Soft;
import cn.infogiga.pojo.Softdownloadstat;
import cn.infogiga.pojo.Users;
import cn.infogiga.sd.dto.JsonSoftDownloadStat;

import scrpit.now.dao.MappingNewDAO;
import scrpit.now.dao.MappingOldDAO;
import cindy.page.beanutils.MyBeanUtils;
import cindy.util.DateUtil;
import cindy.util.ProperiesReader;

public class TestController2 {
	
	MappingOldDAO oldDAO;
	MappingNewDAO newDAO;
	
	public MappingOldDAO getOldDAO() {
		return oldDAO;
	}

	public void setOldDAO(MappingOldDAO oldDAO) {
		this.oldDAO = oldDAO;
	}

	public MappingNewDAO getNewDAO() {
		return newDAO;
	}

	public void setNewDAO(MappingNewDAO newDAO) {
		this.newDAO = newDAO;
	}
	
	
	@RequestMapping(value = "/mock2")
	public void startMock2(HttpServletRequest request,HttpServletResponse response){
		testPhoneTypeNewToOld();
	}

	public void testPhoneTypeNewToOld(){
		
		
		List<cn.infogiga.pojo.Phonetype> newList = newDAO.findAll(cn.infogiga.pojo.Phonetype.class);
		cn.infogiga.pojo.Phonetype p1;
		for(int i=0;i<newList.size();i++){
			p1 = newList.get(i);
			if(p1.getPhonetypeName() == null ){
				continue;
			}
			int count = oldDAO.getCountByProperty(cn.infogiga.exp.pojo.Phonetype.class, "phonetypeName", p1.getPhonetypeName());
			if(count == 0){
				cn.infogiga.exp.pojo.Phonetype op = new cn.infogiga.exp.pojo.Phonetype();
				op.setPhonetypeName(p1.getPhonetypeName());
				cn.infogiga.exp.pojo.Phonebrand phonebrand = new cn.infogiga.exp.pojo.Phonebrand();
				phonebrand.setPhonebrandId(p1.getPhonebrandcategory().getPhonebrand().getId());
				op.setPhonebrand(phonebrand);
				op.setStatus(1);
				System.out.println(p1.getPhonetypeName()+"不存在");
				//oldDAO.save(op);
			}
		}
		
		
	}
}
