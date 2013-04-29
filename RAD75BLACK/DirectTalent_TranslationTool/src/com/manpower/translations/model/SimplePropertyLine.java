package com.manpower.translations.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.Validate;

import com.manpower.translations.hbn.shared.dao.DBProperty;

/**
 * Each PropertyLine is related to a propertyKey and to several located Values,
 * the located values contains all the languages and translations associated.
 */
public class SimplePropertyLine implements PropertyLine {

    /** <code>locatedValues</code> */
    private List<LocatedValue> locatedValues = new ArrayList<LocatedValue>();

    private final String propertyKey;

    /**
     * Creates the SimplePropertyLine.
     */
    public SimplePropertyLine(final String propertyKey) {
        Validate.notEmpty(propertyKey);
        this.propertyKey = propertyKey;
    }

    /** @see PropertyLine#addNewLocale(String) */
    public final void addNewLocale(final String localeName) {
        this.locatedValues.add(new SimpleLocatedValue(localeName));

    }

    /** @see PropertyLine#getLocatedValues() */
    public final List<LocatedValue> getLocatedValues() {
        return locatedValues;
    }

    public final void setLocatedValues(final List<LocatedValue> locatedValues){
    	this.locatedValues =  locatedValues;
    }

    /** @see PropertyLine#getPropertyKey() */
    public final String getPropertyKey() {
        return propertyKey;
    }

    public final void addLocale(final LocatedValue val) {
        Validate.notNull(val);
        for (LocatedValue locatedValue : locatedValues) {
           if (locatedValue.getLocale().equalsIgnoreCase(val.getLocale()) && locatedValue.getValue().equalsIgnoreCase(val.getValue())){
              locatedValues.remove(locatedValue);
              break;
           }
        }
        this.locatedValues.add(val);

    }

    /** @see PropertyFile#getPropertiesToUpdate() */
    public final List<DBProperty> getPropertiesToUpdate(
            String countryCode, String fileName, String defaultLang) {
        final List<DBProperty> props = new ArrayList<DBProperty>();
        for (final LocatedValue locValue : this.locatedValues) {
            final DBProperty prop =
               locValue.getProperyToUpdate(countryCode, fileName, propertyKey, defaultLang);
            if (prop != null) {
                props.add(prop);
            }
        }
        return props;
    }

    /** @see PropertyLine#getLocatedValue(String) */
    public final LocatedValue getLocatedValue(final String locate) {
        Validate.notEmpty(locate);
        return (LocatedValue) CollectionUtils.find(getLocatedValues(),
                new Predicate() {

                    public boolean evaluate(final Object arg0) {
                        final LocatedValue val = (LocatedValue) arg0;
                        return val.getLocale().equals(locate);
                    }
                });
    }

    /** @see PropertyLine#isModified() */
    public final boolean isModified() {
        for (final LocatedValue val : locatedValues) {
            if (val.isChanged()) {
                return true;
            }
        }
        return false;
    }

    /** @see PropertyLine#getEscapedPropertyKey() */
    public final String getEscapedPropertyKey() {
        return propertyKey.replace(".", "-");
    }

}
