package cn.infogiga.exp.power;

import java.util.ArrayList;

import cindy.util.xml.XMLBean;
import cindy.util.xml.XMLElement;
import cindy.util.xml.XMLField;
import cindy.util.xml.XMLTypes;

@XMLElement(value="power",type=XMLTypes.XML_ROOT)
public class Power implements XMLBean{
	
	@XMLField(xmlType=XMLTypes.XML_LIST,mClass=Menu.class)
	ArrayList<Menu> menuList = null;

	public ArrayList<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(ArrayList<Menu> menuList) {
		this.menuList = menuList;
	}
}
