package cn.infogiga.android.experience.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.infogiga.android.experience.R;
import cn.infogiga.android.experience.bean.ErrInfo;
import cn.infogiga.android.experience.bean.Intf;
import cn.infogiga.android.experience.bean.ItemInfo;
import cn.infogiga.android.experience.bean.PCInfo;
import cn.infogiga.android.experience.bean.ReceiveBean;
import cn.infogiga.android.experience.bean.RetInfo;
import cn.infogiga.android.experience.bean.SendBean;
import cn.infogiga.android.experience.util.CmbBeanFactory;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class ExperienceService extends Service{
	
	
	private static final String tag = "ExperienceService";
	
	private ExperienceServiceBinder experienceServiceBinder = new ExperienceServiceBinder();
	
	private static PCInfo pcInfo = null;
	
	private static String serverHost = null;
	
	private static String serverUrl = null;
	private static String cmwapUrl = null;
	
	private static String version = null;
	
	private static boolean hasLogin = false;
	
	private static String empNo = null;
	private static String empPassword = null;
	
	private static String team_id = null;
	
	private static String system_id = null;
	
	private static boolean isCmwap = false;
	
	private static Map<String,String> menuIdList = null;
	private static Map<String,String> funcIdList = null;
	
	@Override
	public IBinder onBind(Intent arg0) {
		Log.e(tag,"service onBind!");
		return experienceServiceBinder;
	}

	@Override
	public void onCreate() {
		
		serverHost = getString(R.string.server_host);
		version = getString(R.string.version);
		team_id = getString(R.string.team_id);
		system_id = getString(R.string.system_id);
		serverUrl = getString(R.string.server_url);
		cmwapUrl = getString(R.string.cmwap_url);
		
		//获得mac地址等信息
		pcInfo = getPCInfo();
		menuIdList = new HashMap<String,String>();
		funcIdList = new HashMap<String,String>();
		getMenuIdList();
		
		//检查是否有新的版本
	}
	/**
	 * 获得本机信息
	 */
	public PCInfo getPCInfo(){
		String xml = "<?xml version='1.0' encoding='UTF-8'?><msg><type>1</type></msg>";
		String callback = ExperienceService.sendMessageBySocket("10.0.2.2",10087,xml);
		Log.e(tag, "pcInfo:"+callback);
		if(callback == null){
			return null;
		}
		return ExperienceUtil.stringToPcInfo(callback);
	}
	
	private void getMenuIdList(){
		
		ReceiveBean rb = CmbBeanFactory.getMenuIdSendBean(pcInfo);
		Intf intf = sendMessageInErr(rb.toString(),serverHost);
		if(intf == null){
			return;
		}
		RetInfo retInfo = intf.getRetInfo();
		if(retInfo == null){
			return;
		}
		ArrayList<ItemInfo> list = retInfo.getItemInfoList();
		if(list == null){
			return;
		}
		int size = list.size();
		ItemInfo info = null;
		menuIdList = new HashMap<String,String>();
		funcIdList = new HashMap<String,String>();
		
		for(int i=0;i<size;i++){
			info = list.get(i);
			menuIdList.put(info.getPkg(), info.getMenuId());
			funcIdList.put(info.getPkg(), info.getFuncId());
		}

	}
	
	public static String sendMsg(String xml, String host) {
		String callback = null;
		URL url = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		HttpURLConnection httpUrlConnection = null;
		try {
			url = new URL(serverUrl);
			httpUrlConnection = (HttpURLConnection) url.openConnection();
			
			httpUrlConnection.setRequestMethod("POST");
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setRequestProperty("Charset", "UTF-8");
			httpUrlConnection.setConnectTimeout(10000); 
			httpUrlConnection.setReadTimeout(20000);
			
			outputStream = httpUrlConnection.getOutputStream();
			outputStream.write(xml.getBytes());
				
			inputStream = httpUrlConnection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
			StringBuilder buffer = new StringBuilder();
			String line = null;
			while((line = reader.readLine()) != null){
				buffer.append(line);
			}
			callback = buffer.toString();
			outputStream.close();
			inputStream.close();
			outputStream = null;
			inputStream = null;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return callback;
	}
	
	public static String sendMessageByCmwap(String host,String xml){
		System.out.println("sendXML="+xml);
		String callback = null;
		URL url = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		HttpURLConnection httpUrlConnection = null;
		try {
			url = new URL(cmwapUrl);
			httpUrlConnection = (HttpURLConnection) url.openConnection();
			httpUrlConnection.setRequestProperty("X-Online-Host",serverHost); 
			httpUrlConnection.setRequestMethod("POST");
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setRequestProperty("Charset", "UTF-8");
			httpUrlConnection.setConnectTimeout(10000); 
			httpUrlConnection.setReadTimeout(20000);
			
			outputStream = httpUrlConnection.getOutputStream();
			outputStream.write(xml.getBytes());
				
			inputStream = httpUrlConnection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
			StringBuilder buffer = new StringBuilder();
			String line = null;
			while((line = reader.readLine()) != null){
				buffer.append(line);
			}
			callback = buffer.toString();
			outputStream.close();
			inputStream.close();
			outputStream = null;
			inputStream = null;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return callback;
	}
	
	public static String sendMessageBySocket(String host,int post,String msg){
		 Socket socket;
		 String callback = null;
			try {
				socket = new Socket(host,post);
				socket.setSoTimeout(10*1000);
				OutputStream os = socket.getOutputStream();
				InputStream is = socket.getInputStream();
				//ByteArrayOutputStream bao = new ByteArrayOutputStream(httpMessage);
				os.write(msg.getBytes("utf-8"));
				BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
				StringBuilder buffer = new StringBuilder();
				String line = null;
				while((line = reader.readLine()) != null){
					buffer.append(line);
				}
				callback = buffer.toString();
				os.flush();
				os.close();
				is.close();
				socket.close();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return callback;
	}
	
	
	

	@Override
	public void onDestroy() {
		Toast.makeText(this.getBaseContext(), "text", Toast.LENGTH_LONG);
		Log.e(tag,"service onDestroy!");
		super.onDestroy();
	}

	@Override
	public void onRebind(Intent intent) {
		Log.e(tag,"service onRebind!");
		// TODO Auto-generated method stub
		super.onRebind(intent);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log.e(tag,"service onStart!");
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

	
	public class ExperienceServiceBinder extends Binder implements IExperienceService {

		@Override
		public Intf checkUpdate() {
			ReceiveBean rb = CmbBeanFactory.getUpdateReceiveBean(pcInfo);

			return sendMessageInErr(rb.toString(), serverHost);
		}

		@Override
		public String getEmpNo() {
			// TODO Auto-generated method stub
			return empNo;
		}

		@Override
		public String getItemInfo(String menuId, String phoneNumber,String isFetion) {
			ReceiveBean rb = CmbBeanFactory.getItemSendBean(pcInfo);
			rb.setMenu_id(menuId);
			rb.setPhone_number(phoneNumber);
			rb.setIs_fetion(isFetion);
			rb.setTeam_id(team_id);
			rb.setSystem_id(system_id);
			rb.setEmp_no(empNo);
			if(isCmwap){
				return sendMessageByCmwap(serverHost, rb.toString());
			}else{
				return sendMsg(rb.toString(), serverHost);
			}
		}

		@Override
		public PCInfo getPcInfo() {
			// TODO Auto-generated method stub
			return pcInfo;
		}

		@Override
		public boolean hasLogin() {
			// TODO Auto-generated method stub
			return hasLogin;
		}

		@Override
		public Intf login(String eNo,String pwd) {
			ReceiveBean rb = CmbBeanFactory.getLoginSendBean(pcInfo);
			rb.setEmp_no(eNo);
			rb.setTeam_id(team_id);
			rb.setSystem_id(system_id);
			rb.setEmp_pwd(pwd);
			Intf intf = sendMessageInErr(rb.toString(), serverHost);
			ErrInfo err = intf.getErrinfo();
			if("0".equals(err.getErrCode())){
				empNo = eNo;
				empPassword = pwd;
			}
			return intf;
		}

		@Override
		public Intf order(String meauId, String funcId, String phoneNumber,
				String targetCode, String targetName,String serverPwd, String sysOper,String action) {
			ReceiveBean rb = CmbBeanFactory.getOrderSendBean(pcInfo);
			//SendBean sb = BeanFactory.getOrderSendBean(pcInfo);
			rb.setMenu_id(meauId);
			rb.setFunc_id(funcId);
			rb.setPhone_number(phoneNumber);
			rb.setTarget_code(targetCode);
			rb.setTarget_name(targetName);
			rb.setSys_oper(sysOper);
			rb.setEmp_no(empNo);
			rb.setServer_pwd(serverPwd);
			rb.setTeam_id(team_id);
			rb.setSystem_id(system_id);
			rb.setAction(action);
			return sendMessageInErr(rb.toString(), serverHost);
		}

		@Override
		public String send(String xml) {
			return sendMsg(xml, serverHost);
		}


		@Override
		public Intf sendForErr(String xml) {
			return sendMessageInErr(xml,serverHost);
		}

		@Override
		public Intf log(String menuId, String type,String phoneNumber,String scene,String comboName,String pkg) {

			ReceiveBean rb = CmbBeanFactory.getLogSendBean(pcInfo, type, scene);
			rb.setEmp_no(empNo);
			rb.setEmp_pwd(empPassword);
			rb.setMenu_id(menuId);
			rb.setPhone_number(phoneNumber);
			rb.setTeam_id(team_id);
			rb.setSystem_id(system_id);
			rb.setCombo_name(comboName);
			rb.setPkg(pkg);
			return sendMessageInErr(rb.toString(),serverHost);
		}

		@Override
		public void setEmpNo(String emp_no,String emp_password) {
			empNo = emp_no;
			empPassword = emp_password;
		}

		@Override
		public void setLoginState(boolean b) {
			hasLogin = b;
		}

		@Override
		public String send(ReceiveBean sb) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Intf sendForErr(ReceiveBean sb) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Intf sendSMS(String phoneNumber, String context,String menuId) {
			ReceiveBean rb = CmbBeanFactory.getSMSSendBean(pcInfo);
			rb.setEmp_no(empNo);
			rb.setMenu_id(menuId);
			rb.setPhone_number(phoneNumber);
			rb.setTeam_id(team_id);
			rb.setSystem_id(system_id);
			rb.setSms_context(context);
			return sendMessageInErr(rb.toString(),serverHost);
		}

		@Override
		public String getMenuId(String pkg) {
			if(menuIdList.size() == 0){
				getMenuIdList();
			}
			if(menuIdList == null){
				return null;
			}
			return menuIdList.get(pkg);
		}

		@Override
		public String getFuncId(String pkg) {
			if(funcIdList.size() == 0){
				getMenuIdList();
			}
			if(funcIdList == null){
				return null;
			}
			return funcIdList.get(pkg);
		}

		@Override
		public void sendToPC(String name,String id) {
			String xml = "<?xml version='1.0' encoding='UTF-8'?><msg><type>2</type><name>"+name+"</name><id>"+id+"</id></msg>";
			String callback = ExperienceService.sendMessageBySocket("10.0.2.2",10087,xml);
		}
		
    }
	
	public Intf sendMessageInErr(String xml,String host){
		String callback = null;
		if(!isCmwap){
			callback = sendMsg(xml, host);
		}else{
			callback = sendMessageByCmwap(host,xml);//ssendMsg(xml, host);
		}
		//String callback = sendMessageByCmwap(host, xml);
		Intf intf = new Intf();
		if(callback == null){
			ErrInfo errInfo = CmbBeanFactory.getNoCallbackErrInfo();
			intf.setErrinfo(errInfo);
		}else{
			try {
				intf.parse(callback);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return intf;
	}
	
	
	@Override
	public boolean onUnbind(Intent intent) {
		Log.e(tag,"service onUnbind!");
		return super.onUnbind(intent);
	}
}
