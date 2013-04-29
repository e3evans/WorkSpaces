/**
 * CodeGroupAccessLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bis.webservice.codegroup;

public class CodeGroupAccessLocator extends org.apache.axis.client.Service implements com.bis.webservice.codegroup.CodeGroupAccess {

    public CodeGroupAccessLocator() {
    }


    public CodeGroupAccessLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CodeGroupAccessLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ICodeGroupPort
    private java.lang.String ICodeGroupPort_address = "http://10.14.2.144:8080/WebApp/CodeGroup";

    public java.lang.String getICodeGroupPortAddress() {
        return ICodeGroupPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ICodeGroupPortWSDDServiceName = "ICodeGroupPort";

    public java.lang.String getICodeGroupPortWSDDServiceName() {
        return ICodeGroupPortWSDDServiceName;
    }

    public void setICodeGroupPortWSDDServiceName(java.lang.String name) {
        ICodeGroupPortWSDDServiceName = name;
    }

    public com.bis.webservice.codegroup.ICodeGroup getICodeGroupPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ICodeGroupPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getICodeGroupPort(endpoint);
    }

    public com.bis.webservice.codegroup.ICodeGroup getICodeGroupPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.bis.webservice.codegroup.ICodeGroupBindingStub _stub = new com.bis.webservice.codegroup.ICodeGroupBindingStub(portAddress, this);
            _stub.setPortName(getICodeGroupPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setICodeGroupPortEndpointAddress(java.lang.String address) {
        ICodeGroupPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.bis.webservice.codegroup.ICodeGroup.class.isAssignableFrom(serviceEndpointInterface)) {
                com.bis.webservice.codegroup.ICodeGroupBindingStub _stub = new com.bis.webservice.codegroup.ICodeGroupBindingStub(new java.net.URL(ICodeGroupPort_address), this);
                _stub.setPortName(getICodeGroupPortWSDDServiceName());
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
        if ("ICodeGroupPort".equals(inputPortName)) {
            return getICodeGroupPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://codegroup.webservice.bis.com/", "CodeGroupAccess");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://codegroup.webservice.bis.com/", "ICodeGroupPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ICodeGroupPort".equals(portName)) {
            setICodeGroupPortEndpointAddress(address);
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
