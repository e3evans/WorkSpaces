package com.manpower.translations.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.manpower.translations.hbn.shared.dao.DBProperty;

public class Propertylines implements java.io.Serializable, DBProperty {

   private static final long serialVersionUID = 1L;
   
   private BigDecimal id;
   
   private String countryCode;
   
   private String createdBy;
   
   private Date createdOn;
   
   private String propertyKey;
   
   private String lang;
   
   private String propertyfileName;
   
   private String translationString;
   
   private String updatedBy;
   
   private Date updatedOn;
   
   private String defaultLang;
   
   public Propertylines() {
	  super();
   }

   public Propertylines(BigDecimal id, String countryCode, String propertyKey, String lang,
         String propertyfileName, String translationString, Date updatedOn, Date createdOn, String isDefaultLang) {
      super();
      this.id = id;
      this.countryCode = countryCode;
      this.propertyKey = propertyKey;
      this.lang = lang;
      this.propertyfileName = propertyfileName;
      this.translationString = translationString;
      this.updatedOn = updatedOn;
      this.createdOn = createdOn;
      this.defaultLang = isDefaultLang;
   }

   public Propertylines(BigDecimal id, String countryCode, String createdBy, Date createdOn,
         String propertyKey, String lang, String propertyfileName, String translationString,
         String updatedBy, Date updatedOn, String isDefaultLang) {
      super();
      this.id = id;
      this.countryCode = countryCode;
      this.createdBy = createdBy;
      this.createdOn = createdOn;
      this.propertyKey = propertyKey;
      this.lang = lang;
      this.propertyfileName = propertyfileName;
      this.translationString = translationString;
      this.updatedBy = updatedBy;
      this.updatedOn = updatedOn;
      this.defaultLang = isDefaultLang;
   }

   public Propertylines(String countryCode, String propertyKey, String lang,
         String propertyfileName, String translationString, Date updatedOn, Date createdOn, String isDefaultLang) {
      super();
      this.countryCode = countryCode;
      this.propertyKey = propertyKey;
      this.lang = lang;
      this.propertyfileName = propertyfileName;
      this.translationString = translationString;
      this.updatedOn = updatedOn;
      this.createdOn = createdOn;
      this.defaultLang = isDefaultLang;
   }

   public BigDecimal getId() {
      return this.id;
   }

   public void setId(BigDecimal id) {
      this.id = id;
   }

   public String getCountryCode() {
      return this.countryCode;
   }

   public void setCountryCode(String countryCode) {
      this.countryCode = countryCode;
   }

   public String getCreatedBy() {
      return this.createdBy;
   }

   public void setCreatedBy(String createdBy) {
      this.createdBy = createdBy;
   }

   public Date getCreatedOn() {
      return this.createdOn;
   }

   public void setCreatedOn(Date createdOn) {
      this.createdOn = createdOn;
   }

   public String getPropertyKey() {
      return this.propertyKey;
   }

   public void setPropertyKey(String propertyKey) {
      this.propertyKey = propertyKey;
   }

   public String getLang() {
      return this.lang;
   }

   public void setLang(String lang) {
      this.lang = lang;
   }

   public String getPropertyfileName() {
      return this.propertyfileName;
   }

   public void setPropertyfileName(String propertyfileName) {
      this.propertyfileName = propertyfileName;
   }

   public String getTranslationString() {
      return this.translationString;
   }

   public void setTranslationString(String translationString) {
      this.translationString = translationString;
   }

   public String getUpdatedBy() {
      return this.updatedBy;
   }

   public void setUpdatedBy(String updatedBy) {
      this.updatedBy = updatedBy;
   }

   public Date getUpdatedOn() {
      return this.updatedOn;
   }

   public void setUpdatedOn(Date updatedOn) {
      this.updatedOn = updatedOn;
   }

   public String getDefaultLang() {
      return this.defaultLang;
   }

   public void setDefaultLang(String defaultLang) {
      this.defaultLang = defaultLang;
   }

}