package com.manpower.ldap.ws;

public class UserAccessWSProxy implements com.manpower.ldap.ws.UserAccessWS {
  private boolean _useJNDI = true;
  private String _endpoint = null;
  private com.manpower.ldap.ws.UserAccessWS __userAccessWS = null;
  
  public UserAccessWSProxy() {
    _initUserAccessWSProxy();
  }
  
  private void _initUserAccessWSProxy() {
  
    if (_useJNDI) {
      try {
        javax.naming.InitialContext ctx = new javax.naming.InitialContext();
        __userAccessWS = ((com.manpower.ldap.ws.UserAccessWSService)ctx.lookup("java:comp/env/service/UserAccessWSService")).getUserAccessWS();
      }
      catch (javax.naming.NamingException namingException) {}
      catch (javax.xml.rpc.ServiceException serviceException) {}
    }
    if (__userAccessWS == null) {
      try {
        __userAccessWS = (new com.manpower.ldap.ws.UserAccessWSServiceLocator()).getUserAccessWS();
        
      }
      catch (javax.xml.rpc.ServiceException serviceException) {}
    }
    if (__userAccessWS != null) {
      if (_endpoint != null)
        ((javax.xml.rpc.Stub)__userAccessWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
      else
        _endpoint = (String)((javax.xml.rpc.Stub)__userAccessWS)._getProperty("javax.xml.rpc.service.endpoint.address");
    }
    
  }
  
  
  public void useJNDI(boolean useJNDI) {
    _useJNDI = useJNDI;
    __userAccessWS = null;
    
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (__userAccessWS != null)
      ((javax.xml.rpc.Stub)__userAccessWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.manpower.ldap.ws.UserAccessWS getUserAccessWS() {
    if (__userAccessWS == null)
      _initUserAccessWSProxy();
    return __userAccessWS;
  }
  
  public boolean grantRemoveAccess(long candidateId, java.lang.String country, boolean grantAccess) throws java.rmi.RemoteException{
    if (__userAccessWS == null)
      _initUserAccessWSProxy();
    return __userAccessWS.grantRemoveAccess(candidateId, country, grantAccess);
  }
  
  public boolean hasAccess_Canada(long candidateId) throws java.rmi.RemoteException{
    if (__userAccessWS == null)
      _initUserAccessWSProxy();
    return __userAccessWS.hasAccess_Canada(candidateId);
  }
  
  public boolean hasAccess_USA(long candidateId) throws java.rmi.RemoteException{
    if (__userAccessWS == null)
      _initUserAccessWSProxy();
    return __userAccessWS.hasAccess_USA(candidateId);
  }
  
  
}