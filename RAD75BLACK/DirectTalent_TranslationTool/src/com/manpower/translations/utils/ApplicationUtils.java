package com.manpower.translations.utils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

import com.manpower.translations.beans.Propertylines;
import com.manpower.translations.model.LocatedValue;
import com.manpower.translations.model.PropertyFile;
import com.manpower.translations.model.PropertyLine;
import com.manpower.translations.model.SimpleLocatedValue;
import com.manpower.translations.model.SimplePropertyFile;
import com.manpower.translations.model.SimplePropertyLine;
import com.manpower.translations.service.PropertyFileManagerImpl;

/**
 * Some utilities conversion method between model and database objects
 */
public class ApplicationUtils {

   private static final String THE_PROPERTY_FILE_CAN_NOT_BE_NULL = "The property file can not be null.";

   /**
    * Given a model loaded from properties files convert to database ready objects.
    * @param propertyFile
    * @return database Propertylines
    */
   public static Propertylines modelPropertyFileToDBPropertyLines(PropertyFile propertyFile){
      SimplePropertyFile modelPropertyFile  = (SimplePropertyFile)propertyFile;
      String countryCode = modelPropertyFile.getCountryCode();
      String propertyfileName = modelPropertyFile.getFileName();
      String defaultLang = modelPropertyFile.getDefaultLang();
      List<PropertyLine> modelPropertyLines = modelPropertyFile.getPropertyLines();
      return modelPropertyFileToDBPropertyLines(modelPropertyLines, countryCode, propertyfileName, defaultLang);
   }

   /**
    * Given a model loaded from properties files convert to database ready objects.
    * @param modelPropertyLines
    * @param countryCode
    * @param propertyfileName
    * @return database Propertylines
    */
   public static Propertylines modelPropertyFileToDBPropertyLines(List<PropertyLine> modelPropertyLines, String countryCode, String propertyfileName, String defaultLang){
      Propertylines dBPropertylines = new Propertylines();
      for (Iterator<PropertyLine> modelPropertyLineIterator = modelPropertyLines.iterator(); modelPropertyLineIterator.hasNext();) {
         PropertyLine modelPropertyLine = modelPropertyLineIterator.next();
         String propertyKey = modelPropertyLine.getPropertyKey();
         List<LocatedValue> modelLocatedValues = modelPropertyLine.getLocatedValues();
         for (Iterator<LocatedValue> localeIterator =
               modelLocatedValues.iterator(); localeIterator.hasNext();) {
            LocatedValue modelLocatedValue = localeIterator.next();
            Date createdOn = modelLocatedValue.getCreatedOn();
            Date updatedOn = modelLocatedValue.getUpdatedOn();
            String lang = modelLocatedValue.getLocale();
            String translationString = modelLocatedValue.getValue();

            dBPropertylines.setCountryCode(countryCode);
            dBPropertylines.setLang(lang);
            dBPropertylines.setPropertyfileName(propertyfileName);

            if (!StringUtils.isEmpty(defaultLang) && !StringUtils.isEmpty(lang) && defaultLang.equals(lang)){
               dBPropertylines.setDefaultLang("true");
            } else {
               dBPropertylines.setDefaultLang("false");
            }
            dBPropertylines.setPropertyKey(propertyKey);
            dBPropertylines.setTranslationString(translationString);
            dBPropertylines.setCreatedOn(createdOn);
            dBPropertylines.setUpdatedOn(updatedOn);
         }
      }
      return dBPropertylines;
   }

   /**
    * given a database object return a model loaded object for the specific countryCode and propertyfileName, if null  or empty value don't filter
    * @param dBPropertylines each row of the database table
    * @return Property File model object
    */
   public static PropertyFile dBPropertyLinesToModelPropertyFile(List<Propertylines> dBPropertylines, String countryCode, String propertyfileName){
      PropertyFile modelPropertyFile  = new SimplePropertyFile(countryCode, propertyfileName);

      for (Propertylines dBPropertyline : dBPropertylines) {
         String countryCodeLocal = dBPropertyline.getCountryCode();
         String propertyfileNameLocal = dBPropertyline.getPropertyfileName();

         if (countryCodeLocal != null && propertyfileNameLocal != null && !countryCodeLocal.equals("") && !propertyfileNameLocal.equals("")){
        	String lang = dBPropertyline.getLang();
            String propertyKey = dBPropertyline.getPropertyKey();
            String translationString = dBPropertyline.getTranslationString();
            Date createdOn = dBPropertyline.getCreatedOn();
            Date updatedOn = dBPropertyline.getUpdatedOn();
            if (!StringUtils.isEmpty(dBPropertyline.getDefaultLang()) && dBPropertyline.getDefaultLang().equals("true")){
               modelPropertyFile.setDefaultLang(lang);
            }
            LocatedValue locatedValue = modelPropertyFile.findByKeyAndLocale(propertyKey, lang);
            if (locatedValue == null) {
               PropertyLine propertyLine = new SimplePropertyLine(propertyKey);
               locatedValue = new SimpleLocatedValue(lang);
               locatedValue.setValue(translationString);
               propertyLine.addLocale(locatedValue);
               locatedValue.setCreatedOn(createdOn);
               locatedValue.setUpdatedOn(updatedOn);
               modelPropertyFile.addNewPropertyLine(propertyLine);
            }else{
               locatedValue.setValue(translationString);
               PropertyLine propertyLine = modelPropertyFile.getLine(propertyKey);
               locatedValue.setCreatedOn(createdOn);
               locatedValue.setUpdatedOn(updatedOn);
               propertyLine.addLocale(locatedValue);
            }
         }
      }
      return modelPropertyFile;
   }

