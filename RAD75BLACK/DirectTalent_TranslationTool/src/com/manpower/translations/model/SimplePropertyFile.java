package com.manpower.translations.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.Validate;

import com.manpower.translations.hbn.shared.dao.DBProperty;

/**
 * This class will contain all the data of the property model:
 * List of PropertyLine, countryCode, fileName and defaultLang.
 */
public class SimplePropertyFile implements PropertyFile {

    public SimplePropertyFile() {
      super();
   }

   private final List<PropertyLine> propertyLines = new ArrayList<PropertyLine>();

    private String countryCode;

    private String fileName;

    private String defaultLang;

   /** @see PropertyFile#addNewLocale(String) */
    public final void addNewLocale(final String localeName) {
        for (final PropertyLine propLine : propertyLines) {
            propLine.addNewLocale(localeName);
        }

    }

    /** @see PropertyFile#addNewPropertyLine(PropertyLine) */
    public final void addNewPropertyLine(final PropertyLine line) {
        Validate.notNull(line);
        propertyLines.add(line);
    }

    /** @see PropertyFile#getPropertyLines() */
    public final List<PropertyLine> getPropertyLines() {
        return Collections.unmodifiableList(propertyLines);
    }

	/**
	 * Creates the SimplePropertyFile.
	 * @param countryCode
	 * @param fileName
	 */
    public SimplePropertyFile(final String countryCode, final String fileName) {
        Validate.notEmpty(countryCode);
        Validate.notEmpty(fileName);
        this.countryCode = countryCode;
        this.fileName = fileName;
    }

    /** @see PropertyFile#getPropertiesToUpdate() */
    public final List<DBProperty> getPropertiesToUpdate() {
        final List<DBProperty> props = new ArrayList<DBProperty>();
        for (final PropertyLine line : propertyLines) {
            props.addAll(line.getPropertiesToUpdate(countryCode, fileName, defaultLang));
        }
        return props;
    }

    /** @see PropertyFile#getLine(String) */
    public final PropertyLine getLine(final String key) {
        Validate.notEmpty(key);
        return (PropertyLine) CollectionUtils.find(propertyLines,
                new Predicate() {
                    public boolean evaluate(final Object arg0) {
                        final PropertyLine line = (PropertyLine) arg0;
                        return line.getPropertyKey().equals(key);
                    }
                });
    }

    /** @see PropertyFile#findByKeyAndLocale(String, String) */
    public final LocatedValue findByKeyAndLocale(final String key,
            final String locale) {
        final PropertyLine line = (PropertyLine) CollectionUtils.find(
                propertyLines, new Predicate() {
                    public boolean evaluate(final Object arg0) {
                        final PropertyLine line = (PropertyLine) arg0;
                        return line.getPropertyKey().equals(key) &&
                           CollectionUtils.find(line.getLocatedValues(), new Predicate() {
                              public boolean evaluate(Object arg0) {
                                 final LocatedValue located = (LocatedValue) arg0;
                                 return located.getLocale().equals(locale);
                              }
                           }) != null;
                    }
                });
        if (line == null) {
            return null;
        }
        return (LocatedValue) CollectionUtils.find(line.getLocatedValues(),
                new Predicate() {
                    public boolean evaluate(final Object arg0) {
                        final LocatedValue locVal = (LocatedValue) arg0;
                        return locVal.getLocale().equals(locale);
                    }
                });
    }

    /** @see PropertyFile#getAllKeys() */
    public final List<String> getAllKeys() {
        final List<String> keys = new ArrayList<String>();
        for (final PropertyLine line : propertyLines) {
            keys.add(line.getPropertyKey());
        }
        return keys;
    }

    public String getCountryCode() {
    	if (countryCode != null && countryCode.length() >= 3){
    		return countryCode.substring(0, 3);
    	} else {
    		return countryCode;
    	}
    }

    public String getFileName() {
      return fileName;
    }

    public void setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
     }

     public void setFileName(final String fileName) {
       this.fileName = fileName;
     }

    public List<String> getLanguageList(){
       Set<String> differentLocales = new HashSet<String>();
       for (PropertyLine propertyLine: getPropertyLines()) {
          for (LocatedValue locatedValue: propertyLine.getLocatedValues()) {
             differentLocales.add(locatedValue.getLocale());
          }
       }
       return new ArrayList<String>(differentLocales);
    }

   public String getDefaultLang() {
      return defaultLang;
   }

   public void setDefaultLang(String defaultLang) {
      this.defaultLang = defaultLang;
   }

   public void updateValue(String propertyKey, String lang, String val) {
      for (PropertyLine propertyLine : propertyLines) {
         if (propertyLine.getPropertyKey().equals(propertyKey)){
            for (LocatedValue located : propertyLine.getLocatedValues()) {
               if (located.getLocale().equals(lang)){
                  located.setValue(val);
                  break;
               }
            }
         }
      }
   }

}
