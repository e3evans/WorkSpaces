package com.manpower.translations.hbn.shared.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manpower.translations.beans.Propertylines;
import com.manpower.translations.hbn.shared.dao.DBProperty;
import com.manpower.translations.hbn.shared.dao.GenericHibernateDAO;
import com.manpower.translations.hbn.shared.dao.PropertylinesDAO;
import com.manpower.translations.model.PropertyFile;

/**
 * A data access object (DAO) providing persistence and search support for
 * Propertylines entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 */

public class PropertylinesDAOImpl extends GenericHibernateDAO implements
	PropertylinesDAO {

    private static final Logger LOG = LoggerFactory.getLogger(PropertylinesDAOImpl.class);

    public PropertylinesDAOImpl(Session currentSession) {
	super(currentSession);
    }

    public List<Propertylines> findBDPropertyFile(String fileName,
	    String countryCode) {
       LOG.debug("Executing find DB prolerty file with file name:" + fileName + " country code: " + countryCode);
	Criteria criteria = getSession().createCriteria(Propertylines.class);

	criteria.add(Restrictions.eq(PropertylinesDAOImpl.PROPERTYFILE_NAME,
		fileName));
	criteria.add(Restrictions.like(PropertylinesDAOImpl.COUNTRY_CODE,
			countryCode, MatchMode.START));
	//criteria.add(Restrictions.eq(PropertylinesDAOImpl.COUNTRY_CODE,countryCode));
	List<Propertylines> dBPropertylines = criteria.list();
	LOG.debug("retrieved amount of entries:" + dBPropertylines.size());
	return dBPropertylines;
    }

   public void performDBPropertyFileUpdate(PropertyFile propertyFile) {
	Session session = getSession();
	session.beginTransaction();

	try {
	    String fileName = propertyFile.getFileName();
	    String countryCode = propertyFile.getCountryCode();
	    List<String> validLanguages = propertyFile.getLanguageList();
	    List<Propertylines> dbPropertyLines = findBDPropertyFile(fileName,
		    countryCode);

	    // delete values from database that are not valid
	    for (Propertylines dbPropertyline : dbPropertyLines) {
			// verification of fileName and country code is not necessary
			if (!validLanguages.contains(dbPropertyline.getLang())) {
			    LOG.info("deleting bd entry: language: "
				    + dbPropertyline.getLang() + ", key: "
				    + dbPropertyline.getPropertyKey());
			    getSession().delete(dbPropertyline);
			}
	    }

	    Date date = Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime();

	    for (DBProperty modelDBPropertyLine : propertyFile
		    .getPropertiesToUpdate()) {
		Propertylines dbPropertyline = (Propertylines) modelDBPropertyLine;
		if (StringUtils.isEmpty(dbPropertyline.getTranslationString())) {
			dbPropertyline.setTranslationString(" ");
	      }
		if (dbPropertyline.getId() == null) {
		    LOG.info("saving bd entry: language: "
			    + dbPropertyline.getLang() + ", key: "
			    + dbPropertyline.getPropertyKey());
		    // dbPropertyline.setId(getNewId());
		    dbPropertyline.setCreatedOn(date);
		    getSession().save(dbPropertyline);
		} else {
		    LOG.info("merge bd entry: language: "
			    + dbPropertyline.getLang() + ", key: "
			    + dbPropertyline.getPropertyKey());
		    dbPropertyline.setUpdatedOn(date);
		    getSession().merge(dbPropertyline);
		}
	    }

	    getSession().flush();
	    getSession().clear();

	    session.getTransaction().commit();

	} catch (Exception e) {
	    LOG.error(e.toString());
	    session.getTransaction().rollback();
	}
    }

    public List<Propertylines> findByProperty(String propertyName, Object value) {
	LOG.debug("finding Propertylines instance with property: "
		+ propertyName + ", value: " + value);
	try {
	    String queryString = "from Propertylines as model where model."
		    + propertyName + "= ?";
	    Query queryObject = getSession().createQuery(queryString);
	    queryObject.setParameter(0, value);
	    return queryObject.list();
	} catch (RuntimeException re) {
	    LOG.error("find by property name failed", re);
	    throw re;
	}
    }

    public List<Propertylines> findByCountryCode(Object countryCode) {
	return findByProperty(COUNTRY_CODE, countryCode);
    }

    public List<Propertylines> findAll() {
	LOG.debug("finding all Propertylines instances");
	try {
	    String queryString = "from Propertylines";
	    Query queryObject = getSession().createQuery(queryString);
	    return queryObject.list();
	} catch (RuntimeException re) {
	    LOG.error("find all failed", re);
	    throw re;
	}
    }

    public List<String> retrieveAllLanguages(String fileName, String countryCode) {
	Criteria criteria = getSession().createCriteria(Propertylines.class);
	criteria.add(Restrictions.eq(PropertylinesDAOImpl.PROPERTYFILE_NAME,
		fileName));
	criteria.add(Restrictions.eq(PropertylinesDAOImpl.COUNTRY_CODE,
		countryCode));
	criteria.setProjection(Projections.distinct(Projections
		.property(PropertylinesDAOImpl.LANG)));

	List<String> languages = criteria.list();

	return languages;
    }

    public void performImportPropertiesValuestoDB(String fileName,
			String countryCode, String lang, Properties srcProp) {

		Session session = getSession();
		session.beginTransaction();
		try {

			List<Propertylines> fileCountryPropertiesList = findBDPropertyFile(
					fileName, countryCode);

			String defaultLang = "";
			Boolean existsInDB = false;
			for (Propertylines propertylines : fileCountryPropertiesList) {
				if (StringUtils.isEmpty(defaultLang)
						&& new Boolean(propertylines.getDefaultLang())) {
					defaultLang = propertylines.getLang();
				}
				if (!existsInDB && propertylines.getLang().equals(lang)) {
					existsInDB = true;
				}
				if (!StringUtils.isEmpty(defaultLang) && existsInDB) {
					break;
				}
			}

//			List<Propertylines> finalList = new ArrayList<Propertylines>();
			Date date = Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime();

			// if exists retrieve values, update them,
			// a default value already exists so no require to change it
			if (existsInDB) {
				for (Propertylines dBPopertyline : fileCountryPropertiesList) {
					if (dBPopertyline.getLang().equals(lang)) {
						String tString = (String)srcProp.get(dBPopertyline.getPropertyKey());
						if (StringUtils.isEmpty(tString)) {
							dBPopertyline.setTranslationString(" ");
						} else {
							dBPopertyline.setTranslationString(tString);
						}
						dBPopertyline.setUpdatedOn(date);
						getSession().merge(dBPopertyline);
					}
				}
			} else if (!existsInDB) {
				// add new value so enter all the info
				Propertylines dbPropertyline = null;
				for (Object oKey:  srcProp.keySet()) {
					String key = (String) oKey;
					dbPropertyline = new Propertylines();
					dbPropertyline.setCountryCode(countryCode);
					dbPropertyline.setCreatedOn(date);
					dbPropertyline.setDefaultLang(new Boolean(StringUtils.isEmpty(defaultLang)).toString());
					dbPropertyline.setLang(lang);
					dbPropertyline.setPropertyfileName(fileName);
					dbPropertyline.setPropertyKey(key);
					String tString = srcProp.getProperty(key);
					if (StringUtils.isEmpty(tString)) {
						dbPropertyline.setTranslationString(" ");
				    } else {
				    	dbPropertyline.setTranslationString(tString);
				    }
					getSession().save(dbPropertyline);
				}
			}

			getSession().flush();
	      getSession().clear();

			session.getTransaction().commit();

		} catch (Exception e) {
			LOG.error(e.toString());
			session.getTransaction().rollback();
		}
	}

   @Override
   public Propertylines findById(Number id) {
      LOG.debug("getting Propertylines instance with id: " + id);
      try {
         Propertylines instance = (Propertylines) getSession().get(
               "com.manpower.translations.beans.Propertylines", id);
         return instance;
      } catch (RuntimeException re) {
         LOG.error("get failed", re);
         throw re;
      }
   }

}
