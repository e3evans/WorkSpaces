package com.manpower.ldap.ws;


public interface UserAccessWS_SEI extends java.rmi.Remote
{
  public boolean grantRemoveAccess(long candidateId,java.lang.String country,boolean grantAccess) throws java.lang.Exception;
  public boolean hasAccess_Canada(long candidateId) throws java.lang.Exception;
  public boolean hasAccess_USA(long candidateId) throws java.lang.Exception;
}