   /**
    * Remove extension of the file name.
    * @param fileName String.
    * @return String name.
    */
   public static String removeFileExtension(String fileName){
      return fileName.substring(0, fileName.lastIndexOf("."));
   }

   /**
    * Merge property File From Db and property File From Template following the bussiness rules.
    * @param propertyFileFromDb PropertyFile retrieved from the Database.
    * @param propertyFileFromDisk PropertyFile retrieved from the Disk.
    * @return A PropertyFile with the merged version of the information.
    * @throws IllegalArgumentException This exception is going to throw if the parameters are null.
    */
   public static PropertyFile mergePropertiesFiles(final PropertyFile propertyFileFromDb, final PropertyFile propertyFileFromDisk)
   			throws IllegalArgumentException {

	   if(null == propertyFileFromDb || null == propertyFileFromDisk){
		   throw new IllegalArgumentException(THE_PROPERTY_FILE_CAN_NOT_BE_NULL);
	   }

	   //I retrieves the property lines from the disk.
	   List<PropertyLine> listOfPropertyLinesFromDisk = propertyFileFromDisk.getPropertyLines();

	  //I retrieves the property lines from the data bases.
	   List<PropertyLine> listOfPropertyLinesFromDB = propertyFileFromDb.getPropertyLines();

	   //It is the property file that it is going to contain the merged information.
	   PropertyFile mergedPropertyFile = propertyFileFromDisk;

	   // set the default language, it was loaded on model load
	   mergedPropertyFile.setDefaultLang(propertyFileFromDb.getDefaultLang());

	   mergedPropertyFile.setCountryCode(propertyFileFromDb.getCountryCode());
	   propertyFileFromDb.setFileName(propertyFileFromDb.getFileName());

      for (PropertyLine propertyLineTemplate : listOfPropertyLinesFromDisk) {
         boolean dbEntryFound = false;
         for (PropertyLine propertyLineDB : listOfPropertyLinesFromDB) {
            if (StringUtils.equals(propertyLineTemplate.getPropertyKey(), propertyLineDB.getPropertyKey())) {
               for (LocatedValue locatedValueDB : propertyLineDB.getLocatedValues()) {
                  propertyLineTemplate.getLocatedValues().add(locatedValueDB);
                  dbEntryFound = true;
               }
            }
         }
         if (!dbEntryFound){
            for (String languageToProcess : propertyFileFromDb.getLanguageList()) {
               LocatedValue locatedValueTemplate = new SimpleLocatedValue(languageToProcess);
               ResourceBundle rb = PropertyFileManagerImpl.getResourceBundlePropertiesFile(propertyFileFromDisk.getFileName());
               String translationString = (String) rb.getObject(propertyLineTemplate.getPropertyKey());
               locatedValueTemplate.setValue(translationString);
               propertyLineTemplate.getLocatedValues().add(locatedValueTemplate);
            }
         }
      }

      for (PropertyLine pl: mergedPropertyFile.getPropertyLines()) {
    	 Collections.sort(pl.getLocatedValues(), new Comparator<LocatedValue>(){
			public int compare(LocatedValue o1, LocatedValue o2) {
				return o1.getLocale().compareTo(o2.getLocale());
			}});
      }
	   return mergedPropertyFile;
   }

   /**
    * Convert string in order to make any HTML string able to be processed using a valid charset.
    * @param string to convert.
    * @return Transformed string.
    */
   public static String stringToHTMLString(String string) {
      StringBuffer sb = new StringBuffer(string.length());
      // true if last char was blank
      boolean lastWasBlankChar = false;
      int len = string.length();
      char c;

      for (int i = 0; i < len; i++)
          {
          c = string.charAt(i);
          if (c == ' ') {
              // blank gets extra work,
              // this solves the problem you get if you replace all
              // blanks with &nbsp;, if you do that you loss
              // word breaking
              if (lastWasBlankChar) {
                  lastWasBlankChar = false;
                  sb.append("&nbsp;");
                  }
              else {
                  lastWasBlankChar = true;
                  sb.append(' ');
                  }
              }
          else {
              lastWasBlankChar = false;
              //
              // HTML Special Chars
              if (c == '"')
                  sb.append("&quot;");
              else if (c == '&')
                  sb.append("&amp;");
              else if (c == '<')
                  sb.append("&lt;");
              else if (c == '>')
                  sb.append("&gt;");
              else if (c == '\n')
                  // Handle Newline
                  sb.append("&lt;br/&gt;");
              else {
                  int ci = 0xffff & c;
                  if (ci < 160 )
                      // nothing special only 7 Bit
                      sb.append(c);
                  else {
                      // Not 7 Bit use the unicode system
                      sb.append("&#");
                      sb.append(new Integer(ci).toString());
                      sb.append(';');
                      }
                  }
              }
          }
      return sb.toString();
  }

   /**
    * Clear's the cache list Map of the ResourceBundle class,
    * this affects all the loaded resources bundles.
    */
   @SuppressWarnings("unchecked")
   public static void clearResourceBundle(){
      try {
          Class type = ResourceBundle.class;
          Field cacheList;
         cacheList = type.getDeclaredField("cacheList");
          cacheList.setAccessible(true);
          ((Map)cacheList.get(ResourceBundle.class)).clear();
      } catch (SecurityException e) {
         e.printStackTrace();
      } catch (NoSuchFieldException e) {
         e.printStackTrace();
      } catch (IllegalArgumentException e) {
         e.printStackTrace();
      } catch (IllegalAccessException e) {
         e.printStackTrace();
      }
   }

}
