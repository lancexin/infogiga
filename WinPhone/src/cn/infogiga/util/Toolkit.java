package cn.infogiga.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.util.Log;
import cn.infogiga.jsi.JSArray;

/**
 * 工具
 * @author chroya
 *
 */
public class Toolkit {
   
	/**
	 * 判断字符串是否空
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return str == null || "".equals(str);
	}
	
	/**
	 * 判断字符串是否相等
	 * @param str
	 * @param str2
	 * @return
	 */
	public static boolean eq(String str, String str2) {		 
		return str!=null && str2!=null && str.equals(str2);		
	}
	
	/**
	 * array中是否包含指定对象
	 * @param array		jsarray
	 * @param obj		对象
	 * @return
	 */
	public static boolean contains(JSArray array, Object obj) {
		for(int index=0; index<array.length(); index++) {
			if(array.get(index).equals(obj)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 写入内容到文件
	 * @param content
	 * @param path
	 * @return 
	 */
	public static boolean write(String content, String path){
		File file = new File(path);
		BufferedWriter writer;
		try {
			if(!file.exists()) {//不存在，则创建
				boolean isCreated = file.createNewFile();
				if(!isCreated) return false;
			}			
			writer = new BufferedWriter(new FileWriter(file), content.length());
			writer.write(content);
			writer.flush();
			writer.close();
		} catch (IOException e) {			
			Log.e("Toolkit", "Write File Fail:"+e);
			return false;
		}
		
		return true;
	}
	
	/**
     * 复制文件夹
     * @param src		源文件夹
     * @param dest		目标文件夹
     * @throws IOException
     */
    public static void copyFolder(File src, File dest) throws IOException {
    	if(!src.exists()) {
    		return;
    	}
    	if(!dest.exists()) {
    		dest.mkdir();
    	}
    	
    	File[] children = src.listFiles();
    	for(File file: children) {
    		if(file.isDirectory()) {
    			copyFolder(file, new File(dest.getPath()+File.separator+file.getName()));
    		} else {
    			copyFile(file, new File(dest.getPath()+File.separator+file.getName()));
    		}
    	}
    }
    
    /**
     * 复制文件
     * @param src		源文件
     * @param dest		目标文件
     * @throws IOException	创建或写入目标文件失败，会抛出IO异常
     */
    public static void copyFile(File src, File dest) throws IOException {
    	if(!src.exists()) {
    		return;
    	}
    	if(!dest.exists()) {
    		dest.createNewFile();
    	}
    	
    	BufferedReader reader = new BufferedReader(new FileReader(src));
    	BufferedWriter writer = new BufferedWriter(new FileWriter(dest));
    	String LF = System.getProperty("line.separator");
    	String buffer = "";
    	while((buffer = reader.readLine()) != null) {
    		writer.write(buffer+ LF);
    	}
    	reader.close();
    	writer.close();
    }
}