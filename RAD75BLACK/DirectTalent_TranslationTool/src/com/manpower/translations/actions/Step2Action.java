package com.manpower.translations.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.manpower.translations.model.LocatedValue;
import com.manpower.translations.model.PropertyFile;
import com.manpower.translations.model.PropertyLine;
import com.manpower.translations.service.PropertyFileManager;
import com.manpower.translations.service.PropertyFilesDBService;
import com.manpower.translations.utils.ApplicationUtils;

/**
 * The entry data process is almost implemented in this class,
 * this is where the languages are added/set as default/deleted
 * and the key values are modified.
 */
public class Step2Action extends ExtendedActionSupport {
   private static final long serialVersionUID = -341929526887892564L;

   private static Logger LOG = Logger.getLogger(Step2Action.class);
   private String selectedCountryCode;
   private String propFile;
   private PropertyFile file;
   private String languages;

   // We have to remove this attribute.
   private String selectedSiteId;
   private List<String> allLangs;
   private List<String> languagesList;
   private List<String> langFile;
   private PropertyFileManager propertyFileManager;
   private PropertyFilesDBService propertyFilesDBService;
   private String taskSelected;
   private final List<FilteredLanguage> filteLangs = new ArrayList<FilteredLanguage>();
   private String languageToProcess;
   private String isDefaultLanguage;
   private String selectedCountryName;

   private String resultMessage;

   /**
    *  Process information to be sent to the next screen
    *  Loads property file model with the request info in the
    *  session before forward.
    */
   @SuppressWarnings("unchecked")
   @Override
   protected final String post() {
      if (!StringUtils.isEmpty(languageToProcess) || !StringUtils.isEmpty(taskSelected)){
         String resultString = get();
         return resultString;
      }

      Map<String,Object> map = req.getParameterMap();
      for (String key : map.keySet()) {
         if (key.startsWith("prp__")){
            key = key.replaceAll("_dot_", ".");
            String[] temp = key.split("__");
            String propertyKey = temp[1];
            String lang = "";
            if (temp.length > 2){
               lang = temp[2];
            }
            String[] val = (String[]) map.get(key.replaceAll("\\.", "_dot_"));
            file.updateValue(propertyKey, lang, val[0]);
         }
      }
      req.getSession().setAttribute("file", this.file);
      return "success";
   }

   /**
    * Process display / redisplay of the page
    * Load some data from the session scope into local variables to be used in the page
    * The default selected language is choosen as this if it is there no language already selected as 'default'
    * The possible tasks in a redisplay could be: add a new language, remove a language, and
    * verify a language (when a set as default button is pressed)
    * If none of this choices are selected a redisplay is the normal flow process, when this occurs
    * the merge process is in charged of mix the template and the database info (in
    * some cases this is required not to happend) for example when going back from step3 to step2.
    */
   @Override
   protected final String get() {
      String result = ERROR;

      setPropFile(req.getSession().getAttribute("propFile").toString());
      this.selectedCountryCode = req.getSession().getAttribute("selectedCountryCode").toString();
      this.selectedCountryName = req.getSession().getAttribute("selectedCountryName").toString();
      LOG.debug("****"+this.getPropFile()+" / " + this.selectedCountryCode + "***********");
      LOG.debug("****"+this.getPropFile()+" / " + this.selectedCountryName + "***********");

      if (!StringUtils.isEmpty(languageToProcess) && !file.getLanguageList().contains(languageToProcess)){
         if (StringUtils.isEmpty(getDefaulFilteredLang()))
         { // if it's first language process it as default language
            isDefaultLanguage = "true";
         } else {
            isDefaultLanguage = "false";
         }
      } else {
    	  filteLangs.clear();
      }

      ActionTask task = ActionTask.getActionTask(taskSelected);

      try {
	    switch (task) {

	    case ADD_LANGUAGE:

		addLangueage(languageToProcess, isDefaultLanguage);
		langFile.add(languageToProcess);
		processFilterLanguage();
		result = INPUT;
		break;

	    case REMOVE_LANGUAGE:
		removeLanguage(languageToProcess);
		langFile.remove(languageToProcess);
		processFilterLanguage();
		result = INPUT;
		break;

	    case VERIFY_DEFAULT:
		file.setDefaultLang(languageToProcess);
		processFilterLanguage();
		result = INPUT;
		break;

	    default:
   		// Process a all the information needed to render the page.
   		// Is called every time that you need show the information in
   		// the jsp.
   		Boolean processMerge = !new Boolean((String) req.getSession()
   			.getAttribute("DO_NOT_REPROCESS_MERGE"));
   		if (selectedCountryCode == null || propFile == null) {
   		    // The parameters was not setup properties.
   		    resultMessage = "An error occurs while processing, Invalid country code or missing property file";
   		    result = ERROR;
   		    return result;
   		}

	      // load all available languages codes in the combo.
	      this.allLangs = propertyFileManager.getAllExistentLanguages();

         if (processMerge) {
            this.file = mergePropertiesFile();
         }

         //reconvert strings before refresh display
         for (PropertyLine line: file.getPropertyLines()) {
        	   for (LocatedValue located: line.getLocatedValues()) {
        		   located.setValue(ApplicationUtils.stringToHTMLString(located.getValue()));
	        	}
	      }

	      // shows filtered languages
	      processFilterLanguage();

			result = INPUT;
				break;
		   }

   	} catch (Exception e) {
         LOG.info(e);
      }

      languageToProcess = "";
      isDefaultLanguage = "";
      taskSelected = "";
      return result;
   }


