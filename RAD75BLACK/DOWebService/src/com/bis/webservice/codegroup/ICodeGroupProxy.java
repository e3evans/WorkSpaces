package com.bis.webservice.codegroup;

public class ICodeGroupProxy implements com.bis.webservice.codegroup.ICodeGroup {
  private String _endpoint = null;
  private com.bis.webservice.codegroup.ICodeGroup iCodeGroup = null;
  
  public ICodeGroupProxy() {
    _initICodeGroupProxy();
  }
  
  public ICodeGroupProxy(String endpoint) {
    _endpoint = endpoint;
    _initICodeGroupProxy();
  }
  
  private void _initICodeGroupProxy() {
    try {
      iCodeGroup = (new com.bis.webservice.codegroup.CodeGroupAccessLocator()).getICodeGroupPort();
      if (iCodeGroup != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iCodeGroup)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iCodeGroup)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iCodeGroup != null)
      ((javax.xml.rpc.Stub)iCodeGroup)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.bis.webservice.codegroup.ICodeGroup getICodeGroup() {
    if (iCodeGroup == null)
      _initICodeGroupProxy();
    return iCodeGroup;
  }
  
  public com.bis.webservice.codegroup.types.CodeBean getCodeByID(java.lang.String string_1, long long_2, java.lang.String string_3) throws java.rmi.RemoteException, com.bis.webservice.codegroup.types.ServerErrorException, com.bis.webservice.codegroup.types.InvalidArgumentException, com.bis.webservice.codegroup.types.DataNotFoundException{
    if (iCodeGroup == null)
      _initICodeGroupProxy();
    return iCodeGroup.getCodeByID(string_1, long_2, string_3);
  }
  
  public com.bis.webservice.codegroup.types.CodeGroupBean getCodeGroup(java.lang.String string_1, java.lang.String string_2) throws java.rmi.RemoteException, com.bis.webservice.codegroup.types.ServerErrorException, com.bis.webservice.codegroup.types.InvalidArgumentException, com.bis.webservice.codegroup.types.DataNotFoundException{
    if (iCodeGroup == null)
      _initICodeGroupProxy();
    return iCodeGroup.getCodeGroup(string_1, string_2);
  }
  
  public com.bis.webservice.codegroup.types.CodeGroupBean getCodeGroupByLanguage(java.lang.String string_1, java.lang.String string_2, java.lang.String string_3) throws java.rmi.RemoteException, com.bis.webservice.codegroup.types.ServerErrorException, com.bis.webservice.codegroup.types.InvalidArgumentException, com.bis.webservice.codegroup.types.DataNotFoundException{
    if (iCodeGroup == null)
      _initICodeGroupProxy();
    return iCodeGroup.getCodeGroupByLanguage(string_1, string_2, string_3);
  }
  
  public long getCodeIDFromPath(java.lang.String string_1, java.lang.String string_2) throws java.rmi.RemoteException, com.bis.webservice.codegroup.types.ServerErrorException, com.bis.webservice.codegroup.types.InvalidArgumentException, com.bis.webservice.codegroup.types.DataNotFoundException{
    if (iCodeGroup == null)
      _initICodeGroupProxy();
    return iCodeGroup.getCodeIDFromPath(string_1, string_2);
  }
  
  
}