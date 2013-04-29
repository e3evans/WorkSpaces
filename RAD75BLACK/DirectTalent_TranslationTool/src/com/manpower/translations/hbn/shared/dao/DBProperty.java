package com.manpower.translations.hbn.shared.dao;

import java.math.BigDecimal;
import java.util.Date;

public interface DBProperty {

   public abstract BigDecimal getId();

   public abstract void setId(BigDecimal id);

   public abstract String getCountryCode();

   public abstract void setCountryCode(String countryCode);

   public abstract String getCreatedBy();

   public abstract void setCreatedBy(String createdBy);

   public abstract Date getCreatedOn();

   public abstract void setCreatedOn(Date createdOn);

   public abstract String getPropertyKey();

   public abstract void setPropertyKey(String propertyKey);

   public abstract String getLang();

   public abstract void setLang(String lang);

   public abstract String getPropertyfileName();

   public abstract void setPropertyfileName(String propertyfileName);

   public abstract String getTranslationString();

   public abstract void setTranslationString(String translationString);

   public abstract String getUpdatedBy();

   public abstract void setUpdatedBy(String updatedBy);

   public abstract Date getUpdatedOn();

   public abstract void setUpdatedOn(Date updatedOn);

   public String getDefaultLang();

   public void setDefaultLang(String defaultLang);

}