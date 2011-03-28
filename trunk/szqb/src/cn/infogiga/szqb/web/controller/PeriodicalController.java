package cn.infogiga.szqb.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import cindy.page.beanutils.MyBeanUtils;
import cindy.page.hibernate.CirteriaBean;
import cindy.page.hibernate.CirteriaQuery;
import cindy.page.hibernate.PageBean;
import cindy.util.DateUtil;
import cindy.util.FileMd5;
import cindy.util.FileUtil;
import cindy.util.ImageUtil;
import cindy.util.MD5;
import cindy.util.ProperiesReader;
import cindy.util.ZipCompressor;
import cn.infogiga.szqb.pojo.Page;
import cn.infogiga.szqb.pojo.Periodical;
import cn.infogiga.szqb.pojo.Reader;
import cn.infogiga.szqb.pojo.Readtype;
import cn.infogiga.szqb.vo.JsonListBean;
import cn.infogiga.szqb.vo.JsonPeriodical;
import cn.infogiga.szqb.web.service.ManageService;

@Controller
public class PeriodicalController {

	@Autowired
	ManageService manageService;
	
	@RequestMapping(value = "/periodical")
	public String list(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model,
			@RequestParam("start")Integer start){
		Integer limit = (request.getParameter("limit")==null || request.getParameter("limit").length()==0)?20:Integer.parseInt(request.getParameter("limit"));
		Integer status = (request.getParameter("status")==null || request.getParameter("status").length()==0)?-1:Integer.parseInt(request.getParameter("status"));
		Integer readerId = (request.getParameter("readerId")==null || request.getParameter("readerId").length()==0)?-1:Integer.parseInt(request.getParameter("readerId"));
		
		PageBean pageBean = new PageBean(start,limit);
		CirteriaBean cBean = new CirteriaBean("publishTime");
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"r.id",readerId,new String[]{"reader","r"}));
		cBean.addQuery(new CirteriaQuery(CirteriaQuery.EQ,CirteriaQuery.IS_INT,"status",status,null));
		int totalCount = manageService.getManageDAO().getCountByPage(Periodical.class, cBean);
		
		List<JsonPeriodical> list = MyBeanUtils.copyListProperties(manageService.getManageDAO().getListByPage(Periodical.class, cBean), JsonPeriodical.class);
		JsonListBean jsonListBean = new JsonListBean(totalCount,list,true,null);
		model.addAttribute("object", jsonListBean);
		cBean.setPageBean(pageBean);
		
		return "list";
	}
	
	@RequestMapping(value = "/periodical",params="update")
	public String update(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model
			,@RequestParam("periodicalId")Integer periodicalID
			,@RequestParam("tabloidPic")String tabloidPic
			,@RequestParam("indexPic")String indexPic
			,@RequestParam("publishTime")String publishTime
			,@RequestParam("number")Integer number
			,@RequestParam("readerId")Integer readerID){
		if(tabloidPic == null || tabloidPic.length() <= 0 
				|| indexPic == null || indexPic.length() <=0 
				|| publishTime == null || publishTime.length() <=0){
			model.put("success", false);
			model.put("msg", "请完整填写信息！");
			return "list";
		}
		
		Periodical periodical = manageService.getManageDAO().findById(Periodical.class, periodicalID);
		periodical.setTabloidPic(tabloidPic);
		periodical.setIndexPic(indexPic);
		periodical.setPublishTime(DateUtil.stringToDate(publishTime, DateUtil.NOW_DATE));
		periodical.setNumber(number);
		Reader reader = new Reader();
		reader.setId(readerID);
		manageService.getManageDAO().update(periodical);
		model.put("success", true);
		model.put("msg", "修改陈功！");
		model.put("periodicalId", periodicalID);
		return "list";
	}
	
	@RequestMapping(value = "/periodical",params="review")
	public String startReview(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model
			,@RequestParam("periodicalId")Integer periodicalID){
		Periodical periodical = manageService.getManageDAO().findById(Periodical.class, periodicalID);
		
		periodical.setStatus(2);
		manageService.getManageDAO().update(periodical);
		List<Page> pages = manageService.getManageDAO().findByProperty(Page.class, "periodical.id", periodicalID,"addTime");
		model.put("periodical", periodical);
		model.put("pages", pages);
		return "reader/review_periodical";
	}
	
	@RequestMapping(value = "/periodical",params="exeReview")
	public String review(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model
			,@RequestParam("periodicalId")Integer periodicalID){
		Periodical periodical = manageService.getManageDAO().findById(Periodical.class, periodicalID);
		int status = periodical.getStatus();
		if(status > 2){
			model.put("success", false);
			model.put("msg", "状态不服！");
			return "list";
		}
		//未找到该读物
		if(periodical == null){
			model.put("success", false);
			model.put("msg", "未找到该读物信息！");
			return "list";
		}
		//读物已经发布或者发布失败
		if(periodical.getStatus() == 3 || periodical.getStatus() == 4){
			model.put("success", false);
			model.put("msg", "已经发布或者发布失败！");
			return "list";
		}
		
		File indexPic = new File(request.getRealPath(periodical.getIndexPic()));
		File tabloidPic = new File(request.getRealPath(periodical.getTabloidPic()));
		if(!indexPic.exists()){
			model.put("success", false);
			model.put("msg", "图片资源不存在！");
			return "list";
		}
		if(!tabloidPic.exists()){
			model.put("success", false);
			model.put("msg", "图片资源不存在！");
			return "list";
		}
		//生成rar压缩包,并生成md5码
		List<File> fileList = new ArrayList<File>();
		fileList.add(indexPic);
		fileList.add(tabloidPic);
		Set set = periodical.getPages();
		Iterator ite = set.iterator();
		while(ite.hasNext()){
			Page page = (Page) ite.next();
			File img = new File(request.getRealPath(page.getImgUrl()));
			if(!img.exists()){
				model.put("success", false);
				model.put("msg", "图片资源不存在！");
				return "list";
			}
			fileList.add(img);
		}
		String attachmentUrl = "zip/"+periodical.getShortName()+".zip";
		File zipFile = ZipCompressor.compress(fileList, request.getRealPath(attachmentUrl));
		if(zipFile == null){
			model.put("success", false);
			model.put("msg", "生成附件文件失败！");
			return "list";
		}
		String md5Code = FileMd5.getFileMD5String(zipFile);
		
		periodical.setAttachmentUrl(attachmentUrl);
		periodical.setAttachmentMd5(md5Code);
		periodical.setStatus(3);
		
		manageService.getManageDAO().update(periodical);
		model.put("success", true);
		model.put("msg", "发布成功！");
		return "list";
	}

	@RequestMapping(value = "/periodical",params="delete")
	public String delete(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model
			,@RequestParam("periodicalId")Integer periodicalID){
		Periodical periodical = manageService.getManageDAO().findById(Periodical.class, periodicalID);
		
		if(periodical.getStatus() == 3){
			model.put("success", false);
			model.put("msg", "已经发布无法删除！");
			return "list";
		}
		//这里需要修改
		
		FileUtil.delete(new File(request.getRealPath(periodical.getIndexPic())));
		FileUtil.delete(new File(request.getRealPath(periodical.getTabloidPic())));
		if(periodical.getAttachmentUrl() != null ){
			FileUtil.delete(new File(request.getRealPath(periodical.getAttachmentUrl())));
		}
		//同时删除图片等信息
		try {
			Set set = periodical.getPages();
			Iterator ite = set.iterator();
			while(ite.hasNext()){
				Page page = (Page) ite.next();
				FileUtil.delete(new File(request.getRealPath(page.getImgUrl())));
				FileUtil.delete(new File(request.getRealPath(page.getOriginalUrl())));
				manageService.getManageDAO().delete(page);
			}
			manageService.getManageDAO().delete(periodical);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			model.put("success", false);
			model.put("msg", "删除失败！");
			e.printStackTrace();
		}
		
		return "list";
	}
	
	@RequestMapping(value = "/periodical",params="add")
	public String add(HttpServletRequest request,HttpServletResponse response,HttpSession session, ModelMap model
			,@RequestParam("tabloidPic")String tabloidPic
			,@RequestParam("indexPic")String indexPic
			,@RequestParam("publishTime")String publishTime
			,@RequestParam("number")Integer number
			,@RequestParam("readerId")Integer readerID){
		
		if(tabloidPic == null || tabloidPic.trim().length() ==0 || 
				indexPic == null || indexPic.trim(). length() ==0 ){
			model.put("success", false);
			model.put("msg", "信息必须填写完整！");
			return "list";
		}
		
		//查看该刊号是否已经存在 根据shortName
		Reader reader = manageService.getManageDAO().findById(Reader.class, readerID);
		Readtype readtype = reader.getReadtype();
		Date publishDate = DateUtil.stringToDate(publishTime, DateUtil.NOW_DATE);
		String shortName = readtype.getShortName()+"_"+reader.getShortName()+"_"+DateUtil.getDateString(publishDate, DateUtil.NOW_TIME3)+"_"+number;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("number", number);
		map.put("reader.id", readerID);
		int count = manageService.getManageDAO().getCountByProperties(Periodical.class,map);
		if(count > 0 ){
			model.put("msg", "该期刊已经创建！");
			model.put("success", false);
			return "list";
		}
		
		//查看图片文件是否已经存在,如果存在则保存到相应的文件夹
		String index = savePNG(request, model, indexPic,shortName+"_index");
		String tabloid = savePNG(request, model, tabloidPic,shortName+"_tabloid");
		if(index == null || tabloid == null){
			return "list";
		}
		//插入数据
		Periodical periodical = new Periodical();
		periodical.setAddTime(new Date());
		periodical.setIndexPic(index);
		periodical.setTabloidPic(tabloid);
		periodical.setShortName(shortName);
		periodical.setNumber(number);
		periodical.setPublishTime(publishDate);
		periodical.setReader(reader);
		/**
		 * 0 未完成            ----- 表示还没有上传pdf文件
		 * 1 图片转换中         ----- pdf文件已经上传完成,正在转换
		 * 2 待审核     ----- pdf文件已经转换完成
		 * 3 已发布     ----- 期刊已经发布
		 * 4 发布失败   ----- 文件转换期间出现错误
		 */
		periodical.setStatus(0);
		try {
			manageService.getManageDAO().save(periodical);
			model.put("success", true);
			model.put("msg", "添加成功！");
			model.put("periodicalId", periodical.getId());
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "添加失败,请联系管理员！");
		}
		return "list";
	}
	
	public String savePNG(HttpServletRequest request,ModelMap model,String url,String shortName){
		File of = new File(request.getRealPath(url));
		String suffix = ImageUtil.validatePNG(of);
		if(suffix == null){
			model.put("success", false);
			model.put("msg", "未能找到文件");
			return null;
		}
		String picUrl = ProperiesReader.getInstence("config.properties").getStringValue("image.url")+shortName+"."+suffix;
		boolean bl = FileUtil.copyFile(of, new File(request.getRealPath(picUrl)),false);
		if(bl){
			return picUrl;
		}
		model.put("success", false);
		model.put("msg", "复制文件出错");
		return null;
	}
}
