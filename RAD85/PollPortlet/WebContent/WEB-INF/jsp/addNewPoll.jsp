<%-- 
    Document   : addNewPoll
    Created on : 29 Aug, 2012, 6:01:06 PM
    Author     : praveen.ka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<portlet:defineObjects />

<portlet:actionURL var="actionUrl">
    <portlet:param name="myaction" value="showPoll"/>
</portlet:actionURL>

<% %>
<html>
    <head>

        <link href="<%=request.getContextPath()%>/css/jquery-calendar.css" rel="stylesheet" type="text/css" />
        <style type="text/css">

            .polls
            {	
                border:1px solid #4D90FE;
                width: 500px;

                box-shadow:2px 3px 10px rgba(0, 0, 0, 0.3) inset; 
            }
            #pollquestindiv
            {
                /*text-align:center;*/
                margin: 9px 20px;
            }	
            .polls .poll-heading{
                background: #4D90FE;
               
                color: #FFFFFF;
                font-size: 25px;
                font-weight: bold;

                padding: 5px 10px;
                text-align: center;

            }
            .polls h3
            {
                text-align:left;
                color:#7F7F7F;
            }
            .pollquestion
            {
               /* height: 5px;*/
                padding: 6px 0px 0px 0px;
                /*width: 480px;*/

            }

            .buttndiv
            {
                text-align: center;
            }
            .buttons{
                background:#68A0E3;
                /*padding: 5px 15px;*/
                border: 1px solid #4D90FE;
                color: #FFFFFF;
                margin:5px;
               /* float:left;*/
            }

            /*.pollchoicediv{
                margin: 9px 10px; 
            }*/

        </style>


        <!-- <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>-->
        <!-- <script type="text/javascript" src="jquery-calendar.js"></script>-->
        <script src="<%=request.getContextPath()%>/js/jquery-1.7.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery-calendar.js"></script>

        <script type="text/javascript">
            $(document).ready(function () {
			
                $('.calendarFocus').calendar();
            });
		
            var TABLE_NAME = 'explanation'; // this should be named in the HTML
          
            var ROW_BASE = 1;
            var resoncode;
            var urllist = '';
            var appextenderarray = [];
            var attacharray = [];
            var hasLoaded = false;
            var apxlength =0;
            window.onload=fillInRows;
            // document.domain='localhost';
            // window.close=deleteAllCookies;
            function fillInRows()
            {
                hasLoaded = true;
                // addRowTo_AdjustmentDetail_Table();
                // addSupporting_Table();
            }
            function deleteRows(rowObjArray)
            {
               
                if (hasLoaded) {
                    for (var i=0; i<rowObjArray.length; i++) {
                        var rIndex = rowObjArray[i].sectionRowIndex;

                        rowObjArray[i].parentNode.deleteRow(rIndex);
                    }
                }
            }
            function myRowObject(one, two)
            {
                this.one = one; // text object
                this.two = two; // input text object
            }
            function addExplanationRow_Table(num)
            {
                alert("in add func...");
                   
                if (hasLoaded) {
                    var tbl = document.getElementById(TABLE_NAME);
                    var nextRow = tbl.tBodies[0].rows.length;
					
                    var iteration = nextRow+ROW_BASE;

                    if (num == null) {

                        num = nextRow;
                    } else {

                        iteration = num + ROW_BASE;
                    }

                    // add the row
                    var row = tbl.tBodies[0].insertRow(num);

                    // CONFIG: requires classes named classy0 and classy1
                    row.className = 'classy' + (iteration % 2);



                    // cell 1 - input text
                    var cell0 = row.insertCell(0);
                    var textNode = document.createElement('input');
                    textNode.setAttribute('type', 'text');
                    textNode.setAttribute('name', 'lno' + iteration);
                    textNode.setAttribute('size', '1');
                    textNode.setAttribute('value', iteration); // iteration included for debug purposes
                    textNode.setAttribute('ReadOnly','true');
                    cell0.appendChild(textNode);

                    var cell1 = row.insertCell(1);
                    var txtInp = document.createElement('input');
                    txtInp.setAttribute('type', 'text');
                    txtInp.setAttribute('name', 'choice' + iteration);
                    txtInp.setAttribute('size', '50');
                    //txtInp.setAttribute('value', iteration); // iteration included for debug purposes
                    cell1.appendChild(txtInp);

                    // cell 2 - input button
                    var cell2 = row.insertCell(2);
                    var btnEl = document.createElement('input');
                    btnEl.setAttribute('type', 'button');
                    btnEl.setAttribute('value', 'Delete');
                    btnEl.onclick = function () {deleteExplanationCurrentRow(this)};
                    cell2.appendChild(btnEl);

                    row.myRow = new myRowObject(textNode, txtInp);
                }
            }

            function deleteExplanationCurrentRow(obj)
            {
                  
                if (hasLoaded) {
                    var delRow = obj.parentNode.parentNode;
                    var tbl = delRow.parentNode.parentNode;
                    var rIndex = delRow.sectionRowIndex;

                    var rowArray = new Array(delRow);
                    if(tbl.tBodies[0].rows.length>2)
                        deleteRows(rowArray);
                    reorderExplanationRows(tbl, rIndex);
                }
				
                length();
            }

            function length(){
			
                //alert(document.getElementById("explanation").getElementsByTagName("tr").length);
                //	alert("row deleted");
            }
            function reorderExplanationRows(tbl, startingIndex)
            {

                if (hasLoaded) {
                    if (tbl.tBodies[0].rows[startingIndex]) {
                        var count = startingIndex+ ROW_BASE;

                        for (var i=startingIndex; i<tbl.tBodies[0].rows.length; i++) {

                            // CONFIG: next line is affected by myRowObject settings
                            //		tbl.tBodies[0].rows[i].myRow.one.data = count; // text
                            tbl.tBodies[0].rows[i].myRow.one.name = 'lno' + count;
                            tbl.tBodies[0].rows[i].myRow.one.value = count;

                            // CONFIG: next line is affected by myRowObject settings
                            tbl.tBodies[0].rows[i].myRow.two.name = 'choice' + count; // input text

                            // CONFIG: requires class named classy0 and classy1
                            tbl.tBodies[0].rows[i].className = 'classy' + (count % 2);

                            count++;
                        }
                    }
                }
            }
        </script>

        <!--<link rel="stylesheet" href="jquery-calendar.css" />-->


    </head>
    <body>
        <form action="${actionUrl}" method="POST">


            <div class="polls">
                <div class="poll-heading">Add New Poll</div>
                <div id ="pollquestindiv" >
                    <h3>Type Your Poll Question</h3>
                    <input type="text" name="pollQ" size="50" class="pollquestion"/>
                    
                         <div class="pollchoicediv"><h3>Poll Choices</h3>
                    <table width="100%" id ="explanation" cellspacing="3" cellpadding="0" border="0">
                        <tbody>
                            <tr id ="classy1">
                                <td><input type ="text" name ="lno" size="1" value ="1" ReadOnly="True"/></td>
                                <td><input type ="text" name ="choice1" size="50" /></td>
                                <!-- <td width="30px"><input type="button" value ="Delete" onclick ="deleteExplanationCurrentRow(this);"/></td>-->
                            </tr>
                            <tr id ="classy0">
                                <td><input type ="text" name ="lno" size="1" value ="2" ReadOnly="True"/></td>
                                <td><input type ="text" name ="choice2" size="50" /></td>
                                <!--<td width="30px"><input type="button" value ="Delete" onclick ="deleteExplanationCurrentRow(this);"/></td>-->
                            </tr>
                        </tbody>
                    </table>


                </div>
                    
                    <input type="button" name ="add" value ="AddChoice" class="buttons" onClick ="addExplanationRow_Table();"/><br/><br/><br/>
                    Expire Date: <input type="text" size="10" class="calendarFocus" name="Edate"/>
                </div>	
           
                <div class="addpolldiv">
                    <table width="100%" cellpadding="0" cellspacing="0" border="0">
                        <tr><td ></td></tr>
                    </table >

                </div>
                


                <div class="buttndiv">
                    <input type="submit" class="buttons" name="login" value="Save"/>
                    <a href="
                       <portlet:renderURL> 
                           <portlet:param name="myaction" value=""/>

                       </portlet:renderURL>"><img src="<%=request.getContextPath()%>/images/cancel.jpg" /></a>

                </div>
                <div></div>
            </div> 
        </form>
    </body>
</html>
