package com.manpower.translations.model;

import java.util.Date;

import com.manpower.translations.hbn.shared.dao.DBProperty;

public interface LocatedValue {

   /** @see LocatedValue#getLocale() */
   public abstract String getLocale();

   /** @see LocatedValue#getValue() */
   public abstract String getValue();

   /** @see LocatedValue#setValue(String) */
   public abstract void setValue(final String value);

   /** @see PropertyFile#getPropertiesToUpdate() */
   public abstract DBProperty getProperyToUpdate(String countryCode, String filename, 
		   String key, String defaultLang);

   /**
    * Returns the changed.
    *
    * @return <code>boolean</code> with the changed.
    */
   public abstract boolean isChanged();

   public abstract void setId(String id);

   public abstract String getId();

   /**
    * Check if to SimpleLocatedValue are equals in base the value of its
    * attribute locale and value.
    */
   public abstract boolean equals(Object o);

   public abstract DBProperty getSimpleDBProperty();

   public abstract void setSimpleDBProperty(DBProperty simpleDBProperty);

   public abstract void setChanged(boolean changed);

   public abstract void setCreatedOn(Date createdOn);

   public abstract void setUpdatedOn(Date updatedOn);

   public abstract Date getCreatedOn();

   public abstract Date getUpdatedOn();
   
}