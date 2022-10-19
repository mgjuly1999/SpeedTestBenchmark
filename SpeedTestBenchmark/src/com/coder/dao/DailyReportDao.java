package com.coder.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import com.coder.model.DailyReport;
import com.coder.util.MyDate;



@Repository("dailyReportDao")
public class DailyReportDao  extends AbstractDao<Integer,DailyReport>{

	public Integer saveDailyReport(DailyReport dailyReport)
	{
	return (Integer)super.persist(dailyReport);
   
		}
	
	public DailyReport getDailyReportById(int id){
		DailyReport dailyReport=super.criteriaQuerryGetObjectById(id,"reportId");
		return dailyReport;
		}
	public List<DailyReport> getDailyReports() {
		  
		
			Session s=sessionFactory.openSession();
			CriteriaBuilder builder=s.getCriteriaBuilder();
			CriteriaQuery<DailyReport> querry=builder.createQuery(DailyReport.class);
			Root<DailyReport> root=querry.from(DailyReport.class);
			querry.select(root);
			querry.orderBy(builder.asc(root.get("reportId")));
			Query<DailyReport> q=s.createQuery(querry);
			List<DailyReport> list=q.getResultList();
			s.close();
			return list;
	    	
		}
    
  

   public Boolean deleteDailyReport(DailyReport dailyReport){
	  Boolean tf= super.delete(dailyReport);
	  return tf;
   }
   public Boolean updateDailyReport(DailyReport dailyReport){
	 Boolean tf=super.update(dailyReport);
	 return tf;
   }
 
public DailyReport getDailyReportByDate(Date date) {
	Transaction transaction=null;
	List<DailyReport> dailyReports=new ArrayList<DailyReport>();

    try ( Session    session=sessionFactory.openSession();) {
 	   transaction = session.beginTransaction();
      CriteriaBuilder builder = session.getCriteriaBuilder();
      CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
      Root<DailyReport> dailyRoot = criteriaQuery.from(DailyReport.class);
      
      criteriaQuery.multiselect(dailyRoot);
	     criteriaQuery.where(
	    		 builder.equal(dailyRoot.get("createDate"),date)
	    		 );
      Query<Object[]> query=session.createQuery(criteriaQuery);
      List<Object[]> list=query.getResultList();
      for (Object object : list) {
    	  DailyReport dailyReport=(DailyReport)object;
    	  dailyReports.add(dailyReport);
      }
      transaction.commit();
      session.close();
   } catch (Exception e) {
      e.printStackTrace();
    
      if (transaction != null) {
         transaction.rollback();
      }
	
	if(dailyReports.size()==0)
	{
		return null;
	}else
	{
	return dailyReports.get(0);
	}
	
}
    if(dailyReports.size()==0)
	{
		return null;
	}else
	{
	return dailyReports.get(0);
	}
}
}


		
	
