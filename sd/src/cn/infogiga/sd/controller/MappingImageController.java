package cn.infogiga.sd.controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cindy.page.beanutils.MyBeanUtils;
import cindy.util.Code;
import cindy.util.ImageUtil;
import cindy.util.ProperiesReader;
import cn.infogiga.sd.dto.JsonListBean;
import cn.infogiga.sd.dto.JsonPicture;
import cn.infogiga.sd.pojo.Picture;
import cn.infogiga.sd.service.ManageService;

public class MappingImageController {
	
	ManageService manageService;
	
	
	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}

	@RequestMapping(value = "/image",params="list")
	public String getPictoreList(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start,@RequestParam("limit")Integer limit){
		List<JsonPicture> powerList = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByPage(Picture.class, start, limit), JsonPicture.class);
		int totalCount = manageService.getManageDAO().getCount(Picture.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,powerList);
		model.addAttribute("object", jsonListBean);
		return "list";
	}

	@RequestMapping(value = "/image",params="delete")
	public String deleteImage(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model
			,@RequestParam("pictureId")Integer pictureId){
		Picture picture = new Picture(pictureId);
		try {
			manageService.getManageDAO().delete(picture);
			model.put("success", true);
			model.put("msg", "删除成功");
		} catch (RuntimeException e) {
			model.put("success", false);
			model.put("msg", "删除失败");
			e.printStackTrace();
		}
		return "list";
	}
	
	
	@RequestMapping(value = "/image",params="upload")
	public String uploadPicture(HttpServletRequest request,
			HttpServletResponse response,ModelMap model){
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
		MultipartFile mFile  =  multipartRequest.getFile("picture");  
		String suffix = validateFile(mFile);
		if(suffix == null){
			model.put("msg", "格式不正确,或超过大小！");
			model.put("success", false);
			return "list";
		}
		String code = Code.getCode();
		File file = saveFile(request, mFile, suffix,code);
		Image image = getImage(file);
		Picture picture = insertPicture(image,code,suffix);
		JsonPicture jp = MyBeanUtils.copyProperties(picture, JsonPicture.class);
		model.put("msg", "上传成功！");
		model.put("success", true);
		model.put("url",jp.getUrl());
		model.put("pictureId",jp.getPictureId());
		model.put("width",jp.getWidth());
		model.put("height",jp.getHeight());
		model.put("code",jp.getCode());
		return "list";
	}

	@RequestMapping(value = "/image",params="cut")
	public String cutPicture(HttpServletRequest request,HttpServletResponse response,ModelMap model,
			@RequestParam("url")String url,
			@RequestParam("x")Integer x,
			@RequestParam("y")Integer y,
			@RequestParam("w")Integer w,
			@RequestParam("h")Integer h){

		File file = new File(request.getRealPath(url));
		String suffix = ImageUtil.validateFile(file);
		String code = Code.getCode();
		File f = ImageUtil.cut(x,y,w,h,request.getRealPath(url),request.getRealPath(ProperiesReader.getInstence("config.properties").getStringValue("upload.image.url"))+"\\"+code+"."+suffix,suffix);
		if(file != null){
			Image image = getImage(f);
			Picture picture = insertPicture(image,code,suffix);
			model.put("success", true);
			model.put("msg", "裁剪成功！");
			model.put("url",picture.getUrl());
			model.put("pictureId",picture.getPictureId());
			model.put("width",picture.getWidth());
			model.put("height",picture.getHeight());
			model.put("code",picture.getCode());
		}else{
			model.put("success", false);
			model.put("msg", "裁剪失败！");
		}
		return "list";
	}

	@RequestMapping(value = "/image")
	public String cutPicture(){
		return "ImageEditor2";
	}
	
	private String validateFile(MultipartFile file) { 
		//判断flie的大小不能大于2000000
	    if(file.getSize()<0 || file.getSize() > 2000000){
	    	return null;  
	    }           
	    String filename = file.getOriginalFilename();  
	    String extName = filename.substring(filename.lastIndexOf(".")).toLowerCase(); 
	    //file确定file的后缀名
	    if(extName.equals(".png") || extName.equals(".PNG")){  
	        return "png";  
	    }else if(extName.equals(".jpg") || extName.equals(".JPG")){  
	        return "jpg";  
	    }else if(extName.equals(".gif") || extName.equals(".GIF")){
	    	return "gif";  
	    }else{
	    	return null;
	    }  
	}

	
	
	private File saveFile(HttpServletRequest request, MultipartFile imgFile,String suffix,String code){
		File file = null;  
        try {

        	file = new File(request.getRealPath(ProperiesReader.getInstence("config.properties").getStringValue("upload.image.url"))+"\\"+code+"."+suffix);
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
	
	private Picture insertPicture(Image image,String code,String suffix){
		Picture picture = null;
		try {
			picture = new Picture();
			picture.setAddTime(new Date());
			picture.setCode(code);
			picture.setUrl(ProperiesReader.getInstence("config.properties").getStringValue("upload.image.url")+code+"."+suffix);
			picture.setWidth(image.getWidth(null));
			picture.setHeight(image.getHeight(null));
			manageService.getManageDAO().save(picture);
			return picture;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private Image getImage(File file){
		try {
			Image src = javax.imageio.ImageIO.read(file);
			return src;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
