package cindy.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ExcelCreatorUtil {

	private static final Log log = LogFactory.getLog(ExcelCreatorUtil.class);

	/**
	 * 导出统计表
	 * 
	 * @param os
	 * @param title
	 * @param list
	 * @throws IOException
	 * @throws WriteException
	 * @throws RowsExceededException
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static void exportExcel(OutputStream os, String[] title, List list)
			throws IOException, RowsExceededException, WriteException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		WritableWorkbook wbook = Workbook.createWorkbook(os);// 创建execl文件
		WritableSheet sheet = wbook.createSheet("工作表", 0);// 建立工作表名称

		// 设置Excel字体
		WritableFont wfont = new WritableFont(WritableFont.ARIAL, 12,
				WritableFont.BOLD, false,
				jxl.format.UnderlineStyle.NO_UNDERLINE, Colour.DARK_RED);
		WritableCellFormat titleFormat = new WritableCellFormat(wfont);
		titleFormat.setAlignment(Alignment.CENTRE);
		titleFormat.setBackground(Colour.GRAY_25);
		titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
			     jxl.format.Colour.BLACK);
		
		// 设置内容
		Iterator it = list.iterator();
		
		WritableFont wfont2 = new WritableFont(WritableFont.ARIAL, 12,
				WritableFont.NO_BOLD, false,
				jxl.format.UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		WritableCellFormat titleFormat2 = new WritableCellFormat(wfont2);
		titleFormat2.setAlignment(Alignment.CENTRE);
		titleFormat2.setBorder(Border.ALL, BorderLineStyle.THIN,
			     jxl.format.Colour.BLACK);
		
		//CellFormat cellFormat = new CellFormat();
		int i = 1;
		int size = 0;
		for(; it.hasNext(); i ++){
			Object obj = it.next();
			List<String> context = praseIterator(obj);
			size = context.size();
			for(int j=0; j <size; j ++){
				log.error("row:"+i+"	cell:"+j);
				String value = String.valueOf(context.get(j));
				Label lable = new Label(j+1, i+1,value );
				sheet.setColumnView(j+1, value.length()*2+2);
				lable.setCellFormat(titleFormat2);
				sheet.addCell(lable);
			}
		}
		Label labTotle = new Label(1,i+1,"总条数：");
		Label labTotleCount = new Label(2,i+1,(i-1)+"条");
		sheet.addCell(labTotle);
		sheet.addCell(labTotleCount);
		
		// 设置表头
		for (int k = 0; k < title.length; k++) {
			Label excelTitle = new Label(k+1, 1, title[k], titleFormat);
			
			int width = sheet.getColumnWidth(k+1);
			int changeWhdth = title[k].length()*2+2;
			if(changeWhdth >width){
				sheet.setColumnView(k+1, changeWhdth);
			}
			sheet.addCell(excelTitle);
		}
		
		//
		wbook.write(); // 写入文件
		wbook.close();
		os.close();
	}

	// 解析iterator
	public static List praseIterator(Object obj) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		List result = null;
		if (obj != null) {
			Class ownerClass = obj.getClass();
			Field[] fields = ownerClass.getDeclaredFields();
			result = new ArrayList<String>();
			for (Field f : fields) {
				String fieldName = f.getName();
				String methodName = fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				Method method = null;
				try {
					method = ownerClass.getMethod("get" + methodName);
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					System.out.println("找不到get" + methodName + "方法！");
				}
				//log.error(methodName + ":" +  method.invoke(obj));
				result.add(method.invoke(obj));
			}
		}
		return result;
	}
}
