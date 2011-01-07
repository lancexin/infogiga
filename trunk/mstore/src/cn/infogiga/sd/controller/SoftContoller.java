package cn.infogiga.sd.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cindy.page.beanutils.MyBeanUtils;
import cindy.page.hibernate.CirteriaBean;
import cindy.page.hibernate.CirteriaQuery;
import cindy.page.hibernate.PageBean;
import cindy.util.Code;
import cindy.util.FileUtil;
import cindy.util.ImageUtil;
import cindy.util.MyValidate;
import cindy.util.ProperiesReader;
import cn.infogiga.pojo.Attachandarray;
import cn.infogiga.pojo.Attachment;
import cn.infogiga.pojo.Download;
import cn.infogiga.pojo.Logtype;
import cn.infogiga.pojo.Phonearray;
import cn.infogiga.pojo.Phonebrand;
import cn.infogiga.pojo.Phonebrandcategory;
import cn.infogiga.pojo.Phonetype;
import cn.infogiga.pojo.Soft;
import cn.infogiga.pojo.Softdownloadstat;
import cn.infogiga.pojo.Softindex;
import cn.infogiga.pojo.Softmenu;
import cn.infogiga.pojo.Users;
import cn.infogiga.sd.dto.JsonListBean;
import cn.infogiga.sd.dto.JsonSoft;
import cn.infogiga.sd.dto.JsonSoftDownloadStat;
import cn.infogiga.sd.service.ManageService;
import cn.infogiga.sd.service.MsoftService;

@Controller
public class SoftContoller {
	@Autowired
	ManageService manageService;
	

	@Autowired
	MsoftService msoftService;
	
