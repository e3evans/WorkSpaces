/**
 * CodeGroupAccess.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bis.webservice.codegroup;

public interface CodeGroupAccess extends javax.xml.rpc.Service {
    public java.lang.String getICodeGroupPortAddress();

    public com.bis.webservice.codegroup.ICodeGroup getICodeGroupPort() throws javax.xml.rpc.ServiceException;

    public com.bis.webservice.codegroup.ICodeGroup getICodeGroupPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
