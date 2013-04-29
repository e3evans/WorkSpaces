/**
 * UserAccessWSServiceInformation.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf150720.02 v7507160841
 */

package com.manpower.ldap.ws;

public class UserAccessWSServiceInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

    private static java.util.Map operationDescriptions;
    private static java.util.Map typeMappings;

    static {
         initOperationDescriptions();
         initTypeMappings();
    }

    private static void initOperationDescriptions() { 
        operationDescriptions = new java.util.HashMap();

        java.util.Map inner0 = new java.util.HashMap();

        java.util.List list0 = new java.util.ArrayList();
        inner0.put("grantRemoveAccess", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc grantRemoveAccess0Op = _grantRemoveAccess0Op();
        list0.add(grantRemoveAccess0Op);

        java.util.List list1 = new java.util.ArrayList();
        inner0.put("hasAccess_Canada", list1);

        com.ibm.ws.webservices.engine.description.OperationDesc hasAccess_Canada1Op = _hasAccess_Canada1Op();
        list1.add(hasAccess_Canada1Op);

        java.util.List list2 = new java.util.ArrayList();
        inner0.put("hasAccess_USA", list2);

        com.ibm.ws.webservices.engine.description.OperationDesc hasAccess_USA2Op = _hasAccess_USA2Op();
        list2.add(hasAccess_USA2Op);

        operationDescriptions.put("UserAccessWS",inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _grantRemoveAccess0Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc grantRemoveAccess0Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "candidateId"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false, false, true),
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "country"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true),
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "grantAccess"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false, false, true),
          };
        grantRemoveAccess0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("grantRemoveAccess", _params0, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "grantRemoveAccessReturn"));
        grantRemoveAccess0Op.setReturnType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "boolean"));
        grantRemoveAccess0Op.setElementQName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "grantRemoveAccess"));
        if (grantRemoveAccess0Op instanceof com.ibm.ws.webservices.engine.configurable.Configurable) {
         ((com.ibm.ws.webservices.engine.configurable.Configurable)grantRemoveAccess0Op).setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "grantRemoveAccessRequest"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)grantRemoveAccess0Op).setOption("inputName","grantRemoveAccessRequest");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)grantRemoveAccess0Op).setOption("outputName","grantRemoveAccessResponse");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)grantRemoveAccess0Op).setOption("targetNamespace","http://ws.ldap.manpower.com");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)grantRemoveAccess0Op).setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "grantRemoveAccessResponse"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)grantRemoveAccess0Op).setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "UserAccessWS"));
        }
        com.ibm.ws.webservices.engine.description.FaultDesc _fault0 = null;
        return grantRemoveAccess0Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _hasAccess_Canada1Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc hasAccess_Canada1Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "candidateId"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false, false, true),
          };
        hasAccess_Canada1Op = new com.ibm.ws.webservices.engine.description.OperationDesc("hasAccess_Canada", _params0, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "hasAccess_CanadaReturn"));
        hasAccess_Canada1Op.setReturnType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "boolean"));
        hasAccess_Canada1Op.setElementQName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "hasAccess_Canada"));
        if (hasAccess_Canada1Op instanceof com.ibm.ws.webservices.engine.configurable.Configurable) {
         ((com.ibm.ws.webservices.engine.configurable.Configurable)hasAccess_Canada1Op).setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "hasAccess_CanadaRequest"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)hasAccess_Canada1Op).setOption("inputName","hasAccess_CanadaRequest");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)hasAccess_Canada1Op).setOption("outputName","hasAccess_CanadaResponse");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)hasAccess_Canada1Op).setOption("targetNamespace","http://ws.ldap.manpower.com");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)hasAccess_Canada1Op).setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "hasAccess_CanadaResponse"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)hasAccess_Canada1Op).setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "UserAccessWS"));
        }
        com.ibm.ws.webservices.engine.description.FaultDesc _fault0 = null;
        return hasAccess_Canada1Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _hasAccess_USA2Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc hasAccess_USA2Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "candidateId"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false, false, true),
          };
        hasAccess_USA2Op = new com.ibm.ws.webservices.engine.description.OperationDesc("hasAccess_USA", _params0, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "hasAccess_USAReturn"));
        hasAccess_USA2Op.setReturnType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "boolean"));
        hasAccess_USA2Op.setElementQName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "hasAccess_USA"));
        if (hasAccess_USA2Op instanceof com.ibm.ws.webservices.engine.configurable.Configurable) {
         ((com.ibm.ws.webservices.engine.configurable.Configurable)hasAccess_USA2Op).setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "hasAccess_USARequest"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)hasAccess_USA2Op).setOption("inputName","hasAccess_USARequest");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)hasAccess_USA2Op).setOption("outputName","hasAccess_USAResponse");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)hasAccess_USA2Op).setOption("targetNamespace","http://ws.ldap.manpower.com");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)hasAccess_USA2Op).setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "hasAccess_USAResponse"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)hasAccess_USA2Op).setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.ldap.manpower.com", "UserAccessWS"));
        }
        com.ibm.ws.webservices.engine.description.FaultDesc _fault0 = null;
        return hasAccess_USA2Op;

    }


    private static void initTypeMappings() {
        typeMappings = new java.util.HashMap();
        typeMappings = java.util.Collections.unmodifiableMap(typeMappings);
    }

    public java.util.Map getTypeMappings() {
        return typeMappings;
    }

    public Class getJavaType(javax.xml.namespace.QName xmlName) {
        return (Class) typeMappings.get(xmlName);
    }

    public java.util.Map getOperationDescriptions(String portName) {
        return (java.util.Map) operationDescriptions.get(portName);
    }

    public java.util.List getOperationDescriptions(String portName, String operationName) {
        java.util.Map map = (java.util.Map) operationDescriptions.get(portName);
        if (map != null) {
            return (java.util.List) map.get(operationName);
        }
        return null;
    }

}
