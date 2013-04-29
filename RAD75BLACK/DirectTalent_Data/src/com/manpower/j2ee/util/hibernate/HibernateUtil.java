/*
 * Created on Sept. 25, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.manpower.j2ee.util.hibernate;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HibernateUtil {
	private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class.getName());
	private static final HibernateUtil instance = new HibernateUtil();
	private static SessionFactory sessionFactory;
	private static Map cache = new HashMap(23);
	
	private HibernateUtil() {
		
	}

	public HibernateUtil getInstance() {
		return instance;
	}
	
	public static SessionFactory getSessionFactory() {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Looking up Hibernate session factory.");
		}
		if(sessionFactory == null) {
			try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (HibernateException e) {
                if(LOGGER.isEnabledFor(Level.FATAL)) {
                    LOGGER.fatal("Unable to retrieve Hibernate session factory, see following message " +
                            e.getMessage(), e);
                }
            }
		}
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieved Hibernate session factory.");
		}
		
		return sessionFactory;
	}
	
	public static Session getCurrentSession() {
		Session session = getSessionFactory().getCurrentSession();
		
		if(session != null){
			session.setFlushMode(FlushMode.COMMIT);
		}
		
		return session;
	} 
	
	public static SessionFactory getSessionFactory(String aSessionFactoryName) {
		if(LOGGER.isEnabledFor(Level.INFO)) {
			LOGGER.info("Looking up session factory " + aSessionFactoryName);
		}
		if(cache.containsKey(aSessionFactoryName)) {
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("Found session factory in cache " + aSessionFactoryName);
			}
			return (SessionFactory) cache.get(aSessionFactoryName);
		} else {
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("Adding session factory to cache " + aSessionFactoryName);
			}
			SessionFactory aSessionFactory = null;
			try {
                aSessionFactory = new Configuration().configure(aSessionFactoryName).buildSessionFactory();
            } catch (HibernateException e) {
                if(LOGGER.isEnabledFor(Level.FATAL)) {
                    LOGGER.fatal("Unable to retrieve Hibernate session factory, see following message " +
                            e.getMessage(), e);
                }
            }
			cache.put(aSessionFactoryName, aSessionFactory);
			return aSessionFactory;
		}
		
	}
}