package com.bis.webservice.search;

public class ISearchServiceProxy implements com.bis.webservice.search.ISearchService {
  private String _endpoint = null;
  private com.bis.webservice.search.ISearchService iSearchService = null;
  
  public ISearchServiceProxy() {
    _initISearchServiceProxy();
  }
  
  public ISearchServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initISearchServiceProxy();
  }
  
  private void _initISearchServiceProxy() {
    try {
      iSearchService = (new com.bis.webservice.search.SearchServiceLocator()).getISearchServicePort();
      if (iSearchService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iSearchService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iSearchService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iSearchService != null)
      ((javax.xml.rpc.Stub)iSearchService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.bis.webservice.search.ISearchService getISearchService() {
    if (iSearchService == null)
      _initISearchServiceProxy();
    return iSearchService;
  }
  
  public void deleteSearchResult(java.lang.String string_1, long long_2) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException{
    if (iSearchService == null)
      _initISearchServiceProxy();
    iSearchService.deleteSearchResult(string_1, long_2);
  }
  
  public com.bis.webservice.search.types.SearchParameter[] echoParameters(com.bis.webservice.search.types.SearchParameter[] arrayOfSearchParameter_1) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException{
    if (iSearchService == null)
      _initISearchServiceProxy();
    return iSearchService.echoParameters(arrayOfSearchParameter_1);
  }
  
  public long[] getAllSearchDefinitionIDs(java.lang.String string_1) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException{
    if (iSearchService == null)
      _initISearchServiceProxy();
    return iSearchService.getAllSearchDefinitionIDs(string_1);
  }
  
  public java.lang.String[] getAllSearchDefinitionNames(java.lang.String string_1) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException{
    if (iSearchService == null)
      _initISearchServiceProxy();
    return iSearchService.getAllSearchDefinitionNames(string_1);
  }
  
  public long[] getAllSearchResultsForUser(java.lang.String string_1, java.lang.String string_2) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException{
    if (iSearchService == null)
      _initISearchServiceProxy();
    return iSearchService.getAllSearchResultsForUser(string_1, string_2);
  }
  
  public long[] getAllSearchResultsForWebUser(java.lang.String string_1) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException{
    if (iSearchService == null)
      _initISearchServiceProxy();
    return iSearchService.getAllSearchResultsForWebUser(string_1);
  }
  
  public java.lang.String getFormattedSearchResult(java.lang.String string_1, long long_2, int int_3, int int_4, com.bis.webservice.search.types.SearchFormattedDateBean searchFormattedDateBean_5) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException{
    if (iSearchService == null)
      _initISearchServiceProxy();
    return iSearchService.getFormattedSearchResult(string_1, long_2, int_3, int_4, searchFormattedDateBean_5);
  }
  
  public com.bis.webservice.search.types.SearchResultBean getSearchResult(java.lang.String string_1, long long_2) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException{
    if (iSearchService == null)
      _initISearchServiceProxy();
    return iSearchService.getSearchResult(string_1, long_2);
  }
  
  public long getSearchResultCount(java.lang.String string_1, long long_2) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException{
    if (iSearchService == null)
      _initISearchServiceProxy();
    return iSearchService.getSearchResultCount(string_1, long_2);
  }
  
  public long[] getStoredResultList(java.lang.String string_1, java.lang.String string_2, com.bis.webservice.search.types.SearchParameter[] arrayOfSearchParameter_3) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException{
    if (iSearchService == null)
      _initISearchServiceProxy();
    return iSearchService.getStoredResultList(string_1, string_2, arrayOfSearchParameter_3);
  }
  
  public void resetToDefaultFormatter(java.lang.String string_1, long long_2) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException{
    if (iSearchService == null)
      _initISearchServiceProxy();
    iSearchService.resetToDefaultFormatter(string_1, long_2);
  }
  
  public com.bis.webservice.search.types.SearchResultBean runSearchAndAssignToUser(java.lang.String string_1, java.lang.String string_2, com.bis.webservice.search.types.SearchParameter[] arrayOfSearchParameter_3, int int_4, java.lang.String string_5) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException{
    if (iSearchService == null)
      _initISearchServiceProxy();
    return iSearchService.runSearchAndAssignToUser(string_1, string_2, arrayOfSearchParameter_3, int_4, string_5);
  }
  
  public com.bis.webservice.search.types.SearchResultBean runSearchById(java.lang.String string_1, long long_2, com.bis.webservice.search.types.SearchParameter[] arrayOfSearchParameter_3, int int_4) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException{
    if (iSearchService == null)
      _initISearchServiceProxy();
    return iSearchService.runSearchById(string_1, long_2, arrayOfSearchParameter_3, int_4);
  }
  
  public com.bis.webservice.search.types.SearchResultBean runSearchByName(java.lang.String string_1, java.lang.String string_2, com.bis.webservice.search.types.SearchParameter[] arrayOfSearchParameter_3, int int_4) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException{
    if (iSearchService == null)
      _initISearchServiceProxy();
    return iSearchService.runSearchByName(string_1, string_2, arrayOfSearchParameter_3, int_4);
  }
  
  public void setSearchResultFormatter(java.lang.String string_1, long long_2, com.bis.webservice.search.types.SearchColumnBean[] arrayOfSearchColumnBean_3) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException{
    if (iSearchService == null)
      _initISearchServiceProxy();
    iSearchService.setSearchResultFormatter(string_1, long_2, arrayOfSearchColumnBean_3);
  }
  
  
}