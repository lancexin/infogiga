package cn.infogiga.exp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.infogiga.exp.service.CmbService;
import cn.infogiga.exp.service.ExperienceService;
import cn.infogiga.exp.webservice.bean.ErrInfo;
import cn.infogiga.exp.webservice.bean.Intf;
import cn.infogiga.exp.webservice.bean.ItemInfo;
import cn.infogiga.exp.webservice.bean.ReceiveBean;
import cn.infogiga.exp.webservice.bean.RetInfo;
import cn.infogiga.exp.webservice.bean.StarSoft;
import cn.infogiga.exp.webservice.bean.TopSoft;
import cn.infogiga.exp.webservice.client.CmbBeanFactory;
import cn.infogiga.pojo.Menu;
import cn.infogiga.pojo.Soft;
import cn.infogiga.pojo.Softdownloadstat;

@Controller
public class ExperienceController {
	private static final Log log = LogFactory.getLog(ExperienceController.class);
	
	@Autowired
	CmbService cmbService;
	
	@Autowired
	ExperienceService experienceService;
		
	
	/**
	 * 入口
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/m")
	public void info(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String xml = request.getReader().readLine();
		log.info("接收信息："+xml);
		//System.out.println("xml:"+xml);
		String callback = null;
		if(xml == null){
			Intf intf = new Intf();
			ErrInfo errInfo = CmbBeanFactory.getNullErrorInfo();
			intf.setErrinfo(errInfo);
			callback = intf.toString();
		}else{
			//解析xml
			ReceiveBean rb = new ReceiveBean();
			try {
				rb.parse(xml);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Integer type = Integer.parseInt(rb.getType());
			System.out.println("type:"+type);
			switch (type) {
			case 1://设备注册
				break;
			case 2://设备开启（登录）
				callback = login(rb);
				break;
			case 3://体验日志记录
				callback = log(rb);
				break;
			case 4://退出体验
				callback = getItemInfo(rb);
				break;
			case 5://订购业务
				String str = checkServerPwd(rb);
				System.out.println("checkServerPwd,str:"+str);
				Intf intf = new Intf();
				try {
					intf.parse(str);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ErrInfo err = intf.getErrinfo();
				System.out.println("0".equals(err.getErrCode()));
				if(!"0".equals(err.getErrCode())){
					callback = str;
					break;
				}
				callback = order(rb);
				break;
			case 7://软件下载统计
				callback = logDownLoadSoft(rb);
				break;
			case 8://发送短信
				callback = sendSMS(rb);
				break;
			case 9://发送wap push
				callback = sendWapPush(rb);
				break;
			case 10://获得每周排行榜
				callback = getTopSoft(rb);
				break;
			case 11://获得本周主打星
				callback = getStarSoft(rb);
				break;
			case 12://获得软件id信息
				callback = getMenuInfo(rb);
				break;
				
			case 15://添加wappush临时统计信息
				callback = addTempDownloadstat(rb);
				
				break;
			case 16://wappush临时统计信息确认
				callback = comfirmDownloadstat(rb);
				
				break;
			default:
				Intf intf2 = new Intf();
				ErrInfo errInfo = CmbBeanFactory.getIllegalErrInfo();
				intf2.setErrinfo(errInfo);
				callback = intf2.toString();
				break;
			}
		}
		log.info("发送信息："+callback);
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(callback);
	}
	
	private String getMenuInfo(ReceiveBean rb){
		List<Menu> list = experienceService.findAll(Menu.class);
		Intf intf = new Intf();
		RetInfo retInfo = new RetInfo();
		ArrayList<ItemInfo> itemInfoList = new ArrayList<ItemInfo>();
		int size = list.size();
		Menu menu;
		ErrInfo errInfo;
		try {
			for(int i=0;i<size;i++){
				menu = list.get(i);
				ItemInfo itemInfo = new ItemInfo();
				itemInfo.setId(menu.getId()+"");
				itemInfo.setMenuId(menu.getMenuid());
				itemInfo.setFuncId(menu.getFuncId());
				itemInfo.setPkg(menu.getPkg());
				itemInfo.setName(menu.getMenuName());
				itemInfoList.add(itemInfo);
				retInfo.setItemInfoList(itemInfoList);
				intf.setRetInfo(retInfo);
			}
			errInfo = CmbBeanFactory.getSuccessErrInfo();
		} catch (RuntimeException e) {
			errInfo = CmbBeanFactory.getItemInfoErrInfo();
			e.printStackTrace();
		}
		intf.setErrinfo(errInfo);
		return intf.toString();
		
	}
	
	
	/**
	 * 订购业务
	 * @param rb
	 * @return
	 */
	private String order(ReceiveBean rb){
		String str = cmbService.sendOrder(rb.getMenu_id(), rb.getFunc_id(), rb.getPhone_number(), rb.getTarget_code(), rb.getTarget_name(), rb.getSys_oper(),rb.getAction());
		System.out.println("order:"+str);
		if(str == null){
			Intf intf = new Intf();
			ErrInfo errInfo = CmbBeanFactory.getNoCallbackErrInfo();
			intf.setErrinfo(errInfo);
			return intf.toString();
		}
		Intf i = new Intf();
		try {
			i.parse(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//如果订购成功则发送短信通知订购用户
		ErrInfo err = i.getErrinfo();
		if("0".equals(err.getErrCode())){
			if("0".equals(rb.getAction())){
				cmbService.sendSMS(rb.getPhone_number(), "您刚刚取消了 "+rb.getTarget_name());
			}else if("1".equals(rb.getAction())){
				cmbService.sendSMS(rb.getPhone_number(), "您刚刚订购了"+rb.getTarget_name()+",谢谢您的订购");
			}
			
		}
		return str;
	}
	
	private String getTopSoft(ReceiveBean rb){
		Intf intf = new Intf();
		
		try {
			List<Soft> list = experienceService.getTopSoft(rb);
			int size = list.size();
			ArrayList<TopSoft> topSoftList = new ArrayList<TopSoft>();
			Soft downloadStat = null;
			for(int i=0;i<size;i++){
				downloadStat = list.get(i);
				TopSoft topSoft = new TopSoft();
				topSoft.setSoftName(downloadStat.getSoftName());
				topSoft.setDownloadCount(""+downloadStat.getDownload().getDownloadCount());
				topSoftList.add(topSoft);
			}
			RetInfo retInfo = new RetInfo();
			retInfo.setTopSoftList(topSoftList);
			intf.setRetInfo(retInfo);
			ErrInfo errinfo = CmbBeanFactory.getSuccessErrInfo();
			intf.setErrinfo(errinfo);
		} catch (RuntimeException e) {
			ErrInfo errinfo = CmbBeanFactory.getTopSoftErrInfo();
			intf.setErrinfo(errinfo);
			e.printStackTrace();
		}
		
		return intf.toString();
	}
	
	private String getStarSoft(ReceiveBean rb){
		Intf intf = new Intf();
		
		try {
			Soft soft = experienceService.getStarSoft(rb);
			RetInfo retInfo = new RetInfo();
			if(soft ==  null){
				StarSoft starSoft = new StarSoft();
				starSoft.setSoftName("UC浏览器");
				starSoft.setDownloadCount("0");
				retInfo.setStarSoft(starSoft);
			}else{
				StarSoft starSoft = new StarSoft();
				starSoft.setSoftName(soft.getSoftName());
				starSoft.setDownloadCount(soft.getDownload().getDownloadCount()+"");
				retInfo.setStarSoft(starSoft);
			}
			intf.setRetInfo(retInfo);
			ErrInfo errinfo = CmbBeanFactory.getSuccessErrInfo();
			intf.setErrinfo(errinfo);
		} catch (RuntimeException e) {
			ErrInfo errinfo = CmbBeanFactory.getTopSoftErrInfo();
			intf.setErrinfo(errinfo);
			e.printStackTrace();
		}
		
		return intf.toString();
	}
	
	/**
	 * 设备登录
	 * @param rb
	 * @return
	 */
	private String login(ReceiveBean rb){
		boolean b = experienceService.checkLogin(rb);
		Intf intf = new Intf();
		ErrInfo errInfo = null;
		if(b){
			//Employee employee = experienceService.getSingleEmployee(rb);
			boolean bol = experienceService.checkEmployeeWithPassword(rb);
			if(!bol){
				errInfo = CmbBeanFactory.getNoSuchEmployeeErrInfo();
			}else{
				errInfo = CmbBeanFactory.getLoginSuccessErrInfo();
			}
		}else{
			errInfo = CmbBeanFactory.getNoSuchEquipmentErrInfo();
		}
		intf.setErrinfo(errInfo);
		return intf.toString();
	}
	
	
	/**
	 * 记录日志
	 * @param rb
	 * @return
	 */
	private String log(ReceiveBean rb){
		boolean b = experienceService.log(rb);
		Intf intf = new Intf();
		ErrInfo errInfo = null;
		if(b){
			errInfo = CmbBeanFactory.getAddSuccessErrInfo();
			
		}else{
			errInfo = CmbBeanFactory.getAddFailureErrInfo();
		}
		intf.setErrinfo(errInfo);
		return intf.toString();
		
	}
	
	
	/**
	 * 软件下载统计
	 * @param rb
	 * @return
	 */
	private String logDownLoadSoft(ReceiveBean rb){
		Intf intf = new Intf();
		ErrInfo errInfo = null;
		
		Softdownloadstat bl = experienceService.logDownloadSoft(rb,1);
		if(bl != null){
			errInfo = CmbBeanFactory.getSuccessErrInfo();
		}else{
			errInfo = CmbBeanFactory.getAddFailureErrInfo();
		}
		
		intf.setErrinfo(errInfo);
		return intf.toString();
	}
	
	private String sendSMS(ReceiveBean rb){
		boolean b = false;
		Intf intf = new Intf();
		ErrInfo errInfo = null;

		if(rb.getSms_context() != null && !"".equals(rb.getSms_context().trim())){
			b = cmbService.sendSMS(rb.getPhone_number(), rb.getSms_context());
		}
		
		if(b){
			boolean bll = experienceService.logSMS(rb);
			if(bll){
				errInfo = CmbBeanFactory.getSendSmsSuccessErrInfo();
			}else{
				errInfo = CmbBeanFactory.getSendSmsSuccessButLogFailureErrInfo();
			}
		}else{
			errInfo = CmbBeanFactory.getSendSmsFailureErrInfo();
		}
		intf.setErrinfo(errInfo);
		return intf.toString();
	}
	
	private String sendWapPush(ReceiveBean rb){
		boolean b = false;
		Intf intf = new Intf();
		ErrInfo errInfo = null;
		
		if(rb.getSms_context() != null && !"".equals(rb.getSms_context().trim())){
			b = cmbService.sendWapPush(rb.getPhone_number(), rb.getSoft_download_url(), rb.getSms_context());
		}
		
		if(b){
			boolean bll = experienceService.logSMS(rb);
			if(bll){
				errInfo = CmbBeanFactory.getSendSmsSuccessErrInfo();
			}else{
				errInfo = CmbBeanFactory.getSendSmsSuccessButLogFailureErrInfo();
			}
		}else{
			errInfo = CmbBeanFactory.getSendSmsFailureErrInfo();
		}
		intf.setErrinfo(errInfo);
		return intf.toString();
	}
	
	/**
	 * 获得业务信息
	 * @param rb
	 * @return
	 */
	private String getItemInfo(ReceiveBean rb){
		String str = cmbService.getBusiInfo(rb.getMenu_id(), rb.getPhone_number());
		Intf intf = new Intf();
		if(str == null){
			ErrInfo errInfo = CmbBeanFactory.getNoCallbackErrInfo();
			intf.setErrinfo(errInfo);
			return intf.toString();
		}else{
			return str;
		}
	}
	
	/**
	 * 检查客户服务密码是否正确
	 * @param rb
	 * @return
	 */
	private String checkServerPwd(ReceiveBean rb){
		String checkInfo = cmbService.checkServerPassword(rb.getPhone_number(), rb.getServer_pwd());
		
		System.out.println("checkInfo:"+checkInfo);
		if(checkInfo == null){
			Intf intf = new Intf();
			ErrInfo errInfo = CmbBeanFactory.getNoCallbackErrInfo();
			intf.setErrinfo(errInfo);
			return intf.toString();
		}else{
			return checkInfo;
		}
	}
	
	/**
	 * 添加临时软件下载统计信息
	 * @param rb
	 * @return
	 */
	private String addTempDownloadstat(ReceiveBean rb){
		Softdownloadstat ds = experienceService.logDownloadSoft(rb, 0);
		boolean bl = experienceService.addTempDownloadstat(rb,ds.getId());
		Intf intf = new Intf();
		if(bl){
			ErrInfo errInfo = CmbBeanFactory.getSuccessErrInfo();
			intf.setErrinfo(errInfo);
			return intf.toString();
		}else{
			ErrInfo errInfo = CmbBeanFactory.getNullErrorInfo();
			intf.setErrinfo(errInfo);
			return intf.toString();
		}
	}
	
	private String comfirmDownloadstat(ReceiveBean rb){
		boolean bl = experienceService.comformDownloadstat(rb);
		Intf intf = new Intf();
		if(bl){
			ErrInfo errInfo = CmbBeanFactory.getSuccessErrInfo();
			intf.setErrinfo(errInfo);
			return intf.toString();
		}else{
			ErrInfo errInfo = CmbBeanFactory.getNullErrorInfo();
			intf.setErrinfo(errInfo);
			return intf.toString();
		}
	}

}
