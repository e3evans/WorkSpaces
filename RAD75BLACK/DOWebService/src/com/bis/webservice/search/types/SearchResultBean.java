/**
 * SearchResultBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bis.webservice.search.types;

public class SearchResultBean  implements java.io.Serializable {
    private long createdBy;

    private java.util.Calendar createdDate;

    private long[] entityIDs;

    private int highlight;

    private java.lang.String label;

    private long modifiedBy;

    private java.util.Calendar modifiedDate;

    private long ownerID;

    private long searchDefinitionID;

    private java.lang.String searchName;

    private long searchResultId;

    public SearchResultBean() {
    }

    public SearchResultBean(
           long createdBy,
           java.util.Calendar createdDate,
           long[] entityIDs,
           int highlight,
           java.lang.String label,
           long modifiedBy,
           java.util.Calendar modifiedDate,
           long ownerID,
           long searchDefinitionID,
           java.lang.String searchName,
           long searchResultId) {
           this.createdBy = createdBy;
           this.createdDate = createdDate;
           this.entityIDs = entityIDs;
           this.highlight = highlight;
           this.label = label;
           this.modifiedBy = modifiedBy;
           this.modifiedDate = modifiedDate;
           this.ownerID = ownerID;
           this.searchDefinitionID = searchDefinitionID;
           this.searchName = searchName;
           this.searchResultId = searchResultId;
    }


    /**
     * Gets the createdBy value for this SearchResultBean.
     * 
     * @return createdBy
     */
    public long getCreatedBy() {
        return createdBy;
    }


    /**
     * Sets the createdBy value for this SearchResultBean.
     * 
     * @param createdBy
     */
    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }


    /**
     * Gets the createdDate value for this SearchResultBean.
     * 
     * @return createdDate
     */
    public java.util.Calendar getCreatedDate() {
        return createdDate;
    }


    /**
     * Sets the createdDate value for this SearchResultBean.
     * 
     * @param createdDate
     */
    public void setCreatedDate(java.util.Calendar createdDate) {
        this.createdDate = createdDate;
    }


    /**
     * Gets the entityIDs value for this SearchResultBean.
     * 
     * @return entityIDs
     */
    public long[] getEntityIDs() {
        return entityIDs;
    }


    /**
     * Sets the entityIDs value for this SearchResultBean.
     * 
     * @param entityIDs
     */
    public void setEntityIDs(long[] entityIDs) {
        this.entityIDs = entityIDs;
    }


    /**
     * Gets the highlight value for this SearchResultBean.
     * 
     * @return highlight
     */
    public int getHighlight() {
        return highlight;
    }


    /**
     * Sets the highlight value for this SearchResultBean.
     * 
     * @param highlight
     */
    public void setHighlight(int highlight) {
        this.highlight = highlight;
    }


    /**
     * Gets the label value for this SearchResultBean.
     * 
     * @return label
     */
    public java.lang.String getLabel() {
        return label;
    }


    /**
     * Sets the label value for this SearchResultBean.
     * 
     * @param label
     */
    public void setLabel(java.lang.String label) {
        this.label = label;
    }


    /**
     * Gets the modifiedBy value for this SearchResultBean.
     * 
     * @return modifiedBy
     */
    public long getModifiedBy() {
        return modifiedBy;
    }


    /**
     * Sets the modifiedBy value for this SearchResultBean.
     * 
     * @param modifiedBy
     */
    public void setModifiedBy(long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }


    /**
     * Gets the modifiedDate value for this SearchResultBean.
     * 
     * @return modifiedDate
     */
    public java.util.Calendar getModifiedDate() {
        return modifiedDate;
    }


    /**
     * Sets the modifiedDate value for this SearchResultBean.
     * 
     * @param modifiedDate
     */
    public void setModifiedDate(java.util.Calendar modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


    /**
     * Gets the ownerID value for this SearchResultBean.
     * 
     * @return ownerID
     */
    public long getOwnerID() {
        return ownerID;
    }


    /**
     * Sets the ownerID value for this SearchResultBean.
     * 
     * @param ownerID
     */
    public void setOwnerID(long ownerID) {
        this.ownerID = ownerID;
    }


    /**
     * Gets the searchDefinitionID value for this SearchResultBean.
     * 
     * @return searchDefinitionID
     */
    public long getSearchDefinitionID() {
        return searchDefinitionID;
    }


    /**
     * Sets the searchDefinitionID value for this SearchResultBean.
     * 
     * @param searchDefinitionID
     */
    public void setSearchDefinitionID(long searchDefinitionID) {
        this.searchDefinitionID = searchDefinitionID;
    }


    /**
     * Gets the searchName value for this SearchResultBean.
     * 
     * @return searchName
     */
    public java.lang.String getSearchName() {
        return searchName;
    }


    /**
     * Sets the searchName value for this SearchResultBean.
     * 
     * @param searchName
     */
    public void setSearchName(java.lang.String searchName) {
        this.searchName = searchName;
    }


    /**
     * Gets the searchResultId value for this SearchResultBean.
     * 
     * @return searchResultId
     */
    public long getSearchResultId() {
        return searchResultId;
    }


    /**
     * Sets the searchResultId value for this SearchResultBean.
     * 
     * @param searchResultId
     */
    public void setSearchResultId(long searchResultId) {
        this.searchResultId = searchResultId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SearchResultBean)) return false;
        SearchResultBean other = (SearchResultBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.createdBy == other.getCreatedBy() &&
            ((this.createdDate==null && other.getCreatedDate()==null) || 
             (this.createdDate!=null &&
              this.createdDate.equals(other.getCreatedDate()))) &&
            ((this.entityIDs==null && other.getEntityIDs()==null) || 
             (this.entityIDs!=null &&
              java.util.Arrays.equals(this.entityIDs, other.getEntityIDs()))) &&
            this.highlight == other.getHighlight() &&
            ((this.label==null && other.getLabel()==null) || 
             (this.label!=null &&
              this.label.equals(other.getLabel()))) &&
            this.modifiedBy == other.getModifiedBy() &&
            ((this.modifiedDate==null && other.getModifiedDate()==null) || 
             (this.modifiedDate!=null &&
              this.modifiedDate.equals(other.getModifiedDate()))) &&
            this.ownerID == other.getOwnerID() &&
            this.searchDefinitionID == other.getSearchDefinitionID() &&
            ((this.searchName==null && other.getSearchName()==null) || 
             (this.searchName!=null &&
              this.searchName.equals(other.getSearchName()))) &&
            this.searchResultId == other.getSearchResultId();
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
        _hashCode += new Long(getCreatedBy()).hashCode();
        if (getCreatedDate() != null) {
            _hashCode += getCreatedDate().hashCode();
        }
        if (getEntityIDs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEntityIDs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEntityIDs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getHighlight();
        if (getLabel() != null) {
            _hashCode += getLabel().hashCode();
        }
        _hashCode += new Long(getModifiedBy()).hashCode();
        if (getModifiedDate() != null) {
            _hashCode += getModifiedDate().hashCode();
        }
        _hashCode += new Long(getOwnerID()).hashCode();
        _hashCode += new Long(getSearchDefinitionID()).hashCode();
        if (getSearchName() != null) {
            _hashCode += getSearchName().hashCode();
        }
        _hashCode += new Long(getSearchResultId()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SearchResultBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "SearchResultBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createdBy");
        elemField.setXmlName(new javax.xml.namespace.QName("", "createdBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createdDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "createdDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityIDs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "entityIDs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("highlight");
        elemField.setXmlName(new javax.xml.namespace.QName("", "highlight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("label");
        elemField.setXmlName(new javax.xml.namespace.QName("", "label"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifiedBy");
        elemField.setXmlName(new javax.xml.namespace.QName("", "modifiedBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifiedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "modifiedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ownerID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ownerID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchDefinitionID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "searchDefinitionID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "searchName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchResultId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "searchResultId"));
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
