package com.coder.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import com.coder.model.ConnectionSpeed;
import com.coder.model.DailyReport;



@Repository("connectionSpeedDao")
public class ConnectionSpeedDao  extends AbstractDao<Integer,ConnectionSpeed>{

	public Integer saveConnectionSpeed(ConnectionSpeed connectionSpeed)
	{
	return (Integer)super.persist(connectionSpeed);
   
		}
	public ConnectionSpeed getConnectionSpeedById(int id){
		ConnectionSpeed connectionSpeed=super.criteriaQuerryGetObjectById(id,"connectionSpeedId");
		return connectionSpeed;
		}
	public List<ConnectionSpeed> getConnectionSpeeds() {
		List<ConnectionSpeed> connectionSpeed=(List<ConnectionSpeed>)super.getAllEntity();
			return connectionSpeed;
		}
    

   public Boolean deleteFolder(ConnectionSpeed connectionSpeed){
	  Boolean tf= super.delete(connectionSpeed);
	  return tf;
   }
   public Boolean updateConnectionSpeed(ConnectionSpeed connectionSpeed){
	 Boolean tf=super.update(connectionSpeed);
	 return tf;
   }
   public double getAverageUploadSpeed(DailyReport dailyReport){
	   Double result = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Double> query = builder.createQuery(Double.class);
			Root<ConnectionSpeed> root = query.from(ConnectionSpeed.class);
			query.select(builder.avg(root.get("uploadSpeed")));
			query.where(builder.equal(root.get("dailyReport"),dailyReport));
			result = (Double) session.createQuery(query).getSingleResult();
			transaction.commit();
		} catch (Exception e) {
			result = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return result;
   }
   public double getAverageDownloadSpeed(DailyReport dailyReport){
	   Double result = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Double> query = builder.createQuery(Double.class);
			Root<ConnectionSpeed> root = query.from(ConnectionSpeed.class);
			query.select(builder.avg(root.get("downloadSpeed")));
			query.where(builder.equal(root.get("dailyReport"),dailyReport));
			result = (Double) session.createQuery(query).getSingleResult();
			transaction.commit();
		} catch (Exception e) {
			result = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return result;
   }
   
   public double getAveragePing(DailyReport dailyReport){
	   Double result = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Double> query = builder.createQuery(Double.class);
			Root<ConnectionSpeed> root = query.from(ConnectionSpeed.class);
			query.select(builder.avg(root.get("ping")));
			query.where(builder.equal(root.get("dailyReport"),dailyReport));
			result = (Double) session.createQuery(query).getSingleResult();
			transaction.commit();
		} catch (Exception e) {
			result = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return result;
   }
public Long getCountByDailyReport(DailyReport dailyReport) {
			Long result = null;
			Session session = null;
			Transaction transaction = null;
			try {
				session = sessionFactory.openSession();
				transaction = session.beginTransaction();
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Long> query = builder.createQuery(Long.class);
				Root<ConnectionSpeed> root = query.from(ConnectionSpeed.class);

				query.multiselect(builder.count(root.get("speedId")));
				query.where(builder.equal(root.get("dailyReport"),dailyReport));
				
				result = (Long) session.createQuery(query).getSingleResult();
				transaction.commit();
			} catch (Exception e) {
				
				if (transaction != null) {
					transaction.rollback();
				}
			} finally {
				session.close();
			}
			
			return result;
}
	
}
