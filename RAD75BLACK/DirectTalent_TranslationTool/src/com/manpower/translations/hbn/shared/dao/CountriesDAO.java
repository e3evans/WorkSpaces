package com.manpower.translations.hbn.shared.dao;

import java.util.List;

import com.manpower.translations.beans.Countries;

/**
 * A data access object (DAO) providing persistence and search support for
 * Countries entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 */

public interface CountriesDAO {

   public static final String COUNTRY_CODE = "countryCode";

   public static final String COUNTRY = "counrty";

   public List<Countries> getAllCountries();

   public List<Countries> findByProperty(String propertyName, Object value);

   public List<Countries> findAll();

   public Countries findCountryByCode(final String countryCode);

   Countries findById(Number id);

}