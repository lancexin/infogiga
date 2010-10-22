package cn.infogiga.exp.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class UploadController {
	
	private final static String SEP = ";";	//两个客户资料之间的分隔符号
	
	@RequestMapping(value = "/upload",params="customer")
	public void upload(HttpServletRequest request,
			HttpServletResponse response) throws IOException, BiffException{
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;		
        MultipartFile mFile  =  multipartRequest.getFile("userfile");  
        /*request.setCharacterEncoding("utf-8");*/
        response.setContentType("text/html;charset=utf-8");
        if(validateFile(mFile).equals("txt")){
        	File file = this.getFile(request, mFile);
        	try {
        		InputStreamReader read = new InputStreamReader (new FileInputStream(file),"utf-8");
				/*FileReader fileRreader = new FileReader(file);*/
				BufferedReader br = new BufferedReader(read);
				String temp = null;
				StringBuffer buffer = new StringBuffer();
				while ((temp = br.readLine()) != null) {
					
					buffer.append(temp).append(SEP);
				}
				String str = buffer.toString();
	
				System.out.println("上传的内容是："+buffer.toString());
				
				response.getWriter().print(buffer.toString());
			} catch (FileNotFoundException e) {
				response.getWriter().print("false");
				e.printStackTrace();
			} catch (IOException e) {
				response.getWriter().print("false");
				e.printStackTrace();
			}
        }else if(validateFile(mFile).equals("xls")){
        	File file = this.getFile(request, mFile);
        	Workbook wb = Workbook.getWorkbook(file);
        	StringBuffer buffer = new StringBuffer();
        	if(wb != null){
        		Sheet[] sheets = wb.getSheets();
        		if(sheets != null && sheets.length != 0){
        			for(int i=0;i<sheets.length;i++){
        				Sheet sheet=wb.getSheet(i);
        				int rows_len=sheet.getRows();
        				for(int j = 0;j < rows_len;j++){
        					Cell[] cells=sheet.getRow(j);
        					if(cells != null && cells.length != 0){
        						Cell cell1=cells[0];
        						Cell cell2=cells[1];
        						if(cell1 != null && cell2 != null && cell1.getContents() != null && cell2.getContents()!= null){
        							buffer.append(cell1.getContents()+" "+cell2.getContents()+";");
        						}
        					}
        				}
        				
        			}
        			//System.out.println(buffer.toString());
        			response.getWriter().print(buffer.toString());
        		}else{
        			response.getWriter().print("error");
        		}
        	}else{
        		response.getWriter().print("error");
        	}
  	
        }else{
			response.getWriter().print("error");
        }
	}
	
	//上传文件并返回文件的实例
	private File getFile(HttpServletRequest request, MultipartFile imgFile) {   
        File file = null;  
        try {
        	//将文件保存在临时文件夹下面         	
        	file = new File(System.getProperty("java.io.tmpdir")+"/"+imgFile.getOriginalFilename());
        	if(file.exists()){
        		file.delete();
        		
        	}
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
	private String validateFile(MultipartFile file) { 
		//判断flie的大小不能大于2000000
	    if(file.getSize()<0 || file.getSize() > 2000000){
	    	return "false";  
	    }           
	    String filename = file.getOriginalFilename();  
	    String extName = filename.substring(filename.lastIndexOf(".")).toLowerCase(); 
	    //file确定file的后缀名
	    if(extName.equals(".txt")){  
	        return "txt";  
	    }else if(extName.equals(".xls")){  
	        return "xls";  
	    }else{
	    	return "false";
	    }  
	} 
	
	public void sendSMS(HttpServletRequest request,
			HttpServletResponse response){
	}
}
