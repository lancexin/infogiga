package cn.infogiga.sd.view;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.web.servlet.view.document.AbstractJExcelView;

public class JxlExcelView extends AbstractJExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> map,
			WritableWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		WritableSheet sheet = workbook.getSheet(0);
		sheet.setName("工作表");
		//sheet.
	}
	
	public List praseIterator(Object obj)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
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
				// log.error(methodName + ":" + method.invoke(obj));
				result.add(method.invoke(obj));
			}
		}
		return result;
	}
}
