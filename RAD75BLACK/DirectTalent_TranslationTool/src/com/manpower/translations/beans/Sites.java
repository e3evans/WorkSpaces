package com.manpower.translations.beans;

import java.util.Date;

public class Sites implements java.io.Serializable {

   private static final long serialVersionUID = 1L;
   private Long id;
   private String sitecode;
   private String sitename;
   private String siteowner;
   private String sitefirstlang;
   private String sitesecondlang;
   private String sitethirdlang;
   private Date createdOn;
   private String createdBy;
   private Date changedOn;
   private String changedBy;
   private Date updatedon;
   private String updatedby;
   private String countrycode;
   private String sitestatus;
   private String defaultLevel;
   private String integrationflag;
   private String distanceUnit;
   private String resourcePrefix;
   private String bgFlag;
   private String sitedesc;


   public Sites() {
   }


   public Sites(Long id, String sitecode, String sitename, String siteowner, String sitefirstlang,
         String sitesecondlang, String sitethirdlang, String createdBy, Date changedOn,
         String changedBy, Date updatedon, String updatedby, String resourcePrefix, String bgFlag) {
      this.id = id;
      this.sitecode = sitecode;
      this.sitename = sitename;
      this.siteowner = siteowner;
      this.sitefirstlang = sitefirstlang;
      this.sitesecondlang = sitesecondlang;
      this.sitethirdlang = sitethirdlang;
      this.createdBy = createdBy;
      this.changedOn = changedOn;
      this.changedBy = changedBy;
      this.updatedon = updatedon;
      this.updatedby = updatedby;
      this.resourcePrefix = resourcePrefix;
      this.bgFlag = bgFlag;
   }

   public Sites(Long id, String sitecode, String sitename, String siteowner, String sitefirstlang,
         String sitesecondlang, String sitethirdlang, Date createdOn, String createdBy,
         Date changedOn, String changedBy, Date updatedon, String updatedby, String countrycode,
         String sitestatus, String defaultLevel, String integrationflag, String distanceUnit,
         String resourcePrefix, String bgFlag, String sitedesc) {
      this.id = id;
      this.sitecode = sitecode;
      this.sitename = sitename;
      this.siteowner = siteowner;
      this.sitefirstlang = sitefirstlang;
      this.sitesecondlang = sitesecondlang;
      this.sitethirdlang = sitethirdlang;
      this.createdOn = createdOn;
      this.createdBy = createdBy;
      this.changedOn = changedOn;
      this.changedBy = changedBy;
      this.updatedon = updatedon;
      this.updatedby = updatedby;
      this.countrycode = countrycode;
      this.sitestatus = sitestatus;
      this.defaultLevel = defaultLevel;
      this.integrationflag = integrationflag;
      this.distanceUnit = distanceUnit;
      this.resourcePrefix = resourcePrefix;
      this.bgFlag = bgFlag;
      this.sitedesc = sitedesc;
   }

   // Property accessors

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getSitecode() {
      return this.sitecode;
   }

   public void setSitecode(String sitecode) {
      this.sitecode = sitecode;
   }

   public String getSitename() {
      return this.sitename;
   }

   public void setSitename(String sitename) {
      this.sitename = sitename;
   }

   public String getSiteowner() {
      return this.siteowner;
   }

   public void setSiteowner(String siteowner) {
      this.siteowner = siteowner;
   }

   public String getSitefirstlang() {
      return this.sitefirstlang;
   }

   public void setSitefirstlang(String sitefirstlang) {
      this.sitefirstlang = sitefirstlang;
   }

   public String getSitesecondlang() {
      return this.sitesecondlang;
   }

   public void setSitesecondlang(String sitesecondlang) {
      this.sitesecondlang = sitesecondlang;
   }

   public String getSitethirdlang() {
      return this.sitethirdlang;
   }

   public void setSitethirdlang(String sitethirdlang) {
      this.sitethirdlang = sitethirdlang;
   }

   public Date getCreatedOn() {
      return this.createdOn;
   }

   public void setCreatedOn(Date createdOn) {
      this.createdOn = createdOn;
   }

   public String getCreatedBy() {
      return this.createdBy;
   }

   public void setCreatedBy(String createdBy) {
      this.createdBy = createdBy;
   }

   public Date getChangedOn() {
      return this.changedOn;
   }

   public void setChangedOn(Date changedOn) {
      this.changedOn = changedOn;
   }

   public String getChangedBy() {
      return this.changedBy;
   }

   public void setChangedBy(String changedBy) {
      this.changedBy = changedBy;
   }

   public Date getUpdatedon() {
      return this.updatedon;
   }

   public void setUpdatedon(Date updatedon) {
      this.updatedon = updatedon;
   }

   public String getUpdatedby() {
      return this.updatedby;
   }

   public void setUpdatedby(String updatedby) {
      this.updatedby = updatedby;
   }

   public String getCountrycode() {
      return this.countrycode;
   }

   public void setCountrycode(String countrycode) {
      this.countrycode = countrycode;
   }

   public String getSitestatus() {
      return this.sitestatus;
   }

   public void setSitestatus(String sitestatus) {
      this.sitestatus = sitestatus;
   }

   public String getDefaultLevel() {
      return this.defaultLevel;
   }

   public void setDefaultLevel(String defaultLevel) {
      this.defaultLevel = defaultLevel;
   }

   public String getIntegrationflag() {
      return this.integrationflag;
   }

   public void setIntegrationflag(String integrationflag) {
      this.integrationflag = integrationflag;
   }

   public String getDistanceUnit() {
      return this.distanceUnit;
   }

   public void setDistanceUnit(String distanceUnit) {
      this.distanceUnit = distanceUnit;
   }

   public String getResourcePrefix() {
      return this.resourcePrefix;
   }

   public void setResourcePrefix(String resourcePrefix) {
      this.resourcePrefix = resourcePrefix;
   }

   public String getBgFlag() {
      return this.bgFlag;
   }

   public void setBgFlag(String bgFlag) {
      this.bgFlag = bgFlag;
   }

   public String getSitedesc() {
      return this.sitedesc;
   }

   public void setSitedesc(String sitedesc) {
      this.sitedesc = sitedesc;
   }

}