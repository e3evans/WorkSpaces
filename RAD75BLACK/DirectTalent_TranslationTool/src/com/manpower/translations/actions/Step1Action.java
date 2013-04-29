package com.manpower.translations.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.manpower.translations.beans.Countries;
import com.manpower.translations.service.PropertyFileManager;
import com.manpower.translations.service.PropertyFilesDBService;

/**
 * The first page of the application where basic data is selected before
 * starting the process of entry the data, country selection is handled
 * via url and template selected from template file combo box, the
 * template files can be added/modified only by an administrator and the
 * changes of this are immediately reflected in the application.
 */
public class Step1Action extends ExtendedActionSupport {
   private static final long serialVersionUID = -7331972806955085241L;

   private static final String EMPTY_STRING = "";
   private static final String COUNTRY_PARAMETER = "CTRY";
   private static Logger LOG = Logger.getLogger(Step1Action.class);
   private List<String> propFiles;

   private Countries country;
   private PropertyFileManager propertyFileManager;
   private PropertyFilesDBService propertyFilesDBService;
   private String propFile;
   private String countryCode;
   private List<Countries> countries;

   private String resultMessage;

   private static final String DEFAULT_SITE = "DEF";

   /**
    * Process display / redisplay of the page
    * The html get param CTRY identifies the country code, this param is used to retrieve
    * the country information from the database, then if the country exists the information is
    * displayed in the screen including the templates files loaded from the sandbox.
    */
   @Override
   protected final String get() {
      String result = ERROR;
      resultMessage = "";
      try {
    	  if (countries == null || countries.isEmpty()){
              countries = new ArrayList<Countries>();
              List<Countries> ctrys = propertyFilesDBService.getAllCountries();
              for (Countries countrie : ctrys) {
                 countries.add(countrie);
              }
           }
    	   country = retrieveCountryInformation(retrieveCountryInformationFromUrl());
    	   if(Step1Action.DEFAULT_SITE.equals(country.getCleanCountryCode()) && req.getSession().getAttribute("selectedCountryCode") != null){
            country = retrieveCountryInformation(req.getSession().getAttribute("selectedCountryCode").toString());
         }
         ServletContext sc = getServletRequest().getSession().getServletContext();
         propFiles = propertyFileManager.getTemplatesPropertyFileNames(sc.getRealPath("/"));
         req.getSession().setAttribute("DO_NOT_REPROCESS_MERGE", "false");
         result = INPUT;
      } catch (Exception e) {
         resultMessage = "The aplication cannot be accessed, please advise the administrator:" + e.getMessage();
         LOG.info(e);
      }

      return result;
   }

   /**
    * Finds the country information in the BD given a country code.
    * @param countryCode 3 digits upper case country code.
    * @return a Countries object.
    */
   private Countries retrieveCountryInformation(final String countryCode) {
      Countries country = null;
      if (!StringUtils.isEmpty(countryCode)) {
         country = propertyFilesDBService.findCountryLikeCountryCode(countryCode);
      } else {
	  country = new Countries(Step1Action.DEFAULT_SITE, "DefaultSite");
      }

      return country;
   }

   /**
    * Retrieves the country code from get parameter.
    * @return String: 3 digits country code.
    */
   private String retrieveCountryInformationFromUrl() {
      String parameter = req.getParameter(COUNTRY_PARAMETER);
      if (null == parameter) {
         parameter = EMPTY_STRING;
      }
      return parameter;
   }

   /**
    * Process information to be sent to the next screen
    * Retrieve country information from DB and store the information
    * in the session request.
    */
   @Override
   protected final String post() {
      if (country != null && !country.getCleanCountryCode().equals(Step1Action.DEFAULT_SITE) &&
            !StringUtils.isEmpty(propFile)) {
           country = retrieveCountryInformation(country.getCleanCountryCode());
   	   req.getSession().setAttribute("selectedCountryCode", this.country.getCleanCountryCode());
   	   req.getSession().setAttribute("selectedCountryName", this.country.getCounrty());
   	   LOG.debug("******Country ID:" + this.country.getCleanCountryCode());
   	   LOG.debug("******Country NAME:" + this.country.getCounrty());
   	   LOG.debug("******PropFile:" + propFile);
   	   req.getSession().setAttribute("propFile", propFile);
         return SUCCESS;
	   } else {
	      return INPUT;
	   }
   }

   @Override
   protected final void validatePost() {

   }

   /**
    * Sets the propFiles.
    * @param propFiles <code>List<String></code> with the propFiles.
    */
   public final void setPropFiles(final List<String> propFiles) {
      this.propFiles = propFiles;
   }

   /**
    * Get a list of templates property file names.
    * @return <code>List<String></code> of templates property file names.
    */
   public final List<String> getPropFiles() {
      return propFiles;
   }

   /**
    * Sets the template file name to process.
    * @param propFile <code>String</code> with the property file name.
    */
   public final void setPropFile(final String propFile) {
      this.propFile = propFile;
   }

   /**
    * Get the template file name to process.
    * @return <code>String</code> with the propFile.
    */
   public final String getPropFile() {
      return propFile;
   }

   /**
    * Setter for the disk Property File Manager utilities.
    * @param propertyFileManager
    */
   public final void setPropertyFileManager(final PropertyFileManager propertyFileManager) {
      this.propertyFileManager = propertyFileManager;
   }

   /**
    * Getter for the disk Property File Manager utilities.
    * @return PropertyFileManager.
    */
   public final PropertyFileManager getPropertyFileManager() {
      return this.propertyFileManager;
   }

   /**
    * getter for country object.
    * @return Countries.
    */
   public Countries getCountry() {
      return country;
   }

   /**
    * setter for country object.
    * @param country object.
    */
   public void setCountry(Countries country) {
      this.country = country;
   }

   /**
    * Getter for the Property File database service utilities.
    * @return PropertyFilesDBService
    */
   public PropertyFilesDBService getPropertyFilesDBService() {
      return propertyFilesDBService;
   }

   /**
    * Setter for the Property File database service utilities.
	* @param propertyFilesDBService
	*/
   public void setPropertyFilesDBService(PropertyFilesDBService propertyFilesDBService) {
      this.propertyFilesDBService = propertyFilesDBService;
   }

    public String getCountryCode() {
    	return countryCode;
    }

    public void setCountryCode(String country) {
    	countryCode = country;
    }

    public List<Countries> getCountriesId() {
    	return countries;
    }

    public void setCountriesId(List<Countries> countrie) {
    	this.countries = countrie;
    }

   public String getResultMessage() {
      return resultMessage;
   }

   public void setResultMessage(String resultMessage) {
      this.resultMessage = resultMessage;
   }

}
