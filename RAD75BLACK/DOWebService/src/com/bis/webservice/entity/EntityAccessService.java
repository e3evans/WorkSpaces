/**
 * EntityAccessService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bis.webservice.entity;

public interface EntityAccessService extends javax.xml.rpc.Service {
    public java.lang.String getIEntityAccessPortAddress();

    public com.bis.webservice.entity.IEntityAccess getIEntityAccessPort() throws javax.xml.rpc.ServiceException;

    public com.bis.webservice.entity.IEntityAccess getIEntityAccessPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
