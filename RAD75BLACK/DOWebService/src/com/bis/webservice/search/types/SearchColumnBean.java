/**
 * SearchColumnBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bis.webservice.search.types;

public class SearchColumnBean  implements java.io.Serializable {
    private short display;

    private java.lang.String language;

    private java.lang.String referencePath;

    private short sort;

    public SearchColumnBean() {
    }

    public SearchColumnBean(
           short display,
           java.lang.String language,
           java.lang.String referencePath,
           short sort) {
           this.display = display;
           this.language = language;
           this.referencePath = referencePath;
           this.sort = sort;
    }


    /**
     * Gets the display value for this SearchColumnBean.
     * 
     * @return display
     */
    public short getDisplay() {
        return display;
    }


    /**
     * Sets the display value for this SearchColumnBean.
     * 
     * @param display
     */
    public void setDisplay(short display) {
        this.display = display;
    }


    /**
     * Gets the language value for this SearchColumnBean.
     * 
     * @return language
     */
    public java.lang.String getLanguage() {
        return language;
    }


    /**
     * Sets the language value for this SearchColumnBean.
     * 
     * @param language
     */
    public void setLanguage(java.lang.String language) {
        this.language = language;
    }


    /**
     * Gets the referencePath value for this SearchColumnBean.
     * 
     * @return referencePath
     */
    public java.lang.String getReferencePath() {
        return referencePath;
    }


    /**
     * Sets the referencePath value for this SearchColumnBean.
     * 
     * @param referencePath
     */
    public void setReferencePath(java.lang.String referencePath) {
        this.referencePath = referencePath;
    }


    /**
     * Gets the sort value for this SearchColumnBean.
     * 
     * @return sort
     */
    public short getSort() {
        return sort;
    }


    /**
     * Sets the sort value for this SearchColumnBean.
     * 
     * @param sort
     */
    public void setSort(short sort) {
        this.sort = sort;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SearchColumnBean)) return false;
        SearchColumnBean other = (SearchColumnBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.display == other.getDisplay() &&
            ((this.language==null && other.getLanguage()==null) || 
             (this.language!=null &&
              this.language.equals(other.getLanguage()))) &&
            ((this.referencePath==null && other.getReferencePath()==null) || 
             (this.referencePath!=null &&
              this.referencePath.equals(other.getReferencePath()))) &&
            this.sort == other.getSort();
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
        _hashCode += getDisplay();
        if (getLanguage() != null) {
            _hashCode += getLanguage().hashCode();
        }
        if (getReferencePath() != null) {
            _hashCode += getReferencePath().hashCode();
        }
        _hashCode += getSort();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SearchColumnBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "SearchColumnBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("display");
        elemField.setXmlName(new javax.xml.namespace.QName("", "display"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("language");
        elemField.setXmlName(new javax.xml.namespace.QName("", "language"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referencePath");
        elemField.setXmlName(new javax.xml.namespace.QName("", "referencePath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sort");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sort"));
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