   /**
    * Removes a language from all the keys available in
    * the property model.
    * @param languageToProcess String 2 digits language name
    */
    private void removeLanguage(String languageToProcess) {
    	PropertyFile propertyFileFromScreen = this.file;

    	List<PropertyLine> propetiesLines =  propertyFileFromScreen.getPropertyLines();

    	List<LocatedValue> listToRemove = new ArrayList<LocatedValue>();

		for (Iterator<PropertyLine> iterator = propetiesLines.iterator(); iterator.hasNext();) {
			PropertyLine propertyLine = iterator.next();

			List<LocatedValue> locatedValues = propertyLine.getLocatedValues();

			for (Iterator<LocatedValue> iterator2 = locatedValues.iterator(); iterator2
					.hasNext();) {

				LocatedValue locatedValue = iterator2.next();

				String localeToRemove = locatedValue.getLocale();

				if (StringUtils.endsWithIgnoreCase(localeToRemove, languageToProcess)) {
					listToRemove.add(locatedValue);
					break;
				}
			}

			for (LocatedValue toBeRemoved : listToRemove) {
			   for (int i = 0; i < locatedValues.size(); i++) {
               LocatedValue locatedValue = locatedValues.get(i);
               if (toBeRemoved.getLocale().equals(locatedValue.getLocale())){
                  locatedValues.remove(i);
                  break;
               }
            }
         }
			propertyLine.setLocatedValues(locatedValues);

		}
	}

    /**
     * Adds a language to all the keys available in
     * the property file model.
     * @param languageToProcess String 2 digits language name.
     * @param isDefaultLanguage String ["true"|"false"].
     */
	private void addLangueage(String languageToProcess, String isDefaultLanguage) {
	   propertyFileManager.addLanguageToPropertyFile(file, languageToProcess);

	   if(!StringUtils.isEmpty(isDefaultLanguage)){
			Boolean isDefault = Boolean.valueOf(isDefaultLanguage);
			if(isDefault){
			   file.setDefaultLang(languageToProcess);
			}
		}

	}

	/**
	 * Joins the property template file information and the existent database
	 * information following the restrictions predefined for the business rules.
	 * @return property model with the merged information
	 */
	private PropertyFile mergePropertiesFile() {

		// retrieve instances of the template from the database
	    PropertyFile propertyFileFromDb = propertyFilesDBService.findModelPropertyFile(propFile, selectedCountryCode);

	    //load lang file with languages that exists on the db for that templates instances
	    this.langFile = propertyFileFromDb.getLanguageList();

	    // load template file from the sandbox
	    PropertyFile propertyFileFromDisk = propertyFileManager.loadTemplateFileNameInPropertyFile(propFile, selectedCountryCode);

	    return ApplicationUtils.mergePropertiesFiles(propertyFileFromDb, propertyFileFromDisk);
	}

