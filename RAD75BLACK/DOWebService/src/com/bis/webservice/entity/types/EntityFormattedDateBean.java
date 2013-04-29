/**
 * EntityFormattedDateBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bis.webservice.entity.types;

public class EntityFormattedDateBean  implements java.io.Serializable {
    private boolean XSDateTime;

    private short dateFormat;

    private java.lang.String localeName;

    private short timeFormat;

    public EntityFormattedDateBean() {
    }

    public EntityFormattedDateBean(
           boolean XSDateTime,
           short dateFormat,
           java.lang.String localeName,
           short timeFormat) {
           this.XSDateTime = XSDateTime;
           this.dateFormat = dateFormat;
           this.localeName = localeName;
           this.timeFormat = timeFormat;
    }


    /**
     * Gets the XSDateTime value for this EntityFormattedDateBean.
     * 
     * @return XSDateTime
     */
    public boolean isXSDateTime() {
        return XSDateTime;
    }


    /**
     * Sets the XSDateTime value for this EntityFormattedDateBean.
     * 
     * @param XSDateTime
     */
    public void setXSDateTime(boolean XSDateTime) {
        this.XSDateTime = XSDateTime;
    }


    /**
     * Gets the dateFormat value for this EntityFormattedDateBean.
     * 
     * @return dateFormat
     */
    public short getDateFormat() {
        return dateFormat;
    }


    /**
     * Sets the dateFormat value for this EntityFormattedDateBean.
     * 
     * @param dateFormat
     */
    public void setDateFormat(short dateFormat) {
        this.dateFormat = dateFormat;
    }


    /**
     * Gets the localeName value for this EntityFormattedDateBean.
     * 
     * @return localeName
     */
    public java.lang.String getLocaleName() {
        return localeName;
    }


    /**
     * Sets the localeName value for this EntityFormattedDateBean.
     * 
     * @param localeName
     */
    public void setLocaleName(java.lang.String localeName) {
        this.localeName = localeName;
    }


    /**
     * Gets the timeFormat value for this EntityFormattedDateBean.
     * 
     * @return timeFormat
     */
    public short getTimeFormat() {
        return timeFormat;
    }


    /**
     * Sets the timeFormat value for this EntityFormattedDateBean.
     * 
     * @param timeFormat
     */
    public void setTimeFormat(short timeFormat) {
        this.timeFormat = timeFormat;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EntityFormattedDateBean)) return false;
        EntityFormattedDateBean other = (EntityFormattedDateBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.XSDateTime == other.isXSDateTime() &&
            this.dateFormat == other.getDateFormat() &&
            ((this.localeName==null && other.getLocaleName()==null) || 
             (this.localeName!=null &&
              this.localeName.equals(other.getLocaleName()))) &&
            this.timeFormat == other.getTimeFormat();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += (isXSDateTime() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getDateFormat();
        if (getLocaleName() != null) {
            _hashCode += getLocaleName().hashCode();
        }
        _hashCode += getTimeFormat();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EntityFormattedDateBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "EntityFormattedDateBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XSDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XSDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateFormat");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateFormat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localeName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "localeName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeFormat");
        elemField.setXmlName(new javax.xml.namespace.QName("", "timeFormat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
