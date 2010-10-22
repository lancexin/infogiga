package cn.infogiga.sd.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cindy.util.Code;
import cindy.util.ImageUtil;
import cindy.util.ProperiesReader;
import cn.infogiga.sd.pojo.Admin;
import cn.infogiga.sd.pojo.Bissinusshall;
import cn.infogiga.sd.pojo.City;
import cn.infogiga.sd.pojo.Download;
import cn.infogiga.sd.pojo.Employee;
import cn.infogiga.sd.pojo.Equipment;
import cn.infogiga.sd.pojo.Music;
import cn.infogiga.sd.pojo.Musicindex;
import cn.infogiga.sd.pojo.Musicman;
import cn.infogiga.sd.pojo.Musicmenu;
import cn.infogiga.sd.pojo.Phonebrand;
import cn.infogiga.sd.pojo.Phoneplatform;
import cn.infogiga.sd.pojo.Phonetype;
import cn.infogiga.sd.pojo.Power;
import cn.infogiga.sd.pojo.Province;
import cn.infogiga.sd.pojo.Soft;
import cn.infogiga.sd.pojo.Softattachment;
import cn.infogiga.sd.pojo.Softindex;
import cn.infogiga.sd.pojo.Softmenu;
import cn.infogiga.sd.pojo.Video;
import cn.infogiga.sd.pojo.Videoindex;
import cn.infogiga.sd.pojo.Videomenu;
import cn.infogiga.sd.service.ManageService;
import cn.infogiga.sd.service.PowerService;

public class MappingAddJsonController {
	ManageService manageService;
	
