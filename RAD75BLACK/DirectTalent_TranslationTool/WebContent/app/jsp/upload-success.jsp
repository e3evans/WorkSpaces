<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.2//EN"
"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd">
<html>
<head>
   <title>Manpower, Inc.</title>
   <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
   <link id="portalStyles" href="../css/styles_002.css" rel="styleSheet" type="text/css">
   <link rel="stylesheet" type="text/css" href="../css/tundra.css">
   <link type="text/css" href="../css/styles.css" rel="stylesheet">
</head>

<body class="wptheme-mainbody tundra" >
   <div id="FLYParent" class="wptheme-FLYParent">
      <jsp:include page="header.jsp"/>
      <table>
         <tr>
            <td colspan="2">
            <h1>Property File Import Succeed</h1>
            </td>
         </tr>
      
         <tr>
            <td class="tdLabel">
               <label for="doUpload_upload" class="label">
                  Content Type:
               </label>
            </td>
            <td><s:property value="uploadContentType" /></td>
         </tr>
      
         <tr>
            <td class="tdLabel">
               <label for="doUpload_upload" class="label">
                  File Name:
               </label>
            </td>
            <td><s:property value="uploadFileName" /></td>
         </tr>
      
      
         <tr>
            <td class="tdLabel">
               <label for="doUpload_upload" class="label">
                  File:
               </label>
            </td>
            <td><s:property value="upload" /></td>
         </tr>
      </table>
   </div>
</body>
</html>