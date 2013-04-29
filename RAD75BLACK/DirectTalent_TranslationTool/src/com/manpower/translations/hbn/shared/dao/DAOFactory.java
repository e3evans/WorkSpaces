package com.manpower.translations.hbn.shared.dao;

/**
 *
 */
public abstract class DAOFactory {

    public static DAOFactory getDAOFactory() {
	return new DAOFactoryHibernate();
    }

    public abstract PropertylinesDAO getPropertyLinesDAO();

    public abstract SitesDAO getSitesDAO();

    public abstract CountriesDAO getCountriesDAO();

}
