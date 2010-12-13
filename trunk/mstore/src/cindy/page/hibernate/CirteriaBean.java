package cindy.page.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CirteriaBean {
	public Map<String,String> alias = new HashMap<String,String>();
	
	public Map<String,Object> eqArray = new HashMap<String,Object>();
	
	public Map<String,Object> likeArray = new HashMap<String,Object>();
	
	public Map<String,Object[]> betweenArray = new HashMap<String,Object[]>();
	
	public List<CirteriaQuery> queryList = new ArrayList<CirteriaQuery>();
	
	public String mOrder = null;
	
	public PageBean page = null;
	
	public CirteriaBean(PageBean page){
		this.page = page;
	}
	
	public CirteriaBean(){}
	
	public CirteriaBean(PageBean page,String order){
		this.mOrder = order;
		this.page = page;
	}
	
	public CirteriaBean(String order){
		this.mOrder = order;
	}
	
	public void addQuery(CirteriaQuery cirteriaQuery){
		queryList.add(cirteriaQuery);
	}
	
	public void setPageBean(PageBean page){
		this.page = page;
	}
	
}
