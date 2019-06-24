package com.demo.main;
import org.hibernate.Session;

import com.demo.config.HibernateUtil;
import com.demo.model.Publication;
import com.demo.model.Topic;

public class App 
{
	public static void main( String[] args )
	{
		try {
			System.out.println("Maven + Hibernate + MySQL");
			Session session = HibernateUtil.getSessionFactory().openSession();

			session.beginTransaction();
			
			Publication pub= new Publication("McGrawHill", "NY", 5.0f);
			Publication offPub= new Publication("McGrawHill", "NY", 3.5f);
			
			Topic stock = new Topic("Stock", "GENOME", pub, offPub);

			//session.save(stock);
			session.persist(stock);
			
			System.out.println("After save...id: "+stock.getId());
			
			System.out.println("author dirty 1");
			stock.setAuthor("Author updated 1");
			
			//...
			
			System.out.println("author dirty 2");
			stock.setAuthor("Author updated 2");
			
			//...
			
			System.out.println("author dirty 3");
			stock.setAuthor("Author updated 3");	//latest change
			
			Topic stock1= session.get(Topic.class, stock.getId());	//no select triggered here, given from cache
			stock1.setName("stock1");	//latest change
			
			session.getTransaction().commit();	//1 insert(mandatory) and 1 update stmt(latest changes) will be fired
			session.close();	//cache is emptied here

			//Detached object changes - no longer associated with persistent context
			stock.setName("stockDetached");	//no corresponding object in cache since we flushed it
			//Topic stockTransient= new Topic();
			//stockTransient.setId(stock.getId());
			//-----------------------------------------------------------------------------------------
			
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Topic stock2= session.load(Topic.class, stock.getId());	//Lazy fetch
			//System.out.println("stock2: "+stock2.toString());	//Now it fires a select query
			
			//session.update(stock); //org.hibernate.NonUniqueObjectException: A different object with the same identifier value was already associated with the session : [com.demo.model.Topic#1]
			session.merge(stock);	//makes this object persistent while merging changes

			session.getTransaction().commit();
			session.close();
			//-----------------------------------------------------------------------------------------
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			HibernateUtil.shutdown();
		}
	}
}