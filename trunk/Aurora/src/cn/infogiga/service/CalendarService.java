package cn.infogiga.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.infogiga.bean.Customer;
import cn.infogiga.bean.Invitation;
import cn.infogiga.dao.AppointmentDAO;
import cn.infogiga.dao.CalendarDAO;
import cn.infogiga.util.DateUtil;
import cn.infogiga.util.StringToolkit;

public class CalendarService extends BaseService{
	private final static Log log = LogFactory.getLog(CalendarService.class);
	
	private CalendarDAO calendarDAO;
	private AppointmentDAO appointmentDAO;

	public void setAppointmentDAO(AppointmentDAO appointmentDAO) {
		this.appointmentDAO = appointmentDAO;
	}

	public void setCalendarDAO(CalendarDAO calendarDAO) {
		this.calendarDAO = calendarDAO;
	}
	
	public boolean addInvitation(){
		return false;
		
	}
	
	public String getInvitationByDay(String date){
		Date startDate = DateUtil.stringToDate(date+" 0:00:00",DateUtil.NOW_TIME);
		Date endDate = DateUtil.stringToDate(date+" 23:59:59",DateUtil.NOW_TIME);

		return doGetInvitation(startDate, endDate);
	}
	
	public String getInvitationByWeek(String date){
		Date startDate = DateUtil.getSunDay(date+" 00:00:00", DateUtil.NOW_TIME);
		Date endDate = DateUtil.getSaturDay(date+" 23:59:59",DateUtil.NOW_TIME);
		
		return doGetInvitation(startDate, endDate);
	}
	
	public String getInvitationByMonth(String date){
		Date start = DateUtil.getFirstDateOfMonth(date+" 00:00:00",DateUtil.NOW_TIME);
		Date startDate = DateUtil.getSunDay(start);
		Date end = DateUtil.getLastDateOfMonth(date+" 23:59:59",DateUtil.NOW_TIME);
		Date endDate = DateUtil.getSaturDay(end);
		
		return doGetInvitation(startDate, endDate);
	}
	
	public String doGetInvitation(Date startDate,Date endDate){
		List<Invitation> list = appointmentDAO.getInvitationByTime(startDate, endDate);
		String callback = "{\"issort\":true,\"start\":\"\\/Date("+startDate.getTime()+")\\/\",\"end\":\"\\/Date("+endDate.getTime()+")\\/\",\"error\":null,\"events\":";
		int size = list.size();
		Invitation invitation;
		String[] temp = new String[size];
		for(int i=0;i<size;i++){
			invitation = list.get(i);
			temp[i] = "["+invitation.getInvitationId()+",\""+invitation.getInvitationTitle()+"\",\"\\/Date("+invitation.getVisitTime().getTime()+")\\/\",\"\\/Date("+invitation.getEndTime().getTime()+")\\/\", 0,0,0,-1,1,null,\"\",\""+(invitation.getManagerByGuiderId()==null?"无":invitation.getManagerByGuiderId().getName())+"\",\""+(invitation.getManagerByTechnicianId()== null?"无":invitation.getManagerByTechnicianId().getName())+"\",\""+(invitation.getManagerByManagerId()==null?"无":invitation.getManagerByManagerId().getName())+"\",";
		
			Set set = invitation.getCustomers();
			
			int setSize = set.size();
			int j = 0;
			Iterator iterator = set.iterator();
			Customer customer;
			String[] temp2 = new String[setSize];
			while(iterator.hasNext()){
				customer = (Customer)iterator.next();
				temp2[j++] = "{\"id\":"+customer.getCustomerId()+",\"name\":\""+customer.getName()+"\",\"states\":"+customer.getSendStatus()+",\"phoneNumber\":\""+customer.getPhoneNumber()+"\",\"company\":\""+customer.getCompany()+"\"}";
				
			}
			temp[i] +=StringToolkit.arrayToString(temp2, ",");
			temp[i] +="]";
		}
		//System.out.println(callback+StringToolkit.arrayToString(temp, ",")+"}");
		return callback+StringToolkit.arrayToString(temp, ",")+"}";
	}
}
