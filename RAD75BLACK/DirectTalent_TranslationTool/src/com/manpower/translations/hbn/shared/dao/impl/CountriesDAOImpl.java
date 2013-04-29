package com.manpower.translations.hbn.shared.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manpower.translations.beans.Countries;
import com.manpower.translations.hbn.shared.dao.CountriesDAO;
import com.manpower.translations.hbn.shared.dao.GenericHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * Countries entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 */

public class CountriesDAOImpl extends GenericHibernateDAO implements
	CountriesDAO {

    private static final Logger log = LoggerFactory.getLogger(CountriesDAOImpl.class);

    public CountriesDAOImpl(Session session) {
	super(session);
    }

    public final List<Countries> getAllCountries() {
	return getSession().createCriteria(Countries.class).list();
    }

    public List<Countries> findByProperty(String propertyName, Object value) {
	log.debug("finding Countries instance with property: " + propertyName
		+ ", value: " + value);
	try {
	    String queryString = "from Countries as model where model." + propertyName + "= ?";
	    Query queryObject = getSession().createQuery(queryString);
	    queryObject.setParameter(0, value);
	    return queryObject.list();
	} catch (RuntimeException re) {
	    log.error("find by property name failed", re);
	    throw re;
	}
    }

    public List<Countries> findAll() {
	log.debug("finding all Countries instances");
	try {
	    String queryString = "from Countries";
	    Query queryObject = getSession().createQuery(queryString);
	    return queryObject.list();
	} catch (RuntimeException re) {
	    log.error("find all failed", re);
	    throw re;
	}
    }

    public Countries findCountryByCode(final String countryCode) {
	Countries result = null;
	Criteria criteria = getSession().createCriteria(Countries.class);
	criteria.add(Restrictions.like("countryCode", countryCode, MatchMode.START));
	result = (Countries) criteria.uniqueResult();

	if (result == null) {
	    throw new IllegalStateException(
		    "There's no country with country code " + countryCode);
	}

	return result;
    }

    @Override
    public Countries findById(Number id) {;
       log.debug("getting Sites instance with id: " + id);
       try {
          Countries instance = (Countries) getSession().get(
                "com.manpower.translations.beans.Countries", id);
          return instance;
       } catch (RuntimeException re) {
          log.error("get failed", re);
          throw re;
       }
    }

}