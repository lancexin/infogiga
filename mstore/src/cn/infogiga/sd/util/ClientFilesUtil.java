package cn.infogiga.sd.util;



import java.io.File;
import java.io.IOException;

import cindy.util.FileMd5;

public class ClientFilesUtil {
	
	private static String outParentpath(String path,String parentPath){
		return path.replace(parentPath, "");
	}
	
	public static void exportClientFiles(File file,StringBuffer buffer,String parentPath){
		
		if(!file.exists()){
			return;
		}
		
		if(file.isFile()){
			if(file.getName() != null && file.getName().equals("Thumbs.db")){
				return;
			}
			
			try {
				String md5Code = FileMd5.getFileMD5String(file);
				buffer.append(outParentpath(file.getParent()+File.separator,parentPath+File.separator)+" <*> "+file.getName()+" <*> "+md5Code);
				buffer.append("\r\n");
				//System.out.println();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(file.isDirectory()){
			buffer.append(outParentpath(file.getAbsolutePath()+File.separator,parentPath+File.separator));
			buffer.append("\r\n");
			File[] files = file.listFiles();
			for(int i=0;i<files.length;i++){
				exportClientFiles(files[i],buffer,parentPath);
			}
		}
	}
}