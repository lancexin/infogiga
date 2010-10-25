package bean;

import java.io.Serializable;

/**
 * 报表统计信息
 * @author ya
 */
public class ResultBean implements Serializable {
	
	private static final long serialVersionUID = 20090429110425L;
	private String name;
	private int num;
	
	public ResultBean(String name, int num) {	
		this.name = name;
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}	
}
