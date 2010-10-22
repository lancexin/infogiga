package cn.infogiga.exp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

import cn.infogiga.exp.pojo.Downloadtype;
import cn.infogiga.exp.pojo.Employee;
import cn.infogiga.exp.pojo.Equipment;
import cn.infogiga.exp.pojo.Menu;
import cn.infogiga.exp.pojo.Phonebrand;
import cn.infogiga.exp.pojo.Scene;
import cn.infogiga.exp.pojo.Team;
import cn.infogiga.exp.service.ExpService;

public class QueryController {

	ExpService expService;

	public void setExpService(ExpService expService) {
		this.expService = expService;
	}
	
	@RequestMapping(value = "/query",params="statistics")
	public String queryStatistics(HttpServletRequest request,
			HttpServletResponse response){
		List<Menu> menuList = expService.findAll(Menu.class);
		List<Scene> sceneList = expService.findAll(Scene.class);
		List<Team> teamList = expService.findByProperty(Team.class,"status",1);
		List<Employee> employeeList = expService.findByProperty(Employee.class,"status",1);
		List<Equipment> equipmentList = expService.findByProperty(Equipment.class,"status",1);
	
		request.setAttribute("menuList", menuList);
		request.setAttribute("sceneList", sceneList);
		request.setAttribute("teamList", teamList);
		request.setAttribute("employeeList", employeeList);
		request.setAttribute("equipmentList", equipmentList);
		return "statistics/statistics_query";
	}
	
	@RequestMapping(value = "/query",params="downloadstat")
	public String queryDownloadstat(HttpServletRequest request,
			HttpServletResponse response){
		//List<Employee> employeeList = expService.findAll(Employee.class);
		List<Employee> employeeList = expService.findByProperty(Employee.class,"status",1);
		request.setAttribute("employeeList", employeeList);
		List<Downloadtype> downloadtypeList = expService.findAll(Downloadtype.class);
		request.setAttribute("downloadtypeList", downloadtypeList);
		List<Equipment> equipmentList = expService.findByProperty(Equipment.class,"status",1);
		request.setAttribute("equipmentList", equipmentList);
		
		List<Phonebrand>  phonebrandList = expService.findAll(Phonebrand.class);
		request.setAttribute("phonebrandList", phonebrandList);
		
		List<Team> teamList = expService.findByProperty(Team.class,"status",1);
		request.setAttribute("teamList", teamList);
		return "downloadstat/downloadstat_query";
	}
	
	@RequestMapping(value = "/query",params="team")
	public String queryTeam(HttpServletRequest request,
			HttpServletResponse response){
		
		return "team/team_query";
	}
	

	@RequestMapping(value = "/query",params="equipment")
	public String queryEquipment(HttpServletRequest request,
			HttpServletResponse response){
		List<Team> teamList = expService.findAll(Team.class);
		request.setAttribute("teamList", teamList);
		return "equipment/equipment_query";
	}

	@RequestMapping(value = "/query",params="employee")
	public String queryEmployee(HttpServletRequest request,
			HttpServletResponse response){
		List<Team> teamList = expService.findAll(Team.class);
		request.setAttribute("teamList", teamList);
		return "employee/employee_query";
	}
	
	
}
