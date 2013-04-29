/**
 * UserAccessWSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf150720.02 v7507160841
 */

package com.manpower.ldap.ws;

import javax.faces.context.FacesContext;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

public class UserAccessWSServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, com.manpower.ldap.ws.UserAccessWSService {
	
	private static final String WS_PORT_CONST = "WEB_SERVICE_CONNECT_PORT";

    public UserAccessWSServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://ws.ldap.manpower.com",
           "UserAccessWSService"));

        context.setLocatorName("com.manpower.ldap.ws.UserAccessWSServiceLocator");
        
        PortletRequest request=(PortletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        PortletPreferences preferences = request.getPreferences();
        
        String port = preferences.getValue(WS_PORT_CONST, "10000");
        
        userAccessWS_address = "http://localhost:" + port + "/DirectOffice_UserAccessWS/services/UserAccessWS";
    }

    public UserAccessWSServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("com.manpower.ldap.ws.UserAccessWSServiceLocator");
        
        PortletRequest request=(PortletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        PortletPreferences preferences = request.getPreferences();
        
        String port = preferences.getValue(WS_PORT_CONST, "10000");
        
        userAccessWS_address = "http://localhost:" + port + "/DirectOffice_UserAccessWS/services/UserAccessWS";
    }

    
    // Use to get a proxy class for userAccessWS
    private java.lang.String userAccessWS_address;

    public java.lang.String getUserAccessWSAddress() {
        return userAccessWS_address;
    }

    private java.lang.String userAccessWSPortName = "UserAccessWS";

    // The WSDD port name defaults to the port name.
    private java.lang.String userAccessWSWSDDPortName = "UserAccessWS";

    public java.lang.String getUserAccessWSWSDDPortName() {
        return userAccessWSWSDDPortName;
    }

    public void setUserAccessWSWSDDPortName(java.lang.String name) {
        userAccessWSWSDDPortName = name;
    }

    public com.manpower.ldap.ws.UserAccessWS getUserAccessWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(userAccessWS_address);
        }
        catch (java.net.MalformedURLException e) {
            return null; // unlikely as URL was validated in WSDL2Java
        }
        return getUserAccessWS(endpoint);
    }

    public com.manpower.ldap.ws.UserAccessWS getUserAccessWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        com.manpower.ldap.ws.UserAccessWS _stub =
            (com.manpower.ldap.ws.UserAccessWS) getStub(
                userAccessWSPortName,
                (String) getPort2NamespaceMap().get(userAccessWSPortName),
                com.manpower.ldap.ws.UserAccessWS.class,
                "com.manpower.ldap.ws.UserAccessWSSoapBindingStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(userAccessWSWSDDPortName);
        }
        return _stub;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.manpower.ldap.ws.UserAccessWS.class.isAssignableFrom(serviceEndpointInterface)) {
                return getUserAccessWS();
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("WSWS3273E: Error: There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        String inputPortName = portName.getLocalPart();
        if ("UserAccessWS".equals(inputPortName)) {
            return getUserAccessWS();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        userAccessWSWSDDPortName = prefix + "/" + userAccessWSPortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return super.getServiceName();
    }

    private java.util.Map port2NamespaceMap = null;

    protected java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "UserAccessWS",
               "http://schemas.xmlsoap.org/wsdl/soap/");
        }
        return port2NamespaceMap;
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            String serviceNamespace = getServiceName().getNamespaceURI();
            for (java.util.Iterator i = getPort2NamespaceMap().keySet().iterator(); i.hasNext(); ) {
                ports.add(
                    com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                        serviceNamespace,
                        (String) i.next()));
            }
        }
        return ports.iterator();
    }

    public javax.xml.rpc.Call[] getCalls(javax.xml.namespace.QName portName) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName should not be null.");
        }
        if  (portName.getLocalPart().equals("UserAccessWS")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "grantRemoveAccess", "grantRemoveAccessRequest"),
                createCall(portName, "hasAccess_Canada", "hasAccess_CanadaRequest"),
                createCall(portName, "hasAccess_USA", "hasAccess_USARequest"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName should not be null.");
        }
    }
}
