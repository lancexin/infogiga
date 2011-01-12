package cn.infogiga.sd.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cindy.util.FileUtil;
import cn.infogiga.pojo.Attachment;
import cn.infogiga.sd.service.ManageService;

@Controller
public class AttachmentController {
	@Autowired
	ManageService manageService;
	
	/*@Autowired
	MsoftService msoftService;*/
	
	@RequestMapping(value = "/attachment",params="delete")
	public String removeAttachment(HttpServletRequest request,HttpServletResponse response,HttpSession session,ModelMap model,
			@RequestParam("attachmentId")Integer attachmentId){
		try {
			Attachment attachment = manageService.getManageDAO().findById(Attachment.class, attachmentId);
			
			FileUtil.delete(new File(request.getRealPath(attachment.getUrl())));
			//msoftService.deleteAttachment(attachment, request);
			manageService.getManageDAO().delete(attachment);
			model.put("success", true);
			model.put("msg", "删除成功！");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("success", false);
			model.put("msg", "删除失败,该软件可能已经投入使用~");
		}
		
		return "list";
	}
}
