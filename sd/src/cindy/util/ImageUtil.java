package cindy.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageUtil {
	public static File cut(int x,int y,int w,int h,String path,String target,String formatName){
		FileInputStream fis = null;
		ImageInputStream iis = null;
		try {
			File file = new File(target);
			fis = new FileInputStream(path);
			iis = ImageIO.createImageInputStream(fis);
			Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(formatName);
			ImageReader reader = iterator.next();
			reader.setInput(iis, true);
			ImageReadParam imagereadparam = reader.getDefaultReadParam();
			Rectangle rectangle = new Rectangle(x, y, w, h);
			imagereadparam.setSourceRegion(rectangle);
			BufferedImage bufferedimage = reader.read(0, imagereadparam);
			ImageIO.write(bufferedimage, formatName, file);
			return file;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if (fis != null)
					fis.close();
				if (iis != null)     
		            iis.close();  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}     
		} 
		return null;
	}
	
	public static String validateFile(File file) { 
		if(!file.exists()){
			return null;
		}          
	    String filename = file.getName();
	    String extName = filename.substring(filename.lastIndexOf(".")).toLowerCase(); 
	    //file确定file的后缀名
	    if(extName.equals(".png") || extName.equals(".PNG")){  
	        return "png";  
	    }else if(extName.equals(".jpg") || extName.equals(".JPG")){  
	        return "jpg";  
	    }else{
	    	return null;
	    }  
	}
	
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
		if(file.exists()){
			return file.delete();
		}
		return false;
	}
}
