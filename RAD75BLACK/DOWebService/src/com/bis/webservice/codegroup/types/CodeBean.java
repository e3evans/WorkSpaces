/**
 * CodeBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bis.webservice.codegroup.types;

public class CodeBean  implements java.io.Serializable {
    private long id;

    private java.lang.String localizedDesc;

    private java.lang.String localizedName;

    private java.lang.String name;

    private long parentId;

    public CodeBean() {
    }

    public CodeBean(
           long id,
           java.lang.String localizedDesc,
           java.lang.String localizedName,
           java.lang.String name,
           long parentId) {
           this.id = id;
           this.localizedDesc = localizedDesc;
           this.localizedName = localizedName;
           this.name = name;
           this.parentId = parentId;
    }


    /**
     * Gets the id value for this CodeBean.
     * 
     * @return id
     */
    public long getId() {
        return id;
    }


    /**
     * Sets the id value for this CodeBean.
     * 
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * Gets the localizedDesc value for this CodeBean.
     * 
     * @return localizedDesc
     */
    public java.lang.String getLocalizedDesc() {
        return localizedDesc;
    }


    /**
     * Sets the localizedDesc value for this CodeBean.
     * 
     * @param localizedDesc
     */
    public void setLocalizedDesc(java.lang.String localizedDesc) {
        this.localizedDesc = localizedDesc;
    }


    /**
     * Gets the localizedName value for this CodeBean.
     * 
     * @return localizedName
     */
    public java.lang.String getLocalizedName() {
        return localizedName;
    }


    /**
     * Sets the localizedName value for this CodeBean.
     * 
     * @param localizedName
     */
    public void setLocalizedName(java.lang.String localizedName) {
        this.localizedName = localizedName;
    }


    /**
     * Gets the name value for this CodeBean.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this CodeBean.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the parentId value for this CodeBean.
     * 
     * @return parentId
     */
    public long getParentId() {
        return parentId;
    }


    /**
     * Sets the parentId value for this CodeBean.
     * 
     * @param parentId
     */
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CodeBean)) return false;
        CodeBean other = (CodeBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.id == other.getId() &&
            ((this.localizedDesc==null && other.getLocalizedDesc()==null) || 
             (this.localizedDesc!=null &&
              this.localizedDesc.equals(other.getLocalizedDesc()))) &&
            ((this.localizedName==null && other.getLocalizedName()==null) || 
             (this.localizedName!=null &&
              this.localizedName.equals(other.getLocalizedName()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            this.parentId == other.getParentId();
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
        _hashCode += new Long(getId()).hashCode();
        if (getLocalizedDesc() != null) {
            _hashCode += getLocalizedDesc().hashCode();
        }
        if (getLocalizedName() != null) {
            _hashCode += getLocalizedName().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        _hashCode += new Long(getParentId()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CodeBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://codegroup.webservice.bis.com/types", "CodeBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localizedDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "localizedDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localizedName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "localizedName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parentId"));
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
