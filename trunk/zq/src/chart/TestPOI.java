package chart;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class TestPOI {
	
	ByteArrayOutputStream os = null;
	TimeSeriesChartDemo1 de = new TimeSeriesChartDemo1();
	
	public TestPOI() {
		this.os = de.getOutputStream();
	}
	
	public static void main(String[] args) {
		FileOutputStream fileOut = null;
		TestPOI tp = new TestPOI();
		
		try {
			// 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
			/*ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			bufferImg = ImageIO.read(new File("d:/PieChart.jpg"));
			ImageIO.write(bufferImg, "jpg", byteArrayOut);*/

			// 创建一个工作薄
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet1 = wb.createSheet("new sheet");
			// HSSFRow row = sheet1.createRow(2);
			HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();
			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 512, 255,
					(short) 2, 2, (short) 10, 24);
			anchor.setAnchorType(2);
			// 插入图片
			patriarch.createPicture(anchor, wb.addPicture(tp.os.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));

			fileOut = new FileOutputStream("d:/workbook.xls");
			// 写入excel文件
			wb.write(fileOut);
			fileOut.close();

		} catch (IOException io) {
			io.printStackTrace();
			System.out.println("io error :  " + io.getMessage());
		} finally {
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
