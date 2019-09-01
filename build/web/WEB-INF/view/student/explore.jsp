<%-- 
    Document   : explore
    Created on : 12 Jul, 2017, 11:14:25 PM
    Author     : HARIOM
--%>



<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="now" class="java.util.Date"/>
<!DOCTYPE html5>
<html>
    <head>

        <meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8" />
        <title>Explore</title>
        <link rel="stylesheet" href="/FastGrasp/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="/FastGrasp/css/main.css" />
        <link rel="stylesheet" href="/FastGrasp/css/explore.css" />
        <script type="text/javascript" src="/FastGrasp/js/jquery-3.2.1.js"></script>
        <script type="text/javascript" src="/FastGrasp/js/main.js"></script>
        <script type="text/javascript" src="/FastGrasp/js/explore.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('input[type="radio"]').click(function(){
                    if(this.value!=="all")
                     window.open("<c:url value='explore?'/>"+this.value,"_self");
                    else window.open("explore","_self");
                });
            });
        </script>

    </head>

    <body>

        <div id="exploreSection1">
            <table id="courseCategory" class="category selection">
                <tr>
                    <td colspan="2" class="linkLogo">
                        <span class="glyphicon glyphicon-list-alt "></span> COURSE CATEGORY
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <c:choose>
                            <c:when test="${empty selectedCategory}">
                                <input type="radio" name="course" value="all" checked>
                            </c:when>
                            <c:otherwise>
                                <input type="radio" name="course" value="all">
                            </c:otherwise>
                        </c:choose>
                        All</td>
                </tr>


                <c:forEach var="category" items="${categories}" varStatus="iter">
                    <c:if test="${iter.index%2==0}">
                        <tr>
                            <td>
                                <c:choose>
                                    <c:when test="${selectedCategory.id==category.id}">
                                        
                                            <input type="radio" name="course" value="${category.id}" checked>
                                        
                                    </c:when>
                                    <c:otherwise>
                                        
                                            <input type="radio" name="course" value="${category.id}">
                                        
                                    </c:otherwise>
                                </c:choose> ${category.name}</td>
                            </c:if>        
                            <c:if test="${iter.index%2!=0}">

                            <td><c:choose>
                                    <c:when test="${selectedCategory.id==category.id}">
                                        
                                            <input type="radio" name="course" value="${category.id}" checked>
                                        
                                    </c:when>
                                    <c:otherwise>
                                        
                                            <input type="radio" name="course" value="${category.id}">
                                        
                                    </c:otherwise>
                                </c:choose> ${category.name}</td>
                        </tr>
                    </c:if>
                    <c:if test="${iter.last && iter.index%2==0}">

                        <td></td>
                        </tr>
                    </c:if>    
                </c:forEach>  
            </table>
        </div>
        
        <div id="exploreSection2">
            <table id="runningCourses" class="category selection">
                <tr>
                    <td colspan="2" class="linkLogo">
                        <span class="glyphicon glyphicon-play "></span> RUNNING COURSES
                    </td>
                </tr>
                <c:forEach var="course" items="${categoryCourses}">
                   
                    <c:if test="${course.startDate le now}">
                <tr>
                    <td colspan="2"> 
                        <a href="<c:url value='explore/courseDetails?courseId=${course.id}'/>">${course.title}</a></td>
                </tr>
                    </c:if>     
                </c:forEach>
                
            </table>
        </div>
        <div id="exploreSection3">
            <table id="upcomingCourses" class="category selection">
                <tr>
                    <td colspan="2" class="linkLogo">
                        <span class="glyphicon glyphicon-step-forward "></span> UPCOMING COURSES
                    </td>
                </tr>
                
                
                <c:forEach var="course" items="${categoryCourses}">
                    <c:if test="${course.startDate gt now}">
                <tr>
                    <td colspan="2"> <a href="<c:url value='explore/courseDetails?courseId=${course.id}'/>">${course.title}</a></td>
                </tr>
                    </c:if>     
                </c:forEach>
                
            </table>
        </div>
        <div class="footer">
            <p>© 2017, FastGrasp.com, Inc. or its affiliates</p>
        </div>
    </body>
</html>