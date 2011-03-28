package cindy.springframework.power;

import java.util.List;

public class Node {
	String text;
	boolean leaf;
	List<Node> children;
	String url;
	String code;
	boolean isframe;
	public boolean isIsframe() {
		return isframe;
	}
	public void setIsframe(boolean isframe) {
		this.isframe = isframe;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public List<Node> getChildren() {
		return children;
	}
	public void setChildren(List<Node> children) {
		this.children = children;
	}
}
