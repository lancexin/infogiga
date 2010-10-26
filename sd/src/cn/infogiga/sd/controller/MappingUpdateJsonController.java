package cn.infogiga.sd.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cindy.util.Code;
import cindy.util.DateUtil;
import cindy.util.ImageUtil;
import cindy.util.ProperiesReader;
import cn.infogiga.sd.pojo.Admin;
import cn.infogiga.sd.pojo.Bissinusshall;
import cn.infogiga.sd.pojo.City;
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

public class MappingUpdateJsonController {

	ManageService manageService;
	
	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}

	@RequestMapping(value = "/update",params="admin")
	public String updateAdmin(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("adminId")Integer adminId,
			@RequestParam("nickName")String nickName,
			@RequestParam("userName")String userName,
			@RequestParam("passWord")String passWord,
			@RequestParam("powerId")Integer powerId,
			@RequestParam("addTime")String addTime,
			@RequestParam("status")Integer status){
		Admin admin = new Admin();
		admin.setUserId(adminId);
		admin.setNickName(nickName);
		admin.setUserName(userName);
		admin.setPassWord(passWord);
		admin.setPower(new Power(powerId));
		admin.setStatus(status);
		admin.setAddTime(DateUtil.stringToDate(addTime, DateUtil.NOW_TIME));
		try {
			manageService.getManageDAO().update(admin);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
		}
		return "list";
	}
	
	@RequestMapping(value = "/update",params="power")
	public String updatePower(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("powerId")Integer powerId,
			@RequestParam("powerValue")String powerValue,
			@RequestParam("powerName")String powerName,
			@RequestParam("status")Integer status){
		Power power = new Power();
		power.setPowerId(powerId);
		power.setPowerName(powerName);
		power.setPowerValue(powerValue);
		power.setStatus(status);
		try {
			manageService.getManageDAO().update(power);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
		}
		return "list";
	}
	
	@RequestMapping(value = "/update",params="hall")
	public String updateHall(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("hallId")Integer hallId,
			@RequestParam("hallName")String hallName,
			@RequestParam("description")String description,
			@RequestParam("status")Integer status,
			@RequestParam("addTime")String addTime,
			@RequestParam("cityId")Integer cityId){
		Bissinusshall hall = new Bissinusshall();
		hall.setHallId(hallId);
		hall.setHallName(hallName);
		hall.setDescription(description);
		hall.setStatus(status);
		hall.setCity(new City(cityId));
		hall.setAddTime(DateUtil.stringToDate(addTime, DateUtil.NOW_TIME));
		try {
			manageService.getManageDAO().update(hall);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/update",params="equipment")
	public String updateEquipment(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("equipmentId")Integer equipmentId,
			@RequestParam("equipmentCode")String equipmentCode,
			@RequestParam("equipmentName")String equipmentName,
			@RequestParam("addTime")String addTime,
			@RequestParam("mac")String mac,
			@RequestParam("status")Integer status,
			@RequestParam("hallId")Integer hallId){
		Equipment equipment = new Equipment();
		equipment.setEquipmentId(equipmentId);
		Bissinusshall hall = new Bissinusshall();
		hall.setHallId(hallId);
		equipment.setBissinusshall(hall);
		equipment.setMac(mac);
		equipment.setEquipmentCode(equipmentCode);
		equipment.setStatus(status);
		equipment.setEquipmentName(equipmentName);
		equipment.setAddTime(DateUtil.stringToDate(addTime, DateUtil.NOW_TIME));
		try {
			manageService.getManageDAO().update(equipment);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/update",params="employee")
	public String updateEmployee(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("employeeId")Integer employeeId,
			@RequestParam("nickName")String nickName,
			@RequestParam("userName")String userName,
			@RequestParam("passWord")String passWord,
			@RequestParam("addTime")String addTime,
			@RequestParam("hallId")Integer hallId,
			@RequestParam("powerId")Integer powerId,
			@RequestParam("status")Integer status){
		Employee employee = new Employee();
		employee.setUserId(employeeId);
		employee.setNickName(nickName);
		employee.setUserName(userName);
		employee.setPassWord(passWord);
		Bissinusshall bissinusshall = new Bissinusshall();
		bissinusshall.setHallId(hallId);
		employee.setBissinusshall(bissinusshall);
		employee.setPower(new Power(powerId));
		employee.setStatus(status);
		employee.setAddTime(DateUtil.stringToDate(addTime, DateUtil.NOW_TIME));
		try {
			manageService.getManageDAO().update(employee);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
		}
		return "list";
	}
	
	@RequestMapping(value = "/update",params="province")
	public String updateProvince(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("provinceId")Integer provinceId,
			@RequestParam("provinceName")String provinceName){
		Province province = new Province(provinceId);
		province.setProvinceName(provinceName);
		try {
			manageService.getManageDAO().update(province);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/update",params="city")
	public String updateCity(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("cityId")Integer cityId,
			@RequestParam("provinceId")Integer provinceId,
			@RequestParam("cityName")String cityName){
		City city = new City();
		city.setCityId(cityId);
		city.setCityName(cityName);
		city.setProvince(new Province(provinceId));
		try {
			manageService.getManageDAO().update(city);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/update",params="phonebrand")
	public String updatePhonebrand(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("phonebrandId")Integer phonebrandId,
			@RequestParam("phonebrandName")String phonebrandName){
		Phonebrand phonebrand = new Phonebrand(phonebrandId);
		
		phonebrand.setPhonebrandName(phonebrandName);
		phonebrand.setStatus(1);
		try {
			manageService.getManageDAO().update(phonebrand);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/update",params="phoneplatform")
	public String updatePhoneplatform(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("platformId")Integer platformId,
			@RequestParam("platformName")String platformName){
		Phoneplatform phoneplatform = new Phoneplatform(platformId);
		phoneplatform.setPlatformName(platformName);
		try {
			manageService.getManageDAO().update(phoneplatform);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}
	

	@RequestMapping(value = "/update",params="videomenu")
	public String updateVideomenu(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("videomenuId")Integer videomenuId,
			@RequestParam("videomenuName")String videomenuName){
		Videomenu videomenu = new Videomenu();
		videomenu.setVideomenuId(videomenuId);
		videomenu.setVideomenuName(videomenuName);
		try {
			manageService.getManageDAO().update(videomenu);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}
	

	@RequestMapping(value = "/update",params="phonetype")
	public String updatePhonetype(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("phonetypeId")Integer phonetypeId,
			@RequestParam("platformId")Integer platformId,
			@RequestParam("pic")String pic,
			@RequestParam("phonebrandId")Integer phonebrandId,
			@RequestParam("phonetypeName")String phonetypeName,
			@RequestParam("status")Integer status){
		
		File of = new File(request.getRealPath(pic));
		if(!of.exists()){
			model.put("success", false);
			model.put("msg", "未能找到文件");
			return "list";
		}
		String picUrl = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+of.getName();
		String insertUrl = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+Code.getCode()+"."+ImageUtil.validateFile(of);
		Phonetype phonetype = manageService.getManageDAO().findById(Phonetype.class, phonetypeId);
		if(!phonetype.getPic().equals(picUrl)){//如果图片不一样
			//将新图片复制到素材区
			boolean bl = ImageUtil.copyFile(of, new File(request.getRealPath(insertUrl)),false);
			if(!bl){
				model.put("success", false);
				model.put("msg", "文件复制出现错误");
				return "list";
			}
			//删除原素材图片
			ImageUtil.delete(new File(request.getRealPath(phonetype.getPic())));
			phonetype.setPic(insertUrl);
		}
		phonetype.setPhonebrand(new Phonebrand(phonebrandId));
		phonetype.setPhonetypeName(phonetypeName);
		phonetype.setPhoneplatform(new Phoneplatform(platformId));
		
		phonetype.setStatus(status);
		try {
			manageService.getManageDAO().save(phonetype);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
		}
		return "list";
	}
	

	@RequestMapping(value = "/update",params="soft")
	public String updateSoft(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("softId")Integer softId,
			@RequestParam("softmenuId")Integer softmenuId,
			@RequestParam("softName")String softName,
			@RequestParam("addTime")String addTime,
			@RequestParam("description")String description,
			@RequestParam("pic1")String pic1,
			@RequestParam("pic2")String pic2,
			@RequestParam("status")Integer status){
		
		File of1 = new File(request.getRealPath(pic1));
		File of2 = new File(request.getRealPath(pic2));
		if(!of1.exists() || !of2.exists()){
			model.put("success", false);
			model.put("msg", "未能找到文件");
			return "list";
		}
		String picUrl1 = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+of1.getName();
		String picUrl2 = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+of2.getName();
		
		String insertUrl1 = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+Code.getCode()+"."+ImageUtil.validateFile(of1);
		String insertUrl2 = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+Code.getCode()+"."+ImageUtil.validateFile(of2);
		
		System.out.println(picUrl1);
		System.out.println(picUrl2);
		System.out.println(insertUrl1);
		System.out.println(insertUrl2);
		Soft soft = manageService.getManageDAO().findById(Soft.class, softId);
		if(!soft.getPic1().equals(picUrl1)){//如果图片不一样
			//将新图片复制到素材区
			boolean bl = ImageUtil.copyFile(of1, new File(request.getRealPath(insertUrl1)),false);
			if(!bl){
				model.put("success", false);
				model.put("msg", "文件复制出现错误");
				return "list";
			}
			//删除原素材图片
			ImageUtil.delete(new File(request.getRealPath(soft.getPic1())));
			soft.setPic1(insertUrl1);
		}
		if(!soft.getPic2().equals(picUrl2)){//如果图片不一样
			//将新图片复制到素材区
			boolean bl = ImageUtil.copyFile(of2, new File(request.getRealPath(insertUrl2)),false);
			if(!bl){
				model.put("success", false);
				model.put("msg", "文件复制出现错误");
				return "list";
			}
			//删除原素材图片
			ImageUtil.delete(new File(request.getRealPath(soft.getPic2())));
			soft.setPic2(insertUrl2);
		}
		
		soft.setAddTime(DateUtil.stringToDate(addTime, DateUtil.NOW_TIME));
		soft.setDescription(description);
		
		
		soft.setSoftmenu(new Softmenu(softmenuId));
		soft.setSoftName(softName);
		try {
			manageService.getManageDAO().update(soft);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
		}
		return "list";
	}
	

	@RequestMapping(value = "/update",params="video")
	public String updateVideo(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("videoId")Integer videoId,
			@RequestParam("videoName")String videoName,
			@RequestParam("description")String description,
			@RequestParam("pic1")String pic1,
			@RequestParam("pic2")String pic2){
		
		File of1 = new File(request.getRealPath(pic1));
		File of2 = new File(request.getRealPath(pic2));
		if(!of1.exists() || !of2.exists()){
			model.put("success", false);
			model.put("msg", "未能找到文件");
			return "list";
		}
		String picUrl1 = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+of1.getName();
		String picUrl2 = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+of2.getName();
		
		String insertUrl1 = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+Code.getCode()+"."+ImageUtil.validateFile(of1);
		String insertUrl2 = ProperiesReader.getInstence("config.properties").getStringValue("material.image.url")+Code.getCode()+"."+ImageUtil.validateFile(of2);
		
		//System.out.println("picUrl1:"+picUrl1);
		//System.out.println("picUrl2:"+picUrl2);
		//System.out.println("insertUrl1:"+insertUrl1);
		//System.out.println("insertUrl2:"+insertUrl2);
		Video video = manageService.getManageDAO().findById(Video.class, videoId);
		//System.out.println("video.getPic1():"+video.getPic1());
		//System.out.println("video.getPic2():"+video.getPic2());
		if(!video.getPic1().equals(picUrl1)){//如果图片不一样
			//将新图片复制到素材区
			boolean bl = ImageUtil.copyFile(of1, new File(request.getRealPath(insertUrl1)),false);
			if(!bl){
				model.put("success", false);
				model.put("msg", "文件复制出现错误");
				return "list";
			}
			//删除原素材图片
			ImageUtil.delete(new File(request.getRealPath(video.getPic1())));
			video.setPic1(insertUrl1);
		}
		if(!video.getPic2().equals(picUrl2)){//如果图片不一样
			//将新图片复制到素材区
			boolean bl = ImageUtil.copyFile(of2, new File(request.getRealPath(insertUrl2)),false);
			if(!bl){
				model.put("success", false);
				model.put("msg", "文件复制出现错误");
				return "list";
			}
			//删除原素材图片
			ImageUtil.delete(new File(request.getRealPath(video.getPic2())));
			video.setPic2(insertUrl2);
		}
		
		video.setDescription(description);
		video.setVideoName(videoName);
		try {
			manageService.getManageDAO().update(video);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
		}
		return "list";
	}
	

	@RequestMapping(value = "/update",params="softmenu")
	public String updateSoftmenu(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("softmenuId")Integer softmenuId,
			@RequestParam("softmenuName")String softmenuName){
		Softmenu softmenu = new Softmenu();
		softmenu.setSoftmenuId(softmenuId);
		softmenu.setSoftmenuName(softmenuName);
		try {
			manageService.getManageDAO().update(softmenu);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
		}
		return "list";
	}
	
	@RequestMapping(value = "/update",params="musicmenu")
	public String updateMusicmenu(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("musicmenuId")Integer musicmenuId,
			@RequestParam("musicmenuName")String musicmenuName){
		Musicmenu musicmenu = manageService.getManageDAO().findById(Musicmenu.class, musicmenuId);
		musicmenu.setMusicmenuName(musicmenuName);
		try {
			manageService.getManageDAO().update(musicmenu);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/update",params="softattachment")
	public String updateSoftattachment(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("attachmentId")Integer attachmentId,
			@RequestParam("softId")Integer softId,
			@RequestParam("attachmentName")String attachmentName){
		Softattachment attachment = manageService.getManageDAO().findById(Softattachment.class, attachmentId);
		Soft soft = new Soft();
		soft.setSoftId(softId);
		attachment.setSoft(soft);
		attachment.setAttachmentName(attachmentName);
		try {
			manageService.getManageDAO().update(attachment);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/update",params="music")
	public String updateMusic(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("musicId")Integer musicId,
			@RequestParam("description")String description,
			@RequestParam("musicName")String musicName){
		Music music = manageService.getManageDAO().findById(Music.class, musicId);
		music.setMusicName(musicName);
		music.setDescription(description);
		try {
			manageService.getManageDAO().update(music);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/update",params="musicindex")
	public String updatemusicindex(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("indexId")Integer indexId,
			@RequestParam("musicmenuId")Integer musicmenuId){
		try {
			
			Musicindex musicindex = manageService.getManageDAO().findById(Musicindex.class, indexId);
			Map<String,Object> properties = new HashMap<String,Object>();
			properties.put("music.musicId", musicindex.getMusic().getMusicId());
			properties.put("musicmenu.musicmenuId", musicmenuId);
			int count =  manageService.getManageDAO().getCountByProperties(Musicindex.class, properties);
			if(count >0 ){
				model.put("success", false);
				model.put("msg", "该菜单已经存在！");
				return "list";
			}
			Musicmenu musicmenu = new Musicmenu();
			musicmenu.setMusicmenuId(musicmenuId);
			musicindex.setMusicmenu(musicmenu);
			
			manageService.getManageDAO().update(musicindex);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/update",params="videoindex")
	public String updatevideoindex(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("indexId")Integer indexId,
			@RequestParam("videomenuId")Integer videomenuId){
		try {
			
			Videoindex videoindex = manageService.getManageDAO().findById(Videoindex.class, indexId);
			Map<String,Object> properties = new HashMap<String,Object>();
			properties.put("video.videoId", videoindex.getVideo().getVideoId());
			properties.put("videomenu.videomenuId", videomenuId);
			int count =  manageService.getManageDAO().getCountByProperties(Videoindex.class, properties);
			if(count >0 ){
				model.put("success", false);
				model.put("msg", "该菜单已经存在！");
				return "list";
			}
			Videomenu videomenu = new Videomenu();
			videomenu.setVideomenuId(videomenuId);
			videoindex.setVideomenu(videomenu);
			
			manageService.getManageDAO().update(videoindex);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
		}
		return "list";
	}
	
	@RequestMapping(value = "/update",params="musicman")
	public String updateMusicman(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("musicmanId")Integer musicmanId,
			@RequestParam("musicmanName")String musicmanName){
		
		try {
			int count =  manageService.getManageDAO().getCountByProperty(Musicman.class, "musicmanName", musicmanName);
			if(count >0 ){
				model.put("success", false);
				model.put("msg", "该歌手已经存在！");
				return "list";
			}
			Musicman musicman = new Musicman();
			musicman.setMusicmanId(musicmanId);
			musicman.setMusicmanName(musicmanName);
			manageService.getManageDAO().update(musicman);
			model.put("success", true);
			model.put("msg", "修改成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，修改失败~");
		}
		return "list";
	}
	
	@RequestMapping(value = "/update",params="softindex")
	public String updateSoftindex(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("indexId")Integer indexId,
			@RequestParam("phonetypeId")Integer phonetypeId,
			@RequestParam("attachmentId")Integer attachmentId){
		
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
		softindex.setIndexId(indexId);
		Phonetype phonetype = new Phonetype();
		phonetype.setPhonetypeId(phonetypeId);
		softindex.setPhonetype(phonetype);
		Softattachment softattachment = new Softattachment();
		softattachment.setAttachmentId(attachmentId);
		softindex.setSoftattachment(softattachment);
		try {
			manageService.getManageDAO().update(softindex);
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
}
