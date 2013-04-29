package com.manpower.translations.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manpower.translations.model.LocatedValue;
import com.manpower.translations.model.PropertyFile;
import com.manpower.translations.model.PropertyLine;
import com.manpower.translations.model.SimpleLocatedValue;
import com.manpower.translations.model.SimplePropertyFile;
import com.manpower.translations.model.SimplePropertyLine;
import com.manpower.translations.utils.ApplicationUtils;

/**
 * File utility class in charged of manipulate property files
 */
public class PropertyFileManagerImpl implements PropertyFileManager {

   private static final Logger LOG = LoggerFactory.getLogger(PropertyFileManagerImpl.class);
   private String templateLocation;
   private String localStorage;
   private String langFileAsString;
   private String[] deployServers;
   private String[] localFiles;

   /**
    * Default contructor
    */
   public PropertyFileManagerImpl() {
   }

	/**
	 * Parse langFileAsString as a list of String.
	 */
   public List<String> getAllExistentLanguages(){
	   String[] langs = langFileAsString.split(",");
	   return Arrays.asList(langs);
   }

   /**
    * Load all the templates from the template location.
    * @param realPath application real path.
    * @return File[] array of tempalte files.
    */
   private File[] getAllPropertyFiles(String realPath) {
      File folder = new File(realPath + templateLocation);
      return folder.listFiles();
   }



   /**
    * Open all properties file's keys/values from templates location.
    * @param fileName file name
    * @return file ResourceBundle
    */
   public static ResourceBundle getResourceBundlePropertiesFile(final String fileName) {
	  ApplicationUtils.clearResourceBundle();
	  String nameWithoutExtension = fileName.substring(0, fileName.lastIndexOf("."));
      return PropertyResourceBundle.getBundle("com.manpower.templates." + nameWithoutExtension);
   }

	/**
	 * Obtain a List of template file names from template location
	 * that accomplish template name condition
	 */
   public List<String> getTemplatesPropertyFileNames(String realPath) {
      File[] files = getAllPropertyFiles(realPath);
      List<String> propertyFiles = new ArrayList<String>();
      if (files != null && files.length > 0) {
         for (int i = 0; i < files.length; i++) {
            if (isTemplateFile(files[i].getName()) && !files[i].isDirectory()) {
               propertyFiles.add(files[i].getName());
            }
         }
      }
      return propertyFiles;
   }

   /**
    * Identifies if a file name is a valid template file name.
    */
   public static boolean isTemplateFile(String fileName) {
      return fileName != null
         && !fileName.equals("")
         && fileName.length() > 2
         && !(fileName.charAt(4) == '_' && fileName.charAt(fileName.length()-14) == '_');
   }

   /**
    * Parses the name of the template.
    * @param fileName
    * @return string template name.
    * @throws IOException
    */
   public static String parseTemplateName(String fileName) throws IOException{
      String beforeDot = fileName.split("\\.")[0];
      if (isTemplateFile(fileName)) {
         return beforeDot;
      }
      throw new IOException("trying to parse an invalid file name");
   }

   /**
    * Create a valid instance file name given the parameters.
    * @param countryCode
    * @param templateName
    * @param lang
    * @return String file name.
    * @throws Exception.
    */
   private String createAndValidateFileNameIntance(String countryCode, String templateName, String lang) throws Exception{
      if (countryCode != null && templateName != null && lang != null &&
            !countryCode.equals("") && !templateName.equals("") && !lang.equals("")){
         String postFix =  countryCode + "_" + templateName + "_" + lang + ".properties";
         return postFix;
      }
      throw new Exception("invalid file name for a concrete instance of a propertie file");
   }

	/**
	 * Open file a template file and make the conversion to expected interface contract
	 * Only load the keys stored in the template.
	 * @param templateFileName a concrete file name implementation of the template.
	 * @param countryCode 3 letters country identification.
	 * @return PropertyFile model.
	 */
   public PropertyFile loadTemplateFileNameInPropertyFile(String templateFileName, String countryCode) {
      PropertyFile propertyFile = new SimplePropertyFile(countryCode, templateFileName);

      ResourceBundle resource = getResourceBundlePropertiesFile(templateFileName);
      Enumeration<String> keys = resource.getKeys();

      while (keys.hasMoreElements()) {
           String propertyKey = keys.nextElement();
           PropertyLine propertyLine = new SimplePropertyLine(propertyKey);
           propertyFile.addNewPropertyLine(propertyLine);
      }
      return propertyFile;
   }

   /**
    *  Adds a new language to a property model having in mind that the default
    *  value has to be loaded from the template file.
    *  @param file
    *  @param languageToProcess
    */
   public void addLanguageToPropertyFile(PropertyFile file, String languageToProcess){
      ResourceBundle resource = getResourceBundlePropertiesFile(file.getFileName());

      for (int i = 0; i < file.getPropertyLines().size(); i++) {
         PropertyLine propertyLine = file.getPropertyLines().get(i);
         Enumeration<String> keys = resource.getKeys();

         while (keys.hasMoreElements()) {
            String propertyKey = keys.nextElement();
            if (propertyLine.getPropertyKey().equals(propertyKey)){
               LocatedValue lv = propertyLine.getLocatedValue(languageToProcess);
               String translationString = ApplicationUtils.stringToHTMLString(resource.getString(propertyKey));
               if (StringUtils.isEmpty(translationString)){
                  translationString = " ";
               }
               if (lv != null){
                  lv.setValue(translationString);
               }else{
                  LocatedValue locatedValue = new SimpleLocatedValue(languageToProcess);
                  locatedValue.setValue(translationString);
                  propertyLine.addLocale(locatedValue);
               }
            }
         }
      }
   }

