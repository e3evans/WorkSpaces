package com.manpower.translations.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.manpower.translations.model.PropertyFile;
import com.manpower.translations.service.PropertyFileManager;
import com.manpower.translations.service.PropertyFilesDBService;
import com.manpower.translations.utils.DeployUtility;

/**
 * This class is in charged of the delivery of the information to remote devices.
 *
 */
public class Step4Action extends ExtendedActionSupport {

    private static final long serialVersionUID = -5296159264706829576L;

    private static Logger LOG = Logger.getLogger(Step4Action.class);

    private String selectedCountryCode;
    private PropertyFilesDBService propertyFilesDBService;
    private PropertyFileManager propertyFileManager;
    private String propFile;
    private PropertyFile file;
    private String languages;
    private String selectedSiteId;

    private String resultMessage;

    private static final String DEPLOY_ACTION = "deploy";

    /**
     * Process information to be deployed and/or stored to database
     * Retrieves the property model, selected from page deploy location and servers from session
     * Stores the property model to a temporal location, then if it's required the deployment;
     * remote files are deleted, the new files are iterated and copy for each selected server uri,
     * the location of the servers and ports are located in translation-tool properties file
     * Finally the store to database process is called sending the property model as parameter.
     */
    @Override
    protected final String post() {
    	String returnValue = SUCCESS;
    	if ((PropertyFile) req.getSession().getAttribute("file") != null){
    		file = (PropertyFile) req.getSession().getAttribute("file");
    	}
        String[] server = req.getParameterValues("deployLocation");
        String allServers = req.getParameter("All");

        propertyFileManager.savePropertiesToFiles(PropertyFileManager.SERVER_DEPLOY_LOCATION, file, true);


        if (DEPLOY_ACTION.equals(req.getParameterValues("buttonToProcess")[0])) {
	        //Deploy Process
	        if (server.length >= 2 || allServers != null) {
	           String[] deployServers = null;
              if (allServers != null) {
                 deployServers = propertyFileManager.getDeployServers();
              } else {
                 List<String> alist = new ArrayList<String>();
            	  for (int i = 0; i < server.length; i++) {
            	     if (!server[i].equals("")) {
            		     alist.add(server[i]);
            		  }
				     }
            	  deployServers = alist.toArray(new String[alist.size()]);
              }
		        ResourceBundle rb = ResourceBundle.getBundle("translation-tool");
		        String [] localFiles = propertyFileManager.getLocalFiles();
		        if (localFiles != null) {
		           resultMessage = "";
		           for (int i = 0; i < deployServers.length; i++) {
		              String fileToWildCard = file.getCountryCode() + "_"
		                 + file.getFileName().substring(0, file.getFileName().lastIndexOf("."));
		              resultMessage = DeployUtility.deleteRemoteFiles(deployServers[i].toLowerCase(),
		                    fileToWildCard, rb);
		              if (!resultMessage.endsWith(" updated successfully.")) {
		                 returnValue = ERROR;
		                 break;
		              }

		              for (int j = 0; j < localFiles.length; j++) {
		                 resultMessage = DeployUtility.deployPropertyFile(deployServers[i]
		                     .toLowerCase(), localFiles[j], rb);
		                 if (!resultMessage.endsWith(" updated successfully.")) {
		                    returnValue = ERROR;
		                    break;
		                 }
		              }
		           }
		        }
	        }
        }

        propertyFilesDBService.persistStep3Changes(file);

        req.getSession().setAttribute("file", null);

        return returnValue;
    }

    /**
     *  This method is not required, is implemented to override the default
     *  value and indicate that it should not be called .
     */
    @Override
    protected final String get() {
        return ERROR;
    }

    public final void setSelectedCountryCode(final String selectedCountryCode) {
        this.selectedCountryCode = selectedCountryCode;
    }

    public final String getSelectedCountryCode() {
        return selectedCountryCode;
    }

    public final void setPropFile(final String propFile) {
        this.propFile = propFile;
    }

    public final String getPropFile() {
        return propFile;
    }

    public final void setFile(final PropertyFile file) {
        this.file = file;
    }

    public final PropertyFile getFile() {
        return file;
    }

    public final void setLanguages(final String languages) {
        this.languages = languages;
    }

    public final String getLanguages() {
        return languages;
    }

    public void setSelectedSiteId(final String selectedSiteId) {
        this.selectedSiteId = selectedSiteId;
    }

    public String getSelectedSiteId() {
        return selectedSiteId;
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

   public String getResultMessage() {
      return resultMessage;
   }

   public void setResultMessage(String resultMessage) {
      this.resultMessage = resultMessage;
   }

}
