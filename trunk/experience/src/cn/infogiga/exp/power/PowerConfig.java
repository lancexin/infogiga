package cn.infogiga.exp.power;

import java.io.File;
import java.util.ArrayList;

import cindy.util.xml.XMLBeanUtil;

public class PowerConfig {
	public static final String POWER_CONFIG = "WEB-INF/power-config.xml";
	
	public static Power power = null;
	
	private static PowerConfig powerConfig = null;
	
	private static XMLBeanUtil beanUtil;
	
	private PowerConfig(){}
	
	public static PowerConfig getInstence(){
		if(powerConfig == null){
			powerConfig = new PowerConfig();
			beanUtil = new XMLBeanUtil();
		}
		return powerConfig;
	}
	
	public Power getBasePower(String path){
		if(power == null){
			//power = new Power();
			File file = new File(path);
			try {
				power = beanUtil.getXMLBeanByFile(Power.class, file);
				System.out.println(power == null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return power;
		}
		return power;
	}

	
	public ArrayList<Menu> getPower(String path,String powers){
		Power base = getBasePower(path);
		if(powers == null){
			return  new ArrayList<Menu>();
		}
		String[] power = powers.split(",");
		ArrayList<Menu> menuList = base.getMenuList();
		
		ArrayList<Menu> newMenuList = new ArrayList<Menu>();
		
		int menuSize = menuList.size();
		Menu menu = null;
		ArrayList<Item> itemList =null;
		Item item = null;
		int itemSize = 0;
		int powerSize = power.length;
		for(int i=0;i<menuSize;i++){
			menu = menuList.get(i);
			itemList = menu.getItemList();
			itemSize = itemList.size();
			
			ArrayList<Item> mItemList = new ArrayList<Item>();
			
			for(int j=0;j<itemSize;j++){
				item = itemList.get(j);
				for(int k=0;k<powerSize;k++){
					if(power[k].equals(item.getCode())){
						Item mItem = (Item) item.clone();
						mItemList.add(mItem);
						break;
					}
				}
			}
			if(mItemList.size() !=0){
				Menu mMenu = new Menu();
				mMenu.setCode(menu.getCode());
				mMenu.setName(menu.getName());
				mMenu.setItemList(mItemList);
				newMenuList.add(mMenu);
			}
		}
		
		return newMenuList;
	}
}