   /**
    * Converts a property file model to a property map.
    * @param propertyFile
    * @return Map<String, Properties>: a map of file names mapped to properties files
    */
   private Map<String, Properties> propertyFileToPropertyMap(PropertyFile propertyFile){
      Map<String, Properties> props = new HashMap<String, Properties>();
      String fileName = null;
      String templateName = null;
      try {
         templateName = parseTemplateName(propertyFile.getFileName());
      } catch (IOException e1) {
         e1.printStackTrace();
      }
      String countryCode = propertyFile.getCountryCode();
      List<PropertyLine> propertyLines = propertyFile.getPropertyLines();
      for (Iterator<PropertyLine> lineIter = propertyLines.iterator(); lineIter.hasNext();) {
         PropertyLine propertyLine = (PropertyLine) lineIter.next();
         String propertyKey = propertyLine.getPropertyKey();
         List<LocatedValue> locatedValues = propertyLine.getLocatedValues();
         for (Iterator<LocatedValue> localeIter = locatedValues.iterator(); localeIter.hasNext();) {
            LocatedValue locatedValue = (LocatedValue) localeIter.next();
            String lang = locatedValue.getLocale();
            String value = locatedValue.getValue();
            try {
               fileName = createAndValidateFileNameIntance(countryCode, templateName, lang);
            } catch (Exception e) {
               e.printStackTrace();
            }

            Properties prop = null;
            if (!props.containsKey(fileName)){
               prop = new Properties();
            }else{
               prop = props.get(fileName);
            }
            prop.put(propertyKey, value);
            props.put(fileName, prop);
         }
      }
      return props;
   }

   /**
    * Saves the property instance in memory to properties files.
    * @param propertyFile
    * @return true if no problem occurs
    */
   public boolean savePropertiesToFiles(String basePath, PropertyFile propertyFile, boolean useFolder) {
      File folderFile = null;

      String folder = "";
      if (useFolder) {
          try {
              folder = propertyFile.getCountryCode() +
              	"_" + parseTemplateName(propertyFile.getFileName()) + File.separatorChar;
           } catch (IOException e) {
              e.printStackTrace();
           }
      }
      if (basePath.equals(PropertyFileManager.SERVER_DEPLOY_LOCATION)){
    	  folderFile = new File(localStorage + File.separatorChar + folder);
	  } else {
		  folderFile = new File(basePath + File.separatorChar + folder);
	  }

      if (folderFile.exists()) {
    	  for (File file : folderFile.listFiles()) {
    		  file.delete();
    	  }
      }
      if (!folderFile.mkdirs() && !folderFile.exists()){
    	 LOG.error("failed to create folder to store properties: " +
    			 folderFile.getAbsolutePath() + ", may have no permission to do it");
      } else {
	      //store cusomized files
	      storePropertyMap(folderFile, propertyFileToPropertyMap(propertyFile), propertyFile.getDefaultLang());
	      this.setLocalFiles(folderFile.list());

      }
      return true;
   }

   /**
    * Persist the map to files location
    * @param folderFile
    * @param propertyMap Map<fileNames, Properties map>
    * @return true if no problem
    */
   private boolean storePropertyMap(File folderFile, Map<String, Properties> propertyMap, String defaultLanguage){
      LOG.debug("Entering property map store method");

      for (Iterator<String> iterator = propertyMap.keySet().iterator(); iterator.hasNext();) {
         String fileN = (String) iterator.next();
         String langName = fileN.substring(fileN.lastIndexOf("_")+1, fileN.lastIndexOf("_")+3);
         File defaultF = null;
         if (defaultLanguage.equals(langName)) {
        	 defaultF = new File(folderFile.getAbsolutePath() + File.separatorChar + fileN.substring(0, fileN.lastIndexOf("_")) + ".properties");
         }
         File f = new File(folderFile.getAbsolutePath() + File.separatorChar + fileN);

         try {

        	if (defaultF != null){
        		if (defaultF.exists()){
        			defaultF.delete();
        		}
        		defaultF.createNewFile();
    		}

    		if (f.exists()) {
    			f.delete();
    		}
    		f.createNewFile();

            Properties prop = propertyMap.get(fileN);
            FileOutputStream out =  new FileOutputStream(f);
            LOG.info("Storing the property file: " + fileN + "  to the location: " + f.getAbsolutePath());
            prop.store(out, null);
            out.close();

            if (defaultF != null){
            	out =  new FileOutputStream(defaultF);
                LOG.info("Storing the property file: " + fileN + "  to the location: " + f.getAbsolutePath());
                prop.store(out, null);
                out.close();
    		}
         } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
         } catch (IOException e) {
            e.printStackTrace();
            return false;
         }
      }
      return true;
   }

	public String getLocalStorage() {
		return localStorage;
	}

	public void setLocalStorage(String localStorage) {
		this.localStorage = localStorage;
	}

	public String getTemplateLocation() {
		return templateLocation;
	}

	public void setTemplateLocation(String templateLocation) {
		this.templateLocation = templateLocation;
	}

	public String getLangFileAsString() {
		return langFileAsString;
	}

	public void setLangFileAsString(String langFileAsString) {
		this.langFileAsString = langFileAsString;
	}

	public String[] getDeployServers() {
		return deployServers;
	}

	public void setDeployServers(String[] deployServers) {
		this.deployServers = deployServers;
	}

	public String[] getLocalFiles() {
		return localFiles;
	}

	public void setLocalFiles(String[] localFiles) {
		this.localFiles = localFiles;
	}

}
