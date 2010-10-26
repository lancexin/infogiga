package cn.infogiga.sd.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cindy.util.ImageUtil;
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

public class MappingDeleteJsonController {
	
	ManageService manageService;

	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}
	
	@RequestMapping(value = "/delete",params="power")
	public String deletePower(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("powerId")Integer powerId){
		Power power = new Power(powerId);
		try {
			manageService.getManageDAO().delete(power);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，删除失败~");
		}
		return "list";
	}
	
	@RequestMapping(value = "/delete",params="admin")
	public String deleteAdmin(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("adminId")Integer adminId){
		Admin admin = new Admin();
		admin.setUserId(adminId);
		try {
			manageService.getManageDAO().delete(admin);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，删除失败~");
		}
		return "list";
	}
	
	@RequestMapping(value = "/delete",params="hall")
	public String deleteHall(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("hallId")Integer hallId){
		Bissinusshall hall = new Bissinusshall();
		hall.setHallId(hallId);
		try {
			manageService.getManageDAO().delete(hall);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，删除失败~");
		}
		return "list";
	}
	
	@RequestMapping(value = "/delete",params="equipment")
	public String deleteEquipment(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("equipmentId")Integer equipmentId){
		Equipment equipment = new Equipment(equipmentId);
		try {
			manageService.getManageDAO().delete(equipment);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，删除失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/delete",params="employee")
	public String deleteEmployee(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("employeeId")Integer employeeId){
		Employee employee = new Employee();
		employee.setUserId(employeeId);
		try {
			manageService.getManageDAO().delete(employee);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，删除失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/delete",params="province")
	public String deleteProvince(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("provinceId")Integer provinceId){
		int cityCount = manageService.getManageDAO().getCountByProperty(City.class, "province.provinceId", provinceId);
		if(cityCount > 0){
			model.put("success", false);
			model.put("msg", "该项已经有归属！");
			return "list";
		}
		Province prvince = new Province(provinceId);
		try {
			manageService.getManageDAO().delete(prvince);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，删除失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/delete",params="city")
	public String deleteCity(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("cityId")Integer cityId){
		City city = new City(cityId);
		try {
			manageService.getManageDAO().delete(city);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，删除失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/delete",params="phonebrand")
	public String deletePhonebrand(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("phonebrandId")Integer phonebrandId){
		Phonebrand phonebrand = new Phonebrand(phonebrandId);
		try {
			manageService.getManageDAO().delete(phonebrand);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，删除失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/delete",params="phoneplatform")
	public String deletePhoneplatform(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("platformId")Integer platformId){
		Phoneplatform phoneplatform = new Phoneplatform(platformId);
		try {
			manageService.getManageDAO().delete(phoneplatform);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/delete",params="phonetype")
	public String deletePhonetype(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("phonetypeId")Integer phonetypeId){

		 Phonetype phonetype = manageService.getManageDAO().findById(Phonetype.class, phonetypeId);
		//同时删除素材区的图片
		ImageUtil.delete(new File(request.getRealPath(phonetype.getPic())));
		try {
			manageService.getManageDAO().delete(phonetype);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/delete",params="soft")
	public String deleteSoft(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("softId")Integer softId){

		 Soft soft = manageService.getManageDAO().findById(Soft.class, softId);
		//同时删除素材区的图片
		ImageUtil.delete(new File(request.getRealPath(soft.getPic1())));
		ImageUtil.delete(new File(request.getRealPath(soft.getPic2())));
		try {
			manageService.getManageDAO().delete(soft);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/delete",params="video")
	public String deleteVideo(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("video")Integer videoId){
		try {
			int count = manageService.getManageDAO().getCountByProperty(Videoindex.class, "video.videoId", videoId);
			if(count > 0){
				model.put("success", false);
				model.put("msg", "还有索引没有删除！");
				return "list";
			}
			Video video = manageService.getManageDAO().findById(Video.class, videoId);
			//同时删除素材区的图片
			ImageUtil.delete(new File(request.getRealPath(video.getPic1())));
			ImageUtil.delete(new File(request.getRealPath(video.getPic2())));
			Download download = video.getDownload();
			//同时删除上传的视频文件
			deleteAttachment(request.getRealPath(download.getUrl()));
			manageService.getManageDAO().delete(video);
			manageService.getManageDAO().delete(download);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，添加失败~");
		}
		return "list";
	}
	

	@RequestMapping(value = "/delete",params="softmenu")
	public String deleteSoftmenu(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("softmenuId")Integer softmenuId){
		Softmenu softmenu = new Softmenu();
		softmenu.setSoftmenuId(softmenuId);
		try {
			manageService.getManageDAO().delete(softmenu);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，删除失败~");
		}
		return "list";
	}
	
	@RequestMapping(value = "/delete",params="softattachment")
	public String deleteSoftAttachment(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("attachmentId")Integer attachmentId){
		Softattachment attachment = manageService.getManageDAO().findById(Softattachment.class, attachmentId);
		Download download = attachment.getDownload();
		//删除存在的附件
		deleteAttachment(request.getRealPath(download.getUrl()));
		try {
			manageService.getManageDAO().delete(attachment);
			manageService.getManageDAO().delete(download);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，删除失败~");
		}
		return "list";
	}
	
	
	@RequestMapping(value = "/delete",params="musicmenu")
	public String deleteMusicmenu(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("musicmenuId")Integer musicmenuId){
		Musicmenu musicmenu = new Musicmenu();
		musicmenu.setMusicmenuId(musicmenuId);
		try {
			int count = manageService.getManageDAO().getCountByProperty(Musicmenu.class, "musicmenu.musicmenuId", musicmenuId);
			if(count > 0){
				model.put("success", false);
				model.put("msg", "还有子菜单没有删除！");
			}else{
				manageService.getManageDAO().delete(musicmenu);
				model.put("success", true);
				model.put("msg", "删除成功！");
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，删除失败~");
		}
		return "list";
	}
	
	
	private void deleteAttachment(String url){
		File file = new File(url);
		if(file.exists()){
			file.delete();
		}
	}

	@RequestMapping(value = "/delete",params="softindex")
	public String deleteSoftindex(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("indexId")Integer indexId){
		Softindex softindex = new Softindex();
		softindex.setIndexId(indexId);
		
		try {
			manageService.getManageDAO().delete(softindex);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "删除失败~");
		}
		return "list";
	}
	
	@RequestMapping(value = "/delete",params="videomenu")
	public String deleteVideomenu(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("videomenuId")Integer videomenuId){
		try {
			Videomenu videomenu = new Videomenu();
			videomenu.setVideomenuId(videomenuId);
			manageService.getManageDAO().delete(videomenu);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "删除失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/delete",params="musicman")
	public String deleteMusicman(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("musicmanId")Integer musicmanId){
		try {
			int count = manageService.getManageDAO().getCountByProperty(Music.class, "musicman.musicmanId", musicmanId);
			if(count > 0){
				model.put("success", true);
				model.put("msg", "该歌手还有歌曲没有删除！");
				return "list";
			}
			Musicman musicman = new Musicman();
			musicman.setMusicmanId(musicmanId);
			manageService.getManageDAO().delete(musicman);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，删除失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/delete",params="music")
	public String deleteMusic(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("musicId")Integer musicId){
		try {
			Music music = manageService.getManageDAO().findById(Music.class, musicId);
			Download download = music.getDownload();
			manageService.getManageDAO().delete(music);
			manageService.getManageDAO().delete(download);
			deleteAttachment(request.getRealPath(download.getUrl()));
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，删除失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/delete",params="musicindex")
	public String deletemusicindex(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("indexId")Integer indexId){
		try {
			Musicindex musicindex = new Musicindex();
			musicindex.setIndexId(indexId);
			manageService.getManageDAO().delete(musicindex);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，删除失败~");
		}
		return "list";
	}

	@RequestMapping(value = "/delete",params="videoindex")
	public String deletevideoindex(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("indexId")Integer indexId){
		try {
			Videoindex videoindex = new Videoindex();
			videoindex.setIndexId(indexId);
			manageService.getManageDAO().delete(videoindex);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "未知原因，删除失败~");
		}
		return "list";
	}
}
