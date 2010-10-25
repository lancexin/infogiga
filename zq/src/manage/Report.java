package manage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import tool.Config;
import bean.RegionBean;
import bean.ReportBean;
import bean.ResultBean;
import data.Database;

/**
 * 报表产生类
 * @author ya
 */
public class Report {
	
	private static Logger log = Logger.getLogger(Report.class.getName());
	
	private Database db = new Database();
	private HSSFWorkbook workBook = null;//工作簿
	private HSSFSheet sheet = null;//工作表
	private String fileName = ""; //保存的excel文件名
	private String[] imageNames = null; //图片的名字 
	
	private ArrayList<ReportBean> list = null;//数据库中读取的code表记录
	private ArrayList<ArrayList<ResultBean>> resultList = null;//统计信息
	
	/**
	 * 默认构造方法
	 */
	public Report() {
		this(0, Config.getValue("report.sheet.name"));	
	}
	
	/**
	 * @param index 放到第几张工作表上
	 * @param name  工作表的名字
	 */
	public Report(int index, String name) {
		workBook = new HSSFWorkbook();
		sheet = workBook.createSheet();
		workBook.setSheetName(index, name);	//设置第index张工作表的名字
	}
	
	/**
	 * 设置保存的文件名
	 */
	public void setFile(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * 设置list
	 * @param list
	 */
	public void setList(ArrayList<ReportBean> list) {
		this.list = list;
	}
	
	/**
	 * 设置图片的名字
	 * @param imageName
	 */
	public void setPictures(String[] imageNames) {
		this.imageNames = imageNames;
	}
	
	/**
	 * 保存到excel文件
	 */
	public boolean build() {
		if(list == null) return false;
		setResult();
		setTableTitle();
		setPicture();
		return setContent();
	}	
	
	/**
	 * 统计信息
	 * @param resultList
	 */
	public void setResultList(ArrayList<ArrayList<ResultBean>> resultList) {
		this.resultList = resultList;
	}
	
	/** 
	 * 统计信息
	 */
	private void setResult() {
		if(resultList == null) return;
		HSSFRow row = null;
		ArrayList<RegionBean> regions = db.getRegions();
		int index = 0;
		int rowId = 1;
		//单元格样式
		HSSFFont font = workBook.createFont();
		font.setColor(HSSFFont.COLOR_RED);
		HSSFCellStyle style = workBook.createCellStyle();
		style.setFont(font);
		//创建第一行
		row = sheet.createRow(0);
		row.createCell(0).setCellValue(new HSSFRichTextString("体验总计："));
		row.createCell(1).setCellValue(list.size());
		row.getCell(1).setCellStyle(style);
		row.createCell(2).setCellValue(new HSSFRichTextString("次"));
		//各个区域信息
		for(ArrayList<ResultBean> result: resultList) {
			row = sheet.createRow(rowId++);
			row.createCell(0).setCellValue(new HSSFRichTextString(regions.get(index++).getRegionName())); //区域的名字
			for(ResultBean bean: result) {
				row = sheet.createRow(rowId++);
				row.createCell(1).setCellValue(new HSSFRichTextString(bean.getName()));				
				row.createCell(2).setCellValue(bean.getNum());
				row.getCell(2).setCellStyle(style); //设置单元格样式，红色字体
				row.createCell(3).setCellValue(new HSSFRichTextString("次"));
			}
		}
	}
	
	/**
	 * 设置excel文件里面的表标题
	 */
	private void setTableTitle() {
		HSSFRow row = sheet.getRow(0);	
		int firstCell = 5;
		
		//单元格样式
		HSSFFont font = workBook.createFont();
		font.setFontName("黑体");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle style = workBook.createCellStyle();
		style.setFont(font);
		//填充内容
		row.createCell(firstCell+ 0).setCellValue(new HSSFRichTextString("序号"));
		row.createCell(firstCell+ 1).setCellValue(new HSSFRichTextString("地区"));
		row.createCell(firstCell+ 2).setCellValue(new HSSFRichTextString("营业厅"));
		row.createCell(firstCell+ 3).setCellValue(new HSSFRichTextString("设备"));
		row.createCell(firstCell+ 4).setCellValue(new HSSFRichTextString("系统"));
		row.createCell(firstCell+ 5).setCellValue(new HSSFRichTextString("体验"));
		row.createCell(firstCell+ 6).setCellValue(new HSSFRichTextString("开始时间"));
		row.createCell(firstCell+ 7).setCellValue(new HSSFRichTextString("结束时间"));
		row.createCell(firstCell+ 8).setCellValue(new HSSFRichTextString("手机号"));
		//设置标题样式
		for(int i=0; i<9; i++) {
			row.getCell(firstCell+ i).setCellStyle(style);
		}
	}
	
	/**
	 * 第二个SHEET中插入图片
	 * @param os
	 */
	private void setPicture() {
		if(imageNames == null) return;
		int sheetNum = workBook.getNumberOfSheets();
		HSSFSheet picSheet = null;
		if(sheetNum < 2) {
			picSheet = workBook.createSheet();
		}
		workBook.setSheetName(1, "图表");
		
		HSSFPatriarch patriarch = picSheet.createDrawingPatriarch();
		for(int i=0; i<imageNames.length; i++) {
			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 512, 255,
					(short) 2, 2+ 24*i, (short) 10, 24+ 24*i);
			anchor.setAnchorType(2);
			// 插入图片
			patriarch.createPicture(anchor, 
					workBook.addPicture(toByteArrayStream(imageNames[i]).toByteArray(),
							HSSFWorkbook.PICTURE_TYPE_JPEG));
		}
	}
	
