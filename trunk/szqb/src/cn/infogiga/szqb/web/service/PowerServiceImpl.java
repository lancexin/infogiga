package cn.infogiga.szqb.web.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import cindy.springframework.power.Item;
import cindy.springframework.power.Menu;
import cindy.springframework.power.Node;

@Component("powerService")
public class PowerServiceImpl implements PowerService{
	
	private static cindy.springframework.power.Power basePoser;

	public List<Node> getPower(cn.infogiga.szqb.pojo.Power power,String path) {
		
		
		
		cindy.springframework.power.Power base = getBasePower(path);
		
		String[] powers = power.getPowerValue().split(",");
		List<cindy.springframework.power.Menu> menuList = base.getMenuList();
		
		List<Node> nodeList = new ArrayList<Node>();
		
		int menuSize = menuList.size();
		Menu menu = null;
		List<cindy.springframework.power.Item> itemList =null;
		cindy.springframework.power.Item item = null;
		int itemSize = 0;
		int powerSize = powers.length;
		for(int i=0;i<menuSize;i++){
			menu = menuList.get(i);
			itemList = menu.getItemList();
			itemSize = itemList.size();
			
			List<Node> mNodeList = new ArrayList<Node>();
			
			for(int j=0;j<itemSize;j++){
				item = itemList.get(j);
				for(int k=0;k<powerSize;k++){
					if(powers[k].equals(item.getCode())){
						//cindy.page.power.Item mItem = (cindy.page.power.Item) item.clone();
						//mItemList.add(mItem);
						Node n = new Node();
						n.setLeaf(true);
						n.setText(item.getName());
						n.setUrl(item.getUrl());
						n.setCode(item.getCode());
						n.setIsframe(item.isIsframe());
						mNodeList.add(n);
						break;
					}
				}
			}
			if(mNodeList.size() !=0){
				Node e = new Node();
				e.setChildren(mNodeList);
				e.setLeaf(false);
				e.setText(menu.getName());
				e.setCode(menu.getCode());
				nodeList.add(e);
				//mMenu.setCode(menu.getCode());
				//mMenu.setName(menu.getName());
				//mMenu.setItemList(mItemList);
				//newMenuList.add(mMenu);
			}
		}
		return nodeList;
	}
	
	
	private cindy.springframework.power.Power getBasePower(String path){
		if(basePoser == null){
			XStream xstream = new XStream(new DomDriver());//创建XStream 对象
			xstream.alias("power",cindy.springframework.power.Power.class);//将XML根元素对应类
			xstream.autodetectAnnotations(true);
			try {
				basePoser = (cindy.springframework.power.Power)xstream.fromXML(new FileInputStream(new File(path)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return basePoser;
	}


	public List<Node> getBaseNode(String path) {
		cindy.springframework.power.Power base = getBasePower(path);
		List<Node> baseNode = new ArrayList<Node>();
		List<Menu> menuList = base.getMenuList();
		int menuListSize = menuList.size();
		Menu menu;
		cindy.springframework.power.Item item;
		List<Item> itemList;
		int itemListSize = 0;
		for(int i=0;i<menuListSize;i++){
			menu = menuList.get(i);
			itemList = menu.getItemList();
			itemListSize = itemList.size();
			for(int j=0;j<itemListSize;j++){
				item = itemList.get(j);
				Node node = new Node();
				node.setCode(item.getCode());
				node.setText(item.getName());
				node.setLeaf(true);
				baseNode.add(node);
			}
		}
		
		return baseNode;
	}

}
