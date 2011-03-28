package cn.infogiga.szqb.pdf;

import java.io.File;
import java.io.IOException;

public class PdfUtil {
	
	public synchronized static boolean pdf2Image(String fromUrl,String toUrl,int b,int z){
		File file = new File(fromUrl);
		if(!file.exists()){
			System.out.println("文件不存在");
			return false;
		}
		String commandText = "pdftoimage -z "+z+" -t png -b "+b+" -i "+fromUrl+" 1 -o "+toUrl;
		try {
			Runtime.getRuntime().exec(commandText);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
		
	public static void main(String[] args) {
		PdfUtil pdf = new PdfUtil();
		//NameChangeConverter converter = new NameChangeConverter();
		//converter.setThreadNumber(1);
		pdf.pdf2Image("d:\\a.pdf", "d:\\pdf\\",24,50);
	}
}
