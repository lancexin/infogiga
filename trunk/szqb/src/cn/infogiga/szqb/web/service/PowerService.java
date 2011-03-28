package cn.infogiga.szqb.web.service;

import java.util.List;

import cn.infogiga.szqb.pojo.Power;

import cindy.springframework.power.Node;



public interface PowerService {
	
	public List<Node> getPower(Power power,String path);

	public List<Node> getBaseNode(String path);
	
}
