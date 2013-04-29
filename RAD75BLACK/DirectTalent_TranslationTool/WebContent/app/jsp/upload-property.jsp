<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.2//EN"
"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd">
<html>
<head>
   <title>Manpower, Inc.</title>
   <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
   <link id="portalStyles" href="../css/styles_002.css" rel="styleSheet" type="text/css">
   <link rel="stylesheet" type="text/css" href="../css/tundra.css">
   <link type="text/css" href="../css/styles.css" rel="stylesheet">
   
   <script type="text/javascript" src="../js/jquery-1.4.2.min.js"></script>
   <script type="text/javascript" src="../js/jquery-ui-1.8.2.min.js"></script>
   <sj:head jqueryui="true"/>

   <script type="text/javascript">
      function checkIfMessage(){
         if ('${message}' !== ""){
            alert('${message}');
         }
      }
   </script>
</head>

<body class="wptheme-mainbody tundra" onload="checkIfMessage();">
   <div id="FLYParent" class="wptheme-FLYParent">
      <jsp:include page="header.jsp"/>
      <s:actionerror />
      <s:fielderror />
      
<!--      <form action="doUpload" method="POST" enctype="multipart/form-data">-->
<!--         <table>-->
<!--            <tr>-->
<!--               <td colspan="2">-->
<!--               <h1>Property File Import Page</h1>-->
<!--               </td>-->
<!--            </tr>-->
<!--            <s:file name="upload" label="File" />-->
<!--            <s:submit />-->
<!--         </table>-->
<!--      </form>-->
<!--      -->
      <form action="importProperty" method="POST" enctype="multipart/form-data">
         <table>
            <tr>
               <td colspan="2"><h1>Property File Import Page</h1></td>
            </tr>
            <tr>
               <td class="tdLabel"><label for="upload" class="label">File:</label></td>
               <td>
                  <input type="file" name="upload" value="" id="upload"/>
                  <input type="submit" id="" value="Submit"/>
               </td>
            </tr>
         </table>
      </form>
      
      
   </div>
</body>

</html>