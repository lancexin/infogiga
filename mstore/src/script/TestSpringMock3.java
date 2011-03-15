package script;



import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import cn.infogiga.exp.pojo.Phonebrand;
import cn.infogiga.exp.pojo.Phonetype;
import cn.infogiga.exp.pojo.Softinfo;
import cn.infogiga.pojo.Soft;

import scrpit.now.dao.MappingNewDAO;
import scrpit.now.dao.MappingOldDAO;


public class TestSpringMock3 extends AbstractDependencyInjectionSpringContextTests{

	
	
	protected String[] getConfigLocations() {
		String [] config = TestConfig.configUrl;
		return config;
	}
	
	
	public void testChangePhonetype(){
		/*MappingOldDAO oldDAO = (MappingOldDAO) this.applicationContext.getBean ("oldDao");
		MappingNewDAO newDAO = (MappingNewDAO) this.applicationContext.getBean ("newDao");
		
		List<cn.infogiga.pojo.Phonetype> sList = newDAO.findAll(cn.infogiga.pojo.Phonetype.class);
		cn.infogiga.pojo.Phonetype pt;
		for(int i=0;i<sList.size();i++){
			pt = sList.get(i);
			cn.infogiga.exp.pojo.Phonetype ltt = oldDAO.findById(cn.infogiga.exp.pojo.Phonetype.class, pt.getId());
			//System.out.println(pt.getPhonetypeName()+"   "+ltt.getPhonetypeName());
			if(ltt == null){
				ltt = new cn.infogiga.exp.pojo.Phonetype();
				ltt.setPhonetypeId(pt.getId());
				ltt.setPhonetypeName(pt.getPhonetypeName());
				ltt.setStatus(1);
				oldDAO.save(ltt);
				System.out.println(pt.getPhonetypeName()+"   未找到");
			}else{
				System.out.println(ltt.getPhonetypeName()+"   转换成    "+pt.getPhonetypeName());
				ltt.setPhonetypeName(pt.getPhonetypeName());
				ltt.setStatus(1);
				oldDAO.update(ltt);
			}
			
			
		}*/
		
	}
	
	public void testChangePhonebrand(){
		MappingOldDAO oldDAO = (MappingOldDAO) this.applicationContext.getBean ("oldDao");
		MappingNewDAO newDAO = (MappingNewDAO) this.applicationContext.getBean ("newDao");
		
		List<cn.infogiga.exp.pojo.Phonetype> sList = oldDAO.findAll(cn.infogiga.exp.pojo.Phonetype.class);
		cn.infogiga.exp.pojo.Phonetype phonetype;
		for(int i=0;i<sList.size();i++){
			phonetype = sList.get(i);
			cn.infogiga.pojo.Phonetype pt = newDAO.findById(cn.infogiga.pojo.Phonetype.class, phonetype.getPhonetypeId());
			if(pt == null){
				continue;
			}
			cn.infogiga.pojo.Phonebrandcategory pl = newDAO.findById(cn.infogiga.pojo.Phonebrandcategory.class, pt.getPhonebrandcategory().getId());
			
			cn.infogiga.exp.pojo.Phonebrand phonebrand = new cn.infogiga.exp.pojo.Phonebrand();
			System.out.println(phonetype.getPhonetypeName()+"  "+phonetype.getPhonebrand().getPhonebrandId()+"   修改为   "+pl.getPhonebrand().getId());
			phonebrand.setPhonebrandId(pl.getPhonebrand().getId());
			phonetype.setPhonebrand(phonebrand);
			oldDAO.update(phonetype);
			
		}
	}
	
	public void testChangeSoft(){
		/*MappingOldDAO oldDAO = (MappingOldDAO) this.applicationContext.getBean ("oldDao");
		MappingNewDAO newDAO = (MappingNewDAO) this.applicationContext.getBean ("newDao");
		
		List<Soft> sList = newDAO.findAll(Soft.class);
		Soft soft;
		for(int i=0;i<sList.size();i++){
			soft = sList.get(i);
			Softinfo info = oldDAO.findById(Softinfo.class, soft.getId());
			if(info == null){
				info = new Softinfo();
				info.setSoftId( soft.getId());
				info.setAddTime(new Date());
				info.setSoftName(soft.getSoftName());
				info.setFlag(0);
				info.setStatus(1);
				oldDAO.save(info);
				System.out.println("保存   "+soft.getSoftName());
			}else{
				info.setSoftName(soft.getSoftName());
				info.setFlag(0);
				info.setStatus(1);
				oldDAO.update(info);
				System.out.println(info.getSoftName()+"    修改   "+soft.getSoftName());
			}
		}*/
	}
}
