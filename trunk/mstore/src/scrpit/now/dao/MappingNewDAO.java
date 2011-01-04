package scrpit.now.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import cn.infogiga.exp.pojo.Downloadstat;
import cn.infogiga.pojo.Softdownloadstat;
import cn.infogiga.sd.dto.JsonSoftDownloadStat;
import cindy.page.beanutils.MyBeanUtils;
import cindy.util.Code;
import cindy.web.AbstractHibernateDAO;

public class MappingNewDAO extends AbstractHibernateDAO {

	public List<Softdownloadstat> getDownloadStatByDate(final Date startTime,final Date endTime){
		return (List<Softdownloadstat>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				//session.
				Criteria criteria = session.createCriteria(Softdownloadstat.class);
				criteria.add(Restrictions.between("addTime", startTime, endTime));
				criteria.createAlias("soft", "si");
				 
				//criteria.add(Restrictions.);
				return criteria.list();
			}
		});
	}
	
	public List<JsonSoftDownloadStat> getJsonDownloadStatByDate(final Date startTime,final Date endTime){
		return (List<JsonSoftDownloadStat>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				//session.
				Criteria criteria = session.createCriteria(Softdownloadstat.class);
				criteria.add(Restrictions.between("addTime", startTime, endTime));
				criteria.createAlias("soft", "si");
				 
				List<JsonSoftDownloadStat> list = MyBeanUtils.copyListProperties(criteria.list(), JsonSoftDownloadStat.class);
				return list;
			}
		});
	}
	
	public Boolean updatePhonenumber(){
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
				//session.
				Criteria criteria = session.createCriteria(Softdownloadstat.class);
				//criteria.add(Restrictions.isNull("phoneNumber"));
				//criteria.createAlias("soft", "si");
				List list = criteria.list();
				
				int size = list.size();
				System.out.println("size: "+size);
				Softdownloadstat ds = null;
				for(int i = 0;i<size;i++){
					String code = Code.getCode(12).toUpperCase();
					ds = (Softdownloadstat) list.get(i);
					if(ds.getPhoneNumber() == null || ds.getPhoneNumber().length() == 0){
						
						ds.setPhoneNumber(code);
						session.update(ds);
						System.out.println("update phoneNumber to "+code);
					}
				}
				
				return true;
			}
		});
	}
}
