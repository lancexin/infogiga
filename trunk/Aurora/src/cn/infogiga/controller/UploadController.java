package cn.infogiga.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import sun.util.logging.resources.logging;

//这是一个ajax上传的例子 url：http://localhost:8080/online/uploadTest.html
//只能上传后缀名为txt的文件
@Controller
public class UploadController {
	private final static String SEP = ";";	//两个客户资料之间的分隔符号
	
	@RequestMapping("/upload.up")
	public void upload(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;		
        MultipartFile mFile  =  multipartRequest.getFile("userfile");  
        response.setContentType("text/html;charset=utf-8");
        if(validateFile(mFile)){
        	File file = this.getFile(request, mFile);
        	try {
				FileReader fileRreader = new FileReader(file);
				BufferedReader br = new BufferedReader(fileRreader);
				String temp = null;
				StringBuffer buffer = new StringBuffer();
				while ((temp = br.readLine()) != null) {
					buffer.append(temp).append(SEP);
				}
				String str = buffer.toString();
				//str = new String(str.getBytes("ISO-8859-1"),"utf-8");
				System.out.println("上传的内容是："+buffer.toString());
				//String upstr ="<html><head><title>a</title></head><body><script type='text/javascript'>window.parent.Next.uploadSuccess('"+str+"');</script></body></html>";
				response.getWriter().print(buffer.toString());
			} catch (FileNotFoundException e) {
				response.getWriter().print("false");
				e.printStackTrace();
			} catch (IOException e) {
				response.getWriter().print("false");
				e.printStackTrace();
			}
        }else{
			response.getWriter().print("error");
        }
	}
	//上传文件并返回文件的实例
	private File getFile(HttpServletRequest request, MultipartFile imgFile) {   
        File file = null;  
        try {
        	//将文件保存在upload文件夹下面         	
        	file = new File(System.getProperty("java.io.tmpdir")+"/"+imgFile.getOriginalFilename());
			imgFile.transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
        return file;  
    }
	  
	//验证文件是否符合标准
	private boolean validateFile(MultipartFile file) { 
		//判断flie的大小不能大于2000000
	    if(file.getSize()<0 || file.getSize() > 2000000){
	    	return false;  
	    }           
	    String filename = file.getOriginalFilename();  
	    String extName = filename.substring(filename.lastIndexOf(".")).toLowerCase(); 
	    //file确定file的后缀名
	    if(extName.equals(".txt")){  
	        return true;  
	    }else{  
	        return false;  
	    }  
	}  
}
