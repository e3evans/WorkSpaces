package com.manpower.translations.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.manpower.translations.beans.Countries;
import com.manpower.translations.beans.Propertylines;
import com.manpower.translations.beans.Sites;
import com.manpower.translations.hbn.shared.dao.CountriesDAO;
import com.manpower.translations.hbn.shared.dao.DAOFactory;
import com.manpower.translations.hbn.shared.dao.PropertylinesDAO;
import com.manpower.translations.hbn.shared.dao.SitesDAO;
import com.manpower.translations.model.LocatedValue;
import com.manpower.translations.model.PropertyFile;
import com.manpower.translations.model.PropertyLine;
import com.manpower.translations.model.SimplePropertyFile;
import com.manpower.translations.utils.ApplicationUtils;

/**
 * Service layer, this is where all the database transactions should 
 * happen, all the DB queries are called from this layer.
 */
public class PropertyFilesDBService {
	
   private static Logger LOG = Logger.getLogger(PropertyFilesDBService.class);

   /**
    * DB query to get all Propertylines given a country code.
    * @param countryCode
    * @return List<String>
    */
   public List<String> findAllPropertyFileNames(String countryCode) {
      LOG.debug("Retrieving all the Property File with the following country code: " + countryCode);
      PropertylinesDAO propertylinesDAO = DAOFactory.getDAOFactory().getPropertyLinesDAO();
      List<Propertylines> propertyLines = propertylinesDAO.findByCountryCode(countryCode);
      List<String> propertyFileNames = new ArrayList<String>();
      
      for (Propertylines propertyline : propertyLines) {
         propertyFileNames.add(propertyline.getPropertyfileName());
      }

      return propertyFileNames;
   }

   /**
    * DB query to get all Propertylines given a name and a country code.
    * @param fileName
    * @param countryCode
    * @return List<Propertylines>.
    */
   public List<Propertylines> findBDPropertyFile(String fileName, String countryCode) {
      LOG.debug("Retrieving the Property File with the following file's name and country code: " 
	      + fileName + " - " + countryCode);  
      PropertylinesDAO propertylinesDAO = DAOFactory.getDAOFactory().getPropertyLinesDAO();
      List<Propertylines> dBPropertylines = propertylinesDAO.findBDPropertyFile(fileName, countryCode);
      return dBPropertylines;
   }

   /**
    * DB query to get all Propertylines given a name and a country code and return it as a property model.
    * @param fileName
    * @param countryCode
    * @return PropertyFile model.
    */
   public PropertyFile findModelPropertyFile(String fileName, String countryCode) {
      LOG.debug("Retrieving the Property File with the following file's name and country code: " 
	      + fileName + " - " + countryCode); 
      List<Propertylines> dBPropertylines = findBDPropertyFile(fileName, countryCode);
      PropertyFile modelPropertyFile = new SimplePropertyFile(countryCode, 
	      ApplicationUtils.removeFileExtension(fileName));
      if (dBPropertylines.size() > 0 &&
            dBPropertylines.get(0).getCountryCode() != null &&
            dBPropertylines.get(0).getPropertyfileName() != null) {
         modelPropertyFile = ApplicationUtils.dBPropertyLinesToModelPropertyFile(
               dBPropertylines, dBPropertylines.get(0).getCountryCode(), 
               dBPropertylines.get(0).getPropertyfileName());

         // get the id's from the database and add the to the model
         fillPropertyLinesWithDBIds(modelPropertyFile, fileName, countryCode);
      }
      return modelPropertyFile;
   }

   /**
    * DB query to countries table and retrieve a the fist occurence of 
    * country that current name start with the given country code.
    * @param countryCode
    * @return Countries object.
    */
   public Countries findCountryLikeCountryCode(final String countryCode) {
      LOG.debug("Retrieving the a country with the following country code: " + countryCode);
      CountriesDAO countriesDAO = DAOFactory.getDAOFactory().getCountriesDAO();
      return countriesDAO.findCountryByCode(countryCode);
   }

