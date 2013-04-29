package com.manpower.translations.service;

import java.util.List;

import com.manpower.translations.model.PropertyFile;

/**
 * File utility interface in charged of manipulate property files
 */
public interface PropertyFileManager {

	public static String SERVER_DEPLOY_LOCATION = "useDeployLocationProperty";
	
	/**
	 * Parse langFileAsString as a list of String.
	 */
	public abstract List<String> getAllExistentLanguages();

	/**
	 * Obtain a List of template file names from template location 
	 * that accomplish template name condition
	 */
	public abstract List<String> getTemplatesPropertyFileNames(String realPath);

	/**
	 * Open file a template file and make the conversion to expected interface contract
	 * Only load the keys stored in the template.
	 * @param templateFileName a concrete file name implementation of the template.
	 * @param countryCode 3 letters country identification.
	 * @return PropertyFile model.
	 */
	public abstract PropertyFile loadTemplateFileNameInPropertyFile(
			String templateFileName, String countryCode);

	/**
	 * Getter for langFileAsString loaded in spring from the 
	 * property key com.manpower.translationtool.lang.list 
	 * from translation-tools.properties file.
	 */
	public abstract String getLangFileAsString();

	/**
	 * Setter for langFileAsString loaded in spring from the 
	 * property key com.manpower.translationtool.lang.list 
	 * from translation-tools.properties file.
	 */
	public abstract void setLangFileAsString(String langFileAsString);

	/**
	 *  Adds a new language to a property model having in mind that the default 
	 *  value has to be loaded from the template file.
	 * @param file
	 * @param languageToProcess
	 */
	public abstract void addLanguageToPropertyFile(PropertyFile file, String languageToProcess);


	/**
	 * Saves the property instance in memory to properties files.
	 * @param serverDeployLocation
	 * @param file
	 * @param b
	 * @return boolean value
	 */
	public abstract boolean savePropertiesToFiles(String serverDeployLocation,
			PropertyFile file, boolean b);
	

	public abstract String getLocalStorage();

	public abstract void setLocalStorage(String localStorage);

	public abstract String getTemplateLocation();

	public abstract void setTemplateLocation(String templateLocation);

	public abstract String[] getDeployServers();

	public abstract void setDeployServers(String[] deployServers);

	public abstract String[] getLocalFiles();

	public abstract void setLocalFiles(String[] localFiles);
}