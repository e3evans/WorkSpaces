package com.manpower.translations.beans;

public class Countries implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String countryCode;

    private String counrty;

    public Countries() {
    }
    
    public Countries(String countryCode, String counrty) {
	super();
	this.countryCode = countryCode;
	this.counrty = counrty;
    }

    public String getCountryCode() {
  		return countryCode;
    }

    public String getCleanCountryCode(){
    	if (countryCode != null && countryCode.length() >= 3){
    		return countryCode.substring(0, 3);
    	} else {
    		return countryCode;
    	}
    }
    
    public void setCountryCode(String countryCode) {
	this.countryCode = countryCode;
    }

    public String getCounrty() {
	return counrty;
    }

    public void setCounrty(String counrty) {
	this.counrty = counrty;
    }
}