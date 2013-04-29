/**
 * SearchServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bis.webservice.search;

public class SearchServiceLocator extends org.apache.axis.client.Service implements com.bis.webservice.search.SearchService {

    public SearchServiceLocator() {
    }


    public SearchServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SearchServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ISearchServicePort
    private java.lang.String ISearchServicePort_address = "http://10.14.2.101:8080/WebApp/SearchService";

    public java.lang.String getISearchServicePortAddress() {
        return ISearchServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ISearchServicePortWSDDServiceName = "ISearchServicePort";

    public java.lang.String getISearchServicePortWSDDServiceName() {
        return ISearchServicePortWSDDServiceName;
    }

    public void setISearchServicePortWSDDServiceName(java.lang.String name) {
        ISearchServicePortWSDDServiceName = name;
    }

    public com.bis.webservice.search.ISearchService getISearchServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ISearchServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getISearchServicePort(endpoint);
    }

    public com.bis.webservice.search.ISearchService getISearchServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.bis.webservice.search.ISearchServiceBindingStub _stub = new com.bis.webservice.search.ISearchServiceBindingStub(portAddress, this);
            _stub.setPortName(getISearchServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setISearchServicePortEndpointAddress(java.lang.String address) {
        ISearchServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.bis.webservice.search.ISearchService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.bis.webservice.search.ISearchServiceBindingStub _stub = new com.bis.webservice.search.ISearchServiceBindingStub(new java.net.URL(ISearchServicePort_address), this);
                _stub.setPortName(getISearchServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ISearchServicePort".equals(inputPortName)) {
            return getISearchServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://search.webservice.bis.com/", "SearchService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://search.webservice.bis.com/", "ISearchServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ISearchServicePort".equals(portName)) {
            setISearchServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
