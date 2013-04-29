/**
 * ICodeGroup.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bis.webservice.codegroup;

public interface ICodeGroup extends java.rmi.Remote {
    public com.bis.webservice.codegroup.types.CodeBean getCodeByID(java.lang.String string_1, long long_2, java.lang.String string_3) throws java.rmi.RemoteException, com.bis.webservice.codegroup.types.ServerErrorException, com.bis.webservice.codegroup.types.InvalidArgumentException, com.bis.webservice.codegroup.types.DataNotFoundException;
    public com.bis.webservice.codegroup.types.CodeGroupBean getCodeGroup(java.lang.String string_1, java.lang.String string_2) throws java.rmi.RemoteException, com.bis.webservice.codegroup.types.ServerErrorException, com.bis.webservice.codegroup.types.InvalidArgumentException, com.bis.webservice.codegroup.types.DataNotFoundException;
    public com.bis.webservice.codegroup.types.CodeGroupBean getCodeGroupByLanguage(java.lang.String string_1, java.lang.String string_2, java.lang.String string_3) throws java.rmi.RemoteException, com.bis.webservice.codegroup.types.ServerErrorException, com.bis.webservice.codegroup.types.InvalidArgumentException, com.bis.webservice.codegroup.types.DataNotFoundException;
    public long getCodeIDFromPath(java.lang.String string_1, java.lang.String string_2) throws java.rmi.RemoteException, com.bis.webservice.codegroup.types.ServerErrorException, com.bis.webservice.codegroup.types.InvalidArgumentException, com.bis.webservice.codegroup.types.DataNotFoundException;
}
