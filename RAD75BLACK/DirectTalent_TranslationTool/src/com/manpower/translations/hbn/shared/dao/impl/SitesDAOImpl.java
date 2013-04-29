package com.manpower.translations.hbn.shared.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manpower.translations.beans.Sites;
import com.manpower.translations.hbn.shared.dao.GenericHibernateDAO;
import com.manpower.translations.hbn.shared.dao.SitesDAO;

/**
 * A data access object (DAO) providing persistence and search support for Sites
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 *
 * @see com.manpower.translations.beans.Sites
 */

public class SitesDAOImpl extends GenericHibernateDAO implements SitesDAO {

   private static final Logger log = LoggerFactory.getLogger(SitesDAOImpl.class);

   public SitesDAOImpl(Session session) {
      super(session);
   }

   public Sites findById(Number id) {
      log.debug("getting Sites instance with id: " + id);
      try {
         Sites instance = (Sites) getSession().get(
               "com.manpower.translations.beans.Sites", id);
         return instance;
      } catch (RuntimeException re) {
         log.error("get failed", re);
         throw re;
      }
   }

    public List<Sites> findAll() {
	log.debug("finding all Sites instances");
	try {
	    String queryString = "from Sites";
	    Query queryObject = getSession().createQuery(queryString);
	    return queryObject.list();
	} catch (RuntimeException re) {
	    log.error("find all failed", re);
	    throw re;
	}
    }

}