	@RequestMapping(value = "/soft")
	public String softJsonList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start){
		String softName = request.getParameter("query");
		Integer limit = (request.getParameter("limit")==null || request.getParameter("limit").length()==0)?20:Integer.parseInt(request.getParameter("limit"));
		PageBean pageBean = new PageBean(start,limit);
		CirteriaBean cBean = new CirteriaBean("id");
		cBean.setPageBean(pageBean);
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.LIKE,CirteriaQuery.IS_STRING,"softName",softName,null));
		int totalCount = manageService.getManageDAO().getCountByPage(Soft.class, cBean);
		List<JsonSoft> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByPage(Soft.class, cBean), JsonSoft.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list,true,null);
		model.addAttribute("object", jsonListBean);
		return "list";
	}
	
	@RequestMapping(value = "/soft",params="export")
	public String softExport(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model){
		
		try {
			msoftService.deleteAll(request);
			
			List<Phonebrand> phonebrands = manageService.getManageDAO().findAll(Phonebrand.class);
			msoftService.addPhonebrands(phonebrands, request);
			
			List<Phonebrandcategory> categories = manageService.getManageDAO().findAll(Phonebrandcategory.class);
			msoftService.addCategories(categories, request);
			
			List<Phonetype> phonetypes = manageService.getManageDAO().findAll(Phonetype.class);
			msoftService.addPhonetypes(phonetypes, request);
			
			List<Attachment> attachments = manageService.getManageDAO().findAll(Attachment.class);
			msoftService.addAttachments(attachments, request);
			
			msoftService.createClientFileList(request);
			
			model.put("success", true);
			model.put("msg", "导出成功！");
		} catch (RuntimeException e) {
			model.put("success", false);
			model.put("msg", "导出出错,请联系管理员或重新试一次！");
			e.printStackTrace();
		}
		return "list";
	}
	
	@RequestMapping(value = "/soft",params="updateStatus")
	public String updateSoftStatus(HttpServletRequest request,HttpServletResponse response,HttpSession session,ModelMap model){
		manageService.getManageDAO().updateAllSoftStatus();
		model.put("success", true);
		model.put("msg", "修改成功！");
		return "list";
	}
	
	
	@RequestMapping(value = "/soft",params="delete")
	public String deleteSoft(HttpServletRequest request,HttpServletResponse response,HttpSession session,ModelMap model,
			@RequestParam("softId")Integer softId){
		Soft soft = manageService.getManageDAO().findById(Soft.class, softId);
		Set attachmentList = soft.getAttachments();
		//System.out.println(attachmentList.size());
		Iterator ite = attachmentList.iterator();
		while(ite.hasNext()){
			Attachment a = (Attachment) ite.next();
			manageService.getManageDAO().delete(a);
			FileUtil.delete(new File(request.getRealPath(a.getUrl())));
		}
		FileUtil.delete(new File(request.getRealPath(soft.getIcon())));
		FileUtil.delete(new File(request.getRealPath(soft.getPic1())));
		FileUtil.delete(new File(request.getRealPath(soft.getPic2())));
		FileUtil.delete(new File(request.getRealPath(soft.getPic3())));
		FileUtil.delete(new File(request.getRealPath(soft.getPic4())));
		FileUtil.delete(new File(request.getRealPath(soft.getPic5())));
		Set siset = soft.getSoftindexes();
		manageService.getManageDAO().deleteAll(siset);
		try {
			manageService.getManageDAO().delete(soft);
			//msoftService.deleteAttachments(soft, request);
			model.put("success", true);
			model.put("msg", "删除成功！");
			manageService.log(Logtype.SOFT, ((Users)session.getAttribute("user")).getNickName(),"删除软件信息,软件名称为："+soft.getSoftName());
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "删除失败,该软件可能已经投入使用~");
		}
		return "list";
	}

	@RequestMapping(value = "/soft",params="add")
	public String addSoft(HttpServletRequest request,HttpServletResponse response,HttpSession session,ModelMap model,
			@RequestParam("icon")String icon,
			@RequestParam("pic1")String pic1,
			@RequestParam("pic2")String pic2,
			@RequestParam("pic3")String pic3,
			@RequestParam("pic4")String pic4,
			@RequestParam("pic5")String pic5,
			@RequestParam("softName")String softName,
			@RequestParam("softCode")String softCode,
			@RequestParam("shortName")String shortName,
			@RequestParam("description")String description,
			@RequestParam("softmenuId")Integer[] softmenus,
			@RequestParam("status")Integer status){
		
		if(icon == null || icon.trim().length() ==0 || 
				softName == null || softName.trim().length() ==0 || 
				shortName == null || shortName.trim().length() ==0 || 
				description == null || description.trim().length() ==0 || 
				pic1 == null || pic1.trim().length() ==0 ||
				pic2 == null || pic2.trim().length() ==0 ||
				pic3 == null || pic3.trim().length() ==0 ||
				pic4 == null || pic4.trim().length() ==0 ||
				pic5 == null || pic5.trim().length() ==0 ){
			model.put("success", false);
			model.put("msg", "信息必须填写完整！");
			return "list";
		}
		
		String picIcon = savePNG(request, model, icon);
		String picUrl1 = saveJPG(request, model, pic1);
		String picUrl2 = saveJPG(request, model, pic2);
		String picUrl3 = saveJPG(request, model, pic3);
		String picUrl4 = saveJPG(request, model, pic4);
		String picUrl5 = saveJPG(request, model, pic5);
		if(picUrl1 == null || picUrl2 == null ||picUrl3 == null ||picUrl4 == null ||picUrl5 == null ||picIcon == null){
			return "list";
		}
		int count = manageService.getManageDAO().getCountByProperty(Soft.class, "softName", softName);
		if(count > 0 ){
			model.put("success", false);
			model.put("msg", "该软件名称已经存在！");
			return "list";
		}
		Soft soft = new Soft();
		soft.setSoftName(softName);
		shortName = shortName.replaceAll(" ", "");
		soft.setShortName(shortName);
		if(softCode != null && softCode.trim().length() > 0 && MyValidate.isNumeric(softCode)){
			soft.setSoftCode(softCode);
		}
		soft.setAddTime(new Date());
		soft.setDescription(description);
		soft.setIcon(picIcon);
		soft.setStatus(status);
		soft.setPic1(picUrl1);
		soft.setPic2(picUrl2);
		soft.setPic3(picUrl3);
		soft.setPic4(picUrl4);
		soft.setPic5(picUrl5);
		
		try {
			Download download = new Download();
			download.setDownloadCount(0);
			soft.setDownload(download);
			Set softindexes = new HashSet();
			for(int i=0;i<softmenus.length;i++){
				Softmenu sm = new Softmenu();
				sm.setId(softmenus[i]);
				Softindex si = new Softindex();
				si.setSoft(soft);
				si.setSoftmenu(sm);
				softindexes.add(si);
			}
			soft.setSoftindexes(softindexes);
			manageService.getManageDAO().save(soft);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "软件保存失败");
			return "list";
		}
		
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> map = multipartRequest.getFileMap();
		Set<String> set = map.keySet();
		Iterator<String> ite = set.iterator();
		while(ite.hasNext()){
			String name = ite.next();
			MultipartFile file = map.get(name);
			if(file.isEmpty()){
				continue;
			}
			String suffix = validateFile(file);
			if(suffix == null){
				manageService.getManageDAO().delete(soft);
				model.put("success", false);
				model.put("msg", "上传附件后缀出现错误");
				return "list";
			}
			String code = Code.getCode();
			File mfile = saveFile(request, file, suffix,code,ProperiesReader.getInstence("config.properties").getStringValue("material.soft.url"));
			if(file == null){
				manageService.getManageDAO().delete(soft);
				model.put("msg", "文件保存失败！");
				model.put("success", false);
				return "list";
			}
			String[] arrayIds = name.split("_");
			if(arrayIds.length > 1){
				Phonearray ph = manageService.getManageDAO().findById(Phonearray.class, Integer.parseInt(arrayIds[1]));
				
				Attachment attachment = new Attachment();
				attachment.setCode(code);
				attachment.setName(shortName+"_"+ph.getPhonearrayName()+suffix);
				attachment.setUrl(ProperiesReader.getInstence("config.properties").getStringValue("material.soft.url")+code+suffix);
				attachment.setSoft(soft);
				
				Set attachandarraies = new HashSet();
		
				for(int i=1;i<arrayIds.length;i++){
					Attachandarray aaa = new Attachandarray();
					aaa.setAttachment(attachment);
					Phonearray phonearray = manageService.getManageDAO().findById(Phonearray.class, Integer.parseInt(arrayIds[i]));
					aaa.setPhonearray(phonearray);
					attachandarraies.add(aaa);
				}
				attachment.setAttachandarraies(attachandarraies);
				manageService.getManageDAO().save(attachment);
				//msoftService.addAttachment(attachment, request);
			}
			
		}
		model.put("success", true);
		model.put("msg", "添加成功！");
		manageService.log(Logtype.SOFT, ((Users)session.getAttribute("user")).getNickName(),"添加软件信息,软件名称为："+softName);
		return "list";
	}
	

	@RequestMapping(value = "/soft",params="update")
	public String updateSoft(HttpServletRequest request,HttpServletResponse response,HttpSession session,ModelMap model,
			@RequestParam("softId")Integer softId,
			@RequestParam("icon")String icon,
			@RequestParam("pic1")String pic1,
			@RequestParam("pic2")String pic2,
			@RequestParam("pic3")String pic3,
			@RequestParam("pic4")String pic4,
			@RequestParam("pic5")String pic5,
			@RequestParam("softName")String softName,
			@RequestParam("softCode")String softCode,
			@RequestParam("shortName")String shortName,
			@RequestParam("description")String description,
			@RequestParam("softmenuId")Integer[] softmenus,
			@RequestParam("status")Integer status){
		
		if(icon == null || icon.trim().length() ==0 || 
				softName == null || softName.trim().length() ==0 || 
				description == null || description.trim().length() ==0 || 
				pic1 == null || pic1.trim().length() ==0 ||
				pic2 == null || pic2.trim().length() ==0 ||
				pic3 == null || pic3.trim().length() ==0 ||
				pic4 == null || pic4.trim().length() ==0 ||
				pic5 == null || pic5.trim().length() ==0 ){
			model.put("success", false);
			model.put("msg", "信息必须填写完整！");
			return "list";
		}
		
		String picIcon = savePNG(request, model, icon);
		String picUrl1 = saveJPG(request, model, pic1);
		String picUrl2 = saveJPG(request, model, pic2);
		String picUrl3 = saveJPG(request, model, pic3);
		String picUrl4 = saveJPG(request, model, pic4);
		String picUrl5 = saveJPG(request, model, pic5);
		if(picUrl1 == null || picUrl2 == null ||picUrl3 == null ||picUrl4 == null ||picUrl5 == null ||picIcon == null){
			return "list";
		}
		int count = manageService.getManageDAO().getCountByProperty(Soft.class, "softName", softName);
		if(count > 1 ){
			model.put("success", false);
			model.put("msg", "该软件名称已经存在！");
			return "list";
		}
		Soft soft = manageService.getManageDAO().findById(Soft.class, softId);
		if(soft.getIcon() != null){
			FileUtil.delete(new File(request.getRealPath(soft.getIcon())));
		}
		if(soft.getPic1() != null){
			FileUtil.delete(new File(request.getRealPath(soft.getPic1())));
		}
		if(soft.getPic2() != null){
			FileUtil.delete(new File(request.getRealPath(soft.getPic2())));
		}
		if(soft.getPic3() != null){
			FileUtil.delete(new File(request.getRealPath(soft.getPic3())));
		}
		
		if(soft.getPic4() != null){
			FileUtil.delete(new File(request.getRealPath(soft.getPic4())));
		}
		
		if(soft.getPic5() != null){
			FileUtil.delete(new File(request.getRealPath(soft.getPic5())));
		}
		
		Set siset = soft.getSoftindexes();
		manageService.getManageDAO().deleteAll(siset);
		
		soft.setSoftName(softName);
		shortName = shortName.replaceAll(" ", "");
		soft.setShortName(shortName);
		if(softCode != null && softCode.trim().length() > 0 && MyValidate.isNumeric(softCode)){
			soft.setSoftCode(softCode);
		}
		soft.setDescription(description);
		soft.setIcon(picIcon);
		soft.setStatus(status);
		soft.setPic1(picUrl1);
		soft.setPic2(picUrl2);
		soft.setPic3(picUrl3);
		soft.setPic4(picUrl4);
		soft.setPic5(picUrl5);
		
		try {
			
			Set softindexes = new HashSet();
			for(int i=0;i<softmenus.length;i++){
				Softmenu sm = new Softmenu();
				sm.setId(softmenus[i]);
				Softindex si = new Softindex();
				si.setSoft(soft);
				si.setSoftmenu(sm);
				softindexes.add(si);
				//manageService.getManageDAO().save(si);
			}
			soft.setSoftindexes(softindexes);
			manageService.getManageDAO().update(soft);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "软件保存失败");
			return "list";
		}
		
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> map = multipartRequest.getFileMap();
		Set<String> set = map.keySet();
		Iterator<String> ite = set.iterator();
		while(ite.hasNext()){
			String name = ite.next();
			MultipartFile file = map.get(name);
			if(file.isEmpty()){
				continue;
			}
			String suffix = validateFile(file);
			if(suffix == null){
				manageService.getManageDAO().delete(soft);
				model.put("success", false);
				model.put("msg", "上传附件后缀出现错误");
				return "list";
			}
			String code = Code.getCode();
			File mfile = saveFile(request, file, suffix,code,ProperiesReader.getInstence("config.properties").getStringValue("material.soft.url"));
			if(file == null){
				manageService.getManageDAO().delete(soft);
				model.put("msg", "文件保存失败！");
				model.put("success", false);
				return "list";
			}
			String[] arrayIds = name.split("_");
			if(arrayIds.length > 1){
				Phonearray ph = manageService.getManageDAO().findById(Phonearray.class, Integer.parseInt(arrayIds[1]));
				
				Attachment attachment = new Attachment();
				attachment.setCode(code);
				attachment.setName(shortName+"_"+ph.getPhonearrayName()+suffix);
				attachment.setUrl(ProperiesReader.getInstence("config.properties").getStringValue("material.soft.url")+code+suffix);
				attachment.setSoft(soft);
				
				Set attachandarraies = new HashSet();
				
				for(int i=1;i<arrayIds.length;i++){
					Attachandarray aaa = new Attachandarray();
					aaa.setAttachment(attachment);
					Phonearray phonearray = manageService.getManageDAO().findById(Phonearray.class, Integer.parseInt(arrayIds[i]));
					aaa.setPhonearray(phonearray);
					attachandarraies.add(aaa);
				}
				attachment.setAttachandarraies(attachandarraies);
				manageService.getManageDAO().save(attachment);
				//msoftService.addAttachment(attachment, request);
			}

			
		}
		model.put("success", true);
		model.put("msg", "修改成功！");
		if(session.getAttribute("user") != null){
			manageService.log(Logtype.SOFT, ((Users)session.getAttribute("user")).getNickName(),"修改软件信息,软件名称为："+softName);
		}
		return "list";
	}
	

	public String savePNG(HttpServletRequest request,ModelMap model,String url){
		File of = new File(request.getRealPath(url));
		String suffix = ImageUtil.validatePNG(of);
		if(suffix == null){
			model.put("success", false);
			model.put("msg", "未能找到文件");
			return null;
		}
		String code = Code.getCode();
		String picUrl = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+code+"."+suffix;
		boolean bl = FileUtil.copyFile(of, new File(request.getRealPath(picUrl)),false);
		if(bl){
			return picUrl;
		}
		model.put("success", false);
		model.put("msg", "复制文件出错");
		return null;
	}
	
	public String saveJPG(HttpServletRequest request,ModelMap model,String url){
		File of = new File(request.getRealPath(url));
		String suffix = ImageUtil.validateJPG(of);
		if(suffix == null){
			model.put("success", false);
			model.put("msg", "未能找到文件");
			return null;
		}
		String code = Code.getCode();
		String picUrl = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+code+"."+suffix;
		boolean bl = FileUtil.copyFile(of, new File(request.getRealPath(picUrl)),false);
		if(bl){
			return picUrl;
		}
		model.put("success", false);
		model.put("msg", "复制文件出错");
		return null;
	}

	private File saveFile(HttpServletRequest request, MultipartFile imgFile,String suffix,String code,String path){
		File file = null;  
        try {

        	file = new File(request.getRealPath(path)+"\\"+code+suffix);
        	if(file.exists()){
        		file.delete();	
        	}
			imgFile.transferTo(file);
			return file;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		return null;
	}
		
	private String validateFile(MultipartFile file) { 
		//判断flie的大小不能大于2000000
	    if(file.getSize()<0 ){
	    	return null;  
	    }           
	    String filename = file.getOriginalFilename();  
	    String extName = filename.substring(filename.lastIndexOf(".")).toLowerCase(); 
	   // System.out.println(extName);
	    
	    String[] suffexs = ProperiesReader.getInstence("config.properties").getStringValue("material.soft.suffix").split(",");
	    
	    for(int i = 0;i<suffexs.length;i++){
	    	if(extName.equals(suffexs[i])){
	    		return extName;
	    	}
	    }
	    return null;
	}
	
	
}
