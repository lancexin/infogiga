package cn.infogiga.szqb.pdf;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class NameChangeConverter implements Runnable {
	private static List<NameChangeBean> converterList = new LinkedList<NameChangeBean>();
	
	private Integer threadNumber = 1;
	
	private static Thread[] threads;
	
	public void setThreadNumber(Integer threadNumber) {
		this.threadNumber = threadNumber;
	}
	
	public NameChangeConverter(){
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
				
				NameChangeBean bean = converterList.get(0);
				//File fromFile = new File(bean.getFromFile());
				if(bean.getFromFile().exists()){
					converterList.remove(0);
					handle(bean);
				}else{
					System.out.println(bean.getFromFile()+" 还没有生成,放弃~~~");
				}
			}
		}
	}
	public static void addConversion(NameChangeBean bean){
		synchronized (converterList) {
			converterList.add(converterList.size(),bean);
			converterList.notifyAll();
		}
	}
	
	public void handle(NameChangeBean bean){
		System.out.println("开始转换"+bean.getFromFile());
		//File from = new File(bean.getFromFile());
		//File to = new File(bean.getToFile());
		if(bean.getToFile().exists()){
			bean.getToFile().delete();
		}
		if(bean.getFromFile().exists()){
			boolean bl = bean.getFromFile().renameTo(bean.getToFile());
			if(!bl){
				converterList.add(bean);
			}
			System.out.println(bl);
			return;
		}
	}	
	
}
