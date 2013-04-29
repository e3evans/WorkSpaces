/**
 * IEntityAccessBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bis.webservice.entity;

public class IEntityAccessBindingStub extends org.apache.axis.client.Stub implements com.bis.webservice.entity.IEntityAccess {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[13];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteEntity");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "long_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.entity.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.entity.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.entity.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getEntity");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "long_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.entity.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.entity.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.entity.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getEntityFormatted");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "long_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "EntityFormattedDateBean_3"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "EntityFormattedDateBean"), com.bis.webservice.entity.types.EntityFormattedDateBean.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.entity.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.entity.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.entity.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getUserAsEntity");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "long_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.entity.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.entity.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.entity.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getUserAsEntityByADUsername");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "EntityFormattedDateBean_3"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "EntityFormattedDateBean"), com.bis.webservice.entity.types.EntityFormattedDateBean.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.entity.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.entity.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.entity.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getUserAsEntityByName");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.entity.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.entity.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.entity.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getUserAsEntityByNameFormatted");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "EntityFormattedDateBean_3"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "EntityFormattedDateBean"), com.bis.webservice.entity.types.EntityFormattedDateBean.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.entity.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.entity.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.entity.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getUserAsEntityFormatted");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "long_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "EntityFormattedDateBean_3"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "EntityFormattedDateBean"), com.bis.webservice.entity.types.EntityFormattedDateBean.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.entity.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.entity.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.entity.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("lockEntity");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "long_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "long_3"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.entity.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.entity.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("removeEntity");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "long_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.entity.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.entity.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.entity.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("saveEntity");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.entity.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.entity.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.entity.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("saveEntityFormatted");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "EntityFormattedDateBean_3"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "EntityFormattedDateBean"), com.bis.webservice.entity.types.EntityFormattedDateBean.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.entity.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "InvalidArgumentException"),
                      "com.bis.webservice.entity.types.InvalidArgumentException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "InvalidArgumentException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.entity.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("unlockEntity");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "long_1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "String_2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "long_3"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "ServerErrorException"),
                      "com.bis.webservice.entity.types.ServerErrorException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "ServerErrorException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "DataNotFoundException"),
                      "com.bis.webservice.entity.types.DataNotFoundException",
                      new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "DataNotFoundException"), 
                      true
                     ));
        _operations[12] = oper;

    }

    public IEntityAccessBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public IEntityAccessBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public IEntityAccessBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "DataNotFoundException");
            cachedSerQNames.add(qName);
            cls = com.bis.webservice.entity.types.DataNotFoundException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "EntityFormattedDateBean");
            cachedSerQNames.add(qName);
            cls = com.bis.webservice.entity.types.EntityFormattedDateBean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "InvalidArgumentException");
            cachedSerQNames.add(qName);
            cls = com.bis.webservice.entity.types.InvalidArgumentException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://entity.webservice.bis.com/types", "ServerErrorException");
            cachedSerQNames.add(qName);
            cls = com.bis.webservice.entity.types.ServerErrorException.class;
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

    public void deleteEntity(java.lang.String string_1, long long_2) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "deleteEntity"));

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
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.ServerErrorException) {
              throw (com.bis.webservice.entity.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.InvalidArgumentException) {
              throw (com.bis.webservice.entity.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.DataNotFoundException) {
              throw (com.bis.webservice.entity.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String getEntity(java.lang.String string_1, long long_2) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "getEntity"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1, new java.lang.Long(long_2)});

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
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.ServerErrorException) {
              throw (com.bis.webservice.entity.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.InvalidArgumentException) {
              throw (com.bis.webservice.entity.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.DataNotFoundException) {
              throw (com.bis.webservice.entity.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String getEntityFormatted(java.lang.String string_1, long long_2, com.bis.webservice.entity.types.EntityFormattedDateBean entityFormattedDateBean_3) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "getEntityFormatted"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1, new java.lang.Long(long_2), entityFormattedDateBean_3});

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
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.ServerErrorException) {
              throw (com.bis.webservice.entity.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.InvalidArgumentException) {
              throw (com.bis.webservice.entity.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.DataNotFoundException) {
              throw (com.bis.webservice.entity.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String getUserAsEntity(java.lang.String string_1, long long_2) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "getUserAsEntity"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1, new java.lang.Long(long_2)});

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
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.ServerErrorException) {
              throw (com.bis.webservice.entity.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.InvalidArgumentException) {
              throw (com.bis.webservice.entity.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.DataNotFoundException) {
              throw (com.bis.webservice.entity.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String getUserAsEntityByADUsername(java.lang.String string_1, java.lang.String string_2, com.bis.webservice.entity.types.EntityFormattedDateBean entityFormattedDateBean_3) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "getUserAsEntityByADUsername"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1, string_2, entityFormattedDateBean_3});

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
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.ServerErrorException) {
              throw (com.bis.webservice.entity.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.InvalidArgumentException) {
              throw (com.bis.webservice.entity.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.DataNotFoundException) {
              throw (com.bis.webservice.entity.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String getUserAsEntityByName(java.lang.String string_1, java.lang.String string_2) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "getUserAsEntityByName"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1, string_2});

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
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.ServerErrorException) {
              throw (com.bis.webservice.entity.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.InvalidArgumentException) {
              throw (com.bis.webservice.entity.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.DataNotFoundException) {
              throw (com.bis.webservice.entity.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String getUserAsEntityByNameFormatted(java.lang.String string_1, java.lang.String string_2, com.bis.webservice.entity.types.EntityFormattedDateBean entityFormattedDateBean_3) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "getUserAsEntityByNameFormatted"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1, string_2, entityFormattedDateBean_3});

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
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.ServerErrorException) {
              throw (com.bis.webservice.entity.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.InvalidArgumentException) {
              throw (com.bis.webservice.entity.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.DataNotFoundException) {
              throw (com.bis.webservice.entity.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String getUserAsEntityFormatted(java.lang.String string_1, long long_2, com.bis.webservice.entity.types.EntityFormattedDateBean entityFormattedDateBean_3) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "getUserAsEntityFormatted"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1, new java.lang.Long(long_2), entityFormattedDateBean_3});

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
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.ServerErrorException) {
              throw (com.bis.webservice.entity.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.InvalidArgumentException) {
              throw (com.bis.webservice.entity.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.DataNotFoundException) {
              throw (com.bis.webservice.entity.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void lockEntity(long long_1, java.lang.String string_2, long long_3) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "lockEntity"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Long(long_1), string_2, new java.lang.Long(long_3)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.ServerErrorException) {
              throw (com.bis.webservice.entity.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.DataNotFoundException) {
              throw (com.bis.webservice.entity.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void removeEntity(java.lang.String string_1, long long_2) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "removeEntity"));

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
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.ServerErrorException) {
              throw (com.bis.webservice.entity.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.InvalidArgumentException) {
              throw (com.bis.webservice.entity.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.DataNotFoundException) {
              throw (com.bis.webservice.entity.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String saveEntity(java.lang.String string_1, java.lang.String string_2) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "saveEntity"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1, string_2});

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
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.ServerErrorException) {
              throw (com.bis.webservice.entity.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.InvalidArgumentException) {
              throw (com.bis.webservice.entity.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.DataNotFoundException) {
              throw (com.bis.webservice.entity.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String saveEntityFormatted(java.lang.String string_1, java.lang.String string_2, com.bis.webservice.entity.types.EntityFormattedDateBean entityFormattedDateBean_3) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.InvalidArgumentException, com.bis.webservice.entity.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "saveEntityFormatted"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {string_1, string_2, entityFormattedDateBean_3});

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
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.ServerErrorException) {
              throw (com.bis.webservice.entity.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.InvalidArgumentException) {
              throw (com.bis.webservice.entity.types.InvalidArgumentException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.DataNotFoundException) {
              throw (com.bis.webservice.entity.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void unlockEntity(long long_1, java.lang.String string_2, long long_3) throws java.rmi.RemoteException, com.bis.webservice.entity.types.ServerErrorException, com.bis.webservice.entity.types.DataNotFoundException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://entity.webservice.bis.com/", "unlockEntity"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Long(long_1), string_2, new java.lang.Long(long_3)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.ServerErrorException) {
              throw (com.bis.webservice.entity.types.ServerErrorException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof com.bis.webservice.entity.types.DataNotFoundException) {
              throw (com.bis.webservice.entity.types.DataNotFoundException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
