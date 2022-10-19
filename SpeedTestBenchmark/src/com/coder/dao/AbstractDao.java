package com.coder.dao;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;




public abstract class AbstractDao<PK extends Serializable,T> {
	@Autowired
	public SessionFactory sessionFactory;
	
	private final Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	public AbstractDao(){
		this.persistentClass=(Class<T>)
				((ParameterizedType)this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}
	
	
	protected Session getSession(){
	
		return this.sessionFactory.openSession();
	}
	protected void SessionClose(){
		
		 this.sessionFactory.close();
	}
	

	
	public boolean persistMtoM(T u) {

	    Session session = sessionFactory.openSession();
	    Transaction tr = session.beginTransaction();
	    boolean y = false;
	    try {
	        session.save(u);
	        tr.commit();
	        y = true;
	    } catch (Exception e) {
	        tr.rollback();
	        e.printStackTrace();
	    } finally {
	        if (session.isOpen()) {
	            session.close();
	        }
	        return y;
	    }
	}
	

	public Integer persist(T u) {

	    Session session = sessionFactory.openSession();
	    Transaction tr = session.beginTransaction();
	    boolean y = false;
        Integer id=-1;
	    try {
	         id=(Integer)session.save(u);
	        tr.commit();
	        y = true;
	    } catch (Exception e) {
	        tr.rollback();
	        e.printStackTrace();
	    } finally {
	        if (session.isOpen()) {
	            session.close();
	        }

			return id;
	    }
	}
	public void persistVoid(T u) {

	    Session session = sessionFactory.openSession();
	    Transaction tr = session.beginTransaction();
	    boolean y = false;
        
	    try {
	         session.save(u);
	        tr.commit();
	        y = true;
	    } catch (Exception e) {
	        tr.rollback();
	        e.printStackTrace();
	    } finally {
	        if (session.isOpen()) {
	            session.close();
	        }

			
	    }
	}
	//update
	public Boolean update(T entity){
		
		  Session session = sessionFactory.openSession();
		    Transaction tr = session.beginTransaction();
		    boolean y = false;
		    try {
		        session.update(entity);
		        tr.commit();
		        y = true;
		    } catch (Exception e) {
		        tr.rollback();
		        e.printStackTrace();
		    } finally {
		        if (session.isOpen()) {
		            session.close();
		        }
		        return y;
		    }
	
	}
	
	//delete
	public Boolean delete(T entity){
    
	    Session session = sessionFactory.openSession();
	    Transaction tr = session.beginTransaction();
	    boolean y = false;
        
	    try {
	       session.delete(entity);
	        tr.commit();
	        y = true;
	    } catch (Exception e) {
	        tr.rollback();
	        e.printStackTrace();
	    } finally {
	        if (session.isOpen()) {
	            session.close();
	        }

			return y;
	    }
	}
	public List<T> criteriaQuerryGetObjectsByValue(String columName,String value){
		Session s=sessionFactory.openSession();
		Transaction tr = s.beginTransaction();
		CriteriaBuilder builder=s.getCriteriaBuilder();
		CriteriaQuery<T> querry=builder.createQuery(persistentClass);
		Root<T> root=querry.from(persistentClass);
		querry.select(root);
		querry.where(builder.equal(root.get(columName),value));
		Query<T> q=s.createQuery(querry);
		List<T> list=q.getResultList();
		tr.commit();
		s.close();
		
		if(list.size()==0){
		return null;
		}else{
		return list;
		}
		
	}
	public List<T> getAllEntity(){
		Session s=sessionFactory.openSession();
		CriteriaBuilder builder=s.getCriteriaBuilder();
		CriteriaQuery<T> querry=builder.createQuery(persistentClass);
		Root<T> root=querry.from(persistentClass);
		querry.select(root);
		Query<T> q=s.createQuery(querry);
		List<T> list=q.getResultList();
		s.close();
		return list;
    	}
	public T criteriaQuerryGetObjectById(int id,String strId){
		Session s=sessionFactory.openSession();
		CriteriaBuilder builder=s.getCriteriaBuilder();
		CriteriaQuery<T> querry=builder.createQuery(persistentClass);
		Root<T> root=querry.from(persistentClass);
		querry.select(root);
		querry.where(builder.equal(root.get(strId),id));
		Query<T> q=s.createQuery(querry);
		List<T> list=q.getResultList();
		s.close();
		if(list.size()==0){
		return null;
		}else{
		return list.get(0);
		}
		
	}
	
	public  List<T> criteriaQuerryGetObjectsById(int id,String strId){
		Session s=sessionFactory.openSession();
		CriteriaBuilder builder=s.getCriteriaBuilder();
		CriteriaQuery<T> querry=builder.createQuery(persistentClass);
		Root<T> root=querry.from(persistentClass);
		querry.select(root);
		querry.where(builder.equal(root.get(strId),id));
		Query<T> q=s.createQuery(querry);
		List<T> list=q.getResultList();
		s.close();
		if(list.size()==0){
		return null;
		}else{
		return list;
		}
		
	}
	public T criteriaQuerryGetObjectByName(String name,String strName){
		Session s=sessionFactory.openSession();
		Transaction tr = s.beginTransaction();
		CriteriaBuilder builder=s.getCriteriaBuilder();
		CriteriaQuery<T> querry=builder.createQuery(persistentClass);
		Root<T> root=querry.from(persistentClass);
		querry.select(root);
		querry.where(builder.equal(root.get(strName),name));
		Query<T> q=s.createQuery(querry);
		List<T> list=q.getResultList();
		tr.commit();
		s.close();
		
		if(list.size()==0){
		return null;
		}else{
		return list.get(0);
		}
		
	}
	public List<T> criteriaQuerryGetObjectsByName(String name,String strName){
		Session s=sessionFactory.openSession();
		Transaction tr = s.beginTransaction();
		CriteriaBuilder builder=s.getCriteriaBuilder();
		CriteriaQuery<T> querry=builder.createQuery(persistentClass);
		Root<T> root=querry.from(persistentClass);
		querry.select(root);
		querry.where(builder.equal(root.get(strName),name));
		Query<T> q=s.createQuery(querry);
		List<T> list=q.getResultList();
		tr.commit();
		s.close();
		
		if(list.size()==0){
		return null;
		}else{
		return list;
		}
		
	} 
	

	

}
