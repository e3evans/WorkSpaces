package com.manpower.translations.hbn.shared.dao;

import org.hibernate.Session;

import com.manpower.translations.hbn.shared.HibernateUtilities;
import com.manpower.translations.hbn.shared.dao.impl.CountriesDAOImpl;
import com.manpower.translations.hbn.shared.dao.impl.PropertylinesDAOImpl;
import com.manpower.translations.hbn.shared.dao.impl.SitesDAOImpl;

/**
 *
 *
 */
public class DAOFactoryHibernate extends DAOFactory {

    protected Session getCurrentSession() {
	return HibernateUtilities.currentSession();
    }

    @Override
    public PropertylinesDAO getPropertyLinesDAO() {
	return new PropertylinesDAOImpl(getCurrentSession());
    }

    @Override
    public CountriesDAO getCountriesDAO() {
	return new CountriesDAOImpl(getCurrentSession());
    }

    @Override
    public SitesDAO getSitesDAO() {
	return new SitesDAOImpl(getCurrentSession());
    }

}
