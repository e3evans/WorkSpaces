package com.bis.webservice.entity;

public class IEntityAccessProxy implements com.bis.webservice.entity.IEntityAccess {
  private String _endpoint = null;
  private com.bis.webservice.entity.IEntityAccess iEntityAccess = null;
  
  public IEntityAccessProxy() {
    _initIEntityAccessProxy();
  }
  
  public IEntityAccessProxy(String endpoint) {
    _endpoint = endpoint;
    _initIEntityAccessProxy();
  }
  
  private void _initIEntityAccessProxy() {
    try {
      iEntityAccess = (new com.bis.webservice.entity.EntityAccessServiceLocator()).getIEntityAccessPort();
      if (iEntityAccess != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iEntityAccess)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iEntityAccess)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iEntityAccess != null)
      ((javax.xml.rpc.Stub)iEntityAccess)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.bis.webservice.entity.IEntityAccess getIEntityAccess() {
    if (iEntityAccess == null)
      _initIEntityAccessProxy();
    return iEntityAccess;
  }
  
  public void deleteEntity(java.lang.String string_1, long long_2) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException{
    if (iEntityAccess == null)
      _initIEntityAccessProxy();
    iEntityAccess.deleteEntity(string_1, long_2);
  }
  
  public java.lang.String getEntity(java.lang.String string_1, long long_2) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException{
    if (iEntityAccess == null)
      _initIEntityAccessProxy();
    return iEntityAccess.getEntity(string_1, long_2);
  }
  
  public java.lang.String getEntityFormatted(java.lang.String string_1, long long_2, com.bis.webservice.entity.types.EntityFormattedDateBean entityFormattedDateBean_3) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException{
    if (iEntityAccess == null)
      _initIEntityAccessProxy();
    return iEntityAccess.getEntityFormatted(string_1, long_2, entityFormattedDateBean_3);
  }
  
  public java.lang.String getUserAsEntity(java.lang.String string_1, long long_2) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException{
    if (iEntityAccess == null)
      _initIEntityAccessProxy();
    return iEntityAccess.getUserAsEntity(string_1, long_2);
  }
  
  public java.lang.String getUserAsEntityByADUsername(java.lang.String string_1, java.lang.String string_2, com.bis.webservice.entity.types.EntityFormattedDateBean entityFormattedDateBean_3) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException{
    if (iEntityAccess == null)
      _initIEntityAccessProxy();
    return iEntityAccess.getUserAsEntityByADUsername(string_1, string_2, entityFormattedDateBean_3);
  }
  
  public java.lang.String getUserAsEntityByName(java.lang.String string_1, java.lang.String string_2) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException{
    if (iEntityAccess == null)
      _initIEntityAccessProxy();
    return iEntityAccess.getUserAsEntityByName(string_1, string_2);
  }
  
  public java.lang.String getUserAsEntityByNameFormatted(java.lang.String string_1, java.lang.String string_2, com.bis.webservice.entity.types.EntityFormattedDateBean entityFormattedDateBean_3) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException{
    if (iEntityAccess == null)
      _initIEntityAccessProxy();
    return iEntityAccess.getUserAsEntityByNameFormatted(string_1, string_2, entityFormattedDateBean_3);
  }
  
  public java.lang.String getUserAsEntityFormatted(java.lang.String string_1, long long_2, com.bis.webservice.entity.types.EntityFormattedDateBean entityFormattedDateBean_3) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException{
    if (iEntityAccess == null)
      _initIEntityAccessProxy();
    return iEntityAccess.getUserAsEntityFormatted(string_1, long_2, entityFormattedDateBean_3);
  }
  
  public void lockEntity(long long_1, java.lang.String string_2, long long_3) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.DataNotFoundException{
    if (iEntityAccess == null)
      _initIEntityAccessProxy();
    iEntityAccess.lockEntity(long_1, string_2, long_3);
  }
  
  public void removeEntity(java.lang.String string_1, long long_2) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException{
    if (iEntityAccess == null)
      _initIEntityAccessProxy();
    iEntityAccess.removeEntity(string_1, long_2);
  }
  
  public java.lang.String saveEntity(java.lang.String string_1, java.lang.String string_2) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException{
    if (iEntityAccess == null)
      _initIEntityAccessProxy();
    return iEntityAccess.saveEntity(string_1, string_2);
  }
  
  public java.lang.String saveEntityFormatted(java.lang.String string_1, java.lang.String string_2, com.bis.webservice.entity.types.EntityFormattedDateBean entityFormattedDateBean_3) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException{
    if (iEntityAccess == null)
      _initIEntityAccessProxy();
    return iEntityAccess.saveEntityFormatted(string_1, string_2, entityFormattedDateBean_3);
  }
  
  public void unlockEntity(long long_1, java.lang.String string_2, long long_3) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.DataNotFoundException{
    if (iEntityAccess == null)
      _initIEntityAccessProxy();
    iEntityAccess.unlockEntity(long_1, string_2, long_3);
  }
  
  
}