<%@ page session="false" isELIgnored="false" import="java.util.*,java.util.logging.*,com.ibm.workplace.wcm.api.*,com.ibm.workplace.wcm.api.exceptions.*,com.asponte.commons.*,com.asponte.commons.portal.*,com.asponte.commons.portal.wcm.*,com.asponte.wcm.widgets.*" 
	%><%
	final String METHOD_NAME="dcatAddCollection.jsp";
	boolean isTraceEnabled=Jsp.LOGGER.isLoggable(Level.FINER);
	boolean isDebugEnabled=Jsp.LOGGER.isLoggable(Level.FINEST);
	boolean isErrorEnabled=Jsp.LOGGER.isLoggable(Level.SEVERE);
	if(isTraceEnabled){Jsp.LOGGER.entering(Jsp.CLASS_NAME,METHOD_NAME,new Object[]{request.getParameterMap()});}
	response.setContentType("application/json; charset=ISO-8859-1");		
	boolean success=false;
	List results=new ArrayList();
	String collectionTitle="";
	String catalogId="";
	catalogId=com.asponte.commons.portal.Utils.param(request,"catalogId");
	if(com.asponte.commons.portal.Utils.empty(catalogId)){
		if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": Missing catalog ID parameter (enable trace to see parameter dump)!");}
		results.add(new ActionResult(Result.WARNING,Results.DOCCAT_ADD_MISSING_CATALOG_ID));
	}
	collectionTitle=com.asponte.commons.portal.Utils.param(request,"collectionTitle");
	if(com.asponte.commons.portal.Utils.empty(collectionTitle)){
		if(isErrorEnabled){Jsp.LOGGER.log(Level.SEVERE,METHOD_NAME+": Missing collection title parameter (enable trace to see parameter dump)!");}
		results.add(new ActionResult(Result.WARNING,Results.DOCCAT_ADD_MISSING_COLLECTION_TITLE));
	}
	if(results.isEmpty()){
		WidgetHelper wHelper=WidgetHelper.createWidgetHelper(request,response);	
		Properties props=new Properties();
		props.setProperty("title",collectionTitle);
		success=wHelper.createChild(Constants.DCAT_TEMPLATE_ID,catalogId,props,results);
	}
	if(Jsp.LOGGER.isLoggable(java.util.logging.Level.FINER)){Jsp.LOGGER.finer(METHOD_NAME+": EXIT");}
	boolean jsonResult=success;
	List jsonResults=results;
	%><%@ include file="../includes/jsonResponse.jspf" %>