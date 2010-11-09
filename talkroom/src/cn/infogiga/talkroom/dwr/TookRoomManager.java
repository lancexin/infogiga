package cn.infogiga.talkroom.dwr;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;



public class TookRoomManager {
	public static Map<String,String> userMap = new HashMap<String,String>();
	
	public String login(final String userName){
		WebContext webContext = WebContextFactory.get();
		ScriptSession ss = webContext.getScriptSession();
		System.out.println("login "+ss.getId());
		
		if(userMap.containsValue(userName)){
			return null;
		}
		
		if(userMap.containsKey(ss.getId())){
			return ss.getId();
		}
		ss.setAttribute("userId", ss.getId());
		userMap.put(ss.getId(), userName);
		Browser.withPageFiltered(webContext.getCurrentPage(), new ScriptSessionFilter(){
			public boolean match(ScriptSession ss) {
				String id = (String) ss.getAttribute("userId");
				if(id != null && !id.equals("")){
					
					
					ScriptBuffer script = new ScriptBuffer();
					 script.appendScript("insertMsg(")
					    .appendData("游客 "+userName+" 进入聊天室")
					    .appendScript(");");
					 ss.addScript(script);
					 
					 ScriptBuffer script2 = new ScriptBuffer();
					 script2.appendScript("freshMenu(")
					    .appendData(getMenu())
					    .appendScript(");");
					 ss.addScript(script2);
					 
					return true;
				}
				return false;
			}
		}, new Runnable(){
			public void run() {
				System.out.println(userName+" 登录 ");
			}
		});
		
		return ss.getId();
	}
	
	public String getMenu(){
		StringBuffer buffer = new StringBuffer("[");
		Set<String> set = userMap.keySet();
		Iterator<String> ite = set.iterator();
		while(ite.hasNext()){
			String key = ite.next();
			buffer.append("{name:'"+userMap.get(key)+"',id:'"+key+"'},");
		}
		buffer.append("{}]");
		System.out.println("刷新菜单信息:"+buffer.toString());
		return buffer.toString();
	}
	
	public void sendMsg(final String fName,final String from,final String to,final String msg){
		WebContext webContext = WebContextFactory.get();
		ScriptSession ss = webContext.getScriptSession();
		String userId = (String) ss.getAttribute("userId");
		if(userId == null){
			removeChildByValue(userMap,fName);
			ss.setAttribute("userId", ss.getId());
			userMap.put(ss.getId(), fName);
			Browser.withPageFiltered(webContext.getCurrentPage(), new ScriptSessionFilter(){
				public boolean match(ScriptSession ss) {
					String id = (String) ss.getAttribute("userId");
					
					if(id != null && !id.equals("")){
						ScriptBuffer script = new ScriptBuffer();
						 script.appendScript("setId(")
						    .appendData(ss.getId())
						    .appendScript(");");
						 ss.addScript(script);
						return true;
					}
					return false;
				}
			}, new Runnable(){
				public void run() {
					System.out.println("发送信息:"+msg);
				}
			});
		}
		
		
		String sid = ss.getId();
		System.out.println("sendMsg "+sid);
		if(!userMap.containsKey(sid)){
			if(userMap.containsValue(fName)){
				removeChildByValue(userMap,fName);
			}
			userMap.put(sid, fName);
			ss.setAttribute("userId", sid);
		}
		final String fromName = userMap.get(from);
		if(fromName == null || fromName.length() <= 0){
			return;
		}
		if(to.equals("all")){
			Browser.withPageFiltered(webContext.getCurrentPage(), new ScriptSessionFilter(){
				public boolean match(ScriptSession ss) {
					String id = (String) ss.getAttribute("userId");
					
					if(id != null && !id.equals("")){
						ScriptBuffer script = new ScriptBuffer();
						 script.appendScript("insertMsg(")
						    .appendData(fromName+" 发送给 所有人："+msg)
						    .appendScript(");");
						 ss.addScript(script);
						return true;
					}
					return false;
				}
			}, new Runnable(){
				public void run() {
					System.out.println("发送信息:"+msg);
				}
			});
			return;
		}
		
		final String toName = userMap.get(to);
		
		
		if(toName == null || toName.length() <= 0){
			Browser.withPageFiltered(webContext.getCurrentPage(), new ScriptSessionFilter(){
				public boolean match(ScriptSession ss) {
					String id = (String) ss.getAttribute("userId");
					
					if(id != null && !id.equals("")){
						if(from.equals(id)){
							ScriptBuffer script = new ScriptBuffer();
							 script.appendScript("insertMsg(")
							    .appendData(""+toName+" 不在线")
							    .appendScript(");");
							 ss.addScript(script);
							return true;
						}else if(to.equals(id)){
							
						}
					}
					return false;
				}
			}, new Runnable(){
				public void run() {
					System.out.println(""+toName+" 不在线");
				}
			});
			return;
		}
		Browser.withPageFiltered(webContext.getCurrentPage(), new ScriptSessionFilter(){
			public boolean match(ScriptSession ss) {
				String id = (String) ss.getAttribute("userId");
				
				if(id != null && !id.equals("")){
					if(from.equals(id)){
						ScriptBuffer script = new ScriptBuffer();
						 script.appendScript("insertMsg(")
						    .appendData("你 发送给 "+toName+": "+msg)
						    .appendScript(");");
						 ss.addScript(script);
						return true;
					}else if(to.equals(id)){
						ScriptBuffer script = new ScriptBuffer();
						 script.appendScript("insertMsg(")
						    .appendData(fromName+" 发送给 你："+msg)
						    .appendScript(");");
						 ss.addScript(script);
						return true;
					}
				}
				return false;
			}
		}, new Runnable(){
			public void run() {
				System.out.println("发送信息:"+msg);
			}
		});
	}
	
	
	public void layout(final String fName){
		WebContext webContext = WebContextFactory.get();
		ScriptSession ss = webContext.getScriptSession();
		final String key = (String) ss.getAttribute("userId");
		System.out.println("layout "+key+" "+ss.getId());
		ss.removeAttribute("userId");
		final String userName = userMap.get(key);
		if(userName == null){
			return;
		}
		userMap.remove(key);
		removeChildByValue(userMap, fName);
		Browser.withPageFiltered(webContext.getCurrentPage(), new ScriptSessionFilter(){
			public boolean match(ScriptSession ss) {
				String id = (String) ss.getAttribute("userId");
				if(id != null && !id.equals("")){
					ScriptBuffer script = new ScriptBuffer();
					script.appendScript("insertMsg(")
					    .appendData("游客 "+userName+" 退出聊天室")
					    .appendScript(");");
					ss.addScript(script);
					
					
					 
					ScriptBuffer script2 = new ScriptBuffer();
					script2.appendScript("freshMenu(")
					    .appendData(getMenu())
					    .appendScript(");");
					ss.addScript(script2);
					
					return true;
				}
				return false;
			}
		}, new Runnable(){
			public void run() {
				System.out.println("游客 "+userName+" 退出聊天室");
			}
		});
		
	}
	
	public boolean checkUser(final String userName){
		if(TookRoomManager.userMap.containsValue(userName)){
			return false;
		}
		return true;
	}
	
	public void removeChildByValue(Map<String,String> map,String value){
		Iterator<String> iterator = map.keySet().iterator();
		while(iterator.hasNext()){
			String key = (String)iterator.next();
			String str = map.get(key);
			if(map.get(key).equals(value)){
				iterator.remove();//删除key
				map.remove(str); //删除值 
				//break;//此处break应该去掉 因为value值可能重复
			}
		}
	}
}
