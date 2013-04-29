package com.manpower.translations.hbn.shared.dao;

import java.util.List;
import java.util.Properties;

import com.manpower.translations.beans.Propertylines;
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

public interface PropertylinesDAO  {

   public static final String COUNTRY_CODE = "countryCode";

   public static final String CREATED_BY = "createdBy";

   public static final String CREATED_ON = "createdOn";

   public static final String PROPERTY_KEY = "propertyKey";

   public static final String LANG = "lang";

   public static final String PROPERTYFILE_NAME = "propertyfileName";

   public static final String TRANSLATION_STRING = "translationString";

   public static final String UPDATED_BY = "updatedBy";

   public static final String UPDATED_ON = "updatedOn";

   public static final String DEFAULT_LANG = "defaultLang";

   public List<Propertylines> findBDPropertyFile(String fileName, String countryCode);

   public void performDBPropertyFileUpdate(PropertyFile propertyFile);

   public List<Propertylines> findByProperty(String propertyName, Object value);

   public List<Propertylines> findByCountryCode(Object countryCode);

   public List<Propertylines> findAll();

   public List<String> retrieveAllLanguages(String fileName, String countryCode);

   public void performImportPropertiesValuestoDB(String fileName, String countryCode, String lang2, Properties srcProp);

}


