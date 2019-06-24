package com.demo.main; 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.demo.config.HibernateUtil;
import com.demo.model.Address;
import com.demo.model.Person;
import com.demo.model.Role;
import com.demo.model.User;

public class App2OneToOne 
{
	public static void main( String[] args )
	{
		try {
			System.out.println("Maven + Hibernate + MySQL");
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Role role1= new Role("Admin", "Technology", new Date());
			Role role2= new Role("User", "Technology", new Date());
			Role role3= new Role("Manager", "Sales", new Date());
			
			User p1= new User("john_doe", "dfltPwd@", role1);
			User p2= new User("john_cloey", "dfltPwd@", role2);
			User p3= new User("vigil_ante", "Saew@123", role2);
			User p4= new User("wakeup_sid", "sdaDweqXX123", role3);
			
			session.save(p1);
			session.save(p2);
			session.save(p3);
			session.save(p4);
			
			session.getTransaction().commit();
			session.close();
			//-----------------------------------------------------------------------------------------
			
			Session session2 = HibernateUtil.getSessionFactory().openSession();
			session2.beginTransaction();

			Role r= session2.get(Role.class, role2.getId());
			
			for(User user: r.getUsers()) {
				System.out.println("User: "+user.getLoginId()+" role: "+r.getName());
			}
			
			
			session2.getTransaction().commit();
			session2.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			HibernateUtil.shutdown();
		}
	}
}