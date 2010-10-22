package cn.infogiga.exp.dao;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import cn.infogiga.exp.pojo.Downloadstat;
import cindy.util.page.hibernate.CirteriaBean;
import cindy.util.page.hibernate.CriteriaFactory;
import cindy.web.dao.HibernateDAO;
import cindy.web.pojo.POJO;

public class ExperienceDAO extends HibernateDAO{
	public void disRenewalDefault() {
		String queryString = "update Renewal set status = 0";
		getHibernateTemplate().bulkUpdate(queryString);
	}
	
public List<Downloadstat> getTopSoft(){
		
		return (List<Downloadstat>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				List<Downloadstat> downoladList = new  ArrayList<Downloadstat>();
				Query query =session.createSQLQuery("select s.softName,count(d.softID) from downloadstat d left join softinfo s on d.softID = s.softID group by s.softName order by count(d.softID) desc limit 0,10");
				List list = query.list();
				int size = list.size();
				for(int i=0;i<size;i++){
					Object[] o = (Object[]) list.get(i);
					BigInteger b =(BigInteger) o[1];
					Downloadstat downloadstat = new Downloadstat();
					downloadstat.setDownloadCount(b.intValue());
					downloadstat.setSoftName((String)o[0]);
					downoladList.add(downloadstat);
				}
				return downoladList;
			}
		});
	}
	
	public List<Downloadstat> getStarSoft(final Date startTime,final Date endTime){
		
		return (List<Downloadstat>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				List<Downloadstat> downoladList = new  ArrayList<Downloadstat>();
				Query query =session.createSQLQuery("select s.softName,count(d.softID) from downloadstat d left join softinfo s on d.softID = s.softID where d.addTime >= :startTime and d.addTime <= :endTime group by s.softName order by count(d.softID) desc limit 0,1");
				query.setDate("startTime", startTime);
				query.setDate("endTime", endTime);
				List list = query.list();
				int size = list.size();
				for(int i=0;i<size;i++){
					Object[] o = (Object[]) list.get(i);
					BigInteger b =(BigInteger) o[1];
					Downloadstat downloadstat = new Downloadstat();
					downloadstat.setDownloadCount(b.intValue());
					downloadstat.setSoftName((String)o[0]);
					downoladList.add(downloadstat);
				}
				return downoladList;
			}
		});
	}
	
	public  <T extends POJO> List<T> getListByPage(final Class<T> clazz,final CirteriaBean cBean){
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				CriteriaFactory factory = CriteriaFactory.getCriteriaFactory();
				//Criteria criteria = factory.get(cBean, session,CriteriaFactory.LIST);
				Criteria criteria = session.createCriteria(clazz);
				criteria = factory.get(cBean, criteria, CriteriaFactory.LIST);
				return criteria.list();
			}
		});
	}
	
	public <T extends POJO> Integer getCountByPage(final Class<T> clazz,final CirteriaBean cBean){
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				CriteriaFactory factory = CriteriaFactory.getCriteriaFactory();
				Criteria criteria = session.createCriteria(clazz);
				criteria = factory.get(cBean, criteria, CriteriaFactory.COUNT);
				//System.out.println(criteria.uniqueResult());
				return ((Integer) criteria.uniqueResult()).intValue();
			}
		});
	}
}
