package cn.infogiga.szqb.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cindy.page.beanutils.MyBeanUtils;
import cindy.util.FileUtil;
import cindy.util.ProperiesReader;
import cn.infogiga.szqb.pdf.PdfUtil;
import cn.infogiga.szqb.pojo.Page;
import cn.infogiga.szqb.pojo.Periodical;
import cn.infogiga.szqb.vo.JsonListBean;
import cn.infogiga.szqb.vo.JsonObjectBean;
import cn.infogiga.szqb.vo.JsonPage;
import cn.infogiga.szqb.web.service.ManageService;

@Controller
public class PageController {
	
	@Autowired
	ManageService manageService;
	
	@RequestMapping(value = "/page")
	public String list(HttpServletRequest request,HttpServletResponse response,ModelMap model,
			@RequestParam("periodicalId")Integer periodicalId){
		
		List<JsonPage> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().findByProperty(Page.class, "periodical.id",periodicalId,null), JsonPage.class);
		int totalCount = manageService.getManageDAO().getCountByProperty(Page.class, "periodical.id",periodicalId);
		
		JsonListBean jsonListBean = new JsonListBean(totalCount,list,true,"addTime");
		model.addAttribute("object", jsonListBean);
		return "list";
	}
	
	@RequestMapping(value = "/page",params="add")
	public String uploadPDF(HttpServletRequest request,
			HttpServletResponse response,ModelMap model,
			@RequestParam("periodicalId")Integer periodicalId) throws IOException{
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
		Periodical periodical = manageService.getManageDAO().findById(Periodical.class, periodicalId);
		int count = manageService.getManageDAO().getCountByProperty(Page.class, "periodical.id", periodicalId);
		String shortName = periodical.getShortName()+"_"+(count+1)+"_";
		
		MultipartFile file = multipartRequest.getFile("files");
		if(file == null){
			model.put("success", false);
			model.put("msg", "未能找到上传文件");
			return "list";
		}
		String suffex = validateFile(file);
		String originalUrl = ProperiesReader.getInstence("config.properties").getStringValue("pdf.url")+shortName+suffex;
		if(suffex == null){
			model.put("success", false);
			model.put("msg", "文件类型不正确");
			return "list";
		}
		File mfile = saveFile(request, file, suffex, shortName,ProperiesReader.getInstence("config.properties").getStringValue("pdf.url"));
		if(mfile == null){
			model.put("msg", "文件保存失败！");
			model.put("success", false);
			return "list";
		}
		Page page = new Page();
		page.setAddTime(new Date());
		page.setOriginalUrl(originalUrl);
		page.setShortName(shortName);
		page.setPeriodical(periodical);
		String imgUrl = ProperiesReader.getInstence("config.properties").getStringValue("image.url")+shortName+"00001.png";
		page.setImgUrl(imgUrl);
		page.setStatus(1);
		try {
			PdfUtil.pdf2Image(request.getRealPath(originalUrl),request.getRealPath(ProperiesReader.getInstence("config.properties").getStringValue("image.url")),8,100);
			manageService.getManageDAO().save(page);
			JsonPage jpage = MyBeanUtils.copyProperties(page, JsonPage.class);
			JsonObjectBean ob = new JsonObjectBean();
			ob.setDate(jpage);
			ob.setMsg("添加成功");
			ob.setSuccess(true);
			model.addAttribute("object", ob);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "添加失败,请联系管理员！");
		}
		return "list";
		
	}
	
	@RequestMapping(value = "/page",params="delete")
	public String deletePDF(HttpServletRequest request,
			HttpServletResponse response,ModelMap model,
			@RequestParam("dId")Integer dId) throws IOException{
		Periodical periodical = manageService.getManageDAO().findById(Periodical.class, dId);
		if(periodical == null){
			model.put("success", false);
			model.put("msg", "未找到相关文件！");
			return "list";
		}
		Set pages = periodical.getPages();
		Iterator ite = pages.iterator();
		Page page;
		while(ite.hasNext()){
			page = (Page) ite.next();
			File imgFile = new File(request.getRealPath(page.getImgUrl()));
			File pdfFile = new File(request.getRealPath(page.getOriginalUrl()));
			FileUtil.delete(imgFile);
			FileUtil.delete(pdfFile);
			manageService.getManageDAO().delete(page);
		}
		periodical.setStatus(0);
		manageService.getManageDAO().update(periodical);
		model.put("success", true);
		model.put("msg", "删除成功！");
		return "list";
	}
	
	private File saveFile(HttpServletRequest request, MultipartFile imgFile,String suffix,String name,String path){
		File file = null;  
        try {
        	file = new File(request.getRealPath(path)+"\\"+name+suffix);
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

	    String[] suffexs = ProperiesReader.getInstence("config.properties").getStringValue("suffix").split(",");
	    for(int i = 0;i<suffexs.length;i++){
	    	if(extName.equals(suffexs[i])){
	    		return extName;
	    	}
	    }
	    return null;
	}
}
