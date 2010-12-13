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

	public static String validatePNG(File file) { 
		if(!file.exists()){
			return null;
		}          
	    String filename = file.getName();
	    String extName = filename.substring(filename.lastIndexOf(".")).toLowerCase(); 
	    //file确定file的后缀名
	    if(extName.equals(".png")){  
	        return "png";  
	    }else{
	    	return null;
	    }  
	}
	

	public static String validateJPG(File file) { 
		if(!file.exists()){
			return null;
		}          
	    String filename = file.getName();
	    String extName = filename.substring(filename.lastIndexOf(".")).toLowerCase(); 
	    //file确定file的后缀名
	    if(extName.equals(".jpg")){  
	        return "jpg";  
	    }else{
	    	return null;
	    }  
	}
	

}
