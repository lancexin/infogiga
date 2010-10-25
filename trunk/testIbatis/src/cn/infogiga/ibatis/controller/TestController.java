package cn.infogiga.ibatis.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.infogiga.ibatis.pojo.User;
import cn.infogiga.ibatis.service.TestService;

@Controller
public class TestController {
	@Autowired
	TestService testService;
	
	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	@RequestMapping(value = "/m")
	public String test(HttpServletRequest request,HttpServletResponse response) throws IOException{
		User user = testService.getIbatisDao().findUserById(1);
		request.setAttribute("item", user);
		return "success";
	}
}
