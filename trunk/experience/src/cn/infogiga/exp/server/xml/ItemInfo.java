package cn.infogiga.exp.server.xml;


import cindy.util.xml.XMLBean;
import cindy.util.xml.XMLElement;
import cindy.util.xml.XMLField;
import cindy.util.xml.XMLTypes;

@XMLElement(value="ITEM_INFO",type=XMLTypes.XML_UNROOT)
public class ItemInfo implements XMLBean{
	
	@XMLField(value="PKG",xmlType=XMLTypes.XML_ELEMENT)
	private String pkg = null;
	@XMLField(value="MENU_ID",xmlType=XMLTypes.XML_ELEMENT)
	private String menuId = null;
	@XMLField(value="FUNC_ID",xmlType=XMLTypes.XML_ELEMENT)
	private String funcId = null;
	@XMLField(value="ID",xmlType=XMLTypes.XML_ELEMENT)
	private String id = null;
	@XMLField(value="NAME",xmlType=XMLTypes.XML_ELEMENT)
	private String name = null;
	public String getPkg() {
		return pkg;
	}
	public void setPkg(String pkg) {
		this.pkg = pkg;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getFuncId() {
		return funcId;
	}
	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
