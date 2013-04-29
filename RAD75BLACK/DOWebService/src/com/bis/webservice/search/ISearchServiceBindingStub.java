/**
 * ISearchServiceBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bis.webservice.search;

public class ISearchServiceBindingStub extends org.apache.axis.client.Stub implements com.bis.webservice.search.ISearchService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[15];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteSearchResult");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "long_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.search.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.search.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.search.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("echoParameters");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arrayOfSearchParameter_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ArrayOfSearchParameter"), com.bis.webservice.search.types.SearchParameter[].class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ArrayOfSearchParameter"));
        oper.setReturnClass(com.bis.webservice.search.types.SearchParameter[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.search.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.search.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.search.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllSearchDefinitionIDs");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ArrayOflong"));
        oper.setReturnClass(long[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.search.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.search.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.search.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllSearchDefinitionNames");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ArrayOfstring"));
        oper.setReturnClass(java.lang.String[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.search.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.search.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.search.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllSearchResultsForUser");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ArrayOflong"));
        oper.setReturnClass(long[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.search.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.search.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.search.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllSearchResultsForWebUser");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ArrayOflong"));
        oper.setReturnClass(long[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.search.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.search.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.search.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getFormattedSearchResult");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "long_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "int_3"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "int_4"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "SearchFormattedDateBean_5"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "SearchFormattedDateBean"), com.bis.webservice.search.types.SearchFormattedDateBean.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.search.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.search.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.search.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getSearchResult");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "long_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "SearchResultBean"));
        oper.setReturnClass(com.bis.webservice.search.types.SearchResultBean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.search.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.search.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.search.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getSearchResultCount");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "long_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        oper.setReturnClass(long.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.search.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.search.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.search.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getStoredResultList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arrayOfSearchParameter_3"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ArrayOfSearchParameter"), com.bis.webservice.search.types.SearchParameter[].class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ArrayOflong"));
        oper.setReturnClass(long[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.search.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.search.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.search.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("resetToDefaultFormatter");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "long_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.search.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.search.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.search.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("runSearchAndAssignToUser");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arrayOfSearchParameter_3"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ArrayOfSearchParameter"), com.bis.webservice.search.types.SearchParameter[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "int_4"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_5"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "SearchResultBean"));
        oper.setReturnClass(com.bis.webservice.search.types.SearchResultBean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.search.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.search.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.search.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("runSearchById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "long_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arrayOfSearchParameter_3"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ArrayOfSearchParameter"), com.bis.webservice.search.types.SearchParameter[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "int_4"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "SearchResultBean"));
        oper.setReturnClass(com.bis.webservice.search.types.SearchResultBean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.search.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.search.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.search.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("runSearchByName");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arrayOfSearchParameter_3"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ArrayOfSearchParameter"), com.bis.webservice.search.types.SearchParameter[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "int_4"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "SearchResultBean"));
        oper.setReturnClass(com.bis.webservice.search.types.SearchResultBean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.search.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.search.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.search.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setSearchResultFormatter");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "long_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arrayOfSearchColumnBean_3"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ArrayOfSearchColumnBean"), com.bis.webservice.search.types.SearchColumnBean[].class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.search.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.search.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.search.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[14] = oper;

    }

    public ISearchServiceBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ISearchServiceBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ISearchServiceBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ArrayOflong");
            cachedSerQNames.add(qName);
            cls = long[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ArrayOfSearchColumnBean");
            cachedSerQNames.add(qName);
            cls = com.bis.webservice.search.types.SearchColumnBean[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "SearchColumnBean");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ArrayOfSearchParameter");
            cachedSerQNames.add(qName);
            cls = com.bis.webservice.search.types.SearchParameter[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "SearchParameter");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ArrayOfstring");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "DataNotFoundException");
            cachedSerQNames.add(qName);
            cls = com.bis.webservice.search.types.DataNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "InvalidArgumentException");
            cachedSerQNames.add(qName);
            cls = com.bis.webservice.search.types.InvalidArgumentException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "SearchColumnBean");
            cachedSerQNames.add(qName);
            cls = com.bis.webservice.search.types.SearchColumnBean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "SearchFormattedDateBean");
            cachedSerQNames.add(qName);
            cls = com.bis.webservice.search.types.SearchFormattedDateBean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "SearchParameter");
            cachedSerQNames.add(qName);
            cls = com.bis.webservice.search.types.SearchParameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "SearchResultBean");
            cachedSerQNames.add(qName);
            cls = com.bis.webservice.search.types.SearchResultBean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://search.webservice.bis.com/types", "ServerErrorException");
            cachedSerQNames.add(qName);
            cls = com.bis.webservice.search.types.ServerErrorException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public void deleteSearchResult(java.lang.String string_1, long long_2) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://search.webservice.bis.com/", "deleteSearchResult"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1, new java.lang.Long(long_2)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.ServerErrorException) {
              throw (com.bis.webservice.search.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.InvalidArgumentException) {
              throw (com.bis.webservice.search.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.DataNotFoundException) {
              throw (com.bis.webservice.search.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.bis.webservice.search.types.SearchParameter[] echoParameters(com.bis.webservice.search.types.SearchParameter[] arrayOfSearchParameter_1) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://search.webservice.bis.com/", "echoParameters"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {arrayOfSearchParameter_1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bis.webservice.search.types.SearchParameter[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bis.webservice.search.types.SearchParameter[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.bis.webservice.search.types.SearchParameter[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.ServerErrorException) {
              throw (com.bis.webservice.search.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.InvalidArgumentException) {
              throw (com.bis.webservice.search.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.DataNotFoundException) {
              throw (com.bis.webservice.search.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public long[] getAllSearchDefinitionIDs(java.lang.String string_1) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://search.webservice.bis.com/", "getAllSearchDefinitionIDs"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (long[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (long[]) org.apache.axis.utils.JavaUtils.convert(_resp, long[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.ServerErrorException) {
              throw (com.bis.webservice.search.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.InvalidArgumentException) {
              throw (com.bis.webservice.search.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.DataNotFoundException) {
              throw (com.bis.webservice.search.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String[] getAllSearchDefinitionNames(java.lang.String string_1) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://search.webservice.bis.com/", "getAllSearchDefinitionNames"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.ServerErrorException) {
              throw (com.bis.webservice.search.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.InvalidArgumentException) {
              throw (com.bis.webservice.search.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.DataNotFoundException) {
              throw (com.bis.webservice.search.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public long[] getAllSearchResultsForUser(java.lang.String string_1, java.lang.String string_2) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://search.webservice.bis.com/", "getAllSearchResultsForUser"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1, string_2});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (long[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (long[]) org.apache.axis.utils.JavaUtils.convert(_resp, long[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.ServerErrorException) {
              throw (com.bis.webservice.search.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.InvalidArgumentException) {
              throw (com.bis.webservice.search.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.DataNotFoundException) {
              throw (com.bis.webservice.search.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public long[] getAllSearchResultsForWebUser(java.lang.String string_1) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://search.webservice.bis.com/", "getAllSearchResultsForWebUser"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (long[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (long[]) org.apache.axis.utils.JavaUtils.convert(_resp, long[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.ServerErrorException) {
              throw (com.bis.webservice.search.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.InvalidArgumentException) {
              throw (com.bis.webservice.search.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.DataNotFoundException) {
              throw (com.bis.webservice.search.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String getFormattedSearchResult(java.lang.String string_1, long long_2, int int_3, int int_4, com.bis.webservice.search.types.SearchFormattedDateBean searchFormattedDateBean_5) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://search.webservice.bis.com/", "getFormattedSearchResult"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1, new java.lang.Long(long_2), new java.lang.Integer(int_3), new java.lang.Integer(int_4), searchFormattedDateBean_5});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.ServerErrorException) {
              throw (com.bis.webservice.search.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.InvalidArgumentException) {
              throw (com.bis.webservice.search.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.DataNotFoundException) {
              throw (com.bis.webservice.search.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.bis.webservice.search.types.SearchResultBean getSearchResult(java.lang.String string_1, long long_2) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://search.webservice.bis.com/", "getSearchResult"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1, new java.lang.Long(long_2)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bis.webservice.search.types.SearchResultBean) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bis.webservice.search.types.SearchResultBean) org.apache.axis.utils.JavaUtils.convert(_resp, com.bis.webservice.search.types.SearchResultBean.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.ServerErrorException) {
              throw (com.bis.webservice.search.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.InvalidArgumentException) {
              throw (com.bis.webservice.search.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.DataNotFoundException) {
              throw (com.bis.webservice.search.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public long getSearchResultCount(java.lang.String string_1, long long_2) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://search.webservice.bis.com/", "getSearchResultCount"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1, new java.lang.Long(long_2)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Long) _resp).longValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Long) org.apache.axis.utils.JavaUtils.convert(_resp, long.class)).longValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.ServerErrorException) {
              throw (com.bis.webservice.search.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.InvalidArgumentException) {
              throw (com.bis.webservice.search.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.DataNotFoundException) {
              throw (com.bis.webservice.search.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public long[] getStoredResultList(java.lang.String string_1, java.lang.String string_2, com.bis.webservice.search.types.SearchParameter[] arrayOfSearchParameter_3) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://search.webservice.bis.com/", "getStoredResultList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1, string_2, arrayOfSearchParameter_3});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (long[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (long[]) org.apache.axis.utils.JavaUtils.convert(_resp, long[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.ServerErrorException) {
              throw (com.bis.webservice.search.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.InvalidArgumentException) {
              throw (com.bis.webservice.search.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.DataNotFoundException) {
              throw (com.bis.webservice.search.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void resetToDefaultFormatter(java.lang.String string_1, long long_2) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://search.webservice.bis.com/", "resetToDefaultFormatter"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1, new java.lang.Long(long_2)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.ServerErrorException) {
              throw (com.bis.webservice.search.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.InvalidArgumentException) {
              throw (com.bis.webservice.search.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.DataNotFoundException) {
              throw (com.bis.webservice.search.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.bis.webservice.search.types.SearchResultBean runSearchAndAssignToUser(java.lang.String string_1, java.lang.String string_2, com.bis.webservice.search.types.SearchParameter[] arrayOfSearchParameter_3, int int_4, java.lang.String string_5) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://search.webservice.bis.com/", "runSearchAndAssignToUser"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1, string_2, arrayOfSearchParameter_3, new java.lang.Integer(int_4), string_5});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bis.webservice.search.types.SearchResultBean) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bis.webservice.search.types.SearchResultBean) org.apache.axis.utils.JavaUtils.convert(_resp, com.bis.webservice.search.types.SearchResultBean.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.ServerErrorException) {
              throw (com.bis.webservice.search.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.InvalidArgumentException) {
              throw (com.bis.webservice.search.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.DataNotFoundException) {
              throw (com.bis.webservice.search.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.bis.webservice.search.types.SearchResultBean runSearchById(java.lang.String string_1, long long_2, com.bis.webservice.search.types.SearchParameter[] arrayOfSearchParameter_3, int int_4) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://search.webservice.bis.com/", "runSearchById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1, new java.lang.Long(long_2), arrayOfSearchParameter_3, new java.lang.Integer(int_4)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bis.webservice.search.types.SearchResultBean) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bis.webservice.search.types.SearchResultBean) org.apache.axis.utils.JavaUtils.convert(_resp, com.bis.webservice.search.types.SearchResultBean.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.ServerErrorException) {
              throw (com.bis.webservice.search.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.InvalidArgumentException) {
              throw (com.bis.webservice.search.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.DataNotFoundException) {
              throw (com.bis.webservice.search.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public com.bis.webservice.search.types.SearchResultBean runSearchByName(java.lang.String string_1, java.lang.String string_2, com.bis.webservice.search.types.SearchParameter[] arrayOfSearchParameter_3, int int_4) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://search.webservice.bis.com/", "runSearchByName"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1, string_2, arrayOfSearchParameter_3, new java.lang.Integer(int_4)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bis.webservice.search.types.SearchResultBean) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bis.webservice.search.types.SearchResultBean) org.apache.axis.utils.JavaUtils.convert(_resp, com.bis.webservice.search.types.SearchResultBean.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.ServerErrorException) {
              throw (com.bis.webservice.search.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.InvalidArgumentException) {
              throw (com.bis.webservice.search.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.DataNotFoundException) {
              throw (com.bis.webservice.search.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void setSearchResultFormatter(java.lang.String string_1, long long_2, com.bis.webservice.search.types.SearchColumnBean[] arrayOfSearchColumnBean_3) throws java.rmi.RemoteException, com.bis.webservice.search.types.ServerErrorException, com.bis.webservice.search.types.InvalidArgumentException, com.bis.webservice.search.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://search.webservice.bis.com/", "setSearchResultFormatter"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1, new java.lang.Long(long_2), arrayOfSearchColumnBean_3});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.ServerErrorException) {
              throw (com.bis.webservice.search.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.InvalidArgumentException) {
              throw (com.bis.webservice.search.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.search.types.DataNotFoundException) {
              throw (com.bis.webservice.search.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
