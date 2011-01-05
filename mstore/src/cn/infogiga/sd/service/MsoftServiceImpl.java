package cn.infogiga.sd.service;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import cindy.util.ProperiesReader;
import cn.infogiga.pojo.Attachandarray;
import cn.infogiga.pojo.Attachment;
import cn.infogiga.pojo.Phonebrand;
import cn.infogiga.pojo.Phonebrandcategory;
import cn.infogiga.pojo.Phonetype;
import cn.infogiga.pojo.Soft;
import cn.infogiga.pojo.Softindex;
import cindy.util.FileUtil;


@Component("msoftService")
public class MsoftServiceImpl implements MsoftService{
	
	public void deleteAll(HttpServletRequest request){
		String phoneUrl = request.getRealPath(ProperiesReader.getInstence("config.properties").getStringValue("msoft.phone.url"));
		String softUrl = request.getRealPath(ProperiesReader.getInstence("config.properties").getStringValue("msoft.soft.url"));
		FileUtil.deleteAllFile(new File(phoneUrl));
		FileUtil.deleteAllFile(new File(softUrl));
		
		FileUtil.addFoler(phoneUrl);
		FileUtil.addFoler(softUrl);
	}
	
	
	public void addPhonebrand(Phonebrand phonebrand,HttpServletRequest request){
		//System.out.println(phonebrand.getPhonebrandName());
		String folerUrl = request.getRealPath(ProperiesReader.getInstence("config.properties").getStringValue("msoft.phone.url")+phonebrand.getPhonebrandName());
		//System.out.println(folerUrl);
		FileUtil.addFoler(folerUrl);
		String imgUrl = request.getRealPath(phonebrand.getUrl());
		if(imgUrl != null){
			FileUtil.copyFile(imgUrl, folerUrl+"/"+phonebrand.getPhonebrandName()+".jpg");
		}
	}
	
	public void deletePhonebrand(Phonebrand phonebrand,HttpServletRequest request){
		String folerUrl = request.getRealPath(ProperiesReader.getInstence("config.properties").getStringValue("msoft.phone.url")+phonebrand.getPhonebrandName());
		FileUtil.deleteAllFile(new File(folerUrl));
	}
	
	public void addCategory(Phonebrandcategory category,HttpServletRequest request){
		Phonebrand phonebrand = category.getPhonebrand();
		if(phonebrand == null){
			return;		
		}
		
		String folerUrl = request.getRealPath(ProperiesReader.getInstence("config.properties").getStringValue("msoft.phone.url")
					+phonebrand.getPhonebrandName()+"/"+category.getCategoryName());
		System.out.println(folerUrl);
		FileUtil.addFoler(folerUrl);
		String imgUrl = request.getRealPath(category.getPic());
		if(imgUrl != null){
			FileUtil.copyFile(imgUrl, folerUrl+"/"+category.getCategoryName()+".jpg");
		}
	}
	
	public void deleteCategory(Phonebrandcategory category,HttpServletRequest request){
		String folerUrl = request.getRealPath(ProperiesReader.getInstence("config.properties").getStringValue("msoft.phone.url")
					+category.getPhonebrand().getPhonebrandName()+"/"+category.getCategoryName());
		FileUtil.deleteAllFile(new File(folerUrl));
	}
	
	public void addPhonetype(Phonetype phonetype,HttpServletRequest request){
		Phonebrandcategory category = phonetype.getPhonebrandcategory();
		if(category == null){
			return;
		}
		Phonebrand phonebrand = category.getPhonebrand();
		if(phonebrand == null){
			return;
		}
		
		String folerUrl = request.getRealPath(ProperiesReader.getInstence("config.properties").getStringValue("msoft.phone.url")
			+phonebrand.getPhonebrandName()+"/"+category.getCategoryName()
			+"/"+phonetype.getPhonetypeName());
		FileUtil.addFoler(folerUrl);
		String imgUrl = request.getRealPath(phonetype.getPic());
		FileUtil.copyFile(imgUrl, folerUrl+"/Name.jpg");
		String system = phonetype.getPhonearray().getId()+","+phonetype.getPhonebrandcategory().getId()+","+phonetype.getPhonebrandcategory().getPhonebrand().getId()+","+phonetype.getId();
		FileUtil.addTxtFile(folerUrl, "system", system);
	
	}
	
