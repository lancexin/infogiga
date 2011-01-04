package cn.infogiga.exp.quartz;

import java.util.ArrayList;
import java.util.List;

import cindy.util.DateUtil;
import cn.infogiga.pojo.Softdownloadstat;

public class ExcelUtil {
	public static List<RDdownloadExcel> getRDdownloadExcelList(List<Softdownloadstat> aList){
		List<RDdownloadExcel> list = new ArrayList<RDdownloadExcel>();
		
		int size = aList.size();
		
		Softdownloadstat downloadstat = null;
		for(int i=0;i<size;i++){
			downloadstat = aList.get(i);
			if(downloadstat.getEquipment().getBissinusshall().getId() == 1){//过滤掉测试组的数据
				
				continue;
			}
			
			if(downloadstat.getSoft().getSoftCode() == null || (downloadstat.getSoft().getSoftCode().trim()).length() <= 0){//过滤掉测试组的数据
				
				continue;
			}
			//System.out.println("add one .....");
			RDdownloadExcel excel = new RDdownloadExcel();
			excel.setTeamName(downloadstat.getEquipment().getBissinusshall().getHallName());
			excel.setEmpNo(downloadstat.getUsers().getUserName());
			excel.setSoftCode(downloadstat.getSoft().getSoftCode());
			excel.setPhonebrand(downloadstat.getPhonetype().getPhonebrandcategory().getPhonebrand().getPhonebrandName());
			excel.setPhonetype(downloadstat.getPhonetype().getPhonetypeName());
			excel.setAddTime(DateUtil.getDateString(downloadstat.getAddTime(), DateUtil.NOW_TIME));
			excel.setPhoneNumber(downloadstat.getPhoneNumber()==null?"":downloadstat.getPhoneNumber());
			list.add(excel);
		}
		return list;
	}
}
