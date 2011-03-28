package cn.infogiga.szqb.pdf;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import cn.infogiga.szqb.pojo.Page;
import cn.infogiga.szqb.pojo.Periodical;
import cn.infogiga.szqb.web.service.ManageService;

public class Pdf2ImageConverter implements Runnable{
	private static List<Integer> converterList = new LinkedList<Integer>();
	
	private Integer threadNumber = 1;
	
	private static Thread[] threads;
	
	public void setThreadNumber(Integer threadNumber) {
		this.threadNumber = threadNumber;
	}
	
	ManageService manageService;

	public Pdf2ImageConverter(){
		threads = new Thread[threadNumber];
		for(int i = 0;i < threadNumber;i++){
			threads[i] = new Thread(this);
			threads[i].start();
		}
	}
	
	public void run() {
		while(true){
			synchronized (converterList) {
				int size = converterList.size();
				if(size == 0){
					try {
						converterList.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					continue;
				}
				
				Integer id = converterList.remove(0);
				handle(id);
			}
		}
		
	}
	
	public static void addConversion(Integer id){
		synchronized (converterList) {
			converterList.add(converterList.size(),id);
			converterList.notifyAll();
		}
	}
	
	public void handle(Integer id){
		System.out.println(id);
		Periodical periodical = manageService.getManageDAO().findById(Periodical.class, id);
		int status = periodical.getStatus();
		if(status != 1){
			return;
		}
		Set pages = periodical.getPages();
		Iterator ite = pages.iterator();
		while(ite.hasNext()){
			Page page = (Page) ite.next();
			page.getOriginalUrl();
		}
		
	}	
}
