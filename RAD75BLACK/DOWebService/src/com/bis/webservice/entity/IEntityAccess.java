/**
 * IEntityAccess.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bis.webservice.entity;

public interface IEntityAccess extends java.rmi.Remote {
    public void deleteEntity(java.lang.String string_1, long long_2) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException;
    public java.lang.String getEntity(java.lang.String string_1, long long_2) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException;
    public java.lang.String getEntityFormatted(java.lang.String string_1, long long_2, com.bis.webservice.entity.types.EntityFormattedDateBean entityFormattedDateBean_3) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException;
    public java.lang.String getUserAsEntity(java.lang.String string_1, long long_2) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException;
    public java.lang.String getUserAsEntityByADUsername(java.lang.String string_1, java.lang.String string_2, com.bis.webservice.entity.types.EntityFormattedDateBean entityFormattedDateBean_3) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException;
    public java.lang.String getUserAsEntityByName(java.lang.String string_1, java.lang.String string_2) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException;
    public java.lang.String getUserAsEntityByNameFormatted(java.lang.String string_1, java.lang.String string_2, com.bis.webservice.entity.types.EntityFormattedDateBean entityFormattedDateBean_3) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException;
    public java.lang.String getUserAsEntityFormatted(java.lang.String string_1, long long_2, com.bis.webservice.entity.types.EntityFormattedDateBean entityFormattedDateBean_3) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException;
    public void lockEntity(long long_1, java.lang.String string_2, long long_3) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.DataNotFoundException;
    public void removeEntity(java.lang.String string_1, long long_2) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException;
    public java.lang.String saveEntity(java.lang.String string_1, java.lang.String string_2) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException;
    public java.lang.String saveEntityFormatted(java.lang.String string_1, java.lang.String string_2, com.bis.webservice.entity.types.EntityFormattedDateBean entityFormattedDateBean_3) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException;
    public void unlockEntity(long long_1, java.lang.String string_2, long long_3) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.DataNotFoundException;
}
