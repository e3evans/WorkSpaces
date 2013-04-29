package com.manpower.translations.hbn.shared;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 *
 */
public class HibernateUtilities {

    private static Logger LOG = Logger.getLogger(HibernateUtilities.class);

    private static Configuration configuration;

    public static final ThreadLocal<Session> session = new ThreadLocal<Session>();

    static {
	try {
	    // Create the SessionFactory from hibernate.cfg.xml

	    configuration = new Configuration();

	    // Jas We could also let Hibernate bind it to JNDI:
	    configuration.configure().buildSessionFactory();

	} catch (Throwable ex) {
	    // Make sure you log the exception, as it might be swallowed
	    LOG.debug("Initial SessionFactory creation failed.", ex);
	    throw new ExceptionInInitializerError(ex);
	}
    }

    /**
     * Returns the SessionFactory used for this static class.
     *
     * @return SessionFactory
     */
    public static SessionFactory getSessionFactory() {
	LOG.debug("Cretating the SessionFactoty.");

	// Instead of a static variable, use JNDI:
	SessionFactory sessions = null;

	try {
	    Context ctx = new InitialContext();
	    String jndiName = null;
	    if (configuration.getProperty("session_factory_name") != null){
	       jndiName = configuration.getProperty("session_factory_name");
	    } else if (configuration.getProperty("hibernate.session_factory_name") != null){
	       jndiName = configuration.getProperty("session_factory_name");
	    }
	    LOG.debug("processing hibernate configuration with jndi name as: " + jndiName);
	    sessions = (SessionFactory) ctx.lookup(jndiName);

	} catch (NamingException ex) {
	    LOG
		    .debug(
			    "An error occurred at the moment of create the SessionFactory",
			    ex);
	    throw new RuntimeException(ex);
	}
	return sessions;
    }

    public static void closeSession() throws HibernateException {
	LOG.debug("The session of hibernate is going to close.");
	Session s = (Session) session.get();
	if (s != null)
	    s.close();
	session.set(null);
    }

    public static Session currentSession() throws HibernateException {
	LOG.debug("Retrieve a session of hibernate");
	Session s = (Session) session.get();
	// Open a new Session, if this thread has none yet
	if (s == null || !s.isOpen()) {
	    LOG
		    .debug("The application is going to create a new session of ibernate.");
	    s = getSessionFactory().openSession();
	    // Store it in the ThreadLocal variable
	    session.set(s);
	}
	return s;
    }

}
