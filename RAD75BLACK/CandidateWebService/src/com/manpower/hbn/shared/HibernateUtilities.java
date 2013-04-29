/*
 * Created on Nov 10, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.hbn.shared;

/**
 * @author Dmitry
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtilities {
	public static  SessionFactory sessionFactory;
	private static Configuration configuration;
	
	public static final ThreadLocal session = new ThreadLocal();


	static {
		try {
			// Create the SessionFactory from hibernate.cfg.xml

			////////////////////////////////////
		//	sessionFactory = new Configuration().configure()
		//			.buildSessionFactory();
			////////////////////////////////////////////
			configuration = new Configuration();

			// Jas We could also let Hibernate bind it to JNDI:
             configuration.configure().buildSessionFactory();

		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}



	  /**
     * Returns the SessionFactory used for this static class.
     *
     * @return SessionFactory
     */
    public static SessionFactory getSessionFactory() {
       //  Instead of a static variable, use JNDI:
        SessionFactory sessions = null;
        try {
            Context ctx = new InitialContext();
            String jndiName = "java:hibernate/HibernateFactory";
            sessions = (SessionFactory)ctx.lookup(jndiName);
           
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
        return sessions;


    }

	

	public static void closeSession() throws HibernateException {
		Session s = (Session) session.get();
		if (s != null)
			s.close();
		session.set(null);
	}



	public static Session currentSession() throws HibernateException {
		Session s = (Session) session.get();
		// Open a new Session, if this thread has none yet
		if (s == null || !s.isOpen()) {
			s = getSessionFactory().openSession();
			// Store it in the ThreadLocal variable
			session.set(s);
			
		}
		return s;
	}

	


}
