<%@page import="entity.CourseContent"%>
<%@page import="java.util.Iterator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${selectedCourse.title} Lectures|FastGrasp</title>
        <!-- Bootstrap -->
        <link href="/FastGrasp/css/bootstrap.min.css" rel="stylesheet">

    </head>
    <body style="padding-bottom:50px;">
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="/FastGrasp/js/jquery-1.11.3.min.js"></script>

        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="/FastGrasp/js/bootstrap.js"></script>

        <div class="container">
            <hr/>
            <h4 class="breadcrumb"><a href="#">Course</a> &gt; <a href="#"><span>Introduction to modern application development</span></a> &gt; <a href="#"><span>Lectures</span></a></h4>

            <div class="alert alert-danger alert-dismissable fade in">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <center><strong>Alert! </strong><span class="announcement">This is an alert.</span><br/>
                </center>
            </div>


            <div class="alert alert-info alert-dismissable fade in">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <center><h4>Announcements</h4></center>
                <span class="announcement">1.This is an announcement.</span><br/>
                <span class="announcement">2.This is another announcement.</span><br/>

            </div>



            <hr/>
            <div class="row">
                <div class="col-md-10">
                    <h3>${selectedCourse.title}</h3>
                </div>
                <c:if test="${userType=='faculty' && !empty selectedCourseContent}"><div class="col-md-2" style="padding-top:10px;"><a href="/resource/delete?courseContent=${selectedCourseContent.id}" class="btn btn-danger" style="float:right">Delete Video</a></div>
                </c:if>
            </div>

            <div class="embed-responsive embed-responsive-16by9">
                <c:if test="${!empty selectedCourseContent}">
                    <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/${selectedCourseContent.link}"></iframe>
                    </c:if>
                    <c:if test="${empty selectedCourseContent}">
                        <%
                           
                            Iterator<CourseContent> iterator = (Iterator) session.getAttribute("courseContentsIterator");
                            
                            while (iterator.hasNext()) {

                                CourseContent i = iterator.next();
                                if (i.getType().equalsIgnoreCase("video")) {
                                    out.println("<iframe class=\"embed-responsive-item\" src=\"https://www.youtube.com/embed/" + i.getLink() + "\"></iframe>");

                                    break;
                                }
                            }
                        %>



                </c:if>
            </div>

<c:if test="${userType=='student'}">
            <div class="row">

                <div class="col-md-3 col-md-offset-9" style="padding-top:10px;"><a href="<c:url value='createIssue?courseId=${selectedCourse.id}'/>" class="btn btn-danger" style="float:right">Report Issues</a></div>
            </div>
</c:if>
        </div>
        <br/>
        <div class="container">


            <c:forEach var="courseContent" items="${courseContents}">
                <c:if test="${courseContent.type=='video'}">
                    <c:choose>
                        <c:when test="${selectedCourseContent.id==courseContent.id}">
                            <a href="<c:url value='courseLecture?videoId=${courseContent.id}'/>" class="list-group-item active">
                                <h4 class="list-group-item-heading">${courseContent.title}</h4>
                                <p class="list-group-item-text">Uploaded On:<span class="date">${courseContent.dateCreated}</span></p>
                            </a>
                        </c:when>

                        <c:otherwise>
                            <a href="<c:url value='courseLecture?videoId=${courseContent.id}'/>" class="list-group-item">
                                <h4 class="list-group-item-heading">${courseContent.title}</h4>
                                <p class="list-group-item-text">Uploaded On:<span class="date">${courseContent.dateCreated}</span></p>
                            </a>
                        </c:otherwise>
                    </c:choose> 
                </c:if>
            </c:forEach>

        </div>
        <div class="container">
            <h4>Resources:</h4>
            <c:forEach var="courseContent" items="${courseContents}">


                <c:if test="${courseContent.type!='video'}">
                    <div class="row">

                        <div class="col-md-6 col-md-offset-1">

                            <span class="resource"><b>${courseContent.title}</b></span>
                        </div>

                        <div class="col-md-2" >
                            <a href="#" class="btn btn-link">Delete</a>
                        </div>
                        <div class="col-md-3">
                            <a href="<c:url value='/resources?courseContent=${courseContent.id}'/>" class="btn btn-link">Download</a>
                        </div>

                    </div>
                </c:if> 

            </c:forEach>



        </div>
        <div class="container">
            <h2>Include My test page with courseid as parameter.</h2>
        </div>


    </body>
</html>
