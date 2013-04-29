package com.manpower.translations.model;

import java.util.List;

import com.manpower.translations.hbn.shared.dao.DBProperty;

public interface PropertyLine {

   public abstract String getPropertyKey();

   public abstract String getEscapedPropertyKey();

   public abstract List<LocatedValue> getLocatedValues();

   public void setLocatedValues(final List<LocatedValue> locatedValues);

   public abstract void addNewLocale(String localeName);

   public abstract void addLocale(LocatedValue val);

   public abstract List<DBProperty> getPropertiesToUpdate(String countryCode, String fileName, String defaultLang);

   public abstract LocatedValue getLocatedValue(String locate);

   public abstract boolean isModified();
}
