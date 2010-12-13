package cn.infogiga.sd.service;

import java.util.List;

import cindy.page.power.Node;
import cn.infogiga.pojo.Power;


public interface PowerService {
	
	public List<Node> getPower(Power power,String path);

	public List<Node> getBaseNode(String path);
	
}
