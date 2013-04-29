package com.manpower.translations.model;

import java.util.List;

import com.manpower.translations.hbn.shared.dao.DBProperty;

public interface PropertyFile {

   public abstract List<PropertyLine> getPropertyLines();

   public abstract void addNewLocale(String localeName);

   public abstract List<DBProperty> getPropertiesToUpdate();

   public abstract void addNewPropertyLine(final PropertyLine line);

   public abstract LocatedValue findByKeyAndLocale(String key, String locale);

   public abstract List<String> getAllKeys();

   public abstract PropertyLine getLine(final String key);

   public abstract List<String> getLanguageList();

   public abstract String getCountryCode();

   public void setCountryCode(final String countryCode);

   public abstract String getFileName();

   public void setFileName(final String fileName);

   public void setDefaultLang(String defaultLang);

   public String getDefaultLang();

   public abstract void updateValue(String propertyKey, String lang, String val);
}
