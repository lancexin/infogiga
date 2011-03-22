package script;





import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import cn.infogiga.pojo.Phonebrand;
import cn.infogiga.pojo.Phonebrandcategory;
import cn.infogiga.pojo.Phonetype;
import cn.infogiga.pojo.Soft;
import cn.infogiga.sd.dao.ManageHibernateDAO;




public class TestSpringMock5 extends AbstractDependencyInjectionSpringContextTests{

	
	
	protected String[] getConfigLocations() {
		String [] config = TestConfig.configUrl;
		return config;
	}
	
	
	public void testChangePhonetype(){
		/*ManageHibernateDAO newDAO = (ManageHibernateDAO) this.applicationContext.getBean ("manageDAO");
		List<Phonetype> list = newDAO.findAll(Phonetype.class);
		Phonetype p = null;
		StringBuffer buffer = new StringBuffer();
		Soft soft;
		for(int i=0;i<list.size();i++){
			buffer.reverse(); 
			p = list.get(i);
			Phonebrandcategory category = newDAO.findById(Phonebrandcategory.class, p.getPhonebrandcategory().getId());
			Phonebrand phonebrand = newDAO.findById(Phonebrand.class, category.getPhonebrand().getId());
			buffer.reverse(); 
			List<Soft> mList = newDAO.searchSoft(-1, p.getId());
			for(int j =0;i<mList.size();j++){
				soft = mList.get(j);
				buffer.append(soft.getSoftName()+",");
			}
			System.out.println();
		}*/
	}
	
	public void testSearchSoft() throws IOException{
		ManageHibernateDAO newDAO = (ManageHibernateDAO) this.applicationContext.getBean ("manageDAO");
		List<Phonebrand> list = newDAO.findAll(Phonebrand.class);
		Phonebrand phonebrand;
		Phonebrandcategory category;
		Phonetype phonetype;
		Soft soft;
		StringBuffer buffer = new StringBuffer();
		PrintWriter print = new PrintWriter(new BufferedWriter(new FileWriter("D:\\IODemo.txt" ))); 
		for(int i=0;i<list.size();i++){
			phonebrand = list.get(i);
			//System.out.println("手机品牌："+phonebrand.getPhonebrandName());
			print.println("手机品牌："+phonebrand.getPhonebrandName());
			List<Phonebrandcategory> aList = newDAO.findByProperty(Phonebrandcategory.class, "phonebrand.id", phonebrand.getId());
			for(int j=0;j<aList.size();j++){
				category = aList.get(j);
				print.println("手机品牌分类："+category.getCategoryName());
				List<Phonetype> bList = newDAO.findByProperty(Phonetype.class, "phonebrandcategory.id", category.getId());
				for(int k = 0;k<bList.size();k++){
					buffer.delete(0, buffer.length());
					phonetype = bList.get(k);
					print.println("手机型号："+phonetype.getPhonetypeName());
					List<Soft> cList = newDAO.searchSoft(-1, phonetype.getId());
					for(int l=0;l<cList.size();l++){
						soft = cList.get(l);
						buffer.append(soft.getSoftName()+",");
					}
					print.println("拥有软件："+buffer.toString());
				}
			}
		}
		print.flush();
		print.close();
	}
}
