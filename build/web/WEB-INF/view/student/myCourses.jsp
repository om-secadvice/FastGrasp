<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Courses</title>
<link href="/FastGrasp/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>
    <jsp:useBean id="now" class="java.util.Date"/>
    <div class="container">
        <div class="alert alert-info alert-dismissable"><a class="panel-close close" data-dismiss="alert">×</a> <em class="fa fa-coffee"></em> This is an <strong>.alert</strong>. Use this to show important messages to the user. </div>
    	<div class="panel-group" id="accordion1">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion1" href="#collapseOne1">Ongoing Courses</a></h4>
            </div>
            <div id="collapseOne1" class="panel-collapse collapse in">
                <c:forEach var="course" items="${myCourses}" varStatus="iter">
                    <c:if test="${course.startDate<now && course.endDate>now}">
                        <div class="panel-body"><a href="<c:url value='courseLecture?courseId=${course.id}'/>">${course.title}</a></div>
                    </c:if>
            </c:forEach>
              
            </div>
          </div>
          <div class="panel panel-default">
            <div class="panel-heading">
              <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion1" href="#collapseTwo1">Completed Courses</a></h4>
            </div>
            <div id="collapseTwo1" class="panel-collapse collapse">
              <c:forEach var="course" items="${myCourses}" varStatus="iter">
                    <c:if test="${course.startDate>now}">
                        <div class="panel-body"><a href="<c:url value='courseLecture?courseId=${course.id}'/>">${course.title}</a></div>
                    </c:if>
            </c:forEach>
            </div>
          </div>
        </div>
	</div>
<script src="/FastGrasp/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="/FastGrasp/js/bootstrap.js" type="text/javascript"></script>
</body>
</html>