	/**
    * Creates the filter related information for the
    * language that it will be shown in the screen.
    */
   @SuppressWarnings("unchecked")
   private void processFilterLanguage() {
      for (String lang : langFile) {
         boolean show = true;
         boolean isDefault = false;
         if(file.getDefaultLang()!=null){
            if(file.getDefaultLang().equals(lang)){
               isDefault = true;
        	}
         } else {
          LOG.info("NO DEFAULT LANG SELECTED YET");
         }
         FilteredLanguage filtered = getFilteredLangByLangName(lang);
         if (filtered == null){
            getFilteLangs().add(new FilteredLanguage(show, lang, isDefault));
         }
      }
      languages = StringUtils.join(getLangFile(), ",");
      List<String> ll = new ArrayList<String>(CollectionUtils.disjunction(allLangs, getLangFile()));
      Collections.sort(ll);
      setLanguagesList(ll);
   }

   /**
    * @param lang 2 digits string lang name
    * @return boolean if lang is in filtered lang object
    */
   public boolean filteredLangContainsLang(String lang){
      for (FilteredLanguage fl : filteLangs) {
         if (fl.getLang().equals(lang)){
            return true;
         }
      }
      return false;
   }

   /**
    * Gets a default language from the filtered information if exists.
    * @return a String language name if exists ortherwise an empty string
    */
   public String getDefaulFilteredLang(){
      for (FilteredLanguage fl : filteLangs) {
         if(fl.isDefault()){
            return fl.getLang();
         }
      }
      return "";
   }

   /**
    * Gets a language from filtered list given a language name.
    * @param langName 2 digits language name
    * @return FilteredLanguage related to the language name param, otherwise null
    */
   public FilteredLanguage getFilteredLangByLangName(String langName){
      FilteredLanguage var = null;
      for (FilteredLanguage fl : filteLangs) {
         if (fl.getLang().equals(langName)){
            var = fl;
            break;
         }
      }
      return var;
   }
   /**
    * Setter for the template file name to process.
    * @param propFile <code>String</code> with the propFile.
    */
   public final void setPropFile(final String propFile) {
      this.propFile = propFile;
   }

   /**
    * Getter the template file name to process.
    * @return <code>String</code> with the propFile.
    */
   public final String getPropFile() {
      return propFile;
   }

   /**
    * Sets the selectedCountryCode.
    * @param selectedCountryCode <code>String</code> with the selectedCountryCode.
    */
   public final void setSelectedCountryCode(final String selectedCountryCode) {
      this.selectedCountryCode = selectedCountryCode;
   }

   /**
    * Returns the selectedCountryCode.
    * @return <code>String</code> with the selectedCountryCode.
    */
   public final String getSelectedCountryCode() {
      return selectedCountryCode;
   }

   /**
    * Setter for property file object with all the model data.
    * @param file <code>PropertyFile</code> with the file.
    */
   public final void setFile(final PropertyFile file) {
      this.file = file;
   }

   /**
 	* Getter for property file object with all the model data.
    * @return <code>PropertyFile</code> with the file.
    */
   public final PropertyFile getFile() {
      return file;
   }

   /**
    * Setter for a comma delimited list of languages to be processed in a string.
    * @param languages <code>String</code> with the languages.
    */
   public final void setLanguages(final String languages) {
      this.languages = languages;
   }

   /**
    * Getter for a comma delimited list of languages to be processed in a string.
    * @return <code>String</code> with the languages.
    */
   public final String getLanguages() {
      return languages;
   }

   /**
    * Setter for list of languages that has not been selected yet.
    * @param languagesList <code>Collection<String></code> with the languagesList.
    */
   public final void setLanguagesList(final List<String> languagesList) {
      this.languagesList = languagesList;
   }

   /**
    * Getter for list of languages that has not been selected yet.
    * @return <code>Collection<String></code> with the languages List.
    */
   public List<String> getLanguagesList() {
      return languagesList;
   }

   /**
    * Sets the selectedSiteId.
    *
    * @param selectedSiteId
    *           <code>String</code> with the selectedSiteId.
    */
   public final void setSelectedSiteId(final String selectedSiteId) {
      this.selectedSiteId = selectedSiteId;
   }

   /**
    * Returns the selectedSiteId.
    *
    * @return <code>String</code> with the selectedSiteId.
    */
   public final String getSelectedSiteId() {
      return selectedSiteId;
   }

   /**
    * Setter for a string List with the actually used languages.
    * @param langFile <code>List<String></code> with the langFile.
    */
   public final void setLangFile(final List<String> langFile) {
      this.langFile = langFile;
   }

