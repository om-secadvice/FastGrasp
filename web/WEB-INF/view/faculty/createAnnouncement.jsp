<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Do Announcement|FastGrasp</title>
        <!-- Bootstrap -->
        <link href="/FastGrasp/css/bootstrap.min.css" rel="stylesheet">


    </head>
    <body>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="/FastGrasp/js/jquery-1.11.3.min.js"></script>

        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="/FastGrasp/js/bootstrap.js"></script>

        <div class="container">
            <h2>Course Announcement</h2>

            <hr/>
            <h4 class="breadcrumb"><a href="<c:url value='explore'/>">Course</a> &gt; <a href="<c:url value='explore/courseDetails'/>"><span>${selectedCourse.title}</span></a></h4>

            <div class="alert alert-danger alert-dismissable fade in">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <center><strong>Alert! </strong><span class="announcement">This is an alert.</span><br/>
                </center>
            </div>

            <form action="<c:url value='createAnnouncement?courseId=${selectedCourse.id}'/>" method="POST">
                <legend>New Announcement</legend>
                <div class="form-group">

                    <!--span >Introduction to modern application development</span--><br/>
                    <label for="title">Title:</label>
                    <input type="text" class="form-control" name="title" required/><br/>
                    <label for="content">Content:</label>
                    <textarea class="form-control" rows="5" name="content" required ></textarea>
                    <br/>
                    <input type="submit" class="btn btn-success" value="Create Announcement"/>
                </div>
            </form>
        </div>
        <br/>

        <div class="container">
            <h4>Previous announcements </h4>

            <c:if test="${!empty courseAnnouncements}">
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                    <c:forEach var="announcement" items="${courseAnnouncements}" varStatus="iter">
                        <c:choose>
                            <c:when test="${iter.index==0}">
                                <div class="panel panel-default">
                                    <div class="panel-heading" role="tab">
                                        <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#${announcement.id}">${announcement.title}</a> &nbsp;&nbsp;<i>${announcement.dateCreated}</i></h4>
                                    </div>
                                    <div id="${announcement.id}" class="panel-collapse collapse in">
                                        <div class="panel-body">${announcement.content}</div>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="panel panel-default">
                                    <div class="panel-heading" role="tab">
                                        <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion" href="#${announcement.id}">${announcement.title}</a> &nbsp;${announcement.dateCreated}</h4>
                                    </div>
                                    <div id="${announcement.id}" class="panel-collapse collapse">
                                        <div class="panel-body">${announcement.content}</div>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>



                    </c:forEach>
                </div></c:if>
        </div>


    </body>
</html>
