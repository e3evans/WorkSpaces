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

<portlet:renderURL var="renderUrl">
    <portlet:param name="myaction" value=""/>
</portlet:renderURL>

<portlet:actionURL var="statusUrl">
    <portlet:param name="myaction" value="status"/>
</portlet:actionURL>

<% %>
<html>
    <head>

        <link href="<%=request.getContextPath()%>/css/jquery-calendar.css" rel="stylesheet" type="text/css" />
       <!-- <link href="<%=request.getContextPath()%>/themes/base/jquery.ui.all.css" rel="stylesheet" type="text/css" />-->
        <link href="<%=request.getContextPath()%>/css/ui-lightness/jquery-ui-1.8.23.custom.css" rel="stylesheet" type="text/css" />

        <style type="text/css">
            #calendar_div{
                z-index: 2000;
            }
            .deletebutn{
                background-image: url('<%=request.getContextPath()%>/images/delete.png');  
                background-color: transparent;
                border: none;
                background-repeat: no-repeat;
                cursor: pointer; 
                height: 17px;
                width: 17px;
            }
            .error{
                color:red;
            }

            .polls .poll-heading{

                background-color: #3399FF;

                color: #FFFFFF;
                font-size: 20px;
                font-weight: bold;

                text-align: center;

            }
            .polls h3
            {
                text-align:left;
                color:#5E5E5F;
                margin: 10px 0px 5px 0;
                font-size: 0.9em;
            }
            .pollquestion{
                border: 1px solid #CCCCCC;
                color: #5D5D5D;
                font-family: arial,sans-serif;
                font-size: 17px;
                font-weight: bold;
                height: 25px;
                padding-left: 4px;
                padding-top: 4px;
                width: 333px;
            }
            .optionfield{
                border: 1px solid #CCCCCC;
                color: #5D5D5D;
                font-family: arial,sans-serif;

                /*                font-weight: bold;
                                padding-left: 4px;*/
                padding-top: 4px;


            }
            .calendarFocus{
                border:1px solid #CCC;
                width: 150px;
                height: 25px;
                padding-left: 4px;
                padding-top: 4px;
            }
            .ui-dialog .ui-dialog-title {
                color: #FFFFFF;
                float: left;
                font-weight: bold;
                margin: 0.1em 16px 0.1em 0;


            }
            /*.buttons{

                              background-color: #E78F08;
                               border-radius: 1.1em;
                               color: white;
                               font-weight: bold;
                               padding: 7px 7px 7px 7px;


               -moz-transition: all 0.5s ease 0s;
               background: none repeat scroll 0 0 #928A76;
               border: 1px solid #928A76;
               border-radius: 5px 5px 5px 5px;
               box-shadow: 0 3px 0 #696251;
               color: #FFFFFF;
               cursor: pointer;
               font-family: Tahoma,Helvetica,Arial,sans-serif;
               font-size: 16px;
               font-weight: bold;
               padding: 2px 5px;
               text-shadow: -1px -1px 0 #928A76;
               width: 148px;
           }*/
            .btn{
                background: none repeat scroll 0 0 #4D90FE;
                border: 0 none;
                color: #FFFFFF;
                font-weight: bold;
                width:100px;
                padding: 5px 10px;
                position: relative;
            }
            .create-btn{
                width:auto;
            }
            .add-ch-btn{left:210px;}
            .quesiondisplay{
                color: #5E5E5F;
                border: 1px solid #CFCFCF;
                box-shadow: 5px 5px 5px #CFCFCF;
                padding: 10px;
                margin-top: 10px;
            }

            .question {
                width: 80%;
                float: left;
                position: relative;
            }
            .quesiondisplay .status {
                background: #ECEDEF;
                margin-top: 10px;
                padding: 2px 0;
            }

            .ui-widget-header {
                border: 1px solid #4D90FE;
                background: #4D90FE;
            }
            .ui-dialog{ box-shadow:5px 5px 5px #CFCFCF;border:1px solid #CFCFCF;}

            .ui-dialog .ui-dialog-titlebar-close { display:none;}
            .lno{border:none;}
        </style>


        <script src="<%=request.getContextPath()%>/js/jquery-1.8.0.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery-ui-1.8.23.custom.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.validate.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery-calendar.js"></script>
        <!--<script src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script>-->
        <script type="text/javascript">
            $(document).ready(function () {
               
                var flag = 1;
               
                $('#dialog_link').click(function()
                {
                    var today = new Date();
                    var d = today.getDate();
                    var m = today.getMonth();
                    var y = today.getFullYear();
                    
                    $("#validateForm").validate();
                    $('.calendarFocus').calendar({minDate:new Date(y,m,d)});
              
                    hasLoaded = true;
                    
                    if(flag === 1){
                        flag = 0;
                        addPollChoiceRow_Table();
                    }
                    
                    $('#dialog').dialog('open');
                
                    return false;
                });		
                $('#dialog').dialog({
                    autoOpen: false,                   
                    
                    buttons: 
                        {
                        "Submit": function() 
                        { 				
                            $('#validateForm').submit(); 
                                                                
                            return False();
                            $(this).dialog("close");
						
                        }, 
                        "Cancel": function() 
                        { 
                             $("#validateForm").find('input.pollquestion, input.optionfield, input.calendarFocus').val('');  
                            $("label").remove(".error");
                            $("#pollchoice").find("tr:gt(1)").remove();
                            $(".deletebutn").hide();
                            $(this).dialog("close"); 
                        } 
						
                    }
                });
                $( "#dialog" ).dialog( "option", "width", 'auto' );                
                 
            });
            
            function checkDate(){
            
                var fullDate = new Date()
                var twoDigitMonth = ((fullDate.getMonth().length+1) === 1)? (fullDate.getMonth()+1) : '0' + (fullDate.getMonth()+1);
                var currentDate = fullDate.getDate() + "/" + twoDigitMonth + "/" + fullDate.getFullYear();
                
                var expiredDate=document.getElementById("exdate").value;

                if( (new Date(expiredDate).getTime() > new Date(currentDate).getTime()))
                {
                    alert("Valid Date");
                }else{
                    alert("In-Valid Date");
                }
            
            }
            
            
            function changeStatus(currentButtonId){
                
                var currentStatus = $("."+currentButtonId).val();
                if(currentStatus == "InActive"){
                   
                    $("."+currentButtonId).val("Active");
                    $('#qid'+currentButtonId).fadeTo('fast',.3);
                   
                }else if(currentStatus == "Active"){
                   
                    $("."+currentButtonId).val("InActive");
                    $('#qid'+currentButtonId).fadeTo('fast',1.5);
                   
                }
               
                document.getElementById('status').value=currentStatus;
                document.getElementById('pollid').value=currentButtonId;
                document.getElementById('formid').value="statusform";
                $('#hideenform').submit(); 
                
            }
            
		
            var TABLE_NAME = 'pollchoice'; // this should be named in the HTML
          
            var ROW_BASE = 1;
            var resoncode;
            var urllist = '';
            var appextenderarray = [];
            var attacharray = [];
            var hasLoaded = false;
            var apxlength =0;
            
           
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
            function addPollChoiceRow_Table(num)
            {
                             
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



                    // cell 0 - input text
                    var cell0 = row.insertCell(0);
                    var textNode = document.createElement('input');
                    textNode.setAttribute('type', 'text');
                    textNode.setAttribute('name', 'lno' + iteration);
                    textNode.setAttribute('size', '1');
                    textNode.setAttribute('value', iteration); // iteration included for debug purposes
                    textNode.setAttribute('ReadOnly','true');
                    textNode.setAttribute('class','lno');
                    cell0.appendChild(textNode);
                    
                    // cell 1 - input choice
                    var cell1 = row.insertCell(1);
                    var txtInp = document.createElement('input');
                    txtInp.setAttribute('type', 'text');
                    txtInp.setAttribute('name', 'choice' + iteration);
                    txtInp.setAttribute('size', '45');
                    txtInp.setAttribute('class','required optionfield');
                    cell1.appendChild(txtInp);

                    // cell 2 - delete button
                    var cell2 = row.insertCell(2);
                    var btnEl = document.createElement('input');
                    btnEl.setAttribute('type', 'button');
                    // btnEl.setAttribute('value', 'Delete');
                    btnEl.setAttribute('class','deletebutn');
                    btnEl.onclick = function () {deletePollChoiceCurrentRow(this)};
                    cell2.appendChild(btnEl);

                    row.myRow = new myRowObject(textNode, txtInp);
                    if(tbl.tBodies[0].rows.length>2){
                        $(".deletebutn").show();   
                    }else{
                        $(".deletebutn").hide();
                    }
                }
            }

            function deletePollChoiceCurrentRow(obj)
            {
                  
                if (hasLoaded) {
                    var delRow = obj.parentNode.parentNode;
                    var tbl = delRow.parentNode.parentNode;
                    var rIndex = delRow.sectionRowIndex;

                    var rowArray = new Array(delRow);
                    if(tbl.tBodies[0].rows.length>2)
                        deleteRows(rowArray);
                    reorderPollChoiceRows(tbl, rIndex);
                    if(tbl.tBodies[0].rows.length>2){
                        $(".deletebutn").show();   
                    }else{
                        $(".deletebutn").hide();
                    }
                }
				
            }

           
            function reorderPollChoiceRows(tbl, startingIndex)
            {

                if (hasLoaded) {
                    if (tbl.tBodies[0].rows[startingIndex]) {
                        var count = startingIndex+ ROW_BASE;

                        for (var i=startingIndex; i<tbl.tBodies[0].rows.length; i++) {
                           
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



    </head>
    <body>
        <div id="dialog" title="CreateNewPoll" >  
            <form action="${actionUrl}" method="POST" id="validateForm">


                <div class="polls">
                    <!--  <div class="poll-heading">Create New Poll</div>-->
                    <div id ="pollquestindiv" >
                        <h3>Poll Question</h3>
                        <input type="text" name="pollQ" size="50" class="pollquestion required"/>
                        <h3>Expire Date:</h3>
                        <input type="text" size="10" id="exdate" class="calendarFocus required" name="Edate" readonly="readonly"/>
                        <div class="pollchoicediv"><h3>Poll Choices</h3>
                            <table  id ="pollchoice" cellspacing="3" cellpadding="0" border="0">
                                <tbody>
                                    <tr id ="classy1">
                                        <td><input type ="text" name ="lno" size="1" value ="1" ReadOnly="True" class="lno"/></td>
                                        <td><input type ="text" name ="choice1" size="45" class="required optionfield"/></td>
                                        <td width="30px"><input type="button" class="deletebutn" onclick ="deletePollChoiceCurrentRow(this);"/></td>
                                    </tr>

                                </tbody>
                            </table>


                        </div>

                        <input type="button" name ="add" value ="AddChoice" class="btn add-ch-btn buttons" onClick ="addPollChoiceRow_Table();"/><br/><br/><br/>

                    </div>	
                    <input type="hidden" value="validateForm" name="formname"/>
                    <div class="addpolldiv">
                        <table width="100%" cellpadding="0" cellspacing="0" border="0">
                            <tr><td></td></tr>
                        </table>

                    </div>

                    <div></div>
                </div> 
            </form>
        </div>

        <input type="button" id="dialog_link" value="CreateNewPoll" class="btn create-btn buttons"/>
        <div class="quesiondisplay">

            <c:forEach var="questions" items="${questionsdata}">

                <div id="${questions.questionID}"> 
                    <div class="question">
                        <c:choose> 
                            <c:when test="${(questions.status == 'Active')}" >
                                <p id="qid${questions.questionID}"><c:out value="${questions.question}" /></p>
                            </c:when>
                            <c:otherwise>
                                <p id="qid${questions.questionID}" style="opacity:.3;"><c:out value="${questions.question}" /></p> 
                            </c:otherwise>
                        </c:choose>

                    </div>
                    <div class="status">
                        <c:choose> 
                            <c:when test="${(questions.status == 'Active')}" >
                                <input type="button" class="btn ${questions.questionID}" value="InActive" name="${questions.questionID}" onclick="changeStatus('${questions.questionID}');" />
                            </c:when>
                            <c:otherwise>
                                <input type="button" class="btn ${questions.questionID}" value="Active" name="${questions.questionID}" onclick="changeStatus('${questions.questionID}');" />
                            </c:otherwise>
                        </c:choose>    
                    </div>

                </div>
            </c:forEach>
            
            <form action="${statusUrl}" method="post" id="hideenform">

                <input type="hidden" value="" id="status" name="status"/>
                
                <input type="hidden" value="" id="pollid" name="pollid"/>
                
                <input type="hidden" value="" id="formid" name="formname"/>

            </form>

        </div>
    </body>
</html>
