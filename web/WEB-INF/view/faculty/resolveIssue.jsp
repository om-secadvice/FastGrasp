<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="en">
  <head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Resolve Issues|FastGrasp</title>
    <!-- Bootstrap -->
	<link href="/FastGrasp/css/bootstrap.min.css" rel="stylesheet">


  </head>
  <body>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="/FastGrasp/js/jquery-1.11.3.min.js"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="/FastGrasp/js/bootstrap.js"></script>
        
        
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
  	<h2>Student Issues</h2>

  <hr/>
  <h4 class="breadcrumb"><a href="#">Course</a> &gt; <a href="#"><span>${selectedCourse.title}</span></a></h4>

  <div class="alert alert-danger alert-dismissable fade in">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
  	<center><strong>Alert! </strong><span class="announcement">This is an alert.</span><br/>
    </center>
  </div>

  <center>
  <label class="radio-inline"><input type="radio" name="filter" value="all" checked/> All Issues</label>
<label class="radio-inline"><input type="radio" name="filter" value="pending"/> Pending</label>
<label class="radio-inline"><input type="radio" name="filter" value="processing"/> Processing</label>
<label class="radio-inline"><input type="radio" name="filter" value="resolved"/> Resolved</label>
<label class="radio-inline"><input type="radio" name="filter" value="closed"/> Closed</label>

</center>
<hr/>

  <div class="panel-group" id="accordion1" role="tablist" aria-multiselectable="true">      
    <c:forEach var="issueThread" items="${issueThread}" varStatus="iter">  
    <div class="panel panel-default">
      <div class="panel-heading" role="tab">
        <h4 class="panel-title"><a data-toggle="collapse" data-parent="#accordion1" href="#collapseOne1">#${issueThread.id}</a> Issue Title 1&nbsp;&nbsp;&nbsp;<label class="label label-danger">${issueThread.status}</label></h4>
      </div>
      <div id="collapseOne1" class="panel-collapse collapse in">
        <div class="panel-body">
        <p><b>Created On:</b> <span class="date1">${issueThread.date_created}</span></p>
        <p><b>Last Modified:</b> <span class="date2">${issueThread.date_terminated}</span></p>
        <p><b>Reported by:</b> <span class="studentsname">${user.name}</span></p>
        <c:forEach var="issuePost" items="${issuePost}" varStatus="iter">
        <c:if test="${issueThread.id==issuePost.issue_thread_id}">
        <p><b>${issuePost.author_name}</b></p>
        <p><span class="bg-info">${issuePost.content}</span></p>
        </c:if>
        </c:forEach>
        <c:when test="${sessionScope.userType == \"faculty\"}">
        <form action="/faculty/submitSolution" method="post">
        <div class="form-group">

    <!--span >Introduction to modern application development</span--><br/>

        <label for="solution">Solution:</label>
        <div id="solution_in_case_of_students1"></div>
        <textarea class="form-control" rows="5" name="solution" required ></textarea>
        <div class="row">
          <div class="col-lg-6">
            <div class="input-group"><span class="input-group-addon">
              <label width="60px"><input type="checkbox" name="issueclosed" style="vertical-align: bottom;"> Closed&nbsp;&nbsp;&nbsp;&nbsp;</label>
              </span>
              <input type="text" class="form-control" placeholder="Closing Remarks" name="closingremarks">
            </div>
            <!-- /input-group -->
          </div>
          <!-- /.col-lg-6 -->
          <div class="col-lg-6">
            <div class="input-group"><span class="input-group-addon">
              <label width="60px"><input type="checkbox" name="issueresolved" style="vertical-align: bottom;"> Resolved
              </label></span>
              <input type="text" class="form-control" placeholder="Remarks" name="resolvedremarks">
            </div>
            <!-- /input-group -->
          </div>
          <!-- /.col-lg-6 -->
        </div>
        <br/>
        <input type="hidden" name="issueThreadId" value="${issueThread.id}">
        <input type="submit" class="btn btn-success" value="Submit"/>
        </div>
        </form>
        </c:when>
        <c:when test="${sessionScope.userType == \"student\"}">
        <form action="/student/submitIssue" method="post">
        <div class="form-group">

    <!--span >Introduction to modern application development</span--><br/>

        <label for="solution">Solution:</label>
        <div id="solution_in_case_of_students1"></div>
        <textarea class="form-control" rows="5" name="solution" required ></textarea>
        <p>
        <div class="row">
          <div class="col-lg-6">
            <div class="input-group"><span class="input-group-addon">
              <label width="60px"><input type="checkbox" name="issueclosed" style="vertical-align: bottom;"> Closed&nbsp;&nbsp;&nbsp;&nbsp;</label>
              </span>
              <input type="text" class="form-control" placeholder="Closing Remarks" name="closingremarks">
            </div>
            <!-- /input-group -->
          </div>
          <!-- /.col-lg-6 -->
          <div class="col-lg-6">
            <div class="input-group"><span class="input-group-addon">
              <label width="60px"><input type="checkbox" name="issueresolved" style="vertical-align: bottom;"> Resolved
              </label></span>
              <input type="text" class="form-control" placeholder="Remarks" name="resolvedremarks">
            </div>
            <!-- /input-group -->
          </div>
          <!-- /.col-lg-6 -->
        </div>
        </p>
        <br/>
        <input type="submit" class="btn btn-success" value="Submit"/>
        </div>
        </form>
        </c:when>
        </div>
      </div>
    </div>
    </c:forEach>
  </div>

  </div>
  </body>
</html>
