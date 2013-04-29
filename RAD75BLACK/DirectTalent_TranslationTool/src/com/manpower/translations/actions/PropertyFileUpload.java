package com.manpower.translations.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manpower.translations.service.PropertyFileManager;
import com.manpower.translations.service.PropertyFilesDBService;
import com.manpower.translations.utils.ApplicationUtils;

public class PropertyFileUpload extends ExtendedActionSupport {
	private static final long serialVersionUID = -772394870513046056L;
	   private static final Logger _log = LoggerFactory.getLogger(PropertyFileUpload.class);

	private File upload;// The actual file
	private String uploadContentType; // The content type of the file
	private String uploadFileName; // The uploaded file name
	private String fileCaption;// The caption of the file entered by user
	private PropertyFileManager propertyFileManager;
	private String resultMessage; // message to be displayed when an error happen
	private PropertyFilesDBService propertyFilesDBService;

	private ResourceBundle translationToolResourceBundle;

	public PropertyFileUpload(){
	   if (translationToolResourceBundle != null){
	      ApplicationUtils.clearResourceBundle();
	   }
      translationToolResourceBundle = ResourceBundle.getBundle("translation-tool");
	}

	@Override
	protected final String get() {
		String user = req.getParameter("user");
		String pass = req.getParameter("pass");
		String rbUserName = translationToolResourceBundle.getString("user.name");
	   String rbPassword = translationToolResourceBundle.getString("user.password");

		if (rbUserName.equalsIgnoreCase(user) && rbPassword.equalsIgnoreCase(pass)){
			return INPUT;
		}
		resultMessage =  "invalid user name and/or password";
		return ERROR;
	}

	@Override
	protected final String post() {
		resultMessage = "";
		String returnValue = SUCCESS;
		try {
			Map<String, String> fileMap = verifyTemplateFileName();
			if (new Boolean(fileMap.get(PropertyFileUploadConstants.ERROR))) {
				return ERROR;
			}

			// if a template does not exists add a new template
			File theTemplateFile = new File(fileMap.get(PropertyFileUploadConstants.TEMPLATE_NAME_FULL_PATH));
			if (!theTemplateFile.exists()){
				//adds the template
				FileUtils.copyFile(upload, theTemplateFile);
			}

			Properties instanceProperties = new Properties();
			instanceProperties.load(new FileInputStream(upload));
			if (fileMap.get(PropertyFileUploadConstants.COUNTRY_CODE) != null
			      && fileMap.get(PropertyFileUploadConstants.LANG) != null) {
				propertyFilesDBService.importPropertiesValuestoDB(
						fileMap.get(PropertyFileUploadConstants.TEMPLATE_NAME_WITH_EXTENSION),
						fileMap.get(PropertyFileUploadConstants.COUNTRY_CODE),
						fileMap.get(PropertyFileUploadConstants.LANG), instanceProperties);
			}
			//importPropertiesValues(upload, theFile);



		} catch (Exception e) {
			addActionError(e.getMessage());
		}
		return returnValue;
	}

	/**
	 * Overwrites values but not keys
	 * @param srcFile
	 * @param destFile
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void importPropertiesValues(File srcFile, File destFile) throws FileNotFoundException, IOException{
		Properties srcProp = new Properties();
		srcProp.load(new FileInputStream(srcFile));
		Properties destProp = new Properties();
		destProp.load(new FileInputStream(destFile));
		for (Object oKey : srcProp.keySet()) {
			String key = (String)oKey;
			if (destProp.get(key) != null){
				destProp.setProperty(key, srcProp.getProperty(key));
			} else {
				if (StringUtils.isEmpty(resultMessage)){
					resultMessage += "the following keys are missing from the original file and \\n" +
							"won't be added, the rest of the keys will be updated";
				}
				resultMessage += "\\n" + key;
			}
		}
		destProp.store(new FileOutputStream(destFile), null);
	}

	/**
	 * Verifies if file name is correct to be processed
	 * @return
	 */
	private Map<String, String> verifyTemplateFileName() {
		Map<String, String> fileMap = new HashMap<String, String>();
		fileMap.put(PropertyFileUploadConstants.FILE_NAME, "");
		fileMap.put(PropertyFileUploadConstants.ERROR, "false");
		if (!uploadFileName.toLowerCase().endsWith(".properties") || uploadFileName.length() < 4 ) {
			resultMessage = "please select a valid properties file to proceed";
			fileMap.put(PropertyFileUploadConstants.ERROR, "true");
			return fileMap;
		}
		String fNameNoExtension = uploadFileName.split("\\.")[0];
		fileMap.put(PropertyFileUploadConstants.FILENAME_WITHOUT_EXTENSION, fNameNoExtension);

		if (!(fNameNoExtension.charAt(3) == '_')){
			resultMessage = fNameNoExtension +
				" is an invalid file name format, the valid file \\n" +
				"names should be in one of the following formats \\n" +
				"XXX_<templateName>.properties \\n" +
				"XXX_<templateName>_XX.properties" ;
			fileMap.put(PropertyFileUploadConstants.ERROR, "true");
         return fileMap;
		}

		fileMap.put(PropertyFileUploadConstants.COUNTRY_CODE, fNameNoExtension.substring(0, 3));

		String fNameNoCCode = fNameNoExtension.substring(4, fNameNoExtension.length());
		fileMap.put(PropertyFileUploadConstants.FILENAME_WITHOUT_COUNTRYCODE, fNameNoCCode);

		// remove language trail
		if (fNameNoCCode.length() > 3 && fNameNoCCode.charAt(fNameNoCCode.length() - 3) == '_') {
			fileMap.put(PropertyFileUploadConstants.LANG,
			      fNameNoCCode.substring(fNameNoCCode.length() - 2, fNameNoCCode.length()));
			fileMap.put(PropertyFileUploadConstants.TEMPLATE_NAME, fNameNoCCode.substring(0, fNameNoCCode.length()-3 ));
			fileMap.put(PropertyFileUploadConstants.TEMPLATE_NAME_WITH_EXTENSION,
			      fileMap.get(PropertyFileUploadConstants.TEMPLATE_NAME) + ".properties");
		}else{
			fileMap.put(PropertyFileUploadConstants.TEMPLATE_NAME_WITH_EXTENSION,
			      fileMap.get(PropertyFileUploadConstants.FILENAME_WITHOUT_COUNTRYCODE) + ".properties");
		}


		String relativeTemplatePath =
		   translationToolResourceBundle.getString("com.manpower.translationtool.templates.location");
		ServletContext sc = getServletRequest().getSession().getServletContext();

		fileMap.put(PropertyFileUploadConstants.TEMPLATE_NAME_FULL_PATH, sc.getRealPath("/") + relativeTemplatePath +
		      "/" + fileMap.get(PropertyFileUploadConstants.TEMPLATE_NAME_WITH_EXTENSION));
		_log.debug("remote file name := " + fileMap.get(PropertyFileUploadConstants.TEMPLATE_NAME_FULL_PATH));

		return fileMap;
	}

	public String getFileCaption() {
		return fileCaption;
	}

	public void setFileCaption(String fileCaption) {
		this.fileCaption = fileCaption;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * Getter for the disk Property File Manager utilities.
	 *
	 * @return PropertyFileManager.
	 */
	public PropertyFileManager getPropertyFileManager() {
		return propertyFileManager;
	}

	/**
	 * Setter for the disk Property File Manager utilities.
	 *
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

   public String getResultMessage() {
      return resultMessage;
   }

   public void setResultMessage(String resultMessage) {
      this.resultMessage = resultMessage;
   }

}