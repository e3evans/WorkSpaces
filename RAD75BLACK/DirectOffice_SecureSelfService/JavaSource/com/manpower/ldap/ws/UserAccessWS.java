/**
 * UserAccessWS.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf150720.02 v7507160841
 */

package com.manpower.ldap.ws;

public interface UserAccessWS extends java.rmi.Remote {
    public boolean grantRemoveAccess(long candidateId, java.lang.String country, boolean grantAccess) throws java.rmi.RemoteException;
    public boolean hasAccess_Canada(long candidateId) throws java.rmi.RemoteException;
    public boolean hasAccess_USA(long candidateId) throws java.rmi.RemoteException;
}
