package me.hagen.ssh.config;

import static org.junit.Assert.*;

import java.util.List;




import me.hagen.ssh.config.SessionUtility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

public class SessionUtilityTest {

	@Test
	public void testGetSession() {
		/*
		SessionFactory sf = SessionUtility.getSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		List<User> ulist = session.createQuery("Select u from User u")
				.list();
		System.out.println(ulist.size());
		if(ulist.size()>0)
		{
			System.out.println(ulist.get(0).getId());
		}
		session.getTransaction().commit();;
		sf.close();
		*/
	}

	@Test
	public void testGetSessionFactory() {
		SessionFactory sf = SessionUtility.getSessionFactory();
		sf.close();
	}

}
