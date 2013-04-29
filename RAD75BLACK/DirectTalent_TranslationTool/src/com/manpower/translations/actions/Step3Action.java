package com.manpower.translations.actions;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.manpower.translations.model.PropertyFile;
import com.manpower.translations.utils.ApplicationUtils;

/**
 * This class just display the information and the available options,
 * store to database, or store to db & deploy to servers.
 */
public class Step3Action extends ExtendedActionSupport {
    private static final long serialVersionUID = -5296159264706829576L;

    private String selectedCountryCode;
    private String propFileName;
    private PropertyFile file;
    private String languages;
    private String selectedSiteId;
    private List<String> servers;

    /**
     *  This method is not required Step4Action post method is called instead.
     */
    @Override
    protected final String post() {
    	return ERROR;
    }

    public List<String> getServers() {
        return servers;
    }

    /**
     * Process display / redisplay of the page
     * @see ExtendedActionSupport#get()
     * Gets the file  model information from the request session into
     * the class and set the server names.
     *
     */
    @Override
    protected final String get() {
      servers = loadServersArray();
    	file = (PropertyFile) req.getSession().getAttribute("file");
    	return SUCCESS;
    }

    /**
     * Load server list from translation tool resource bundle
     */
    private List<String> loadServersArray(){
       ApplicationUtils.clearResourceBundle();
       ResourceBundle translationToolResourceBundle = ResourceBundle.getBundle("translation-tool");
       String serversCommaDelimited = translationToolResourceBundle.getString("com.manpower.translationtool.deployservers");
       return Arrays.asList(serversCommaDelimited.split(","));
    }

    public final void setSelectedCountryCode(final String selectedCountryCode) {
        this.selectedCountryCode = selectedCountryCode;
    }

    public final String getSelectedCountryCode() {
        return selectedCountryCode;
    }

    public final void setPropFile(final String propFile) {
        this.propFileName = propFile;
    }

    public final String getPropFile() {
        return propFileName;
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

}