   /**
    * DB query to get all countries.
    * @return List of countries.
    */
   public List<Countries> getAllCountries() {
      LOG.debug("Retrieving all the Countries.");
      CountriesDAO countriesDAO = DAOFactory.getDAOFactory().getCountriesDAO(); 
      List<Countries> countries = countriesDAO.getAllCountries();
      return countries;
   }

   /**
    * DB query Propertylines to get all distinct language names given a filename and a country code.
    * @param fileName
    * @param countryCode
    * @return List of langunage names.
    */
   public List<String> getAllLanguages(final String fileName, final String countryCode) {
      LOG.debug("Retrieving all the Language of a property line with the following file's name and country code: " 
	      + fileName + " - " + countryCode);
      PropertylinesDAO propertylinesDAO = DAOFactory.getDAOFactory().getPropertyLinesDAO();
	  List<String> languages = propertylinesDAO.retrieveAllLanguages(fileName, countryCode);
      return languages;
   }

   /**
    * Persist all the property model.
    * @param propertyFile model.
    */
   public void persistStep3Changes(PropertyFile propertyFile){
     LOG.debug("The application is storing the changes related to Step3.");
     PropertylinesDAO propertylinesDAO = DAOFactory.getDAOFactory().getPropertyLinesDAO();
     propertylinesDAO.performDBPropertyFileUpdate(propertyFile);
   }

   /**
    * DB query to get all Sites.
    * @return List of Sites.
    */
   public List<Sites> getAllSites() {
      LOG.debug("Retriving all the Sites availabes.");
      SitesDAO sitesDAO = DAOFactory.getDAOFactory().getSitesDAO();
      List<Sites> sites = sitesDAO.findAll();
      return sites;
   }

   /**
    * DB query to get Sites given a site id.
    * @param id
    * @return Sites object.
    */
   public Sites getSiteById(Long id) {
      LOG.debug("Retriving the Sites with the following id: " + id); 
      SitesDAO sitesDAO = DAOFactory.getDAOFactory().getSitesDAO();
      Sites sites = sitesDAO.findById(id);
      return sites;
   }

   /**
    * Retrieve a list of Propertylines given a COUNTRY_CODE and a PROPERTYFILE_NAME
    * and fill the model with id's to identify the rows in the table PROPERYLINES.
    */
   public void fillPropertyLinesWithDBIds(PropertyFile modelPropertyFile, String fileName, String countryCode){
      LOG.debug("The application is merging the property line. File's name: " 
	      + fileName + " - Country code: " + countryCode);
      List<Propertylines> dbPropertyLines = findBDPropertyFile(fileName, countryCode);
      for (Propertylines dbPropertyLine : dbPropertyLines) {
         if (modelPropertyFile.getCountryCode().equals(dbPropertyLine.getCountryCode()) &&
               modelPropertyFile.getFileName().equals(dbPropertyLine.getPropertyfileName())){
            for (PropertyLine propertyLine : modelPropertyFile.getPropertyLines()) {
               if (propertyLine.getPropertyKey().equals(dbPropertyLine.getPropertyKey())){
                  for (LocatedValue locatedValue : propertyLine.getLocatedValues()) {
                     if (locatedValue.getLocale().equals(dbPropertyLine.getLang()) &&
                           locatedValue.getValue().equals(dbPropertyLine.getTranslationString())){
                        locatedValue.setId(dbPropertyLine.getId().toPlainString());
                     }
                  }
               }
            }
         }
      }
   }
   
   /**
	 * Adds entries to the database for country_code and language should check
	 * if have to update or add new entries depending on the language and
	 * country_code related, if entries already exists in the DB
	 * 
	 * @param lang
	 * @param countryCode
	 * @param srcProp properties object to add to the database
	 */
	public void importPropertiesValuestoDB(String fileName, String countryCode,
			String lang, Properties srcProp) {

		LOG.debug("The application is storing the changes of the imported keys.");
		PropertylinesDAO propertylinesDAO = DAOFactory.getDAOFactory()
				.getPropertyLinesDAO();
		propertylinesDAO.performImportPropertiesValuestoDB(fileName, countryCode,
				lang, srcProp);
	}

}
