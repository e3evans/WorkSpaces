package com.manpower.translations.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.manpower.translations.beans.Propertylines;
import com.manpower.translations.hbn.shared.dao.DBProperty;

/**
 * The located values contains the following information language 
 * string, created on date, update on date, translation string, 
 * id and a isChanged attribute that indicate if the translation 
 * string has changes 
 */
public class SimpleLocatedValue implements LocatedValue {
    private DBProperty simpleDBProperty;

    private final String locale;

    private Date createdOn;
    
    private Date updatedOn;
    
    private String value;

    private boolean changed = false;

    private transient String id;

    /**
     * Creates the SimpleLocatedValue.
     */
    public SimpleLocatedValue(final DBProperty simpleDBProperty) {
        this.simpleDBProperty = simpleDBProperty;
        this.locale = simpleDBProperty.getLang();
        this.value = simpleDBProperty.getTranslationString();
    }

    /**
     * Creates the SimpleLocatedValue.
     */
    public SimpleLocatedValue(final String locale) {
        this.locale = locale;
    }

    /** @see LocatedValue#getLocale() */
    public final String getLocale() {
        return this.locale;
    }

    /** @see LocatedValue#getValue() */
    public final String getValue() {
        return value;
    }

    /** @see LocatedValue#setValue(String) */
    public final void setValue(final String value) {
        if (!value.equals(this.value)) {
            changed = true;
        }
        this.value = value;

    }

    /** @see PropertyFile#getPropertiesToUpdate() */
    public final DBProperty getProperyToUpdate(String countryCode, String filename, 
    		String key, String defaultLang) {
       String isDefaultLang = "false";
        if (this.simpleDBProperty == null) {
           if (locale.equals(defaultLang)){
              isDefaultLang = "true";
           }
           if (id != null) {
              return new Propertylines( new BigDecimal(id), countryCode,
                    key, locale, filename, value, updatedOn, createdOn, isDefaultLang);
           } else {
              return new Propertylines(countryCode, key, locale, filename, value, updatedOn, createdOn, isDefaultLang);
           }

        }
        if (!simpleDBProperty.getTranslationString().equals(value)) {
            simpleDBProperty.setTranslationString(value);
            return simpleDBProperty;
        }
        return null;

    }

    /**
     * Returns the changed.
     *
     * @return <code>boolean</code> with the changed.
     */
    public final boolean isChanged() {
        return changed;
    }

   public void setId(String id) {
     this.id = id;
   }

   public String getId() {
      return id;
   }


   /**
    * Check if to SimpleLocatedValue are equals in base the value of its
    * attribute locale and value.
    *
    */
   @Override
   public boolean equals(Object o) {

	   boolean isValueEqual = false;
	   boolean isLocaleEqual = false;

	   if(o instanceof SimpleLocatedValue){
		   if(!StringUtils.isEmpty(this.value) && !StringUtils.isEmpty(this.locale)){
			   SimpleLocatedValue parameter = (SimpleLocatedValue) o;
			   isValueEqual = this.value.equalsIgnoreCase(parameter.getValue());
			   isLocaleEqual = this.locale.equalsIgnoreCase(parameter.getLocale());
		   }
	   }

	   return (isValueEqual && isLocaleEqual);
   }

   public DBProperty getSimpleDBProperty() {
      return this.simpleDBProperty;
   }

   public void setChanged(boolean changed) {
      this.changed=changed;
   }

   public void setSimpleDBProperty(DBProperty simpleDBProperty) {
      this.simpleDBProperty = simpleDBProperty;
   }

	public Date getCreatedOn() {
		return createdOn;
	}
	
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public Date getUpdatedOn() {
		return updatedOn;
	}
	
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
}
