package cindy.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileUtil {
	public static boolean copyFile(java.io.File filefrom, java.io.File fileto,
			boolean rewrite) {
		if (!filefrom.exists()) {
			System.out.println("文件不存在");
			return false;
		}
		if (!filefrom.isFile()) {
			System.out.println("不能够复制文件夹");
			return false;
		}
		if (!filefrom.canRead()) {
			System.out.println("不能够读取需要复制的文件");
			return false;
		}
		if (!fileto.getParentFile().exists()) {
			fileto.getParentFile().mkdirs();
		}
		if (fileto.exists() && rewrite) {
			fileto.delete();
		}
		try {
			java.io.FileInputStream fosfrom = new java.io.FileInputStream(
					filefrom);
			java.io.FileOutputStream fosto = new java.io.FileOutputStream(fileto);
			byte bt[] = new byte[1024];
			int c;
			while ((c = fosfrom.read(bt)) > 0) {
				fosto.write(bt, 0, c);
			}
			fosfrom.close();
			fosto.close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}


	public static boolean copyFile(String from, String to) {
		java.io.File filefrom = new java.io.File(from);
		java.io.File fileto = new java.io.File(to);
		return copyFile(filefrom, fileto, true);
	}
	
	public static boolean delete(File file){
		if(file == null){
			return false;
		}
		
		/*if(file.){
			
		}*/
		if(file.exists()){
			return file.delete();
		}
		return false;
	}
	
	public static void deleteAllFile(File file){
		if(!file.exists()){
			return;
		}
		if(!file.isDirectory()){
			file.delete();
			return;
		}
		File[] fs = file.listFiles();
		for(int i=0;i<fs.length;i++){
			deleteAllFile(fs[i]);
		}
		file.delete();
	}
	
	public static void addFoler(String url){
		File file = new File(url);
		if(file.exists()){
			if(file.isDirectory()){
				return;
			}
			file.delete();
		}
		file.mkdir();
	}
	
	public static void addTxtFile(String url,String name,String text){
		File file = new File(url+"/"+name+".txt");
		if(file.exists()){
			delete(file);
		}
		try {
			file.createNewFile();
			
			java.io.FileOutputStream fis = new java.io.FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fis);
			osw.write(text);
			osw.flush();
			osw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void copyAllFile(String from,String to){
		File fileFrom = new File(from);
		File fileTo = new File(to);
		if(!fileFrom.exists()){
			return;
		}
		if(fileFrom.isDirectory()){
			
			if(!fileTo.exists()){
				fileTo.mkdir();
			}
			File[] lf = fileFrom.listFiles();
			for(int i=0;i<lf.length;i++){
				String toStr = fileTo.getAbsolutePath()+"\\"+lf[i].getName();
				copyAllFile(lf[i].getAbsolutePath(),toStr);
			}
		}else{
			FileUtil.copyFile(fileFrom,fileTo,true);
		}
	}
}
