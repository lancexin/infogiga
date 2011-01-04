package script;

import java.util.ArrayList;
import java.util.List;

import cindy.util.DateUtil;
import cn.infogiga.exp.pojo.Downloadstat;

public class ExcelUtil {
	public static List<RDdownloadExcel> getRDdownloadExcelList(List<Downloadstat> aList){
		List<RDdownloadExcel> list = new ArrayList<RDdownloadExcel>();
		
		int size = aList.size();
		Downloadstat downloadstat = null;
		for(int i=0;i<size;i++){
			downloadstat = aList.get(i);
			if(downloadstat.getEquipment().getTeam().getTeamId() == 1){//过滤掉测试组的数据
				continue;
			}
			
			/*if(downloadstat.getSoftinfo().getSoftCode() == null || (downloadstat.getSoftinfo().getSoftCode().trim()).length() <= 0){//过滤掉测试组的数据
				continue;
			}*/
			
			RDdownloadExcel excel = new RDdownloadExcel();
			excel.setTeamName(downloadstat.getEquipment().getTeam().getTeamName());
			excel.setEmpNo(downloadstat.getEmployee().getEmpNo());
			excel.setSoftCode(downloadstat.getSoftinfo().getSoftCode());
			excel.setPhonebrand(downloadstat.getPhonetype().getPhonebrand().getPhonebrandName());
			excel.setPhonetype(downloadstat.getPhonetype().getPhonetypeName());
			excel.setAddTime(DateUtil.getDateString(downloadstat.getAddTime(), DateUtil.NOW_TIME));
			excel.setPhoneNumber(downloadstat.getPhoneNumber()==null?"":downloadstat.getPhoneNumber());
			list.add(excel);
		}
		return list;
	}
}
