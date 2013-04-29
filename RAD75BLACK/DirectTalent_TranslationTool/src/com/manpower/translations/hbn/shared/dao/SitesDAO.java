package com.manpower.translations.hbn.shared.dao;

import java.util.List;

import com.manpower.translations.beans.Sites;

/**
 * A data access object (DAO) providing persistence and search support for Sites
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 *
 * @see com.manpower.translations.beans.Sites
 * @author MyEclipse Persistence Tools
 */

public interface SitesDAO {

   public static final String SITECODE = "sitecode";

   public static final String SITENAME = "sitename";

   public static final String SITEOWNER = "siteowner";

   public static final String SITEFIRSTLANG = "sitefirstlang";

   public static final String SITESECONDLANG = "sitesecondlang";

   public static final String SITETHIRDLANG = "sitethirdlang";

   public static final String CREATED_BY = "createdBy";

   public static final String CHANGED_BY = "changedBy";

   public static final String UPDATEDBY = "updatedby";

   public static final String COUNTRYCODE = "countrycode";

   public static final String SITESTATUS = "sitestatus";

   public static final String DEFAULT_LEVEL = "defaultLevel";

   public static final String INTEGRATIONFLAG = "integrationflag";

   public static final String DISTANCE_UNIT = "distanceUnit";

   public static final String RESOURCE_PREFIX = "resourcePrefix";

   public static final String BG_FLAG = "bgFlag";

   public static final String SITEDESC = "sitedesc";

   public Sites findById(Number id);

   public List<Sites> findAll() ;

}