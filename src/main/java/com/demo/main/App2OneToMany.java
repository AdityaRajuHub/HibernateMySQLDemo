package com.demo.main;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.demo.config.HibernateUtil;
import com.demo.model.Address;
import com.demo.model.Person;

public class App2OneToMany 
{
	public static void main( String[] args )
	{
		try {
			System.out.println("Maven + Hibernate + MySQL");
			Session session = HibernateUtil.getSessionFactory().openSession();

			session.beginTransaction();
			
			Person p1= new Person("John", "Doe");
			
			Address add= new Address("302, Water St.", "NY", "NY", 120002, p1);
			Address offAdd= new Address("101, Crown St", "Phil", "Philadelphia", 132001, p1);
			List<Address> addresses= new ArrayList<Address>();
			addresses.add(add);
			addresses.add(offAdd);
			
			p1.setAddresses(addresses);
			
			session.save(p1);
			
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