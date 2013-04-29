/**
 * CodeGroupBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bis.webservice.codegroup.types;

public class CodeGroupBean  implements java.io.Serializable {
    private com.bis.webservice.codegroup.types.LocalName[] allDescriptions;

    private com.bis.webservice.codegroup.types.CodeBean[] codes;

    private java.lang.String groupName;

    private long id;

    public CodeGroupBean() {
    }

    public CodeGroupBean(
           com.bis.webservice.codegroup.types.LocalName[] allDescriptions,
           com.bis.webservice.codegroup.types.CodeBean[] codes,
           java.lang.String groupName,
           long id) {
           this.allDescriptions = allDescriptions;
           this.codes = codes;
           this.groupName = groupName;
           this.id = id;
    }


    /**
     * Gets the allDescriptions value for this CodeGroupBean.
     * 
     * @return allDescriptions
     */
    public com.bis.webservice.codegroup.types.LocalName[] getAllDescriptions() {
        return allDescriptions;
    }


    /**
     * Sets the allDescriptions value for this CodeGroupBean.
     * 
     * @param allDescriptions
     */
    public void setAllDescriptions(com.bis.webservice.codegroup.types.LocalName[] allDescriptions) {
        this.allDescriptions = allDescriptions;
    }


    /**
     * Gets the codes value for this CodeGroupBean.
     * 
     * @return codes
     */
    public com.bis.webservice.codegroup.types.CodeBean[] getCodes() {
        return codes;
    }


    /**
     * Sets the codes value for this CodeGroupBean.
     * 
     * @param codes
     */
    public void setCodes(com.bis.webservice.codegroup.types.CodeBean[] codes) {
        this.codes = codes;
    }


    /**
     * Gets the groupName value for this CodeGroupBean.
     * 
     * @return groupName
     */
    public java.lang.String getGroupName() {
        return groupName;
    }


    /**
     * Sets the groupName value for this CodeGroupBean.
     * 
     * @param groupName
     */
    public void setGroupName(java.lang.String groupName) {
        this.groupName = groupName;
    }


    /**
     * Gets the id value for this CodeGroupBean.
     * 
     * @return id
     */
    public long getId() {
        return id;
    }


    /**
     * Sets the id value for this CodeGroupBean.
     * 
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CodeGroupBean)) return false;
        CodeGroupBean other = (CodeGroupBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.allDescriptions==null && other.getAllDescriptions()==null) || 
             (this.allDescriptions!=null &&
              java.util.Arrays.equals(this.allDescriptions, other.getAllDescriptions()))) &&
            ((this.codes==null && other.getCodes()==null) || 
             (this.codes!=null &&
              java.util.Arrays.equals(this.codes, other.getCodes()))) &&
            ((this.groupName==null && other.getGroupName()==null) || 
             (this.groupName!=null &&
              this.groupName.equals(other.getGroupName()))) &&
            this.id == other.getId();
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
        if (getAllDescriptions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAllDescriptions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAllDescriptions(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCodes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCodes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCodes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getGroupName() != null) {
            _hashCode += getGroupName().hashCode();
        }
        _hashCode += new Long(getId()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CodeGroupBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://codegroup.webservice.bis.com/types", "CodeGroupBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allDescriptions");
        elemField.setXmlName(new javax.xml.namespace.QName("", "allDescriptions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://codegroup.webservice.bis.com/types", "LocalName"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://codegroup.webservice.bis.com/types", "CodeBean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groupName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "groupName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
