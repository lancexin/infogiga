package cindy.page.hibernate;

import java.util.Iterator;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class CriteriaFactory {
	
	private static final Log log = LogFactory.getLog(CriteriaFactory.class);
	
	public static final int LIST = 101;
	
	public static final int COUNT = 102;
	
	CirteriaBean bean;
	
	private static CriteriaFactory factory = null;
	
	private CriteriaFactory(){}
	
	public static CriteriaFactory getCriteriaFactory(){
		if(factory == null){
			factory = new CriteriaFactory();
		}
		return factory;
	}
	
	 
	public synchronized Criteria get(CirteriaBean mBean,Criteria criteria,int type){
		bean = mBean;
		//Criteria criteria = session.createCriteria(mBean.clazz);
		if(type == LIST){
			setPage(criteria);
		}else if(type == COUNT){
			criteria.setProjection(Projections.rowCount());
		}
		doQuery(criteria);
		setOrder(criteria);
		setAlias(criteria);
		addEQArray(criteria);
		addLIKEArray(criteria);
		addBETWEENArray(criteria);
		return criteria;
	}
	
	public void addEQArray(Criteria criteria){
		Set<String> set = bean.eqArray.keySet();
		if(set.isEmpty()){
			log.debug("add query is empty");
			return;
		}
		Iterator<String> ite = set.iterator();
		while(ite.hasNext()){
			String key = ite.next();
			
			Object value = bean.eqArray.get(key);
			log.debug("addEQArray,key="+key+"  valye="+value);
			if(value != null){
				criteria.add(Restrictions.eq(key, value));
			}
		}
	}
	
	public void addLIKEArray(Criteria criteria){
		Set<String> set = bean.likeArray.keySet();
		if(set.isEmpty()){
			return;
		}
		Iterator<String> ite = set.iterator();
		while(ite.hasNext()){
			String key = ite.next();
			String value = (String) bean.likeArray.get(key);
			log.info("addLIKEArray,key="+key+"  valye="+value);
			if(value != null){
				criteria.add(Restrictions.like(key, "%"+value+"%"));
			}
		}
	}
	
	public void addBETWEENArray(Criteria criteria){
		Set<String> set = bean.betweenArray.keySet();
		if(set.isEmpty()){
			return;
		}
		Iterator<String> ite = set.iterator();
		while(ite.hasNext()){
			String key = ite.next();
			Object[] values = bean.betweenArray.get(key); 
			log.debug("addBETWEENArray,key="+key+"  value1="+values[0]+"   value2="+values[1]);
			if(values[0] != null && values[1] != null){
				criteria.add(Restrictions.between(key, values[0], values[1]));
			}
		}
	}
	
	private void setPage(Criteria criteria){
		 PageBean pbean = bean.page;
		// System.out.println("setPage:"+(pbean==null));
		 //System.out.println("start:"+pbean.getStart()+" limit:"+pbean.getLimit());
		 if(pbean != null){
			 criteria.setFirstResult(pbean.getStart());
			 criteria.setMaxResults(pbean.getLimit());
		 }
	}
	
	private void setAlias(Criteria criteria){
		//System.out.println(bean.alias.size());
		if(bean.alias.isEmpty()){
			return;
		}
		
		Set<String> set = bean.alias.keySet();
		Iterator<String> ite = set.iterator();
		while(ite.hasNext()){
			String key = ite.next();
			System.out.println("key:"+key+",value:"+bean.alias.get(key));
			criteria.createAlias(key,bean.alias.get(key));
		}
	}
	
	private void setOrder(Criteria criteria){
	//	System.out.println(bean.mOrder);
		if(bean.mOrder == null){
			return;
		}
		criteria.addOrder(Order.desc(bean.mOrder));
	}
	
	private void doQuery(Criteria criteria){
		
		int size = bean.queryList.size();
		//System.out.println("queryList size:"+size);
		CirteriaQuery query = null;
		for(int i=0;i<size;i++){
			query = bean.queryList.get(i);
			int type = query.getType();
			if(type == CirteriaQuery.IS_INT){
				Integer val = (Integer) query.getValue();
				if(val != -1){
					//System.out.println(val);
					String[] a = query.getAlias();
					if(a != null){
						for(int k=0;k<a.length;k+=2){
							bean.alias.put(a[k], a[k+1]);
						}
					}
					int mark = query.getMark();
					if(mark == CirteriaQuery.EQ){
						bean.eqArray.put(query.getPrame(), val);
					}else if(mark == CirteriaQuery.LIKE){
						bean.likeArray.put(query.getPrame(), val);
					}else if(mark == CirteriaQuery.BETWEED){
						bean.betweenArray.put(query.getPrame(), query.getValues());
					}
				}
			}else if(type == CirteriaQuery.IS_STRING){
				String val = (String) query.getValue();
				
				if(val != null && !"".equals(val)){
				//	System.out.println(val);
					String[] a = query.getAlias();
					if(a != null){
						for(int k=0;k<a.length;k+=2){
							bean.alias.put(a[k], a[k+1]);
						}
					}
					int mark = query.getMark();
					if(mark == CirteriaQuery.EQ){
						bean.eqArray.put(query.getPrame(), val);
					}else if(mark == CirteriaQuery.LIKE){
						bean.likeArray.put(query.getPrame(), val);
					}else if(mark == CirteriaQuery.BETWEED){
						bean.betweenArray.put(query.getPrame(), query.getValues());
					}
				}
			}else if(type == CirteriaQuery.IS_OBJECT){
				String[] a = query.getAlias();
				if(a != null){
					for(int k=0;k<a.length;k+=2){
						bean.alias.put(a[k], a[k+1]);
					}
				}
				int mark = query.getMark();
				if(mark == CirteriaQuery.EQ){
					bean.eqArray.put(query.getPrame(), query.getValue());
				}else if(mark == CirteriaQuery.LIKE){
					bean.likeArray.put(query.getPrame(), query.getValue());
				}else if(mark == CirteriaQuery.BETWEED){
					bean.betweenArray.put(query.getPrame(), query.getValues());
				}
			}
		}
	}
	
	
	
}
