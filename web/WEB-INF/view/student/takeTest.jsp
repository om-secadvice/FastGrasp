<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>New Test</title>
<link href="/FastGrasp/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script src="/FastGrasp/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="/FastGrasp/js/bootstrap.js" type="text/javascript"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>
    
    
    
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
        	<div class="alert alert-info alert-dismissable"><a class="panel-close close" data-dismiss="alert">×</a> <em class="fa fa-coffee"></em> This is an <strong>.alert</strong>. Use this to show important messages to the user. </div>
	</div>
    <div class="container">
    	<div class="row" style="font-weight:bold">
        	<p>${selectedCourse.title}</p>
            <div class="col-md-4">
            	<span>${testId.title}</span>
            </div>
            <div class="col-md-4" style="text-align:center">
            	<span>${testId.dueDate}</span>
            </div>
            <div class="col-md-4" style="text-align:right">
            	<span>${testId.fullMarks}</span>
            </div>
        </div>
            <form action="submitResponse" method="post">
            
            <c:forEach var="testQuestion" items="${testQuestions}" varStatus="iter">
                <div class="row">
                    <div class="col-md-10">
                	<h5><b>${iter.count}  ${testQuestion.question}</b></h5>
                    </div>
                    <div class="col-md-2"  style="padding-top:10px;text-align:right">
                    	<span class="marks"><b>${testQuestion.marks}</b></span>
                    </div>
                </div>
                <div class="radio">
                <label><input type="radio" name="${testQuestion.id}" value="1">${testQuestion.choiceOne}</label>
                </div>
                <div class="radio">
                <label><input type="radio" name="${testQuestion.id}" value="2">${testQuestion.choiceTwo}</label>
                </div>
                <div class="radio">
                <label><input type="radio" name="${testQuestion.id}" value="3">${testQuestion.choiceThree}</label>
                </div>
                <div class="radio">
                <label><input type="radio" name="${testQuestion.id}" value="4">${testQuestion.choiceFour}</label>
                </div>
                <br/>
            
            </c:forEach>
                
                
            <button class="btn btn-success">Submit</button>
        </form>
    </div>
</body>
</html>
