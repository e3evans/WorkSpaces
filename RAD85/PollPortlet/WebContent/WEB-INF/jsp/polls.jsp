<%-- 
    Document   : polls
    Created on : 29 Aug, 2012, 5:41:39 PM
    Author     : praveen.ka
--%>



<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>

<%@page import="com.eblue.springpoll.domain.VoteCount"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html" isELIgnored="false" %>

<portlet:actionURL var="radiobutn">
    <portlet:param name="myaction" value="votedate"/>

</portlet:actionURL>
<html>
    <head>
<!--       <link href="<%=request.getContextPath()%>/css/demos.css" rel="stylesheet" type="text/css" />-->
        <link href="<%=request.getContextPath()%>/themes/base/jquery.ui.all.css" rel="stylesheet" type="text/css" />
<!--         <img src="<%=request.getContextPath()%>/images/pbar-ani.gif"/>-->
        <script src="<%=request.getContextPath()%>/js/jquery-1.7.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/ui/jquery.ui.core.js"></script>
        <script src="<%=request.getContextPath()%>/js/ui/jquery.ui.widget.js"></script>
        <script src="<%=request.getContextPath()%>/js//ui/jquery.ui.progressbar.js"></script>

        <style type="text/css">
            .ui-progressbar-value { background-image:  url(<%=request.getContextPath()%>/images/pbar-ani.gif); }

            .polls_form, .VotedPolls {
                border: 1px solid #CFCFCF;
                color: #5E5E5F;
                font-weight: bold;
                margin-bottom: 10px;
                padding: 10px 5px;
                text-align: left;
                width: 200px;
                box-shadow: 5px 5px 5px #CFCFCF;
            }
            #polldataclass .pollheader p, .VotedPolls p {
                border-bottom: 1px solid #CFCFCF;
                margin: 0 0 15px;
                padding-bottom: 10px;
                text-align: left;
            }
            .ui-corner-all{
                height: 15px;
                margin-top: 5px;
                border-radius:0px;
            }
            .poll-result{
                position:relative;
            }
            .poll-percent{
                position: absolute; 
                right: 0px;   
            }
            .polloptions {
                background: none repeat scroll 0 0 #ECEDEF;
                margin-bottom: 5px;
            }
            .vote-butn {
                background: none repeat scroll 0 0 #4D90FE;
                border: 0 none;
                color: #FFFFFF;
                font-weight: bold;
                left: 70%;
                padding: 5px 10px;
                position: relative;
            }
        </style>
        <script>
            $(function() {
                $(".VotedPolls").children("div.bars").each(function() {
                    $("#"+this.id).progressbar({
                        value: parseInt(this.title)
                    });
                });
            });
             
         
             
        </script>

    </head>
    <body>


        <c:set var="optionsCount" value="0"/>

        <c:set var="questionCount" value="0"/>
        <c:set var="pollivard" value="0" />
        <c:forEach var="voteObj" items="${votesData}" varStatus="row">
            <c:choose>
                <c:when test="${not (pollivard eq voteObj.pollId)}">

                    <c:forEach var="tempPoll" items="${questioncount}">
                        <c:forEach var="entry" items="${tempPoll}" varStatus="status">
                            <fmt:parseNumber var="optionKey" integerOnly="true" type="number" value="${entry.key}" />
                            <c:if test="${optionKey == voteObj.pollId}">
                                <fmt:parseNumber var="countValue" integerOnly="true" type="number" value="${entry.value}" />
                                <c:set var="optionsCount" value="${countValue}"/>
                            </c:if>

                        </c:forEach>

                    </c:forEach>

                    <c:if test="${not(pollivard eq 0)}" >
                        <br/><br/>
                    </div>
                </c:if>
                <c:set var="questionCount" value="${questionCount+1}"/>
                <div id="container" class="VotedPolls paginationIndex${questionCount}">

                    <p><c:out value="${voteObj.questionName}" /></p>
                    <div class="poll-result">
                        <span class="poll-option"><c:out value="${voteObj.optionName}" /></span> 

                        <span class="poll-percent">
                            <fmt:formatNumber value="${((voteObj.count-1)/(pollCounts[voteObj.pollId] -optionsCount))* 100 }" maxFractionDigits="0" />%
                        </span>
                        <c:set var="pollivard" value="${voteObj.pollId}" /></div>
                    <div class="bars" id="${voteObj.optionId}" title="${((voteObj.count-1)/(pollCounts[voteObj.pollId] -optionsCount))* 100 }"></div>

                </c:when>
                <c:otherwise>
                    <div class="poll-result">
                        <span class="poll-option"><c:out value="${voteObj.optionName}" /></span> 
                        <span class="poll-percent">
                            <fmt:formatNumber value="${((voteObj.count-1)/(pollCounts[voteObj.pollId] -optionsCount))* 100 }" maxFractionDigits="0" />%
                        </span>
                    </div>                    
                    <div class="bars" id="${voteObj.optionId}" title="${((voteObj.count-1)/(pollCounts[voteObj.pollId] -optionsCount))* 100 }"></div>

                </c:otherwise>
            </c:choose>
            <br/>



        </c:forEach>
        <c:if test="${not(pollivard eq 0)}" >

        </div>
    </c:if>

    <c:set var="pollid" value="0" />


    <c:forEach var="beanObj" items="${pollData}" varStatus="row">

        <c:choose> 
            <c:when test="${not (pollid eq beanObj.pollId)}" >
                <c:if test="${not(pollid eq 0)}" >
                    <input type="submit" value="vote" class="vote-butn"/>

                    <input type="hidden" value="${pollid}" name="qName"/>
                    <input type="hidden" value="${pollid}" name="qid"/>

                </form>
            </div>
        </c:if>
        <c:set var="pollid" value="${beanObj.pollId}" />

        <c:set var="questionCount" value="${questionCount+1}"/>
        <div class="paginationIndex${questionCount}" id="polldataclass">


            <form action="${radiobutn}" method ="post" class="polls_form">
                <div class="pollheader" name="${beanObj.qname}">
                    <p><c:out value="${beanObj.qname}" /></p>
                </div>


                <div class=" polloptions" id="optionsDiv">
                    <input type="radio" name="${beanObj.pollId}" value="${beanObj.id}"/>                                
                    <c:out value="${beanObj.option}"></c:out>
                    </div>    
            </c:when> 
            <c:otherwise>
                <div class="polloptions" id="optionsDiv">
                    <input type="radio" name="${beanObj.pollId}" value="${beanObj.id}"/>  
                    <c:out value="${beanObj.option}"></c:out>
                    </div> 

            </c:otherwise>  

        </c:choose>

    </c:forEach>
    <c:if test="${not(pollid eq 0)}" >
        <input type="submit" value="vote" class="vote-butn"/>

        <input type="hidden" value="${pollid}" name="qName"/>
        <input type="hidden" value="${pollid}" name="qid"/>

    </form>
</div>
</c:if>   

</body>
</html>


