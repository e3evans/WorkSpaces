/**
 * UserAccessWSSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf150720.02 v7507160841
 */

package com.manpower.ldap.ws;

public class UserAccessWSSoapBindingStub extends com.ibm.ws.webservices.engine.client.Stub implements com.manpower.ldap.ws.UserAccessWS {
    public UserAccessWSSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
        if (service == null) {
            super.service = new com.ibm.ws.webservices.engine.client.Service();
        }
        else {
            super.service = service;
        }
        super.engine = ((com.ibm.ws.webservices.engine.client.Service) super.service).getEngine();
        initTypeMapping();
        super.cachedEndpoint = endpointURL;
        super.connection = ((com.ibm.ws.webservices.engine.client.Service) super.service).getConnection(endpointURL);
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[3];
    }

    private void initTypeMapping() {
        javax.xml.rpc.encoding.TypeMapping tm = super.getTypeMapping(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
        java.lang.Class javaType = null;
        javax.xml.namespace.QName xmlType = null;
        com.ibm.ws.webservices.engine.encoding.SerializerFactory sf = null;
        com.ibm.ws.webservices.engine.encoding.DeserializerFactory df = null;
    }

    private com.ibm.ws.webservices.engine.description.OperationDesc _grantRemoveAccessOperation0 = null;
    private com.ibm.ws.webservices.engine.description.OperationDesc _getgrantRemoveAccessOperation0() {
        if (_grantRemoveAccessOperation0 == null) {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "candidateId"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false, false, true),
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "country"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true),
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "grantAccess"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false, false, true),
          };
        _grantRemoveAccessOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("grantRemoveAccess", _params0, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "grantRemoveAccessReturn"));
        _grantRemoveAccessOperation0.setReturnType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "boolean"));
        _grantRemoveAccessOperation0.setElementQName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "grantRemoveAccess"));
        _grantRemoveAccessOperation0.setSoapAction("grantRemoveAccess");
        if (_grantRemoveAccessOperation0 instanceof com.ibm.ws.webservices.engine.configurable.Configurable) {
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_grantRemoveAccessOperation0).setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "grantRemoveAccessRequest"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_grantRemoveAccessOperation0).setOption("inputName","grantRemoveAccessRequest");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_grantRemoveAccessOperation0).setOption("outputName","grantRemoveAccessResponse");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_grantRemoveAccessOperation0).setOption("targetNamespace","http://ws.ldap.manpower.com");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_grantRemoveAccessOperation0).setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "grantRemoveAccessResponse"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_grantRemoveAccessOperation0).setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "UserAccessWS"));
        }
        com.ibm.ws.webservices.engine.description.FaultDesc _fault0 = null;
        }
        return _grantRemoveAccessOperation0;
    }

    private int _grantRemoveAccessIndex0 = 0;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getgrantRemoveAccessInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_grantRemoveAccessIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(_getgrantRemoveAccessOperation0());
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("grantRemoveAccess");
            mc.setOperationStyle("rpc");
            mc.setOperationUse("literal");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.ws.webservices.engine.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
            mc.setProperty(com.ibm.ws.webservices.engine.WebServicesEngine.PROP_DOMULTIREFS, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_grantRemoveAccessIndex0] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public boolean grantRemoveAccess(long candidateId, java.lang.String country, boolean grantAccess) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getgrantRemoveAccessInvoke0(new java.lang.Object[] {new java.lang.Long(candidateId), country, new java.lang.Boolean(grantAccess)}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            throw wsf;
        } 
        try {
            return ((java.lang.Boolean) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue()).booleanValue();
        } catch (java.lang.Exception _exception) {
            return ((java.lang.Boolean) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), boolean.class)).booleanValue();
        }
    }

    private com.ibm.ws.webservices.engine.description.OperationDesc _hasAccess_CanadaOperation1 = null;
    private com.ibm.ws.webservices.engine.description.OperationDesc _gethasAccess_CanadaOperation1() {
        if (_hasAccess_CanadaOperation1 == null) {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params1 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "candidateId"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false, false, true),
          };
        _hasAccess_CanadaOperation1 = new com.ibm.ws.webservices.engine.description.OperationDesc("hasAccess_Canada", _params1, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "hasAccess_CanadaReturn"));
        _hasAccess_CanadaOperation1.setReturnType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "boolean"));
        _hasAccess_CanadaOperation1.setElementQName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "hasAccess_Canada"));
        _hasAccess_CanadaOperation1.setSoapAction("hasAccess_Canada");
        if (_hasAccess_CanadaOperation1 instanceof com.ibm.ws.webservices.engine.configurable.Configurable) {
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_hasAccess_CanadaOperation1).setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "hasAccess_CanadaRequest"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_hasAccess_CanadaOperation1).setOption("inputName","hasAccess_CanadaRequest");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_hasAccess_CanadaOperation1).setOption("outputName","hasAccess_CanadaResponse");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_hasAccess_CanadaOperation1).setOption("targetNamespace","http://ws.ldap.manpower.com");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_hasAccess_CanadaOperation1).setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "hasAccess_CanadaResponse"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_hasAccess_CanadaOperation1).setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "UserAccessWS"));
        }
        com.ibm.ws.webservices.engine.description.FaultDesc _fault1 = null;
        }
        return _hasAccess_CanadaOperation1;
    }

    private int _hasAccess_CanadaIndex1 = 1;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _gethasAccess_CanadaInvoke1(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_hasAccess_CanadaIndex1];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(_gethasAccess_CanadaOperation1());
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("hasAccess_Canada");
            mc.setOperationStyle("rpc");
            mc.setOperationUse("literal");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.ws.webservices.engine.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
            mc.setProperty(com.ibm.ws.webservices.engine.WebServicesEngine.PROP_DOMULTIREFS, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_hasAccess_CanadaIndex1] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public boolean hasAccess_Canada(long candidateId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _gethasAccess_CanadaInvoke1(new java.lang.Object[] {new java.lang.Long(candidateId)}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            throw wsf;
        } 
        try {
            return ((java.lang.Boolean) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue()).booleanValue();
        } catch (java.lang.Exception _exception) {
            return ((java.lang.Boolean) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), boolean.class)).booleanValue();
        }
    }

    private com.ibm.ws.webservices.engine.description.OperationDesc _hasAccess_USAOperation2 = null;
    private com.ibm.ws.webservices.engine.description.OperationDesc _gethasAccess_USAOperation2() {
        if (_hasAccess_USAOperation2 == null) {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params2 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "candidateId"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false, false, true),
          };
        _hasAccess_USAOperation2 = new com.ibm.ws.webservices.engine.description.OperationDesc("hasAccess_USA", _params2, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "hasAccess_USAReturn"));
        _hasAccess_USAOperation2.setReturnType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "boolean"));
        _hasAccess_USAOperation2.setElementQName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "hasAccess_USA"));
        _hasAccess_USAOperation2.setSoapAction("hasAccess_USA");
        if (_hasAccess_USAOperation2 instanceof com.ibm.ws.webservices.engine.configurable.Configurable) {
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_hasAccess_USAOperation2).setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "hasAccess_USARequest"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_hasAccess_USAOperation2).setOption("inputName","hasAccess_USARequest");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_hasAccess_USAOperation2).setOption("outputName","hasAccess_USAResponse");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_hasAccess_USAOperation2).setOption("targetNamespace","http://ws.ldap.manpower.com");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_hasAccess_USAOperation2).setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "hasAccess_USAResponse"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_hasAccess_USAOperation2).setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "UserAccessWS"));
        }
        com.ibm.ws.webservices.engine.description.FaultDesc _fault2 = null;
        }
        return _hasAccess_USAOperation2;
    }

    private int _hasAccess_USAIndex2 = 2;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _gethasAccess_USAInvoke2(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_hasAccess_USAIndex2];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(_gethasAccess_USAOperation2());
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("hasAccess_USA");
            mc.setOperationStyle("rpc");
            mc.setOperationUse("literal");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.ws.webservices.engine.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
            mc.setProperty(com.ibm.ws.webservices.engine.WebServicesEngine.PROP_DOMULTIREFS, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_hasAccess_USAIndex2] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public boolean hasAccess_USA(long candidateId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _gethasAccess_USAInvoke2(new java.lang.Object[] {new java.lang.Long(candidateId)}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            throw wsf;
        } 
        try {
            return ((java.lang.Boolean) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue()).booleanValue();
        } catch (java.lang.Exception _exception) {
            return ((java.lang.Boolean) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), boolean.class)).booleanValue();
        }
    }

}
