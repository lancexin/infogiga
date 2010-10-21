package cindy.page.power;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("power")
public class Menu implements Serializable,Cloneable{
	@XStreamAlias("code")
	String code;
	@XStreamAlias("name")
	String name;
	@XStreamAlias("item_list")
	List<Item> itemList;
	
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
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
}