   /**
    * Getter for a string List with the actually used languages.
    * @return <code>List<String></code> with the actually used languages.
    */
   public final List<String> getLangFile() {
      return langFile;
   }

   /**
    * Getter for the list of filtered languages.
    * @return <code>List<FilteredLanguage></code> with the filteLangs.
    */
   public List<FilteredLanguage> getFilteLangs() {
      return filteLangs;
   }

   /**
    * Gets a language list from filtered languages as a list of string.
    * @return a String List of languages.
    */
   public List<String> getLangList() {
      List<String> ret = new ArrayList<String>();
      for (FilteredLanguage filteredLanguage : getFilteLangs()) {
         ret.add(filteredLanguage.getLang());
      }
      return ret;
   }

   /**
    * A language to filter.
    */
   public static class FilteredLanguage {
      private final boolean show;
      private final String lang;
      private boolean isDefault;

	/**
	 * Creates the FilteredLanguage.
	 * @param show boolean, should be shown in the filter
	 * @param lang 2 digits string lang name
	 * @param isDefault boolean, is a dafault language.
	 */
      public FilteredLanguage(final boolean show, final String lang, final boolean isDefault) {
    	  super();
	      this.show = show;
	      this.lang = lang;
	      this.isDefault = isDefault;
      }

      /**
       * Returns the lang.
       * @return <code>String</code> with the lang.
       */
      public final String getLang() {
         return lang;
      }

      /**
       * Returns the show.
       * @return <code>boolean</code> with the show.
       */
      public final boolean isShow() {
         return show;
      }

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

   }

   /**
    * Getter for the disk Property File Manager utilities.
    * @return PropertyFileManager.
    */
   public PropertyFileManager getPropertyFileManager() {
      return propertyFileManager;
   }

   /**
    * Setter for the disk Property File Manager utilities.
    * @param propertyFileManager
    */
   public void setPropertyFileManager(PropertyFileManager propertyFileManager) {
      this.propertyFileManager = propertyFileManager;
   }

   /**
    * Getter for the Property File database service utilities.
    * @return PropertyFilesDBService.
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

   /**
    * Getter for task to process.
    * @see ActionTask
    * @return String: task selected.
    */
   public String getTaskSelected() {
   	return taskSelected;
   }

   /**
    * Setter for task to process.
    * @see ActionTask
    * @param taskSelected task selected String.
    */
   public void setTaskSelected(String taskSelected) {
   	this.taskSelected = taskSelected;
   }


   /**
    * Getter for language to be processed. Determine if a language has
    * been selected from the form.
    * @return String as selected language.
    */
   public String getLanguageToProcess() {
   	return languageToProcess;
   }

   /**
    * Setter for language to be processed.
    * @param languageToProcess String language to be processed.
    */
   public void setLanguageToProcess(String languageToProcess) {
   	this.languageToProcess = languageToProcess;
   }

   /**
    * Getter for variable that identifies if languageToProcess
    * will be process as a default language.
    * @return ["true"|"false"].
    */
   public String getIsDefaultLanguage() {
   	return isDefaultLanguage;
   }

   /**
    * Setter for variable that identifies if languageToProcess
    * will be process as a default language.
    * @param isDefaultLanguage ["true"|"false"].
    */
   public void setIsDefaultLanguage(String isDefaultLanguage) {
   	this.isDefaultLanguage = isDefaultLanguage;
   }

   /**
    * Getter for list of all the language supported by the application
    * Loaded from the translation-tool properties file.
    * @return List of 2 digits language string.
    */
   public List<String> getAllLangs() {
   	return allLangs;
   }

   /**
    * Setter for list of all the language supported by the application.
    * @param allLangs List of 2 digits language string.
    */
   public void setAllLangs(List<String> allLangs) {
   	this.allLangs = allLangs;
   }

   /**
    * Getter for selected country name.
    * @return String as country name.
    */
   public String getSelectedCountryName() {
      return selectedCountryName;
   }

   /**
    * Setter for selected country name.
    * @param selectedCountryName String as country name.
    */
   public void setSelectedCountryName(String selectedCountryName) {
      this.selectedCountryName = selectedCountryName;
   }

   public String getResultMessage() {
      return resultMessage;
   }

   public void setResultMessage(String resultMessage) {
      this.resultMessage = resultMessage;
   }

}
