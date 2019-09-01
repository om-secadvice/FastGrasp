<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dashboard-Faculty|FastGrasp.edu</title>
    <!-- Bootstrap -->
	<link href="/FastGrasp/css/bootstrap.min.css" rel="stylesheet">


  </head>
  <body style="margin-bottom:50px;">
  <nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#inverseNavbar1" aria-expanded="false"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
        <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-book" aria-hidden="true"></span> &nbsp;FastGrasp</a></div>
      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="inverseNavbar1">
        <ul class="nav navbar-nav">
            <li class="active"><a href="<c:url value='dashboard'/>"><span class="glyphicon glyphicon-dashboard" aria-hidden="true"></span> Dashboard</a></li>
            <li><a href="<c:url value='profile'/>"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> My Profile</a></li>
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-edit" ></span> Courses <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="<c:url value='explore'/>">Explore</a></li>
              <li><a href="<c:url value='initiateCourse'/>">Initiate</a></li>
              <li><a href="<c:url value='myCourses'/>">My Courses</a></li>

            </ul>
          </li>
        </ul>

        <ul class="nav navbar-nav navbar-right">

          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Issues<span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="<c:url value='createIssues'/>">Create</a></li>
              <li><a href="<c:url value='myIssues'/>">History</a></li>

            </ul>
          </li>
          <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
        </ul>
      </div>
      <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
  </nav>



<div class="container">
<div class="alert alert-danger alert-dismissable fade in">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
  	<center><strong>Alert! </strong><span class="announcement">This is an alert.</span><br/>
    </center>
  </div>

  <div class="row">

      <div class="col-md-2 col-md-offset-1 col-sm-2">

	    <img src="${currentUser.profilePic}" class="img-circle img-responsive" alt="Placeholder image">


      </div>
      <div class="col-md-5 col-sm-6">
      <div class="caption" style="vertical-align:bottom">
	        <h3>${currentUser.name}</h3>
	        <span>${currentUser.designation}</span><br/>
                <span>${currentUser.institution}</span>

        </div>
      </div>
      <div class="col-md-4 col-sm-4" style="text-align:center;">

      <h4> Course Initiation</h4>
    <a href="#"><span class="label label-warning well-sm">Pending <span class="badge">${pendingInitiation}</span></span></a>

    &nbsp;&nbsp;<a href="#" ><span class="label label-success well-sm">Completed <span class="badge">${completedInitiation}</span></span></a>
     </div>

  </div>

  </div>
  <hr/>



  <h3 style="padding-left:20px;color:#118B72">Your Ongoing Courses:</h3>


  <div class="container">

      <c:forEach items="${myCourses}" var="course" varStatus="i">
          <c:choose>
            <c:when test="${i.index%2==0}">
                  
            <div class="row" style="margin-bottom:0px">
                    <center>
                    <div class="col-xs-1 ">
                        <c:choose>
                                    <c:when test="${selectedCourse.id==course.id}">
                                        
                                            <input type="radio" name="course" value="${course.id}" style="margin:25px auto;" checked>
                                        
                                    </c:when>
                                    <c:otherwise>
                                        
                                            <input type="radio" name="course" value="${course.id}" style="margin:25px auto;">
                                        
                                    </c:otherwise>
                        </c:choose>
                    
                    </div></center>
                    <div class="list-group col-xs-11">
                        <a href="<c:url value='courseLecture?courseId=${course.id}'/>" class="list-group-item active" >
                      <h4 class="list-group-item-heading">${course.title}</h4>
                            <p class="list-group-item-text" ><b>Start Date:</b> ${course.startDate}</p>
                            <p class="list-group-item-text" ><b>End Date:</b> ${course.endDate}</p>
                      </a>
                      </div>
                      </div>
            </c:when>
            <c:otherwise>
                     <div class="row" style="margin-bottom:0px">
                    <center>
                    <div class="col-xs-1 ">
                        <c:choose>
                                    <c:when test="${selectedCourse.id==course.id}">
                                        
                                            <input type="radio" name="course" value="${course.id}" style="margin:25px auto;" checked>
                                        
                                    </c:when>
                                    <c:otherwise>
                                        
                                            <input type="radio" name="course" value="${course.id}" style="margin:25px auto;">
                                        
                                    </c:otherwise>
                        </c:choose>
                    </div></center>
                    <div class="list-group col-xs-11">
                        <a href="<c:url value='courseLecture?courseId=${course.id}'/>" class="list-group-item" >
                      <h4 class="list-group-item-heading">${course.title}</h4>
                      <p class="list-group-item-text" ><b>Start Date:</b> ${course.startDate}</p>
                            <p class="list-group-item-text" ><b>End Date:</b> ${course.endDate}</p>
                      </a>
                      </div>
                      </div>
            </c:otherwise>
         </c:choose>

      </c:forEach>
  </div>
    
  <hr/>
 
  <div class="container-fluid" style="text-align:center;">
<!--    <div class="btn-group btn-group-justified" role="group" aria-label="Justified button group with nested dropdown">
--> <c:choose>
        <c:when test="${!empty selectedCourse}">
            <a href="<c:url value='uploadContent?courseId=${selectedCourse.id}'/>" class="btn btn-success" role="button">Upload Content</a>
            <a href="<c:url value='createTest?courseId=${selectedCourse.id}'/>" class="btn btn-link" role="button">Create Test</a>
            <a href="<c:url value='createAnnouncement?courseId=${selectedCourse.id}'/>" class="btn btn-success" role="button">Announcement</a>
            <a href="<c:url value='resolveIssue?courseId=${selectedCourse.id}'/>" class="btn btn-link" role="button">Resolve Issues</a>
            <a href="<c:url value='viewFeedback?courseId=${selectedCourse.id}'/>" class="btn btn-success" role="button">View Feedback</a>
        </c:when>
        <c:otherwise>
            <a href="#" class="btn btn-success" role="button">Upload Content</a>
            <a href="#" class="btn btn-link" role="button">Create Test</a>
            <a href="#" class="btn btn-success" role="button">Announcement</a>
            <a href="#" class="btn btn-link" role="button">Resolve Issues</a>
            <a href="#" class="btn btn-success" role="button">View Feedback</a>
        </c:otherwise>
        
    </c:choose>


  </div>
  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="/FastGrasp/js/jquery-1.11.3.min.js"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="/FastGrasp/js/bootstrap.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('input[type="radio"]').click(function(){
                    window.open("<c:url value='dashboard?selected='/>"+this.value,"_self");
                });
            });
        </script>
  </body>
</html>



<%--<c:url value='uploadContent?courseId=${selectedCourse.id}'/>--%>