	PowerService powerService;

	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}

	public void setPowerService(PowerService powerService) {
		this.powerService = powerService;
	}
	
	@RequestMapping(value = "/add",params="power")
	public String addPower(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("powerName")String powerName,@RequestParam("powerValue")String powerValue,@RequestParam("status")Integer status){
		Power power = new Power();
		power.setPowerName(powerName);
		power.setPowerValue(powerValue);
		power.setStatus(status);
		try {
			manageService.getManageDAO().save(power);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}
	
	@RequestMapping(value = "/add",params="admin")
	public String addAdmin(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("nickName")String nickName,
			@RequestParam("userName")String userName,
			@RequestParam("passWord")String passWord,
			@RequestParam("powerId")Integer powerId,
			@RequestParam("status")Integer status){
		Admin admin = new Admin();
		admin.setNickName(nickName);
		admin.setUserName(userName);
		admin.setPassWord(passWord);
		admin.setPower(new Power(powerId));
		admin.setStatus(status);
		admin.setAddTime(new Date());
		try {
			manageService.getManageDAO().save(admin);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}
	
	@RequestMapping(value = "/add",params="hall")
	public String addHall(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("hallName")String hallName,
			@RequestParam("description")String description,
			@RequestParam("status")Integer status,
			@RequestParam("cityId")Integer cityId){
		Bissinusshall hall = new Bissinusshall();
		hall.setHallName(hallName);
		hall.setDescription(description);
		hall.setStatus(status);
		hall.setCity(new City(cityId));
		hall.setAddTime(new Date());
		try {
			manageService.getManageDAO().save(hall);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}
	
	@RequestMapping(value = "/add",params="equipment")
	public String addEquipment(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("equipmentName")String equipmentName,
			@RequestParam("mac")String mac,
			@RequestParam("status")Integer status,
			@RequestParam("hallId")Integer hallId){
		Equipment equipment = new Equipment();
		equipment.setAddTime(new Date());
		equipment.setBissinusshall(new Bissinusshall(hallId));
		equipment.setMac(mac);
		equipment.setEquipmentCode(cindy.util.Code.getCode(8));
		equipment.setStatus(status);
		equipment.setEquipmentName(equipmentName);
		try {
			manageService.getManageDAO().save(equipment);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/add",params="employee")
	public String addEmployee(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("nickName")String nickName,
			@RequestParam("userName")String userName,
			@RequestParam("passWord")String passWord,
			@RequestParam("hallId")Integer hallId,
			@RequestParam("powerId")Integer powerId,
			@RequestParam("status")Integer status){
		Employee employee = new Employee();
		employee.setNickName(nickName);
		employee.setUserName(userName);
		employee.setPassWord(passWord);
		employee.setBissinusshall(new Bissinusshall(hallId));
		employee.setPower(new Power(powerId));
		employee.setAddTime(new Date());
		employee.setStatus(status);
		try {
			manageService.getManageDAO().save(employee);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/add",params="province")
	public String addProvince(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("provinceName")String provinceName){
		Province province = new Province();
		province.setProvinceName(provinceName);
		try {
			manageService.getManageDAO().save(province);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/add",params="videomenu")
	public String addVideomenu(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("videomenuName")String videomenuName){
		
		try {
			int count =  manageService.getManageDAO().getCountByProperty(Videomenu.class, "videomenuName", videomenuName);
			if(count > 0){
				model.put("success", false);
				model.put("msg", "该菜单已经存在！");
				return "list";
			}
			Videomenu videomenu = new Videomenu();
			videomenu.setVideomenuName(videomenuName);
			manageService.getManageDAO().save(videomenu);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/add",params="city")
	public String addCity(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("cityName")String cityName,
			@RequestParam("provinceId")Integer provinceId){
		City city = new City();
		city.setCityName(cityName);
		city.setProvince(new Province(provinceId));
		try {
			manageService.getManageDAO().save(city);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}
	
	@RequestMapping(value = "/add",params="phonebrand")
	public String addPhonebrand(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("phonebrandName")String phonebrandName){
		Phonebrand phonebrand = new Phonebrand();
		phonebrand.setPhonebrandName(phonebrandName);
		phonebrand.setStatus(1);
		try {
			manageService.getManageDAO().save(phonebrand);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/add",params="phoneplatform")
	public String addPhoneplatform(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("platformName")String platformName){
		Phoneplatform phoneplatform = new Phoneplatform();
		phoneplatform.setPlatformName(platformName);
		try {
			manageService.getManageDAO().save(phoneplatform);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/add",params="softindex")
	public String addSoftindex(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("phonetypeId")Integer phonetypeId,@RequestParam("attachmentId")Integer attachmentId){
		
		Map<String,Object> properties = new HashMap<String,Object>();
		properties.put("phonetype.phonetypeId", phonetypeId);
		properties.put("softattachment.attachmentId", attachmentId);
		List<Softindex> list = manageService.getManageDAO().findByProperties(Softindex.class, properties);
		if(list != null && list.size() != 0){
			model.put("success", false);
			model.put("msg", "该型号已经存在！");
			return "list";
		}
		Softindex softindex = new Softindex();
		Phonetype phonetype = new Phonetype();
		phonetype.setPhonetypeId(phonetypeId);
		softindex.setPhonetype(phonetype);
		Softattachment softattachment = new Softattachment();
		softattachment.setAttachmentId(attachmentId);
		softindex.setSoftattachment(softattachment);
		try {
			manageService.getManageDAO().save(softindex);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/add",params="softmenu")
	public String addSoftmenu(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("softmenuName")String softmenuName){
		Softmenu softmenu = new Softmenu();
		softmenu.setSoftmenuName(softmenuName);
		try {
			manageService.getManageDAO().save(softmenu);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}
	
	@RequestMapping(value = "/add",params="musicmenu")
	public String addMusicmenu(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("musicmenuName")String musicmenuName){
		Musicmenu musicmenu = new Musicmenu();
		musicmenu.setIsLeaf(0);
		musicmenu.setMusicmenuName(musicmenuName);
		
		try {
			manageService.getManageDAO().save(musicmenu);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}
	
	
	@RequestMapping(value = "/add",params="leafmusicmenu")
	public String addLeafmusicmenu(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("musicmenuName")String musicmenuName,
			@RequestParam("fathermusicmenuId")Integer fathermusicmenuId){
		Musicmenu musicmenu = new Musicmenu();
	
		musicmenu.setMusicmenuName(musicmenuName);
		musicmenu.setIsLeaf(1);
		
		Musicmenu fathermusicmenu = new Musicmenu();
		fathermusicmenu.setMusicmenuId(fathermusicmenuId);
		musicmenu.setMusicmenu(fathermusicmenu);
		try {
			manageService.getManageDAO().save(musicmenu);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}
	
	@RequestMapping(value = "/add",params="musicman")
	public String addmusicman(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("musicmanName")String musicmanName){
		try {
			int count =  manageService.getManageDAO().getCountByProperty(Musicman.class, "musicmanName", musicmanName);
			if(count >0 ){
				model.put("success", false);
				model.put("msg", "该歌手已经存在！");
				return "list";
			}
			Musicman musicman = new Musicman();
			musicman.setMusicmanName(musicmanName);
			manageService.getManageDAO().save(musicman);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/add",params="videoindex")
	public String addvideoindex(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("videoId")Integer videoId,
			@RequestParam("videomenuId")Integer videomenuId){
		try {
			Map<String,Object> properties = new HashMap<String,Object>();
			properties.put("video.videoId", videoId);
			properties.put("videomenu.videomenuId", videomenuId);
			int count =  manageService.getManageDAO().getCountByProperties(Videoindex.class, properties);
			if(count >0 ){
				model.put("success", false);
				model.put("msg", "该菜单已经存在！");
				return "list";
			}
			Videoindex videoindex = new Videoindex();
			
			Video video = new Video();
			video.setVideoId(videoId);
			Videomenu videomenu = new Videomenu();
			videomenu.setVideomenuId(videomenuId);
			videoindex.setVideo(video);
			videoindex.setVideomenu(videomenu);
			manageService.getManageDAO().save(videoindex);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/add",params="musicindex")
	public String addmusicindex(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("musicId")Integer musicId,
			@RequestParam("musicmenuId")Integer musicmenuId){
		try {
			Map<String,Object> properties = new HashMap<String,Object>();
			properties.put("music.musicId", musicId);
			properties.put("musicmenu.musicmenuId", musicmenuId);
			int count =  manageService.getManageDAO().getCountByProperties(Musicindex.class, properties);
			if(count >0 ){
				model.put("success", false);
				model.put("msg", "该菜单已经存在！");
				return "list";
			}
			Musicindex musicindex = new Musicindex();
			
			Music music = new Music();
			music.setMusicId(musicId);
			Musicmenu musicmenu = new Musicmenu();
			musicmenu.setMusicmenuId(musicmenuId);
			musicindex.setMusic(music);
			musicindex.setMusicmenu(musicmenu);
			manageService.getManageDAO().save(musicindex);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/add",params="phonetype")
	public String addPhonetype(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("pic")String pic,
			@RequestParam("platformId")Integer platformId,
			@RequestParam("phonebrandId")Integer phonebrandId,
			@RequestParam("phonetypeName")String phonetypeName,
			@RequestParam("status")Integer status){
		if(pic == null || pic.length() <= 0){
			model.put("success", false);
			model.put("msg", "必须选择图片");
			return "list";
		}
		File of = new File(request.getRealPath(pic));
		String suffix = ImageUtil.validateFile(of);
		if(suffix == null){
			model.put("success", false);
			model.put("msg", "未能找到文件");
			return "list";
		}
		String code = Code.getCode();
		String picUrl = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+code+"."+suffix;
		boolean bl = ImageUtil.copyFile(of, new File(request.getRealPath(picUrl)),false);
		if(!bl){
			model.put("success", false);
			model.put("msg", "文件复制出现错误");
			return "list";
		}
		Phonetype phonetype = new Phonetype();
		phonetype.setPhonebrand(new Phonebrand(phonebrandId));
		phonetype.setPhonetypeName(phonetypeName);
		phonetype.setPhoneplatform(new Phoneplatform(platformId));
		phonetype.setPic(picUrl);
		phonetype.setStatus(status);
		try {
			manageService.getManageDAO().save(phonetype);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}
	
	@RequestMapping(value = "/add",params="softattachment")
	public String addPhoneAttachment(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model
			,@RequestParam("attachmentName")String attachmentName,
			@RequestParam("softId")Integer softId
			){
	//	String attachmentName = request.getParameter("attachmentName");
	//	Integer softId = Integer.parseInt(request.getParameter("softId"));
		System.out.println(attachmentName);
		System.out.println(softId);
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
		MultipartFile mFile  =  multipartRequest.getFile("upload");
		String suffix = validateFile(mFile);
		if(suffix == null){
			model.put("msg", "格式不正确,或超过大小！");
			model.put("success", false);
			return "list";
		}
		String code = Code.getCode();
		File file = saveFile(request, mFile, suffix,code,ProperiesReader.getInstence("config.properties").getStringValue("material.soft.url"));
		if(file == null){
			model.put("msg", "文件保存失败！");
			model.put("success", false);
			return "list";
		}
		try {
			Download download = new Download();
			download.setDownloadCode(code);
			download.setUrl(ProperiesReader.getInstence("config.properties").getStringValue("material.soft.url")+code+"."+suffix);
			manageService.getManageDAO().save(download);
			Softattachment attachment = new Softattachment();
			attachment.setAttachmentName(attachmentName);
			Soft s = new Soft();
			s.setSoftId(softId);
			attachment.setSoft(s);
			attachment.setDownload(download);
			manageService.getManageDAO().save(attachment);
			model.put("msg", "上传成功！");
			model.put("success", true);
		} catch (RuntimeException e) {
			model.put("msg", "上传失败！");
			model.put("success", false);
			e.printStackTrace();
		}
		return "list";  
	}
	
	private String validateFile(MultipartFile file) { 
		//判断flie的大小不能大于2000000
	    if(file.getSize()<0 || file.getSize() > ProperiesReader.getInstence("config.properties").getIntegerValue("upload.maxsize")){
	    	return null;  
	    }           
	    String filename = file.getOriginalFilename();  
	    String extName = filename.substring(filename.lastIndexOf(".")).toLowerCase(); 
	    System.out.println(extName);
	    //file确定file的后缀名
	    if(extName.equals(".apk") || extName.equals(".APK")){  
	        return "apk";  
	    }else if(extName.equals(".cab") || extName.equals(".CAB")){  
	        return "cab";  
	    }else if(extName.equals(".sis") || extName.equals(".SIS")){
	    	return "sis";  
	    }else if(extName.equals(".sisx") || extName.equals(".SISX")){
	    	return "sisx";  
	    }else if(extName.equals(".rar") || extName.equals(".RAR")){
	    	return "zip";  
	    }else{
	    	return null;
	    }  
	}

	private String validateMusicFile(MultipartFile file) { 
	    if(file.getSize()<0 || file.getSize() > ProperiesReader.getInstence("config.properties").getIntegerValue("upload.maxsize")){
	    	return null;  
	    }           
	    String filename = file.getOriginalFilename();  
	    String extName = filename.substring(filename.lastIndexOf(".")).toLowerCase(); 

	    //file确定file的后缀名
	    if(extName.equals(".mp3") || extName.equals(".MP3")){  
	        return "mp3";  
	    }else if(extName.equals(".wma") || extName.equals(".WMA")){  
	        return "wma";  
	    }else{
	    	return null;
	    }  
	}

	private String validateVideoFile(MultipartFile file) {           
	    String filename = file.getOriginalFilename();  
	    String extName = filename.substring(filename.lastIndexOf(".")).toLowerCase(); 

	    //file确定file的后缀名
	    if(extName.equals(".rmvb") || extName.equals(".RMVB")){  
	        return "rmvb";  
	    }else if(extName.equals(".mp4") || extName.equals(".MP4")){  
	        return "mp4";  
	    }else if(extName.equals(".avi") || extName.equals(".AVI")){  
	        return "avi";  
	    }else{
	    	return null;
	    }  
	}
	
	private File saveFile(HttpServletRequest request, MultipartFile imgFile,String suffix,String code,String path){
		File file = null;  
        try {

        	file = new File(request.getRealPath(path)+"\\"+code+"."+suffix);
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

	

	@RequestMapping(value = "/add",params="soft")
	public String addSoft(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("softmenuId")Integer softmenuId,
			@RequestParam("softName")String softName,
			@RequestParam("description")String description,
			@RequestParam("pic1")String pic1,
			@RequestParam("pic2")String pic2,
			@RequestParam("status")Integer status){
		File of1 = new File(request.getRealPath(pic1));
		String suffix1 = ImageUtil.validateFile(of1);
		if(suffix1 == null){
			model.put("success", false);
			model.put("msg", "未能找到文件");
			return "list";
		}
		String code1 = Code.getCode();
		String picUrl1 = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+code1+"."+suffix1;
		boolean bl = ImageUtil.copyFile(of1, new File(request.getRealPath(picUrl1)),false);
		if(!bl){
			model.put("success", false);
			model.put("msg", "文件复制出现错误");
			return "list";
		}
		File of2 = new File(request.getRealPath(pic2));
		String suffix2 = ImageUtil.validateFile(of2);
		if(suffix2 == null){
			model.put("success", false);
			model.put("msg", "未能找到文件");
			return "list";
		}
		String code2 = Code.getCode();
		String picUrl2 = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+code2+"."+suffix2;
		bl = ImageUtil.copyFile(of2, new File(request.getRealPath(picUrl2)),false);
		
		if(!bl){
			model.put("success", false);
			model.put("msg", "文件复制出现错误");
			return "list";
		}
		Soft soft = new Soft();
		soft.setAddTime(new Date());
		soft.setDescription(description);
		soft.setDownloadCount(0);
		soft.setPic1(picUrl1);
		soft.setPic2(picUrl2);
		soft.setStatus(1);
		soft.setSoftmenu(new Softmenu(softmenuId));
		soft.setSoftName(softName);
		try {
			manageService.getManageDAO().save(soft);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}
	
	@RequestMapping(value = "/add",params="video")
	public String addVideo(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model
			,
			@RequestParam("videoName")String videoName,
			@RequestParam("description")String description,
			@RequestParam("pic1")String pic1,
			@RequestParam("pic2")String pic2){
		/*String videoName = request.getParameter("videoName");
		String description = request.getParameter("description");
		String pic1 = request.getParameter("pic1");
		String pic2 = request.getParameter("pic2");
		System.out.println(videoName);
		System.out.println(description);
		System.out.println(pic1);
		System.out.println(pic2);*/
		/**视频文件上传**/
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
		MultipartFile mFile  =  multipartRequest.getFile("upload");
		String suffix = validateVideoFile(mFile);
		if(suffix == null){
			model.put("msg", "格式不正确,或超过大小！");
			model.put("success", false);
			return "list";
		}
		String code = Code.getCode();
		File file = saveFile(request, mFile, suffix,code,ProperiesReader.getInstence("config.properties").getStringValue("material.video.url"));
		if(file == null){
			model.put("msg", "文件保存失败！");
			model.put("success", false);
			return "list";
		}
		/**保存图片**/
		File of1 = new File(request.getRealPath(pic1));
		String suffix1 = ImageUtil.validateFile(of1);
		if(suffix1 == null){
			model.put("success", false);
			model.put("msg", "未能找到文件");
			return "list";
		}
		File of2 = new File(request.getRealPath(pic2));
		String suffix2 = ImageUtil.validateFile(of2);
		if(suffix2 == null){
			model.put("success", false);
			model.put("msg", "未能找到文件");
			return "list";
		}
		String code1 = Code.getCode();
		String picUrl1 = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+code1+"."+suffix1;
		boolean bl = ImageUtil.copyFile(of1, new File(request.getRealPath(picUrl1)),false);
		if(!bl){
			model.put("success", false);
			model.put("msg", "文件复制出现错误");
			return "list";
		}
		
		String code2 = Code.getCode();
		String picUrl2 = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+code2+"."+suffix2;
		bl = ImageUtil.copyFile(of2, new File(request.getRealPath(picUrl2)),false);
		
		if(!bl){
			model.put("success", false);
			model.put("msg", "文件复制出现错误");
			return "list";
		}
		
		try {
			Download download = new Download();
			download.setDownloadCode(code);
			download.setUrl(ProperiesReader.getInstence("config.properties").getStringValue("material.video.url")+code+"."+suffix);
			manageService.getManageDAO().save(download);
			
			Video video = new Video();
			video.setVideoName(videoName);
			video.setDescription(description);
			video.setPic1(picUrl1);
			video.setPic2(picUrl2);
			video.setStatus(1);
			video.setAddTime(new Date());
			video.setDownload(download);
			manageService.getManageDAO().save(video);
			model.put("success", true);
			model.put("msg", "添加成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}
	

	@RequestMapping(value = "/add",params="music")
	public String addMusic(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("musicmanId")Integer musicmanId,
			@RequestParam("musicName")String musicName,
			@RequestParam("description")String description){
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
		MultipartFile mFile  =  multipartRequest.getFile("upload");
		String suffix = validateMusicFile(mFile);
		if(suffix == null){
			model.put("msg", "格式不正确,或超过大小！");
			model.put("success", false);
			return "list";
		}
		String code = Code.getCode();
		File file = saveFile(request, mFile, suffix,code,ProperiesReader.getInstence("config.properties").getStringValue("material.music.url"));
		if(file == null){
			model.put("msg", "文件保存失败！");
			model.put("success", false);
			return "list";
		}
		try {
			Download download = new Download();
			download.setDownloadCode(code);
			download.setUrl(ProperiesReader.getInstence("config.properties").getStringValue("material.music.url")+code+"."+suffix);
			manageService.getManageDAO().save(download);
			Music music = new Music();
			music.setAddTime(new Date());
			music.setDescription(description);
			music.setMusicName(musicName);
			music.setStatus(1);
			music.setDownloadCount(0);
			
			Musicman musicman = new Musicman();
			musicman.setMusicmanId(musicmanId);
			music.setMusicman(musicman);
			
			music.setDownload(download);
			
			manageService.getManageDAO().save(music);
			model.put("msg", "上传成功！");
			model.put("success", true);
		} catch (RuntimeException e) {
			model.put("msg", "上传失败！");
			model.put("success", false);
			e.printStackTrace();
		}
		return "list";  
	}
}