	/**
	 * 将文件转换为ByteArrayOutputStream
	 * @return
	 */
	private ByteArrayOutputStream toByteArrayStream(String imageName) {
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream bais = new ByteArrayOutputStream();
		FileInputStream fos;
		try {
			fos = new FileInputStream(new File(imageName));
			while(fos.read(buffer) != -1) {
				bais.write(buffer);
			}
		} catch (FileNotFoundException e) {
			log.error("文件未找到"+ e);
		} catch (IOException e) {
			log.error("IO异常："+ e);
		}
		return bais;
	}
	/**
	 * 生成内容
	 * @return 是否成功
	 */
	private boolean setContent() {
		FileOutputStream fo = null;
		HSSFRow row = null;
		boolean isOk = false;//读写成功与否
		int firstCell = 5;
		
		//生成内容
		for(int rowId=1; rowId<list.size(); rowId++) {
			row = sheet.getRow(rowId)==null?sheet.createRow(rowId):sheet.getRow(rowId);
			row.createCell(firstCell+ 0).setCellValue(rowId);
			row.createCell(firstCell+ 1).setCellValue(new HSSFRichTextString(list.get(rowId).getAddressName()));
			row.createCell(firstCell+ 2).setCellValue(new HSSFRichTextString(list.get(rowId).getSellingName()));
			row.createCell(firstCell+ 3).setCellValue(new HSSFRichTextString(list.get(rowId).getEquipmentName()));
			row.createCell(firstCell+ 4).setCellValue(new HSSFRichTextString(list.get(rowId).getSystemName()));
			row.createCell(firstCell+ 5).setCellValue(new HSSFRichTextString(list.get(rowId).getOperateName()));
			row.createCell(firstCell+ 6).setCellValue(new HSSFRichTextString(list.get(rowId).getTime()));
		}
		for(int col=0; col<14; col++) { //自动调整列宽
			sheet.autoSizeColumn((short)col);			
		}
		//写入
		try {
			fo = new FileOutputStream(fileName);
			workBook.write(fo);//写入
			isOk = true;
		} catch (FileNotFoundException e) {
			isOk = false;
			log.error("文件未找到");
		} catch (IOException e) {
			isOk = false;
			log.error("IO异常，文件读写失败");
		}
		finally {
			try {
				fo.flush();
				fo.close();
			} catch (IOException e) {
				log.error("文件流关闭失败");
			}
		}
		return isOk; 
	}
}