	public void deletePhoentype(Phonetype phonetype,HttpServletRequest request){
		try {
			String folerUrl = request.getRealPath(ProperiesReader.getInstence("config.properties").getStringValue("msoft.phone.url")
				+phonetype.getPhonebrandcategory().getPhonebrand().getPhonebrandName()+"/"+phonetype.getPhonebrandcategory().getCategoryName()
				+"/"+phonetype.getPhonetypeName());
			FileUtil.deleteAllFile(new File(folerUrl));
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addAttachment(Attachment attachment,HttpServletRequest request){
		StringBuffer folderName = new StringBuffer();
		Soft soft = attachment.getSoft();
		Set indexs = soft.getSoftindexes();
		Iterator iite = indexs.iterator();
		//附件所在的菜单
		while(iite.hasNext()){
			Softindex index = (Softindex) iite.next();
			folderName.append("@"+index.getSoftmenu().getId()+"@");
		}
		//String arrayName = null;
		Set attachandarraies = attachment.getAttachandarraies();
		Iterator aite = attachandarraies.iterator();
		while(aite.hasNext()){
			Attachandarray  aaa = (Attachandarray) aite.next();
			folderName.append("^"+aaa.getPhonearray().getId()+"^");
			//arrayName = aaa.getPhonearray().getPhonearrayName();
		}
		//附件所属的软件
		folderName.append("-"+soft.getId()+"-");
		if(soft.getSoftCode() != null && soft.getSoftCode().trim().length() > 0){
			folderName.append("_"+soft.getSoftCode().trim()+"_");
		}
		
		//System.out.println(folderName);
		String folderUrl = request.getRealPath(ProperiesReader.getInstence("config.properties").getStringValue("msoft.soft.url")
			+folderName.toString());
		//将模板文件拷贝到对应的目录
		FileUtil.copyAllFile(request.getRealPath(ProperiesReader.getInstence("config.properties").getStringValue("msoft.templete.url")),folderUrl);
		//添加name.txt文件
		FileUtil.addTxtFile(folderUrl, "name", soft.getSoftName());
		//添加in.txt文件
		StringBuffer inBuffer = new StringBuffer();
		inBuffer.append("软件名称："+soft.getSoftName()+"<*>\r\n");
		inBuffer.append("软件资费：免费软件<*>\r\n");
		inBuffer.append("软件介绍："+soft.getDescription()+"<*>\r\n");
		FileUtil.addTxtFile(folderUrl, "in", inBuffer.toString());
		//添加name.png文件
		FileUtil.copyFile(request.getRealPath(soft.getIcon()), folderUrl+"/name.png");
		
		//添加软件截图图片
		String imgFolder = folderUrl+"\\软件介绍\\image";
		
		FileUtil.copyFile(request.getRealPath(soft.getPic1()), imgFolder+"\\1.jpg");
		FileUtil.copyFile(request.getRealPath(soft.getPic2()), imgFolder+"\\2.jpg");
		FileUtil.copyFile(request.getRealPath(soft.getPic3()), imgFolder+"\\3.jpg");
		FileUtil.copyFile(request.getRealPath(soft.getPic4()), imgFolder+"\\4.jpg");
		FileUtil.copyFile(request.getRealPath(soft.getPic5()), imgFolder+"\\5.jpg");
		
		//添加软件附件
		String attFolder = folderUrl+"\\down";
		//String attachmentName = soft.getShortName()+"_"+arrayName+attachment.getName().substring(attachment.getName().lastIndexOf(".")).toLowerCase();
		FileUtil.copyFile(request.getRealPath(attachment.getUrl()), attFolder+"\\"+attachment.getName());
	}
	
	public void deleteAttachment(Attachment attachment,HttpServletRequest request){
		final List<String> array = new LinkedList<String>();
		Soft soft = attachment.getSoft();
		Set indexs = soft.getSoftindexes();
		Iterator iite = indexs.iterator();
		//附件所在的菜单
		while(iite.hasNext()){
			Softindex index = (Softindex) iite.next();
			array.add("@"+index.getSoftmenu().getId()+"@");
		}
		
		Set attachandarraies = attachment.getAttachandarraies();
		Iterator aite = attachandarraies.iterator();
		while(aite.hasNext()){
			Attachandarray  aaa = (Attachandarray) aite.next();
			array.add("^"+aaa.getPhonearray().getId()+"^");
		}
		//附件所属的软件
		array.add("-"+soft.getId()+"-");
		//附件所属的手机组
		//array.add("^"+attachment.getPhonearray().getId());
		String folderUrl = request.getRealPath(ProperiesReader.getInstence("config.properties").getStringValue("msoft.soft.url"));
		File file = new File(folderUrl);
		FilenameFilter ffilter = new FilenameFilter(){
			public boolean accept(File dir, String name) {
				if(dir.isDirectory()){
					int size = array.size();
					for(int i=0;i<size;i++ ){
						if(!name.contains(array.get(i))){
							return false;
						}
					}
					return true;
				}
				return false;
			}
		};
		File[] files = file.listFiles(ffilter);
		for(int j=0;j<files.length;j++){
			FileUtil.deleteAllFile(files[j]);
		}
		
	}
	
	public void deleteAttachments(Soft soft,HttpServletRequest request){
		
		final String key = "-"+soft.getId()+"-";
	
		String folderUrl = request.getRealPath(ProperiesReader.getInstence("config.properties").getStringValue("msoft.soft.url"));
		File file = new File(folderUrl);
		FilenameFilter ffilter = new FilenameFilter(){
			public boolean accept(File dir, String name) {
				//System.out.println(name);
				if(dir.isDirectory()){
					if(!name.contains(key)){
						return false;
					}
					return true;
				}
				return false;
			}
		};
		File[] files = file.listFiles(ffilter);
		for(int j=0;j<files.length;j++){
			FileUtil.deleteAllFile(files[j]);
		}
		
	}


	public void addAttachments(List<Attachment> attachments,
			HttpServletRequest request) {
		int size = attachments.size();
		Attachment attachment = null;
		for(int i=0;i<size;i++){
			attachment = attachments.get(i);
			addAttachment(attachment, request);
		}
		
	}


	public void addCategories(List<Phonebrandcategory> categories,
			HttpServletRequest request) {
		int size = categories.size();
		Phonebrandcategory category = null;
		for(int i=0;i<size;i++){
			category = categories.get(i);
			addCategory(category, request);
		}
		
	}


	public void addPhonebrands(List<Phonebrand> phonebrands,
			HttpServletRequest request) {
		int size = phonebrands.size();
		Phonebrand phonebrand = null;
		for(int i=0;i<size;i++){
			phonebrand = phonebrands.get(i);
			addPhonebrand(phonebrand, request);
		}
	}


	public void addPhonetypes(List<Phonetype> phonetypes,
			HttpServletRequest request) {
		int size = phonetypes.size();
		Phonetype phonetype = null;
		for(int i=0;i<size;i++){
			phonetype = phonetypes.get(i);
			addPhonetype(phonetype, request);
		}
	}


	public void createClientFileList(HttpServletRequest request) {
		String softUrl = request.getRealPath(ProperiesReader.getInstence("config.properties").getStringValue("msoft.soft.url"));
		File softFile = new File(softUrl);
		File clientFolder = softFile.getParentFile();
		String exportUrl = clientFolder.getAbsolutePath()+File.separator;
		StringBuffer buffer = new StringBuffer();
		cn.infogiga.sd.util.ClientFilesUtil.exportClientFiles(clientFolder, buffer, clientFolder.getParent());
		FileUtil.addTxtFile(exportUrl, "clientFiles", buffer.toString());
	}
	

}
