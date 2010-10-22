package cn.infogiga.exp.power;

import java.util.ArrayList;

import cindy.util.xml.XMLBean;
import cindy.util.xml.XMLElement;
import cindy.util.xml.XMLField;
import cindy.util.xml.XMLTypes;

@XMLElement(value="menu",type=XMLTypes.XML_UNROOT)
public class Menu implements XMLBean{
	@XMLField(value="code",xmlType=XMLTypes.XML_ELEMENT)
	private String code = null;
	@XMLField(value="name",xmlType=XMLTypes.XML_ELEMENT)
	private String name = null;
	@XMLField(value="item_list",xmlType=XMLTypes.XML_LIST,mClass=Item.class)
	ArrayList<Item> itemList = null;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Item> getItemList() {
		return itemList;
	}
	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}
}
